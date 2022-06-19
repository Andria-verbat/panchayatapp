package com.diploma.panchayatapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Toast;

import com.diploma.panchayatapp.R;

import com.diploma.panchayatapp.databinding.ActivityAdminLoginBinding;
import com.diploma.panchayatapp.utils.constants;

public class AdminLogin extends AppCompatActivity {

    ActivityAdminLoginBinding binding;
    boolean isClicked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logincheck(binding.edtUsername.getText().toString(),
                        binding.edtPwd.getText().toString());
            }
        });


        binding.imgPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isClicked){
                    isClicked=false;
                    //Hide Password
                    binding.edtPwd.setTransformationMethod(PasswordTransformationMethod.
                            getInstance());
                    binding.imgPwd.setImageResource(R.drawable.ic_baseline_visibility_off_24);

                }else if(!isClicked){
                    isClicked=true;
                    //Show Password
                    binding.edtPwd.setTransformationMethod(HideReturnsTransformationMethod.
                            getInstance());
                    binding.imgPwd.setImageResource(R.drawable.ic_baseline_visibility_24);
                }
            }
        });
    }

    private void logincheck(String uname, String pwd) {
        if(!uname.equals("")&&!pwd.equals("")){
            if(uname.equalsIgnoreCase("Anakkara")&&
                    pwd.equalsIgnoreCase("Anakkara@123")){
                sendtoActivity("Anakkara Panchayat");
            }
            else if(uname.equalsIgnoreCase("Chalissery")&&
                    pwd.equalsIgnoreCase("Chalissery@123")){
                sendtoActivity("Chalissery Panchayat");
            }
            else if(uname.equalsIgnoreCase("Kappur")&&
                    pwd.equalsIgnoreCase("Kappur@123")){
                sendtoActivity("Kappur Panchayat");
            }
            else if(uname.equalsIgnoreCase("Koppam")&&
                    pwd.equalsIgnoreCase("Koppam@123")){
                sendtoActivity("Koppam Panchayat");
            }
            else if(uname.equalsIgnoreCase("Kulukkallur")&&
                    pwd.equalsIgnoreCase("Kulukkallur@123")){
                sendtoActivity("Kulukkallur Panchayat");
            }
            else if(uname.equalsIgnoreCase("Muthuthala")&&
                    pwd.equalsIgnoreCase("Muthuthala@123")){
                sendtoActivity("Muthuthala Panchayat");
            }
            else if(uname.equalsIgnoreCase("Ongallur")&&
                    pwd.equalsIgnoreCase("Ongallur@123")){
                sendtoActivity("Ongallur Panchayat");
            }
            else if(uname.equalsIgnoreCase("Nagalassery")&&
                    pwd.equalsIgnoreCase("Nagalassery@123")){
                sendtoActivity("Nagalassery Panchayat");
            }
            else if(uname.equalsIgnoreCase("Paruthur")&&
                    pwd.equalsIgnoreCase("Paruthur@123")){
                sendtoActivity("Paruthur Panchayat");
            }
            else if(uname.equalsIgnoreCase("Thirumittakkode")&&
                    pwd.equalsIgnoreCase("Thirumittakkode@123")){
                sendtoActivity("Thirumittakkode Panchayat");
            }
            else if(uname.equalsIgnoreCase("Thiruvegappura")&&
                    pwd.equalsIgnoreCase("Thiruvegappura@123")){
                sendtoActivity("Thiruvegappura Panchayat");
            }
            else if(uname.equalsIgnoreCase("Thrithala")&&
                    pwd.equalsIgnoreCase("Thrithala@123")){
                sendtoActivity("Thrithala Panchayat");
            }
            else if(uname.equalsIgnoreCase("Vallapuzha")&&
                    pwd.equalsIgnoreCase("Vallapuzha@123")){
                sendtoActivity("Vallapuzha Panchayat");
            }
            else if(uname.equalsIgnoreCase("Vilayur")&&
                    pwd.equalsIgnoreCase("Vilayur@123")){
                sendtoActivity("Vilayur Panchayat");
            }
            else {
                Toast.makeText(this, "Enter valid credentials to login", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Enter valid credentials to login", Toast.LENGTH_SHORT).show();
        }

    }

    private void sendtoActivity(String name) {
        Intent i=new Intent(AdminLogin.this,AdminHome.class);
        constants.setType(AdminLogin.this,"Admin");
        constants.setName(AdminLogin.this,name);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminLogin.this, DashBoard.class));
        finish();
    }
}