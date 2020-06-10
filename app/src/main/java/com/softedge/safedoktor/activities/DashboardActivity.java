package com.softedge.safedoktor.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.google.android.material.navigation.NavigationView;
import com.softedge.safedoktor.api.SwaggerCalls;
import com.softedge.safedoktor.databases.appDB;
import com.softedge.safedoktor.models.fireModels.PatientPackage.Biography;
import com.softedge.safedoktor.models.retrofitModels.retro_patSearch;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.models.swaggerModels.PatientModel;
import com.softedge.safedoktor.models.swaggerModels.body.Specialties;
import com.softedge.safedoktor.utilities.*;
import com.softedge.safedoktor.utilities.common_code;
import com.softedge.safedoktor.fragments.chats_fragment;
import com.softedge.safedoktor.fragments.library_fragment;
import com.softedge.safedoktor.fragments.partners_fragment;
import com.softedge.safedoktor.utilities.init_code;

import java.lang.ref.WeakReference;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DashboardActivity extends AppCompatActivity implements
        TabHost.OnTabChangeListener{

    DrawerLayout dash_drawer_layout;
    NavigationView dash_nav_view;

    TabHost dash_tabhost;

    final static String lib = "Library";
    final static String partner = "Partners";
    final static String chatroom = "chatroom";
    final static String home = "Home";

    TextView
            tv_nav_logout, tv_nav_settings,
            tv_nav_fullname, tv_dash_username,
            tv_dash_usernumber, tv_nav_help, tv_dash_tos,
            tv_appointment_count;

    CircleImageView iv_nav_avatarpic;

    WeakReference<DashboardActivity> weakDash;
    int safeDoktorID;
    //SafeDB safe_db;

    ActionBar actionBar;

    Biography app_userBio;
    PatientModel patient;
    appDB app_Db;
    SharedPreferences appPref;

    //==========================================ON CREATE===========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Todo work on profile pic sync and load

        weakDash = new WeakReference<>(this);
        //safe_db = new SafeDB(weakDash.get(),null);

        appPref = common_code.appPref(weakDash.get());
        safeDoktorID = appPref.getInt(AppConstants.KEY_PATIENT_ID,0);
        app_Db = appDB.getInstance(weakDash.get());

        Toolbar dash_toolbar = findViewById(R.id.dash_toolbar);
        dash_drawer_layout = findViewById(R.id.dash_drawer_layout);
        dash_nav_view = findViewById(R.id.dash_nav_view);

        tv_appointment_count = findViewById(R.id.tv_app_count);

        dash_tabhost = findViewById(R.id.dash_tabhost);
        dash_tabhost.setup();

        patient = common_code.currentUser(weakDash.get());
//        common_code.getDBExecutor().execute(()-> patient = app_Db.safeDoktorAccessObj().getpatient(safeDoktorID));

        //------------------------------------DASHBOARD TABS----------------------------------------

        TabHost.TabSpec homeSpec = dash_tabhost.newTabSpec(home);
        TabHost.TabSpec libSpec = dash_tabhost.newTabSpec(lib);
        TabHost.TabSpec chatSpec = dash_tabhost.newTabSpec(chatroom);
        TabHost.TabSpec partnerSpec = dash_tabhost.newTabSpec(partner);

        homeSpec.setIndicator("",getDrawable(R.drawable.home));
        libSpec.setIndicator("",getDrawable(R.drawable.book));
        chatSpec.setIndicator("",getDrawable(R.drawable.dialog));
        partnerSpec.setIndicator("",getDrawable(R.drawable.users));

        homeSpec.setContent(R.id.const_dash_content);
        libSpec.setContent(R.id.dash_frag_holder);
        partnerSpec.setContent(R.id.dash_frag_holder);
        chatSpec.setContent(R.id.dash_frag_holder);

        dash_tabhost.addTab(homeSpec);
        dash_tabhost.addTab(libSpec);
        dash_tabhost.addTab(chatSpec);
        dash_tabhost.addTab(partnerSpec);

        dash_tabhost.setOnTabChangedListener(this);
        //------------------------------------DASHBOARD TABS----------------------------------------

        tv_dash_username = findViewById(R.id.tv_dash_username);
        tv_nav_logout = dash_nav_view.findViewById(R.id.tv_nav_logout);
        tv_nav_settings = dash_nav_view.findViewById(R.id.tv_nav_settings);
        tv_nav_fullname = dash_nav_view.findViewById(R.id.dash_header_fullname);
        tv_dash_usernumber = dash_nav_view.findViewById(R.id.dash_header_usernumber);
        tv_nav_help = dash_nav_view.findViewById(R.id.tv_nav_help);
        tv_dash_tos = dash_nav_view.findViewById(R.id.tv_nav_terms);

        iv_nav_avatarpic = dash_nav_view.findViewById(R.id.dash_header_avatarpic);

        if (dash_toolbar != null){
            dash_toolbar.setElevation(5);
            setSupportActionBar(dash_toolbar);
        }

        //---------------------------------------get user id----------------------------------------
//        if (FirebaseAuth.getInstance().getCurrentUser() != null){
//            fireID = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        }
        //---------------------------------------get user id----------------------------------------

        //--------------------------------------HOME BUTTON ON APP BAR------------------------------
        actionBar = getSupportActionBar();

        if (actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        }
        //--------------------------------------HOME BUTTON ON APP BAR------------------------------

        // if user is new show intro, else if not logged in show login screen
//        if (common_code.isFirstRun(weakDash.get())){
//            //show account creation instructions
//            common_code.toIntro(weakDash.get());
//        }else {
//
//            if (common_code.isUserLogged_in()){
//                //go to login screen
//                init_code.logout(weakDash.get());
//            }else {
//
//                try {
//                    loadLocal_data();
//                }catch (Exception ignored){
//                    init_code.loadBioData_online(weakDash.get(),fireID);
//                }
//            }
//
//        }

        //click listener to logout
        tv_nav_logout.setOnClickListener(v -> init_code.logout(weakDash.get()));

        //click listener to settings
        tv_nav_settings.setOnClickListener(v -> common_code.toSettings(weakDash.get()));

        //click listener to terms of service
        tv_dash_tos.setOnClickListener(v -> common_code.toTOS(weakDash.get()));

        //Calendar calendar = Calendar.getInstance();

        //SimpleDateFormat dateFormat = new SimpleDateFormat(common_code.dateTimeformat, Locale.getDefault());

        //String date = dateFormat.format(calendar.getTimeInMillis());

        //common_code.Mysnackbar(findViewById(R.id.dash_drawer_layout),date, Snackbar.LENGTH_INDEFINITE).show();

        //app_userBio = common_code.appuser(weakDash.get());
    }
    //==========================================ON CREATE===========================================

    //--------------------------------------OVERRIDE METHODS----------------------------------------
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

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

        //TODO fetch token
        //CarewexCalls.get_access_token(getApplicationContext());

        if (dash_drawer_layout.isDrawerOpen(GravityCompat.START)){
            dash_drawer_layout.closeDrawer(GravityCompat.START);
        }

        //init_code.loadBioData_online(weakDash.get(),fireID);
        //init_code.saveDeviceToken();

        try{
            loadLocal_data();
        }catch (Exception e){
            Log.e("dashboard",e.toString());
        }

    }

    @Override
    public void onBackPressed() {

        Intent startmain = new Intent(Intent.ACTION_MAIN);
        startmain.addCategory(Intent.CATEGORY_HOME);
        startmain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startmain);

    }

    @Override
    public void onTabChanged(String tabId) {

        switch (tabId){

            case home:

                if (actionBar != null){
                    actionBar.setTitle(getString(R.string.app_name));
                }
                break;

            case lib:
                toLibrary();
                break;

            case partner:
                toPartners();
                break;

            case chatroom:
                toChatroom();
                break;

        }

    }

    //--------------------------------------OVERRIDE METHODS----------------------------------------

    //------------------------------------------DEFINED METHODS-------------------------------------

    //load data into navigation views
    public void loadLocal_data(){

//        String username = app_userBio.getFirstname() + " " + app_userBio.getLastname();
        String username = patient.getFirstname() + " " + patient.getLastname();
//        String usernumber = "+" + app_userBio.getCountry_code() + app_userBio.getMobile_number();
        String usernumber = patient.getPhonenumber();
        int appointmentCount;

//
//        tv_dash_username.setText(username);
        tv_dash_username.setText(username);
//        tv_nav_fullname.setText(username);
        tv_nav_fullname.setText(username);
//        tv_dash_usernumber.setText(usernumber);
        tv_dash_usernumber.setText(usernumber);

        SwaggerCalls.loadAppointments(dash_drawer_layout);
        SwaggerCalls.getSpecialties(dash_drawer_layout);

        app_Db.safeDoktorAccessObj().appointments(patient.getPatientid())
                .observe(weakDash.get(), appointments -> tv_appointment_count.setText(String.valueOf(appointments.size())));

        app_Db.safeDoktorAccessObj().getAllSpecialties().observe(weakDash.get(), specialties -> {

            for (Specialties spec : specialties){
                SwaggerCalls.getSpecialtyDoctors(dash_drawer_layout,spec);
            }
        });

        //SwaggerCalls.getUserAccounts(dash_drawer_layout);

//
//        if (!app_userBio.getPropic_url().isEmpty() || !app_userBio.getPropic_url().equals("")) {
//            GlideApp.with(weakDash.get())
//                    .load(app_userBio.getPropic_url())
//                    .into(iv_nav_avatarpic);
//        }

    }

    private void findpatient(){
        String[] genderArr = getResources().getStringArray(R.array.gender_Arr);
        retro_patSearch searchObj = new retro_patSearch(
                app_userBio.getFirstname(),
                app_userBio.getLastname(),
                "",
                genderArr[app_userBio.getGender()]
                );

        //CarewexCalls.getPatientsResult(searchObj,weakDash.get(),);
    }
    //------------------------------------------DEFINED METHODS-------------------------------------

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=INTENTS-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-

    void toLibrary(){

        if (actionBar != null){
            actionBar.setTitle("Health Info");
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.dash_frag_holder, new library_fragment());
        ft.commit();
    }

    void toChatroom() {
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.chat));
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.dash_frag_holder, new chats_fragment());
        ft.commit();
    }

    void toPartners(){
        if (actionBar != null){
            actionBar.setTitle(partner);
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.dash_frag_holder, new partners_fragment());
        ft.commit();
    }
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=INTENTS-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-

    //-------------------------------------------CLICK LISTENERS------------------------------------
    public void dashView_clicked(View view) {

        switch (view.getId()){

            case R.id.const_appt_body:
                common_code.toAppointment(weakDash.get());
                break;

            case R.id.const_ov_body:
                //findpatient();
                break;

            case R.id.const_cv_body:
                common_code.toOpdCard(weakDash.get());
                break;

            case R.id.const_rmds_body:
                break;
        }

    }
    //-------------------------------------------CLICK LISTENERS------------------------------------

}
