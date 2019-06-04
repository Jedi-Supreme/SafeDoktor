package com.softedge.care_assist.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class CloudMessaging extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            DatabaseReference token_ref = FirebaseDatabase.getInstance().getReference("NotifyID")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("devToken");

            token_ref.setValue(s);
            //FirebaseMessaging.getInstance().subscribeToTopic();
        }


    }
}
