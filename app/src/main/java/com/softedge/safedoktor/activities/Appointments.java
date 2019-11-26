package com.softedge.safedoktor.activities;

import android.content.Intent;
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

import com.softedge.safedoktor.R;

import java.lang.ref.WeakReference;

public class Appointments extends AppCompatActivity {

    TabHost appt_tabhost;
    WeakReference<Appointments> weakAppt;
    ConstraintLayout const_appt_layout;

    //===========================================ON CREATE==========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        weakAppt = new WeakReference<>(this);

        //RecyclerView appt_recycler;

        //--------------------------------------HOME BUTTON ON APP BAR------------------------------
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //--------------------------------------HOME BUTTON ON APP BAR------------------------------

        appt_tabhost = findViewById(R.id.appt_tabhost);
        const_appt_layout = findViewById(R.id.const_appt);
        //appt_recycler = findViewById(R.id.tab_recycler);
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

    }
    //===========================================ON CREATE==========================================

    //------------------------------------------OVERRIDE METHODS------------------------------------
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

        Intent mk_appt = new Intent(getApplicationContext(), PhysicalAppt_Activity.class);
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
    //----------------------------------------DEFINED METHODS---------------------------------------
}