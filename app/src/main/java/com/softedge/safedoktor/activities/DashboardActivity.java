package com.softedge.safedoktor.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.databases.SafeDB;
import com.softedge.safedoktor.models.GlideApp;
import com.softedge.safedoktor.models.PatientPackage.Biography;
import com.softedge.safedoktor.models.PatientPackage.ContactPerson;

import java.lang.ref.WeakReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class DashboardActivity extends AppCompatActivity {

    SharedPreferences safe_pref;

    DrawerLayout dash_drawer_layout;
    NavigationView dash_nav_view;

    TextView tv_nav_logout, tv_nav_settings, tv_nav_fullname, tv_dash_username, tv_dash_usernumber;
    CircleImageView iv_nav_avatarpic;

    WeakReference<DashboardActivity> weakDash;
    String fireID;
    SafeDB safe_db;

    //==========================================ON CREATE===========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        weakDash = new WeakReference<>(this);
        safe_db = new SafeDB(weakDash.get(),null);

        Toolbar dash_toolbar = findViewById(R.id.dash_toolbar);
        dash_drawer_layout = findViewById(R.id.dash_drawer_layout);
        dash_nav_view = findViewById(R.id.dash_nav_view);

        tv_dash_username = findViewById(R.id.tv_dash_username);
        tv_nav_logout = dash_nav_view.findViewById(R.id.tv_nav_logout);
        tv_nav_settings = dash_nav_view.findViewById(R.id.tv_nav_settings);
        tv_nav_fullname = dash_nav_view.findViewById(R.id.dash_header_fullname);
        tv_dash_usernumber = dash_nav_view.findViewById(R.id.dash_header_usernumber);

        iv_nav_avatarpic = dash_nav_view.findViewById(R.id.dash_header_avatarpic);

        if (dash_toolbar != null){
            dash_toolbar.setElevation(5);
            setSupportActionBar(dash_toolbar);
        }

        //---------------------------------------get user id----------------------------------------
        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            fireID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }
        //---------------------------------------get user id----------------------------------------

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
            }else {
                try {
                    loadLocal_data();
                }catch (Exception ignored){
                    loadBioData_online();
                }
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

        loadBioData_online();

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

    //--------------------------------------OVERRIDE METHODS----------------------------------------

    //------------------------------------------DEFINED METHODS-------------------------------------
    //test if user is logged in
    boolean isUserLogged_in(){
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    void loadBioData_online(){

        String all_users = getResources().getString(R.string.all_users);
        String biography = getResources().getString(R.string.bio_ref);
        String contacts = getResources().getString(R.string.contacts_ref);

        safe_db = new SafeDB(weakDash.get(),null);

        final DatabaseReference bio_ref = FirebaseDatabase.getInstance().getReference(all_users).child(biography);
        final DatabaseReference contacts_ref = FirebaseDatabase.getInstance().getReference(all_users).child(contacts);

        bio_ref.child(fireID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Biography userBio = dataSnapshot.getValue(Biography.class);

                if (userBio != null){

                    try {
                        safe_db.addPat_bio(userBio);
                    }catch (Exception ignored){
                        safe_db.updatePat_bio(userBio);
                    }
                }

                bio_ref.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        contacts_ref.child(fireID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot singlecontact : dataSnapshot.getChildren()){

                    ContactPerson contactPerson = singlecontact.getValue(ContactPerson.class);

                    if (contactPerson!= null){
                        try {
                            safe_db.addContact(contactPerson);
                        }catch (Exception ignored){
                            safe_db.updateContact(contactPerson);
                        }
                    }
                }


                contacts_ref.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //loadLocal_data();

    }

    public void loadLocal_data(){

        Biography app_userBio = fetchfromLocalDB();

        String username = app_userBio.getFirstname() + " " + app_userBio.getLastname();
        String usernumber = "+" + app_userBio.getCountry_code() + app_userBio.getMobile_number();

        tv_dash_username.setText(username);
        tv_nav_fullname.setText(username);
        tv_dash_usernumber.setText(usernumber);

        GlideApp.with(weakDash.get())
                .load(app_userBio.getPropic_url())
                .into(iv_nav_avatarpic);

    }

    //test if user is new user
    boolean isFirstRun(){
        return safe_pref.getBoolean(getResources().getString(R.string.first_run_prefkey),true);
    }

    Biography fetchfromLocalDB(){
        return safe_db.local_appUser(fireID);
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
