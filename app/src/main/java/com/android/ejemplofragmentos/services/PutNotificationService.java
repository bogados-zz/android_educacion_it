package com.android.ejemplofragmentos.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.android.ejemplofragmentos.Activities.HomeActivity;
import com.android.ejemplofragmentos.R;
import com.google.android.gms.gcm.GcmListenerService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sbogado on 08/11/16.
 */

public class PutNotificationService extends GcmListenerService {

    @Override
    public void onMessageReceived(String s, Bundle bundle) {
        super.onMessageReceived(s, bundle);
        String message = bundle.getString("message");
        showNotification(message);
    }


    private void showNotification(String message){
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 ,intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this).setContentIntent(pendingIntent).setContentTitle("Sincronizacion").setSound(sound).setContentText(message).setSmallIcon(R.mipmap.ic_launcher).setPriority(NotificationCompat.PRIORITY_HIGH).build();
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // SI creo una notification diferente y de dejo el id 0 => entonces, reemplazo el notify 0
        nm.notify(0,notification);
    }

}
