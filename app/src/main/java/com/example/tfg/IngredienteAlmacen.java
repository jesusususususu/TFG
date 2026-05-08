package com.example.tfg;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

// Esta etiqueta le dice a Room que cree una tabla llamada "almacen"
@Entity(tableName = "almacen")
public class IngredienteAlmacen implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id; // El ID se genera solo (1, 2, 3...)

    private String nombre;
    private int cantidad;
    private String unidad; // Ejemplo: "unidades", "gramos", "ml"
    private int imagenResource; // Aquí guardaremos el R.drawable de la foto

    // Constructor
    public IngredienteAlmacen(String nombre, int cantidad, String unidad, int imagenResource) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.imagenResource = imagenResource;
    }

    // --- GETTERS Y SETTERS (Importantes para que Room pueda leer y escribir) ---

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }

    public int getImagenResource() { return imagenResource; }
    public void setImagenResource(int imagenResource) { this.imagenResource = imagenResource; }
}