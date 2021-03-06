package com.softedge.safedoktor.activities;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.api.SwaggerCalls;
import com.softedge.safedoktor.models.swaggerModels.body.Login;
import com.softedge.safedoktor.utilities.common_code;

import java.lang.ref.WeakReference;
import java.util.Objects;


public class LoginActivity extends AppCompatActivity {

    TextInputEditText et_login_id, et_login_password;
    TextInputLayout input_login_password;
    ProgressBar probar_login;

    ConstraintLayout const_login_layout;
    AlertDialog alertDialog;

    WeakReference<LoginActivity> weak_login;

    //=============================================ON CREATE========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        weak_login = new WeakReference<>(LoginActivity.this);

        input_login_password = findViewById(R.id.input_login_password);
        et_login_id = findViewById(R.id.et_login_id);
        et_login_password = findViewById(R.id.et_login_password);
        probar_login = findViewById(R.id.probar_login_bar);

        const_login_layout = findViewById(R.id.main_login_Activity);

        et_login_password.setOnFocusChangeListener((v, hasFocus) -> {
            input_login_password.setEndIconActivated(hasFocus);
            input_login_password.setEndIconMode(TextInputLayout.END_ICON_PASSWORD_TOGGLE);
            });

        alertDialog = new AlertDialog.Builder(weak_login.get()).create();

        View searchView = LayoutInflater.from(weak_login.get()).inflate(R.layout.diag_reg_choice,const_login_layout,false);

        Button bt_pos = searchView.findViewById(R.id.bt_positive),
                bt_neg = searchView.findViewById(R.id.bt_negative);

        bt_neg.setOnClickListener(v -> common_code.toSignup(weak_login.get()));
        bt_pos.setOnClickListener(v -> toSearch());

        alertDialog.setView(searchView);
    }
    //=============================================ON CREATE========================================

    @Override
    protected void onResume() {
        super.onResume();

        alertDialog.dismiss();
    }

    //------------^^^^^----------^^^^---------Defined Methods------^^^^^------^^^^^-------^^^^^-----

    void testInputs(){
        if (Objects.requireNonNull(et_login_id.getText()).toString().isEmpty() || et_login_id.getText().toString().equals("")) {
           et_login_id.setError("This field is required");
            et_login_id.requestFocus();
       }else if (Objects.requireNonNull(et_login_password.getText()).toString().isEmpty() || et_login_password.getText().toString().equals("")) {
            common_code.Mysnackbar(findViewById(R.id.main_login_Activity), "Enter Password", Snackbar.LENGTH_SHORT).show();
        }else {
            Login userlogin = new Login(et_login_id.getText().toString(),et_login_password.getText().toString());

            SwaggerCalls.login(const_login_layout,userlogin);

        }
    }





//    void testInputs_prev() {
//
//        if (Objects.requireNonNull(et_login_identification.getText()).toString().isEmpty() || et_login_identification.getText().toString().equals("")) {
//            et_login_identification.setError("This field is required");
//            et_login_identification.requestFocus();
//        }
//        else {
//            //if it contains '@' and '.' then its an email, sign in with email else test integer value
//            if (et_login_identification.getText().toString().contains("@")) {
//
//                //its an email
//                login_with_credentials(et_login_identification.getText().toString(), Objects.requireNonNull(et_login_password.getText()).toString());
//
//            } else {
//
//                //test for phone number
//                int number;
//
//                try {
//                    number = Integer.parseInt(et_login_identification.getText().toString());
//
//                    if (String.valueOf(number).length() == 9) {
//
//                        try {
//                            //its a valid number
//                            fetch_email(et_login_identification.getText().toString(), Objects.requireNonNull(et_login_password.getText()).toString());
//                        }catch (Exception e){
//                            Toast.makeText(getApplicationContext(),"Error logging in: " + e.toString(),Toast.LENGTH_LONG).show();
//                        }
//
//                    } else {
//                        common_code.Mysnackbar(findViewById(R.id.main_login_Activity), "Enter Valid Mobile number",
//                                Snackbar.LENGTH_SHORT).show();
//                    }
//                } catch (Exception ignored) {
//                    common_code.Mysnackbar(findViewById(R.id.main_login_Activity), "Invalid Mobile number or Email Address",
//                            Snackbar.LENGTH_LONG).show();
//                }
//            }
//        }
//
//        if (Objects.requireNonNull(et_login_password.getText()).toString().isEmpty() || et_login_password.getText().toString().equals("")) {
//            common_code.Mysnackbar(findViewById(R.id.main_login_Activity), "Enter Password", Snackbar.LENGTH_SHORT).show();
//        }
//    }

    void login_with_credentials(final String email, String password) {

        probar_login.setVisibility(View.VISIBLE);
        probar_login.animate();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        common_code.toDashboard(weak_login.get());

                    } else {
                        // If sign in fails, display a message to the user.
                        if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                            if (email.contains(getResources().getString(R.string.default_email_suffix))) {
                                common_code.Mysnackbar(findViewById(R.id.main_login_Activity), "Invalid Mobile number",
                                        Snackbar.LENGTH_SHORT).show();
                            } else {
                                common_code.Mysnackbar(findViewById(R.id.main_login_Activity), "Invalid Email Address",
                                        Snackbar.LENGTH_SHORT).show();
                            }
                            probar_login.setVisibility(View.INVISIBLE);
                            probar_login.clearAnimation();

                        } else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                            common_code.Mysnackbar(findViewById(R.id.main_login_Activity), "Wrong Password",
                                    Snackbar.LENGTH_SHORT).show();

                            probar_login.setVisibility(View.INVISIBLE);
                            probar_login.clearAnimation();
                        }


                    }

                });

    }

    void passwordReset_dialog() {

        final AlertDialog reset_dialog = new AlertDialog.Builder(weak_login.get()).create();
        reset_dialog.setCancelable(true);

        View reset_view = LayoutInflater.from(weak_login.get())
                .inflate(R.layout.diag_pass_reset, const_login_layout, false);
        Button bt_reset_send = reset_view.findViewById(R.id.bt_reset_send);
        final TextInputEditText et_reset_email = reset_view.findViewById(R.id.et_reset_email);
        reset_dialog.setView(reset_view);

        bt_reset_send.setOnClickListener(v -> {
            if (!Objects.requireNonNull(et_reset_email.getText()).toString().isEmpty() || !et_reset_email.getText().toString().equals("")) {

                if (et_reset_email.getText().toString().isEmpty() || et_reset_email.getText().toString().equals("") ||
                        !et_reset_email.getText().toString().contains("@")) {
                    et_reset_email.setError("Enter Email");
                    et_reset_email.requestFocus();
                } else {
                    et_reset_email.setEnabled(false);
                    FirebaseAuth.getInstance().sendPasswordResetEmail(et_reset_email.getText().toString())
                            .addOnCompleteListener(task -> {

                                if (task.isSuccessful()) {

                                    reset_dialog.dismiss();
                                    common_code.Mysnackbar(const_login_layout,
                                            "Reset Link Sent Successfully", Snackbar.LENGTH_LONG).show();
                                } else {
                                    et_reset_email.setEnabled(true);
                                    common_code.Mysnackbar(const_login_layout,
                                            "Invalid Email, Please Check and try Again", Snackbar.LENGTH_LONG).show();
                                }
                            });
                }
            }
        });

        reset_dialog.show();
    }

    void fetch_email(String mobilenumber, final String password) {

        final DatabaseReference records_ref = FirebaseDatabase.getInstance().getReference(getResources().getString(R.string.records_ref));
        probar_login.setVisibility(View.VISIBLE);
        probar_login.animate();

        records_ref.child(mobilenumber).child("email").addValueEventListener(
                new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           String email = dataSnapshot.getValue(String.class);

                             if (email != null){
                                 login_with_credentials(email, password);
                             }else {
                                 probar_login.setVisibility(View.GONE);
                                 Toast.makeText(getApplicationContext(),"No account record found for " + mobilenumber, Toast.LENGTH_LONG).show();
                             }
                             records_ref.removeEventListener(this);

                         }

                          @Override
                          public void onCancelled(@NonNull DatabaseError databaseError) {}

                }
        );

    }

    //------------^^^^^----------^^^^---------Defined Methods------^^^^^------^^^^^-------^^^^^-----

    //---------------------------------------Click Listeners----------------------------------------
    public void loginToDashboard(View view) {

        if (!common_code.isInternetConnected(weak_login.get())){
            common_code.connection_toast(getApplicationContext());
        }else {
            testInputs();
            //testInputs_prev();
        }

    }

    public void UserSignup(View view) {
        alertDialog.show();
    }

    public void ResetPassword(View view) {

        passwordReset_dialog();

    }

    public void toggleProbar(boolean Isvisible){

        if (Isvisible){
            probar_login.setVisibility(View.VISIBLE);
        }else {
            probar_login.setVisibility(View.GONE);
        }
    }
    //---------------------------------------Click Listeners----------------------------------------

    //dashboard intent


    void toSearch(){
        Intent search_intent = new Intent(getApplicationContext(), RegSearch.class);
        startActivity(search_intent);
    }

}
