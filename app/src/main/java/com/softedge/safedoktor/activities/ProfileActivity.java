package com.softedge.safedoktor.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.softedge.safedoktor.BuildConfig;
import com.softedge.safedoktor.models.fireModels.PatientPackage.Biography;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.utilities.common_code;
import com.softedge.safedoktor.databases.SafeDB;
import com.softedge.safedoktor.models.GlideApp;

import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private final int GET_FROM_CAMERA = 201;
    private final int GET_FROM_GALLERY = 202;

    CircleImageView iv_profile_pic, iv_profile_pic_bt;
    ConstraintLayout const_profile_layout;
    TextView tv_profile_fullname, tv_app_version;

    ProgressBar probar_profile;

    Button bt_profile_acc, bt_profile_bio, bt_profile_pers, bt_profile_med, bt_profile_medhis;

    AlertDialog pic_dialog_builder;
    WeakReference<ProfileActivity> weak_profile;

    Biography appUser_bio;
    SafeDB safe_db;

    //=============================================ON CREATE========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        weak_profile = new WeakReference<>(this);

        safe_db = new SafeDB(weak_profile.get(), null);

        //--------------------------------------HOME BUTTON ON APP BAR------------------------------
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //--------------------------------------HOME BUTTON ON APP BAR------------------------------

        iv_profile_pic = findViewById(R.id.iv_profile_pic);
        iv_profile_pic_bt = findViewById(R.id.iv_profile_pic_bt);
        const_profile_layout = findViewById(R.id.const_profile_layout);
        tv_profile_fullname = findViewById(R.id.tv_profile_fullname);
        tv_app_version = findViewById(R.id.tv_app_version);

        probar_profile = findViewById(R.id.probar_profile);

        bt_profile_acc = findViewById(R.id.bt_profile_account);
        bt_profile_bio = findViewById(R.id.bt_profile_bio);
        bt_profile_pers = findViewById(R.id.bt_profile_pers);
        bt_profile_med = findViewById(R.id.bt_profile_medication);
        bt_profile_medhis = findViewById(R.id.bt_profile_medhistory);

        iv_profile_pic_bt.setOnClickListener(this);
        iv_profile_pic.setOnClickListener(this);

        bt_profile_acc.setOnClickListener(this);
        bt_profile_bio.setOnClickListener(this);
        bt_profile_pers.setOnClickListener(this);
        bt_profile_med.setOnClickListener(this);
        bt_profile_medhis.setOnClickListener(this);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            appUser_bio = safe_db.local_appUser(FirebaseAuth.getInstance().getCurrentUser().getUid());
        }

        String appver = getResources().getString(R.string.app_name) + " Version "+BuildConfig.VERSION_NAME;
        tv_app_version.setText(appver);
    }
    //=============================================ON CREATE========================================

    //--=--=--=--=--=--=--=--=--=--=--=--=--=--=--OVERRIDE METHODS=--=--=--=--=--=--=--=--=--=--=--=


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == common_code.CAMERA_AUDIO_REQ_CODE){

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        pic_dialog_builder.dismiss();

        switch (requestCode) {

            case GET_FROM_CAMERA:
                if (resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap;

                    if (extras != null) {
                        imageBitmap = (Bitmap) extras.get("data");
                        pic_preview_dialog(imageBitmap);
                    }

                }
                break;

            case GET_FROM_GALLERY:
                if (resultCode == RESULT_OK) {
                    pic_preview_dialog(data.getData());
                }
                break;
        }
    }

    //activity onclick listener implementation
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.iv_profile_pic:
            case R.id.iv_profile_pic_bt:
                profile_pic_dialog();
                break;

            case R.id.bt_profile_pers:
                toContacts();
                break;

            case R.id.bt_profile_account:
                Intent acc_intent = new Intent(getApplicationContext(), AccountActivity.class);
                authDialog(acc_intent);
                break;

            case R.id.bt_profile_bio:
                toBiography();
                break;


            case R.id.bt_profile_medhistory:
                Intent medhis_intent = new Intent(getApplicationContext(), MedicalHistoryActivity.class);
                authDialog(medhis_intent);

                //toMedHistory();
                break;

        }
    }

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

        if (appUser_bio != null) {
            String fullname = appUser_bio.getFirstname() + " " + appUser_bio.getLastname();
            tv_profile_fullname.setText(fullname);

            if (appUser_bio.getPropic_url() != null ) {

                if (!appUser_bio.getPropic_url().isEmpty() || !appUser_bio.getPropic_url().equals("")){
                    GlideApp.with(weak_profile.get())
                            .load(appUser_bio.getPropic_url())
                            .into(iv_profile_pic);
                }

            }
        }

    }

    //--=--=--=--=--=--=--=--=--=--=--=--=--=--=--OVERRIDE METHODS=--=--=--=--=--=--=--=--=--=--=--=

    //----------------------------------------------METHODS-----------------------------------------

    void login_with_credentials(final String email, String password, final Intent toActivity) {

        probar_profile.setVisibility(View.VISIBLE);

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        startActivity(toActivity);
                        probar_profile.setVisibility(View.GONE);

                    } else {
                        // If sign in fails, display a message to the user.
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                            common_code.Mysnackbar(const_profile_layout, "Wrong Password",
                                    Snackbar.LENGTH_SHORT).show();

                            probar_profile.setVisibility(View.GONE);

                        }


                    }

                });

    }

    //--------------------------------------SAVE TO ONLINE DB---------------------------------------
    void save_Online(Biography fireBio) {
        DatabaseReference all_users_ref = FirebaseDatabase.getInstance().getReference(getResources().getString(R.string.all_users));

        //save user details to All_Users/Biography/Uid
        all_users_ref.child(Biography.TABLE).child(fireBio.getFirebase_Uid())
                .setValue(fireBio).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                probar_profile.setVisibility(View.GONE);
                common_code.Mysnackbar(const_profile_layout, "Profile picture Changed Successfully",
                        Snackbar.LENGTH_LONG).show();
            } else {
                probar_profile.setVisibility(View.GONE);
                common_code.Mysnackbar(const_profile_layout, "Biography Update Failed, Please Try again later",
                        Snackbar.LENGTH_SHORT).show();
            }
        });
    }
    //--------------------------------------SAVE TO ONLINE DB---------------------------------------

    //build dialog for password reauthentication
    void authDialog(final Intent activity_intent) {

        final AlertDialog authdialog = new AlertDialog.Builder(weak_profile.get()).create();

        View authview = LayoutInflater.from(weak_profile.get())
                .inflate(R.layout.diag_re_auth, const_profile_layout, false);

        final TextInputEditText et_authpass = authview.findViewById(R.id.et_auth_pass);

        Button bt_auth_accept = authview.findViewById(R.id.bt_auth_accept);
        Button bt_auth_cancel = authview.findViewById(R.id.bt_auth_cancel);

        bt_auth_accept.setOnClickListener(v -> {
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {

                String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                String password = et_authpass.getText().toString();

                if (!password.isEmpty() || !password.equals("")) {
                    login_with_credentials(email, password, activity_intent);
                    authdialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "Please Enter password", Toast.LENGTH_SHORT).show();
                }


            }
        });

        bt_auth_cancel.setOnClickListener(v -> authdialog.dismiss());

        authdialog.setView(authview);
        authdialog.show();
    }

    //build dialog for user to select profile pic
    void profile_pic_dialog() {
        pic_dialog_builder = new AlertDialog.Builder(weak_profile.get()).create();

        View propic_view = LayoutInflater.from(weak_profile.get())
                .inflate(R.layout.diag_propic_src, const_profile_layout, false);

        pic_dialog_builder.setCancelable(true);
        pic_dialog_builder.setView(propic_view);

        Button bt_camera = propic_view.findViewById(R.id.bt_set_camera);
        Button bt_gallery = propic_view.findViewById(R.id.bt_set_gallery);

        bt_camera.setOnClickListener(v -> {
            if (common_code.checkPermissionForCameraAndMicrophone(weak_profile.get())){
                cameraPic();
            }else {
                common_code.requestPermissionForCameraAndMicrophone(weak_profile.get());
            }
        });

        bt_gallery.setOnClickListener(v -> {
            galleryPic();
        });

        pic_dialog_builder.show();

    }

    //user previews selected picture from camera
    void pic_preview_dialog(final Bitmap profile_pic_bitmap) {

        final AlertDialog preview_dialog_builder = new AlertDialog.Builder(weak_profile.get()).create();

        View propic_view = LayoutInflater.from(weak_profile.get())
                .inflate(R.layout.diag_pic_preview, const_profile_layout, false);

        preview_dialog_builder.setCancelable(true);
        preview_dialog_builder.setView(propic_view);

        CircleImageView iv_prev_propic = propic_view.findViewById(R.id.iv_prev_propic);
        Button bt_prev_accept = propic_view.findViewById(R.id.bt_prev_accept);
        Button bt_prev_cancel = propic_view.findViewById(R.id.bt_prev_cancel);

        try {
            iv_prev_propic.setImageBitmap(profile_pic_bitmap);
        } catch (Exception ignored) {
        }


        //user accepted to set selected pic
        bt_prev_accept.setOnClickListener(v -> {
            probar_profile.setVisibility(View.VISIBLE);
            iv_profile_pic.setImageBitmap(profile_pic_bitmap);
            upload_profile_pic(appUser_bio.getFirebase_Uid(), profile_pic_bitmap);
            preview_dialog_builder.dismiss();
        });

        //user declined pic setting process
        bt_prev_cancel.setOnClickListener(v -> preview_dialog_builder.dismiss());

        preview_dialog_builder.show();
    }

    //preview overload for gallery
    void pic_preview_dialog(final Uri profile_pic_path) {

        final AlertDialog preview_dialog_builder = new AlertDialog.Builder(weak_profile.get()).create();

        View propic_view = LayoutInflater.from(weak_profile.get()).inflate(R.layout.diag_pic_preview, const_profile_layout,
                false);

        preview_dialog_builder.setCancelable(true);
        preview_dialog_builder.setView(propic_view);

        CircleImageView iv_prev_propic = propic_view.findViewById(R.id.iv_prev_propic);
        Button bt_prev_accept = propic_view.findViewById(R.id.bt_prev_accept);
        Button bt_prev_cancel = propic_view.findViewById(R.id.bt_prev_cancel);

        try {
            iv_prev_propic.setImageURI(profile_pic_path);
        } catch (Exception ignored) {
        }


        //user accepted to set selected pic
        bt_prev_accept.setOnClickListener(v -> {
            probar_profile.setVisibility(View.VISIBLE);
            upload_profile_pic(appUser_bio.getFirebase_Uid(), profile_pic_path);
            iv_profile_pic.setImageURI(profile_pic_path);
            preview_dialog_builder.dismiss();
        });

        //user declined pic setting process
        bt_prev_cancel.setOnClickListener(v -> preview_dialog_builder.dismiss());

        preview_dialog_builder.show();
    }

    //upload pic from camera
    void upload_profile_pic(String fire_id, Bitmap bitmap) {

        probar_profile.setVisibility(View.VISIBLE);

        FirebaseStorage pic_storage = FirebaseStorage.getInstance();

        if (bitmap != null) {
            UploadTask uptask;
            final StorageReference pic_ref = pic_storage.getReference().child("Profile_pictures/" + fire_id + ".png");
            Bitmap scaled_bitmap = Bitmap.createScaledBitmap(bitmap, 600, 600, true);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            scaled_bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            byte[] pic_data = outputStream.toByteArray();

            uptask = pic_ref.putBytes(pic_data);

            uptask.continueWithTask(task -> pic_ref.getDownloadUrl()).addOnCompleteListener(task -> {

                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();

                    if (downloadUri != null) {
                        appUser_bio.setPropic_url(downloadUri.toString());
                        safe_db.updatePat_bio(appUser_bio);
                    }

                    //update biodata online after uploading pic
                    save_Online(appUser_bio);

                } else {

                    // Handle failures
                    probar_profile.setVisibility(View.GONE);
                    common_code.Mysnackbar(const_profile_layout, "Profile picture upload failed, Please try again later",
                            Snackbar.LENGTH_LONG).show();
                }

            });

        }
    }

    //upload pic from gallery
    void upload_profile_pic(String fire_id, Uri profile_pic_path) {

        FirebaseStorage pic_storage = FirebaseStorage.getInstance();

        if (profile_pic_path != null) {

            UploadTask uptask;
            final StorageReference pic_ref = pic_storage.getReference().child("Profile_pictures/" + fire_id + ".png");
            //Bitmap scaled_bitmap = Bitmap.createScaledBitmap(bitmap,200,200,true);
            uptask = pic_ref.putFile(profile_pic_path);

            uptask.continueWithTask(task -> {

                // Continue with the task to get the download URL
                return pic_ref.getDownloadUrl();

            }).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();

                    if (downloadUri != null) {
                        appUser_bio.setPropic_url(downloadUri.toString());

                        safe_db.updatePat_bio(appUser_bio);
                    }

                    //update biodata online after uploading pic
                    save_Online(appUser_bio);

                } else {

                    // Handle failures
                    probar_profile.setVisibility(View.GONE);
                    common_code.Mysnackbar(const_profile_layout, "Profile picture upload failed, Please try again later",
                            Snackbar.LENGTH_LONG).show();
                }
            });

        }
    }
    //----------------------------------------------METHODS-----------------------------------------

    //================================================INTENTS=======================================
    void toBiography() {

        Intent bio_intent = new Intent(getApplicationContext(), BiographyActivity.class);
        startActivity(bio_intent);

    }

    void toContacts() {

        Intent contact_intent = new Intent(getApplicationContext(), ContactsActivity.class);
        startActivity(contact_intent);
    }

    void cameraPic(){
        Intent start_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (start_camera.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(start_camera, GET_FROM_CAMERA);
        }
    }

    void galleryPic(){
        Intent gallery_pic = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);

        if (gallery_pic.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(gallery_pic, GET_FROM_GALLERY);
        }
    }

    /*void toMedHistory(){
        Intent history_intent = new Intent(getApplicationContext(), MedicalHistoryActivity.class);
        startActivity(history_intent);
    }*/
    //================================================INTENTS=======================================


}
