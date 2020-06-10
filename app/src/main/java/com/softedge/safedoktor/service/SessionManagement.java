package com.softedge.safedoktor.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.softedge.safedoktor.models.swaggerModels.response.rLogin;
import com.softedge.safedoktor.utilities.common_code;

import static com.softedge.safedoktor.utilities.AppConstants.*;

public class SessionManagement {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SessionManagement(Context context) {
        this.sharedPreferences = common_code.appPref(context);
        this.editor = sharedPreferences.edit();
        editor.apply();
    }

    public void setNotificationStatus(boolean status){
        editor.putBoolean(NOTI_STATUS,status);
        editor.commit();
    }

    public boolean getNotificationStatus(){
        return sharedPreferences.getBoolean(NOTI_STATUS,true);
    }

    public void createLoginSession(rLogin patient_login){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        //token model
        String fulltoken = patient_login.getTokenModel().getTokenType() + " " + patient_login.getTokenModel().getToken();

        editor.putString(KEY_FULL_TOKEN,fulltoken);
        editor.putInt(KEY_PATIENT_ID,patient_login.getPatient().getPatientid());
        editor.putString(KEY_TOKEN_TYPE, patient_login.getTokenModel().getTokenType());
        editor.putString(KEY_TOKEN, patient_login.getTokenModel().getToken());
        editor.putString(KEY_CREATED_DATE, patient_login.getTokenModel().getCreatedDate());
        editor.putString(KEY_EXPIRES_IN, patient_login.getTokenModel().getExpiresOn());

        // apply changes
        editor.apply();
        Log.e("SharedPreferences","shared preferences saved");
    }

    public  void saveCredentials(String username, String password){
        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_PASSWORD,password);

        editor.apply();
    }

    public void LogoutWipe(){
        editor.clear();
        editor.apply();
    }
//
//    public void setProfileImage(String image){
//        editor.putString(KEY_PATIENT_IMAGE,image);
//        editor.commit();
//    }
//
//    public String getProfileImage(){
//        return sharedPreferences.getString(KEY_PATIENT_IMAGE,"");
//    }
//
//    public boolean isLoggedIn(){
//        return sharedPreferences.getBoolean(IS_LOGIN, false);
//    }

//    public void TokenExpired(){ //Formerly Session expired
//        Toast.makeText(context, "Your session has expired please login again", Toast.LENGTH_SHORT).show();
//
//        Intent login_intent = new Intent(context, LoginActivity.class);
//        login_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        context.startActivity(login_intent);
//
//    }


}
