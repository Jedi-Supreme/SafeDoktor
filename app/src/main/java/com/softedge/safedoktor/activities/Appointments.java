package com.softedge.safedoktor.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TabHost;

import com.softedge.safedoktor.R;

import java.lang.ref.WeakReference;

public class Appointments extends AppCompatActivity {

    TabHost appt_tabhost;
    WeakReference<Appointments> weakAppt;

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

    //-------------------------------------BUTTON CLICK LISTENER------------------------------------
    public void createAppointment(View view) {
        makeAppointment();
    }
    //-------------------------------------BUTTON CLICK LISTENER------------------------------------

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-INTENTS=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    void makeAppointment() {
        Intent mk_appt = new Intent(getApplicationContext(), ApptMake_Activity.class);
        startActivity(mk_appt);
    }
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-INTENTS=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
}
