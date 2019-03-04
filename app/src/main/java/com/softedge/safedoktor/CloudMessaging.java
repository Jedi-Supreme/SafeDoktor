package com.softedge.safedoktor;

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
            DatabaseReference token_ref = FirebaseDatabase.getInstance().getReference("Notification_id")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("device_token_key");

            token_ref.setValue(s);

            //FirebaseMessaging.getInstance().subscribeToTopic();
        }


    }
}
