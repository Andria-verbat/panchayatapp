package com.diploma.panchayatapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Toast;

import com.diploma.panchayatapp.R;
import com.diploma.panchayatapp.databinding.ActivityUserRegistrationBinding;
import com.diploma.panchayatapp.localdb.EGramDatabase;

public class UserRegistration extends AppCompatActivity {

    ActivityUserRegistrationBinding binding;
    EGramDatabase database;
    boolean isClickedPwd = false, isClickedCPwd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserRegistrationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        database = EGramDatabase.getInstance(UserRegistration.this);

        binding.btnUseReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.edtMobnum.getText().toString().equals("") &&
                        !binding.edtPwd.getText().toString().equals("")) {
                    if (binding.edtPwd.getText().toString().equals(binding.edtCpwd.getText().toString())) {
                        if (database.eGramDao().getUserDetails(Long.parseLong(binding.edtMobnum.getText().toString())).size() > 0) {
                            /*for (int i = 0; i < database.eGramDao().getPhoneNUmber().size(); i++) {
                                if (Long.parseLong(binding.edtMobnum.getText().toString()) == database.eGramDao().getPhoneNUmber().get(i)) {*/
                                    //phone = 0;

                                    database.eGramDao().update(Long.valueOf(binding.edtMobnum.getText().toString()),
                                            binding.edtPwd.getText().toString());
                                    Toast.makeText(UserRegistration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(UserRegistration.this, UserDashBoard.class));
                                    finish();
                             /*       break;
                                } else {
                                    Toast.makeText(UserRegistration.this, "Mobile number not registered", Toast.LENGTH_SHORT).show();
                                }
                            }*/
                        } else {
                            Toast.makeText(UserRegistration.this, "Mobile number not registered", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(UserRegistration.this, "Password Mismatch", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UserRegistration.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                }

            }
        });


        binding.imgPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isClickedPwd) {
                    isClickedPwd = false;
                    //Hide Password
                    binding.edtPwd.setTransformationMethod(PasswordTransformationMethod.
                            getInstance());
                    binding.imgPwd.setImageResource(R.drawable.ic_baseline_visibility_off_24);

                } else if (!isClickedPwd) {
                    isClickedPwd = true;
                    //Show Password
                    binding.edtPwd.setTransformationMethod(HideReturnsTransformationMethod.
                            getInstance());
                    binding.imgPwd.setImageResource(R.drawable.ic_baseline_visibility_24);
                }
            }
        });


        binding.imgCpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isClickedCPwd) {
                    isClickedCPwd = false;
                    //Hide Password
                    binding.edtCpwd.setTransformationMethod(PasswordTransformationMethod.
                            getInstance());
                    binding.imgCpwd.setImageResource(R.drawable.ic_baseline_visibility_off_24);

                } else if (!isClickedCPwd) {
                    isClickedCPwd = true;
                    //Show Password
                    binding.edtCpwd.setTransformationMethod(HideReturnsTransformationMethod.
                            getInstance());
                    binding.imgCpwd.setImageResource(R.drawable.ic_baseline_visibility_24);
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(UserRegistration.this, UserDashBoard.class));
        finish();
    }

}
