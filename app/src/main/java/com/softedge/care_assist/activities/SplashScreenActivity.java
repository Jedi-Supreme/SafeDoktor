package com.softedge.care_assist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.softedge.care_assist.models.fireModels.PatientPackage.Biography;
import com.softedge.care_assist.R;
import com.softedge.care_assist.databases.SafeDB;

import java.lang.ref.WeakReference;

public class SplashScreenActivity extends AppCompatActivity {

    final long COUNTDOWN_TIME = 1500; // 1.5 seconds
    final long SECS = 1000;

    String fireID;
    WeakReference<SplashScreenActivity> weahSplash;

    SafeDB safe_db;

    //=============================================ON CREATE========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        weahSplash = new WeakReference<>(this);

        /*if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            fireID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        try {
            loadBioData_online();
        } catch (Exception ignored) {
        }*/

        //--------------------------------------------COUNTDOWN TIMER-------------------------------
        CountDownTimer countDownTimer = new CountDownTimer(COUNTDOWN_TIME, SECS) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                toDashboard();
            }
        };
        //--------------------------------------------COUNTDOWN TIMER-------------------------------

        countDownTimer.start();

    }
    //=============================================ON CREATE========================================


    void loadBioData_online() {

        String all_users = getResources().getString(R.string.all_users);

        safe_db = new SafeDB(weahSplash.get(), null);

        DatabaseReference bio_ref = FirebaseDatabase.getInstance().getReference(all_users)
                .child(Biography.TABLE);

        bio_ref.child(fireID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Biography userBio = dataSnapshot.getValue(Biography.class);

                if (userBio != null) {

                    try {
                        safe_db.addPat_bio(userBio);
                    } catch (Exception ignored) {
                        safe_db.updatePat_bio(userBio);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    //dashboard intent
    void toDashboard() {
        Intent dashboard_intent = new Intent(getApplicationContext(), OpdCardActivity.class);
        dashboard_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(dashboard_intent);
        super.finish();
    }
}
