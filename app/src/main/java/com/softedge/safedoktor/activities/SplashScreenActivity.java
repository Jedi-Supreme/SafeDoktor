package com.softedge.safedoktor.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;

import com.softedge.safedoktor.R;
import com.softedge.safedoktor.api.SwaggerCalls;
import com.softedge.safedoktor.databases.appDB;
import com.softedge.safedoktor.models.swaggerModels.body.Login;
import com.softedge.safedoktor.models.swaggerModels.body.Specialties;
import com.softedge.safedoktor.utilities.AppConstants;
import com.softedge.safedoktor.utilities.common_code;

import java.lang.ref.WeakReference;
import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {

    final long COUNTDOWN_TIME = 1500; // 1.5 seconds
    final long SECS = 1000;

    ConstraintLayout const_splash_parent;

    WeakReference<SplashScreenActivity> weahSplash;

    //=============================================ON CREATE========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        weahSplash = new WeakReference<>(this);
        const_splash_parent = findViewById(R.id.const_splash_parent);

        //--------------------------------------------COUNTDOWN TIMER-------------------------------
        CountDownTimer countDownTimer = new CountDownTimer(COUNTDOWN_TIME, SECS) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                preLoginTest();
            }
        };
        //--------------------------------------------COUNTDOWN TIMER-------------------------------

        countDownTimer.start();

    }
    //=============================================ON CREATE========================================


    void preLoginTest() {

        SharedPreferences appPref = common_code.appPref(weahSplash.get());
        String username = appPref.getString(AppConstants.KEY_USERNAME,null);
        String password = appPref.getString(AppConstants.KEY_PASSWORD,null);

        appDB appDbase = appDB.getInstance(weahSplash.get());
        appDbase.safeDoktorAccessObj().getAllSpecialties();

        Login login  = new Login(username,password);

        boolean isLogin = appPref.getBoolean(AppConstants.IS_LOGIN,false);

        if (!common_code.isFirstRun(weahSplash.get())){
            if (isLogin){
                //Refresh token
                SwaggerCalls.login(const_splash_parent,login);
                common_code.toDashboard(weahSplash.get());

                appDbase.safeDoktorAccessObj().getAllSpecialties().observe(weahSplash.get(), specialties -> {

                    for (Specialties spec : specialties){
                        SwaggerCalls.getSpecialtyDoctors(const_splash_parent,spec);
                    }

                });

            }else {
                common_code.toLogin(weahSplash.get());
            }
        }else {
            common_code.toIntro(weahSplash.get());
        }
//

    }

    //dashboard intent
    void toDashboard() {
        Intent dashboard_intent = new Intent(getApplicationContext(), DashboardActivity.class);
        dashboard_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(dashboard_intent);
        super.finish();
    }
}
