package com.android.ejemplofragmentos.Activities.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.net.ContentHandler;
import java.util.Set;

/**
 * Created by sbogado on 26/10/16.
 */

public class SettingManager {


    private static final String FOR_REGISTRATION ="FOR_REGISTRATION" ;

    private static SettingManager instance;
    private static final String SETTINGS_PREFERENCE = "SETTINGS_PREF";
    private static final String ONLY_WIFI="ONLY_WIFI";




    private SharedPreferences sharedPreferences;


    public static SettingManager getInstance(Context context) {
        if(instance==null){
            instance =new SettingManager(context);
        }
        return  instance;
    }

    private SettingManager(Context context){
        sharedPreferences = context.getSharedPreferences(SETTINGS_PREFERENCE, Context.MODE_PRIVATE);
    }


    public boolean isOnlyWifi(){
        return sharedPreferences.getBoolean(ONLY_WIFI,false);
    }

    public void setOnlyWifi(boolean onlyWifi){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(ONLY_WIFI,onlyWifi);
        editor.apply();
    }

    public boolean getFromRegistration(){
        return sharedPreferences.getBoolean(FOR_REGISTRATION, false);
    }

    public void setFromRegistration(boolean register){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(FOR_REGISTRATION, register);
        editor.apply();
    }




}
