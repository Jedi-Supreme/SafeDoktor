package com.softedge.safedoktor.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.softedge.safedoktor.R;
import com.softedge.safedoktor.common_code;
import com.softedge.safedoktor.models.retrofitModels.retroPatient;
import com.softedge.safedoktor.models.retrofitModels.retroToken;
import com.softedge.safedoktor.models.retrofitModels.retro_patSearch;
import com.softedge.safedoktor.models.retrofitModels.searchResults;
import com.softedge.safedoktor.models.retrofitModels.token_ReqBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarewexCalls {

    public static void get_access_token(Context ctx){

        SafeClient safeClient = ServiceGenerator.createService(SafeClient.class);

        SharedPreferences token_pref = common_code.appPref(ctx);
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

    public static void getPatientsResult(retro_patSearch search, Context context){

        SharedPreferences tokenpref = common_code.appPref(context);
        String token = tokenpref.getString("access_token",null);

        SafeClient safeClient = ServiceGenerator.createService(SafeClient.class);

        Call<searchResults> callPatients = safeClient.patResults_list("Bearer " + token,search);

        callPatients.enqueue(new Callback<searchResults>() {
            @Override
            public void onResponse(@NonNull Call<searchResults> call, @NonNull Response<searchResults> response) {

                if (response.body() != null) {
                    List<retroPatient> patientsList = response.body().getPatientslist();

                    for (retroPatient pat: patientsList){
                        Toast.makeText(context,pat.getPatientId(),Toast.LENGTH_LONG).show();

                    }
                }else {
                    Toast.makeText(context,"list empty" + response.raw().message(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<searchResults> call, @NonNull Throwable t) {
                Toast.makeText(context,"on failure: " + t.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }

}
