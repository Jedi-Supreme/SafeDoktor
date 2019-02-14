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
import com.softedge.safedoktor.common_code;


public class LoginActivity extends AppCompatActivity {

    TextInputEditText et_login_identification, et_login_password;
    TextInputLayout input_login_password;
    ProgressBar probar_login;

    //=============================================ON CREATE========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        input_login_password = findViewById(R.id.input_login_password);
        et_login_identification = findViewById(R.id.et_login_id);
        et_login_password = findViewById(R.id.et_login_password);
        probar_login = findViewById(R.id.probar_login_bar);

        et_login_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                input_login_password.setPasswordVisibilityToggleEnabled(hasFocus);
            }
        });
    }
    //=============================================ON CREATE========================================

    //------------^^^^^----------^^^^---------Defined Methods------^^^^^------^^^^^-------^^^^^-----
    void testinputs(){

        String mobilenumber;
        String password;

        if ( et_login_identification.getText().toString().isEmpty() || et_login_identification.getText().toString().equals("")){
            et_login_identification.setError("This field is required");
            et_login_identification.requestFocus();
        }else{
            //if it contains '@' and '.' then its an email, sign in with email else test integer value
            if (et_login_identification.getText().toString().contains("@") && et_login_identification.getText().toString().contains(".")){
                //its an email
                //TODO login with email address
            }else {
                //test for phone number
                int number;
                try {
                    number = Integer.parseInt(et_login_identification.getText().toString());
                    if (String.valueOf(number).length() == 9 ){
                        //its a valid number
                        //TODO Login with email by checking user records
                    }else {
                        common_code.Mysnackbar(findViewById(R.id.main_login_Activity),"Enter Valid Mobile number",
                                Snackbar.LENGTH_SHORT);
                    }
                }catch (Exception ignored){
                    common_code.Mysnackbar(findViewById(R.id.main_login_Activity),"Invalid Mobile number or Email Address",
                            Snackbar.LENGTH_LONG);
                }
            }
        }

        if (et_login_password.getText().toString().isEmpty() || et_login_password.getText().toString().equals("")){
            common_code.Mysnackbar(findViewById(R.id.main_login_Activity),"Enter Password",Snackbar.LENGTH_SHORT);
        }
    }
    //------------^^^^^----------^^^^---------Defined Methods------^^^^^------^^^^^-------^^^^^-----

    //---------------------------------------Click Listeners----------------------------------------
    public void loginToDashboard(View view) {
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

}
