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

        // 1. Inicializar base de datos con protección
        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "base-recetas")
                .fallbackToDestructiveMigration()
                .build();

        // 2. Vincular vista del Recycler
        recycler = findViewById(R.id.recyclerFiltrado);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        // 3. Cargar la lista maestra de recetas
        cargarDatos();

        // 4. LÓGICA AUTOMÁTICA: Filtrar nada más entrar
        filtrarSegunAlmacen();

        findViewById(R.id.btnVolverMenu).setOnClickListener(v -> {
            finish(); // Esto cierra la pantalla actual y te devuelve automáticamente al Menú
        });
    }

    private void cargarDatos() {
        todasLasRecetas = new ArrayList<>();
        todasLasRecetas.add(new Receta("Tortilla francesa", "Huevos, sal", "Batir y cuajar", 5, R.drawable.tortilla));
        todasLasRecetas.add(new Receta("Sandwich mixto", "Pan, jamon, queso", "Tostar el pan", 10, R.drawable.sandwich));
        todasLasRecetas.add(new Receta("Ensalada rápida", "Lechuga, tomate", "Mezclar vegetales", 7, R.drawable.ensalada));
    }

    private void filtrarSegunAlmacen() {
        Executors.newSingleThreadExecutor().execute(() -> {
            // Coger ingredientes de Room
            List<String> misIngredientes = db.ingredienteDao().obtenerNombresAlmacen();
            List<Receta> filtradas = new ArrayList<>();

            // Comparar
            for (Receta r : todasLasRecetas) {
                for (String ingAlmacen : misIngredientes) {
                    if (r.getIngredientes().toLowerCase().contains(ingAlmacen.toLowerCase())) {
                        if (!filtradas.contains(r)) {
                            filtradas.add(r);
                        }
                    }
                }
            }

            // Mostrar resultados
            runOnUiThread(() -> {
                if (filtradas.isEmpty()) {
                    Toast.makeText(this, "Añade ingredientes al almacén para ver sugerencias", Toast.LENGTH_LONG).show();
                }
                adapter = new RecetaAdapter(filtradas, this);
                recycler.setAdapter(adapter);
            });
        });
    }
}