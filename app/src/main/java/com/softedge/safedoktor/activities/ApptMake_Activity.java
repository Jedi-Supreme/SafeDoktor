package com.softedge.safedoktor.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TabHost;

import com.softedge.safedoktor.R;

public class ApptMake_Activity extends AppCompatActivity implements TabHost.OnTabChangeListener{

    TabHost appt_tabhost;
    final static String INFO_TAG = "Info";
    final static String PAYMENT_TAG = "Payment";
    final static String SUMMARY_TAG = "Summary";

    //==========================================ON CREATE===========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appt_make);

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



    }
    //==========================================ON CREATE===========================================

    //--------------------------------------------OVERRIDE METHODS----------------------------------
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
    public void onTabChanged(String tabId) {

    }
    //--------------------------------------------OVERRIDE METHODS----------------------------------
}
