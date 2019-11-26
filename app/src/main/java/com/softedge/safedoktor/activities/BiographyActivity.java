package com.softedge.safedoktor.activities;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.softedge.safedoktor.api.CarewexCalls;
import com.softedge.safedoktor.models.fireModels.Patient;
import com.softedge.safedoktor.models.fireModels.PatientPackage.Address;
import com.softedge.safedoktor.models.fireModels.PatientPackage.Biography;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.models.fireModels.PatientPackage.Physicals;
import com.softedge.safedoktor.models.retrofitModels.retroPatient;
import com.softedge.safedoktor.models.retrofitModels.retro_patSearch;
import com.softedge.safedoktor.utilities.common_code;
import com.softedge.safedoktor.databases.SafeDB;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BiographyActivity extends AppCompatActivity {

    Spinner sp_bio_marry, sp_bio_gender, sp_bio_bloodgrp;

    TextInputEditText
            et_bio_fn, et_bio_ln, et_bio_dob,
            et_bio_Address, et_bio_height, et_bio_weight;

    ProgressBar probar_bio_update;
    Biography loadBio;

    ConstraintLayout const_bio_layout;
    WeakReference<BiographyActivity> weakBio;

    DatePickerDialog datePickerDialog;

    String userID;

    SharedPreferences bioAct_pref;

    Patient patient;

    //==============================================ON CREATE=======================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biography);

        weakBio = new WeakReference<>(this);

        const_bio_layout = findViewById(R.id.const_bio_layout);
        probar_bio_update = findViewById(R.id.probar_bio_update);

        bioAct_pref = common_code.appPref(weakBio.get());

        //--------------------------------------HOME BUTTON ON APP BAR------------------------------
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //--------------------------------------HOME BUTTON ON APP BAR------------------------------

        sp_bio_marry = findViewById(R.id.sp_bio_marry);
        sp_bio_gender = findViewById(R.id.sp_bio_gender);
        sp_bio_bloodgrp = findViewById(R.id.sp_bio_bloodgrp);

        et_bio_fn = findViewById(R.id.et_bio_fn);
        et_bio_ln = findViewById(R.id.et_bio_ln);
        et_bio_dob = findViewById(R.id.et_bio_dob);
        et_bio_Address = findViewById(R.id.et_bio_add);
        et_bio_height = findViewById(R.id.et_bio_height);
        et_bio_weight = findViewById(R.id.et_bio_weight);

        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, R.style.DatePickerTheme, dateSetListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        try {
            loadUser_biodata();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

        et_bio_dob.setOnClickListener(v -> user_pick_date());

    }
    //==============================================ON CREATE=======================================

    //----------------------------------------DEFINED METHODS---------------------------------------

    void getpatResult(String opdnumber){
        retro_patSearch search = new retro_patSearch("","",opdnumber,"");
        CarewexCalls.getPatientsResult(search,weakBio.get());
    }

    void loadUser_biodata() {

        SafeDB safe_db = new SafeDB(weakBio.get(), null);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        loadBio = safe_db.local_appUser(userID);

        sp_bio_marry.setSelection(loadBio.getMarital_state(), true);
        et_bio_fn.setText(loadBio.getFirstname());
        et_bio_ln.setText(loadBio.getLastname());

        sp_bio_gender.setSelection(loadBio.getGender(), true);
        et_bio_dob.setText(loadBio.getDate_of_birth());

        if (bioAct_pref != null){
            sp_bio_bloodgrp.setSelection(bdGrp_index());
            et_bio_Address.setText(bioAct_pref.getString(Address.LOC_ADDRESS,""));
            et_bio_height.setText(String.valueOf(bioAct_pref.getInt(Physicals.HEIGHT,0)));
            et_bio_weight.setText(String.valueOf(bioAct_pref.getInt(Physicals.WEIGHT,0)));
        }

    }

    void testInputs() {

        SafeDB safe_db = new SafeDB(weakBio.get(), null);

        FirebaseUser fireUser = FirebaseAuth.getInstance().getCurrentUser();
        Physicals physicals = new Physicals();
        Address pat_address = new Address();

        pat_address.setUser_fireId(userID);
        physicals.setUser_fireID(userID);

        if (sp_bio_marry.getSelectedItemPosition() == 0) {
            common_code.Mysnackbar(const_bio_layout, "Select Marital Status", Snackbar.LENGTH_SHORT).show();
        }
        else if (et_bio_fn.getText().toString().isEmpty() || et_bio_fn.getText().toString().equals("")) {
            common_code.Mysnackbar(const_bio_layout, "Enter First name", Snackbar.LENGTH_SHORT).show();
        }
        else if (et_bio_ln.getText().toString().isEmpty() || et_bio_ln.getText().toString().equals("")) {
            common_code.Mysnackbar(const_bio_layout, "Enter Last name", Snackbar.LENGTH_SHORT).show();
        }
        else if (sp_bio_gender.getSelectedItemPosition() == 0) {
            common_code.Mysnackbar(const_bio_layout, "Select Gender", Snackbar.LENGTH_SHORT).show();
        }
        else if (et_bio_dob.getText().toString().isEmpty() || et_bio_dob.getText().toString().equals("")) {
            common_code.Mysnackbar(const_bio_layout, "Enter Date of Birth", Snackbar.LENGTH_SHORT).show();
        }
        else {

            probar_bio_update.setVisibility(View.VISIBLE);

            if (!et_bio_Address.getText().toString().isEmpty() || !et_bio_Address.getText().toString().equals("")){
                pat_address.setLoc_address(et_bio_Address.getText().toString());
            }

            if (sp_bio_bloodgrp.getSelectedItemPosition() > 0){
                String bloodgroup = sp_bio_bloodgrp.getItemAtPosition(sp_bio_bloodgrp.getSelectedItemPosition()).toString();
                physicals.setBlood_group(bloodgroup);
                //Toast.makeText(getApplicationContext(),bloodgroup,Toast.LENGTH_SHORT).show();
            }

            if (!et_bio_weight.getText().toString().isEmpty()){

                try {
                    double weight = Double.parseDouble(et_bio_weight.getText().toString());
                    physicals.setWeight(weight);
                }catch (Exception ignored){}

            }

            if (!et_bio_height.getText().toString().isEmpty()){

                try {
                    double height = Double.parseDouble(et_bio_height.getText().toString());
                    physicals.setHeight(height);
                }catch (Exception ignored){}

            }

            if (fireUser != null) {

                Biography bio = new Biography(
                        fireUser.getUid(),
                        loadBio.getOpd_Id(),
                        et_bio_fn.getText().toString(),
                        et_bio_ln.getText().toString(),
                        sp_bio_gender.getSelectedItemPosition(),
                        loadBio.getCountry_code(),
                        loadBio.getMobile_number(),
                        fireUser.getEmail(),
                        et_bio_dob.getText().toString(),
                        sp_bio_marry.getSelectedItemPosition(),
                        loadBio.getPropic_url()
                );

                patient = new Patient(bio,pat_address,physicals);

                //save online and update local db
                //TODO updae carewex details
                safe_db.updatePat_bio(bio);
                physicals_Address_topref(patient);
                save_Online();

            } else {
                probar_bio_update.setVisibility(View.GONE);
            }
        }


    }

    int bdGrp_index(){

        String bgrp = bioAct_pref.getString(Physicals.BLOOD_GROUP,null);
        String[] brgp_arr = getResources().getStringArray(R.array.blood_groups_arr);

        if (bgrp != null){

            for (int x = 0; x < brgp_arr.length; x++){

                if (bgrp.equals(brgp_arr[x])){
                    return x;
                }
            }
        }

        return 0;
    }

    void physicals_Address_topref(Patient patient){

        SharedPreferences.Editor prefEditor = bioAct_pref.edit();

        if (patient.getPhysicals() != null){
            prefEditor.putString(Physicals.BLOOD_GROUP,patient.getPhysicals().getBlood_group());
            prefEditor.putInt(Physicals.HEIGHT,(int)patient.getPhysicals().getHeight());
            prefEditor.putInt(Physicals.WEIGHT,(int)patient.getPhysicals().getWeight());
        }

        if (patient.getAddress() != null){
            prefEditor.putString(Address.LOC_ADDRESS,patient.getAddress().getLoc_address());
        }

        prefEditor.apply();
    }
    //-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^_^-^-^-^-^-^-^-DATE-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-
    DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth)
            -> showDate(year, month, dayOfMonth);

    void user_pick_date(){
        datePickerDialog.show();
    }

    public void showDate(int year, int month, int day) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault());
        SimpleDateFormat parseDateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        String userdate;
        Date date;

        if (month+1<10){
            userdate =  "0" + (month + 1) + "/" + day +  "/" + year;
            try {
                date = parseDateFormat.parse(userdate);
                et_bio_dob.setText(simpleDateFormat.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }else{
            userdate = (month + 1) + "/" + day +  "/" + year;
            try {
                date = parseDateFormat.parse(userdate);
                et_bio_dob.setText(simpleDateFormat.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    //-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^_^-^-^-^-^-^-^-DATE-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-

    //--------------------------------------SAVE TO ONLINE DB---------------------------------------
    void save_Online() {

        DatabaseReference all_users_ref = FirebaseDatabase.getInstance().getReference(getResources().getString(R.string.all_users));

        try {
            //CarewexCalls.update_patient(common_code.carewex_pat(fireBio,weakBio.get()),weakBio.get());

            if (patient.getBiography() != null){

                getpatResult(patient.getBiography().getOpd_Id());

                //save user details to All_Users/Biography/Uid
                all_users_ref.child(Biography.TABLE).child(patient.getBiography().getFirebase_Uid())
                        .setValue(patient.getBiography()).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        probar_bio_update.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"Biography Updated Successfully",Toast.LENGTH_SHORT).show();
                        //common_code.Mysnackbar(const_bio_layout, , Snackbar.LENGTH_SHORT).show();
                    } else {
                        probar_bio_update.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"Biography Update Failed, Please Try again later",Toast.LENGTH_SHORT).show();
                        //common_code.Mysnackbar(const_bio_layout, ,Snackbar.LENGTH_SHORT).show();
                    }
                });
            }

            if (patient.getAddress() != null){
                //save user details to All_Users/Address/Uid
                all_users_ref.child(Address.TABLE).child(patient.getAddress().getUser_fireId())
                        .setValue(patient.getAddress()).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        probar_bio_update.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"Address Updated Successfully",Toast.LENGTH_SHORT).show();
                        //common_code.Mysnackbar(const_bio_layout, , Snackbar.LENGTH_SHORT).show();
                    } else {
                        probar_bio_update.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"Address Update Failed, Please Try again later",Toast.LENGTH_SHORT).show();
                        //common_code.Mysnackbar(const_bio_layout, ,Snackbar.LENGTH_SHORT).show();
                    }
                });
            }

            if (patient.getPhysicals() != null){
                //save user details to All_Users/Physicals/Uid
                all_users_ref.child(Physicals.TABLE).child(patient.getPhysicals().getUser_fireID())
                        .setValue(patient.getPhysicals()).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        probar_bio_update.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"Physicals Updated Successfully",Toast.LENGTH_SHORT).show();
                        //common_code.Mysnackbar(const_bio_layout, , Snackbar.LENGTH_SHORT).show();
                    } else {
                        probar_bio_update.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"Physicals Update Failed, Please Try again later",Toast.LENGTH_SHORT).show();
                        //common_code.Mysnackbar(const_bio_layout, ,Snackbar.LENGTH_SHORT).show();
                    }
                });
            }

        }catch (Exception ignored){}


    }
    //--------------------------------------SAVE TO ONLINE DB---------------------------------------

    //Register user on carewex and return OPD number
    public void carewex_patID(List<retroPatient> retroPatientUser){

        if (retroPatientUser.size() > 0){
            patient.getBiography().setId(retroPatientUser.get(0).getId());
            patient.getBiography().setOpd_Id(retroPatientUser.get(0).getPatientId());

            // register patient on carewex
            CarewexCalls.register_patient(common_code.carewex_pat(patient.getBiography(),weakBio.get()),weakBio.get());
        }
    }

    //----------------------------------------DEFINED METHODS---------------------------------------

    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-OVERRIDE METHODS-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
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

        if (common_code.isInternetConnected(weakBio.get())){
            CarewexCalls.get_access_token(weakBio.get());
        }else{
            common_code.connection_toast(getApplicationContext());
        }
    }

    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-OVERRIDE METHODS-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    //-----------------------------------------BUTTON CLICK LISTENER--------------------------------
    public void Save_bio_data(View view) {
        testInputs();
    }
    //-----------------------------------------BUTTON CLICK LISTENER--------------------------------
}
