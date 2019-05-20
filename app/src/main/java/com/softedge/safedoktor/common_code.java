package com.softedge.safedoktor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.softedge.safedoktor.api.SafeClient;
import com.softedge.safedoktor.api.ServiceGenerator;
import com.softedge.safedoktor.models.fireModels.PatientPackage.Biography;
import com.softedge.safedoktor.models.retrofitModels.retroToken;
import com.softedge.safedoktor.models.retrofitModels.token_ReqBody;

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

    public static void get_access_token(Context ctx){

            SafeClient safeClient = ServiceGenerator.createService(SafeClient.class);

            SharedPreferences token_pref = appPref(ctx);
            SharedPreferences.Editor token_editor = token_pref.edit();

            token_ReqBody body = new token_ReqBody("safedoktor","doktor@softedge_carewex.2019");

            Call<retroToken> tokencall = safeClient.getToken(
                    body.getPassword(),
                    body.getUsername(),
                    body.getGrant_type(),
                    body.getClient_secret(),
                    body.getClient_id());

            tokencall.enqueue(new Callback<retroToken>() {
                @Override
                public void onResponse(@NonNull Call<retroToken> call, @NonNull Response<retroToken> response) {
                    retroToken token = response.body();

                    if (token != null) {
                        token_editor.putString("access_token",token.getAccessToken()).apply();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<retroToken> call, @NonNull Throwable t) {
                    Toast.makeText(ctx,"Fetch Access Token Failed with error: " + t.getMessage(),Toast.LENGTH_LONG).show();
                }
            });

    }

    public static SharedPreferences appPref(Context context){
        return context.getSharedPreferences("safedoktor",Context.MODE_PRIVATE);
    }

    /*private boolean checkPermissionForCameraAndMicrophone(Context context) {
        int resultCamera = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        int resultMic = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO);
        return (resultCamera == PackageManager.PERMISSION_GRANTED) && (resultMic == PackageManager.PERMISSION_GRANTED);
    }

    try{
                    SafeClient safeClient = ServiceGenerator.createService(SafeClient.class);

                    token_ReqBody body = new token_ReqBody("safedoktor","doktor@softedge_carewex.2019");

                    Call<retroToken> tokencall = safeClient.getToken(
                            body.getPassword(),
                            body.getUsername(),
                            body.getGrant_type(),
                            body.getClient_secret(),
                            body.getClient_id());

                    tokencall.enqueue(new Callback<retroToken>() {
                        @Override
                        public void onResponse(@NonNull Call<retroToken> call, @NonNull Response<retroToken> response) {
                            retroToken token = response.body();

                            if (token != null) {
                                Toast.makeText(getApplicationContext(),"Access Token = " + token.getAccessToken(),Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(getApplicationContext(),"Access Token = empty " + response.raw().toString(),Toast.LENGTH_LONG).show();
                                //Toast.makeText(getApplicationContext(),"body " + body.getGrant_type(),Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<retroToken> call, @NonNull Throwable t) {
                            Toast.makeText(getApplicationContext(),"Access Token = failed "  + t.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_LONG).show();
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


