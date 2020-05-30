package com.softedge.safedoktor.activities;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.softedge.safedoktor.R;
import com.softedge.safedoktor.utilities.common_code;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Date;

public class VirtualAppt_Activity extends AppCompatActivity implements TabHost.OnTabChangeListener{

    TabHost appt_tabhost;
    final static String INFO_TAG = "Info";
    final static String PAYMENT_TAG = "Payment";
    final static String SUMMARY_TAG = "Summary";

    WeakReference<VirtualAppt_Activity> weak_apptmake;

    CalendarView appt_Calendar;
    DatePicker app_date_picker;

    TextView //tv_spec_view,
            tv_docs_view;

    //==========================================ON CREATE===========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appt_make);

        weak_apptmake = new WeakReference<>(this);

        //--------------------------------------HOME BUTTON ON APP BAR------------------------------
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //--------------------------------------HOME BUTTON ON APP BAR------------------------------

        //------------------------------------------TAB HOST----------------------------------------
        appt_tabhost = findViewById(R.id.appt_tabhost);
        appt_tabhost.setup();

        TabHost.TabSpec infoSpec = appt_tabhost.newTabSpec(INFO_TAG);
        TabHost.TabSpec paymentSpec = appt_tabhost.newTabSpec(PAYMENT_TAG);
        TabHost.TabSpec sumSpec = appt_tabhost.newTabSpec(SUMMARY_TAG);

        infoSpec.setIndicator(INFO_TAG);
        paymentSpec.setIndicator(PAYMENT_TAG);
        sumSpec.setIndicator(SUMMARY_TAG);

        infoSpec.setContent(R.id.appt_info_view);
        paymentSpec.setContent(R.id.appt_payment_view);
        sumSpec.setContent(R.id.appt_sum_view);

        appt_tabhost.addTab(infoSpec);
        appt_tabhost.addTab(paymentSpec);
        appt_tabhost.addTab(sumSpec);
        //------------------------------------------TAB HOST----------------------------------------

//        appt_Calendar = findViewById(R.id.appt_calendar);
        app_date_picker = findViewById(R.id.appt_date_picker);
        tv_docs_view = findViewById(R.id.tv_docs_view);

        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_MONTH, 7);

        Calendar startDate = Calendar.getInstance();

//        appt_Calendar.setMinDate(startDate.getTimeInMillis());
        app_date_picker.setMinDate(startDate.getTimeInMillis());
//        appt_Calendar.setDate(startDate.getTimeInMillis());
        app_date_picker.updateDate(startDate.get(Calendar.YEAR),startDate.get(Calendar.MONTH),startDate.get(Calendar.DAY_OF_MONTH));

        startDate.add(Calendar.DAY_OF_MONTH, 7);
        app_date_picker.setMaxDate(startDate.getTimeInMillis());

        tv_docs_view.setOnClickListener(v -> common_code.toDocProfile(weak_apptmake.get()));

        //TODO Load time slots

    }
    //==========================================ON CREATE===========================================

    //--------------------------------------------OVERRIDE METHODS----------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabChanged(String tabId) {

    }
    //--------------------------------------------OVERRIDE METHODS----------------------------------
}