package com.android.ejemplofragmentos.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.ejemplofragmentos.model.Producto;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by sbogado on 25/10/16.
 */



public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private static  String DB_NAME = "Ejemplo.sqlite";
    private static int DB_VERSION=1;



    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Producto.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
