package com.softedge.care_assist.activities;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;
import com.softedge.care_assist.models.fireModels.PatientPackage.Biography;
import com.softedge.care_assist.R;
import com.softedge.care_assist.utilities.common_code;
import com.softedge.care_assist.databases.SafeDB;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener {

    final long COUNTDOWN_TIME = 30000; // 30 seconds
    final long SECS = 1000;

    ConstraintLayout const_account_layout, const_acc_verify;
    WeakReference<AccountActivity> weakAccount;
    TextView tv_acc_downtime, tv_acc_verify;
    Button bt_acc_change_number, bt_acc_change_email, bt_acc_change_pass, bt_acc_update;
    ProgressBar probar_acc_update;
    TextInputLayout input_acc_email, input_acc_pass, input_acc_confpass;
    TextInputEditText et_acc_email, et_acc_mobNumb, et_acc_pass, et_acc_confpass,
            et_acc_code_1, et_acc_code_2, et_acc_code_3, et_acc_code_4, et_acc_code_5, et_acc_code_6;
    TextInputEditText[] code_acc_Array;
    TableRow tbr_acc_number, tbr_acc_email;
    CountryCodePicker ccd_acc_picker;
    CountDownTimer countDownTimer;
    String verification_id;
    Biography appUser_bio;
    SafeDB safe_db;

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
                    Toast.makeText(getApplicationContext(), "Verification failed with error: "
                            + e.getMessage(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);

                    //storing the verification id that is sent to the user
                    verification_id = s;
                    //resendingToken = forceResendingToken;
                }

            };
    //============================================ON CREATE=========================================

    //TODO show verification code view

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=DEFINED METHODS=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    //============================================ON CREATE=========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        weakAccount = new WeakReference<>(this);
        safe_db = new SafeDB(weakAccount.get(), null);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            appUser_bio = safe_db.local_appUser(FirebaseAuth.getInstance().getCurrentUser().getUid());
        }


        //--------------------------------------HOME BUTTON ON APP BAR------------------------------
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //--------------------------------------HOME BUTTON ON APP BAR------------------------------

        const_account_layout = findViewById(R.id.const_account_layout);
        const_acc_verify = findViewById(R.id.const_acc_verify);
        tv_acc_downtime = findViewById(R.id.tv_acc_dwntime);
        tv_acc_verify = findViewById(R.id.tv_acc_verify);


        bt_acc_change_number = findViewById(R.id.bt_acc_change_number);
        bt_acc_change_email = findViewById(R.id.bt_acc_change_email);
        bt_acc_change_pass = findViewById(R.id.bt_acc_change_pass);
        bt_acc_update = findViewById(R.id.bt_acc_update);

        probar_acc_update = findViewById(R.id.probar_acc_update);

        input_acc_email = findViewById(R.id.input_acc_email);
        input_acc_pass = findViewById(R.id.input_acc_newpass);
        input_acc_confpass = findViewById(R.id.input_acc_confpass);

        et_acc_mobNumb = findViewById(R.id.et_acc_mobile_number);
        et_acc_email = findViewById(R.id.et_acc_email);
        et_acc_pass = findViewById(R.id.et_acc_newpass);
        et_acc_confpass = findViewById(R.id.et_acc_confpass);

        et_acc_code_1 = findViewById(R.id.et_acc_code_1);
        et_acc_code_2 = findViewById(R.id.et_acc_code_2);
        et_acc_code_3 = findViewById(R.id.et_acc_code_3);
        et_acc_code_4 = findViewById(R.id.et_acc_code_4);
        et_acc_code_5 = findViewById(R.id.et_acc_code_5);
        et_acc_code_6 = findViewById(R.id.et_acc_code_6);

        code_acc_Array = new TextInputEditText[]
                {et_acc_code_1, et_acc_code_2, et_acc_code_3, et_acc_code_4, et_acc_code_5, et_acc_code_6};

        tbr_acc_number = findViewById(R.id.tbr_acc_number);
        tbr_acc_email = findViewById(R.id.tbr_acc_email);
        ccd_acc_picker = findViewById(R.id.hbb_acc_picker);

        et_acc_pass.setOnFocusChangeListener((v, hasFocus) -> input_acc_pass.setPasswordVisibilityToggleEnabled(hasFocus));

        et_acc_confpass.setOnFocusChangeListener((v, hasFocus) -> input_acc_confpass.setPasswordVisibilityToggleEnabled(hasFocus));

        //--------------------------------------------COUNTDOWN TIMER-------------------------------
        countDownTimer = new CountDownTimer(COUNTDOWN_TIME, SECS) {

            @Override
            public void onTick(long millisUntilFinished) {

                String remaining_time;
                int timeleft = (int) (millisUntilFinished / SECS);

                if (timeleft < 10) {
                    remaining_time = getResources().getString(R.string.resend_code_in) + "0"
                            + String.valueOf((millisUntilFinished / SECS));
                } else {
                    remaining_time = getResources().getString(R.string.resend_code_in)
                            + String.valueOf((millisUntilFinished / SECS));
                }

                tv_acc_downtime.setText(remaining_time);
            }

            @Override
            public void onFinish() {
                common_code.Mysnackbar(const_account_layout, "Verification timed out, Please try again later",
                        Snackbar.LENGTH_LONG).show();
                hideViews(new View[]{const_acc_verify});
            }
        };
        //--------------------------------------------COUNTDOWN TIMER-------------------------------

        bt_acc_change_pass.setOnClickListener(this);
        bt_acc_change_number.setOnClickListener(this);
        bt_acc_change_email.setOnClickListener(this);
        bt_acc_update.setOnClickListener(this);
    }

    void hideViews(View[] views) {
        for (View v : views) {
            v.setVisibility(View.GONE);
        }
    }

    void showViews(View[] views) {
        for (View v : views) {
            v.setVisibility(View.VISIBLE);
        }
    }

    //---------------------------------------------------------------NUMBER CHANGE PROCESS----------
    void checkNumber() {

        String countrycode = ccd_acc_picker.getSelectedCountryCode();

        if (!et_acc_mobNumb.getText().toString().isEmpty()) {

            try {
                int mob_numb = Integer.parseInt(et_acc_mobNumb.getText().toString());

                if (String.valueOf(mob_numb).length() != 9) {
                    common_code.Mysnackbar(const_account_layout,
                            "Enter valid mobile number", Snackbar.LENGTH_LONG).show();
                    et_acc_mobNumb.requestFocus();

                } else {

                    String usernumber = "+" + countrycode + String.valueOf(mob_numb);
                    send_Code_Method(usernumber);
                }

            } catch (Exception mobilenumb_error) {
                common_code.Mysnackbar(const_account_layout,
                        "Enter valid mobile number", Snackbar.LENGTH_LONG).show();
                et_acc_mobNumb.requestFocus();
            }
        } else {
            hideViews(new View[]{tbr_acc_number, probar_acc_update});
        }
    }

    void start_timer() {
        countDownTimer.start();
    }

    void stop_timer() {
        countDownTimer.cancel();
    }

    void codeValues_into_views(String code) {

        char[] codes_arr = code.toCharArray();

        if (codes_arr.length == code_acc_Array.length) {

            for (int x = 0; x < codes_arr.length; x++) {
                code_acc_Array[x].setText(String.valueOf(codes_arr[x]));
            }
        }

    }

    //send verification code
    void send_Code_Method(String mobile_number) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mobile_number,
                COUNTDOWN_TIME,
                TimeUnit.MILLISECONDS,
                weakAccount.get(),
                verificationCallbacks);

        start_timer();

    }

    //verify code
    private void verifyVerificationCode(String code) {

        //show progress bar
        probar_acc_update.setVisibility(View.VISIBLE);
        probar_acc_update.animate();

        try {
            //creating the credential
            final PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verification_id, code);

            //change phone number
            try {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {

                    FirebaseAuth.getInstance().getCurrentUser().updatePhoneNumber(credential)
                            .addOnCompleteListener(task -> {

                                if (task.isSuccessful()) {

                                    appUser_bio.setMobile_number(String.valueOf(
                                            Integer.parseInt(et_acc_mobNumb.getText().toString())
                                    ));

                                    //update details online and locally
                                    save_Online(appUser_bio);
                                    safe_db.updatePat_bio(appUser_bio);

                                    common_code.Mysnackbar(const_account_layout, "Phone Number Changed Successfully",
                                            Snackbar.LENGTH_LONG).show();
                                    hideViews(new View[]{probar_acc_update, bt_acc_update, tbr_acc_number});

                                } else {
                                    common_code.Mysnackbar(const_account_layout, "Phone Number update failed, Please try again later",
                                            Snackbar.LENGTH_LONG).show();
                                    hideViews(new View[]{probar_acc_update});
                                }
                            });
                }

            } catch (Exception ignored) {
                common_code.Mysnackbar(const_account_layout, "Phone Number update failed, Please try again later",
                        Snackbar.LENGTH_LONG).show();

                hideViews(new View[]{probar_acc_update});
            }

        } catch (Exception e) {
            probar_acc_update.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }
    //---------------------------------------------------------------NUMBER CHANGE PROCESS----------

    void checkEmail() {

        if (!et_acc_email.getText().toString().isEmpty() &&
                et_acc_email.getText().toString().contains("@") &&
                et_acc_email.getText().toString().contains(".")) {

            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                FirebaseAuth.getInstance().getCurrentUser().updateEmail(et_acc_email.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {

                                appUser_bio.setEmail(et_acc_email.getText().toString());

                                //update details online and locally
                                save_Online(appUser_bio);
                                safe_db.updatePat_bio(appUser_bio);

                                common_code.Mysnackbar(const_account_layout,
                                        "Email Update Successful", Snackbar.LENGTH_LONG).show();
                                hideViews(new View[]{tbr_acc_email, probar_acc_update, bt_acc_update});

                            } else {

                                common_code.Mysnackbar(const_account_layout,
                                        "Email Update Failed, please try again", Snackbar.LENGTH_LONG).show();
                                hideViews(new View[]{probar_acc_update});
                            }
                        });
            }

        } else {

            common_code.Mysnackbar(const_account_layout,
                    "Enter valid Email address", Snackbar.LENGTH_LONG).show();
            et_acc_email.requestFocus();
            hideViews(new View[]{probar_acc_update});
        }
    }

    void checkPassword() {

        if (et_acc_pass.getText().toString().isEmpty()) {
            common_code.Mysnackbar(const_account_layout,
                    "Enter Password", Snackbar.LENGTH_LONG).show();

        } else if (et_acc_pass.getText().toString().length() < 6) {
            common_code.Mysnackbar(const_account_layout,
                    "Enter Minimum of six characters", Snackbar.LENGTH_LONG).show();

        } else if (!et_acc_confpass.getText().toString().equals(et_acc_pass.getText().toString())) {
            common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                    "Passwords do not match", Snackbar.LENGTH_LONG).show();
        } else {

            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                FirebaseAuth.getInstance().getCurrentUser().updatePassword(et_acc_confpass.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {

                                common_code.Mysnackbar(const_account_layout,
                                        "Password Update Successful", Snackbar.LENGTH_LONG).show();
                                hideViews(new View[]{input_acc_pass, input_acc_confpass, bt_acc_update, probar_acc_update});

                            } else {

                                common_code.Mysnackbar(const_account_layout,
                                        " Password Update Failed, please try again later",
                                        Snackbar.LENGTH_LONG).show();
                                hideViews(new View[]{probar_acc_update});
                            }
                        });
            }

        }

    }

    void update_modules() {

        if (tbr_acc_number.getVisibility() == View.VISIBLE) {
            checkNumber();
        }

        if (tbr_acc_email.getVisibility() == View.VISIBLE) {
            checkEmail();
        }

        if (input_acc_pass.getVisibility() == View.VISIBLE) {
            checkPassword();
        }

    }

    //--------------------------------------SAVE TO ONLINE DB---------------------------------------
    void save_Online(Biography fireBio) {

        DatabaseReference records_ref = FirebaseDatabase.getInstance().getReference(getResources().getString(R.string.records_ref));
        DatabaseReference all_users_ref = FirebaseDatabase.getInstance().getReference(getResources().getString(R.string.all_users));

        String cell_number = "0" + String.valueOf(fireBio.getMobile_number());
        records_ref.child(cell_number).child("email").setValue(fireBio.getEmail());

        //save user details to All_Users/Biography/Uid
        all_users_ref.child(Biography.TABLE).child(fireBio.getFirebase_Uid()).setValue(fireBio);
    }
    //--------------------------------------SAVE TO ONLINE DB---------------------------------------

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=DEFINED METHODS=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    //----------------------------------------OVERRIDE METHODS--------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (FirebaseAuth.getInstance().getCurrentUser() != null){

            String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

            if (email != null && !email.contains(getResources().getString(R.string.default_email_suffix))){
                et_acc_email.setText(email);
            }

            if (FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()){
                et_acc_email.setCompoundDrawablesWithIntrinsicBounds(null,null,
                        getResources().getDrawable(R.drawable.ic_check_circle_green_24dp),null);

            }else {
                et_acc_email.setCompoundDrawablesWithIntrinsicBounds(null,null,
                        getResources().getDrawable(R.drawable.ic_cancel_red_24dp),null);
                tv_acc_verify.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        super.onBackPressed();
    }
    //----------------------------------------OVERRIDE METHODS--------------------------------------

    //---------------------------------------BUTTON CLICK LISTENER----------------------------------
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bt_acc_change_number:
                showViews(new View[]{tbr_acc_number, bt_acc_update});
                break;

            case R.id.bt_acc_change_email:
                showViews(new View[]{bt_acc_update, tbr_acc_email});
                break;

            case R.id.bt_acc_change_pass:
                showViews(new View[]{input_acc_pass, input_acc_confpass, bt_acc_update});
                break;

            case R.id.bt_acc_update:
                probar_acc_update.setVisibility(View.VISIBLE);
                probar_acc_update.animate();
                update_modules();
                break;
        }
    }
    //---------------------------------------BUTTON CLICK LISTENER----------------------------------

}
