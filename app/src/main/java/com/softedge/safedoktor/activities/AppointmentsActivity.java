package com.softedge.safedoktor.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softedge.safedoktor.R;
import com.softedge.safedoktor.adapters.Appointments_recycler_Adapter;
import com.softedge.safedoktor.databases.appDB;
import com.softedge.safedoktor.models.swaggerModels.Appointment;
import com.softedge.safedoktor.models.swaggerModels.PatientModel;
import com.softedge.safedoktor.utilities.common_code;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.softedge.safedoktor.utilities.AppConstants.APPT_STATUS_BOOKED;
import static com.softedge.safedoktor.utilities.AppConstants.APPT_STATUS_COMPLETED;
import static com.softedge.safedoktor.utilities.AppConstants.APPT_STATUS_INPROGRESS;
import static com.softedge.safedoktor.utilities.AppConstants.KEY_PATIENT_ID;

public class AppointmentsActivity extends AppCompatActivity {

    TabHost appt_tabhost;
    WeakReference<AppointmentsActivity> weakAppt;
    ConstraintLayout const_appt_layout;

    RecyclerView appt_recycler;

    int patientID;
    appDB app_DB;

    //===========================================ON CREATE==========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        weakAppt = new WeakReference<>(this);
        app_DB = appDB.getInstance(weakAppt.get());

        SharedPreferences appPref = common_code.appPref(weakAppt.get());

        patientID = appPref.getInt(KEY_PATIENT_ID,0);

        //--------------------------------------HOME BUTTON ON APP BAR------------------------------
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //--------------------------------------HOME BUTTON ON APP BAR------------------------------

        appt_tabhost = findViewById(R.id.appt_tabhost);
        const_appt_layout = findViewById(R.id.const_appt);
        appt_recycler = findViewById(R.id.tab_recycler);
        appt_tabhost.setup();

        String booked = getResources().getString(R.string.booked);
        String ongoing = getResources().getString(R.string.ongoing);
        String completed = getResources().getString(R.string.completed);
        String doctors = getResources().getString(R.string.doctors);

        //create tab spec
        TabHost.TabSpec bookedSpec = appt_tabhost.newTabSpec(booked);
        TabHost.TabSpec ongoingSpec = appt_tabhost.newTabSpec(ongoing);
        TabHost.TabSpec completedSpec = appt_tabhost.newTabSpec(completed);
        TabHost.TabSpec doctorsSpec = appt_tabhost.newTabSpec(doctors);

        //set tab views
        bookedSpec.setContent(R.id.tab_recycler);
        ongoingSpec.setContent(R.id.tab_recycler);
        completedSpec.setContent(R.id.tab_recycler);
        doctorsSpec.setContent(R.id.tab_recycler);

        //set tab titles
        bookedSpec.setIndicator(booked);
        ongoingSpec.setIndicator(ongoing);
        completedSpec.setIndicator(completed);
        doctorsSpec.setIndicator(doctors);

        //add spec to tabhost
        appt_tabhost.addTab(bookedSpec);
        appt_tabhost.addTab(ongoingSpec);
        appt_tabhost.addTab(completedSpec);
        appt_tabhost.addTab(doctorsSpec);

        if (appt_tabhost.getCurrentTabTag() != null){
            appt_tabhost.setOnTabChangedListener(tabId -> loadappointsByType(appt_tabhost.getCurrentTabTag()));
        }
    }
    //===========================================ON CREATE==========================================

    //------------------------------------------OVERRIDE METHODS------------------------------------


    @Override
    protected void onResume() {
        super.onResume();

        if (appt_tabhost.getCurrentTabTag() != null){
            loadappointsByType(appt_tabhost.getCurrentTabTag());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    //------------------------------------------OVERRIDE METHODS------------------------------------

    //-------------------------------------BUTTON CLICK LISTENER------------------------------------
    public void createAppointment(View view) {
        makeAppointment();
    }
    //-------------------------------------BUTTON CLICK LISTENER------------------------------------

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-INTENTS=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    void makeAppointment() {
        //TODO enable appointment picker dialog apptType_pickDiag();

        Intent mk_appt = new Intent(getApplicationContext(), VirtualAppt_Activity.class);
        startActivity(mk_appt);

    }
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-INTENTS=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    //===============================================INTENTS========================================
    void virtual_intent(AlertDialog dialog){
        Intent mk_appt = new Intent(getApplicationContext(), VirtualAppt_Activity.class);
        startActivity(mk_appt);
        dialog.dismiss();
    }

    void physical_intent(AlertDialog dialog){
        Intent mk_appt = new Intent(getApplicationContext(), PhysicalAppt_Activity.class);
        startActivity(mk_appt);
        dialog.dismiss();
    }
    //===============================================INTENTS========================================

    //----------------------------------------DEFINED METHODS---------------------------------------
    void apptType_pickDiag(){

        AlertDialog alertDialog = new AlertDialog.Builder(weakAppt.get()).create();

        alertDialog.setCancelable(true);

        View appt_diag_view = LayoutInflater.from(weakAppt.get())
                .inflate(R.layout.diag_appt_pick,const_appt_layout,false);

        Button  bt_appt_virtual = appt_diag_view.findViewById(R.id.bt_pop_virtual),
                bt_appt_physical = appt_diag_view.findViewById(R.id.bt_pop_physical);

        bt_appt_virtual.setOnClickListener(v -> virtual_intent(alertDialog));

        bt_appt_physical.setOnClickListener(v -> physical_intent(alertDialog));

        alertDialog.setView(appt_diag_view);

        alertDialog.show();
    }

    void loadappointsByType(String tabTag){

        String booked = getResources().getString(R.string.booked);
        String ongoing = getResources().getString(R.string.ongoing);
        String completed = getResources().getString(R.string.completed);

       if (tabTag.equals(booked)){
           refresh_contacts_list(APPT_STATUS_BOOKED);
       }

       if (tabTag.equals(ongoing)){
           refresh_contacts_list(APPT_STATUS_INPROGRESS);
       }

       if (tabTag.equals(completed)){
           refresh_contacts_list(APPT_STATUS_COMPLETED);
       }


    }


    public void refresh_contacts_list(int statusID) {

        app_DB.safeDoktorAccessObj().appointmentByStatus(patientID,statusID).observe(weakAppt.get(), appointments -> {
            Appointments_recycler_Adapter appointmentsAdapter = new Appointments_recycler_Adapter(weakAppt.get(),appointments);
            appt_recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            appt_recycler.setAdapter(appointmentsAdapter);
        });


    }
    //----------------------------------------DEFINED METHODS---------------------------------------
}