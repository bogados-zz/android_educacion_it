package com.android.ejemplofragmentos.daos;

import android.content.Context;

import com.android.ejemplofragmentos.model.Producto;
import com.android.ejemplofragmentos.utils.DataBaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by sbogado on 25/10/16.
 */

public class ProductoDao {

    private Dao<Producto, Integer> dao ;

    public ProductoDao(Context context){
        try {
           dao =  OpenHelperManager.getHelper(context, DataBaseHelper.class).getDao(Producto.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void save (Producto producto){
        try {
            dao.createOrUpdate(producto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Producto findById(Integer id){
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        }
    }


    public List<Producto> findAll(){
        try {
            dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Producto producto){
        try {
            dao.delete(producto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
