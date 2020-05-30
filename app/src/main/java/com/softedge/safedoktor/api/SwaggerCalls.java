package com.softedge.safedoktor.api;

import android.content.Context;
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
import com.softedge.safedoktor.databases.appDB;
import com.softedge.safedoktor.models.swaggerModels.BookingsList;
import com.softedge.safedoktor.models.swaggerModels.DataModel;
import com.softedge.safedoktor.models.swaggerModels.PatientModel;
import com.softedge.safedoktor.models.swaggerModels.SwaggerAPI_ResponseArr;
import com.softedge.safedoktor.models.swaggerModels.body.Login;
import com.softedge.safedoktor.models.swaggerModels.body.PhoneNumber;
import com.softedge.safedoktor.models.swaggerModels.body.UserReg;
import com.softedge.safedoktor.models.swaggerModels.body.ValidateCode;
import com.softedge.safedoktor.models.swaggerModels.rSwaggerAPI;
import com.softedge.safedoktor.models.swaggerModels.response.rApptData;
import com.softedge.safedoktor.models.swaggerModels.response.rAppt_Content;
import com.softedge.safedoktor.models.swaggerModels.response.rIsValid;
import com.softedge.safedoktor.models.swaggerModels.response.rLogin;
import com.softedge.safedoktor.models.swaggerModels.response.rToken;
import com.softedge.safedoktor.models.swaggerModels.response.rValidation;
import com.softedge.safedoktor.service.SessionManagement;
import com.softedge.safedoktor.utilities.common_code;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softedge.safedoktor.utilities.AppConstants.KEY_FULL_TOKEN;
import static com.softedge.safedoktor.utilities.AppConstants.KEY_PATIENT_ID;

public class SwaggerCalls {

    static WeakReference<Context> weakContext;
    private static SwaggerClient swagger_Client = SafeDoktorService.createService(SwaggerClient.class);

    //Login user with number and password
    public static void login(View view, Login loginModel){

        weakContext = new WeakReference<>(view.getContext());
        SessionManagement sessionManagement = new SessionManagement(view.getContext());
        appDB app_Db = appDB.getInstance(view.getContext());

        if (weakContext.get() instanceof LoginActivity){
            ((LoginActivity)weakContext.get()).toggleProbar(true);
        }

        // loginProgressDialog.show();
        Call<SwaggerAPI_ResponseArr<List<rLogin>>> call = swagger_Client.patientLogin(loginModel);

        call.enqueue(new Callback<SwaggerAPI_ResponseArr<List<rLogin>>>() {
            @Override
            public void onResponse(@NonNull Call<SwaggerAPI_ResponseArr<List<rLogin>>> call, @NonNull Response<SwaggerAPI_ResponseArr<List<rLogin>>> response) {
                //Log.i("Safe", "we're in");
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

                        for (rLogin pats_Tokens: userlogin){

                            PatientModel patient = pats_Tokens.getPatient();

                            sessionManagement.createLoginSession(pats_Tokens);
                            //sessionManagement.createPatientDetail(patient);

                            //Toast.makeText(weakContext.get(), "Login worked - \n"  + patient.getPhonenumber(), Toast.LENGTH_LONG).show();

                            //Add patient object to room database
                            common_code.getDBExecutor(2).execute(() -> app_Db.safeDoktorAccessObj().addPatient(patient));

//                            Load all appointments
                            loadConfirmedAppointments(view);

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

    //Registration process user details signup
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
            public void onFailure(@NonNull Call<SwaggerAPI_ResponseArr<List<PatientModel>>> call, @NonNull Throwable t) {
                //signUpProgressDialog.dismiss();
                t.printStackTrace();
                Log.i("message", t.toString());
                //Toast.makeText(getApplicationContext(),getResources().getString(R.string.alert_networkissues), Toast.LENGTH_LONG).show();

            }
        });

    }

    public static void loadConfirmedAppointments( View view){
        //appointmentsRefresh.setRefreshing(true);
        //Token type + " " + token string
        WeakReference<Context> weakcontext = new WeakReference<>(view.getContext());
        SharedPreferences appPref = common_code.appPref(weakcontext.get());
        appDB app_Db = appDB.getInstance(weakcontext.get());

        int patientID = appPref.getInt(KEY_PATIENT_ID,0);
        String fulltoken = appPref.getString(KEY_FULL_TOKEN,null);

        Call<rSwaggerAPI<List<rAppt_Content>>> call = swagger_Client.getAllAppointments(fulltoken, patientID);

        call.enqueue(new Callback<rSwaggerAPI<List<rAppt_Content>>>() {
            @Override
            public void onResponse(@NonNull Call<rSwaggerAPI<List<rAppt_Content>>> call,
                                   @NonNull Response<rSwaggerAPI<List<rAppt_Content>>> response) {


                if (response.body() != null && response.body().getData() != null) {
                    List<rAppt_Content> apptData_list = response.body().getData().getContent();

                        //Todo check if details, payments and notifications list contains more than one item
//                    Toast.makeText(view.getContext(),"Appt response: " + apptData_list.size(),Toast.LENGTH_SHORT).show();

                    for (rAppt_Content content : apptData_list){
                        //Log.e("Safe", "\n Booking number: " + content.getAppointment().getBookingnumber());
                        //Log.e("Safe", "\n Details createUserid: " + content.getDetails().get(0).getCreateuserid());

                        int bookingid = content.getAppointment().getBookingid();
                        String bookingDate = content.getAppointment().getBookingdate();

                        BookingsList bookingsList = new BookingsList(bookingid,patientID,bookingDate);

                        common_code.getDBExecutor(1).execute(()->app_Db.safeDoktorAccessObj().addBooking(bookingsList));

                        if (content.getAppointment() != null){
                            common_code.getDBExecutor(1).execute(()->app_Db.safeDoktorAccessObj().addAppointment(content.getAppointment()));
                        }

                        if (content.getDetails() != null && content.getDetails().size() > 0){
                            app_Db.safeDoktorAccessObj().addDetails(content.getDetails().get(0));
                        }

                        if (content.getPayments() != null && content.getPayments().size() > 0){
                            app_Db.safeDoktorAccessObj().addPayments(content.getPayments().get(0));
                        }

                        if (content.getNotifications() != null && content.getNotifications().size() > 0){
                            app_Db.safeDoktorAccessObj().addNotifications(content.getNotifications().get(0));
                        }

//                        Login user after saving appointments and boking details
                        common_code.toDashboard(view.getContext());
                        if (weakcontext.get() instanceof LoginActivity){
                            ((LoginActivity)weakcontext.get()).toggleProbar(false);
                        }
                    }


                }


//                if (response.code() == HttpURLConnection.HTTP_FORBIDDEN || response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
//                    Toast.makeText(context, "Your session has expired please login again", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(context, FormLogin.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
//sd
//                }
//                else if (response.isSuccessful())
//                {
//                    Log.i("Safe", "loadConfirmedAppoints successfully called");
//                    confirmedAppointmentResponse = response.body();
//
//                    if (confirmedAppointmentResponse.getData() == null) {
//                        List<String> noServiceAvailable = new ArrayList<String>();
//                        noServiceAvailable.add("No Appointment Available");
//                        noAppointmentsTv.setVisibility(View.VISIBLE);
//                    }
//                    else
//                    {
//                        appointmentData = confirmedAppointmentResponse.getData();
//                        confirmedAppointmentsList = appointmentData.getContent();
//                        showAppointments(confirmedAppointmentsList,statusid);
//                    }
//                }
//                else
//                {
//                    //appointmentFetchProgressDialog.dismiss();
//
//                    try {
//                        //Toast.makeText(getContext(), "Unable to Fetch Appointment. Please contact Helpline", Toast.LENGTH_SHORT).show();
//                        String error=response.errorBody().string();
//                        tokenerror(error);
//                        Log.i("Safe", "Fetching error code " + response.code() + "");
//                        Log.i("Safe", "Response is " + response.errorBody().string());
//                    }
//                    catch (Exception e)
//                    {
//                        e.printStackTrace();
//                    }
//
//
//                }
            }

            @Override
            public void onFailure(@NonNull Call<rSwaggerAPI<List<rAppt_Content>>> call, @NonNull Throwable t) {

                //Todo refresh appointment list
//                if (appointmentsRefresh.isRefreshing())
//                {
//                    appointmentsRefresh.setRefreshing(false);
//                }

                Toast.makeText(view.getContext(),"Appointment load error:" + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("Safe", "Fetching error: " + t.getMessage() + "");
                //Log.i("Safe", "Fetching error: " + t.getMessage() + "");
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



