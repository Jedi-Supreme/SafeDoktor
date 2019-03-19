package com.softedge.safedoktor.api;

import com.softedge.safedoktor.models.retrofitModels.retroLogin;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface tokenUtils {

    @POST("")
    Call<ResponseBody> safeLogin(@Body retroLogin login);

    @GET
    Call<ResponseBody> token(@Header("Authorization") String token);

}
