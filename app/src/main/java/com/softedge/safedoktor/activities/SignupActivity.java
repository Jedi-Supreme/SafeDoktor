package com.softedge.safedoktor.activities;

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

import com.hbb20.CountryCodePicker;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.utilities.common_code;
import com.softedge.safedoktor.models.fireModels.PatientPackage.Biography;

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

    Spinner sp_reg_gender;

    //============================================ON CREATE=========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //todo terms and conditions

        weak_signup = new WeakReference<>(SignupActivity.this);

        et_reg_fn = findViewById(R.id.et_reg_fn);
        et_reg_ln = findViewById(R.id.et_reg_ln);
        et_reg_mobile = findViewById(R.id.et_reg_mobile_number);
        et_reg_email = findViewById(R.id.et_reg_email);
        et_reg_dob = findViewById(R.id.et_reg_dob);
        et_reg_pass = findViewById(R.id.et_reg_password);
        et_reg_confpass = findViewById(R.id.et_reg_conf_pass);

        sp_reg_gender = findViewById(R.id.sp_reg_gender);
        signup_layout = findViewById(R.id.reg_const_layout);

        input_reg_password = findViewById(R.id.input_reg_password);
        input_reg_confpass = findViewById(R.id.input_reg_conf_pass);

        country_code = findViewById(R.id.hbb_picker);

        et_reg_dob.setOnClickListener(v -> user_pick_date());

        et_reg_pass.setOnFocusChangeListener((v, hasFocus) -> input_reg_password.setPasswordVisibilityToggleEnabled(hasFocus));

        et_reg_confpass.setOnFocusChangeListener((v, hasFocus) -> input_reg_confpass.setPasswordVisibilityToggleEnabled(hasFocus));

        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, R.style.DatePickerTheme, dateSetListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));


    }
    //============================================ON CREATE=========================================

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
                            et_reg_dob.getText().toString()
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
            String default_email = "0"+String.valueOf(reg_biography.getMobile_number())
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

        startActivity(verification_intent);

        //todo use carewex api to register patient and get opd number for user profile

    }

    //-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^_^-^-^-^-^-^-^-DATE-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-
    DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> showDate(year, month, dayOfMonth);

    void user_pick_date(){
        datePickerDialog.show();
    }

    public void showDate(int year, int month, int day) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault());
        SimpleDateFormat parseDateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        String userdate;
        Date date;

        if (month+1<10){
            userdate =  "0" + String.valueOf(month+1)+ "/" + String.valueOf(day) +  "/" +  String.valueOf(year) ;
            try {
                date = parseDateFormat.parse(userdate);
                et_reg_dob.setText(simpleDateFormat.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }else{
            userdate = String.valueOf(month+1)+ "/" + String.valueOf(day) +  "/" +  String.valueOf(year) ;
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
