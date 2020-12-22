package com.softedge.safedoktor.interfaces;

import com.softedge.safedoktor.models.redde.*;
import com.softedge.safedoktor.utilities.AppConstants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface paymentClient {

    //send payment request
    @POST("v1/receive")
    Call<rPayment> payment(@Header("apikey") String apiKey, @Body paymentBody payment);
}
