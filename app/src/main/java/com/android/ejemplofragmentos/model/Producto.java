package com.android.ejemplofragmentos.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by sbogado on 18/10/16.
 */

@DatabaseTable(tableName = "PRODUCTO")
public class Producto implements Serializable{

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    @SerializedName("description")
    private String nombre;

    @DatabaseField
    @SerializedName("image")
    private String urlImagen;

    @DatabaseField
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
