package com.diploma.panchayatapp.activities;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.TypeConverter;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.diploma.panchayatapp.R;
import com.diploma.panchayatapp.adapter.ProjectAdapter;
import com.diploma.panchayatapp.databinding.ActivityAdminHomeBinding;
import com.diploma.panchayatapp.localdb.EGramDatabase;
import com.diploma.panchayatapp.model.ApplicationModel;
import com.diploma.panchayatapp.model.ProjectModel;
import com.diploma.panchayatapp.utils.ImageBitmapString;
import com.diploma.panchayatapp.utils.constants;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AdminHome extends AppCompatActivity {
    ActivityAdminHomeBinding binding;
    EGramDatabase eGramDatabase;
    Date currentTime;
    Calendar calendar;
    ProjectModel projectModel;
    ApplicationModel applicationModel;
    int phone=1;
    String imageSource="";
    Bitmap bitmap;
    ImageView profImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        projectModel = new ProjectModel();
        applicationModel = new ApplicationModel();
        eGramDatabase = EGramDatabase.getInstance(AdminHome.this);
        binding.textView.setText(constants.getName(AdminHome.this));

        showList();

        binding.imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constants.logout(AdminHome.this);
            }
        });

        binding.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProject();
            }
        });

        binding.imgAddFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applicationForm();

            }
        });

        binding.imgAddlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLink();
            }
        });
    }

    private void addLink() {
        LayoutInflater inflater = LayoutInflater.from(AdminHome.this);
        final View view = inflater.inflate(R.layout.addlink, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminHome.this);
        builder.setView(view);
        Button addlink_btn = view.findViewById(R.id.addlink_btn);
        EditText edt_applink = view.findViewById(R.id.edt_applink);

        builder.setCancelable(true);
        final AlertDialog alert = builder.create();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.show();

        addlink_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edt_applink.getText().toString().equals("")&& URLUtil.isValidUrl(edt_applink.getText().toString())){
                    eGramDatabase.eGramDao().updatePanchayat(constants.getName(AdminHome.this),
                            edt_applink.getText().toString());
                    Toast.makeText(AdminHome.this, "Successfully added the link", Toast.LENGTH_SHORT).show();
                    alert.dismiss();

                }else {
                    Toast.makeText(AdminHome.this, "Please enter the valid link", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void applicationForm() {
        LayoutInflater inflater = LayoutInflater.from(AdminHome.this);
        final View view = inflater.inflate(R.layout.applicationform, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminHome.this);
        builder.setView(view);
        Button applnbtn_submit = view.findViewById(R.id.applnbtn_submit);
        EditText edt_dob = view.findViewById(R.id.edt_dob);
        EditText edt_mobnum = view.findViewById(R.id.edt_mobnum);
        EditText edt_fullname = view.findViewById(R.id.edt_fullname);
        EditText edt_panchaytname = view.findViewById(R.id.edt_panchaytName);
        profImage = view.findViewById(R.id.profImage);
        edt_panchaytname.setText(constants.getName(AdminHome.this));
        builder.setCancelable(true);
        final AlertDialog alert = builder.create();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.show();

        profImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImagesFromGallery();


            }
        });
        edt_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year, month, dayOfMonth;
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AdminHome.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                if (month == 0) {
                                    edt_dob.setText(day + "-" + "Jan" + "-" + year);

                                } else if (month == 1) {
                                    edt_dob.setText(day + "-" + "Feb" + "-" + year);

                                } else if (month == 2) {
                                    edt_dob.setText(day + "-" + "Mar" + "-" + year);

                                } else if (month == 3) {
                                    edt_dob.setText(day + "-" + "Apr" + "-" + year);

                                } else if (month == 4) {
                                    edt_dob.setText(day + "-" + "May" + "-" + year);

                                } else if (month == 5) {
                                    edt_dob.setText(day + "-" + "Jun" + "-" + year);

                                } else if (month == 6) {
                                    edt_dob.setText(day + "-" + "Jul" + "-" + year);

                                } else if (month == 7) {
                                    edt_dob.setText(day + "-" + "Aug" + "-" + year);

                                } else if (month == 8) {
                                    edt_dob.setText(day + "-" + "Sep" + "-" + year);

                                } else if (month == 9) {
                                    edt_dob.setText(day + "-" + "Oct" + "-" + year);

                                } else if (month == 10) {
                                    edt_dob.setText(day + "-" + "Nov" + "-" + year);

                                } else if (month == 11) {
                                    edt_dob.setText(day + "-" + "Dec" + "-" + year);

                                }
                            }
                        }, year, month, dayOfMonth);
                //  datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        applnbtn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(constants.isValidPhoneNumber(edt_mobnum.getText().toString())
                &&!edt_fullname.getText().toString().equals("")
                        &&!imageSource.equals("")
                &&!edt_dob.getText().toString().equals("")
                &&!edt_panchaytname.getText().toString().equals("")){
                    //phone = Integer.parseInt(edt_mobnum.getText().toString());
                    for (int i = 0; i < eGramDatabase.eGramDao().getPhoneNUmber().size(); i++) {
                        if (Long.parseLong(edt_mobnum.getText().toString())==eGramDatabase.eGramDao().getPhoneNUmber().get(i)) {
                            phone = 0;
                            Toast.makeText(AdminHome.this, "Mobile Number Already Exists", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    if (phone != 0) {
                        alert.dismiss();
                        applicationModel.setFullname(edt_fullname.getText().toString());
                        applicationModel.setMobNum(Long.parseLong(edt_mobnum.getText().toString()));
                        applicationModel.setProfImg(imageSource);
                        applicationModel.setDob(edt_dob.getText().toString());
                        applicationModel.setPanchayatName(edt_panchaytname.getText().toString());
                        eGramDatabase.eGramDao().insertApplnForm(applicationModel);

                        Toast.makeText(AdminHome.this, "Successfully submitted the application", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AdminHome.this, "Enter all fields",
                                Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(AdminHome.this, "Enter valid details." +
                                    "Cannot start mobile number with zero.",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void showList() {
        ProjectAdapter projectAdapter = new ProjectAdapter(AdminHome.this, eGramDatabase.eGramDao()
                .getAllProjects());
        //constants.getName(AdminHome.this))
        binding.projectList.setHasFixedSize(true);
        binding.projectList.setLayoutManager(new LinearLayoutManager(AdminHome.this));
        binding.projectList.setAdapter(projectAdapter);

    }

    private void addProject() {
        LayoutInflater inflater = LayoutInflater.from(AdminHome.this);
        final View view = inflater.inflate(R.layout.addproject, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminHome.this);
        builder.setView(view);
        Button pbtn_submit = view.findViewById(R.id.pbtn_submit);
        EditText edt_pdate = view.findViewById(R.id.edt_pdate);
        EditText edt_pduration = view.findViewById(R.id.edt_pduration);
        EditText edt_pdesc = view.findViewById(R.id.edt_pdesc);
        EditText edt_pname = view.findViewById(R.id.edt_pname);
        EditText edt_panchaytname = view.findViewById(R.id.edt_panchaytName);
        edt_panchaytname.setText(constants.getName(AdminHome.this));
        builder.setCancelable(true);
        final AlertDialog alert = builder.create();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.show();
        edt_pdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year, month, dayOfMonth;
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AdminHome.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                if (month == 0) {
                                    edt_pdate.setText(day + "-" + "Jan" + "-" + year);

                                } else if (month == 1) {
                                    edt_pdate.setText(day + "-" + "Feb" + "-" + year);

                                } else if (month == 2) {
                                    edt_pdate.setText(day + "-" + "Mar" + "-" + year);

                                } else if (month == 3) {
                                    edt_pdate.setText(day + "-" + "Apr" + "-" + year);

                                } else if (month == 4) {
                                    edt_pdate.setText(day + "-" + "May" + "-" + year);

                                } else if (month == 5) {
                                    edt_pdate.setText(day + "-" + "Jun" + "-" + year);

                                } else if (month == 6) {
                                    edt_pdate.setText(day + "-" + "Jul" + "-" + year);

                                } else if (month == 7) {
                                    edt_pdate.setText(day + "-" + "Aug" + "-" + year);

                                } else if (month == 8) {
                                    edt_pdate.setText(day + "-" + "Sep" + "-" + year);

                                } else if (month == 9) {
                                    edt_pdate.setText(day + "-" + "Oct" + "-" + year);

                                } else if (month == 10) {
                                    edt_pdate.setText(day + "-" + "Nov" + "-" + year);

                                } else if (month == 11) {
                                    edt_pdate.setText(day + "-" + "Dec" + "-" + year);

                                }
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        pbtn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                projectModel.setProjectname(edt_pname.getText().toString());
                projectModel.setProjectDesc(edt_pdesc.getText().toString());
                projectModel.setDuration(Integer.parseInt(edt_pduration.getText().toString()));
                projectModel.setStartDate(edt_pdate.getText().toString());
                projectModel.setPanchayatName(edt_panchaytname.getText().toString());
                eGramDatabase.eGramDao().insertProject(projectModel);
                showList();
                Toast.makeText(AdminHome.this, "Successfully added the project", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void loadImagesFromGallery() {

        if (ActivityCompat.checkSelfPermission(AdminHome.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) !=PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AdminHome.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    100);
            return;
        }

       /* Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setType("image/*");
        startActivityForResult(intent, 1);*/

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1);
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK)
        {

             /*   Uri imageUri = data.getData();
                try {
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    bitmap = BitmapFactory.decodeStream(is);
                    profImage.setImageBitmap(bitmap);
                    imageSource =  ImageBitmapString.BitMapToString(bitmap);
                }
                catch (FileNotFoundException e){e.printStackTrace();}*/

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            profImage.setImageBitmap(imageBitmap);
            imageSource =  ImageBitmapString.BitMapToString(imageBitmap);
          }

    }
}