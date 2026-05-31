package com.example.tfg;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class IngredientesActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecetaAdapter adapter;
    private List<Receta> todasLasRecetas;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredientes);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "base-recetas")
                .fallbackToDestructiveMigration()
                .build();

        recycler = findViewById(R.id.recyclerFiltrado);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        cargarDatos();
        filtrarSegunAlmacen();

        findViewById(R.id.btnVolverMenu).setOnClickListener(v -> finish());
    }

    private void cargarDatos() {
        todasLasRecetas = new ArrayList<>();
        todasLasRecetas.add(new Receta("Tortilla francesa", "Huevos, sal", "Batir y cuajar", 5, R.drawable.tortilla));
        todasLasRecetas.add(new Receta("Sandwich mixto", "Pan, jamon, queso", "Tostar el pan", 10, R.drawable.sandwich));
        todasLasRecetas.add(new Receta("Ensalada rápida", "Lechuga, tomate", "Mezclar vegetales", 7, R.drawable.ensalada));
    }

    private void filtrarSegunAlmacen() {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<String> misIngredientes = db.ingredienteDao().obtenerNombresAlmacen();
            List<Receta> filtradas = new ArrayList<>();

            for (Receta receta : todasLasRecetas) {
                // Separamos los ingredientes de la receta por comas y limpiamos espacios
                String[] ingRecetaArray = receta.getIngredientes().split(",");

                boolean contieneAlMenosUno = false;
                for (String ingReceta : ingRecetaArray) {
                    String ingRecetaLimpio = ingReceta.trim().toLowerCase();

                    for (String ingAlmacen : misIngredientes) {
                        String ingAlmacenLimpio = ingAlmacen.trim().toLowerCase();

                        // Comprobación bidireccional (ej: "tomate" está en "tomates" o viceversa)
                        if (ingRecetaLimpio.contains(ingAlmacenLimpio) || ingAlmacenLimpio.contains(ingRecetaLimpio)) {
                            contieneAlMenosUno = true;
                            break;
                        }
                    }
                    if (contieneAlMenosUno) break;
                }

                if (contieneAlMenosUno) {
                    filtradas.add(receta);
                }
            }

            runOnUiThread(() -> {
                if (filtradas.isEmpty()) {
                    Toast.makeText(this, "No hay recetas exactas para tus ingredientes", Toast.LENGTH_LONG).show();
                }
                adapter = new RecetaAdapter(filtradas, this);
                recycler.setAdapter(adapter);
            });
        });
    }
}