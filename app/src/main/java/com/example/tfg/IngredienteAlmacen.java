package com.example.tfg;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName = "almacen")
public class IngredienteAlmacen implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nombre;
    private int cantidad;
    private String unidad;
    private int imagenResurce;

    public IngredienteAlmacen(String nombre, int cantidad, String unidad, int imagenResurce) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.imagenResurce = imagenResurce;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }


    public int getImagenResurce() { return imagenResurce; }
    public void setImagenResurce(int imagenResurce) { this.imagenResurce = imagenResurce; }
}