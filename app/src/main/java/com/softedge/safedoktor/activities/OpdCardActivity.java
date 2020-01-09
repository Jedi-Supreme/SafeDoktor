package com.softedge.safedoktor.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.softedge.safedoktor.api.CarewexCalls;
import com.softedge.safedoktor.models.GlideApp;
import com.softedge.safedoktor.models.fireModels.PatientPackage.Biography;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.adapters.Affiliates_adapter;
import com.softedge.safedoktor.models.fireModels.PatientPackage.Physicals;
import com.softedge.safedoktor.models.retrofitModels.retroPatient;
import com.softedge.safedoktor.utilities.common_code;
import com.softedge.safedoktor.utilities.init_code;

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

    Boolean isEmailDialogDisabled = false; //check box off

    TextView
            tv_opd_logout, tv_opd_settings,
            tv_opd_fullname, tv_opd_mobNumb,
            tv_opdnav_help, tv_opdnav_tos,
            tv_promo, tv_header_bg, tv_header_bmi;

    String fireID;

    SharedPreferences appPref;

    //==========================================ON CREATE===========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opd_card);

        weakOpd = new WeakReference<>(this);
        String[] pluscodes_arr = getResources().getStringArray(R.array.plusCodes_arr);

        appPref = common_code.appPref(weakOpd.get());

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
        String bloodgroup = promoPref.getString(Physicals.BLOOD_GROUP,"");

        //-------------------------------SHOW BLOOD GROUP----------------------------------------
        if (!bloodgroup.equals("")){
            tv_header_bg.setText(bloodgroup);
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
        int position = appPref.getInt(retroPatient.REGISTRATION_FACILITY,0);
        CarewexCalls.get_access_token(getApplicationContext(),common_code.Build_Employee(position));

        if (opd_drawer_layout.isDrawerOpen(GravityCompat.START)){
            opd_drawer_layout.closeDrawer(GravityCompat.START);
        }

        init_code.loadBioData_online(weakOpd.get(),fireID);
        init_code.saveDeviceToken();

        try{
            loadLocal_data();
        }catch (Exception ignored){}

        SharedPreferences appPref = common_code.appPref(weakOpd.get());
        SharedPreferences.Editor prefEditor = appPref.edit();

        //--------------------------Check email verification and remind user------------------------
        if (
                FirebaseAuth.getInstance().getCurrentUser() != null && // user is not null
                FirebaseAuth.getInstance().getCurrentUser().getEmail() != null && // user email is not null
                FirebaseAuth.getInstance().getCurrentUser().getEmail().contains(getResources().getString(R.string.default_email_suffix))
        ){

            //Toast.makeText(getApplicationContext(), FirebaseAuth.getInstance().getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();

            AlertDialog emailDiag = new AlertDialog.Builder(weakOpd.get()).create();

            View view_email_verify = LayoutInflater.from(weakOpd.get()).inflate(R.layout.diag_email_verify,opd_drawer_layout,false);

            TextView tv_diag_close = view_email_verify.findViewById(R.id.tv_diag_close);
            CheckBox checkbox = view_email_verify.findViewById(R.id.checkbox_verifydiag);
            ContentLoadingProgressBar probar_verifydiag = view_email_verify.findViewById(R.id.probar_verifydiag);
            TextInputEditText et_diag_email = view_email_verify.findViewById(R.id.et_verifydiag_email);
            Button bt_accept = view_email_verify.findViewById(R.id.bt_verifydiag_accept);
            Button bt_later = view_email_verify.findViewById(R.id.bt_verifydiag_later);

            checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                isEmailDialogDisabled = isChecked;
                prefEditor.putBoolean(common_code.EMAIL_REMINDER_KEY, isChecked);
                prefEditor.apply();
            });

            View.OnClickListener email_diag_listener = v -> {

                switch (v.getId()){

                    case R.id.tv_diag_close:
                    case R.id.bt_verifydiag_later:
                        emailDiag.dismiss();
                        break;

                    case R.id.bt_verifydiag_accept:

                        Intent profile_intent = new Intent(weakOpd.get(), ProfileActivity.class);
                        profile_intent.putExtra("action",common_code.EMAIL_ACTION);
                        startActivity(profile_intent);
                        emailDiag.dismiss();
                        /*if (et_diag_email.getText() != null){

                            if(!et_diag_email.getText().toString().isEmpty() &&
                                    (et_diag_email.getText().toString().contains("@") && et_diag_email.getText().toString().contains("."))){
                                //TODO intent to open account settings

                                //probar_verifydiag.setVisibility(View.VISIBLE);
                                common_code.accountEmailAvailability(et_diag_email.getText().toString(),emailDiag);
                                //FirebaseAuth.getInstance().getCurrentUser().updateEmail(et_diag_email.getText().toString());
                            }

                        }*/
                        break;
                }

            };

            tv_diag_close.setOnClickListener(email_diag_listener);
            bt_accept.setOnClickListener(email_diag_listener);
            bt_later.setOnClickListener(email_diag_listener);

            emailDiag.setView(view_email_verify);

            final long WAIT = 10000;
            final long SECS = 1000;

            //Wait 10 secs before showing dialog
            //Check if reminder is turned off

            if (!appPref.getBoolean(common_code.EMAIL_REMINDER_KEY,false)){
                new CountDownTimer(WAIT,SECS) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        if (!emailDiag.isShowing()){
                            emailDiag.show();
                        }
                    }
                }.start();

            }

        }
        //--------------------------Check email verification and remind user------------------------
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
        SharedPreferences app_pref = common_code.appPref(weakOpd.get());
        String token = app_pref.getString("access_token",null);
        String employeeId = app_pref.getString("employeeID",null);

        Toast.makeText(getApplicationContext(), " token: "  + " emp: " + employeeId, Toast.LENGTH_LONG).show();
        //Intent old_dash_intent = new Intent(getApplicationContext(), DashboardActivity.class);
       // startActivity(old_dash_intent);
    }
    //----===-==-=-===-==-===-==-===-===-====-===-===-OVERRIDE METHODS===-==-==--==--==-=-=--==--==-



}
