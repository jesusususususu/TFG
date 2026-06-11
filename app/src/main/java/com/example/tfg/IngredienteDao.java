package com.example.tfg;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface IngredienteDao {


    @Query("SELECT * FROM almacen")
    List<IngredienteAlmacen> obtenerTodo();


    @Insert
    void insertar(IngredienteAlmacen ingrediente);


    @Delete
    void eliminar(IngredienteAlmacen ingrediente);


    @Query("SELECT nombre FROM almacen")
    List<String> obtenerNombresAlmacen();
}