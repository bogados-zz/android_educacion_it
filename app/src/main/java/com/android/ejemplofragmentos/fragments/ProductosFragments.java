package com.android.ejemplofragmentos.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.ejemplofragmentos.ProductoAdapter;
import com.android.ejemplofragmentos.R;
import com.android.ejemplofragmentos.model.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbogado on 18/10/16.
 */

public class ProductosFragments extends android.support.v4.app.Fragment {

    private ListView productos;
    private OnDetailClickListener callback;

    public interface OnDetailClickListener{
        void onDetailClickListener(Producto producto);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        callback =  (OnDetailClickListener)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle){
        return inflater.inflate(R.layout.fragment_productos, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        productos = (ListView) view.findViewById(R.id.productos);
        productos.setAdapter(new ProductoAdapter(cargarProductos()));
        productos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callback.onDetailClickListener((Producto)productos.getItemAtPosition(position));
            }
        });
    }



    private List<Producto> cargarProductos(){
        List<Producto>productosList = new ArrayList<>();
        productosList.add(new Producto(1,"Saint Burguer"));
        productosList.add(new Producto(2,"180 Burguer"));
        productosList.add(new Producto(3,"Del Toro"));
        productosList.add(new Producto(4,"Wendys"));
        productosList.add(new Producto(5,"Dean and Denis"));
        return productosList;
    }




}
