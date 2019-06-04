package com.softedge.care_assist.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.softedge.care_assist.activities.Appointments;
import com.softedge.care_assist.activities.OpdCardActivity;
import com.softedge.care_assist.activities.SettingsActivity;
import com.softedge.care_assist.activities.TOS_Activity;
import com.softedge.care_assist.activities.VideoCallingActivity;
import com.softedge.care_assist.activities.WelcomeActvity;
import com.softedge.care_assist.models.fireModels.PatientPackage.Biography;
import com.softedge.care_assist.R;
import com.softedge.care_assist.databases.SafeDB;

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
                patbundle.getString(Biography.DATE_OF_BIRTH),
                patbundle.getInt(Biography.MARITAL_STATUS)
        );

        return new_biography;
    }

    public static SharedPreferences appPref(Context context){
        return context.getSharedPreferences("care_assist",Context.MODE_PRIVATE);
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
        return FirebaseAuth.getInstance().getCurrentUser() == null;
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

    public static void toTOS(Context context){
        Intent tos_Intent = new Intent(context, TOS_Activity.class);
        context.startActivity(tos_Intent);
    }

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=INTENTS-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    public static void toIntro(Activity context){
        Intent intro_intent = new Intent(context, WelcomeActvity.class);
        intro_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intro_intent);
        context.finish();
    }

    public static void toSettings(Context context){
        Intent settings_intent = new Intent(context, SettingsActivity.class);
        context.startActivity(settings_intent);
    }

    public static void toAppointment(Context context){
        Intent appt_intent = new Intent(context, Appointments.class);
        context.startActivity(appt_intent);
    }

    public static void toVidecall(Context context) {
        Intent videoIntent = new Intent(context, VideoCallingActivity.class);
        context.startActivity(videoIntent);
    }

    public static void toOpdCard(Context context){
        Intent opdcared_intent = new Intent(context, OpdCardActivity.class);
        context.startActivity(opdcared_intent);
    }
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=INTENTS-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-





























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


