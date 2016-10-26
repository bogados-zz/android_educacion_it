package com.android.ejemplofragmentos.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.webkit.DownloadListener;

import com.android.ejemplofragmentos.services.DownloadService;

/**
 * Created by sbogado on 26/10/16.
 */

public class NetworkReceiver extends BroadcastReceiver {

    private static String TAG = "Conection activity";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(TAG,"Network changed");

        //Esto se maneja con todas las redes.
        ConnectivityManager conn = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);


        //Esta viendo si esta conectado
        if(conn.getActiveNetworkInfo().isConnectedOrConnecting()){
            Intent download = new Intent(context, DownloadService.class);
            context.startService(download);

        }
    }
}
