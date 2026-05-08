package com.example.tfg;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {IngredienteAlmacen.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract IngredienteDao ingredienteDao();
}