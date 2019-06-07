package com.softedge.care_assist.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;
import com.softedge.care_assist.api.CarewexCalls;
import com.softedge.care_assist.models.fireModels.PatientPackage.Biography;
import com.softedge.care_assist.R;
import com.softedge.care_assist.utilities.common_code;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SignupActivity extends AppCompatActivity {

    WeakReference<SignupActivity> weak_signup;

    TextInputLayout
            input_reg_password,
            input_reg_confpass;

    TextInputEditText
            et_reg_fn, et_reg_ln,
            et_reg_mobile, et_reg_email,
            et_reg_dob, et_reg_pass, et_reg_confpass;

    CountryCodePicker country_code;
    ConstraintLayout signup_layout;

    DatePickerDialog datePickerDialog;

    Spinner sp_reg_gender, sp_marital_status;

    TextView tv_reg_tos;

    //============================================ON CREATE=========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        weak_signup = new WeakReference<>(SignupActivity.this);

        et_reg_fn = findViewById(R.id.et_reg_fn);
        et_reg_ln = findViewById(R.id.et_reg_ln);
        et_reg_mobile = findViewById(R.id.et_reg_mobile_number);
        et_reg_email = findViewById(R.id.et_reg_email);
        et_reg_dob = findViewById(R.id.et_reg_dob);
        et_reg_pass = findViewById(R.id.et_reg_password);
        et_reg_confpass = findViewById(R.id.et_reg_conf_pass);

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
        datePickerDialog = new DatePickerDialog(this, R.style.DatePickerTheme, dateSetListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));


    }
    //============================================ON CREATE=========================================

    @Override
    protected void onResume() {
        super.onResume();
        CarewexCalls.get_access_token(weak_signup.get());
    }
    //--------------------------------------DEFINED METHODS-----------------------------------------

    void test_userinputs(){

        if (et_reg_fn.getText().toString().isEmpty()){
            common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                    "Please Enter First name", Snackbar.LENGTH_LONG).show();

        }else if(et_reg_ln.getText().toString().isEmpty()){
            common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                    "Please Enter Last name", Snackbar.LENGTH_LONG).show();

        }else if (et_reg_mobile.getText().toString().isEmpty()){

            common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                    "Enter mobile number", Snackbar.LENGTH_LONG).show();

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

        }else if (et_reg_pass.getText().toString().length() < 6){
            common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                    "Enter Minimum of six characters", Snackbar.LENGTH_LONG).show();

        }else if (!et_reg_confpass.getText().toString().equals(et_reg_pass.getText().toString())){
            common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                    "Passwords do not match", Snackbar.LENGTH_LONG).show();
        }else {

            try {
                int mob_numb = Integer.parseInt(et_reg_mobile.getText().toString());

                if (String.valueOf(mob_numb).length() != 9){
                    common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                            "Enter valid mobile number", Snackbar.LENGTH_LONG).show();
                    et_reg_mobile.requestFocus();

                }else {

                    Biography reg_biography = new Biography(
                            et_reg_fn.getText().toString(),
                            et_reg_ln.getText().toString(),
                            sp_reg_gender.getSelectedItemPosition(),
                            country_code.getSelectedCountryCode(),
                            String.valueOf(mob_numb),
                            et_reg_dob.getText().toString(),
                            sp_marital_status.getSelectedItemPosition()
                    );

                    pass_dataTo_verification(reg_biography,et_reg_confpass.getText().toString());

                }
            }catch (Exception mobilenumb_error){
                common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                        "Enter valid mobile number", Snackbar.LENGTH_LONG).show();
                et_reg_mobile.requestFocus();
            }
        }
    }

    void pass_dataTo_verification(Biography reg_biography, String password){
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

        verification_intent.putExtra(Biography.FIRSTNAME, reg_biography.getFirstname());
        verification_intent.putExtra(Biography.LASTNAME, reg_biography.getLastname());
        verification_intent.putExtra(Biography.GENDER, reg_biography.getGender());
        verification_intent.putExtra(Biography.PASSWORD,password);

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        SimpleDateFormat parseDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
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

    //--------------------------------------DEFINED METHODS-----------------------------------------

    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=ONCLICK LISTENER-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=
    public void toVerification(View view) {
        test_userinputs();
    }
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=ONCLICK LISTENER-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=
}
