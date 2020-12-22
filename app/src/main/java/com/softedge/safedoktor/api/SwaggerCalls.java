package com.softedge.safedoktor.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.activities.LoginActivity;
import com.softedge.safedoktor.activities.SignupActivity;
import com.softedge.safedoktor.activities.VerificationActivity;
import com.softedge.safedoktor.activities.VirtualAppt_Activity;
import com.softedge.safedoktor.databases.appDB;
import com.softedge.safedoktor.interfaces.SwaggerClient;
import com.softedge.safedoktor.interfaces.paymentClient;
import com.softedge.safedoktor.models.redde.paymentBody;
import com.softedge.safedoktor.models.redde.rPayment;
import com.softedge.safedoktor.models.swaggerModels.Appointment;
import com.softedge.safedoktor.models.swaggerModels.ApptBooking;
import com.softedge.safedoktor.models.swaggerModels.BookingsList;
import com.softedge.safedoktor.models.swaggerModels.DataModel;
import com.softedge.safedoktor.models.swaggerModels.PatientModel;
import com.softedge.safedoktor.models.swaggerModels.SwaggerAPI_ResponseArr;
import com.softedge.safedoktor.models.swaggerModels.rSwaggerAPI;
import com.softedge.safedoktor.models.swaggerModels.body.*;
import com.softedge.safedoktor.models.swaggerModels.response.*;
import com.softedge.safedoktor.service.SessionManagement;
import com.softedge.safedoktor.utilities.common_code;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softedge.safedoktor.utilities.AppConstants.*;

public class SwaggerCalls {

    private static WeakReference<Context> weakContext;
    private static final SwaggerClient swagger_Client = SafeDoktorService.createService(SwaggerClient.class);
    private static final paymentClient reddePayClient = reddeService.createService(paymentClient.class);

    //Login user with number and password
    //Doubles as token refreshing
    public static void login(@NonNull View view, Login loginModel){

        weakContext = new WeakReference<>(view.getContext());
        SessionManagement sessionManagement = new SessionManagement(view.getContext());
        appDB app_Db = appDB.getInstance(view.getContext());

        if (weakContext.get() instanceof LoginActivity){
            ((LoginActivity)weakContext.get()).toggleProbar(true);
        }

        Call<SwaggerAPI_ResponseArr<List<rLogin>>> call = swagger_Client.patientLogin(loginModel);

        call.enqueue(new Callback<SwaggerAPI_ResponseArr<List<rLogin>>>() {
            @Override
            public void onResponse(@NonNull Call<SwaggerAPI_ResponseArr<List<rLogin>>> call, @NonNull Response<SwaggerAPI_ResponseArr<List<rLogin>>> response) {
                Log.e("login", "we're in");

                    if (response.isSuccessful()){
                    Log.i("Safe", "successful");
                    if (response.body() != null) {
                        List<rLogin> userlogin = response.body().getData();

                        for (rLogin pats_Tokens: userlogin){

                            PatientModel patient = pats_Tokens.getPatient();

                            sessionManagement.createLoginSession(pats_Tokens);
                            sessionManagement.saveCredentials(loginModel.getUsername(),loginModel.getPassword());
                            //sessionManagement.createPatientDetail(patient);

                            //Toast.makeText(weakContext.get(), "Login worked - \n"  + patient.getPhonenumber(), Toast.LENGTH_LONG).show();

                            if (view.getContext() instanceof LoginActivity){
                                //Add patient object to room database
                                common_code.getDBExecutor().execute(() -> {
                                    Log.e("executor", "saving patient: " + patient.getPatientid());
                                    app_Db.safeDoktorAccessObj().addPatient(patient);
                                });
                                common_code.toDashboard(view.getContext());

//                            Load all appointments
                                //loadAppointments(view);
                            }

                        }
                    }

//                    AppConstants.PatientObj = patient;
//                    TokenString.tokenString = token.getTokenType() + " " + token.getToken();
//                    AppConstants.patientID = patient.getPatientid();
//                    AppConstants.patientPhoneNumber = patient.getPhonenumber();
//                    AppConstants.patientName = StringUtils.join(" ",patient.getFirstname(),patient.getMiddlename(),patient.getLastname());

                    //Log.i("Safe", "Patient number is " + AppConstants.patientPhoneNumber);

                    //common.fetchProfilePicture();
                    //AppConstants.IS_LOGIN = true;
                }
                else {
                    Toast.makeText(view.getContext(), "Invalid Phone number or Password", Toast.LENGTH_LONG).show();
                    //showProgress(false);
                    try {
                        String error= null;
                        if (response.errorBody() != null) {
                            error = response.errorBody().string();
                        }
                        Log.e("login wrong pass",""+error);

                        if (view.getContext() instanceof  LoginActivity){

                            ((LoginActivity)view.getContext()).toggleProbar(false);
                        }else {
                            Log.e("login instance", false + " Class: " +view.getContext().getClass().toString());

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }


            @Override
            public void onFailure(@NonNull Call<SwaggerAPI_ResponseArr<List<rLogin>>> call, @NonNull Throwable t) {
                //Toast.makeText(getApplicationContext(), "Network Error, please try again", Toast.LENGTH_SHORT).show();
                common_code.Mysnackbar(view,t.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    //Check user registration status
    public static void CheckPhoneNumber(View view,PhoneNumber phoneNumber){
//        enterPhoneDialog.setMessage("Working. Please wait...");
//        enterPhoneDialog.show();
        WeakReference<Context> weak_context = new WeakReference<>(view.getContext());

        Call<SwaggerAPI_ResponseArr<List<rValidation>>> firstrequest = swagger_Client.checkValidatePhoneCode(phoneNumber.getPhonenumber());
        firstrequest.enqueue(new Callback<SwaggerAPI_ResponseArr<List<rValidation>>>() {
            @Override
            public void onResponse(@NonNull Call<SwaggerAPI_ResponseArr<List<rValidation>>> call, @NonNull Response<SwaggerAPI_ResponseArr<List<rValidation>>> response) {
//                Log.i("Safe", "Code");
//                Log.i("Safe", response.code() + "");
//                enterPhoneDialog.dismiss();
                if (response.code() == HttpURLConnection.HTTP_OK || response.code() == HttpURLConnection.HTTP_CREATED)
                {
                    Log.i("Safe", "successful");
                    //swagStartRegistrationResponse = response.body();
                    if (response.body() != null ) {
                        List<rValidation> validations_list = response.body().getData();

                        if(validations_list != null && validations_list.size() > 0){
                            if(validations_list.get(0).getIsvalidated()){

                                // enterPhoneDialog.dismiss();
                                if(validations_list.get(0).getPatientid() > 0){// Has already registered remind to login or reset password
                                    //Toast.makeText(getApplicationContext(), getResources().getString(R.string.alert_already_registered), Toast.LENGTH_LONG).show();
                                    common_code.Mysnackbar(view,weak_context.get().getResources().getString(R.string.alert_already_registered), Snackbar.LENGTH_LONG).show();
                                }else{
                                    //no ID found, mark as new registration and continue process
                                    //Toast.makeText(getApplicationContext(), getResources().getString(R.string.alert_already_validated), Toast.LENGTH_LONG).show();
                                    if (view.getContext() instanceof SignupActivity){
                                        ((SignupActivity)view.getContext()).pass_dataTo_verification();
                                    }
                                    common_code.toLogin(weak_context.get());
                                }
                            }
//                            else{
//                                fab.setText("Code Already sent via SMS");
//                                fab.setTextSize(12);
//                                fab.setEnabled(false);
//                                //waitUntilSMSReceived();
//                                resend_code.setVisibility(View.VISIBLE);
//                                //phonenumber.setEnabled(false);
//                            }

                            return;

                        }
                    }

                }

                startRegProcess(view,phoneNumber);

            }

            @Override
            public void onFailure(@NonNull Call<SwaggerAPI_ResponseArr<List<rValidation>>> call, @NonNull Throwable t) {
                //Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
                //enterPhoneDialog.dismiss();
            }
        });


    }

    //Registration process step one
    public static void startRegProcess(View view,PhoneNumber phoneNumber){

        Call<SwaggerAPI_ResponseArr<List<rValidation>>> call = swagger_Client.startRegistration(phoneNumber);
        call.enqueue(new Callback<SwaggerAPI_ResponseArr<List<rValidation>>>() {
            @Override
            public void onResponse(@NonNull Call<SwaggerAPI_ResponseArr<List<rValidation>>> call, @NonNull Response<SwaggerAPI_ResponseArr<List<rValidation>>> response) {
                Log.i(this.toString(), "Code");
                Log.i(this.toString(), response.code() + "");
                if (response.code() == HttpURLConnection.HTTP_OK || response.code() == HttpURLConnection.HTTP_CREATED){
                    Log.i("Safe", "successful");
                    if (response.body() != null) {
                        List<rValidation> validations_list  = response.body().getData();

                        String validation_code = validations_list.get(0).getValidationcode();
                        String usernumber = validations_list.get(0).getPhonenumber();

                        ValidateCode phoneCode = new ValidateCode(validation_code,usernumber);
                        if (view.getContext() instanceof VerificationActivity){
                            ((VerificationActivity) view.getContext()).codeValues_into_views(validation_code);
                        }
                        startValidation(view,phoneCode);

                        //TODO start timer to wait for sms here
                        //waitUntilSMSReceived();
                    }
                    //enterPhoneDialog.dismiss();

//                    fab.setText("Waiting to receive SMS for auto validation");
//                    fab.setTextSize(12);
//                    fab.setEnabled(false);
//
//                    resend_code.setVisibility(View.VISIBLE);
                }
//                else{
//                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.alert_actionnotcompleted), Toast.LENGTH_SHORT).show();
//                    enterPhoneDialog.dismiss();
//                }
            }

            @Override
            public void onFailure(@NonNull Call<SwaggerAPI_ResponseArr<List<rValidation>>> call, @NonNull Throwable t) {
                //Toast.makeText(getApplicationContext(), getResources().getString(R.string.alert_networkissues), Toast.LENGTH_LONG).show();
                t.printStackTrace();
                //enterPhoneDialog.dismiss();
            }
        });

    }

    //Registration process step two
    public static void startValidation(View view, ValidateCode validPhoneCode) {

//        enterPhoneDialog.setMessage("Validating phone code. Please wait...");
//        enterPhoneDialog.show();
        Call<SwaggerAPI_ResponseArr<List<rIsValid>>> call= swagger_Client.validatePhoneCode(validPhoneCode);

        call.enqueue(new Callback<SwaggerAPI_ResponseArr<List<rIsValid>>>() {
            @Override
            public void onResponse(@NonNull Call<SwaggerAPI_ResponseArr<List<rIsValid>>> call, @NonNull Response<SwaggerAPI_ResponseArr<List<rIsValid>>>response) {
                Log.i("Safe", "startValidation Called");
                if (response.code() == HttpURLConnection.HTTP_FORBIDDEN || response.code() == HttpURLConnection.HTTP_UNAUTHORIZED){
                    //enterPhoneDialog.dismiss();
                    //Toast.makeText(getApplicationContext(), "Authentication Issue. Please try again", Toast.LENGTH_SHORT).show();
                    common_code.Mysnackbar(view,"Authentication Issue. Please try again",Snackbar.LENGTH_SHORT).show();
                }
                else if (response.isSuccessful()) {
                    Log.i("Safe", "Fetching validation");
                    //swagResponse = response.body();
                    if (response.body() != null) {
                        List<rIsValid> isValidList = response.body().getData();
                        for (rIsValid validCode : isValidList) {
                            if (validCode.getValid()) {
                                common_code.Mysnackbar(view,"Phone Number Verified.",Snackbar.LENGTH_SHORT).show();

                                if (view.getContext() instanceof VerificationActivity){
                                    ((VerificationActivity) view.getContext()).UserRegistration();
                                }
//                                Intent intent = new Intent(getApplicationContext(), SignUp.class);
//                                startActivity(intent);
                            } else {
                                //enterPhoneDialog.dismiss();
                                //Toast.makeText(getApplicationContext(), "Phone number do not match with code. Please check and trya again", Toast.LENGTH_LONG).show();
                                common_code.Mysnackbar(view,"Phone number does not match with code. Please check and try again",Snackbar.LENGTH_SHORT).show();
                                Log.i("Safe", "Fetching error code " + response.code() + "");
                                try {
                                    if (response.errorBody() != null) {
                                        Log.i("Safe", "Response is " + response.errorBody().string());
                                    }

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<SwaggerAPI_ResponseArr<List<rIsValid>>> call, @NonNull Throwable t) {
                //Log.i("Safe", ""+t.getMessage());
//                enterPhoneDialog.dismiss();
//                Toast.makeText(getApplicationContext(), "Network Error. Please try again", Toast.LENGTH_LONG).show();
            }
        });
    }

    //Registration process user details sign up
    public static void StartSignUp(View view,UserReg registrationModel){
        //signUpProgressDialog.show();
        appDB app_Db = appDB.getInstance(view.getContext());

        Call<SwaggerAPI_ResponseArr<List<PatientModel>>> call = swagger_Client.patientRegister(registrationModel);
        call.enqueue(new Callback<SwaggerAPI_ResponseArr<List<PatientModel>>>() {
            @Override
            public void onResponse(@NonNull Call<SwaggerAPI_ResponseArr<List<PatientModel>>> call, @NonNull Response<SwaggerAPI_ResponseArr<List<PatientModel>>> response) {
                Log.i("Safe", "we're in");
                Log.i("Safe", "Fetching error code " + response.code() + "");
                //signUpProgressDialog.dismiss();
                if (response.isSuccessful()){
                    Log.i("Safe", "successful");
                    //swagRegisterResponse = response.body();
                    if (response.body() != null) {
                        List<PatientModel>patients_list = response.body().getData();
                        PatientModel regPatient = patients_list.get(0);

                        common_code.getDBExecutor().execute(() -> app_Db.safeDoktorAccessObj().addPatient(regPatient));

                        //take user to login screen after successful registration
                        Toast.makeText(view.getContext(),"Sign up successful, Please login to proceed", Toast.LENGTH_LONG).show();
                        common_code.toLogin(view.getContext());

                    }
                    //registeredPatient = registerData.get(0);
                    //AppConstants.patientID = registeredPatient.getPatientid();


                }
                else
                {
                    Toast.makeText(view.getContext(), "Sign Up Failed. Please Try again", Toast.LENGTH_LONG).show();
                    //signUpProgressDialog.dismiss();
                }
            }


            @Override
            public void onFailure(@NonNull Call<SwaggerAPI_ResponseArr<List<PatientModel>>> call, @NonNull Throwable t) {
                //signUpProgressDialog.dismiss();
                t.printStackTrace();
                Log.i("message", t.toString());
                //Toast.makeText(getApplicationContext(),getResources().getString(R.string.alert_networkissues), Toast.LENGTH_LONG).show();

            }
        });

    }

    //Todo look at loading appointments at the right place
    public static void loadAppointments(View view){
        //appointmentsRefresh.setRefreshing(true);
        //Token type + " " + token string
        WeakReference<Context> weakcontext = new WeakReference<>(view.getContext());
        SharedPreferences appPref = common_code.appPref(weakcontext.get());

        int patientID = appPref.getInt(KEY_PATIENT_ID,0);
        String fulltoken = appPref.getString(KEY_FULL_TOKEN,null);

//        Log.e("Appt","Pat id: " + patientID);

        Call<rSwaggerAPI<List<rAppt_Content>>> call = swagger_Client.getAllAppointments(fulltoken, patientID);

        call.enqueue(new Callback<rSwaggerAPI<List<rAppt_Content>>>() {
            @Override
            public void onResponse(@NonNull Call<rSwaggerAPI<List<rAppt_Content>>> call,
                                   @NonNull Response<rSwaggerAPI<List<rAppt_Content>>> response) {


                if (response.body() != null && response.body().getData() != null) {

                    List<rAppt_Content> apptData_list = response.body().getData().getContent();

//                    Toast.makeText(view.getContext(),"Appt response: " + apptData_list.size(),Toast.LENGTH_SHORT).show();
                    Log.e("Appointment",String.valueOf(apptData_list.size()));

                    for (rAppt_Content content : apptData_list){
                        saveAppointment_stuff(content, weakcontext.get());
                    }

                    //                        Login user after saving appointments and booking details
                    if (weakcontext.get() instanceof LoginActivity){
                        common_code.toDashboard(view.getContext());
                        ((LoginActivity)weakcontext.get()).toggleProbar(false);
                    }

                }

//
            }

            @Override
            public void onFailure(@NonNull Call<rSwaggerAPI<List<rAppt_Content>>> call, @NonNull Throwable t) {

                Toast.makeText(view.getContext(),"Appointment load error:" + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("Appt Load Error", "Fetching error: " + t.getMessage());
                //Log.i("Safe", "Fetching error: " + t.getMessage() + "");
            }
        });
    }

    private static void saveAppointment_stuff(rAppt_Content content, Context context){

        appDB app_Db = appDB.getInstance(context);
        int bookingid = content.getAppointment().getBookingid();
        String bookingDate = content.getAppointment().getBookingdate();

        BookingsList bookingsList = new BookingsList(bookingid,content.getAppointment().getPatientid(),bookingDate);

        common_code.getDBExecutor().execute(()->app_Db.safeDoktorAccessObj().addBooking(bookingsList));

        if (content.getAppointment() != null){
            common_code.getDBExecutor().execute(()->app_Db.safeDoktorAccessObj().addAppointment(content.getAppointment()));
//            Log.e("appt save","appointment saved");
        }

        if (content.getDetails() != null && content.getDetails().size() > 0){
            app_Db.safeDoktorAccessObj().addDetails(content.getDetails().get(0));
            //Log.e("appt save","details saved");
        }

        if (content.getPayments() != null && content.getPayments().size() > 0){
            app_Db.safeDoktorAccessObj().addPayments(content.getPayments().get(0));
            //Log.e("appt save","payments saved");
        }

        if (content.getNotifications() != null && content.getNotifications().size() > 0){
            app_Db.safeDoktorAccessObj().addNotifications(content.getNotifications().get(0));
            //Log.e("appt save","notifications saved");
        }

        if (context instanceof VirtualAppt_Activity){
            ((VirtualAppt_Activity)context).dismissSubmitDialog();
            ((Activity) context).onBackPressed();
            Toast.makeText(weakContext.get(), "Appointment Booking Successful.", Toast.LENGTH_LONG).show();

//          delete time slot from DB
            common_code.getDBExecutor().execute(() -> app_Db.safeDoktorAccessObj().deleteTimeSlot(content.getDetails().get(0).getSlotid()));

        }

        //Log.e("appt save","saving ends");


    }

    //Get specialties for appointment booking
    public static void getSpecialties(View view) {

        weakContext = new WeakReference<>(view.getContext());

        appDB appDb = appDB.getInstance(weakContext.get());

        Call<rSwaggerAPI<List<Specialties>>> call = swagger_Client.getClinicalSpecialties(common_code.getLoginToken(weakContext.get()));

        call.enqueue(new Callback<rSwaggerAPI<List<Specialties>>>() {
            @Override
            public void onResponse(@NonNull Call<rSwaggerAPI<List<Specialties>>> call,@NonNull Response<rSwaggerAPI<List<Specialties>>> response) {

                    if (response.isSuccessful()) {

                    DataModel<List<Specialties>> responsedata = null;

                    if (response.body() != null) {
                        responsedata = response.body().getData();
                    }

                    if (responsedata != null && responsedata.getContent().size() > 0) {

                        for (Specialties specialty : responsedata.getContent()){

                            //Save specialty to database
                            common_code.getDBExecutor().execute(()-> appDb.safeDoktorAccessObj().addSpecialties(specialty));

                        }

//                        if (weakContext.get() instanceof VirtualAppt_Activity){
//                            ((VirtualAppt_Activity)weakContext.get()).dismissDialog();
//                        }

                    }

                }

            }

            @Override
            public void onFailure(@NonNull Call<rSwaggerAPI<List<Specialties>>> call,@NonNull Throwable t) {
//                listener.onTaskCompleted(false);
                Log.e("Safe", "Specialties fetch error: " + t.getMessage());

//                if (weakContext.get() instanceof VirtualAppt_Activity){
//                    ((VirtualAppt_Activity)weakContext.get()).dismissDialog();
//                }
                Toast.makeText(weakContext.get(), "Connection Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    //Get time slots by specialty Id
    public static void getSpecialtyTimeSlots(View view, int specialityid) {

        weakContext = new WeakReference<>(view.getContext());
        appDB appDbase = appDB.getInstance(weakContext.get());

        Call<SwaggerAPI_ResponseArr<List<TimeSlot>>> call = swagger_Client.getSpecialtyAvailableServices(common_code.getLoginToken(weakContext.get()), specialityid, common_code.addDayDate(0), common_code.addDayDate(7) );

        Log.e("TimeSlot","Fetching started");

        call.enqueue(new Callback<SwaggerAPI_ResponseArr<List<TimeSlot>>>() {
            @Override
            public void onResponse(@NonNull Call<SwaggerAPI_ResponseArr<List<TimeSlot>>> call, @NonNull Response<SwaggerAPI_ResponseArr<List<TimeSlot>>> response) {
//                  Log.e("TimeSlot", "loadSlot Called:" + response.code() + "\n count: " + response.body().getData().size());
//                if (response.code() == HttpURLConnection.HTTP_FORBIDDEN || response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
//
//                    sessionManagement.logoutSessionExpired();
//
//
//                } else

                    if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {

                    List<TimeSlot> specialtyTimeSlots  = response.body().getData();

                        Log.e("TimeSlot response",response.message());

                    if (specialtyTimeSlots != null && specialtyTimeSlots.size() > 0) {

                        for (TimeSlot timeSlot : specialtyTimeSlots){

                            Log.e("TimeSlot",timeSlot.getDoctorname() + " date: " + timeSlot.getDate());
                            common_code.getDBExecutor().execute(() -> appDbase.safeDoktorAccessObj().addTimeSlots(timeSlot));
                        }

                        if (weakContext.get() instanceof VirtualAppt_Activity){
                            ((VirtualAppt_Activity) weakContext.get()).getDoctorsForSpecialtySlots(specialityid);
                        }

                    }else {

                        if (weakContext.get() instanceof VirtualAppt_Activity){
                            ((VirtualAppt_Activity) weakContext.get()).getDoctorsForSpecialtySlots(specialityid);
                            common_code.Mysnackbar(view,"No slots Available",Snackbar.LENGTH_SHORT).show();
                        }
                    }

//                    listener.onTaskCompleted(true);
                }else {

                        if (weakContext.get() instanceof VirtualAppt_Activity){
                            ((VirtualAppt_Activity) weakContext.get()).getDoctorsForSpecialtySlots(specialityid);
                        }
                    }

            }

            @Override
            public void onFailure(@NonNull Call<SwaggerAPI_ResponseArr<List<TimeSlot>>> call,@NonNull Throwable t) {

                if (weakContext.get() instanceof VirtualAppt_Activity){
                    ((VirtualAppt_Activity) weakContext.get()).getDoctorsForSpecialtySlots(specialityid);
                }

//                listener.onTaskCompleted(false);
                Toast.makeText(weakContext.get(), "Error Fetching Time Slots: " + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    //--------------------USER -> PHOTO -> SAVE-----------------------------------------------------
    private static void getUserPhoto(Context context, String token, UserAccount user){

        swagger_Client.getUserPhoto(token,user.getId()).enqueue(new Callback<SwaggerAPI_ResponseArr<List<Userphoto>>>() {

            @Override
            public void onResponse(@NonNull Call<SwaggerAPI_ResponseArr<List<Userphoto>>> call,@NonNull Response<SwaggerAPI_ResponseArr<List<Userphoto>>> response) {

                if (response.isSuccessful() && response.body() != null){

                    Log.e("pro pic","Getting user photo: " + user.getId());
                    String userPhoto;

                    if (response.body().getData().size() > 0){
                        Userphoto photo = response.body().getData().get(0);
                        userPhoto = photo.getPhoto();
                        //TODO reuse for user profile picture
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<SwaggerAPI_ResponseArr<List<Userphoto>>> call,@NonNull Throwable t) {
                Log.e("pro pic","Photo Error: "+ t.getMessage());
            }
        });
    }
    //--------------------USER -> PHOTO -> SAVE-----------------------------------------------------

    public static void getSpecialtyDoctors(View view,Specialties specialty) {

        weakContext = new WeakReference<>(view.getContext());
        appDB appDbase =  appDB.getInstance(weakContext.get());

        SharedPreferences appPref = common_code.appPref(weakContext.get());

        String fullToken = appPref.getString(KEY_FULL_TOKEN,null);

        Call<SwaggerAPI_ResponseArr<List<DoctorOutObj>>> call = swagger_Client.getClinicalSpecialtyDoctors(fullToken, specialty.getId());

        //Log.i("Safe", mSafeDoctorService.getFormData(TokenString.tokenString, AppConstants.patientID).request().url().toString());

        call.enqueue(new Callback<SwaggerAPI_ResponseArr<List<DoctorOutObj>>>() {
            @Override
            public void onResponse(@NonNull Call<SwaggerAPI_ResponseArr<List<DoctorOutObj>>> call,@NonNull Response<SwaggerAPI_ResponseArr<List<DoctorOutObj>>> response) {
                //Create doctor and specialty object from result

                Log.i("TheSafe", response.code() + "");
//                if (response.code() == HttpURLConnection.HTTP_FORBIDDEN || response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
//
//                } else

                if (response.isSuccessful()) {

                    if (response.body() != null && response.body().getData() != null && response.body().getData().size() > 0) {

                        for (DoctorOutObj doc:response.body().getData()) {

                            if(!doc.getDoctor().isLocked() && doc.getDoctor().getUserprofileid() == ROLE_DOCTOR){
//
                                Doctor doctor = new Doctor(
                                        doc.getDoctor().getId(),
                                        doc.getDoctor().getTitle().getName(),
                                        doc.getDoctor().getFirstname(),
                                        doc.getDoctor().getLastname(),
                                        doc.getDoctor().getOthername(),
                                        doc.getDoctor().getGendergroup().getName(),
                                        doc.getDoctor().getEmailaddress(),
                                        ""
                                );

                                if (doc.getPicture() != null){
                                    doctor.setDoctorPhoto(doc.getPicture().getPhoto());
                                }

                                Doctor_Specialty doc_spec = new Doctor_Specialty(specialty.getId(),doc.getDoctor().getId(),specialty.getName());

                                //Save doctor and specialty to db
                                common_code.getDBExecutor().execute(() -> {
//                                    Log.e("Doc Spec", "Saving doctor:" + doctor.getFullName());
                                    appDbase.safeDoktorAccessObj().addDoctor(doctor);
                                    appDbase.safeDoktorAccessObj().addDoc_Specs(doc_spec);
                                });


                            }

                        }

                    }

//                    listener.onTaskCompleted(true);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SwaggerAPI_ResponseArr<List<DoctorOutObj>>> call,@NonNull Throwable throwable) {
//                listener.onTaskCompleted(false);
                Log.e("Specialty_doctors",throwable.toString());

//                Toast.makeText(context, context.getResources().getString(R.string.alert_networkissues), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public static void loadServiceFee(View view,int chatTypeID,int serviceId) {

        weakContext = new WeakReference<>(view.getContext());

        Call<SwaggerAPI_ResponseArr<List<rServiceFee>>> call = swagger_Client.getServiceFee(common_code.getLoginToken(weakContext.get()),
                serviceId, chatTypeID, common_code.currentUser(weakContext.get()).getPatientid());

        call.enqueue(new Callback<SwaggerAPI_ResponseArr<List<rServiceFee>>>() {
            @Override
            public void onResponse(@NonNull Call<SwaggerAPI_ResponseArr<List<rServiceFee>>> call,@NonNull Response<SwaggerAPI_ResponseArr<List<rServiceFee>>> response) {

//                if (response.code() == HttpURLConnection.HTTP_FORBIDDEN || response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
//                    serviceProgressDialog.dismiss();
//                    Toast.makeText(getApplicationContext(), "Your session has expired. Please relogin", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(), FormLogin.class);
//                    startActivity(intent);
//                } else

                    if (response.isSuccessful() && response.body() != null) {
//                    Log.i("Safe", "Fetching Cash");
//                    serviceFeeResponse = response.body();

                        List<rServiceFee> serviceFees = response.body().getData();

                    for (rServiceFee fee : serviceFees) {

                        if (weakContext.get() instanceof VirtualAppt_Activity){
                            ((VirtualAppt_Activity) weakContext.get()).setServiceFee(fee.getFee());
                        }

                    }

                } else {
                    //Toast.makeText(getApplicationContext(), "Unable To Fetch. Please Contact HelpLine", Toast.LENGTH_LONG).show();
                    Log.e("fee fetch", "Fetching fee error code " + response.code() + "");
                }
            }

            @Override
            public void onFailure(@NonNull Call<SwaggerAPI_ResponseArr<List<rServiceFee>>> call,@NonNull Throwable t) {

                //Log.i("SafeRes", t.getMessage());
//                serviceProgressDialog.dismiss();
//                Toast.makeText(getApplicationContext(), "Network Error, please try again", Toast.LENGTH_SHORT).show();
                Log.e("fee fetch", "Fetching fee error: " + t.getMessage());
            }
        });
    }

    public static void loadBookAndPay(View view, ApptBooking booking, int serviceFee, String consultTypeName){

        weakContext = new WeakReference<>(view.getContext());

        appDB appDbase =  appDB.getInstance(weakContext.get());
//        serviceProgressDialog.setMessage("Booking Appointment and Making Payment");
//        serviceProgressDialog.show();
//
//        String wallet = AppConstants.patientPhoneNumber + "555";
//        bookNPay.setConsultationchattypeid(AppConstants.consultationchattypeid);
//        bookNPay.setDoctorid(AppConstants.doctorid);
//        bookNPay.setMobilemoneynetworkid(AppConstants.mobilemoneynetworkid);
//        bookNPay.setPatientid(AppConstants.patientID);
//        bookNPay.setReasonid(AppConstants.reasonid);
//        bookNPay.setServiceid(AppConstants.serviceID);
//        bookNPay.setSlotid(AppConstants.slotid);
//
//        if(!alternativeNumberEditText.getText().toString().isEmpty())
//        {
//            bookNPay.setWalletnumber(alternativeNumberEditText.getText().toString());
//        }
//        else {
//            bookNPay.setWalletnumber(AppConstants.patientPhoneNumber);
//        }
        String paymentDescription = "Payment for " + consultTypeName + " Consultation";


        Call<SwaggerAPI_ResponseArr<List<rAppt_Content>>> call = swagger_Client.bookAndPay(common_code.getLoginToken(weakContext.get()), booking);

//        Log.i("Safe", "After loadBookNPay, wallet is " + bookNPay.getWalletnumber().toString() + " pNo is " + wallet);
//        Log.i("Safe", "After loadBookNPay, chat is " + bookNPay.getConsultationchattypeid() + "");
//        Log.i("Safe", "After loadBookNPay, patientid is " + bookNPay.getPatientid() + "");
//        Log.i("Safe", "After loadBookNPay, network is " + bookNPay.getMobilemoneynetworkid() + "");
//        Log.i("Safe", "After loadBookNPay, service is " + bookNPay.getServiceid() + "");
//        Log.i("Safe", "After loadBookNPay, bookNPay is " + bookNPay.getSlotid() + "");
//        Log.i("Safe", "After loadBookNPay, bookNPay is " + bookNPay.getReasonid() + "");
//        Log.i("Safe", "After loadBookNPay, bookNPay is " + bookNPay.getDoctorid() + "");
//        Log.i("Safe", "After loadBookNPay, bookNPay is " + bookNPay.getVodafonetoken() + "");

        call.enqueue(new Callback<SwaggerAPI_ResponseArr<List<rAppt_Content>>>() {
            @Override
            public void onResponse(@NonNull Call<SwaggerAPI_ResponseArr<List<rAppt_Content>>> call,@NonNull Response<SwaggerAPI_ResponseArr<List<rAppt_Content>>> response) {
                Log.e("booking", "loadBookNPay Called");

//                if (response.code() == HttpURLConnection.HTTP_FORBIDDEN) {
//                    serviceProgressDialog.dismiss();
//                    Toast.makeText(getApplicationContext(), "Please Login", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(), FormLogin.class);
//                    startActivity(intent);
//                } else

                    if (response.isSuccessful() && response.body() != null && response.body().getData() != null){
//                    serviceProgressDialog.dismiss();
                    Log.e("booking", "Fetching BookNPay Response");
//                    bookNPayResponse = response.body();
//                    confirmedAppointmentList = bookNPayResponse.getData();
                    Appointment appointment = response.body().getData().get(0).getAppointment();

                    Log.e("appt", "booking Id: " + appointment.getBookingid());
                        Log.e("appt", "momo id: " + booking.getMobilemoneynetworkid());

                        paymentBody payment_body = new paymentBody(
                                serviceFee,booking.getWalletnumber(),
                                appointment.getBookingnumber(),paymentDescription,
                                booking.getMobilemoneynetworkid(),booking.getWalletnumber());

                    payment_body.setClientTransId(String.valueOf(appointment.getBookingid()));
                        Log.e("appt", "app id: " + payment_body.getAppId());

                    if (serviceFee > 0) {

                        //call redde payment
                        Toast.makeText(weakContext.get(), "Booking Successful. Awaiting Payment", Toast.LENGTH_SHORT).show();
                        makePayment(payment_body);

                    }else {

                        //save appointment and return to appointment page
                        saveAppointment_stuff(response.body().getData().get(0),weakContext.get());
//                        common_code.getDBExecutor().execute(() -> appDbase.safeDoktorAccessObj().addAppointment(appointment));
                    }

//                    popUpMakePaymentTransaction();

                } else {
//                    serviceProgressDialog.dismiss();
//                    Toast.makeText(getApplicationContext(), "Unable To Make Booking. Please Contact HelpLine", Toast.LENGTH_LONG).show();
//                    Log.i("Safe", "Fetching error code " + response.code() + "");
//                    try {
//                        Log.i("Safe", "Response is " + response.errorBody().string());
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                    Log.i("SafeTggggg", "Out kwraa");
                }
            }

            @Override
            public void onFailure(@NonNull Call<SwaggerAPI_ResponseArr<List<rAppt_Content>>> call,@NonNull Throwable t) {
                Log.i("SafeRes","Fetching error"+  t.getMessage());

//                try {
//                    Log.i("SafeRes","Fetching error"+  t.getMessage());
//                    serviceProgressDialog.dismiss();
//                    Toast.makeText(getApplicationContext(), "Network Error, please try again", Toast.LENGTH_SHORT).show();
//                    t.printStackTrace();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    private static void makePayment(paymentBody payment){

        Call<rPayment> reddeCall = reddePayClient.payment(REDDE_API_KEY,payment);

        reddeCall.enqueue(new Callback<rPayment>() {
            @Override
            public void onResponse(@NonNull Call<rPayment> call,@NonNull Response<rPayment> response) {

                if (response.isSuccessful() && response.body() != null){
                    Log.e("pay",response.body().getReason());
                }else if (response.errorBody() != null){
                    try {
                        Log.e("pay Error",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onFailure(@NonNull Call<rPayment> call,@NonNull Throwable t) {
                Log.e("payment","Failed to pay: " + t.getMessage());
            }
        });
    }

//    TODO GET ONLINE VISITS
    public void getOnlineVisits(View view,int patientid,String startDate,String endDate) {

        //String from=formateDate("2018-02-01 00:00:00");
        weakContext = new WeakReference<>(view.getContext());
        Call<SwaggerAPI_ResponseArr<List<rOnlineVisits>>> call = swagger_Client.getOnlineVisits(common_code.getLoginToken(weakContext.get()),patientid,startDate,endDate);

        call.enqueue(new Callback<SwaggerAPI_ResponseArr<List<rOnlineVisits>>>() {
            @Override
            public void onResponse(@NonNull Call<SwaggerAPI_ResponseArr<List<rOnlineVisits>>> call, @NonNull Response<SwaggerAPI_ResponseArr<List<rOnlineVisits>>> response) {

                List<rOnlineVisits> visitList=new ArrayList<>();

//                if (response.code() == HttpURLConnection.HTTP_FORBIDDEN || response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
//                    sessionManagement.logoutSessionExpired();
//
//
//                } else

                    if (response.isSuccessful() && response.body() != null && response.body().getData().size() > 0) {
//                        TODO save online visits
                    visitList =response.body().getData();
//                    AppConstants.CACHE_ONLINE_VISIT=visitList;

//                    listener.onTaskCompleted(visitList );
                }
//                    else {
//                    try {
//                        Log.e("getConsultationReferral",response.errorBody().string().toString());
//                        String error=response.errorBody().string().toString();
//                        Log.e("getOnlineVisits",error);
//                        tokenerror(error);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    listener.onTaskCompleted(visitList);
//                }
//
//                if(swipeRefreshLayout!=null){
//                    swipeRefreshLayout.setRefreshing(false);
//
//                }
            }

            @Override
            public void onFailure(@NonNull Call<SwaggerAPI_ResponseArr<List<rOnlineVisits>>> call,@NonNull Throwable t) {
                //Toast.makeText(context,"Fetching Online Visits....Network Error occured",Toast.LENGTH_LONG).show();
//                try{
//                    Log.e("getConsultationReferral","Fetching error"+ t.getMessage());
//                }catch (Exception e){
//
//                }
//
//                if(swipeRefreshLayout!=null){
//                    swipeRefreshLayout.setRefreshing(false);
//
//                }
            }
        });


    }

//    private void loadBookingStatus(int bookingid) {
//
////        serviceProgressDialog.setMessage("Verifying Payment. \nPlease ensure payment has been made");
////        serviceProgressDialog.show();
//
//        Log.i("Safe", "loadBooking About To be called");
//
//        Call<SwaggerAPI_ResponseArr> call = swagger_Client.getBookingStatus(common_code.getLoginToken(weakContext.get()), bookingid);
////        Log.i("Safe", "Fee URL is " + mSafeDoctorService.getBookingStatus(TokenString.tokenString, bookingid).request().url().toString());
//        call.enqueue(new Callback<SwagArrayResponseModel>() {
//            @Override
//            public void onResponse(Call<SwagArrayResponseModel> call, Response<SwagArrayResponseModel> response) {
//                Log.i("Safe", "loadBookingStatus Called");
//                if (response.code() == HttpURLConnection.HTTP_FORBIDDEN) {
//                    serviceProgressDialog.dismiss();
//                    Toast.makeText(getApplicationContext(), "Please Login", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(), FormLogin.class);
//                    startActivity(intent);
//                } else if (response.code() == 200) {
//                    Log.i("Safe", "Fetching Booking Status");
//                    bookingStatusResponse = response.body();
//                    serviceProgressDialog.dismiss();
//                    status = true;
//                    Toast.makeText(getApplicationContext(), "Payment Successful", Toast.LENGTH_SHORT).show();
//                    finish();
//                } else if (response.code() == 500)
//                {
//                    serviceProgressDialog.dismiss();
//                    Toast.makeText(getApplicationContext(), "Payment Pending. Please Try Again", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    serviceProgressDialog.dismiss();
//                    Toast.makeText(getApplicationContext(), "Unable To Fetch Booking Status. Please Contact HelpLine", Toast.LENGTH_SHORT).show();
//                    Log.i("Safe", "Fetching fee error code " + response.code() + "");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SwagArrayResponseModel> call, Throwable t) {
//                Log.i("SafeRes", "Fetching error"+ t.getMessage());
//                serviceProgressDialog.dismiss();
//                Toast.makeText(getApplicationContext(), "Network Error, please try again", Toast.LENGTH_SHORT).show();
//                t.printStackTrace();
//            }
//        });
//    }

    }



