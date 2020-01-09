package com.softedge.safedoktor.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.softedge.safedoktor.adapters.contacts_recycler_Adapter;
import com.softedge.safedoktor.adapters.dependants_recycler_Adapter;
import com.softedge.safedoktor.api.CarewexCalls;
import com.softedge.safedoktor.models.fireModels.Dependant_class;
import com.softedge.safedoktor.models.fireModels.PatientPackage.Biography;
import com.softedge.safedoktor.models.fireModels.PatientPackage.ContactPerson;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.models.retrofitModels.retroPatient;
import com.softedge.safedoktor.utilities.common_code;
import com.softedge.safedoktor.databases.SafeDB;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;

public class Contacts_dependantsActivity extends AppCompatActivity {

    RecyclerView conts_deps_recycler;
    ConstraintLayout const_contact_layout;

    WeakReference<Contacts_dependantsActivity> weakcontact_dep;

    SafeDB safe_DB;
    String fireID;

    Bundle flag_bundle;
    ActionBar actionBar;
    String incoming_flag;
    ProgressBar probar_dep_reg;

    //==========================================ON CREATE===========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        weakcontact_dep = new WeakReference<>(this);
        safe_DB = new SafeDB(weakcontact_dep.get(), null);

        flag_bundle = getIntent().getExtras();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            fireID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        //--------------------------------------HOME BUTTON ON APP BAR------------------------------
        actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //--------------------------------------HOME BUTTON ON APP BAR------------------------------

        conts_deps_recycler = findViewById(R.id.contacts_recycler);
        const_contact_layout = findViewById(R.id.const_contact_layout);
        probar_dep_reg = findViewById(R.id.probar_cont_dep_reg);

    }
    //==========================================ON CREATE===========================================

    //-=--=-=--=-=--=-=--=-=--=-=--=-=--=-=--=-=--=--=--DEFINED METHODS-=--=-=--=-=--=-=--=-=--=-=--

    public void save_to_local(retroPatient patient){

        Dependant_class dependant_class = new Dependant_class(
                patient.getPatientId(),
                fireID,patient.getFirstname(),
                patient.getLastName(),
                patient.getPhoneNumber(),
                patient.getDateOfBirth(),
                gender_index(patient.getGender()),
                maarital_index(patient.getMaritalStatus())
                );
        toggle_progressbar();

        if (safe_DB != null){

            safe_DB.addDependant(dependant_class);

            /*if (){
                refresh_dependants_list();
                save_DependantsOnline(safe_DB.all_dependants(fireID));
                toggle_progressbar();

                common_code.Mysnackbar(const_contact_layout, "Dependant added Successfully",
                        Snackbar.LENGTH_SHORT).show();
            }*/

        }else {
            Toast.makeText(getApplicationContext(), "DB null", Toast.LENGTH_SHORT).show();
        }
    }

    int gender_index(String gender){

        if (gender.equals("Male")){
            return common_code.MALE_GENDER;
        }else {
            return common_code.FEMALE_GENDER;
        }
    }

    int maarital_index(String maritalStatus){

        if (maritalStatus.equals("Single")){
            return common_code.MAR_SINGLE;
        }else {
            return common_code.MAR_SINGLE;
        }
    }
    //---------------------------------------------------Load list items----------------------------
    public void refresh_contacts_list() {
        contacts_recycler_Adapter contactsAdapter = new contacts_recycler_Adapter(this, safe_DB.contactsList(fireID));
        conts_deps_recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        conts_deps_recycler.setAdapter(contactsAdapter);
    }

    public void refresh_dependants_list() {
        dependants_recycler_Adapter dependantAdapter = new dependants_recycler_Adapter(this, safe_DB.all_dependants(fireID));
        conts_deps_recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        conts_deps_recycler.setAdapter(dependantAdapter);
    }
    //---------------------------------------------------Load list items----------------------------

    //====================-----------------------Dialog boxes------------------=====================
    public void ContactPersonDialog(@Nullable final ContactPerson contactPerson) {

        final AlertDialog contactdialog = new AlertDialog.Builder(weakcontact_dep.get()).create();

        contactdialog.setCancelable(true);

        View contactView = LayoutInflater.from(weakcontact_dep.get())
                .inflate(R.layout.diag_add_contact, const_contact_layout, false);

        final TextInputEditText
                et_contact_fullname = contactView.findViewById(R.id.et_cntc_name),
                et_contact_email = contactView.findViewById(R.id.et_cntc_email),
                et_contact_number = contactView.findViewById(R.id.et_cntc_phone),
                et_contact_address = contactView.findViewById(R.id.et_cntc_hadd);

        final Spinner sp_contact_rel = contactView.findViewById(R.id.sp_pop_contact_rel);
        Button bt_contact_submit = contactView.findViewById(R.id.bt_cntc_submit);

        contactdialog.setView(contactView);

        if (contactPerson !=null){
            et_contact_fullname.setText(contactPerson.getFullname());
            et_contact_email.setText(contactPerson.getEmail());
            et_contact_number.setText(contactPerson.getNumber());
            et_contact_address.setText(contactPerson.getAddress());
            sp_contact_rel.setSelection(contactPerson.getRelation());
        }

        bt_contact_submit.setOnClickListener(v -> {

            if (et_contact_fullname.getText().toString().isEmpty()) {
                et_contact_fullname.setError("This Field is Required");
                et_contact_fullname.requestFocus();

            } else if (et_contact_number.getText().toString().isEmpty()
                    || et_contact_number.getText().toString().length() < 10) {
                et_contact_number.setError("Contact's Mobile Number Required");
                et_contact_number.requestFocus();

            } else if (sp_contact_rel.getSelectedItemPosition() <= 0) {
                Toast.makeText(getApplicationContext(), "Select Contact Relation", Toast.LENGTH_LONG).show();
                sp_contact_rel.requestFocus();
            } else {

               ContactPerson contPers = new ContactPerson(
                        fireID,
                        et_contact_fullname.getText().toString(),
                        "",
                        et_contact_number.getText().toString(),
                        "",
                        sp_contact_rel.getSelectedItemPosition());

                if (!et_contact_email.getText().toString().isEmpty()) {
                    contPers.setEmail(et_contact_email.getText().toString());
                }

                if (!et_contact_address.getText().toString().isEmpty()) {
                    contPers.setAddress(et_contact_address.getText().toString());
                }

                if (safe_DB.addContact(contPers)) {

                    ArrayList<ContactPerson> contacts_list = safe_DB.contactsList(fireID);
                    save_ContactsOnline(contacts_list);

                    common_code.Mysnackbar(const_contact_layout, "Contact Person added Successfully",
                            Snackbar.LENGTH_SHORT).show();
                    refresh_contacts_list();

                } else {
                    common_code.Mysnackbar(const_contact_layout, "Error adding Contact Person",
                            Snackbar.LENGTH_SHORT).show();
                }

                contactdialog.dismiss();

            }

        });

        contactdialog.show();
    }

    public void DependantsDialog(@Nullable final Dependant_class dependantPerson) {

        String[] gender_arr = getResources().getStringArray(R.array.gender_Arr);
        String[] marital_arr = getResources().getStringArray(R.array.marital_status);

        final AlertDialog dependdialog = new AlertDialog.Builder(weakcontact_dep.get()).create();

        dependdialog.setCancelable(true);

        View dependView = LayoutInflater.from(weakcontact_dep.get())
                .inflate(R.layout.diag_add_dependant, const_contact_layout, false);

        Biography appUser = common_code.appuser(weakcontact_dep.get());
        String number;

        final TextInputEditText
                et_dep_fname = dependView.findViewById(R.id.et_dep_fname),
                et_dep_lname = dependView.findViewById(R.id.et_dep_lname),
                et_dep_number = dependView.findViewById(R.id.et_dep_phone);

        Spinner sp_dep_gender = dependView.findViewById(R.id.sppop_depend_rel);
        DatePicker datePicker = dependView.findViewById(R.id.dep_dob_picker);

        if (appUser != null){
            number = "0"+appUser.getMobile_number();
            et_dep_number.setText(number);
        }

        Button bt_depend_submit = dependView.findViewById(R.id.bt_dep_submit);

        dependdialog.setView(dependView);

        if (dependantPerson != null){
            String numb = "0" + dependantPerson.getMobile_number();
            et_dep_fname.setText(dependantPerson.getDepend_firstname());
            et_dep_lname.setText(dependantPerson.getDepend_lastname());
            et_dep_number.setText(numb);
            sp_dep_gender.setSelection(dependantPerson.getGender());
        }

        bt_depend_submit.setOnClickListener(v -> {

            if (Objects.requireNonNull(et_dep_fname.getText()).toString().isEmpty()){
                et_dep_fname.setError("Enter first name");
                et_dep_fname.requestFocus();

            }else if (Objects.requireNonNull(et_dep_lname.getText()).toString().isEmpty()){
                et_dep_lname.setError("Enter Last name");
                et_dep_lname.requestFocus();

            }else if (Objects.requireNonNull(et_dep_number.getText()).toString().isEmpty()){
                et_dep_number.setError("This Field is Required");
                et_dep_number.requestFocus();

            }else if (sp_dep_gender.getSelectedItemPosition() <= 0){
                Toast.makeText(getApplicationContext(),"Select gender",Toast.LENGTH_SHORT).show();
            }else {

                String dob = datePicker.getDayOfMonth() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getYear();

                String title;

                if (sp_dep_gender.getSelectedItemPosition() == common_code.MALE_GENDER){
                    title = "Mr";
                }else {
                    title = "Miss";
                }

                retroPatient patient = new retroPatient(
                        null,title,
                        et_dep_fname.getText().toString(),
                        et_dep_lname.getText().toString(),
                        et_dep_number.getText().toString(),
                        "","","Ghanaian",
                        dob,gender_arr[sp_dep_gender.getSelectedItemPosition()],
                        marital_arr[1],"","","",
                        null,null

                );

                carewex_patRegID(patient);
                dependdialog.dismiss();
            }

        });

        dependdialog.show();
    }
    //====================-----------------------Dialog boxes------------------=====================

    //Register user on carewex and return OPD number
    void carewex_patRegID(retroPatient retro_pat){
        // register patient on carewex
        CarewexCalls.register_patient(retro_pat,weakcontact_dep.get());
        //toggle_progressbar();
    }

    //--------------------------------------SAVE TO ONLINE DB---------------------------------------
    public void save_ContactsOnline(ArrayList<ContactPerson> fireContacts) {

        DatabaseReference all_users_ref = FirebaseDatabase.getInstance().getReference(getResources().getString(R.string.all_users));

        //save user details to All_Users/Contacts/Uid
        all_users_ref.child(ContactPerson.TABLE).child(fireID)
                .setValue(fireContacts);
    }

    public void save_DependantsOnline(ArrayList<Dependant_class> fireDependanta) {

        DatabaseReference all_users_ref = FirebaseDatabase.getInstance().getReference(getResources().getString(R.string.all_users));

        //save user details to All_Users/Dependants/Uid
        all_users_ref.child(Dependant_class.TABLE).child(fireID)
                .setValue(fireDependanta);
    }
    //--------------------------------------SAVE TO ONLINE DB---------------------------------------

    void toggle_progressbar(){
        if (probar_dep_reg.getVisibility() == View.VISIBLE){
            probar_dep_reg.setVisibility(View.GONE);
        }else {
            probar_dep_reg.setVisibility(View.VISIBLE);
        }
    }

    //-=--=-=--=-=--=-=--=-=--=-=--=-=--=-=--=-=--=--=--DEFINED METHODS-=--=-=--=-=--=-=--=-=--=-=--

    //------------------------------------------OVERRIDE METHODS------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (flag_bundle != null){
            incoming_flag = flag_bundle.getString("flag");

            if (incoming_flag != null){
                actionBar.setTitle(incoming_flag);

                if (incoming_flag.equals(common_code.CONTACTS_FLAG)){
                try {
                    //refresh contacts list
                    refresh_contacts_list();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "Unable to refresh contacts list with error " + e.toString(), Toast.LENGTH_LONG).show();
                }
                }else if (incoming_flag.equals(common_code.DEPENDANTS_FLAG)){
                    try {
                        //refresh dependants
                        refresh_dependants_list();
                    }catch ( Exception e){
                        safe_DB.createDependants_Table();
                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                    }
                }
            }
        }


    }
    //------------------------------------------OVERRIDE METHODS------------------------------------

    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=BUTTON CLICK LISTENER-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    public void add_contact_dependant(View view) {

        if (incoming_flag != null){

            if (incoming_flag.equals(common_code.CONTACTS_FLAG)){
                if (conts_deps_recycler.getAdapter() != null && conts_deps_recycler.getAdapter().getItemCount() < 3) {
                    ContactPersonDialog(null);
                } else {
                    common_code.Mysnackbar(const_contact_layout, "Maximum of 3 Contacts allowed",
                            Snackbar.LENGTH_SHORT).show();
                }
            }else if (incoming_flag.equals(common_code.DEPENDANTS_FLAG)){
                DependantsDialog(null);
                //Toast.makeText(getApplicationContext(), "Trying to add dependants", Toast.LENGTH_SHORT).show();
            }

        }


    }
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=BUTTON CLICK LISTENER-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

}
