package com.example.tfg;

import java.io.Serializable;

public class Receta implements Serializable {

    private String nombre;
    private String ingredientes;
    private String pasos;
    private int tiempoPreparacion;
    private int imagenResurce;

    public Receta(String nombre, String ingredientes, String pasos, int tiempoPreparacion, int imagenResurce) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.pasos = pasos;
        this.tiempoPreparacion = tiempoPreparacion;
        this.imagenResurce = imagenResurce;
    }

    public String getNombre() { return nombre; }
    public String getIngredientes() { return ingredientes; }
    public String getPasos() { return pasos; }
    public int getTiempoPreparacion() { return tiempoPreparacion; }
    public int getImagenResurce() { return imagenResurce; }
}