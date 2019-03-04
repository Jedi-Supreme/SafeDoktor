package com.softedge.safedoktor.activities;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class VideoCallingActivity extends AppCompatActivity implements Session.SessionListener, PublisherKit.PublisherListener {

    private static final int RC_VIDEO_APP_PERM = 124;
    private static String API_KEY = "46279932";
    private static String SESSION_ID = "2_MX40NjI3OTkzMn5-MTU1MTY5MDk4OTY4OX5ZTWtQZGpSeWxteWJDWFB2eDMvc2xKU2x-fg";
    private static String TOKEN = "T1==cGFydG5lcl9pZD00NjI3OTkzMiZzaWc9MjQ5ZWNjNjliOTU5N2JkM2ZhM2UzMDliNjJkZWI1ZDQ0NDExNjAzNDpzZXNzaW9uX2lkPTJfTVg0ME5qSTNPVGt6TW41LU1UVTFNVFk1TURrNE9UWTRPWDVaVFd0UVpHcFNlV3h0ZVdKRFdGQjJlRE12YzJ4S1UyeC1mZyZjcmVhdGVfdGltZT0xNTUxNjkxMDU0Jm5vbmNlPTAuNDE2Nzc5OTEyMzkxNjAyNSZyb2xlPXB1Ymxpc2hlciZleHBpcmVfdGltZT0xNTUxNzEyNzUwJmluaXRpYWxfbGF5b3V0X2NsYXNzX2xpc3Q9";
    WeakReference<VideoCallingActivity> weakVideo;
    FrameLayout doc_frame, pat_frame;

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
