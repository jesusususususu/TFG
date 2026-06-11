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

public class IngredientesActivity extends AppCompatActivity {

    private RecyclerView recyclerFiltrado;
    private RecetaAdapter adapter;
    private List<Receta> listaRecetas;
    private AppDatabase db;


    private Map<Integer, Integer> estadoRecetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredientes);

        recyclerFiltrado = findViewById(R.id.recyclerFiltrado);
        recyclerFiltrado.setLayoutManager(new LinearLayoutManager(this));

        db = AppDatabase.getInstance(this);
        listaRecetas = db.recetaDao().obtenerTodas();


        estadoRecetas = new HashMap<>();

        aplicarAlgoritmoInteligente();

        if (findViewById(R.id.btnVolverMenu) != null) {
            findViewById(R.id.btnVolverMenu).setOnClickListener(v -> finish());
        }
    }

    private void aplicarAlgoritmoInteligente() {
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

        List<Receta> recetasFiltradas = new ArrayList<>();
        Map<Receta, Double> viabilidadRecetas = new HashMap<>();

        for (Receta receta : listaRecetas) {
            Map<String, Double> requisitos = receta.getMapaIngredientes();

            if (requisitos == null || requisitos.isEmpty()) {
                continue;
            }

            int coincidencias = 0;
            double puntos = 0;
            int totalIngredientes = requisitos.size();

            for (Map.Entry<String, Double> req : requisitos.entrySet()) {
                String nombreReq = req.getKey();
                double cantidadNecesaria = req.getValue();

                if (miAlmacenMap.containsKey(nombreReq)) {
                    coincidencias++;

                    double cantidadTengo = miAlmacenMap.get(nombreReq);
                    if (cantidadTengo >= cantidadNecesaria) {
                        puntos += 1;
                    } else {
                        puntos += cantidadTengo / cantidadNecesaria;
                    }
                }
            }


            if (coincidencias == 0) {
                continue;
            }


            estadoRecetas.put(receta.getId(), coincidencias);
            recetasFiltradas.add(receta);

            double porcentaje = puntos / totalIngredientes;
            viabilidadRecetas.put(receta, porcentaje);
        }


        Collections.sort(
                recetasFiltradas,
                (r1, r2) -> Double.compare(
                        viabilidadRecetas.get(r2),
                        viabilidadRecetas.get(r1)
                )
        );

        adapter = new RecetaAdapter(recetasFiltradas, estadoRecetas);
        recyclerFiltrado.setAdapter(adapter);
    }
}