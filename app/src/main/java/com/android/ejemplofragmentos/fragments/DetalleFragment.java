package com.android.ejemplofragmentos.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.ejemplofragmentos.DetalleActivity;
import com.android.ejemplofragmentos.R;
import com.android.ejemplofragmentos.model.Producto;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by sbogado on 18/10/16.
 */

public class DetalleFragment extends Fragment {

    private TextView id;
    private TextView nombre;
    private TextView precio;
    private ImageView imagen;

    //Esto lo hago para no tener un contructor que reciva un producto
    public static DetalleFragment newInstance(Bundle args) {
        DetalleFragment fragment = new DetalleFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle){
        return inflater.inflate(R.layout.fragment_detalle, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imagen = (ImageView) view.findViewById(R.id.imagen);
        id= (TextView)view.findViewById(R.id.id);
        nombre= (TextView)view.findViewById(R.id.nombre);
        precio = (TextView)view.findViewById(R.id.precio);
        if(getArguments() != null){
            Bundle args = getArguments();

            Integer id = args.getInt(DetalleActivity.EXTRA_ID);
            String nombre = args.getString(DetalleActivity.EXTRA_NOMBRE);
            Double precio = args.getDouble(DetalleActivity.EXTRA_PRECIO);
            String urlNombre = args.getString(DetalleActivity.EXTRA_IMAGEN);

            Producto prod = new Producto();
            prod.setId(id);
            prod.setNombre(nombre);
            prod.setPrecio(precio);
            prod.setUrlImagen(urlNombre);

            mostrarDetalle(prod);


        }
    }

    public void mostrarDetalle(Producto producto){
        id.setText(producto.getId().toString());
        nombre.setText(producto.getNombre());
        precio.setText(producto.getPrecio().toString());
        //TODO: change with access a cache.
        ImageLoader.getInstance().displayImage(producto.getUrlImagen(), imagen);
        /*imagen.setImageBitmap();*/
        /*(ImageView) convertView.findViewById(R.id.imagen);*/
    }


}
