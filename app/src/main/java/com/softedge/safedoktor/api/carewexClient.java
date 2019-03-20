package com.softedge.safedoktor.api;

import com.softedge.safedoktor.models.retrofitModels.retroDoctor;
import com.softedge.safedoktor.models.retrofitModels.retroPatient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface carewexClient {

    @GET("/patient/{patientId}")
    Call<retroPatient> patient(@Path("patientId") String patientId);

    @GET("/patient/{doctorID}")
    Call<List<retroDoctor>> doctors(@Path("doctorID") String doctorID);




}
