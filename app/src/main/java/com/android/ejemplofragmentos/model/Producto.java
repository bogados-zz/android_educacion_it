package com.android.ejemplofragmentos.model;

/**
 * Created by sbogado on 18/10/16.
 */

public class Producto {

    private Integer id;
    private String nombre;

    public Producto(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
