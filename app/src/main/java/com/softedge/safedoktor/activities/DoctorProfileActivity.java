package com.softedge.safedoktor.activities;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.softedge.safedoktor.R;

public class DoctorProfileActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);
    }
}
