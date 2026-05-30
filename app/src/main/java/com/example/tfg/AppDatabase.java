package com.example.tfg;

import androidx.room.Database;
import androidx.room.RoomDatabase;

// Subimos a versión 2 porque cambiamos la estructura de IngredienteAlmacen
@Database(entities = {IngredienteAlmacen.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract IngredienteDao ingredienteDao();
}