package com.example.tfg;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface IngredienteDao {

    // Para dar de alta un producto (ej: añadir huevos)
    @Insert
    void insertar(IngredienteAlmacen ingrediente);


    // Para cuando gastamos un producto del todo
    @Delete
    void eliminar(IngredienteAlmacen ingrediente);


    @Query("SELECT * FROM almacen")
    List<IngredienteAlmacen> obtenerTodo();
}