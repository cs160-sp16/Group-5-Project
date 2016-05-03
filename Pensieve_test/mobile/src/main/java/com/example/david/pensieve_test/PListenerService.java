package com.example.david.pensieve_test;

/**
 * Created by david on 3/1/16.
 */
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;


public class PListenerService extends WearableListenerService {
    private static final String TAG = "@>@>@>@>";
    private static final String DATA = "/dataToPhone";
    private static final String NOTHING = "/send_nothing";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d(TAG, "in PListenerService, got: " + messageEvent.getPath());

        if( messageEvent.getPath().equalsIgnoreCase(DATA) ) {
            String data = new String(messageEvent.getData(), StandardCharsets.UTF_8);

            Intent intent = new Intent(this, Notification.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(DATA, data);
            startActivity(intent);

        } else if( messageEvent.getPath().equalsIgnoreCase(NOTHING) ) {
            String nothing = new String(messageEvent.getData(), StandardCharsets.UTF_8);

            Intent i = new Intent(this, TaskListActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        } else {
            Log.d(TAG, "does not match");
            super.onMessageReceived(messageEvent);
        }

    }

}