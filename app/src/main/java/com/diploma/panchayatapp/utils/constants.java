package com.diploma.panchayatapp.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;


import com.diploma.panchayatapp.activities.DashBoard;
import com.diploma.panchayatapp.R;

import java.io.File;
import java.io.IOException;



public class constants {

    public static String PRFF = "EGram";
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    public static void init(Context context, boolean isSet) {
        sharedPreferences = context.getSharedPreferences(PRFF, Context.MODE_PRIVATE);
        if (isSet) {
            editor = sharedPreferences.edit();
        }
    }


    public static void setName(Context context, String name) {
        init(context, true);
        editor.putString("name", name);
        editor.commit();
    }

    public static String getName(Context context) {
        init(context, false);
        return sharedPreferences.getString("name", "");
    }


    public static void setPhn(Context context, Long mob) {
        init(context, true);
        editor.putLong("mob", mob);
        editor.commit();
    }

    public static Long getPhn(Context context) {
        init(context, false);
        return sharedPreferences.getLong("mob", 0);
    }

    public static void setType(Context context, String type) {
        init(context, true);
        editor.putString("type", type);
        editor.commit();
    }

    public static String getType(Context context) {
        init(context, false);
        return sharedPreferences.getString("type", "");
    }





    public static void logout(Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.logout_alert, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        RelativeLayout rl_no = view.findViewById(R.id.rl_no);
        RelativeLayout rl_yes = view.findViewById(R.id.rl_yes);
        builder.setCancelable(true);
        final AlertDialog alert = builder.create();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        alert.show();
        rl_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();

            }
        });
        rl_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constants.setType(context,"");
                constants.setName(context,"");
                alert.dismiss();
                context.startActivity(new Intent(context, DashBoard.class));
                //context.finish();
            }
        });
    }

    public static Bitmap rotateImageIfRequired(File selectedImage, Bitmap img) throws IOException {
        ExifInterface ei = new ExifInterface(selectedImage.getPath());
        String o = ei.getAttribute(ExifInterface.TAG_ORIENTATION);
        int orientation = Integer.valueOf(o);
        System.out.println("imageeeeeeeee" + o);
        Bitmap rotatedBitmap = null;
        switch (orientation) {

            case ExifInterface.ORIENTATION_ROTATE_90:

            case ExifInterface.ORIENTATION_UNDEFINED:
                rotatedBitmap = rotateImage(img, 90);
                break;

            case ExifInterface.ORIENTATION_ROTATE_180:
                rotatedBitmap = rotateImage(img, 180);
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                rotatedBitmap = rotateImage(img, 270);
                break;
            default:
                rotatedBitmap = img;
        }
        return rotatedBitmap;
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    public static Bitmap convertStringToBitmap(String string) {
        byte[] byteArray1;
        byteArray1 = Base64.decode(string, Base64.DEFAULT);
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray1, 0,
                byteArray1.length);/* w  w  w.ja va 2 s  .  c om*/
        return bmp;
    }

    public static boolean isValidPhoneNumber(String phone) {

        if (!phone.trim().equals("") && phone.length() == 10 && !phone.startsWith("0")) {
            return Patterns.PHONE.matcher(phone).matches();
        }

        return false;
    }

    public static boolean isNetworkAvilable(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
            if (activeNetwork != null) { // connected to the internet

                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi
                    // Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    // connected to the mobile provider's data plan
                    // Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                }
                return true;
            } else {
                // not connected to the internet
                return false;
            }


        } catch (Exception e) {
            System.out.println("NetworkError" + e);
            return true;
        }

    }
}



