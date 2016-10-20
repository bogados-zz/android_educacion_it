package com.android.ejemplofragmentos;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.ejemplofragmentos.fragments.DetalleFragment;

public class DetalleActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "ID";
    public static final String EXTRA_IMAGEN = "IMAGEN";
    public static final String EXTRA_NOMBRE = "NOMBRE";
    public static final String EXTRA_PRECIO = "PRECIO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        DetalleFragment detalleFragment = DetalleFragment.newInstance(getIntent().getExtras());
        FragmentTransaction ft  = getSupportFragmentManager().beginTransaction();
                // El tag lo puedo usar si lo genero a través de codigo. Los tags no deberian repetirse en el activity
                ft.add(R.id.container, detalleFragment, "Detalle");
                ft.commit();

    }
}
