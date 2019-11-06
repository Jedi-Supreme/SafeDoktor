package com.softedge.care_assist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.softedge.care_assist.R;
import com.softedge.care_assist.api.CarewexCalls;
import com.softedge.care_assist.models.fireModels.PatientPackage.Biography;
import com.softedge.care_assist.models.retrofitModels.retroPatient;
import com.softedge.care_assist.models.retrofitModels.retro_patSearch;
import com.softedge.care_assist.utilities.common_code;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class RegSearch extends AppCompatActivity {

    Spinner sp_srch_facilities;
    ConstraintLayout const_srch_layout;
    ScrollView scroll_srch_result;

    TextInputEditText
            et_srch_opd, et_srch_fn, et_srch_ln,
            et_srch_dob, et_srch_email, et_srch_mobilenumb,
            et_srch_gender, et_srch_marital;
    ProgressBar probar_srch;

    String[] fac_codes_arr;
    retroPatient srch_patient;
    WeakReference<RegSearch> weakSearch;

    //=============================================ON CREATE========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_search);

        sp_srch_facilities = findViewById(R.id.sp_srch_facilities);
        const_srch_layout = findViewById(R.id.const_srch_layout);
        scroll_srch_result = findViewById(R.id.card_srch_result);
        weakSearch = new WeakReference<>(this);
        probar_srch = findViewById(R.id.probar_srch);

        et_srch_opd = findViewById(R.id.et_srch_opd);
        et_srch_fn = findViewById(R.id.et_srch_fn);
        et_srch_ln = findViewById(R.id.et_srch_ln);
        et_srch_dob  = findViewById(R.id.et_srch_dob);
        et_srch_email = findViewById(R.id.et_srch_email);
        et_srch_mobilenumb = findViewById(R.id.et_srch_phone);
        et_srch_gender = findViewById(R.id.et_srch_gender);
        et_srch_marital = findViewById(R.id.et_srch_marital);

        fac_codes_arr = getResources().getStringArray(R.array.facCodes_arr);
        sp_srch_facilities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                scroll_srch_result.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    //=============================================ON CREATE========================================

    void testOpdNumber(){

        if (Objects.requireNonNull(et_srch_opd.getText()).toString().isEmpty() || et_srch_opd.getText().toString().equals("")){
            common_code.Mysnackbar(const_srch_layout,"Enter O.P.D number", Snackbar.LENGTH_SHORT).show();
            probar_srch.setVisibility(View.GONE);
        }else if (sp_srch_facilities.getSelectedItemPosition() == 0){
            probar_srch.setVisibility(View.GONE);
            common_code.Mysnackbar(const_srch_layout,"Select registration facility", Snackbar.LENGTH_SHORT).show();
        }else {
            validate_opd(et_srch_opd.getText().toString());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (common_code.isInternetConnected(weakSearch.get())){
            CarewexCalls.get_access_token(weakSearch.get());
        }else{
            common_code.connection_toast(getApplicationContext());
        }
    }

    void validate_opd(String userOPD){

        try {
            String[] opd_arr = userOPD.split("-");
            probar_srch.setVisibility(View.VISIBLE);

            String opdnumber;

            switch (opd_arr[0].length()){

                case 4:
                    opdnumber  = fac_codes_arr[sp_srch_facilities.getSelectedItemPosition()]
                            + "-000"
                            + opd_arr[0] + "-"
                            + opd_arr[1];

                    getpatResult(opdnumber);
                    break;

                case 5:

                    opdnumber  = fac_codes_arr[sp_srch_facilities.getSelectedItemPosition()]
                            + "-00"
                            + opd_arr[0] + "-"
                            + opd_arr[1];

                    Toast.makeText(getApplicationContext(), opdnumber, Toast.LENGTH_SHORT).show();
                    getpatResult(opdnumber);
                    break;

                case 6:
                    opdnumber  = fac_codes_arr[sp_srch_facilities.getSelectedItemPosition()]
                            + "-0"
                            + opd_arr[0] + "-"
                            + opd_arr[1];

                    Toast.makeText(getApplicationContext(), opdnumber, Toast.LENGTH_SHORT).show();
                    getpatResult(opdnumber);
                    break;

                case 7:
                    opdnumber  = fac_codes_arr[sp_srch_facilities.getSelectedItemPosition()]
                            + "-"
                            + opd_arr[0] + "-"
                            + opd_arr[1];

                    Toast.makeText(getApplicationContext(), opdnumber, Toast.LENGTH_SHORT).show();

                    getpatResult(opdnumber);
                    break;

                    default:
                        probar_srch.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), opd_arr[0], Toast.LENGTH_SHORT).show();
                        common_code.Mysnackbar(const_srch_layout,"Invalid O.P.D number, Please Check and try again",
                                Snackbar.LENGTH_LONG).show();

            }

            /*if (opd_arr[0].length() == 5 && opd_arr[1].length() == 2){

                opdnumber  = fac_codes_arr[sp_srch_facilities.getSelectedItemPosition()]
                        + "-00"
                        + opd_arr[0] + "-"
                        + opd_arr[1];

                getpatResult(opdnumber);

            } else if (opd_arr[0].length() == 7 && opd_arr[1].length() == 2){

                opdnumber  = fac_codes_arr[sp_srch_facilities.getSelectedItemPosition()]
                        + opd_arr[0] + "-"
                        + opd_arr[1];

                getpatResult(opdnumber);

            }else {

                probar_srch.setVisibility(View.GONE);
                common_code.Mysnackbar(const_srch_layout,"Invalid O.P.D number, Please Check and try again",
                        Snackbar.LENGTH_LONG).show();
            }*/

        }catch (Exception ignored){
            probar_srch.setVisibility(View.GONE);
            common_code.Mysnackbar(const_srch_layout,"Invalid O.P.D number, Please Check and try again",
                    Snackbar.LENGTH_LONG).show();
        }

    }

    void getpatResult(String opdnumber){
        retro_patSearch search = new retro_patSearch("","",opdnumber,"");
        CarewexCalls.getPatientsResult(search,weakSearch.get());
    }

    //Populate fields with user data
    public void populate_result(List<retroPatient> patientsList){

        probar_srch.setVisibility(View.GONE);
        Calendar calendar = Calendar.getInstance();

        if (patientsList.size() > 0){

            for (retroPatient pat: patientsList){

                srch_patient = pat;

                calendar.setTimeInMillis(Long.parseLong(pat.getDateOfBirth()));

                SimpleDateFormat dateFormat = new SimpleDateFormat(common_code.regDateformat, Locale.getDefault());

                SimpleDateFormat dateFormat_humanReadable = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());

                et_srch_fn.setText(pat.getFirstname().toUpperCase());
                et_srch_ln.setText(pat.getLastName().toUpperCase());
                et_srch_email.setText(pat.getEmail());
                et_srch_gender.setText(pat.getGender());
                et_srch_marital.setText(pat.getMaritalStatus());
                et_srch_dob.setText(dateFormat_humanReadable.format(calendar.getTime()));
                et_srch_mobilenumb.setText(pat.getPhoneNumber());
                scroll_srch_result.setVisibility(View.VISIBLE);
            }

        }else {
            scroll_srch_result.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(),"No Results Found",Toast.LENGTH_LONG).show();
            regSnackbar();
        }

    }

    void regSnackbar(){
        Snackbar snackbar = Snackbar.make(const_srch_layout, getResources().getString(R.string.reg_qn), Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(getResources().getColor(R.color.colorPrimary));
        snackbar.setAction(getResources().getString(R.string.register_txt), v -> common_code.toSignup(weakSearch.get()));
        snackbar.show();
    }

    //Search button click listener
    public void Search_Method(View view) {
        // search button method
        //probar_srch.setVisibility(View.VISIBLE);

        if (!common_code.isInternetConnected(weakSearch.get())){
            common_code.connection_toast(getApplicationContext());
        }else {
            testOpdNumber();
        }
    }

    //Confirm button click listener
    public void Confirm_method(View view) {
        toRegistration();
    }

    //pass user data to registration activity
    void toRegistration(){
        Intent link_reg_intent = new Intent(getApplicationContext(), SignupActivity.class);

        if (srch_patient != null){

            link_reg_intent.putExtra(Biography.FIRSTNAME,srch_patient.getFirstname().toUpperCase());
            link_reg_intent.putExtra(Biography.LASTNAME,srch_patient.getLastName().toUpperCase());

            link_reg_intent.putExtra(Biography.MOBILE_NUMBER,srch_patient.getPhoneNumber());
            link_reg_intent.putExtra(Biography.EMAIL,srch_patient.getEmail());

            link_reg_intent.putExtra(Biography.DATE_OF_BIRTH,et_srch_dob.getText().toString());
            link_reg_intent.putExtra(Biography.GENDER,genderIndex(srch_patient.getGender()));

            link_reg_intent.putExtra(Biography.MARITAL_STATUS,marital_statusIndex(srch_patient.getMaritalStatus()));
            link_reg_intent.putExtra(Biography.OPD_ID,srch_patient.getPatientId());

            startActivity(link_reg_intent);
        }
    }

    //-------------------------Get index of gender and marital status-------------------------------
    int genderIndex(String gender){

        int x = 0;
        String[] gender_arr = getResources().getStringArray(R.array.gender_Arr);

        for (; x < gender_arr.length; x += 1){

            if (gender_arr[x].equalsIgnoreCase(gender)){
                return x;
            }
        }

        return 1;
    }

    int marital_statusIndex(String marital_status){
        int x = 0;
        String[] marital_status_arr = getResources().getStringArray(R.array.marital_status);

        for (;x < marital_status_arr.length; x++){

            if (marital_status_arr[x].equalsIgnoreCase(marital_status)){
                return x;
            }
        }

        return 1;
    }
    //-------------------------Get index of gender and marital status-------------------------------

}
