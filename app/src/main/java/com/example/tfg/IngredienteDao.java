package com.example.tfg;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface IngredienteDao {

    // 1. Obtener todos los ingredientes para mostrarlos en la lista
    @Query("SELECT * FROM almacen")
    List<IngredienteAlmacen> obtenerTodo();

    // 2. Insertar un nuevo ingrediente (el que escribes en el formulario)
    @Insert
    void insertar(IngredienteAlmacen ingrediente);

    // 3. Eliminar un ingrediente (para cuando lo gastes o te equivoques)
    @Delete
    void eliminar(IngredienteAlmacen ingrediente);

    // 4. Actualizar un ingrediente (útil si solo quieres cambiar la cantidad)
    @Update
    void actualizar(IngredienteAlmacen ingrediente);

    // 5. Query especial para el filtro de recetas: solo devuelve los nombres
    @Query("SELECT nombre FROM almacen")
    List<String> obtenerNombresAlmacen();
}