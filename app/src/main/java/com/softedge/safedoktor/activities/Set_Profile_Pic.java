package com.softedge.safedoktor.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.softedge.safedoktor.R;
import com.softedge.safedoktor.activities.VerificationActivity;

public class Set_Profile_Pic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile_pic);
    }

    public void toVerification(View view) {
        Intent verification_intent = new Intent(getApplicationContext(), VerificationActivity.class);
        startActivity(verification_intent);
    }
}
