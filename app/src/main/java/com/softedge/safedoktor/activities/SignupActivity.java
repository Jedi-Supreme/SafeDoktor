package com.softedge.safedoktor.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;
import com.softedge.safedoktor.api.CarewexCalls;
import com.softedge.safedoktor.models.fireModels.PatientPackage.Biography;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.utilities.common_code;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class SignupActivity extends AppCompatActivity {

    WeakReference<SignupActivity> weak_signup;

    TextInputLayout
            input_reg_password,
            input_reg_confpass;

    TextInputEditText
            et_reg_fn, et_reg_ln,
            et_reg_mobile, et_reg_email,
            et_reg_dob, et_reg_pass,
            et_reg_confpass;

    ProgressBar probar_reg_check;

    CountryCodePicker country_code;
    ConstraintLayout signup_layout;

    DatePickerDialog datePickerDialog;

    Bundle existing_srch_pat;

    Spinner sp_reg_gender, sp_marital_status;

    TextView tv_reg_tos, tv_reg_acc_chk;
    String opd_ID,serialised_carwex_ID = null;

    Biography reg_biography;

    //============================================ON CREATE=========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        weak_signup = new WeakReference<>(SignupActivity.this);

        existing_srch_pat = getIntent().getExtras();

        et_reg_fn = findViewById(R.id.et_reg_fn);
        et_reg_ln = findViewById(R.id.et_reg_ln);
        et_reg_mobile = findViewById(R.id.et_reg_mobile_number);
        et_reg_email = findViewById(R.id.et_reg_email);
        et_reg_dob = findViewById(R.id.et_reg_dob);
        et_reg_pass = findViewById(R.id.et_reg_password);
        et_reg_confpass = findViewById(R.id.et_reg_conf_pass);

        probar_reg_check = findViewById(R.id.probar_reg_check);
        tv_reg_acc_chk = findViewById(R.id.tv_reg_acc_chk);

        tv_reg_tos = findViewById(R.id.tv_reg_tos);

        sp_reg_gender = findViewById(R.id.sp_reg_gender);
        sp_marital_status = findViewById(R.id.sp_reg_marital_State);

        signup_layout = findViewById(R.id.reg_const_layout);

        input_reg_password = findViewById(R.id.input_reg_password);
        input_reg_confpass = findViewById(R.id.input_reg_conf_pass);

        country_code = findViewById(R.id.hbb_picker);

        tv_reg_tos.setOnClickListener(v -> common_code.toTOS(weak_signup.get()));

        et_reg_dob.setOnClickListener(v -> user_pick_date());

        et_reg_pass.setOnFocusChangeListener((v, hasFocus) -> input_reg_password.setPasswordVisibilityToggleEnabled(hasFocus));

        et_reg_confpass.setOnFocusChangeListener((v, hasFocus) -> input_reg_confpass.setPasswordVisibilityToggleEnabled(hasFocus));

        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(weak_signup.get(), R.style.DatePickerTheme, dateSetListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        if (existing_srch_pat != null){
            loadExistingSrchData(existing_srch_pat);
        }


    }
    //============================================ON CREATE=========================================

    @Override
    protected void onResume() {
        super.onResume();

        if (common_code.isInternetConnected(weak_signup.get())){
            CarewexCalls.get_access_token(weak_signup.get());
        }else {
            common_code.connection_toast(getApplicationContext());
        }
    }
    //--------------------------------------DEFINED METHODS-----------------------------------------

    void test_userinputs(){

        if (et_reg_fn.getText().toString().isEmpty()){
            common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                    "Please Enter First name", Snackbar.LENGTH_LONG).show();
            et_reg_fn.requestFocus();

        }else if(et_reg_ln.getText().toString().isEmpty()){
            common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                    "Please Enter Last name", Snackbar.LENGTH_LONG).show();
            et_reg_ln.requestFocus();

        }else if (et_reg_mobile.getText().toString().isEmpty()){
            common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                    "Enter mobile number", Snackbar.LENGTH_LONG).show();
            et_reg_mobile.requestFocus();

        }else if (et_reg_dob.getText().toString().isEmpty()){
            common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                    "Pick date of birth (DD/MMM/YYYY)", Snackbar.LENGTH_LONG).show();

        }else if (sp_reg_gender.getSelectedItemPosition() <= 0){
            common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                    "Please Select Gender", Snackbar.LENGTH_LONG).show();

        }else if (sp_marital_status.getSelectedItemPosition() <= 0){
            common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                    "Please Select Marital Status", Snackbar.LENGTH_LONG).show();

        }else if (et_reg_pass.getText().toString().isEmpty()){
            common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                    "Enter Password", Snackbar.LENGTH_LONG).show();
            et_reg_pass.requestFocus();

        }else if (et_reg_pass.getText().toString().length() < 6){
            common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                    "Enter Minimum of six characters", Snackbar.LENGTH_LONG).show();
            et_reg_pass.requestFocus();

        }else if (!et_reg_confpass.getText().toString().equals(et_reg_pass.getText().toString())){
            common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                    "Passwords do not match", Snackbar.LENGTH_LONG).show();
            et_reg_confpass.requestFocus();

        }else {

            try {

                int mob_numb = 0;

                //if mobile number does not contain asterisk, pick from text input else pick from intent bundle
                if (!et_reg_mobile.getText().toString().contains("*")){
                    mob_numb = Integer.parseInt(et_reg_mobile.getText().toString());
                }else {
                    mob_numb = Integer.parseInt(Objects.requireNonNull(existing_srch_pat.getString(Biography.MOBILE_NUMBER)));
                }

                if (String.valueOf(mob_numb).length() != 9){
                    common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                            "Enter valid mobile number", Snackbar.LENGTH_LONG).show();
                    et_reg_mobile.requestFocus();

                }else {

                    reg_biography = new Biography(
                            et_reg_fn.getText().toString(),
                            et_reg_ln.getText().toString(),
                            sp_reg_gender.getSelectedItemPosition(),
                            country_code.getSelectedCountryCode(),
                            String.valueOf(mob_numb),
                            et_reg_dob.getText().toString(),
                            sp_marital_status.getSelectedItemPosition()
                    );

                    //pass_dataTo_verification();
                    String mobile = "0"+ mob_numb;
                    probar_reg_check.setVisibility(View.VISIBLE);
                    tv_reg_acc_chk.setVisibility(View.VISIBLE);
                    common_code.emailAvailability_fetch(mobile,weak_signup.get());
                }

            }catch (Exception mobilenumb_error){
                common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                        "Enter valid mobile number", Snackbar.LENGTH_LONG).show();
                et_reg_mobile.requestFocus();
            }
        }
    }

    void loadExistingSrchData(Bundle existing_pat){

                et_reg_fn.setText(existing_pat.getString(Biography.FIRSTNAME));
                et_reg_ln.setText(existing_pat.getString(Biography.LASTNAME));
                sp_reg_gender.setSelection(existing_pat.getInt(Biography.GENDER));

                et_reg_mobile.setText(common_code.hidden_number(existing_pat.getString(Biography.MOBILE_NUMBER)));
                et_reg_email.setText(existing_pat.getString(Biography.EMAIL));
                et_reg_dob.setText(existing_pat.getString(Biography.DATE_OF_BIRTH));

                sp_marital_status.setSelection(existing_pat.getInt(Biography.MARITAL_STATUS));

                opd_ID = existing_pat.getString(Biography.OPD_ID);
                serialised_carwex_ID = existing_pat.getString(Biography.ID);
    }

    public void pass_dataTo_verification(){

        hideProbar();
        Intent verification_intent = new Intent(getApplicationContext(), VerificationActivity.class);

        if (!et_reg_email.getText().toString().isEmpty() &&
                et_reg_email.getText().toString().contains("@") &&
                et_reg_email.getText().toString().contains(".")){
            verification_intent.putExtra(Biography.EMAIL, et_reg_email.getText().toString());
        }else {
            //create default email if patient does not enter valid email
            String default_email = "0"+ reg_biography.getMobile_number()
                    +getResources().getString(R.string.default_email_suffix);
            verification_intent.putExtra(Biography.EMAIL, default_email);
        }

        if (opd_ID != null){
            verification_intent.putExtra(Biography.OPD_ID, opd_ID);
        }

        if (serialised_carwex_ID != null){
            verification_intent.putExtra(Biography.ID,serialised_carwex_ID);
        }

        verification_intent.putExtra(Biography.FIRSTNAME, reg_biography.getFirstname());
        verification_intent.putExtra(Biography.LASTNAME, reg_biography.getLastname());
        verification_intent.putExtra(Biography.GENDER, reg_biography.getGender());
        verification_intent.putExtra(Biography.PASSWORD,et_reg_confpass.getText().toString());

        verification_intent.putExtra(Biography.MOBILE_NUMBER, reg_biography.getMobile_number());
        verification_intent.putExtra(Biography.COUNTRY_CODE, reg_biography.getCountry_code());
        verification_intent.putExtra(Biography.DATE_OF_BIRTH, reg_biography.getDate_of_birth());
        verification_intent.putExtra(Biography.MARITAL_STATUS,reg_biography.getMarital_state());

        startActivity(verification_intent);
    }

    //-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^_^-^-^-^-^-^-^-DATE-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-
    DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> showDate(year, month, dayOfMonth);

    void user_pick_date(){
        datePickerDialog.show();
    }

    public void showDate(int year, int month, int day) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(common_code.regDateformat, Locale.getDefault());
        SimpleDateFormat parseDateFormat = new SimpleDateFormat(common_code.regDateformat, Locale.getDefault());
        String userdate;
        Date date;

        if (month+1<10){
            userdate =  day + "-" + "0" + (month + 1) + "-" + year;
            //et_reg_dob.setText(userdate);
            try {
                date = parseDateFormat.parse(userdate);
                et_reg_dob.setText(simpleDateFormat.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }else{
            userdate = day + "-" + (month + 1) + "-" + year;
            //et_reg_dob.setText(userdate);
            try {
                date = parseDateFormat.parse(userdate);
                et_reg_dob.setText(simpleDateFormat.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    //-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^_^-^-^-^-^-^-^-DATE-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-

    public void number_verification(){

        if (!common_code.isInternetConnected(weak_signup.get())){
            common_code.connection_toast(getApplicationContext());
        }else {
            test_userinputs();
        }

    }

    public void hideProbar(){
        tv_reg_acc_chk.setVisibility(View.GONE);
        probar_reg_check.setVisibility(View.GONE);
    }

    //--------------------------------------DEFINED METHODS-----------------------------------------

    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=ONCLICK LISTENER-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=
    public void toVerification(View view) {

        //Toast.makeText(getApplicationContext(), opd_ID, Toast.LENGTH_SHORT).show();
        number_verification();
    }
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=ONCLICK LISTENER-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=
}
