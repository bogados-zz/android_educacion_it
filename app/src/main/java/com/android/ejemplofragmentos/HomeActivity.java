package com.android.ejemplofragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.ejemplofragmentos.fragments.DetalleFragment;
import com.android.ejemplofragmentos.fragments.ProductosFragments;
import com.android.ejemplofragmentos.model.Producto;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

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
        /*  init(new ImageLoderConfiguration.Builder(this).DiskAge  )  */
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
        /*Bundle bundle =  new Bundle() ;*/
        Bundle bundle = getIntent().getExtras();
        Toast.makeText(this, "Hola "+ bundle.getString("USER"), Toast.LENGTH_SHORT).show();
        this.doblePantalla = this.getSupportFragmentManager().findFragmentById(R.id.detalle)!= null;
    }

    @Override
    public void onDetailClickListener(Producto producto) {
        Toast.makeText(this, "Producto"+ producto.getNombre(),Toast.LENGTH_SHORT).show();
        if(doblePantalla){
            DetalleFragment detalleFragment = (DetalleFragment)getSupportFragmentManager().findFragmentById(R.id.detalle);
            detalleFragment.mostrarDetalle(producto);
        }else{
            Intent detalle = new Intent(this, DetalleActivity.class);
            Bundle bundle= new Bundle();
            bundle.putInt(DetalleActivity.EXTRA_ID, producto.getId());
            bundle.putDouble(DetalleActivity.EXTRA_PRECIO, producto.getPrecio());
            bundle.putString(DetalleActivity.EXTRA_NOMBRE, producto.getNombre());
            bundle.putString(DetalleActivity.EXTRA_IMAGEN, producto.getUrlImagen());
            detalle.putExtras(bundle);

            startActivity(detalle);

        }
    }
}
