package com.softedge.safedoktor.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.softedge.safedoktor.R;


public class LoginActivity extends AppCompatActivity {

    TextInputEditText et_login_number, et_login_password;
    TextInputLayout input_login_password;
    ProgressBar probar_login;

    //=============================================ON CREATE========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        input_login_password = findViewById(R.id.input_login_password);
        et_login_number = findViewById(R.id.et_login_number);
        et_login_password = findViewById(R.id.et_login_password);
        probar_login = findViewById(R.id.probar_login);


        et_login_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                input_login_password.setPasswordVisibilityToggleEnabled(hasFocus);
            }
        });
    }
    //=============================================ON CREATE========================================

    //---------------------------------------Click Listeners----------------------------------------
    public void loginToDashboard(View view) {

        //testing inputs

        if ( et_login_number.getText().toString().isEmpty() || et_login_number.getText().toString().length() < 10){
            showSnackbar(findViewById(R.id.main_login_Activity),"Enter valid Mobile Number");
        }else  if (et_login_password.getText().toString().isEmpty() || et_login_password.getText().toString().equals("")){
            showSnackbar(findViewById(R.id.main_login_Activity),"Enter Password");
        }
    }

    public void UserSignup(View view) {
        Intent signup_intent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(signup_intent);
    }

    public void ResetPassword(View view) {
    }
    //---------------------------------------Click Listeners----------------------------------------

    //dashboard intent
    void toDashboard(){
        Intent dashboard_intent = new Intent(this,DashboardActivity.class);
        dashboard_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(dashboard_intent);
    }

    void showSnackbar(View mainView,String message){

        final Snackbar snackbar = Snackbar.make(mainView,message,Snackbar.LENGTH_SHORT);
        snackbar.setAction("Close", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

}
