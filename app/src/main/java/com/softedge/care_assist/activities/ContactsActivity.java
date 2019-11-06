package com.softedge.care_assist.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import com.softedge.care_assist.adapters.contacts_recycler_Adapter;
import com.softedge.care_assist.models.fireModels.PatientPackage.ContactPerson;
import com.softedge.care_assist.R;
import com.softedge.care_assist.utilities.common_code;
import com.softedge.care_assist.databases.SafeDB;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {

    RecyclerView contacts_recycler;
    ConstraintLayout const_contact_layout;

    WeakReference<ContactsActivity> weakcontact;

    SafeDB safe_DB;
    String fireID;

    //==========================================ON CREATE===========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        weakcontact = new WeakReference<>(this);
        safe_DB = new SafeDB(weakcontact.get(), null);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            fireID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        //--------------------------------------HOME BUTTON ON APP BAR------------------------------
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //--------------------------------------HOME BUTTON ON APP BAR------------------------------

        contacts_recycler = findViewById(R.id.contacts_recycler);
        const_contact_layout = findViewById(R.id.const_contact_layout);

    }
    //==========================================ON CREATE===========================================

    //-=--=-=--=-=--=-=--=-=--=-=--=-=--=-=--=-=--=--=--DEFINED METHODS-=--=-=--=-=--=-=--=-=--=-=--
    public void refresh_contacts_list() {
        contacts_recycler_Adapter contactsAdapter = new contacts_recycler_Adapter(this, safe_DB.contactsList(fireID));
        contacts_recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        contacts_recycler.setAdapter(contactsAdapter);
    }


    public void ContactPersonDialog(@Nullable final ContactPerson contactPerson) {

        final AlertDialog contactdialog = new AlertDialog.Builder(weakcontact.get()).create();

        contactdialog.setCancelable(true);

        View contactView = LayoutInflater.from(weakcontact.get())
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
                    save_Online(contacts_list);

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

    //--------------------------------------SAVE TO ONLINE DB---------------------------------------
    public void save_Online(ArrayList<ContactPerson> fireContacts) {

        DatabaseReference all_users_ref = FirebaseDatabase.getInstance().getReference(getResources().getString(R.string.all_users));

        //save user details to All_Users/Contacts/Uid
        all_users_ref.child(ContactPerson.TABLE).child(fireID)
                .setValue(fireContacts);
    }
    //--------------------------------------SAVE TO ONLINE DB---------------------------------------


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

        try {
            refresh_contacts_list();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "Unable to refresh contacts list with error " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    //------------------------------------------OVERRIDE METHODS------------------------------------

    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=BUTTON CLICK LISTENER-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    public void add_contact(View view) {
        if (contacts_recycler.getAdapter() != null && contacts_recycler.getAdapter().getItemCount() < 3) {
            ContactPersonDialog(null);
        } else {
            common_code.Mysnackbar(const_contact_layout, "Maximum of 3 Contacts allowed",
                    Snackbar.LENGTH_SHORT).show();
        }
    }
    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=BUTTON CLICK LISTENER-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

}
