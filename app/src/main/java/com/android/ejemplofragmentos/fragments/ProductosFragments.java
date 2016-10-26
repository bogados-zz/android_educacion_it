package com.android.ejemplofragmentos.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.ejemplofragmentos.Activities.ProductoAdapter;
import com.android.ejemplofragmentos.Activities.SettingsActivity;
import com.android.ejemplofragmentos.R;
import com.android.ejemplofragmentos.daos.ProductoDao;
import com.android.ejemplofragmentos.model.Producto;
import com.android.ejemplofragmentos.services.DownloadService;

/**
 * Created by sbogado on 18/10/16.
 */

public class ProductosFragments extends android.support.v4.app.Fragment {

    private ListView productos;
    private OnDetailClickListener callback;
    private DownloadReciver downloadReciver;
    private IntentFilter intentFilter;
    private Button sendToConfigView;

    public interface OnDetailClickListener{
        void onDetailClickListener(Producto producto);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        downloadReciver = new DownloadReciver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(DownloadService.ACTION_DOWNLOAD_SUCCESS);
        intentFilter.addAction(DownloadService.ACTION_DOWNLOAD_ERROR);
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

        sendToConfigView = (Button) view.findViewById(R.id.change_to_settings);
        sendToConfigView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductosFragments.this.changeViewToSettingsActivity();
            }
        });


        pedirProducto();
        productos = (ListView) view.findViewById(R.id.productos);
        /*productos.setAdapter(new ProductoAdapter(pedirProducto()));*/
        productos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callback.onDetailClickListener((Producto)productos.getItemAtPosition(position));
            }
        });
    }


    /**
     *  El fragment no tienen un contexto. getActivity o getContext
     *
    * */
    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(downloadReciver,intentFilter);
    }

    /**
     * GetContext y getActivity son lo mismo
     */
    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(downloadReciver);

    }

    @Override
    public void onResume() {
        super.onResume();
        /*callback.onDetailClickListener((Producto)productos.getItemAtPosition(0));*/
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void cargarProductos(){
        ProductoDao productoDao = new ProductoDao(getContext());
        productos.setAdapter(new ProductoAdapter(productoDao.findAll()));
    }

    private void pedirProducto(){
        Intent downloadIntent = new Intent(getActivity(), DownloadService.class);
        Bundle bundle = new Bundle();
        bundle.putString("URL", "http://webkathon.com/pruebasit/products.php");
        downloadIntent.putExtras(bundle);

        getActivity().startService(downloadIntent);
        /*getActivity().stopService(downloadIntent);*/
    }


    private void changeViewToSettingsActivity() {
        Intent changeViewIntent = new Intent(getContext(), SettingsActivity.class);
        startActivity(changeViewIntent);
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


    //Son un componente principal. Se necesita declararla en el manifest.
    //Pero al ser una innerclass nosotros nos encargamos de inicializar
    // por lo cual no es necesariol decalrarlo en el manifest
    private class DownloadReciver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(DownloadService.ACTION_DOWNLOAD_ERROR)){
                Toast.makeText(context, "Error al sincronizar", Toast.LENGTH_SHORT).show();
            }
            cargarProductos();
        }
    }








}
