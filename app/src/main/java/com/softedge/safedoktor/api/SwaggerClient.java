package com.softedge.safedoktor.api;

import com.softedge.safedoktor.models.swaggerModels.PatientModel;
import com.softedge.safedoktor.models.swaggerModels.body.Login;
import com.softedge.safedoktor.models.swaggerModels.body.UserReg;
import com.softedge.safedoktor.models.swaggerModels.response.rIsValid;
import com.softedge.safedoktor.models.swaggerModels.response.rLogin;
import com.softedge.safedoktor.models.swaggerModels.body.PhoneNumber;
import com.softedge.safedoktor.models.swaggerModels.response.rValidation;
import com.softedge.safedoktor.models.swaggerModels.SwaggerAPI_ResponseModel;
import com.softedge.safedoktor.models.swaggerModels.response.rValidateCode;
import com.softedge.safedoktor.models.swaggerModels.body.ValidateCode;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SwaggerClient {

    //login
    @POST("api/patients/login")
    Call<SwaggerAPI_ResponseModel<List<rLogin>>> patientLogin(@Body Login patient);

    //logout
    @GET("api//patients/{patientid}/logout")
    Call<SwaggerAPI_ResponseModel> patientLogout(@Header("Authorization") String authHeader, @Path("patientid") int patientid);

    //===============================================Registration processes======================================================
    @POST("api/patients/startregistration")
    Call<SwaggerAPI_ResponseModel<List<rValidation>>> startRegistration(@Body PhoneNumber phonenumber);

    @POST("api/patients/validatephonecode")
    Call<SwaggerAPI_ResponseModel<List<rIsValid>>> validatePhoneCode(@Body ValidateCode phonenumber);

    @GET("api/patients/{phoneNumber}/getvalidation")
    Call<SwaggerAPI_ResponseModel<List<rValidation>>> checkValidatePhoneCode(@Path("phoneNumber") String phoneNumber);

    @POST("api/patients/resendphonecode")
    Call<SwaggerAPI_ResponseModel<List<rValidation>>>resendCode(@Body PhoneNumber phonenumber);

    @POST("api/patients")
    Call<SwaggerAPI_ResponseModel<List<PatientModel>>> patientRegister(@Body UserReg patient);

    @PUT("api/patients")
    Call<SwaggerAPI_ResponseModel<List<PatientModel>>> updatePatient(@Header("Authorization") String authHeader,@Body PatientModel patient);

    //===============================================Registration processes======================================================
}
