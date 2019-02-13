package com.softedge.safedoktor.activities;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.common_code;
import com.softedge.safedoktor.models.Patient;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class VerificationActivity extends AppCompatActivity {

    final long COUNTDOWN_TIME = 30000; // 30 seconds
    final long SECS = 1000;
    TextInputEditText et_verify_code;
    CountDownTimer countDownTimer;

    WeakReference<VerificationActivity> weakverification;

    TextView tv_verify_downtime, tv_verify_resend;
    ProgressBar probar_verify_code;

    String verification_id;
    PhoneAuthProvider.ForceResendingToken resendingToken;
    Bundle registration_bundle;

    //============================================ON CREATE=========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        weakverification = new WeakReference<>(this);

        et_verify_code = findViewById(R.id.et_verify_code);
        tv_verify_downtime = findViewById(R.id.tv_verify_dwntime);

        tv_verify_resend = findViewById(R.id.tv_verify_resend);
        probar_verify_code = findViewById(R.id.probar_verify_code);

        registration_bundle = getIntent().getExtras();

        //--------------------------------------------COUNTDOWN TIMER-------------------------------
        countDownTimer = new CountDownTimer(COUNTDOWN_TIME, SECS) {

            @Override
            public void onTick(long millisUntilFinished) {

                String remaining_time;
                int timeleft = (int) (millisUntilFinished/SECS);

                if (timeleft < 10){
                    remaining_time = getResources().getString(R.string.resend_code_in) + "0"
                            + String.valueOf((millisUntilFinished/SECS));
                }else {
                   remaining_time = getResources().getString(R.string.resend_code_in)
                            + String.valueOf((millisUntilFinished/SECS));
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

        if (registration_bundle != null) {
            int mobile_number = registration_bundle.getInt(Patient.MOBILE_NUMBER);
            String country_code = registration_bundle.getString(Patient.COUNTRY_CODE);
            String usernumber = "+" + country_code + String.valueOf(mobile_number);
            send_Code_Method(usernumber);
        }

    }
    //============================================ON CREATE=========================================

    //--------------------------------------------METHODS-------------------------------------------

    void start_timer(){
        tv_verify_downtime.setVisibility(View.VISIBLE);
        countDownTimer.start();
    }

    void stop_timer(){
        tv_verify_downtime.setVisibility(View.GONE);
        countDownTimer.cancel();
    }

    //send verification code
    void send_Code_Method(String mobile_number){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mobile_number,
                COUNTDOWN_TIME,
                TimeUnit.MILLISECONDS,
                weakverification.get(),
                verificationCallbacks);
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

                        stop_timer();
                        //verify the code
                        verifyVerificationCode(code);
                    }
                }

                @Override
                public void onCodeAutoRetrievalTimeOut(String s) {
                    super.onCodeAutoRetrievalTimeOut(s);
                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    Toast.makeText(getApplicationContext(),"Verification failed with error: "
                            + e.getMessage(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);

                    //storing the verification id that is sent to the user
                    verification_id = s;
                    resendingToken = forceResendingToken;
                }

            };


    //verify code
    private void verifyVerificationCode(String code) {

        //show progress bar
        probar_verify_code.setVisibility(View.VISIBLE);
        probar_verify_code.animate();

        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verification_id, code);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    //sign in
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //verification successful ,move on to the second step
                            if (mAuth.getCurrentUser() != null){
                                toDashboard();
                            }

                        } else {

                            //verification unsuccessful.. display an error message
                            String message = "Verification Unsuccessful, Please try again...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                                probar_verify_code.setVisibility(View.GONE);
                                probar_verify_code.clearAnimation();
                            }

                            common_code.Mysnackbar(findViewById(R.id.const_verify_parentview),
                                    message,Snackbar.LENGTH_LONG).show();
                        }
                    }
                });

    }

    //--------------------------------------------METHODS-------------------------------------------

    public void VerifyCodeRecieved(View view) {

        if (!et_verify_code.getText().toString().isEmpty()){
            verifyVerificationCode(et_verify_code.getText().toString());
        }else {
            common_code.Mysnackbar(findViewById(R.id.const_verify_parentview),
                    "Enter Verification Code", Snackbar.LENGTH_LONG).show();
        }

        //toDashboard();
    }

    public void ResendCode(View view) {

    }

    void toDashboard(){
        Intent dashboard_intent = new Intent(this,DashboardActivity.class);
        dashboard_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(dashboard_intent);
    }

}
