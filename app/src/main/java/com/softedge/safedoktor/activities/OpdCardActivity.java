package com.softedge.safedoktor.activities;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.adapters.Affiliates_adapter;
import com.softedge.safedoktor.common_code;
import com.softedge.safedoktor.databases.SafeDB;
import com.softedge.safedoktor.models.fireModels.PatientPackage.Biography;

import java.lang.ref.WeakReference;

public class OpdCardActivity extends AppCompatActivity {

    ListView lv_affiliates;
    TextView tv_opd_username, tv_opd_number, tv_promo;
    WeakReference<OpdCardActivity> weakOpd;
    ConstraintLayout const_opd_layout;

    SafeDB safedb;
    Biography appUserbio;

    //==========================================ON CREATE===========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opd_card);

        weakOpd = new WeakReference<>(this);
        safedb = new SafeDB(weakOpd.get(),null);

        const_opd_layout = findViewById(R.id.const_opd_layout);
        tv_opd_number = findViewById(R.id.tv_opd_number);
        tv_opd_username = findViewById(R.id.tv_opd_username);

        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            appUserbio = safedb.local_appUser(FirebaseAuth.getInstance().getCurrentUser().getUid());
        }

        if (appUserbio != null){
            String username = appUserbio.getFirstname() + " " + appUserbio.getLastname();
            tv_opd_username.setText(username);
        }
    }
    //==========================================ON CREATE===========================================

    //---------------------------------------------------ONCLICK LISTENER---------------------------
    public void direction_services(View view) {

        switch (view.getId()){

            case R.id.bt_opd_directions:
               common_code.getNearest_Affiliate(weakOpd.get());
                break;

            case R.id.et_opd_list:
                show_affiliates_diag();
                break;
        }
    }
    //---------------------------------------------------ONCLICK LISTENER---------------------------

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-DEFINED METHODS-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    void show_affiliates_diag(){

        String[] pluscodes_arr = getResources().getStringArray(R.array.plusCodes_arr);

        AlertDialog aff_dialog = new AlertDialog.Builder(weakOpd.get()).create();

        View aff_view = LayoutInflater.from(weakOpd.get()).inflate(R.layout.diag_affiliates_pick,const_opd_layout,false);
        aff_dialog.setView(aff_view);
        aff_dialog.setCancelable(true);

        lv_affiliates = aff_view.findViewById(R.id.lv_opd_affiliates);

        Affiliates_adapter affAdapter = new Affiliates_adapter(getApplicationContext()
                ,getResources().getStringArray(R.array.facilities_Arr));

        lv_affiliates.setAdapter(affAdapter);
        lv_affiliates.setOnItemClickListener((parent, view, position, id) -> {
            aff_dialog.dismiss();
            String[] add_split = pluscodes_arr[position].split("-");
            common_code.getreq_Affiliate(add_split,weakOpd.get());

        });

        aff_dialog.show();

    }
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-DEFINED METHODS-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
}
