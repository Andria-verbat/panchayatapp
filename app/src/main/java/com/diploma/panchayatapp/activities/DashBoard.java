package com.diploma.panchayatapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.diploma.panchayatapp.databinding.ActivityDashBoardBinding;
import com.diploma.panchayatapp.databinding.ActivityMainBinding;
import com.diploma.panchayatapp.localdb.EGramDatabase;
import com.diploma.panchayatapp.model.PanchayatModel;

public class DashBoard extends AppCompatActivity {
    ActivityDashBoardBinding binding;
    EGramDatabase eGramDatabase;
    PanchayatModel panchayatModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashBoardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        eGramDatabase = EGramDatabase.getInstance(DashBoard.this);
       // eGramDatabase.eGramDao().deletePanchayatDetails();
        if(eGramDatabase.eGramDao().getAllPanchayatDetails().size()==0){
            panchayatModel=new PanchayatModel("Anakkara Panchayat","20.95","24699","1178.95","17",
                    "8512","Thrithala","Ponnani","Thrithala",1,"Anakkara","");
            eGramDatabase.eGramDao().insertPanchayatDetails(panchayatModel);
            panchayatModel=new PanchayatModel("Chalissery Panchayat","19.20","24238","1262.40","15",
                    "6800","Thrithala","Ponnani","Thrithala",1,"Chalissery","");
            eGramDatabase.eGramDao().insertPanchayatDetails(panchayatModel);
            panchayatModel=new PanchayatModel("Kappur Panchayat","23.50","31337","1333.49","18",
                    "8684","Thrithala","Ponnani","Thrithala",1,"Kappur","");
            eGramDatabase.eGramDao().insertPanchayatDetails(panchayatModel);
            panchayatModel=new PanchayatModel("Koppam Panchayat","25.70","30169","1173.89","17",
                    "9736","Pattambi","Palakkad","Pattambi",1,"Koppam","");
            eGramDatabase.eGramDao().insertPanchayatDetails(panchayatModel);
            panchayatModel=new PanchayatModel("Kulukkallur Panchayat","22.74","27971","1230.04","17",
                    "6847","Pattambi","Palakkad","Pattambi",1,"Kulukkallur","");
            eGramDatabase.eGramDao().insertPanchayatDetails(panchayatModel);
            panchayatModel=new PanchayatModel("Muthuthala Panchayat","19.95","24861","1246.17","15",
                    "5242","Pattambi","Palakkad","Pattambi",1,"Muthuthala","");
            eGramDatabase.eGramDao().insertPanchayatDetails(panchayatModel);
            panchayatModel=new PanchayatModel("Nagalassery Panchayat","26.20","27606","1053.66","17",
                    "8258","Thrithala","Ponnani","Thrithala",1,"Nagalassery","");
            eGramDatabase.eGramDao().insertPanchayatDetails(panchayatModel);
            panchayatModel=new PanchayatModel("Ongallur Panchayat","31.68","43271","1365.88","22",
                    "8517","Pattambi","Palakkad","Pattambi",2,"Ongallur 1,Ongallur 2","");
            eGramDatabase.eGramDao().insertPanchayatDetails(panchayatModel);
            panchayatModel=new PanchayatModel("Paruthur Panchayat","20.71","26638","1286.24","16",
                    "5410","Pattambi","Ponnani","Thrithala",1,"Paruthur","");
            eGramDatabase.eGramDao().insertPanchayatDetails(panchayatModel);
            panchayatModel=new PanchayatModel("Thirumittakkode Panchayat","32.31","31998","990.34","18",
                    "8323","Thrithala","Ponnani","Thrithala",2,"Thirumittakkode 1,Thirumittakkode 2","");
            eGramDatabase.eGramDao().insertPanchayatDetails(panchayatModel);
            panchayatModel=new PanchayatModel("Thiruvegappura Panchayat","20.46","33942","1658.94","18",
                    "8408","Pattambi","Palakkad","Pattambi",1,"Thiruvegappura","");
            eGramDatabase.eGramDao().insertPanchayatDetails(panchayatModel);
            panchayatModel=new PanchayatModel("Thrithala Panchayat","22.78","27796","1220.19","17",
                    "8176","Thrithala","Ponnani","Thrithala",2,"Thrithala","");
            eGramDatabase.eGramDao().insertPanchayatDetails(panchayatModel);
            panchayatModel=new PanchayatModel("Vallapuzha Panchayat","21.64","28018","1294.73","16",
                    "9856","Ottappalam","Palakkad","Pattambi",1,"Vallapuzha","");
            eGramDatabase.eGramDao().insertPanchayatDetails(panchayatModel);
            panchayatModel=new PanchayatModel("Vilayur Panchayat","17.78","23389","1315.47","15",
                    "6753","Pattambi","Palakkad","Pattambi",1,"Vilayur","");
            eGramDatabase.eGramDao().insertPanchayatDetails(panchayatModel);
        }


        binding.rlAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, AdminLogin.class));
                finish();
            }
        });


        binding.rlUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoard.this, UserDashBoard.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}