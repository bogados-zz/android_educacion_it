package com.android.ejemplofragmentos.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;

import com.android.ejemplofragmentos.Activities.utils.SettingManager;
import com.android.ejemplofragmentos.R;
import com.android.ejemplofragmentos.services.RegistrationService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class SettingsActivity extends AppCompatActivity {

    private CheckBox onlyWifi;
    private Button registrar;
    private ProgressBar progressBar;
    private RegistrationReceiver registrationReceiver;
    private IntentFilter intentFilter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        registrationReceiver = new RegistrationReceiver();
        intentFilter = new IntentFilter(RegistrationService.REGISTRATION_SUCCESS_ACTION);


        onlyWifi = (CheckBox) findViewById(R.id.only_wifi);
        onlyWifi.setChecked(getIsCheckedFromSharedPreference());
        onlyWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingsActivity.this.writeOnlyWifiOption(isChecked);
            }
        });
        registrar = (Button) findViewById(R.id.registry_into_fcm);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                startService(new Intent(SettingsActivity.this, RegistrationService.class));
            }
        });


        if(SettingManager.getInstance(this).getFromRegistration()){
            registrar.setText("Registrado correctamente");
            registrar.setEnabled(false);
        }
        checkPlayServices();

    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver(registrationReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(registrationReceiver);
    }

    private void writeOnlyWifiOption(boolean isChecked) {
        SettingManager.getInstance(this).setOnlyWifi(isChecked);
    }


    private Boolean getIsCheckedFromSharedPreference(){
        Boolean isOnlyWifi = SettingManager.getInstance(this).isOnlyWifi();
        return isOnlyWifi==null?true:isOnlyWifi;
    }


    //TODO finish
    private void checkPlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {

            }
        }
    }



    public class RegistrationReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            registrar.setText("Registrado correctamente");
            registrar.setEnabled(false);
        }


    }

}
