package com.softedge.care_assist.activities;

import android.Manifest;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.softedge.care_assist.R;

import java.lang.ref.WeakReference;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class VideoCallingActivity extends AppCompatActivity {

    private static final int RC_VIDEO_APP_PERM = 124;
    private static String API_KEY;
    private static String SESSION_ID;
    private static String TOKEN;
    WeakReference<VideoCallingActivity> weakVideo;

    FrameLayout doc_frame, pat_frame;
    TextInputEditText et_vidchat_msg;
    Button bt_vidchat_send;

    //============================================ON CREATE=========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_calling);

        weakVideo = new WeakReference<>(this);

        doc_frame = findViewById(R.id.doc_frame);
        pat_frame = findViewById(R.id.pat_frame);

        et_vidchat_msg = findViewById(R.id.et_vidchat_msg);
        bt_vidchat_send = findViewById(R.id.bt_vidchat_send);

        requestPermissions();

    }
    //============================================ON CREATE=========================================

    //--------------------------------------------OVERRIDE METHODS----------------------------------
    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, weakVideo.get());
    }

    @AfterPermissionGranted(RC_VIDEO_APP_PERM)
    private void requestPermissions() {

        String[] perms = {Manifest.permission.INTERNET, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};

        if (EasyPermissions.hasPermissions(weakVideo.get(), perms)) {

            // initialize and connect to the session


        } else {
            EasyPermissions.requestPermissions(weakVideo.get(),
                    "Camera and Microphone permissions needed. Please allow in App Settings to make video calls", RC_VIDEO_APP_PERM, perms);
        }
    }

    //-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-DEFINED METHODS-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=



    //-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-DEFINED METHODS-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=


}
