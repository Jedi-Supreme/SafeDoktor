package com.softedge.care_assist.activities;


import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.softedge.care_assist.api.CarewexCalls;
import com.softedge.care_assist.models.GlideApp;
import com.softedge.care_assist.models.fireModels.PatientPackage.Biography;
import com.softedge.care_assist.R;
import com.softedge.care_assist.adapters.Affiliates_adapter;
import com.softedge.care_assist.utilities.common_code;
import com.softedge.care_assist.utilities.init_code;

import java.lang.ref.WeakReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class OpdCardActivity extends AppCompatActivity {

    ListView lv_affiliates;
    TextView tv_opd_username, tv_opd_number;
    WeakReference<OpdCardActivity> weakOpd;
    ConstraintLayout const_opd_layout;

    Biography appUserbio;

    DrawerLayout opd_drawer_layout;
    NavigationView opd_nav_view;
    CircleImageView iv_opdnav_avatarpic;

    TextView
            tv_opd_logout, tv_opd_settings,
            tv_opd_fullname,
            tv_opdnav_usernumber, tv_opdnav_help, tv_opdnav_tos;

    String fireID;

    //==========================================ON CREATE===========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opd_card);

        //todo create drawer view
        weakOpd = new WeakReference<>(this);
        String[] pluscodes_arr = getResources().getStringArray(R.array.plusCodes_arr);

        opd_drawer_layout = findViewById(R.id.const_opdrawer_layout);
        opd_nav_view = findViewById(R.id.opd_nav_view);

        iv_opdnav_avatarpic = opd_nav_view.findViewById(R.id.dash_header_avatarpic);

        tv_opd_number = findViewById(R.id.tv_opd_number);
        tv_opd_username = findViewById(R.id.tv_opd_username);
        tv_opd_logout = opd_nav_view.findViewById(R.id.tv_nav_logout);
        tv_opd_settings = opd_nav_view.findViewById(R.id.tv_nav_settings);

        tv_opd_fullname = opd_nav_view.findViewById(R.id.dash_header_fullname);
        tv_opd_username = opd_nav_view.findViewById(R.id.dash_header_usernumber);
        tv_opdnav_help = opd_nav_view.findViewById(R.id.tv_nav_help);
        tv_opdnav_tos = opd_nav_view.findViewById(R.id.tv_nav_terms);

        lv_affiliates = findViewById(R.id.lv_opd_affiliates);

        //---------------------------------------get user id----------------------------------------
        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            fireID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }
        //---------------------------------------get user id----------------------------------------

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

        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            appUserbio = common_code.appuser(weakOpd.get());
        }

        //set list view adapter
        Affiliates_adapter affAdapter = new Affiliates_adapter(getApplicationContext()
                ,getResources().getStringArray(R.array.facilities_Arr));
        lv_affiliates.setAdapter(affAdapter);

        //list view item click listener
        lv_affiliates.setOnItemClickListener((parent, view, position, id) -> {
            String[] add_split = pluscodes_arr[position].split("-");
            common_code.getreq_Affiliate(add_split,weakOpd.get());

        });

    }
    //==========================================ON CREATE===========================================

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-DEFINED METHODS-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    //load data into navigation views
    public void loadLocal_data(){

        if (appUserbio != null){
            String username = appUserbio.getFirstname() + " " + appUserbio.getLastname();
            String usernumber = "+" + appUserbio.getCountry_code() + appUserbio.getMobile_number();

            tv_opd_username.setText(username);
            tv_opd_fullname.setText(username);
            tv_opd_number.setText(appUserbio.getOpd_Id());
            tv_opdnav_usernumber.setText(usernumber);

            if (!appUserbio.getPropic_url().isEmpty() || !appUserbio.getPropic_url().equals("")) {
                GlideApp.with(weakOpd.get())
                        .load(appUserbio.getPropic_url())
                        .into(iv_opdnav_avatarpic);
            }

        }







    }
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-DEFINED METHODS-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    //----===-==-=-===-==-===-==-===-===-====-===-===-OVERRIDE METHODS===-==-==--==--==-=-=--==--==-

    @Override
    protected void onResume() {
        super.onResume();
        //fetch token
        CarewexCalls.get_access_token(getApplicationContext());
    }

}
