package com.android.ejemplofragmentos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.ejemplofragmentos.fragments.ProductosFragments;
import com.android.ejemplofragmentos.model.Producto;

public class HomeActivity extends AppCompatActivity implements ProductosFragments.OnDetailClickListener {

    private Boolean doblePantalla;
    /*
    * Ciclo de vida
    *   Metodo onCreate se llama unicamente cuando se crea.
    *   Cuando roto el dispositivo se crea la activity nuevamente
    *   No se debe guardar nada
    *
    *
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Clase R tiene que ser la de nuestro proyecto
        setContentView(R.layout.activity_home);
        Bundle bundle =  new Bundle() ;
        Toast.makeText(this, "Hola"+ bundle.get("USER"), Toast.LENGTH_SHORT).show();
        this.doblePantalla = this.getSupportFragmentManager().findFragmentById(R.id.detalle)!= null;
    }

    @Override
    public void onDetailClickListener(Producto producto) {
        Toast.makeText(this, "Producto"+ producto.getNombre(),Toast.LENGTH_SHORT).show();
        if(doblePantalla){

        }else{

        }
    }
}
