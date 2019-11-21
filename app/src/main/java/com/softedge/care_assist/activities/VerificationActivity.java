package com.softedge.care_assist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.softedge.care_assist.api.CarewexCalls;
import com.softedge.care_assist.models.fireModels.PatientPackage.Biography;
import com.softedge.care_assist.R;
import com.softedge.care_assist.utilities.common_code;
import com.softedge.care_assist.databases.SafeDB;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class VerificationActivity extends AppCompatActivity {

    //verify number -> link credentials -> create carewex account -> login user -> save online

    final long COUNTDOWN_TIME = 60000; // 60 seconds
    final long SECS = 1000;
    TextInputEditText et_code_1,et_code_2,et_code_3,et_code_4,et_code_5,et_code_6;
    CountDownTimer countDownTimer;

    PhoneAuthCredential credential;

    TextInputEditText[] code_input_Array;

    WeakReference<VerificationActivity> weakverification;

    TextView tv_verify_downtime, tv_verify_resend, tv_verify_status;
    ProgressBar probar_verify_code;
    Button bt_verify_code;

    String verification_id, patient_ID = null, serialised_carewex_ID = null;
    PhoneAuthProvider.ForceResendingToken resendingToken;
    Bundle registration_bundle;

    FirebaseUser email_fire_user;

    //============================================ON CREATE=========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        weakverification = new WeakReference<>(this);
        registration_bundle = getIntent().getExtras();

        tv_verify_downtime = findViewById(R.id.tv_verify_dwntime);
        tv_verify_resend = findViewById(R.id.tv_verify_resend);
        tv_verify_status = findViewById(R.id.tv_verify_Status);

        et_code_1 = findViewById(R.id.et_code_1);
        et_code_2 = findViewById(R.id.et_code_2);
        et_code_3 = findViewById(R.id.et_code_3);
        et_code_4 = findViewById(R.id.et_code_4);
        et_code_5 = findViewById(R.id.et_code_5);
        et_code_6 = findViewById(R.id.et_code_6);

        probar_verify_code = findViewById(R.id.probar_verify_code);
        bt_verify_code = findViewById(R.id.bt_verifycode);

        code_input_Array = new TextInputEditText[]{et_code_1,et_code_2,et_code_3,et_code_4,et_code_5,et_code_6};

        //--------------------------------------------COUNTDOWN TIMER-------------------------------
        countDownTimer = new CountDownTimer(COUNTDOWN_TIME, SECS) {

            @Override
            public void onTick(long millisUntilFinished) {

                String remaining_time;
                int timeleft = (int) (millisUntilFinished/SECS);

                if (timeleft < 10){
                    remaining_time = getResources().getString(R.string.resend_code_in) + "0"
                            + millisUntilFinished / SECS;
                }else {
                   remaining_time = getResources().getString(R.string.resend_code_in)
                            + millisUntilFinished / SECS;
                }

                tv_verify_downtime.setText(remaining_time);
            }

            @Override
            public void onFinish() {
                tv_verify_resend.setEnabled(true);
                tv_verify_downtime.setVisibility(View.GONE);
            }
        };
        //--------------------------------------------COUNTDOWN TIMER-------------------------------

        //verify number -> link credentials -> create carewex account -> login user -> save online
        if (registration_bundle != null) {
            String mobile_number = registration_bundle.getString(Biography.MOBILE_NUMBER);
            String country_code = registration_bundle.getString(Biography.COUNTRY_CODE);
            patient_ID = registration_bundle.getString(Biography.OPD_ID);
            serialised_carewex_ID = registration_bundle.getString(Biography.ID);
            String usernumber = "+" + country_code + mobile_number;

            //carewex_patRegID(common_code.patientFromBundle(registration_bundle));
            send_Code_Method(usernumber);

            //fakeverify(usernumber,testcode);
            //Toast.makeText(getApplicationContext(),patient_ID + ", serial: " + serialised_carewex_ID,Toast.LENGTH_LONG).show();
        }//else {
         //   Toast.makeText(getApplicationContext(),"bundle is empty",Toast.LENGTH_LONG).show();
        //}

        editorSwitcher();

    }
    //============================================ON CREATE=========================================

    @Override
    protected void onResume() {
        super.onResume();
        CarewexCalls.get_access_token(weakverification.get());
    }

    //--------------------------------------------METHODS-------------------------------------------

    void start_timer(){
        tv_verify_downtime.setVisibility(View.VISIBLE);
        tv_verify_resend.setEnabled(false);
        countDownTimer.start();
    }

    void stop_timer(){
        tv_verify_downtime.setVisibility(View.GONE);
        countDownTimer.cancel();
    }

    void editorSwitcher(){

        for (final TextInputEditText et_input : code_input_Array){
            et_input.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    switch (et_input.getId()){
                        case R.id.et_code_1:
                            et_code_2.requestFocus();
                            break;
                        case R.id.et_code_2:
                            et_code_3.requestFocus();
                            break;
                        case R.id.et_code_3:
                            et_code_4.requestFocus();
                            break;
                        case R.id.et_code_4:
                            et_code_5.requestFocus();
                            break;
                        case R.id.et_code_5:
                            et_code_6.requestFocus();
                            break;
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    //create user email account

    //--------------------------------------CREATE ACCOUNT------------------------------------------
    //verify number -> link credentials -> create carewex account -> login user -> save online
     void create_firebase_account(){

        final String email = registration_bundle.getString(Biography.EMAIL,null);
        String password = registration_bundle.getString(Biography.PASSWORD,null);

        //show status text
        tv_verify_status.setText(getResources().getString(R.string.creating_account));
        //tv_verify_status.setText(email);

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(weakverification.get(), task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        //Log.d(TAG, "createUserWithEmail:success");
                        email_fire_user = FirebaseAuth.getInstance().getCurrentUser();
                        if (email_fire_user != null) {
                            try {

                                //link credentials ------------------------- 2
                                email_fire_user.linkWithCredential(credential).addOnCompleteListener(weakverification.get(),
                                        task1 -> {
                                    // if successful register on carewex
                                            if (task1.isSuccessful()) {
                                                carewex_patRegID(common_code.patientFromBundle(registration_bundle));
                                                //save_Online(email_fire_user.getUid(), patid);
                                            }
                                        });
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), " Unable to save online." + e.toString(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                        Toast.makeText(getApplicationContext(), "Account Creation Successful.",
                                Toast.LENGTH_LONG).show();

                    } else {
                        // If sign in fails, display a message to the user.
                        //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        tv_verify_status.setText("");
                        Toast.makeText(getApplicationContext(), "Account Creation failed: " + task.getException(),
                                Toast.LENGTH_LONG).show();
                        probar_verify_code.clearAnimation();
                        probar_verify_code.setVisibility(View.INVISIBLE);
                        //updateUI(null);
                    }

                });
    }
    //--------------------------------------CREATE ACCOUNT------------------------------------------

    //--------------------------------------SAVE TO ONLINE DB---------------------------------------
    public void save_Online(String firebase_id, String patID){

        DatabaseReference records_ref = FirebaseDatabase.getInstance().getReference(getResources().getString(R.string.records_ref));
        DatabaseReference all_users_ref = FirebaseDatabase.getInstance().getReference(getResources().getString(R.string.all_users));

        Biography firebase_biography = common_code.patientFromBundle(registration_bundle);
        firebase_biography.setFirebase_Uid(firebase_id);
        firebase_biography.setOpd_Id(patID);
        firebase_biography.setPropic_url("");
        String cell_number = "0"+ firebase_biography.getMobile_number();
        records_ref.child(cell_number).child("email").setValue(firebase_biography.getEmail());

        //save user details to All_Users/Biography/Uid
        all_users_ref.child(Biography.TABLE)
                .child(firebase_id).setValue(firebase_biography);

        //login_with_email();
        //Load user details online
        if (FirebaseAuth.getInstance().getCurrentUser() != null){
        loadBioData_online(FirebaseAuth.getInstance().getCurrentUser().getUid());
        }
    }
    //--------------------------------------SAVE TO ONLINE DB---------------------------------------

    //verify number -> link credentials -> create carewex account -> login user -> save online
    //Register user on carewex and return OPD number
    void carewex_patRegID(Biography basicUser){

        basicUser.setId(serialised_carewex_ID);
        basicUser.setOpd_Id(patient_ID);

        // register patient on carewex
        CarewexCalls.register_patient(common_code.carewex_pat(basicUser,weakverification.get()),weakverification.get());
    }

    void loadBioData_online(String fireID){

        String all_users = getResources().getString(R.string.all_users);

        final SafeDB safe_db = new SafeDB(weakverification.get(),null);

        DatabaseReference bio_ref = FirebaseDatabase.getInstance().getReference(all_users)
                .child(Biography.TABLE);

        bio_ref.child(fireID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Biography userBio = dataSnapshot.getValue(Biography.class);

                if (userBio != null){
                    try {
                        safe_db.addPat_bio(userBio);
                        toDashboard();
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),"Error loading online data "
                                +e.toString(),Toast.LENGTH_LONG).show();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    //send verification code ------ 1
    void send_Code_Method(String mobile_number){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mobile_number,
                COUNTDOWN_TIME,
                TimeUnit.MILLISECONDS,
                weakverification.get(),
                verificationCallbacks);
        start_timer();
    }

    //resend verification code
    void resend_Code_Method(String mobile_number, PhoneAuthProvider.ForceResendingToken token){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mobile_number,
                COUNTDOWN_TIME,
                TimeUnit.MILLISECONDS,
                weakverification.get(),
                verificationCallbacks,
                token);
        start_timer();
    }

    //the callback to detect the verification status
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                String code;

                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                    //Getting the code sent by SMS
                    code = phoneAuthCredential.getSmsCode();

                    //sometimes the code is not detected automatically
                    //in this case the code will be null
                    //so user has to manually enter the code
                    if (code != null) {

                        codeValues_into_views(code);

                        stop_timer();
                        //verify the code ------ 1
                        verifyVerificationCode(code);
                    }
                }

                @Override
                public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                    super.onCodeAutoRetrievalTimeOut(s);
                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    probar_verify_code.setVisibility(View.GONE);
                    tv_verify_status.setText("");
                    Toast.makeText(getApplicationContext(),"Verification failed with error: "
                            + e.getMessage(), Toast.LENGTH_LONG).show();
                }


                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);

                    //storing the verification id that is sent to the user
                    verification_id = s;
                    resendingToken = forceResendingToken;
                }

            };

    //FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    //FirebaseAuthSettings firebaseAuthSettings = firebaseAuth.getFirebaseAuthSettings();

    /*void fakeverify(String phoneNumber, String smsCode){

// Configure faking the auto-retrieval with the whitelisted numbers.
        firebaseAuthSettings.setAutoRetrievedSmsCodeForPhoneNumber(phoneNumber, smsCode);

        PhoneAuthProvider phoneAuthProvider = PhoneAuthProvider.getInstance();
        phoneAuthProvider.verifyPhoneNumber(
                phoneNumber,
                COUNTDOWN_TIME,
                TimeUnit.MILLISECONDS,
                this, // activity
                verificationCallbacks
                );
    }*/

    //verify code -------------------------------- 1
    private void verifyVerificationCode(String code) {

        //show progress bar
        probar_verify_code.setVisibility(View.VISIBLE);
        probar_verify_code.animate();

        //show status text
        tv_verify_status.setText(getResources().getString(R.string.verifying));
        tv_verify_status.setVisibility(View.VISIBLE);

        try {
            //creating the credential
            credential = PhoneAuthProvider.getCredential(verification_id, code);

            //link credentials step 2 0f 4
            create_firebase_account();
            // TODO carewex_patRegID(common_code.patientFromBundle(registration_bundle));

        }catch (Exception e){
            probar_verify_code.setVisibility(View.GONE);
            tv_verify_status.setText("");
            Toast.makeText(getApplicationContext(),"Verification code invalid: " + verification_id,Toast.LENGTH_LONG).show();
        }


    }

    //sign in
    public void login_with_email(String patient_ID){

        //show status text
        tv_verify_status.setText(getResources().getString(R.string.logging_in));

        String email = registration_bundle.getString(Biography.EMAIL);
        String password = registration_bundle.getString(Biography.PASSWORD);

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if (email != null && password != null) {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(weakverification.get(), task -> {
                        if (task.isSuccessful()) {

                            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                                save_Online(FirebaseAuth.getInstance().getCurrentUser().getUid(),patient_ID);
                            }
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithEmail:success");

                        }

                    });
        }
    }

    void codeValues_into_views(String code){

        char[] codes_arr = code.toCharArray();

        if (codes_arr.length == code_input_Array.length){

            for (int x = 0; x < codes_arr.length; x++){
                code_input_Array[x].setText(String.valueOf(codes_arr[x]));
            }
        }

    }

    //--------------------------------------------METHODS-------------------------------------------

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=ONCLICK LISTENERS-=-=-=-=-=-=-=-=-=-=-=-=--==-=-=-=-
    public void VerifyCodeRecieved(View view) {

        StringBuilder codebuilder = new StringBuilder();

        for (TextInputEditText et_code : code_input_Array){
            codebuilder.append(et_code.getText().toString());
        }

        if (!codebuilder.toString().isEmpty() && codebuilder.toString().length() == 6){
            //Toast.makeText(getApplicationContext(),codebuilder.toString(),Toast.LENGTH_LONG).show();
            verifyVerificationCode(codebuilder.toString());
        }else {
            common_code.Mysnackbar(findViewById(R.id.const_verify_parentview),
                    "Enter Verification Code", Snackbar.LENGTH_LONG).show();
        }

    }

    public void ResendCode(View view) {

        if (registration_bundle != null && resendingToken != null) {
            String mobile_number = registration_bundle.getString(Biography.MOBILE_NUMBER);
            String country_code = registration_bundle.getString(Biography.COUNTRY_CODE);
            String usernumber = "+" + country_code + mobile_number;
            resend_Code_Method(usernumber,resendingToken);
        }

    }
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=ONCLICK LISTENERS-=-=-=-=-=-=-=-=-=-=-=-=--==-=-=-=-

    void toDashboard(){
        probar_verify_code.setVisibility(View.GONE);
        Intent dashboard_intent = new Intent(getApplicationContext(),OpdCardActivity.class);
        dashboard_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(dashboard_intent);
        super.finish();
    }

}
