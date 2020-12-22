package com.softedge.safedoktor.utilities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.softedge.safedoktor.activities.AppointmentsActivity;
import com.softedge.safedoktor.activities.DashboardActivity;
import com.softedge.safedoktor.activities.DoctorProfileActivity;
import com.softedge.safedoktor.activities.LoginActivity;
import com.softedge.safedoktor.activities.OpdCardActivity;
import com.softedge.safedoktor.activities.ProfileActivity;
import com.softedge.safedoktor.activities.SettingsActivity;
import com.softedge.safedoktor.activities.SignupActivity;
import com.softedge.safedoktor.activities.TOS_Activity;
import com.softedge.safedoktor.activities.VideoCallingActivity;
import com.softedge.safedoktor.activities.WelcomeActvity;
import com.softedge.safedoktor.databases.appDB;
import com.softedge.safedoktor.models.fireModels.PatientPackage.Biography;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.databases.SafeDB;
import com.softedge.safedoktor.models.retrofitModels.employee_login;
import com.softedge.safedoktor.models.retrofitModels.retroPatient;
import com.softedge.safedoktor.models.swaggerModels.PatientModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.softedge.safedoktor.utilities.AppConstants.KEY_FULL_TOKEN;

public class common_code {

    public static final int CAMERA_AUDIO_REQ_CODE = 434;
    public static final int CALL_REQ_CODE = 454;
    public static final String EMAIL_REMINDER_KEY = "email_reminder_key";

    public static final int MALE_GENDER = 1;
    public static final int FEMALE_GENDER = 2;

    public static final int MAR_SINGLE = 1;
    public static final int MAR_MARRIED = 2;
    public static final int MAR_DIVORCED = 3;
    public static final int MAR_WIDOWED = 4;

    public static final int REV_LOWEST = 0;
    public static final int REV_LOW = 25;
    public static final int REV_MEDIUM = 50;
    public static final int REV_HIGH = 75;
    public static final int REV_HIGHEST = 100;

    public static final String EMAIL_ACTION = "email_update";
    public static final String DEPENDANTS_FLAG = "Dependants";
    public static final String CONTACTS_FLAG = "Contacts";

    private static final int AK_SELECT = 1;
    private static final int AF_SELECT = 2;
    private static final int ASH_SELECT = 3;
    private static final int MC_SELECT = 4;
    private static final int TADI_SELECT = 5;
    private static final int TM_SELECT = 6;

    private static final String[] usernames_arr = new String[]{"","ak.doktor","af.doktor","safe.doktor","mc.doktor","tadi.doktor","tm.doktor"};
    private static final String[] pass_arr = new String[]{"","ak.doktor@nchs_SE2019","af.doktor@nchs_SE2019","safe.doktor@nchs_SE2019","mc.doktor@nchs_SE2019","tadi.doktor@nchs_SE2019","tm.doktor@nchs_SE2019"};

    public static employee_login Build_Employee(int position){

        if (position > 0 && position < usernames_arr.length){
            return new employee_login(usernames_arr[position],pass_arr[position]);
        }else {
            return new employee_login("safe.doktor","safe.doktor@nchs_SE2019");
        }

    }

    public static final String dateTimeformat = "dd/MMM/yyyy hh:mm:ss aa";
    public static final String regDateformat = "dd-MM-yyyy";

    public static final String humanDateformat = "dd-MMM-yyyy";

    //public static final String daynameTimeformat = "E, dd MMM yyyy";

    public static PatientModel currentUser(Context context){

        SharedPreferences appPref = common_code.appPref(context);

        int safeDoktorID = appPref.getInt(AppConstants.KEY_PATIENT_ID,0);

        appDB app_Db = appDB.getInstance(context);

        return app_Db.safeDoktorAccessObj().getpatient(safeDoktorID);
    }

    //-------------------------------------------DATE AND TIME--------------------------------------
    public static String readableDate(String rDate){

        String readableDate = "";

        rDate = rDate.replace("T"," ");
        rDate = rDate.replace("Z","");

        SimpleDateFormat rdate_format = new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.getDefault());
        SimpleDateFormat readableFormat = new SimpleDateFormat("E, d MMMM, yyyy",Locale.getDefault());

        Date date;

        try {
            date = rdate_format.parse(rDate);

            if (date != null) {
                readableDate = readableFormat.format(date);
            }
        }catch (Exception ignored){

        }

        return readableDate;
    }

    public static String reverseDate(String dateStr){

        String reverseDate = "";

        SimpleDateFormat reverseDate_format = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, d MMMM, yyyy",Locale.getDefault());

        Date date;

        try {
            date = dateFormat.parse(dateStr);

            if (date != null) {
                reverseDate = reverseDate_format.format(date);
            }
        }catch (Exception ignored){

        }

        return reverseDate + "T00:00:00Z";

    }

    public static String getReadabledate() {
        long milles = System.currentTimeMillis();
        SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm", Locale.getDefault());
        java.util.Date resultdate = new java.util.Date(milles);
        return df.format(resultdate);
    }

    public static String readableTime(String time){

        SimpleDateFormat rTimeformat = new SimpleDateFormat("HH:mm:s",Locale.getDefault());
        SimpleDateFormat readableFormat = new SimpleDateFormat("h:mm a",Locale.getDefault());

        Date datetime;

        try {
            datetime = rTimeformat.parse(time);

            if (datetime != null) {
                return readableFormat.format(datetime);
            }else {
                return null;
            }
        }catch (Exception ignore){
            return  null;
        }

    }

    public static String addDayDate(int numberOfDays){
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_MONTH,numberOfDays);
        SimpleDateFormat todayFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());

        return todayFormat.format(calendar.getTime());
    }
    //-------------------------------------------DATE AND TIME--------------------------------------

    public static Date getDateObject(String datetime){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());

        datetime = datetime.replace("T"," ");
        datetime = datetime.replace("Z","");

        try {
            return dateFormat.parse(datetime);
        }catch (Exception ignored){
            return null;
        }
    }

    public static Snackbar Mysnackbar(View parent_view, String message, int lenght) {

        final Snackbar snackbar = Snackbar.make(parent_view, message, lenght);
        snackbar.setActionTextColor(parent_view.getContext().getResources().getColor(R.color.colorPrimary));
        snackbar.setAction("Close", v -> snackbar.dismiss());

        return snackbar;
    }

    public static Biography patientFromBundle(Bundle patbundle) {

        Biography new_biography;

        new_biography = new Biography(
                "",
                patbundle.getString(Biography.OPD_ID),
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
        if (basicUser.getGender() == MALE_GENDER) {
            title = "Mr";
        } else {
            //else if female and married set title Mrs
            if (basicUser.getMarital_state() == FEMALE_GENDER) {
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
                basicUser.getId(),
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
                basicUser.getOpd_Id(),
                null
        );

    }

    public static SharedPreferences appPref(Context context) {
        return context.getSharedPreferences("safeDoktor", Context.MODE_PRIVATE);
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

    public static String rebuildDoctorId(String iDending){

        //sample = SD00000014

        switch (iDending.length()){

            case 1:
                return "SD0000000";
            case 2:
                return "SD000000";
            case 3:
                return "SD00000";
            case 4:
                return "SD0000";
            case 5:
                return "SD000";
            case 6:
                return "SD00";
            case 7:
                return "SD0";
            default:
                return "SD";
        }

    }

    public static String getLoginToken(Context context){
        SharedPreferences appPref = common_code.appPref(context);
        return appPref.getString(KEY_FULL_TOKEN,null);
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
        return safe_pref.getBoolean(AppConstants.KEY_FIRST_RUN, true);
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

    //Hide phone numbers for patient safety
    public static String hidden_number(String phonenumb){

        StringBuilder builder = new StringBuilder(phonenumb);

        if (phonenumb.length() > 0){
            return builder.replace(0,7,"*******").toString();
        }else {
            return "";
        }

    }

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=INTENTS-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    public static void toTOS(Context context) {
        Intent tos_Intent = new Intent(context, TOS_Activity.class);
        context.startActivity(tos_Intent);
    }

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
        Intent appt_intent = new Intent(context, AppointmentsActivity.class);
        context.startActivity(appt_intent);
    }

    public static void toVideoCall(Context context) {
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

    public static void toLogin(Context context) {
        Intent login_intent = new Intent(context, LoginActivity.class);
        login_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(login_intent);
        ((Activity)context).finish();
    }

    public static void toDashboard(Context context) {
        Intent dashboard_intent = new Intent(context, DashboardActivity.class);
        dashboard_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(dashboard_intent);
        ((Activity)context).finish();
    }

    public static void toDocProfile(Context context){
        Intent docProfile_intent = new Intent(context, DoctorProfileActivity.class);
        context.startActivity(docProfile_intent);
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

    public static boolean checkPermissionForPhone(Context context) {
        int resultCall = ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE);
        return (resultCall == PackageManager.PERMISSION_GRANTED);
    }

    public static void requestPermissionForPhone(Activity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CALL_PHONE)) {
            Toast.makeText(activity, "Call permissions needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE},CALL_REQ_CODE
                    );
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

    private static void accountNumberAvailability(String email, Context context) {

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

    public static void emailAvailability_viaNumber(String mobilenumber, Context context) {

        final DatabaseReference records_ref = FirebaseDatabase.getInstance()
                .getReference(context.getResources().getString(R.string.records_ref));

        records_ref.child(mobilenumber).child("email").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String email = dataSnapshot.getValue(String.class);

                        if (email != null && !email.contains(context.getResources().getString(R.string.default_email_suffix))){
                            accountNumberAvailability(email,context);
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

    public static void accountEmailAvailability(String email, AlertDialog dialog) {

        FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email)
                .addOnCompleteListener(task -> {

                    // if email is already registered (unavailable)
                    if (task.getResult() != null && task.getResult().getSignInMethods() != null) {

                        if (task.getResult().getSignInMethods().size() > 0) {
                            String err = "An account Already Exists with this Email.";
                                Toast.makeText(dialog.getContext(),err,Toast.LENGTH_LONG).show();
                        }else {
                            if (FirebaseAuth.getInstance().getCurrentUser() != null){
                                //FirebaseAuth.getInstance().getCurrentUser().updateEmail(email);

                                FirebaseAuth.getInstance().getCurrentUser().updateEmail(email)
                                        .addOnCompleteListener(task1 -> {
                                            if (task.isSuccessful()) {

                                                dialog.dismiss();
                                                Toast.makeText(dialog.getContext(),"Email updated successfully",Toast.LENGTH_LONG).show();

                                            } else {

                                                String err = "Email Update Failed, please try again later.";
                                                Toast.makeText(dialog.getContext(),err,Toast.LENGTH_LONG).show();
                                            }
                                        });


                            }

                        }
                    }
                });


    }

    public static Executor getDBExecutor(){
        return Executors.newFixedThreadPool(6);
    }

    public static byte[] displayImage(String base64string) {

        try{
            return Base64.decode(base64string, Base64.DEFAULT);
        } catch (Exception ignored) {
            return null;
        }
    }

    public static RoundedBitmapDrawable roundedImage(Context context){
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.generic_avatar);
        bitmap = Bitmap.createScaledBitmap(bitmap,200,200,false);

        return RoundedBitmapDrawableFactory.create(context.getResources(),bitmap);
    }




}



