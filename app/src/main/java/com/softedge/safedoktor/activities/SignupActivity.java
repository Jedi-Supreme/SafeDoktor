package com.softedge.safedoktor.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;
import com.softedge.safedoktor.api.CarewexCalls;
import com.softedge.safedoktor.api.SwaggerCalls;
import com.softedge.safedoktor.models.fireModels.PatientPackage.Biography;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.models.retrofitModels.retroPatient;
import com.softedge.safedoktor.models.swaggerModels.body.PhoneNumber;
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
            et_reg_fn;
    TextInputEditText et_reg_ln;
    TextInputEditText et_reg_mobile;
    static TextInputEditText et_reg_dob;
    TextInputEditText et_reg_pass;
    TextInputEditText et_reg_confpass;

    ProgressBar probar_reg_check;

    CountryCodePicker country_code;
    ConstraintLayout const_signup_layout;

    DatePickerDialog datePickerDialog;

    Bundle existing_srch_pat;

    Spinner sp_reg_gender;//, sp_marital_status, sp_facility_pick;

    TextView tv_reg_tos, tv_reg_acc_chk;
    String opd_ID, pat_search_email,serialised_carwex_ID = null;

    Biography reg_biography;

    SharedPreferences appPref;

    //============================================ON CREATE=========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        weak_signup = new WeakReference<>(SignupActivity.this);
        appPref = common_code.appPref(weak_signup.get());

        existing_srch_pat = getIntent().getExtras();

        et_reg_fn = findViewById(R.id.et_reg_fn);
        et_reg_ln = findViewById(R.id.et_reg_ln);
        et_reg_mobile = findViewById(R.id.et_reg_mobile_number);
        et_reg_dob = findViewById(R.id.et_reg_dob);
        et_reg_pass = findViewById(R.id.et_reg_password);
        et_reg_confpass = findViewById(R.id.et_reg_conf_pass);

        probar_reg_check = findViewById(R.id.probar_reg_check);
        tv_reg_acc_chk = findViewById(R.id.tv_reg_acc_chk);

        tv_reg_tos = findViewById(R.id.tv_reg_tos);

        sp_reg_gender = findViewById(R.id.sp_reg_gender);
//        sp_facility_pick = findViewById(R.id.sp_reg_facility_pick);
//        sp_marital_status = findViewById(R.id.sp_reg_marital_State);

        const_signup_layout = findViewById(R.id.reg_const_layout);

        input_reg_password = findViewById(R.id.input_reg_password);
        input_reg_confpass = findViewById(R.id.input_reg_conf_pass);

        country_code = findViewById(R.id.hbb_picker);

        tv_reg_tos.setOnClickListener(v -> common_code.toTOS(weak_signup.get()));

        et_reg_dob.setOnClickListener(v -> user_pick_date());

        et_reg_pass.setOnFocusChangeListener((v, hasFocus) -> input_reg_password.setPasswordVisibilityToggleEnabled(hasFocus));

        et_reg_confpass.setOnFocusChangeListener((v, hasFocus) -> input_reg_confpass.setPasswordVisibilityToggleEnabled(hasFocus));

        if (existing_srch_pat != null){
            loadExistingSrchData(existing_srch_pat);
        }

    }
    //============================================ON CREATE=========================================

    @Override
    protected void onResume() {
        super.onResume();

        int position = appPref.getInt(retroPatient.REGISTRATION_FACILITY,0);

        if (common_code.isInternetConnected(weak_signup.get())){
            CarewexCalls.get_access_token(weak_signup.get(),common_code.Build_Employee(position));
        }else {
            common_code.connection_toast(getApplicationContext());
        }
    }
    //--------------------------------------DEFINED METHODS-----------------------------------------

    void test_userinputs(){

        if (Objects.requireNonNull(et_reg_fn.getText()).toString().isEmpty()){
            common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                    "Please Enter First name", Snackbar.LENGTH_LONG).show();
            et_reg_fn.requestFocus();

        }else if(Objects.requireNonNull(et_reg_ln.getText()).toString().isEmpty()){
            common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                    "Please Enter Last name", Snackbar.LENGTH_LONG).show();
            et_reg_ln.requestFocus();

        }else if (Objects.requireNonNull(et_reg_mobile.getText()).toString().isEmpty()){
            common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                    "Enter mobile number", Snackbar.LENGTH_LONG).show();
            et_reg_mobile.requestFocus();

        }else if (Objects.requireNonNull(et_reg_dob.getText()).toString().isEmpty()){
            common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                    "Pick date of birth (DD/MMM/YYYY)", Snackbar.LENGTH_LONG).show();

        }else if (sp_reg_gender.getSelectedItemPosition() <= 0){
            common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                    "Please Select Gender", Snackbar.LENGTH_LONG).show();

        }else if (Objects.requireNonNull(et_reg_pass.getText()).toString().isEmpty()){
            common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                    "Enter Password", Snackbar.LENGTH_LONG).show();
            et_reg_pass.requestFocus();

        }else if (6 > et_reg_pass.getText().toString().length()){
            common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                    "Enter Minimum of six characters", Snackbar.LENGTH_LONG).show();
            et_reg_pass.requestFocus();

        }else if (!Objects.requireNonNull(et_reg_confpass.getText()).toString().equals(et_reg_pass.getText().toString())){
            common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                    "Passwords do not match", Snackbar.LENGTH_LONG).show();
            et_reg_confpass.requestFocus();

        }else {

            try {

                int mob_numb;

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
                            1//sp_marital_status.getSelectedItemPosition()
                    );

                    probar_reg_check.setVisibility(View.VISIBLE);
                    tv_reg_acc_chk.setVisibility(View.VISIBLE);

                    String fullnumber = country_code.getSelectedCountryCode()+mob_numb;
                    SwaggerCalls.CheckPhoneNumber(const_signup_layout, new PhoneNumber(fullnumber));

                    //pass_dataTo_verification();
                    //String mobile = "0"+ mob_numb;

                    //common_code.emailAvailability_viaNumber(mobile,weak_signup.get());
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
                pat_search_email = existing_pat.getString(Biography.EMAIL);
                et_reg_dob.setText(existing_pat.getString(Biography.DATE_OF_BIRTH));

                //sp_marital_status.setSelection(existing_pat.getInt(Biography.MARITAL_STATUS));

                opd_ID = existing_pat.getString(Biography.OPD_ID);
                serialised_carwex_ID = existing_pat.getString(Biography.ID);
    }

    public void pass_dataTo_verification(){

        hideProbar();
        Intent verification_intent = new Intent(getApplicationContext(), VerificationActivity.class);

//        if (pat_search_email != null && pat_search_email.contains("@")){
//            verification_intent.putExtra(Biography.EMAIL, pat_search_email);
//        }else {
//            //create default email if patient does not enter valid email
//            String default_email = "0"+ reg_biography.getMobile_number()
//                    +getResources().getString(R.string.default_email_suffix);
//            verification_intent.putExtra(Biography.EMAIL, default_email);
//        }

//        if (opd_ID != null){
//            verification_intent.putExtra(Biography.OPD_ID, opd_ID);
//        }
//
//        if (serialised_carwex_ID != null){
//            verification_intent.putExtra(Biography.ID,serialised_carwex_ID);
//        }

        //------------------------------SAVE USER FACILITY CHOICE-----------------------------------
        SharedPreferences appPref = common_code.appPref(weak_signup.get());
        SharedPreferences.Editor prefEditor = appPref.edit();
//        prefEditor.putInt(retroPatient.REGISTRATION_FACILITY,sp_facility_pick.getSelectedItemPosition());
//        prefEditor.putInt(retroPatient.CURRENT_FACILITY,sp_facility_pick.getSelectedItemPosition());
        prefEditor.apply();
        //------------------------------SAVE USER FACILITY CHOICE-----------------------------------

        verification_intent.putExtra(Biography.FIRSTNAME, reg_biography.getFirstname());
        verification_intent.putExtra(Biography.LASTNAME, reg_biography.getLastname());
        verification_intent.putExtra(Biography.GENDER, reg_biography.getGender());
        verification_intent.putExtra(Biography.PASSWORD, Objects.requireNonNull(et_reg_confpass.getText()).toString());

        verification_intent.putExtra(Biography.MOBILE_NUMBER, reg_biography.getMobile_number());
        verification_intent.putExtra(Biography.COUNTRY_CODE, reg_biography.getCountry_code());
        verification_intent.putExtra(Biography.DATE_OF_BIRTH, reg_biography.getDate_of_birth());
        verification_intent.putExtra(Biography.MARITAL_STATUS,reg_biography.getMarital_state());

        startActivity(verification_intent);
    }

    //-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^_^-^-^-^-^-^-^-DATE-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-
    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(Objects.requireNonNull(getContext()), R.style.DatePickerTheme,this, year, month, day);
        }


        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            //Toast.makeText(view.getContext(),"Year: " + year + " Month: " + (month+1) +
            // " Day: " + dayOfMonth,Toast.LENGTH_LONG).show();

            showDate(year,month,dayOfMonth);

        }
    }

    void user_pick_date(){
        //datePickerDialog.show();
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

    public static void showDate(int year, int month, int day) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(common_code.humanDateformat, Locale.getDefault());
        SimpleDateFormat parseDateFormat = new SimpleDateFormat(common_code.regDateformat, Locale.getDefault());
        String userdate;
        Date date;

        if (month+1<10){
            userdate =  day + "-" + "0" + (month + 1) + "-" + year;
            //et_reg_dob.setText(userdate);
            try {
                date = parseDateFormat.parse(userdate);
                if (date != null) {
                    et_reg_dob.setText(simpleDateFormat.format(date));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }else{
            userdate = day + "-" + (month + 1) + "-" + year;
            //et_reg_dob.setText(userdate);
            try {
                date = parseDateFormat.parse(userdate);
                if (date != null) {
                    et_reg_dob.setText(simpleDateFormat.format(date));
                }
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
