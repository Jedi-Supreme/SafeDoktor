package com.softedge.safedoktor.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.softedge.safedoktor.R;

public class SettingsActivity extends AppCompatActivity {

    TextView tv_sett_profile, tv_sett_info, tv_sett_notifications;

    //============================================ON CREATE=========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //--------------------------------------HOME BUTTON ON APP BAR------------------------------
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //--------------------------------------HOME BUTTON ON APP BAR------------------------------

        tv_sett_info = findViewById(R.id.tv_sett_info);
        tv_sett_profile = findViewById(R.id.tv_sett_profile);
        tv_sett_notifications = findViewById(R.id.tv_sett_notifications);

        tv_sett_profile.setOnClickListener(v -> toProfile());

    }
    //============================================ON CREATE=========================================

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
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-OVERRIDE METHODS-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    //--------------------------------------------INTENTS-------------------------------------------
    void toProfile() {
        Intent profile_intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(profile_intent);
    }
    //--------------------------------------------INTENTS-------------------------------------------
}
