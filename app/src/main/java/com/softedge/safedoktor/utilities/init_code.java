package com.softedge.safedoktor.utilities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.activities.DashboardActivity;
import com.softedge.safedoktor.activities.LoginActivity;
import com.softedge.safedoktor.databases.SafeDB;
import com.softedge.safedoktor.models.fireModels.PatientPackage.Address;
import com.softedge.safedoktor.models.fireModels.PatientPackage.Biography;
import com.softedge.safedoktor.models.fireModels.PatientPackage.ContactPerson;
import com.softedge.safedoktor.models.fireModels.PatientPackage.Physicals;

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

        String promo = "Promotions";
        SharedPreferences promoPref = common_code.appPref(activity);
        SharedPreferences.Editor proEditor = promoPref.edit();

        SafeDB safe_db = new SafeDB(activity,null);

        final DatabaseReference bio_ref = FirebaseDatabase.getInstance().getReference(all_users)
                .child(Biography.TABLE);
        final DatabaseReference address_ref = FirebaseDatabase.getInstance().getReference(all_users)
                .child(Address.TABLE);
        final DatabaseReference phy_ref = FirebaseDatabase.getInstance().getReference(all_users)
                .child(Physicals.TABLE);
        final DatabaseReference promo_ref = FirebaseDatabase.getInstance().getReference(promo);
        final DatabaseReference contacts_ref = FirebaseDatabase.getInstance().getReference(all_users)
                .child(ContactPerson.TABLE);

        bio_ref.child(fireID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Biography userBio = dataSnapshot.getValue(Biography.class);

                if (userBio != null){

                    try {
                        safe_db.addPat_bio(userBio);

                        if(userBio.getPropic_url() != null && activity instanceof DashboardActivity){
                            ((DashboardActivity) activity).loadLocal_data();
                        }

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

        address_ref.child(fireID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Address address = dataSnapshot.getValue(Address.class);

                if (address != null){
                    proEditor.putString(Address.LOC_ADDRESS,address.getLoc_address());
                    proEditor.apply();
                }

                address_ref.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        phy_ref.child(fireID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Physicals physicals = dataSnapshot.getValue(Physicals.class);

                if (physicals != null){
                    proEditor.putString(Physicals.BLOOD_GROUP,physicals.getBlood_group());
                    proEditor.putInt(Physicals.HEIGHT,(int)physicals.getHeight());
                    proEditor.putInt(Physicals.WEIGHT,(int)physicals.getWeight());
                    proEditor.apply();
                }
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
                    //Toast.makeText(activity.getApplicationContext(),promotxt,Toast.LENGTH_LONG).show();
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
