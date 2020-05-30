package com.softedge.safedoktor.api;

import com.softedge.safedoktor.models.swaggerModels.body.*;
import com.softedge.safedoktor.models.swaggerModels.*;
import com.softedge.safedoktor.models.swaggerModels.response.*;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface SwaggerClient {

    //login
    @POST("api/patients/login")
    Call<SwaggerAPI_ResponseArr<List<rLogin>>> patientLogin(@Body Login patient);

    //logout
    @GET("api//patients/{patientid}/logout")
    Call<SwaggerAPI_ResponseArr> patientLogout(@Header("Authorization") String authHeader, @Path("patientid") int patientid);

    //===============================================Registration processes======================================================
    @POST("api/patients/startregistration")
    Call<SwaggerAPI_ResponseArr<List<rValidation>>> startRegistration(@Body PhoneNumber phonenumber);

    @POST("api/patients/validatephonecode")
    Call<SwaggerAPI_ResponseArr<List<rIsValid>>> validatePhoneCode(@Body ValidateCode phonenumber);

    @GET("api/patients/{phoneNumber}/getvalidation")
    Call<SwaggerAPI_ResponseArr<List<rValidation>>> checkValidatePhoneCode(@Path("phoneNumber") String phoneNumber);

    @POST("api/patients/resendphonecode")
    Call<SwaggerAPI_ResponseArr<List<rValidation>>>resendCode(@Body PhoneNumber phonenumber);

    @POST("api/patients")
    Call<SwaggerAPI_ResponseArr<List<PatientModel>>> patientRegister(@Body UserReg patient);

    @PUT("api/patients")
    Call<SwaggerAPI_ResponseArr<List<PatientModel>>> updatePatient(@Header("Authorization") String authHeader, @Body PatientModel patient);

    //===============================================Registration processes======================================================

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-APPOINTMENT=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    //to fetch list of confirmed appointments
    @GET("api/patients/{patientid}/appointments") //in progress = 7, completed = 3 , booked = 8
    Call<rSwaggerAPI<List<rAppt_Content>>> getAllAppointments(@Header("Authorization") String authHeader, @Path("patientid") int patientid);
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-APPOINTMENT=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    //-------------------------------------------fetch list of services-----------------------------
    @GET("api/patients/{patientid}/services")
    Call<rSwaggerAPI<List<rServiceContent>>> getServiceResponse(@Header("Authorization") String authHeader, @Path("patientid") int patientid);
    //-------------------------------------------fetch list of services-----------------------------


    //=======================================================TO BOOK APPOINTMENT====================================================================================================

    //------------------------------------fetch list of specialties---------------------------------
    @GET("api/setup/clinicalspecialties")
    Call<rSwaggerAPI<List<BasicObject>>> getClinicalSpecialties(@Header("Authorization") String authHeader);
    //------------------------------------fetch list of specialties---------------------------------

    //list available appointment slots
    @GET("api/appointments/serviceavailability/{serviceid}")
    Call<rSwaggerAPI<List<rServiceSlots>>> getAvailableServices(@Header("Authorization") String authHeader, @Path("serviceid") int serviceid, @QueryMap Map<String, String> query);

    //list available appointment slots based on speciality
    //To avoid overuse of user data bundle, limit slots fetched from today's date to 1 week after
    @GET("api/appointments/specialtyavailability/{specialtyid}?from={startDate}&to={endDate}")
    Call<SwaggerAPI_ResponseArr<List<TimeSlot>>> getSpecialtyAvailableServices(@Header("Authorization") String authHeader, @Path("specialtyid") int specialtyid, String startDate, String endDate);

    //=======================================================TO BOOK APPOINTMENT====================================================================================================


}
