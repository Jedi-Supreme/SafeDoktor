package com.softedge.safedoktor.activities;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.opentok.android.OpentokError;
import com.opentok.android.Publisher;
import com.opentok.android.PublisherKit;
import com.opentok.android.Session;
import com.opentok.android.Stream;
import com.opentok.android.Subscriber;
import com.softedge.safedoktor.R;

import org.json.JSONException;

import java.lang.ref.WeakReference;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class VideoCallingActivity extends AppCompatActivity implements Session.SessionListener,
        PublisherKit.PublisherListener {

    private static final int RC_VIDEO_APP_PERM = 124;
    private static String API_KEY;
    private static String SESSION_ID;
    private static String TOKEN;
    WeakReference<VideoCallingActivity> weakVideo;

    FrameLayout doc_frame, pat_frame;
    TextInputEditText et_vidchat_msg;
    Button bt_vidchat_send;

    private Session mSession;
    private Subscriber mSubscriber;

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
            fetchSessionConnectionData();

        } else {
            EasyPermissions.requestPermissions(weakVideo.get(),
                    "Camera and Microphone permissions needed. Please allow in App Settings to make video calls", RC_VIDEO_APP_PERM, perms);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mSession != null) {
            mSession.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mSession != null) {
            mSession.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSession != null) {
            mSession.disconnect();
        }
    }

    //------------------------------------------------SESSION LISTENER------------------------------
    @Override
    public void onConnected(Session session) {

        Publisher mPublisher = new Publisher.Builder(this).build();
        mPublisher.setPublisherListener(this);

        pat_frame.addView(mPublisher.getView());
        mSession.publish(mPublisher);

    }

    @Override
    public void onDisconnected(Session session) {

    }

    @Override
    public void onStreamReceived(Session session, Stream stream) {

        if (mSubscriber == null) {
            mSubscriber = new Subscriber.Builder(weakVideo.get(), stream).build();
            mSession.subscribe(mSubscriber);
            doc_frame.addView(mSubscriber.getView());
        }

    }

    @Override
    public void onStreamDropped(Session session, Stream stream) {

        if (mSubscriber != null) {
            mSubscriber = null;
            doc_frame.removeAllViews();
        }

    }

    @Override
    public void onError(Session session, OpentokError opentokError) {

    }
    //------------------------------------------------SESSION LISTENER------------------------------

    //------------------------------------PUBLISHER LISTENER----------------------------------------
    @Override
    public void onStreamCreated(PublisherKit publisherKit, Stream stream) {

    }

    @Override
    public void onStreamDestroyed(PublisherKit publisherKit, Stream stream) {

    }

    @Override
    public void onError(PublisherKit publisherKit, OpentokError opentokError) {

    }
    //------------------------------------PUBLISHER LISTENER----------------------------------------
    //--------------------------------------------OVERRIDE METHODS----------------------------------

    //-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-DEFINED METHODS-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public void fetchSessionConnectionData() {
        RequestQueue reqQueue = Volley.newRequestQueue(weakVideo.get());
        reqQueue.add(new JsonObjectRequest(Request.Method.GET,
                "https://safe-doktor.herokuapp.com" + "/session",
                null, response -> {
            try {
                API_KEY = response.getString("apiKey");
                SESSION_ID = response.getString("sessionId");
                TOKEN = response.getString("token");

                mSession = new Session.Builder(weakVideo.get(), API_KEY, SESSION_ID).build();
                mSession.setSessionListener(weakVideo.get());
                mSession.connect(TOKEN);

            } catch (JSONException jexcpt) {
                Toast.makeText(getApplicationContext(), jexcpt.toString(), Toast.LENGTH_LONG).show();
            }
        }, error -> {

        }));
    }

    //-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-DEFINED METHODS-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=


}
