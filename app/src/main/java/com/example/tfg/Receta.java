package com.example.tfg;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Receta implements Serializable {
    private String nombre;
    private String ingredientesTexto;
    private String pasos;
    private int tiempoPreparacion;
    private int imagenResource; // Nombre unificado
    private Map<String, Double> mapaIngredientes;

    public Receta(String nombre, String ingredientesTexto, String pasos, int tiempoPreparacion, int imagenResource) {
        this.nombre = nombre;
        this.ingredientesTexto = ingredientesTexto;
        this.pasos = pasos;
        this.tiempoPreparacion = tiempoPreparacion;
        this.imagenResource = imagenResource;
        this.mapaIngredientes = new HashMap<>();
    }

    public void agregarIngredienteRequisito(String nombreIngrediente, double cantidad) {
        this.mapaIngredientes.put(nombreIngrediente.toLowerCase().trim(), cantidad);
    }

    // GETTERS Y SETTERS CORREGIDOS
    public String getNombre() { return nombre; }
    public String getIngredientes() { return ingredientesTexto; }
    public String getPasos() { return pasos; }
    public int getTiempoPreparacion() { return tiempoPreparacion; }

    // Fíjate aquí: Añadida la "e" para que se lea Resource
    public int getImagenResurce() { return imagenResource; }
    public int getImagenResource() { return imagenResource; }

    public Map<String, Double> getMapaIngredientes() {
        if (this.mapaIngredientes == null) {
            this.mapaIngredientes = new HashMap<>();
        }
        return mapaIngredientes;
    }
}