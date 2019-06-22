package com.softedge.care_assist.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.softedge.care_assist.R;
import com.softedge.care_assist.activities.LoginActivity;
import com.softedge.care_assist.databases.SafeDB;
import com.softedge.care_assist.models.fireModels.PatientPackage.Biography;
import com.softedge.care_assist.models.fireModels.PatientPackage.ContactPerson;

public class init_code {

    //logout user
    public static void logout(Activity ctx){
        FirebaseAuth.getInstance().signOut();

        Intent login_intent = new Intent(ctx, LoginActivity.class);
        login_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ctx.startActivity(login_intent);
        ctx.finish();
    }

    public static void saveDeviceToken(){

        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            DatabaseReference personal_token_ref = FirebaseDatabase.getInstance().getReference("NotifyID")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("devToken");

            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(task -> {

                        if (task.getResult() != null && task.isSuccessful()){
                            String token = task.getResult().getToken();
                            personal_token_ref.setValue(token);
                        }
                        // Get new Instance ID token
                    });

        }
    }

    public static void loadBioData_online(Activity activity, String fireID){

        String all_users = activity.getResources().getString(R.string.all_users);
        String biography = activity.getResources().getString(R.string.bio_ref);
        String contacts = activity.getResources().getString(R.string.contacts_ref);

        String promo = "Promotions";
        SharedPreferences promoPref = common_code.appPref(activity);
        SharedPreferences.Editor proEditor = promoPref.edit();

        SafeDB safe_db = new SafeDB(activity,null);

        final DatabaseReference bio_ref = FirebaseDatabase.getInstance().getReference(all_users).child(biography);
        final DatabaseReference promo_ref = FirebaseDatabase.getInstance().getReference(promo);
        final DatabaseReference contacts_ref = FirebaseDatabase.getInstance().getReference(all_users).child(contacts);

        bio_ref.child(fireID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Biography userBio = dataSnapshot.getValue(Biography.class);

                if (userBio != null){

                    try {
                        safe_db.addPat_bio(userBio);
                    }catch (Exception ignored){
                        safe_db.updatePat_bio(userBio);
                    }
                }

                bio_ref.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        promo_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String promotxt = dataSnapshot.getValue(String.class);

                if (promotxt != null){
                    proEditor.putString("promo",promotxt).apply();
                }

                promo_ref.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        contacts_ref.child(fireID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot singlecontact : dataSnapshot.getChildren()){

                    ContactPerson contactPerson = singlecontact.getValue(ContactPerson.class);

                    if (contactPerson!= null){
                        try {
                            safe_db.addContact(contactPerson);
                        }catch (Exception ignored){
                            safe_db.updateContact(contactPerson);
                        }
                    }
                }


                contacts_ref.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}