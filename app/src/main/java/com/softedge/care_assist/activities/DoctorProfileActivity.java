package com.softedge.care_assist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.softedge.care_assist.R;

public class DoctorProfileActivity extends AppCompatActivity {

    //========================================ON CREATE=============================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        //--------------------------------------HOME BUTTON ON APP BAR------------------------------
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //--------------------------------------HOME BUTTON ON APP BAR------------------------------

    }
    //========================================ON CREATE=============================================

    //--------------------------------------------BUTTON CLICK LISTENERS----------------------------
    public void ReviewDoc(View view) {
        toReview();
    }

    public void VideoChatDoc(View view) {

    }

    public void TextChatDoc(View view) {

    }

    public void VoiceCallDoc(View view) {

    }
    //--------------------------------------------BUTTON CLICK LISTENERS----------------------------

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-INTENTS--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    void toReview(){
        Intent rev_intent = new Intent(getApplicationContext(),ReviewActivity.class);
        startActivity(rev_intent);
    }
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-INTENTS--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

    //----------------------------------------OVERRIDE METHODS--------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                super.onBackPressed();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    //----------------------------------------OVERRIDE METHODS--------------------------------------

}
