package com.softedge.safedoktor.interfaces;

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
import retrofit2.http.Query;
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
    @GET("api/patients/{patientid}/appointments?size=1000") //in progress = 7, completed = 3 , booked = 8
    Call<rSwaggerAPI<List<rAppt_Content>>> getAllAppointments(@Header("Authorization") String authHeader, @Path("patientid") int patientid);
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-APPOINTMENT=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    //-------------------------------------------fetch list of services-----------------------------
    @GET("api/patients/{patientid}/services")
    Call<rSwaggerAPI<List<rServiceContent>>> getServiceResponse(@Header("Authorization") String authHeader, @Path("patientid") int patientid);
    //-------------------------------------------fetch list of services-----------------------------

//    doctor user list -> specialties list -> time slots

    //Get user profiles but save only doctors
    @GET("api/users")
    Call<rSwaggerAPI<List<UserAccount>>> getUsersInfo(@Header("Authorization") String authHeader);

    //=========================================TO BOOK APPOINTMENT==================================

    //------------------------------------fetch list of specialties---------------------------------
    @GET("api/setup/clinicalspecialties")
    Call<rSwaggerAPI<List<Specialties>>> getClinicalSpecialties(@Header("Authorization") String authHeader);
    //------------------------------------fetch list of specialties---------------------------------

    //---------------------------------------fetch profile photo------------------------------------
    @GET("api/users/{userId}/photo")
    Call<SwaggerAPI_ResponseArr<List<Userphoto>>> getUserPhoto(@Header("Authorization") String authHeader,@Path("userId") String userId);
    //---------------------------------------fetch profile photo------------------------------------

    //list available appointment slots
    @GET("api/appointments/serviceavailability/{serviceid}")
    Call<rSwaggerAPI<List<rServiceSlots>>> getAvailableServices(@Header("Authorization") String authHeader, @Path("serviceid") int serviceid, @QueryMap Map<String, String> query);

    //list available appointment slots based on speciality (Time slots)
    //To avoid overuse of user data bundle, limit slots fetched from today's date to 1 week after
    @GET("api/appointments/specialtyavailability/{specialtyId}")
    Call<SwaggerAPI_ResponseArr<List<TimeSlot>>> getSpecialtyAvailableServices(
            @Header("Authorization") String authHeader,
            @Path("specialtyId") int specialtyId,
            @Query("from") String startDate,
            @Query("to") String endDate);

    //Doctors belonging to a specific clinical specialty
    @GET("api/setup/clinicalspecialties/{id}/doctors")
    Call<SwaggerAPI_ResponseArr<List<DoctorOutObj>>> getClinicalSpecialtyDoctors(@Header("Authorization") String authHeader, @Path("id") int id);

    //show servicefee
    @GET("api/appointments/servicefee/{serviceid}")
    Call<SwaggerAPI_ResponseArr<List<rServiceFee>>> getServiceFee(
            @Header("Authorization") String authHeader,
            @Path("serviceid") int serviceId,
            @Query("consultationchattypeid") int chatTypeId,
            @Query("patientid") int patientId);

    //Booking object sent to finalise appointment booking on server
    @POST("api/appointments/payandbook")
    Call<SwaggerAPI_ResponseArr<List<rAppt_Content>>> bookAndPay(@Header("Authorization") String authHeader, @Body ApptBooking bookNPay);

    //Make Payments
    @GET("api/appointments/{bookingid}/paymentstatus")
    Call<SwaggerAPI_ResponseArr> getBookingStatus(@Header("Authorization") String authHeader, @Path("bookingid") int bookingid);
    //=========================================TO BOOK APPOINTMENT==================================

    //====================================ONLINE VISITS=============================================
    //http://infotechhims.com:1350/api/consultations/patients/1/consultationhistroy?from=1-01-2018&to=1-02-2018
    @GET("api/consultations/patients/{patientid}/consultationhistroy?")
    Call<SwaggerAPI_ResponseArr<List<rOnlineVisits>>> getOnlineVisits(
            @Header("Authorization") String authHeader,
            @Path("patientid") int patientid,
            @Query("from") String startDate,
            @Query("to") String endDate);

    //====================================ONLINE VISITS=============================================


}
