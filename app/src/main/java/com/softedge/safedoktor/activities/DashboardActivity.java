package com.softedge.safedoktor.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.softedge.safedoktor.R;

public class DashboardActivity extends AppCompatActivity {

    SharedPreferences safe_pref;
    TextView tv_dash_username;

    DrawerLayout dash_drawer_layout;
    NavigationView dash_nav_view;

    TextView tv_nav_logout, tv_nav_settings;

    //==========================================ON CREATE===========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar dash_toolbar = findViewById(R.id.dash_toolbar);
        dash_drawer_layout = findViewById(R.id.dash_drawer_layout);
        dash_nav_view = findViewById(R.id.dash_nav_view);

        tv_dash_username = findViewById(R.id.tv_dash_username);
        tv_nav_logout = dash_nav_view.findViewById(R.id.tv_nav_logout);
        tv_nav_settings = dash_nav_view.findViewById(R.id.tv_nav_settings);

        if (dash_toolbar != null){
            dash_toolbar.setElevation(5);
            setSupportActionBar(dash_toolbar);
        }

        //--------------------------------------HOME BUTTON ON APP BAR------------------------------
        ActionBar actionBar = getSupportActionBar();

        if (actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        }
        //--------------------------------------HOME BUTTON ON APP BAR------------------------------

        safe_pref = getSharedPreferences(getResources().getString(R.string.safe_pref_name),MODE_PRIVATE);
        // if user is new show intro, else if not logged in show login screen
        if (isFirstRun()){
            //show account creation instructions
            toIntro();
        }else {

            if (!isUserLogged_in()){
                //go to login screen
                tologin();
            }

        }

        //click listener to logout
        tv_nav_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        //click listener to settings
        tv_nav_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSettings();
            }
        });

    }
    //==========================================ON CREATE===========================================

    //--------------------------------------OVERRIDE METHODS----------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (dash_drawer_layout.isDrawerOpen(GravityCompat.START)){
            dash_drawer_layout.closeDrawer(GravityCompat.START);
        }else {
            dash_drawer_layout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dash_drawer_layout.isDrawerOpen(GravityCompat.START)){
            dash_drawer_layout.closeDrawer(GravityCompat.START);
        }
    }

    //--------------------------------------OVERRIDE METHODS----------------------------------------

    //------------------------------------------DEFINED METHODS-------------------------------------
    //test if user is logged in
    boolean isUserLogged_in(){
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    //test if user is new user
    boolean isFirstRun(){
        return safe_pref.getBoolean(getResources().getString(R.string.first_run_prefkey),true);
    }

    //logout user
    void logout(){
        FirebaseAuth.getInstance().signOut();
        tologin();
    }
    //------------------------------------------DEFINED METHODS-------------------------------------

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=INTENTS-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    void toIntro(){
        Intent intro_intent = new Intent(getApplicationContext(), WelcomeActvity.class);
        intro_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intro_intent);
        super.finish();
    }

    void tologin(){
        Intent login_intent = new Intent(getApplicationContext(), LoginActivity.class);
        login_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(login_intent);
        super.finish();
    }

    void toSettings(){
        Intent settings_intent = new Intent(getApplicationContext(),SettingsActivity.class);
        startActivity(settings_intent);
    }
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=INTENTS-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-

}
