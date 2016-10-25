package com.android.ejemplofragmentos.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.ejemplofragmentos.GsonRequest;
import com.android.ejemplofragmentos.ProductoAdapter;
import com.android.ejemplofragmentos.R;
import com.android.ejemplofragmentos.model.Producto;
import com.android.ejemplofragmentos.services.DownloadService;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.Arrays;
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
        cargarProductos();
        productos = (ListView) view.findViewById(R.id.productos);
        /*productos.setAdapter(new ProductoAdapter(cargarProductos()));*/
        productos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callback.onDetailClickListener((Producto)productos.getItemAtPosition(position));
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        /*callback.onDetailClickListener((Producto)productos.getItemAtPosition(0));*/
    }

    private void cargarProductos(){
        Intent downloadIntent = new Intent(getActivity(), DownloadService.class);
        Bundle bundle = new Bundle();
        bundle.putString("URL", "http://webkathon.com/pruebasit/products.php");
        downloadIntent.putExtras(bundle);

        getActivity().startService(downloadIntent);
        /*getActivity().stopService(downloadIntent);*/
    }

/*
        GsonRequest<Producto[]> request = new GsonRequest<>(Request.Method.GET, "http://webkathon.com/pruebasit/products.php", Producto[].class, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                List<Producto>listaDeProductos = Arrays.asList((Producto[])response);
                productos.setAdapter(new ProductoAdapter(listaDeProductos));
                //callback.onDetailClickListener(listaDeProductos.get(0));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Se produjo un error", Toast.LENGTH_SHORT).show();
                */
/*error.networkResponse.;*//*

            }
        });
        Volley.newRequestQueue(getActivity()).add(request);
*/


}
