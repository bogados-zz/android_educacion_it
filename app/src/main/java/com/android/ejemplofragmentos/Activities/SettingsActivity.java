package com.android.ejemplofragmentos.Activities;

import android.support.annotation.CheckResult;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.android.ejemplofragmentos.Activities.utils.SettingManager;
import com.android.ejemplofragmentos.R;

public class SettingsActivity extends AppCompatActivity {

    private CheckBox onlyWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        onlyWifi = (CheckBox) findViewById(R.id.only_wifi);
        onlyWifi.setChecked(getIsCheckedFromSharedPreference());
        onlyWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SettingsActivity.this.writeOnlyWifiOption(isChecked);
            }
        });

    }

    private void writeOnlyWifiOption(boolean isChecked) {
        SettingManager.getInstance(this).setOnlyWifi(isChecked);
    }


    private Boolean getIsCheckedFromSharedPreference(){
        Boolean isOnlyWifi = SettingManager.getInstance(this).isOnlyWifi();
        return isOnlyWifi==null?true:isOnlyWifi;
    }


}
