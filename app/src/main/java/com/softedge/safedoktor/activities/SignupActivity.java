package com.softedge.safedoktor.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.hbb20.CountryCodePicker;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.common_code;
import com.softedge.safedoktor.models.Patient;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignupActivity extends AppCompatActivity {

    WeakReference<SignupActivity> weak_signup;

    AlertDialog pic_dialog_builder;

    TextInputLayout
            input_reg_password,
            input_reg_confpass;

    TextInputEditText
            et_reg_fn, et_reg_ln,
            et_reg_mobile, et_reg_email,
            et_reg_dob, et_reg_pass, et_reg_confpass;

    CircleImageView iv_reg_propic;

    CountryCodePicker country_code;
    ConstraintLayout signup_layout;

    //Uri global_profile_pic_path;

    DatePickerDialog datePickerDialog;

    Spinner sp_reg_gender;
    private final int  GET_FROM_CAMERA = 201;
    private final int GET_FROM_GALLERY = 202;

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

        sp_reg_gender = findViewById(R.id.sp_reg_gender);
        signup_layout = findViewById(R.id.reg_const_layout);

        input_reg_password = findViewById(R.id.input_reg_password);
        input_reg_confpass = findViewById(R.id.input_reg_conf_pass);

        iv_reg_propic = findViewById(R.id.iv_reg_pro_pic);

        country_code = findViewById(R.id.hbb_picker);

        et_reg_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_pick_date();
            }
        });

        et_reg_pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                input_reg_password.setPasswordVisibilityToggleEnabled(hasFocus);
            }
        });

        et_reg_confpass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                input_reg_confpass.setPasswordVisibilityToggleEnabled(hasFocus);
            }
        });

        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this,R.style.DatePickerTheme,dateSetListener,
                calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

        iv_reg_propic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_pic_dialog();
            }
        });

    }
    //============================================ON CREATE=========================================

    //--=--=--=--=--=--=--=--=--=--=--=--=--=--=--OVERRIDE METHODS=--=--=--=--=--=--=--=--=--=--=--=
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        pic_dialog_builder.dismiss();

        switch (requestCode){

            case GET_FROM_CAMERA:
                if (resultCode == RESULT_OK){
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap;

                    if (extras != null) {
                        imageBitmap = (Bitmap) extras.get("data");
                        pic_preview_dialog(imageBitmap);
                    }

                }
                break;

            case GET_FROM_GALLERY:
                if (resultCode == RESULT_OK){
                    pic_preview_dialog(data.getData());
                }
                break;
        }
    }

    //--=--=--=--=--=--=--=--=--=--=--=--=--=--=--OVERRIDE METHODS=--=--=--=--=--=--=--=--=--=--=--=

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

                    Patient reg_patient = new Patient(
                            et_reg_fn.getText().toString(),
                            et_reg_ln.getText().toString(),
                            sp_reg_gender.getSelectedItemPosition(),
                            country_code.getSelectedCountryCode(),
                            mob_numb,
                            et_reg_dob.getText().toString()
                    );

                    pass_dataTo_verification(reg_patient,et_reg_confpass.getText().toString());

                }
            }catch (Exception mobilenumb_error){
                common_code.Mysnackbar(findViewById(R.id.reg_const_layout),
                        "Enter valid mobile number", Snackbar.LENGTH_LONG).show();
                et_reg_mobile.requestFocus();
            }
        }
    }

    //build dialog for user to select profile pic
    void profile_pic_dialog(){
        pic_dialog_builder = new AlertDialog.Builder(weak_signup.get()).create();

        View propic_view = LayoutInflater.from(weak_signup.get()).inflate(R.layout.frag_propic,signup_layout,false);

        pic_dialog_builder.setCancelable(true);
        pic_dialog_builder.setView(propic_view);

        Button bt_camera = propic_view.findViewById(R.id.bt_set_camera);
        Button bt_gallery = propic_view.findViewById(R.id.bt_set_gallery);

        bt_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent start_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (start_camera.resolveActivity(getPackageManager())!= null){
                    startActivityForResult(start_camera,GET_FROM_CAMERA);
                }
            }
        });

        bt_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gallery_pic = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);

                if (gallery_pic.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(gallery_pic,GET_FROM_GALLERY);
                }

            }
        });

        pic_dialog_builder.show();

    }

    //user previews selected picture from camera
    void pic_preview_dialog(final Bitmap profile_pic_bitmap){

        final AlertDialog preview_dialog_builder = new AlertDialog.Builder(weak_signup.get()).create();

        View propic_view = LayoutInflater.from(weak_signup.get()).inflate(R.layout.frag_pic_preview,signup_layout,false);

        preview_dialog_builder.setCancelable(true);
        preview_dialog_builder.setView(propic_view);

        CircleImageView iv_prev_propic = propic_view.findViewById(R.id.iv_prev_propic);
        Button bt_prev_accept = propic_view.findViewById(R.id.bt_prev_accept);
        Button bt_prev_cancel = propic_view.findViewById(R.id.bt_prev_cancel);

        try {
            iv_prev_propic.setImageBitmap(profile_pic_bitmap);
        }catch (Exception ignored){}


        //user accepted to set selected pic
        bt_prev_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_reg_propic.setImageBitmap(profile_pic_bitmap);
                preview_dialog_builder.dismiss();
            }
        });

        //user declined pic setting process
        bt_prev_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preview_dialog_builder.dismiss();
            }
        });

        preview_dialog_builder.show();
    }

    //preview overload for gallery
    void pic_preview_dialog(final Uri profile_pic_path){

        final AlertDialog preview_dialog_builder = new AlertDialog.Builder(weak_signup.get()).create();

        View propic_view = LayoutInflater.from(weak_signup.get()).inflate(R.layout.frag_pic_preview,signup_layout,false);

        preview_dialog_builder.setCancelable(true);
        preview_dialog_builder.setView(propic_view);

        CircleImageView iv_prev_propic = propic_view.findViewById(R.id.iv_prev_propic);
        Button bt_prev_accept = propic_view.findViewById(R.id.bt_prev_accept);
        Button bt_prev_cancel = propic_view.findViewById(R.id.bt_prev_cancel);

        try {
            iv_prev_propic.setImageURI(profile_pic_path);
        }catch (Exception ignored){}


        //user accepted to set selected pic
        bt_prev_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_reg_propic.setImageURI(profile_pic_path);
                preview_dialog_builder.dismiss();
            }
        });

        //user declined pic setting process
        bt_prev_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preview_dialog_builder.dismiss();
            }
        });

        preview_dialog_builder.show();
    }

    void pass_dataTo_verification(Patient reg_patient, String password){
        Intent verification_intent = new Intent(getApplicationContext(), VerificationActivity.class);

        if (!et_reg_email.getText().toString().isEmpty() &&
                et_reg_email.getText().toString().contains("@") &&
                et_reg_email.getText().toString().contains(".")){
            verification_intent.putExtra(Patient.EMAIL, et_reg_email.getText().toString());
        }else {
            //create default email if patient does not enter valid email
            String default_email = "0"+String.valueOf(reg_patient.getMobile_number())+"@safedoktor.com";
            verification_intent.putExtra(Patient.EMAIL, default_email);
        }

        verification_intent.putExtra(Patient.FIRSTNAME, reg_patient.getFirstname());
        verification_intent.putExtra(Patient.LASTNAME, reg_patient.getLastname());
        verification_intent.putExtra(Patient.GENDER, reg_patient.getGender());
        verification_intent.putExtra(Patient.PASSWORD,password);

        verification_intent.putExtra(Patient.MOBILE_NUMBER, reg_patient.getMobile_number());
        verification_intent.putExtra(Patient.COUNTRY_CODE, reg_patient.getCountry_code());
        verification_intent.putExtra(Patient.DATE_OF_BIRTH,reg_patient.getDate_of_birth());

        startActivity(verification_intent);

    }

    //-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^_^-^-^-^-^-^-^-DATE-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-
    void user_pick_date(){
        datePickerDialog.show();
    }

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            showDate(year,month,dayOfMonth);
        }
    };

    public void showDate(int year, int month, int day) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/YYYY", Locale.getDefault());
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
