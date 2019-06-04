package com.softedge.care_assist.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.softedge.care_assist.models.fireModels.PatientPackage.Biography;
import com.softedge.care_assist.models.retrofitModels.regResult;
import com.softedge.care_assist.models.retrofitModels.retroEmployee;
import com.softedge.care_assist.models.retrofitModels.retroPatient;
import com.softedge.care_assist.models.retrofitModels.retroToken;
import com.softedge.care_assist.models.retrofitModels.retro_patSearch;
import com.softedge.care_assist.models.retrofitModels.pat_List;
import com.softedge.care_assist.models.retrofitModels.token_ReqBody;
import com.softedge.care_assist.utilities.common_code;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarewexCalls {

    private static final String EmployeeUID = "care.assist";
    private static final String EmployeePass = "care.assist@nchs_SE2019";

    public static void get_access_token(Context ctx){

        SafeClient safeClient = ServiceGenerator.createService(SafeClient.class);

        SharedPreferences token_pref = common_code.appPref(ctx);
        SharedPreferences.Editor token_editor = token_pref.edit();

        token_ReqBody body = new token_ReqBody(EmployeeUID,EmployeePass);

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

                    getEmployeeId(ctx);
                }
            }

            @Override
            public void onFailure(@NonNull Call<retroToken> call, @NonNull Throwable t) {
                Toast.makeText(ctx,"Fetch Access Token Failed with error: " + t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }

    public static void getPatientsResult(retro_patSearch search, Context context){

        SharedPreferences tokenpref = common_code.appPref(context);
        String token = tokenpref.getString("access_token",null);

        SafeClient safeClient = ServiceGenerator.createService(SafeClient.class);

        Call<pat_List> callPatients = safeClient.patResults_list("Bearer " + token,search);

        callPatients.enqueue(new Callback<pat_List>() {
            @Override
            public void onResponse(@NonNull Call<pat_List> call, @NonNull Response<pat_List> response) {

                if (response.body() != null) {
                    List<retroPatient> patientsList = response.body().getPatientslist();

                    for (retroPatient pat: patientsList){
                        Toast.makeText(context,pat.getPatientId(),Toast.LENGTH_LONG).show();

                    }
                }else {
                    Toast.makeText(context,"Search response: " + response.raw().message(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<pat_List> call, @NonNull Throwable t) {
                Toast.makeText(context,"Search failed with error: " + t.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }

    public static Biography register_patient(retroPatient pat, Context context, String employeeId){

        //Todo register patient call
        SharedPreferences tokenpref = common_code.appPref(context);
        String token = tokenpref.getString("access_token",null);

        Biography careBio = new Biography();

        SafeClient safeClient = ServiceGenerator.createService(SafeClient.class);

        Call<regResult> callPatients = safeClient.patReg("Bearer " + token,pat, employeeId);

        callPatients.enqueue(new Callback<regResult>() {
            @Override
            public void onResponse(@NonNull Call<regResult> call, @NonNull Response<regResult> response) {

                if (response.body() != null) {

                    regResult resp = response.body();
                    careBio.setOpd_Id(resp.getPatientId());

                }else {
                    Toast.makeText(context,"Registration response: " + response.raw().message(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<regResult> call,@NonNull Throwable t) {
                Toast.makeText(context,"Registration failed with error: " + t.toString(),Toast.LENGTH_LONG).show();
            }
        });

        return careBio;
    }

    private static void getEmployeeId(Context ctx){

        SharedPreferences tokenpref = common_code.appPref(ctx);
        String token = tokenpref.getString("access_token",null);

        SharedPreferences empID_pref = common_code.appPref(ctx);
        SharedPreferences.Editor empID_editor = empID_pref.edit();

        SafeClient safeClient = ServiceGenerator.createService(SafeClient.class);

        Call<retroEmployee> callPatients = safeClient.employee("Bearer " + token,EmployeeUID);

        callPatients.enqueue(new Callback<retroEmployee>() {
            @Override
            public void onResponse(@NonNull Call<retroEmployee> call, @NonNull Response<retroEmployee> response) {

                if (response.body() != null) {
                    retroEmployee employee = response.body();

                    empID_editor.putString("employeeID",employee.getId()).apply();

                }else {
                    Toast.makeText(ctx,"Employee ID response: " + response.raw().message(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<retroEmployee> call, @NonNull Throwable t) {
                Toast.makeText(ctx,"Fetch Employee ID failed with error: " + t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
