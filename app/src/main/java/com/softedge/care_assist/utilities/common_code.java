package com.softedge.care_assist.utilities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.softedge.care_assist.activities.Appointments;
import com.softedge.care_assist.activities.OpdCardActivity;
import com.softedge.care_assist.activities.ProfileActivity;
import com.softedge.care_assist.activities.SettingsActivity;
import com.softedge.care_assist.activities.SignupActivity;
import com.softedge.care_assist.activities.TOS_Activity;
import com.softedge.care_assist.activities.VideoCallingActivity;
import com.softedge.care_assist.activities.WelcomeActvity;
import com.softedge.care_assist.models.fireModels.PatientPackage.Biography;
import com.softedge.care_assist.R;
import com.softedge.care_assist.databases.SafeDB;
import com.softedge.care_assist.models.retrofitModels.retroPatient;

public class common_code {

    public static final int CAMERA_AUDIO_REQ_CODE = 434;

    public static final int REV_LOWEST = 0;
    public static final int REV_LOW = 25;
    public static final int REV_MEDIUM = 50;
    public static final int REV_HIGH = 75;
    public static final int REV_HIGHEST = 100;

    public static final String dateTimeformat = "dd/MMM/yyyy hh:mm:ss aa";
    public static final String regDateformat = "dd-MM-yyyy";
    //public static final String daynameTimeformat = "E, dd MMM yyyy";

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

    public static retroPatient carewex_pat(Biography basicUser, Context context) {

        String[] gender_arr = context.getResources().getStringArray(R.array.gender_Arr);
        String[] marital_arr = context.getResources().getStringArray(R.array.marital_status);

        String title, email = "", nationality = "Ghanaian";

        //if user is male set title Mr
        if (basicUser.getGender() == 1) {
            title = "Mr";
        } else {
            //else if female and married set title Mrs
            if (basicUser.getMarital_state() == 2) {
                title = "Mrs";
            } else {
                //else set title Miss
                title = "Miss";
            }
        }

        if (!basicUser.getEmail().contains(context.getString(R.string.default_email_suffix))) {
            email = basicUser.getEmail();
        }

        return new retroPatient(
                "",
                title,
                basicUser.getFirstname(),
                basicUser.getLastname(),
                basicUser.getMobile_number(),
                email,
                "",
                nationality,
                basicUser.getDate_of_birth(),
                gender_arr[basicUser.getGender()],
                marital_arr[basicUser.getMarital_state()],
                "", "", "",
                basicUser.getOpd_Id()
        );

    }

    public static SharedPreferences appPref(Context context) {
        return context.getSharedPreferences("care_assist", Context.MODE_PRIVATE);
    }

    public static void getreq_Affiliate(String[] plusCode, Context context) {

        Uri gmmIntentUri = Uri.parse("http://plus.codes/" + plusCode[0] + "," + Uri.encode(plusCode[1]));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
        } else {
            Toast.makeText(context, "Map Application Not Found", Toast.LENGTH_LONG).show();
        }
    }

    //====================================LOGIN TESTS===============================================
    //test if user is logged in
    public static boolean isUserLogged_in() {
        return FirebaseAuth.getInstance().getCurrentUser() == null;
    }

    //test if user is new user
    public static boolean isFirstRun(Context activity_ctx) {
        SharedPreferences safe_pref = activity_ctx.getSharedPreferences(
                activity_ctx.getResources().getString(R.string.safe_pref_name), Context.MODE_PRIVATE);
        return safe_pref.getBoolean(activity_ctx.getResources().getString(R.string.first_run_prefkey), true);
    }
    //====================================LOGIN TESTS===============================================

    public static Biography appuser(Context activity_ctx) {

        SafeDB safe_db = new SafeDB(activity_ctx, null);
        String fireID;

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            fireID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            return safe_db.local_appUser(fireID);
        }

        return null;
    }

    public static void toTOS(Context context) {
        Intent tos_Intent = new Intent(context, TOS_Activity.class);
        context.startActivity(tos_Intent);
    }

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=INTENTS-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    public static void toIntro(Activity context) {
        Intent intro_intent = new Intent(context, WelcomeActvity.class);
        intro_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intro_intent);
        context.finish();
    }

    public static void toSettings(Context context) {
        Intent settings_intent = new Intent(context, SettingsActivity.class);
        context.startActivity(settings_intent);
    }

    public static void toSignup(Context context) {
        Intent signup_intent = new Intent(context, SignupActivity.class);
        context.startActivity(signup_intent);
    }

    public static void toAppointment(Context context) {
        Intent appt_intent = new Intent(context, Appointments.class);
        context.startActivity(appt_intent);
    }

    public static void toVidecall(Context context) {
        Intent videoIntent = new Intent(context, VideoCallingActivity.class);
        context.startActivity(videoIntent);
    }

    public static void toProfile(Context context) {
        Intent profile_intent = new Intent(context, ProfileActivity.class);
        context.startActivity(profile_intent);
    }

    public static void toOpdCard(Context context) {
        Intent opdcared_intent = new Intent(context, OpdCardActivity.class);
        context.startActivity(opdcared_intent);
    }
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=INTENTS-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-

    public static boolean checkPermissionForCameraAndMicrophone(Context context) {
        int resultCamera = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        int resultMic = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO);
        return (resultCamera == PackageManager.PERMISSION_GRANTED) && (resultMic == PackageManager.PERMISSION_GRANTED);
    }

    public static void requestPermissionForCameraAndMicrophone(Activity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)
                || ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECORD_AUDIO)) {
            Toast.makeText(activity, "Camera and Microphone permissions needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO},
                    CAMERA_AUDIO_REQ_CODE);
        }
    }

    public static boolean isInternetConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService
                (Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnectedOrConnecting();
        } else {
            return false;
        }


    }

    public static void connection_toast(Context context) {
        Toast.makeText(context, context.getResources().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
    }

    private static void accountAvailability(String email, Context context) {

        FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email)
                .addOnCompleteListener(task -> {

                    // if email is already registered (unavailable)
                    if (task.getResult() != null && task.getResult().getSignInMethods() != null) {
                        if (task.getResult().getSignInMethods().size() > 0) {
                            String err = "An account Already Exists with this number.";

                            if (context instanceof SignupActivity){
                                Toast.makeText(context,err,Toast.LENGTH_LONG).show();
                                ((SignupActivity) context).hideProbar();
                            }

                        } else {
                            // you are good to go
                            if (context instanceof SignupActivity){
                                ((SignupActivity) context).pass_dataTo_verification();
                            }//else if (context instanceof AccountActivity){
                                //Todo check in account activity
                            //}
                        }
                    }
                });


    }

    public static void emailAvailability_fetch(String mobilenumber, Context context) {

        final DatabaseReference records_ref = FirebaseDatabase.getInstance()
                .getReference(context.getResources().getString(R.string.records_ref));

        records_ref.child(mobilenumber).child("email").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String email = dataSnapshot.getValue(String.class);

                        if (email != null){
                            accountAvailability(email,context);
                        }else {

                            if (context instanceof SignupActivity){
                                ((SignupActivity) context).pass_dataTo_verification();
                            }
                        }
                        records_ref.removeEventListener(this);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}

                }
        );

    }

}



