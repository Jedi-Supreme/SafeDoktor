package com.softedge.safedoktor.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.softedge.safedoktor.activities.BiographyActivity;
import com.softedge.safedoktor.activities.Contacts_dependantsActivity;
import com.softedge.safedoktor.activities.RegSearch;
import com.softedge.safedoktor.activities.VerificationActivity;
import com.softedge.safedoktor.models.retrofitModels.employee_login;
import com.softedge.safedoktor.models.retrofitModels.regResult;
import com.softedge.safedoktor.models.retrofitModels.retroEmployee;
import com.softedge.safedoktor.models.retrofitModels.retroPatient;
import com.softedge.safedoktor.models.retrofitModels.retroToken;
import com.softedge.safedoktor.models.retrofitModels.retro_patSearch;
import com.softedge.safedoktor.models.retrofitModels.pat_List;
import com.softedge.safedoktor.models.retrofitModels.token_ReqBody;
import com.softedge.safedoktor.utilities.common_code;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarewexCalls {

    //private static final String EmployeeUID = "safe.doktor";
    //private static final String EmployeePass = "safe.doktor@nchs_SE2019";

    public static void get_access_token(Context ctx, employee_login employeeLogin){

        CarewexClient carewexClient = ServiceGenerator.createService(CarewexClient.class);

        SharedPreferences token_pref = common_code.appPref(ctx);
        SharedPreferences.Editor token_editor = token_pref.edit();

        token_ReqBody body = new token_ReqBody(employeeLogin.getUsername(),employeeLogin.getPassword());

        Call<retroToken> tokencall = carewexClient.getToken(
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
                    getEmployeeId(ctx,employeeLogin);
                }
            }

            @Override
            public void onFailure(@NonNull Call<retroToken> call, @NonNull Throwable t) {
                Toast.makeText(ctx,"Fetch Access Token Failed with error: " + t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }

    public static void getPatientsResult(retro_patSearch search, Context context,employee_login employeeLogin){

        SharedPreferences tokenpref = common_code.appPref(context);
        String token = tokenpref.getString("access_token",null);

        CarewexClient carewexClient = ServiceGenerator.createService(CarewexClient.class);

        Call<pat_List> callPatients = carewexClient.patResults_list("Bearer " + token,search);

        callPatients.enqueue(new Callback<pat_List>() {
            @Override
            public void onResponse(@NonNull Call<pat_List> call, @NonNull Response<pat_List> response) {

                if (response.body() != null) {

                    List<retroPatient> patientsList = response.body().getPatientslist();

                    if (context instanceof RegSearch){
                        ((RegSearch)context).populate_result(patientsList);
                    }else if (context instanceof BiographyActivity){
                        ((BiographyActivity) context).carewex_patID(patientsList);
                        //TODO update user details on carewex
                        //TODO return retro patient for update operation
                    }

                }else if (response.raw().message().equals("Unauthorized")){
                    CarewexCalls.get_access_token(context,employeeLogin);
                    getPatientsResult(search,context,employeeLogin);
                }

            }

            @Override
            public void onFailure(@NonNull Call<pat_List> call, @NonNull Throwable t) {
                Toast.makeText(context,"Search failed with error: " + t.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }

    public static void register_patient(retroPatient pat, Context context){
        
        SharedPreferences app_pref = common_code.appPref(context);
        String token = app_pref.getString("access_token",null);
        String employeeId = app_pref.getString("employeeID",null);

        CarewexClient carewexClient = ServiceGenerator.createService(CarewexClient.class);

        Call<regResult> callPatients = carewexClient.patReg("Bearer " + token,pat, employeeId);

        callPatients.enqueue(new Callback<regResult>() {
            @Override
            public void onResponse(@NonNull Call<regResult> call, @NonNull Response<regResult> response) {

                if (response.body() != null) {

                    regResult resp = response.body();

                    try {
                        //create user account with email
                        if (resp.getStatus().equals("Success")){

                            if (context instanceof  VerificationActivity) {
                                ((VerificationActivity) context).login_with_email(resp.getPatientId());
                            }else if (context instanceof Contacts_dependantsActivity){

                                pat.setPatientId(resp.getPatientId());
                                //Toast.makeText(context,resp.getPatientId(),Toast.LENGTH_LONG).show();
                                ((Contacts_dependantsActivity) context).save_to_local(pat);
                            }

                        }
                    }catch (Exception ignored){}

                }else {
                    Toast.makeText(context,"Registration response: " + response.raw().message(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<regResult> call,@NonNull Throwable t) {
                Toast.makeText(context,"Registration failed with error: " + t.toString(),Toast.LENGTH_LONG).show();
            }
        });


    }

    private static void getEmployeeId(Context ctx,employee_login employeeLogin){

        SharedPreferences tokenpref = common_code.appPref(ctx);
        String token = tokenpref.getString("access_token",null);

        SharedPreferences empID_pref = common_code.appPref(ctx);
        SharedPreferences.Editor empID_editor = empID_pref.edit();

        CarewexClient carewexClient = ServiceGenerator.createService(CarewexClient.class);

        Call<retroEmployee> callPatients = carewexClient.employee("Bearer " + token,employeeLogin.getUsername());

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

    //verifiy code -> register patient on carewex -> link email credentials -> login user

}
