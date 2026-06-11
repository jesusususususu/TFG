package com.example.tfg;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity(tableName = "recetas")
public class Receta implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nombre;
    private String ingredientesTexto;
    private String pasos;
    private int tiempoPreparacion;
    private int imagenResource;
    private Map<String, Double> mapaIngredientes;


    public Receta(String nombre, String ingredientesTexto, String pasos, int tiempoPreparacion, int imagenResource) {
        this.nombre = nombre;
        this.ingredientesTexto = ingredientesTexto;
        this.pasos = pasos;
        if ( tiempoPreparacion<=0) throw new RuntimeException("el tiempo no puede ser negativo");
        this.tiempoPreparacion = tiempoPreparacion;
        this.imagenResource = imagenResource;
        this.mapaIngredientes = new HashMap<>();
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public void agregarIngredienteRequisito(String nombreIngrediente, double cantidad) {
        this.mapaIngredientes.put(nombreIngrediente.toLowerCase().trim(), cantidad);
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getIngredientesTexto() { return ingredientesTexto; }
    public void setIngredientesTexto(String ingredientesTexto) { this.ingredientesTexto = ingredientesTexto; }

    public String getIngredientes() { return ingredientesTexto; }

    public String getPasos() { return pasos; }
    public void setPasos(String pasos) { this.pasos = pasos; }

    public int getTiempoPreparacion() { return tiempoPreparacion; }
    public void setTiempoPreparacion(int tiempoPreparacion) { this.tiempoPreparacion = tiempoPreparacion; }

    public int getImagenResource() { return imagenResource; }
    public void setImagenResource(int imagenResource) { this.imagenResource = imagenResource; }

    public int getImagenResurce() { return imagenResource; }

    public Map<String, Double> getMapaIngredientes() {
        if (this.mapaIngredientes == null) {
            this.mapaIngredientes = new HashMap<>();
        }
        return mapaIngredientes;
    }

    public void setMapaIngredientes(Map<String, Double> mapaIngredientes) {
        this.mapaIngredientes = mapaIngredientes;
    }
}