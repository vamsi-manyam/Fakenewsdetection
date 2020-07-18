package com.example.geofencingpa1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "GeofenceBroadcastReceiv";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        Toast.makeText(context, "Geofence triggered...", Toast.LENGTH_SHORT).show();

        NotificationHelper notificationHelper = new NotificationHelper(context);
        String ID=intent.getStringExtra("ID");
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);

        if (geofencingEvent.hasError()) {
            Log.d(TAG, "onReceive: Error receiving geofence event...");
            return;
        }

        List<Geofence> geofenceList = geofencingEvent.getTriggeringGeofences();
        String Identity = null;
        for (Geofence geofence: geofenceList) {
            Log.d(TAG, "onReceive: " + geofence.getRequestId());
             Identity=geofence.getRequestId();
        }
//        Location location = geofencingEvent.getTriggeringLocation();
        int transitionType = geofencingEvent.getGeofenceTransition();
        Phonestate state=new Phonestate(context);
        switch (transitionType) {
            case Geofence.GEOFENCE_TRANSITION_ENTER:
//                Toast.makeText(context, "c", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "GEOFENCE_TRANSITION_ENTER "+Identity);
                state.changeringer();
                notificationHelper.sendHighPriorityNotification("GEOFENCE_TRANSITION_ENTER "+Identity, "", MapsActivity.class);
                break;
            case Geofence.GEOFENCE_TRANSITION_DWELL:
                Toast.makeText(context, "GEOFENCE_TRANSITION_DWELL", Toast.LENGTH_SHORT).show();
                state.changeringer();
                notificationHelper.sendHighPriorityNotification("GEOFENCE_TRANSITION_DWELL"+Identity, "", MapsActivity.class);

                break;
            case Geofence.GEOFENCE_TRANSITION_EXIT:
                Toast.makeText(context, "GEOFENCE_TRANSITION_EXIT", Toast.LENGTH_SHORT).show();
                state.changenormal();
                notificationHelper.sendHighPriorityNotification("GEOFENCE_TRANSITION_EXIT"+Identity, "", MapsActivity.class);
                break;
        }

    }
}
