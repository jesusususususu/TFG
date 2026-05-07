package com.example.tfg;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class IngredientesActivity extends AppCompatActivity {

    private EditText etIngredientes;
    private RecyclerView recycler;
    private RecetaAdapter adapter;
    private List<Receta> todasLasRecetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredientes);

        // 1. Vincular vistas
        etIngredientes = findViewById(R.id.etIngredientes);
        recycler = findViewById(R.id.recyclerFiltrado);
        Button btnBuscar = findViewById(R.id.btnBuscar);

        // 2. Configurar RecyclerView
        recycler.setLayoutManager(new LinearLayoutManager(this));

        // 3. Cargar la base de datos de recetas
        cargarDatos();

        // 4. Configurar el clic del botón
        btnBuscar.setOnClickListener(v -> {
            String textoBusqueda = etIngredientes.getText().toString().trim();

            if (textoBusqueda.isEmpty()) {
                Toast.makeText(this, "Escribe algún ingrediente", Toast.LENGTH_SHORT).show();
            } else {
                filtrar(textoBusqueda);
            }
        });
    }

    private void cargarDatos() {
        todasLasRecetas = new ArrayList<>();
        // IMPORTANTE: Asegúrate de que los nombres coincidan con tus strings de ingredientes
        todasLasRecetas.add(new Receta("Tortilla francesa", "Huevos, sal", "Batir y cuajar", 5, R.drawable.tortilla));
        todasLasRecetas.add(new Receta("Sandwich mixto", "Pan, jamon, queso", "Tostar el pan", 10, R.drawable.sandwich));
        todasLasRecetas.add(new Receta("Ensalada rápida", "Lechuga, tomate", "Mezclar vegetales", 7, R.drawable.ensalada));
    }

    private void filtrar(String texto) {
        List<Receta> filtradas = new ArrayList<>();

        for (Receta r : todasLasRecetas) {
            // Comprobamos si el ingrediente está en la receta (en minúsculas para evitar errores)
            if (r.getIngredientes().toLowerCase().contains(texto.toLowerCase())) {
                filtradas.add(r);
            }
        }

        if (filtradas.isEmpty()) {
            Toast.makeText(this, "No hay recetas con ese ingrediente", Toast.LENGTH_SHORT).show();
        }

        // 5. ACTUALIZAR EL ADAPTADOR
        adapter = new RecetaAdapter(filtradas, this);
        recycler.setAdapter(adapter);
    }
}