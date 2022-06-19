package com.diploma.panchayatapp.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.diploma.panchayatapp.R;
import com.diploma.panchayatapp.databinding.ActivityAdminLoginBinding;
import com.diploma.panchayatapp.databinding.ActivityUserDashBoardBinding;
import com.diploma.panchayatapp.localdb.EGramDatabase;
import com.diploma.panchayatapp.utils.constants;

public class UserDashBoard extends AppCompatActivity {
    ActivityUserDashBoardBinding binding;
    boolean isClicked=false,isClickedFP=false;
    EGramDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserDashBoardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        database=EGramDatabase.getInstance(UserDashBoard.this);
        binding.Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserDashBoard.this, UserRegistration.class));
                finish();
            }
        });

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.edtUsername.getText().toString().equals("")&&
                !binding.edtPwd.getText().toString().equals("")){
                    if(database.eGramDao().getUser(Long.valueOf(binding.edtUsername.getText().toString()),
                            binding.edtPwd.getText().toString()).size()>0){
                        Intent intent=new Intent(UserDashBoard.this, UserHomePage.class);

                        constants.setName(UserDashBoard.this,database.eGramDao().getUser(Long.valueOf(binding.edtUsername.getText().toString()),
                                binding.edtPwd.getText().toString()).get(0).getFullname());
                        constants.setType(UserDashBoard.this,"User");
                        constants.setPhn(UserDashBoard.this,Long.valueOf(binding.edtUsername.getText().toString()));
                        startActivity(intent);
                        finish();
                        Toast.makeText(UserDashBoard.this, "Logged In", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(UserDashBoard.this, "Enter valid credentials", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(UserDashBoard.this, "Enter the credentials", Toast.LENGTH_SHORT).show();
                }

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

        binding.forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(UserDashBoard.this);
                final View view1 = inflater.inflate(R.layout.frgetpwd, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(UserDashBoard.this);
                builder.setView(view1);
                Button submitBtn=view1.findViewById(R.id.submitBtn);
                EditText edt_mobnum=view1.findViewById(R.id.edt_mobnum);
                EditText edt_pwd=view1.findViewById(R.id.edt_pwd);
                ImageView img_pwd=view1.findViewById(R.id.img_pwd);
                builder.setCancelable(true);
                final AlertDialog alert = builder.create();
                alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alert.show();

                img_pwd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(isClickedFP){
                            isClickedFP=false;
                            //Hide Password
                            edt_pwd.setTransformationMethod(PasswordTransformationMethod.
                                    getInstance());
                            img_pwd.setImageResource(R.drawable.ic_baseline_visibility_off_24);

                        }else if(!isClickedFP){
                            isClickedFP=true;
                            //Show Password
                            edt_pwd.setTransformationMethod(HideReturnsTransformationMethod.
                                    getInstance());
                            img_pwd.setImageResource(R.drawable.ic_baseline_visibility_24);
                        }
                    }
                });

                submitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        for (int i = 0; i < database.eGramDao().getPhoneNUmber().size(); i++) {
                            if (Long.parseLong(edt_mobnum.getText().toString()) == database.eGramDao().getPhoneNUmber().get(i)) {

                                database.eGramDao().update(Long.valueOf(edt_mobnum.getText().toString()),
                                        binding.edtPwd.getText().toString());
                                alert.dismiss();
                                Toast.makeText(UserDashBoard.this, "Password Successfully", Toast.LENGTH_SHORT).show();

                                break;
                            } else {
                                Toast.makeText(UserDashBoard.this, "Mobile number not registered", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(UserDashBoard.this, DashBoard.class));
        finish();
    }
}