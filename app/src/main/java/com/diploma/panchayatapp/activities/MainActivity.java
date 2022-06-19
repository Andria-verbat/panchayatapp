package com.diploma.panchayatapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.diploma.panchayatapp.R;
import com.diploma.panchayatapp.utils.constants;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(constants.getType(MainActivity.this).equals("Admin")){
                    startActivity(new Intent(MainActivity.this, AdminHome.class));
                    finish();
                }else if(constants.getType(MainActivity.this).equals("User")){
                    startActivity(new Intent(MainActivity.this, UserHomePage.class));
                    finish();
                }else {
                    startActivity(new Intent(MainActivity.this, DashBoard.class));
                    finish();
                }

            }
        }, SPLASH_TIME_OUT);
    }
}