package com.softedge.safedoktor.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;

import com.softedge.safedoktor.R;
import com.softedge.safedoktor.adapters.historyQns_Adapter;
import com.softedge.safedoktor.models.Historypackage.FamilyHistory;
import com.softedge.safedoktor.models.Historypackage.PersonalHistory;
import com.softedge.safedoktor.models.Historypackage.SocialHistory;
import com.softedge.safedoktor.models.Historypackage.SurgicalHistory;

import java.lang.ref.WeakReference;

public class MedicalHistoryActivity extends AppCompatActivity implements
        TabHost.OnTabChangeListener {

    TabHost history_tabhost;

    WeakReference<MedicalHistoryActivity> weakhistory;

    RecyclerView his_recycler;

    //==========================================ON CREATE===========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history);

        weakhistory = new WeakReference<>(this);

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

    }
    //==========================================ON CREATE===========================================

    //------------------------------------------DEFINED METHODS-------------------------------------
    void LoadByTag(String tag) {

        switch (tag) {

            case PersonalHistory.TABLE:
                String[] personalQns_arr = getResources().getStringArray(R.array.personal_qns);
                refreshRecycler(personalQns_arr);
                break;
            case FamilyHistory.TABLE:
                String[] familyQns_arr = getResources().getStringArray(R.array.family_qns);
                refreshRecycler(familyQns_arr);
                break;
            case SocialHistory.TABLE:
                String[] socialQns_arr = getResources().getStringArray(R.array.social_qns);
                refreshRecycler(socialQns_arr);
                break;
            case SurgicalHistory.TABLE:
                String[] surgicalQns_arr = getResources().getStringArray(R.array.surgical_qns);
                refreshRecycler(surgicalQns_arr);
                break;

        }
    }

    void refreshRecycler(String[] qns_arr) {

        historyQns_Adapter contactsAdapter = new historyQns_Adapter(weakhistory.get(), qns_arr);
        his_recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        his_recycler.setAdapter(contactsAdapter);

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

    }
    //-----------------------------------------BUTTON CLICK LISTENER--------------------------------
}
