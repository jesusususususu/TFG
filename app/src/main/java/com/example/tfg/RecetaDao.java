package com.example.tfg;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface RecetaDao {

    @Insert
    void insertar(Receta receta);

    @Query("SELECT * FROM recetas")
    List<Receta> obtenerTodas();
}

