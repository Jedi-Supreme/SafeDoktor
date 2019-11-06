package com.softedge.care_assist.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.softedge.care_assist.api.CarewexCalls;
import com.softedge.care_assist.models.GlideApp;
import com.softedge.care_assist.models.fireModels.PatientPackage.Biography;
import com.softedge.care_assist.R;
import com.softedge.care_assist.adapters.Affiliates_adapter;
import com.softedge.care_assist.models.fireModels.PatientPackage.Physicals;
import com.softedge.care_assist.utilities.common_code;
import com.softedge.care_assist.utilities.init_code;

import java.lang.ref.WeakReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class OpdCardActivity extends AppCompatActivity {

    ListView lv_affiliates;
    TextView tv_opd_username, tv_opd_number;
    WeakReference<OpdCardActivity> weakOpd;

    Biography appUserbio;

    DrawerLayout opd_drawer_layout;
    NavigationView opd_nav_view;
    CircleImageView iv_opdnav_avatarpic;

    TextView
            tv_opd_logout, tv_opd_settings,
            tv_opd_fullname, tv_opd_mobNumb,
            tv_opdnav_help, tv_opdnav_tos,
            tv_promo, tv_header_bg, tv_header_bmi;

    String fireID;

    //==========================================ON CREATE===========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opd_card);

        weakOpd = new WeakReference<>(this);
        String[] pluscodes_arr = getResources().getStringArray(R.array.plusCodes_arr);

        Toolbar opd_toolbar = findViewById(R.id.opd_toolbar);
        opd_drawer_layout = findViewById(R.id.const_opdrawer_layout);
        opd_nav_view = findViewById(R.id.opd_nav_view);

        iv_opdnav_avatarpic = opd_nav_view.findViewById(R.id.dash_header_avatarpic);

        tv_opd_number = findViewById(R.id.tv_opd_number);
        tv_opd_username = findViewById(R.id.tv_opd_username);
        tv_opd_logout = opd_nav_view.findViewById(R.id.tv_opdnav_logout);
        tv_opd_settings = opd_nav_view.findViewById(R.id.tv_opd_settings);

        tv_header_bg = opd_nav_view.findViewById(R.id.tv_header_bg);
        tv_header_bmi = opd_nav_view.findViewById(R.id.tv_header_bmi);

        tv_promo = findViewById(R.id.tv_promo);

        tv_opd_fullname = opd_nav_view.findViewById(R.id.dash_header_fullname);
        tv_opd_mobNumb = opd_nav_view.findViewById(R.id.dash_header_usernumber);
        tv_opdnav_help = opd_nav_view.findViewById(R.id.tv_nav_help);
        tv_opdnav_tos = opd_nav_view.findViewById(R.id.tv_opdnav_terms);

        if (opd_toolbar != null){
            opd_toolbar.setElevation(5);
            setSupportActionBar(opd_toolbar);
        }

        lv_affiliates = findViewById(R.id.lv_opd_affiliates);

        //---------------------------------------get user id----------------------------------------
        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            fireID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }
        //---------------------------------------get user id----------------------------------------

        //--------------------------------------HOME BUTTON ON APP BAR------------------------------
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        }
        //--------------------------------------HOME BUTTON ON APP BAR------------------------------

        // if user is new show intro, else if not logged in show login screen
        if (common_code.isFirstRun(weakOpd.get())){
            //show account creation instructions
            common_code.toIntro(weakOpd.get());
        }else {

            if (common_code.isUserLogged_in()){
                //go to login screen
                init_code.logout(weakOpd.get());
            }else {

                try {
                    loadLocal_data();
                }catch (Exception ignored){
                    init_code.loadBioData_online(weakOpd.get(),fireID);
                }
            }

        }

        //set list view adapter
        Affiliates_adapter affAdapter = new Affiliates_adapter(getApplicationContext()
                ,getResources().getStringArray(R.array.fac_map_Arr));
        lv_affiliates.setAdapter(affAdapter);

        //list view item click listener
        lv_affiliates.setOnItemClickListener((parent, view, position, id) -> {
            String[] add_split = pluscodes_arr[position].split("-");
            common_code.getreq_Affiliate(add_split,weakOpd.get());

        });

        tv_opd_settings.setOnClickListener(v -> common_code.toProfile(weakOpd.get()));
        tv_opdnav_tos.setOnClickListener(v -> common_code.toTOS(weakOpd.get()));
        tv_opd_logout.setOnClickListener(v -> logout());
    }
    //==========================================ON CREATE===========================================

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-DEFINED METHODS-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    //load data into navigation views
    public void loadLocal_data(){

        SharedPreferences promoPref = common_code.appPref(weakOpd.get());
        String promotion = promoPref.getString("promo","");

        int height = promoPref.getInt(Physicals.HEIGHT,0);
        int weight = promoPref.getInt(Physicals.WEIGHT,0);
        String bg = promoPref.getString(Physicals.BLOOD_GROUP,"");

        //-------------------------------SHOW BLOOD GROUP----------------------------------------
        if (!bg.equals("")){
            tv_header_bg.setText(bg);
            tv_header_bg.setVisibility(View.VISIBLE);
        }else {
            tv_header_bg.setVisibility(View.GONE);
        }
        //-------------------------------SHOW BLOOD GROUP----------------------------------------

        //-------------------------------SHOW BMI----------------------------------------
        if (height != 0 && weight != 0){
            double x = height/100.0;
            double bmi = weight/Math.pow(x,2);


            String bmi_txt = getResources().getString(R.string.bmi_lbl) + " " + Math.round(bmi);
            tv_header_bmi.setText(bmi_txt);

            tv_header_bmi.setVisibility(View.VISIBLE);
        }else {
            tv_header_bmi.setVisibility(View.GONE);
        }
        //-------------------------------SHOW BMI----------------------------------------

        tv_promo.setText(promotion);

        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            appUserbio = common_code.appuser(weakOpd.get());
        }



        if (appUserbio != null){
            String username = appUserbio.getFirstname() + " " + appUserbio.getLastname();
            String usernumber = "+" + appUserbio.getCountry_code() + appUserbio.getMobile_number();

            tv_opd_username.setText(username);
            tv_opd_fullname.setText(username);
            tv_opd_mobNumb.setText(usernumber);
            tv_opd_number.setText(appUserbio.getOpd_Id());


            if (!appUserbio.getPropic_url().isEmpty() || !appUserbio.getPropic_url().equals("")) {
                GlideApp.with(weakOpd.get())
                        .load(appUserbio.getPropic_url())
                        .into(iv_opdnav_avatarpic);
            }

        }else {
            Toast.makeText(getApplicationContext(),"App user empty",Toast.LENGTH_SHORT).show();
        }







    }

    //logout user
    void logout(){
        FirebaseAuth.getInstance().signOut();
        tologin();
    }

    void tologin(){
        Intent login_intent = new Intent(getApplicationContext(), LoginActivity.class);
        login_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(login_intent);
        super.finish();
    }
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-DEFINED METHODS-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    //----===-==-=-===-==-===-==-===-===-====-===-===-OVERRIDE METHODS===-==-==--==--==-=-=--==--==-
    @Override
    protected void onResume() {
        super.onResume();
        //fetch token
        CarewexCalls.get_access_token(getApplicationContext());

        if (opd_drawer_layout.isDrawerOpen(GravityCompat.START)){
            opd_drawer_layout.closeDrawer(GravityCompat.START);
        }

        init_code.loadBioData_online(weakOpd.get(),fireID);
        init_code.saveDeviceToken();

        try{
            loadLocal_data();
        }catch (Exception ignored){}
    }

    @Override
    public void onBackPressed() {

        Intent startmain = new Intent(Intent.ACTION_MAIN);
        startmain.addCategory(Intent.CATEGORY_HOME);
        startmain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startmain);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (opd_drawer_layout.isDrawerOpen(GravityCompat.START)){
            opd_drawer_layout.closeDrawer(GravityCompat.START);
        }else {
            opd_drawer_layout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    public void test_OldDash(View view) {
        Intent old_dash_intent = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(old_dash_intent);
    }
    //----===-==-=-===-==-===-==-===-===-====-===-===-OVERRIDE METHODS===-==-==--==--==-=-=--==--==-



}
