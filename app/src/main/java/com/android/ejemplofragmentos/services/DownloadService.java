package com.android.ejemplofragmentos.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.android.ejemplofragmentos.daos.ProductoDao;
import com.android.ejemplofragmentos.model.Producto;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

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
        stopSelf();
    }

    /*@Override
    public void onDestroy() {
        Log.v(TAG, "Paso por el onDestroy");
        super.onDestroy();
    }*/

}
