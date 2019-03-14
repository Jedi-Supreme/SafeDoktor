package com.softedge.safedoktor.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.common_code;
import com.softedge.safedoktor.databases.SafeDB;
import com.softedge.safedoktor.fireModels.PatientPackage.Biography;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BiographyActivity extends AppCompatActivity {

    Spinner sp_bio_marry, sp_bio_gender, sp_bio_bloodgrp;

    TextInputEditText et_bio_fn, et_bio_ln, et_bio_dob, et_bio_Address, et_bio_height, et_bio_weight;

    ProgressBar probar_bio_update;
    Biography loadBio;

    ConstraintLayout const_bio_layout;
    WeakReference<BiographyActivity> weakBio;

    DatePickerDialog datePickerDialog;

    String userID;

    //==============================================ON CREATE=======================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biography);

        weakBio = new WeakReference<>(this);

        const_bio_layout = findViewById(R.id.const_bio_layout);
        probar_bio_update = findViewById(R.id.probar_bio_update);

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

    }

    void testBioInputs() {

        SafeDB safe_db = new SafeDB(weakBio.get(), null);

        FirebaseUser fireUser = FirebaseAuth.getInstance().getCurrentUser();

        if (sp_bio_marry.getSelectedItemPosition() == 0) {
            common_code.Mysnackbar(const_bio_layout, "Select Marital Status", Snackbar.LENGTH_SHORT).show();
        } else if (et_bio_fn.getText().toString().isEmpty() || et_bio_fn.getText().toString().equals("")) {
            common_code.Mysnackbar(const_bio_layout, "Enter First name", Snackbar.LENGTH_SHORT).show();
        } else if (et_bio_ln.getText().toString().isEmpty() || et_bio_ln.getText().toString().equals("")) {
            common_code.Mysnackbar(const_bio_layout, "Enter Last name", Snackbar.LENGTH_SHORT).show();
        } else if (sp_bio_gender.getSelectedItemPosition() == 0) {
            common_code.Mysnackbar(const_bio_layout, "Select Gender", Snackbar.LENGTH_SHORT).show();
        } else if (et_bio_dob.getText().toString().isEmpty() || et_bio_dob.getText().toString().equals("")) {
            common_code.Mysnackbar(const_bio_layout, "Enter Date of Birth", Snackbar.LENGTH_SHORT).show();
        } else {

            probar_bio_update.setVisibility(View.VISIBLE);

            if (fireUser != null) {

                Biography bio = new Biography(
                        fireUser.getUid(),
                        et_bio_fn.getText().toString(),
                        et_bio_ln.getText().toString(),
                        sp_bio_gender.getSelectedItemPosition(),
                        loadBio.getCountry_code(),
                        loadBio.getMobile_number(),
                        fireUser.getEmail(),
                        et_bio_dob.getText().toString(),
                        sp_bio_marry.getSelectedItemPosition()

                );

                //save online and update local db
                safe_db.updatePat_bio(bio);
                save_Online(bio);

            } else {
                probar_bio_update.setVisibility(View.GONE);

            }
        }


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
                et_bio_dob.setText(simpleDateFormat.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }else{
            userdate = String.valueOf(month+1)+ "/" + String.valueOf(day) +  "/" +  String.valueOf(year) ;
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
    void save_Online(Biography fireBio) {
        DatabaseReference all_users_ref = FirebaseDatabase.getInstance().getReference(getResources().getString(R.string.all_users));

        //save user details to All_Users/Biography/Uid
        all_users_ref.child(getResources().getString(R.string.bio_ref)).child(fireBio.getFirebase_Uid())
                .setValue(fireBio).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                probar_bio_update.setVisibility(View.GONE);
                common_code.Mysnackbar(const_bio_layout, "Biography Updated Successfully", Snackbar.LENGTH_SHORT).show();
            } else {
                probar_bio_update.setVisibility(View.GONE);
                common_code.Mysnackbar(const_bio_layout, "Biography Update Failed, Please Try again later",
                        Snackbar.LENGTH_SHORT).show();
            }
        });
    }
    //--------------------------------------SAVE TO ONLINE DB---------------------------------------

    //----------------------------------------DEFINED METHODS---------------------------------------

    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-OVERRIDE METHODS-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                super.onBackPressed();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-OVERRIDE METHODS-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    //-----------------------------------------BUTTON CLICK LISTENER--------------------------------
    public void Save_bio_data(View view) {
        testBioInputs();
    }
    //-----------------------------------------BUTTON CLICK LISTENER--------------------------------
}
