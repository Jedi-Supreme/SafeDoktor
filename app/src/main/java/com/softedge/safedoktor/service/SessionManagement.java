package com.softedge.safedoktor.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.softedge.safedoktor.activities.LoginActivity;
import com.softedge.safedoktor.models.swaggerModels.response.rToken;

public class SessionManagement {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String PREF_NAME ="Safedoktor";
    private static final String IS_LOGIN="isloggedin";
    private static final String NOTI_STATUS="notistatus";

    public static final String KEY_TOKEN_TYPE = "tokentype";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_CREATED_DATE = "datecreated";
    public static final String KEY_EXPIRES_IN = "expires";
    public static final String KEY_PATIENT_IMAGE = "patientimage";

    public SessionManagement(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
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

    public void createLoginSession(rToken tokenModel){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        //token model
        editor.putString(KEY_TOKEN_TYPE, tokenModel.getTokenType());
        editor.putString(KEY_TOKEN, tokenModel.getToken());
        editor.putString(KEY_CREATED_DATE, tokenModel.getCreatedDate());
        editor.putString(KEY_EXPIRES_IN, tokenModel.getExpiresOn());

        // commit changes
        editor.commit();
        Log.e("SharedPreferences","shared preferences saved");
    }

    public void setProfileImage(String image){
        editor.putString(KEY_PATIENT_IMAGE,image);
        editor.commit();
    }

    public String getProfileImage(){
        return sharedPreferences.getString(KEY_PATIENT_IMAGE,"");
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

//    public void TokenExpired(){ //Formerly Session expired
//        Toast.makeText(context, "Your session has expired please login again", Toast.LENGTH_SHORT).show();
//
//        Intent login_intent = new Intent(context, LoginActivity.class);
//        login_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        context.startActivity(login_intent);
//
//    }


}
