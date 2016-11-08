package com.android.ejemplofragmentos.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.ejemplofragmentos.R;

/**
 * Created by sbogado on 12/10/2016.
 */


public class LoginActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private Button loginButton;
    private Button createAccount;


    @Override
    protected void onCreate(@Nullable Bundle savedinstanceState){
        super.onCreate(savedinstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.login_user);
        password = (EditText) findViewById(R.id.login_password);
        loginButton =  (Button) findViewById(R.id.login_button);
        createAccount =  (Button) findViewById(R.id.login_create_account_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*una activity es un contexto*/
                        goHomeScreen();
                        /*Toast.makeText(LoginActivity.this, userName.getText().toString(), Toast.LENGTH_SHORT ).show();*/
                    }
                }

        );
    }


    private void goHomeScreen(){
        //Peticion al sistema operativo
        //Primer parametro me paso a mi mismo como contexto

        /**
         * Usar parseleable => android
         * Usar json
         * Ojo lo pasa por copia
         */
        Intent homeIntent = new Intent(this, HomeActivity.class);
        Bundle bundle =new Bundle();
        bundle.putString("USER", this.userName.getText().toString());
        homeIntent.putExtras(bundle);
        startActivity(homeIntent);
        finish();
    }
}
