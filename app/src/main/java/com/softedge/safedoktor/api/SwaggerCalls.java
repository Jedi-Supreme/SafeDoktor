package com.softedge.safedoktor.api;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.activities.SignupActivity;
import com.softedge.safedoktor.activities.VerificationActivity;
import com.softedge.safedoktor.databases.appDB;
import com.softedge.safedoktor.models.swaggerModels.PatientModel;
import com.softedge.safedoktor.models.swaggerModels.SwaggerAPI_ResponseModel;
import com.softedge.safedoktor.models.swaggerModels.body.Login;
import com.softedge.safedoktor.models.swaggerModels.body.PhoneNumber;
import com.softedge.safedoktor.models.swaggerModels.body.UserReg;
import com.softedge.safedoktor.models.swaggerModels.body.ValidateCode;
import com.softedge.safedoktor.models.swaggerModels.response.rIsValid;
import com.softedge.safedoktor.models.swaggerModels.response.rLogin;
import com.softedge.safedoktor.models.swaggerModels.response.rToken;
import com.softedge.safedoktor.models.swaggerModels.response.rValidation;
import com.softedge.safedoktor.service.SafeDoctorSMSReceiver;
import com.softedge.safedoktor.service.SessionManagement;
import com.softedge.safedoktor.utilities.common_code;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission_group.SMS;

public class SwaggerCalls {

    private static SwaggerClient swagger_Client = SafeDoktorService.createService(SwaggerClient.class);

    //Login user with number and password
    public static void login(View view, Login loginModel){

        SessionManagement sessionManagement = new SessionManagement(view.getContext());
        appDB app_Db = appDB.getInstance(view.getContext());

        // loginProgressDialog.show();
        Call<SwaggerAPI_ResponseModel<List<rLogin>>> call = swagger_Client.patientLogin(loginModel);

        call.enqueue(new Callback<SwaggerAPI_ResponseModel<List<rLogin>>>() {
            @Override
            public void onResponse(@NonNull Call<SwaggerAPI_ResponseModel<List<rLogin>>> call, @NonNull Response<SwaggerAPI_ResponseModel<List<rLogin>>> response) {
                Log.i("Safe", "we're in");
                if (response.code() ==  HttpURLConnection.HTTP_FORBIDDEN || response.code() == HttpURLConnection.HTTP_UNAUTHORIZED)
                {

                    try {
                        String error= null;
                        if (response.errorBody() != null) {
                            error = response.errorBody().string();
                        }
                        Log.e("login",""+error);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else if (response.isSuccessful() && response.code()==200){
                    Log.i("Safe", "successful");
                    if (response.body() != null) {
                        List<rLogin> userlogin = response.body().getData();

                        for (rLogin pats_Tokens: userlogin)
                        {
                            PatientModel patient = pats_Tokens.getPatient();
                            rToken token = pats_Tokens.getTokenModel();

                            sessionManagement.createLoginSession(token);
                            //sessionManagement.createPatientDetail(patient);

                            Toast.makeText(view.getContext(), "Login worked - \n"  + patient.getPhonenumber() + "\n" + patient.getGendergroupid(), Toast.LENGTH_LONG).show();

                            //Add patient object to room database
                            common_code.getDBExecutor(2).execute(() -> app_Db.safeDoktorAccessObj().addPatient(patient));

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
                        Log.e("login",""+error);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }


            @Override
            public void onFailure(@NonNull Call<SwaggerAPI_ResponseModel<List<rLogin>>> call,@NonNull Throwable t) {
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

        Call<SwaggerAPI_ResponseModel<List<rValidation>>> firstrequest = swagger_Client.checkValidatePhoneCode(phoneNumber.getPhonenumber());
        firstrequest.enqueue(new Callback<SwaggerAPI_ResponseModel<List<rValidation>>>() {
            @Override
            public void onResponse(@NonNull Call<SwaggerAPI_ResponseModel<List<rValidation>>> call,@NonNull Response<SwaggerAPI_ResponseModel<List<rValidation>>> response) {
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
            public void onFailure(@NonNull Call<SwaggerAPI_ResponseModel<List<rValidation>>> call,@NonNull Throwable t) {
                //Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
                //enterPhoneDialog.dismiss();
            }
        });


    }

    //Registration process step one
    public static void startRegProcess(View view,PhoneNumber phoneNumber){

        Call<SwaggerAPI_ResponseModel<List<rValidation>>> call = swagger_Client.startRegistration(phoneNumber);
        call.enqueue(new Callback<SwaggerAPI_ResponseModel<List<rValidation>>>() {
            @Override
            public void onResponse(@NonNull Call<SwaggerAPI_ResponseModel<List<rValidation>>> call,@NonNull Response<SwaggerAPI_ResponseModel<List<rValidation>>> response) {
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
            public void onFailure(@NonNull Call<SwaggerAPI_ResponseModel<List<rValidation>>> call,@NonNull Throwable t) {
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
        Call<SwaggerAPI_ResponseModel<List<rIsValid>>> call= swagger_Client.validatePhoneCode(validPhoneCode);

        call.enqueue(new Callback<SwaggerAPI_ResponseModel<List<rIsValid>>>() {
            @Override
            public void onResponse(@NonNull Call<SwaggerAPI_ResponseModel<List<rIsValid>>> call,@NonNull Response<SwaggerAPI_ResponseModel<List<rIsValid>>>response) {
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
            public void onFailure(@NonNull Call<SwaggerAPI_ResponseModel<List<rIsValid>>> call,@NonNull Throwable t) {
                //Log.i("Safe", ""+t.getMessage());
//                enterPhoneDialog.dismiss();
//                Toast.makeText(getApplicationContext(), "Network Error. Please try again", Toast.LENGTH_LONG).show();
            }
        });
    }

    //Registration process user details signup
    public static void StartSignUp(View view,UserReg registrationModel){
        //signUpProgressDialog.show();
        appDB app_Db = appDB.getInstance(view.getContext());

        Call<SwaggerAPI_ResponseModel<List<PatientModel>>> call = swagger_Client.patientRegister(registrationModel);
        call.enqueue(new Callback<SwaggerAPI_ResponseModel<List<PatientModel>>>() {
            @Override
            public void onResponse(@NonNull Call<SwaggerAPI_ResponseModel<List<PatientModel>>> call,@NonNull Response<SwaggerAPI_ResponseModel<List<PatientModel>>> response) {
                Log.i("Safe", "we're in");
                Log.i("Safe", "Fetching error code " + response.code() + "");
                //signUpProgressDialog.dismiss();
                if (response.isSuccessful()){
                    Log.i("Safe", "successful");
                    //swagRegisterResponse = response.body();
                    if (response.body() != null) {
                        List<PatientModel>patients_list = response.body().getData();
                        PatientModel regPatient = patients_list.get(0);

                        common_code.getDBExecutor(1).execute(() -> app_Db.safeDoktorAccessObj().addPatient(regPatient));

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
            public void onFailure(@NonNull Call<SwaggerAPI_ResponseModel<List<PatientModel>>> call, @NonNull Throwable t) {
                //signUpProgressDialog.dismiss();
                t.printStackTrace();
                Log.i("message", t.toString());
                //Toast.makeText(getApplicationContext(),getResources().getString(R.string.alert_networkissues), Toast.LENGTH_LONG).show();

            }
        });

    }

    public void loadConfirmedAppointments(final int statusid){
        //appointmentsRefresh.setRefreshing(true);
        Call<SwagResponseModel<List<ConfirmedAppointmentContentModel>>> call = mSafeDoctorService.getConfirmedAppointments(TokenString.tokenString, AppConstants.patientID,statusid);
        call.enqueue(new Callback<SwagResponseModel<List<ConfirmedAppointmentContentModel>>>() {
            @Override
            public void onResponse(Call<SwagResponseModel<List<ConfirmedAppointmentContentModel>>> call, Response<SwagResponseModel<List<ConfirmedAppointmentContentModel>>> response) {

                if (appointmentsRefresh.isRefreshing())
                {
                    appointmentsRefresh.setRefreshing(false);
                }

                if (response.code() == HttpURLConnection.HTTP_FORBIDDEN || response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    Toast.makeText(context, "Your session has expired please login again", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, FormLogin.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
                else if (response.isSuccessful())
                {
                    Log.i("Safe", "loadConfirmedAppoints successfully called");
                    confirmedAppointmentResponse = response.body();

                    if (confirmedAppointmentResponse.getData() == null) {
                        List<String> noServiceAvailable = new ArrayList<String>();
                        noServiceAvailable.add("No Appointment Available");
                        noAppointmentsTv.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        appointmentData = confirmedAppointmentResponse.getData();
                        confirmedAppointmentsList = appointmentData.getContent();
                        showAppointments(confirmedAppointmentsList,statusid);
                    }
                }
                else
                {
                    //appointmentFetchProgressDialog.dismiss();

                    try {
                        //Toast.makeText(getContext(), "Unable to Fetch Appointment. Please contact Helpline", Toast.LENGTH_SHORT).show();
                        String error=response.errorBody().string();
                        tokenerror(error);
                        Log.i("Safe", "Fetching error code " + response.code() + "");
                        Log.i("Safe", "Response is " + response.errorBody().string());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }


                }
            }

            @Override
            public void onFailure(Call<SwagResponseModel<List<ConfirmedAppointmentContentModel>>> call, Throwable t) {

                if (appointmentsRefresh.isRefreshing())
                {
                    appointmentsRefresh.setRefreshing(false);
                }

                Toast.makeText(context, "Network Error, please try again", Toast.LENGTH_SHORT).show();
                Log.i("Safe", "Fetching error: " + t.getMessage() + "");
            }
        });
    }

//    public void waitUntilSMSReceived(){
//
//        //60 seconds time out
//         new CountDownTimer(60000,1000) {
//            @Override
//            public void onTick(long l) {
//
//            }
//
//            @Override
//            public void onFinish()
//            {
//                fab.setText("SMS Verification failed");
//                Snackbar.make(getWindow().getDecorView().getRootView(), "Did not receive SMS after waiting for 2mins. Please resend", Snackbar.LENGTH_LONG).show();
//
//            }
//        }.start();


    }



