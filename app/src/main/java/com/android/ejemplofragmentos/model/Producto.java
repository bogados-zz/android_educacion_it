package com.android.ejemplofragmentos.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sbogado on 18/10/16.
 */

public class Producto implements Serializable{

    private Integer id=0;
    @SerializedName("description")
    private String nombre;
    @SerializedName("image")
    private String urlImagen;
    @SerializedName("price")
    private Double precio=0D;

    public Producto() {

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

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
