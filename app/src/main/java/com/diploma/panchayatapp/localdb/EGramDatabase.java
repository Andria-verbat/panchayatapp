package com.diploma.panchayatapp.localdb;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.diploma.panchayatapp.model.ApplicationModel;
import com.diploma.panchayatapp.model.PanchayatModel;
import com.diploma.panchayatapp.model.ProjectModel;


@Database(entities = {ProjectModel.class, ApplicationModel.class, PanchayatModel.class},
        exportSchema = false, version = 1)

public abstract class EGramDatabase extends RoomDatabase {

    private static final String DB_NAME = "egram_db";
    private static EGramDatabase instance;

    public static synchronized EGramDatabase getInstance(Context con) {
        if (instance == null) {
            instance = Room.databaseBuilder(con.getApplicationContext(), EGramDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract EGramDao eGramDao();
}
