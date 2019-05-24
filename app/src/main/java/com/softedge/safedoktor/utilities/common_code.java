package com.softedge.safedoktor.utilities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.api.SafeClient;
import com.softedge.safedoktor.api.ServiceGenerator;
import com.softedge.safedoktor.databases.SafeDB;
import com.softedge.safedoktor.models.fireModels.PatientPackage.Biography;
import com.softedge.safedoktor.models.retrofitModels.retroPatient;
import com.softedge.safedoktor.models.retrofitModels.retroToken;
import com.softedge.safedoktor.models.retrofitModels.retro_patSearch;
import com.softedge.safedoktor.models.retrofitModels.token_ReqBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class common_code {

    //private static final int CAMERA_AUDIO_REQ_CODE = 434;

    public static final int REV_LOWEST = 0;
    public static final int REV_LOW = 25;
    public static final int REV_MEDIUM = 50;
    public static final int REV_HIGH = 75;
    public static final int REV_HIGHEST = 100;

    public static final String dateTimeformat = "dd/MMM/yyyy hh:mm:ss aa";
    public static final String daynameTimeformat = "E, dd MMM yyyy";

    public static Snackbar Mysnackbar(View parent_view, String message, int lenght) {

        final Snackbar snackbar = Snackbar.make(parent_view, message, lenght);
        snackbar.setActionTextColor(parent_view.getContext().getResources().getColor(R.color.colorPrimary));
        snackbar.setAction("Close", v -> snackbar.dismiss());

        return snackbar;
    }

    public static Biography patientFromBundle(Bundle patbundle) {

        Biography new_biography;

        new_biography = new Biography(
                patbundle.getString(Biography.FIRSTNAME),
                patbundle.getString(Biography.LASTNAME),
                patbundle.getInt(Biography.GENDER),
                patbundle.getString(Biography.COUNTRY_CODE),
                patbundle.getString(Biography.MOBILE_NUMBER),
                patbundle.getString(Biography.EMAIL),
                patbundle.getString(Biography.DATE_OF_BIRTH)
        );

        return new_biography;
    }

    public static SharedPreferences appPref(Context context){
        return context.getSharedPreferences("safedoktor",Context.MODE_PRIVATE);
    }

    public static void getNearest_Affiliate(Context context){
        Uri gmmIntentUri = Uri.parse("google.navigation:q=New+Crystal");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

        //Uri gmmIntentUri = Uri.parse("http://plus.codes/MXR8+48," + Uri.encode(" Ashaiman, Tema"));
        //Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
        }else {
            Toast.makeText(context, "Map Application Not Found", Toast.LENGTH_LONG).show();
        }
    }

    public static void getreq_Affiliate(String[] plusCode,Context context){

        Uri gmmIntentUri = Uri.parse("http://plus.codes/" + plusCode[0]+ "," + Uri.encode(plusCode[1]));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
        }else {
            Toast.makeText(context, "Map Application Not Found", Toast.LENGTH_LONG).show();
        }
    }

    //====================================LOGIN TESTS===============================================
    //test if user is logged in
    public static boolean isUserLogged_in(){
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    //test if user is new user
    public static boolean isFirstRun(Context activity_ctx){
        SharedPreferences safe_pref = activity_ctx.getSharedPreferences(
                activity_ctx.getResources().getString(R.string.safe_pref_name), Context.MODE_PRIVATE);
        return safe_pref.getBoolean(activity_ctx.getResources().getString(R.string.first_run_prefkey),true);
    }
    //====================================LOGIN TESTS===============================================

    public static Biography appuser(Context activity_ctx){

        SafeDB safe_db = new SafeDB(activity_ctx,null);
        String fireID ;

        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            fireID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            return safe_db.local_appUser(fireID);
        }

        return null;
    }





























    /*private boolean checkPermissionForCameraAndMicrophone(Context context) {
        int resultCamera = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        int resultMic = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO);
        return (resultCamera == PackageManager.PERMISSION_GRANTED) && (resultMic == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermissionForCameraAndMicrophone(Activity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)
                || ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECORD_AUDIO)) {
            Toast.makeText(activity, "Camera and Microphone permissions needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO},
                    CAMERA_AUDIO_REQ_CODE);
        }
    }*/


}


