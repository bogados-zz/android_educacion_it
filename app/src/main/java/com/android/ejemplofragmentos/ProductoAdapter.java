package com.android.ejemplofragmentos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.ejemplofragmentos.model.Producto;

import java.util.List;

/**
 * Created by sbogado on 18/10/16.
 */

public class ProductoAdapter extends BaseAdapter {

    private List<Producto> productos;

    public ProductoAdapter(List<Producto> productos) {
        this.productos = productos;
    }


    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Object getItem(int position) {
        return productos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return productos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_producto, parent, false);
        Producto producto = productos.get(position);

        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView nombre =(TextView) convertView.findViewById(R.id.nombre);
        id.setText(producto.getId().toString());
        nombre.setText(producto.getNombre());
        return convertView;
    }
}
