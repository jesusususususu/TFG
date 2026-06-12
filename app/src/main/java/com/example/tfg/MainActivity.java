package com.example.tfg;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerRecetas;
    private RecetaAdapter adapter;
    private List<Receta> listaRecetas;
    private AppDatabase db;
    private Map<Integer, Integer> estadoRecetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerRecetas = findViewById(R.id.recyclerRecetas);
        recyclerRecetas.setLayoutManager(new LinearLayoutManager(this));

        db = AppDatabase.getInstance(this);
        listaRecetas = db.recetaDao().obtenerTodas();


        estadoRecetas = new HashMap<>();


        List<IngredienteAlmacen> almacen = db.ingredienteDao().obtenerTodo();

        Map<String, Double> miAlmacenMap = new HashMap<>();
        for (IngredienteAlmacen ing : almacen) {
            if (ing.getNombre() != null) {
                miAlmacenMap.put(
                        ing.getNombre().toLowerCase().trim(),
                        (double) ing.getCantidad()
                );
            }
        }


        for (Receta receta : listaRecetas) {
            int coincidencias = 0;

            if (receta.getMapaIngredientes() != null) {
                for (String ing : receta.getMapaIngredientes().keySet()) {
                    if (miAlmacenMap.containsKey(ing)) {
                        coincidencias++;
                    }
                }
            }
            estadoRecetas.put(receta.getId(), coincidencias);
        }


        Collections.shuffle(listaRecetas);
        if (listaRecetas.size() > 3) {
            listaRecetas = new ArrayList<>(listaRecetas.subList(0, 3));
        }


        adapter = new RecetaAdapter(listaRecetas, estadoRecetas);
        recyclerRecetas.setAdapter(adapter);

        if (findViewById(R.id.btnVolverMenu) != null) {
            findViewById(R.id.btnVolverMenu).setOnClickListener(v -> finish());
        }
    }
}




