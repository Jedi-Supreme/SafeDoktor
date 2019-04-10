package com.softedge.safedoktor.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.adapters.historyQns_Adapter;
import com.softedge.safedoktor.common_code;
import com.softedge.safedoktor.databases.SafeDB;
import com.softedge.safedoktor.models.fireModels.HistoryPackage.FamilyHistory;
import com.softedge.safedoktor.models.fireModels.HistoryPackage.History;
import com.softedge.safedoktor.models.fireModels.HistoryPackage.PersonalHistory;
import com.softedge.safedoktor.models.fireModels.HistoryPackage.SocialHistory;
import com.softedge.safedoktor.models.fireModels.HistoryPackage.SurgicalHistory;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MedicalHistoryActivity extends AppCompatActivity implements
        TabHost.OnTabChangeListener {

    TabHost history_tabhost;

    WeakReference<MedicalHistoryActivity> weakhistory;
    String fireid;
    Calendar calendar;
    SafeDB safe_db;

    RecyclerView his_recycler;

    //==========================================ON CREATE===========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history);

        weakhistory = new WeakReference<>(this);
        safe_db = new SafeDB(weakhistory.get(),null);

        //--------------------------------------HOME BUTTON ON APP BAR------------------------------
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //--------------------------------------HOME BUTTON ON APP BAR------------------------------

        history_tabhost = findViewById(R.id.history_tabhost);
        his_recycler = findViewById(R.id.tab_recycler);
        history_tabhost.setup();

        TabHost.TabSpec personalSpec = history_tabhost.newTabSpec(PersonalHistory.TABLE);
        TabHost.TabSpec familyspec = history_tabhost.newTabSpec(FamilyHistory.TABLE);
        TabHost.TabSpec socialSpec = history_tabhost.newTabSpec(SocialHistory.TABLE);
        TabHost.TabSpec surgerySpec = history_tabhost.newTabSpec(SurgicalHistory.TABLE);

        //set tab views
        personalSpec.setContent(R.id.tab_recycler);
        familyspec.setContent(R.id.tab_recycler);
        socialSpec.setContent(R.id.tab_recycler);
        surgerySpec.setContent(R.id.tab_recycler);

        //set tab titles
        personalSpec.setIndicator(PersonalHistory.TABLE);
        familyspec.setIndicator(FamilyHistory.TABLE);
        socialSpec.setIndicator(SocialHistory.TABLE);
        surgerySpec.setIndicator(SurgicalHistory.TABLE);

        history_tabhost.addTab(personalSpec);
        history_tabhost.addTab(familyspec);
        history_tabhost.addTab(socialSpec);
        history_tabhost.addTab(surgerySpec);

        history_tabhost.setOnTabChangedListener(this);

        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            fireid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        calendar = Calendar.getInstance();

    }
    //==========================================ON CREATE===========================================

    //------------------------------------------DEFINED METHODS-------------------------------------
    void LoadByTag(String tag) {

        ArrayList<History> historiesArraylist;

        switch (tag) {

            case PersonalHistory.TABLE:

                safe_db.createHistoryTable(PersonalHistory.TABLE,fireid);
                historiesArraylist = safe_db.fetchAllHistory(tag,fireid);
                String[] personalQns_arr = getResources().getStringArray(R.array.personal_qns);

                if (historiesArraylist != null && historiesArraylist.size() > 0){
                    refreshRecycler(personalQns_arr,historiesArraylist);
                }else {
                    refreshRecycler(personalQns_arr,makeHistoryQns(personalQns_arr));
                }

                break;

            case FamilyHistory.TABLE:

                safe_db.createHistoryTable(FamilyHistory.TABLE,fireid);
                String[] familyQns_arr = getResources().getStringArray(R.array.family_qns);
                historiesArraylist = safe_db.fetchAllHistory(tag,fireid);

                if (historiesArraylist != null && historiesArraylist.size() > 0){
                    refreshRecycler(familyQns_arr,historiesArraylist);
                }else {
                    refreshRecycler(familyQns_arr,makeHistoryQns(familyQns_arr));
                }
                break;

            case SocialHistory.TABLE:

                safe_db.createHistoryTable(SocialHistory.TABLE,fireid);
                String[] socialQns_arr = getResources().getStringArray(R.array.social_qns);
                historiesArraylist = safe_db.fetchAllHistory(tag,fireid);

                if (historiesArraylist != null && historiesArraylist.size() > 0){
                    refreshRecycler(socialQns_arr,historiesArraylist);
                }else {
                    refreshRecycler(socialQns_arr,makeHistoryQns(socialQns_arr));
                }
                break;

            case SurgicalHistory.TABLE:

                safe_db.createHistoryTable(SurgicalHistory.TABLE,fireid);
                String[] surgicalQns_arr = getResources().getStringArray(R.array.surgical_qns);
                historiesArraylist = safe_db.fetchAllHistory(tag,fireid);

                if (historiesArraylist != null && historiesArraylist.size() > 0){
                    refreshRecycler(surgicalQns_arr,historiesArraylist);
                }else {
                    refreshRecycler(surgicalQns_arr,makeHistoryQns(surgicalQns_arr));
                }
                break;

        }
    }

    void refreshRecycler(String[] qns_arr, ArrayList<History> histories) {

        historyQns_Adapter contactsAdapter = new historyQns_Adapter(qns_arr,histories);
        his_recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        his_recycler.setAdapter(contactsAdapter);

    }

    ArrayList<History> makeHistoryQns(String[] qns_arr){

        ArrayList<History> histories = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat(common_code.dateTimeformat, Locale.getDefault());

        String date = dateFormat.format(calendar.getTimeInMillis());

        for (int x = 0; x < qns_arr.length; x++){
            History history = new History(fireid,String.valueOf(false),"",x,date);
            histories.add(history);
        }

        return histories;
    }

    public void saveData(ArrayList<History> histories){

        for (History history : histories){
            safe_db.addMedicalHistory(history_tabhost.getCurrentTabTag(),history);
        }

    }
    //------------------------------------------DEFINED METHODS-------------------------------------


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

    @Override
    public void onTabChanged(String tabId) {
        //on tab changed update recycler view adapter with appropriate questions
        LoadByTag(tabId);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (history_tabhost.getCurrentTabTag() != null) {
            LoadByTag(history_tabhost.getCurrentTabTag());
        }

    }
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-OVERRIDE METHODS-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=



    //-----------------------------------------BUTTON CLICK LISTENER--------------------------------
    public void Save_history_data(View view) {

        try {
            saveData(((historyQns_Adapter) his_recycler.getAdapter()).getHistories());
            common_code.Mysnackbar(
                    findViewById(R.id.const_medhistory_layout),
                    history_tabhost.getCurrentTabTag() + " HISTORY SAVED", Snackbar.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }

    }
    //-----------------------------------------BUTTON CLICK LISTENER--------------------------------
}
