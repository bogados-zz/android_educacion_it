package com.android.ejemplofragmentos.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.android.ejemplofragmentos.Activities.HomeActivity;
import com.android.ejemplofragmentos.R;
import com.android.ejemplofragmentos.daos.ProductoDao;
import com.android.ejemplofragmentos.model.Producto;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DownloadService extends Service implements Response.ErrorListener, Response.Listener<Producto[]>{

    private static final String TAG = "Download service";
    public static final String ACTION_DOWNLOAD_SUCCESS="com.android.ejemplofragmentos.fragments.DOWNLOAD_SUCCESS";
    public static final String ACTION_DOWNLOAD_ERROR="com.android.ejemplofragmentos.fragments.DOWNLOAD_ERROR";

    private GsonRequest<Producto[]> productsRequest;

    private ProductoDao dao;

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG, "Paso por el onBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.productsRequest = new GsonRequest<>(Request.Method.GET, "http://webkathon.com/pruebasit/products.php", Producto[].class, this,this);
        Log.v(TAG, "DownloadService created");
    }


    /*@Override
    public boolean onUnbind(Intent intent) {
        Log.v(TAG, "Paso por el onUnbind");
        return super.onUnbind(intent);

    }*/


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "DownloadService created  "+ startId);
        /*Se autostopea*/
        /*stopSelf();*/

        /*Inicializa cuando pueda
        return START_STICKY*/

        /*No levante cuando pueda*/


        Volley.newRequestQueue(this).add(productsRequest);
        return START_NOT_STICKY;

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.v(TAG, "Download completed");
        Intent errorEvent = new Intent(ACTION_DOWNLOAD_ERROR);
        //Esta clase se usa solo para eventos internos de la application
        LocalBroadcastManager.getInstance(this).sendBroadcast(errorEvent);
        stopSelf();
    }

    @Override
    public void onResponse(Producto[] response) {
        dao= new ProductoDao(this.getBaseContext());
        Log.v(TAG, "Download completed");
        for(int i = 0 ; i < response.length;i++){
            dao.save(response[i]);
        }
        Intent successEvent = new Intent(ACTION_DOWNLOAD_SUCCESS);
        LocalBroadcastManager.getInstance(this).sendBroadcast(successEvent);
        showNotification();
        stopSelf();
    }

    private void showNotification(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 ,intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this).setContentIntent(pendingIntent).setContentTitle("Sincronizacion").setSound(sound).setContentText("Se realizo la sincronizacion a las: " + simpleDateFormat.format(new Date())).setSmallIcon(R.mipmap.ic_launcher).setPriority(NotificationCompat.PRIORITY_HIGH).build();
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // SI creo una notification diferente y de dejo el id 0 => entonces, reemplazo el notify 0
        nm.notify(0,notification);
    }

    /*@Override
    public void onDestroy() {
        Log.v(TAG, "Paso por el onDestroy");
        super.onDestroy();
    }*/

}
