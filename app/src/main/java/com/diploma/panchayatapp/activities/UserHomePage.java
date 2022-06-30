package com.diploma.panchayatapp.activities;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.diploma.panchayatapp.R;

import com.diploma.panchayatapp.adapter.ProjectAdapter;
import com.diploma.panchayatapp.databinding.ActivityUserHomePageBinding;
import com.diploma.panchayatapp.localdb.EGramDatabase;
import com.diploma.panchayatapp.model.ApplicationModel;
import com.diploma.panchayatapp.model.PanchayatModel;
import com.diploma.panchayatapp.utils.constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserHomePage extends AppCompatActivity {

    ActivityUserHomePageBinding binding;
    EGramDatabase database;
    Intent i;
    // constant code for runtime permissions
    private static final int PERMISSION_REQUEST_CODE = 200;
    // declaring width and height
    // for our PDF file.
    int pageHeight = 1120;
    int pagewidth = 792;
    // creating a bitmap variable
    // for storing our images
    Bitmap scaledbmp;
    String imgDB;
    List<ApplicationModel> applicationModelList;
    List<PanchayatModel> panchayatModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserHomePageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        database = EGramDatabase.getInstance(UserHomePage.this);
        applicationModelList = new ArrayList<>();
        panchayatModelList = new ArrayList<>();
        applicationModelList = database.eGramDao().getUserDetails(constants.getPhn(UserHomePage.this));

        imgDB = database.eGramDao().getImg(constants.getPhn(UserHomePage.this));
        if (imgDB != null) {
            scaledbmp = Bitmap.createScaledBitmap(constants.convertStringToBitmap(imgDB),
                    200, 200, false);
        }


        showList();


        binding.textView.setText("Hi " + constants.getName(UserHomePage.this));

        binding.imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                panchayatModelList = database.eGramDao().getPanchayatDetails(applicationModelList.get(0).getPanchayatName());
                showpanchayatinfo();

            }
        });

        binding.imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constants.logout(UserHomePage.this);
            }
        });

        binding.imgDwnload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    generatePDF();
                } else {
                    requestPermission();
                }
            }
        });
    }

    private void showpanchayatinfo() {
        LayoutInflater inflater = LayoutInflater.from(UserHomePage.this);
        final View view1 = inflater.inflate(R.layout.panchayatinfo, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(UserHomePage.this);
        builder.setView(view1);
        TextView panchayatname = view1.findViewById(R.id.panchayatname);
        TextView txt_area = view1.findViewById(R.id.txt_area);
        TextView txt_pop = view1.findViewById(R.id.txt_pop);
        TextView txt_dpop = view1.findViewById(R.id.txt_dpop);
        TextView txt_wards = view1.findViewById(R.id.txt_wards);
        TextView txt_no = view1.findViewById(R.id.txt_no);
        TextView txt_blk = view1.findViewById(R.id.txt_blk);
        TextView txt_par = view1.findViewById(R.id.txt_par);
        TextView txt_assembly = view1.findViewById(R.id.txt_assembly);
        TextView txt_nov = view1.findViewById(R.id.txt_nov);
        TextView txt_villagess = view1.findViewById(R.id.txt_villagess);
        TextView txt_url = view1.findViewById(R.id.txt_url);
        builder.setCancelable(true);
        final AlertDialog alert = builder.create();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.show();
        panchayatname.setText(panchayatModelList.get(0).getPanchayaName());
        txt_area.setText("Area in Sq.Km: " + panchayatModelList.get(0).getPanchayaArea());
        txt_pop.setText("Population: " + panchayatModelList.get(0).getPanchayaPop());
        txt_dpop.setText("Density of Population: " + panchayatModelList.get(0).getPanchayaDensityOPop());
        txt_wards.setText("Wards: " + panchayatModelList.get(0).getPanchayaWards());
        txt_no.setText("No. of HouseHolds: " + panchayatModelList.get(0).getPanchayaHouseHolds());
        txt_blk.setText("Block Panchayat: " + panchayatModelList.get(0).getPanchayaBlk());
        txt_par.setText("Parliament Panchayat: " + panchayatModelList.get(0).getPanchayaParliament());
        txt_assembly.setText("Assembly Constituency: " + panchayatModelList.get(0).getPanchayaAssembly());
        txt_nov.setText("No. of Villages: " + panchayatModelList.get(0).getVillages());
        txt_villagess.setText("Villages: " + panchayatModelList.get(0).getVillageNames());
        if (!panchayatModelList.get(0).getUrl().equals("")) {
            SpannableString content = new SpannableString("Link: " + panchayatModelList.get(0).getUrl());
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            txt_url.setText(content);
            txt_url.setTextColor(getColor(R.color.link));
        } else {
            txt_url.setText("Link: " + "N/A");
            txt_url.setTextColor(getColor(R.color.textColor));
        }

        txt_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!panchayatModelList.get(0).getUrl().equals("")) {
                    if (constants.isNetworkAvilable(UserHomePage.this)) {
                        alert.dismiss();
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(panchayatModelList.get(0).getUrl()));
                        startActivity(browserIntent);
                    } else {
                        Toast.makeText(UserHomePage.this, "No network available", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void generatePDF() {
        // creating an object variable
        // for our PDF document.
        PdfDocument pdfDocument = new PdfDocument();

        // two variables for paint "paint" is used
        // for drawing shapes and we will use "title"
        // for adding text in our PDF file.
        Paint paint = new Paint();
        Paint title = new Paint();

        // we are adding page info to our PDF file
        // in which we will be passing our pageWidth,
        // pageHeight and number of pages and after that
        // we are calling it to create our PDF.
        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 1).create();

        // below line is used for setting
        // start page for our PDF file.
        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);

        // creating a variable for canvas
        // from our page of PDF.
        Canvas canvas = myPage.getCanvas();

        // below line is used to draw our image on our PDF file.
        // the first parameter of our drawbitmap method is
        // our bitmap
        // second parameter is position from left
        // third parameter is position from top and last
        // one is our variable for paint.
        canvas.drawBitmap(scaledbmp, 50, 50, paint);

        // below line is used for adding typeface for
        // our text which we will be adding in our PDF file.
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        // below line is used for setting text size
        // which we will be displaying in our PDF file.
        title.setTextSize(35);

        // below line is sued for setting color
        // of our text inside our PDF file.
        title.setColor(ContextCompat.getColor(this, R.color.black));

        // below line is used to draw text in our PDF file.
        // the first parameter is our text, second parameter
        // is position from start, third parameter is position from top
        // and then we are passing our variable of paint which is title.
        canvas.drawText(applicationModelList.get(0).getPanchayatName(), 300, 150, title);
        canvas.drawText("Application Form", 300, 100, title);

        // similarly we are creating another text and in this
        // we are aligning this text to center of our PDF file.
        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        title.setColor(ContextCompat.getColor(this, R.color.black));
        title.setTextSize(25);

        // below line is used for setting
        // our text to center of PDF.
        title.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Applicant Name: " + applicationModelList.get(0).getFullname(), 50, 400, title);
        canvas.drawText("Date of Birth: " + applicationModelList.get(0).getDob(), 50, 450, title);
        canvas.drawText("Contact Number: " + applicationModelList.get(0).getMobNum(), 50, 500, title);


        // after adding all attributes to our
        // PDF file we will be finishing our page.
        pdfDocument.finishPage(myPage);

        // below line is used to set the name of
        // our PDF file and its path.
        File file = new File(Environment.getExternalStorageDirectory(), constants.getName(UserHomePage.this) + ".pdf");

        try {
            // after creating a file name we will
            // write our PDF file to that location.
            pdfDocument.writeTo(new FileOutputStream(file));

            // below line is to print toast message
            // on completion of PDF generation.
            Toast.makeText(UserHomePage.this, "Application form downloaded successfully.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            // below line is used
            // to handle error
            e.printStackTrace();
        }
        // after storing our pdf to that
        // location we are closing our PDF file.
        pdfDocument.close();
    }

    private boolean checkPermission() {
        // checking of permissions.
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        // requesting permissions if not provided.
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                // after requesting permissions we are showing
                // users a toast message of permission granted.
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (writeStorage && readStorage) {
                    Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denined.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    private void showList() {
        ProjectAdapter projectAdapter = new ProjectAdapter(UserHomePage.this, database.eGramDao()
                .getAllProjects());
        //constants.getName(AdminHome.this))
        binding.projectList.setHasFixedSize(true);
        binding.projectList.setLayoutManager(new LinearLayoutManager(UserHomePage.this));
        binding.projectList.setAdapter(projectAdapter);

    }
}