package com.android.ejemplofragmentos.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.android.ejemplofragmentos.Activities.utils.SettingManager;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by sbogado on 08/11/16.
 */

public class RegistrationService extends IntentService {
    private static final String TAG = "ResgistrationService";

    public static final String REGISTRATION_SUCCESS_ACTION = "com.android.ejemplofragmentos.fragments.REGISTRATION_SUCCESS_ACTION";

    private static final String SERVER_ID = "246375657097";

    public RegistrationService() {
        super("RegistrationService");
    }

    /*Una vez que se ejecuta aca muere*/
    @Override
    protected void onHandleIntent(Intent intent) {
        InstanceID instanceID = InstanceID.getInstance(this);
        String token = null;
        try{
            token = instanceID.getToken(SERVER_ID, GoogleCloudMessaging.INSTANCE_ID_SCOPE,null);
            Log.v(TAG, "Registration token" + token);
            LocalBroadcastManager.getInstance(this);

        } catch (IOException e) {
            e.printStackTrace();
            SettingManager.getInstance(this).setFromRegistration(false);
        }
    }
}
