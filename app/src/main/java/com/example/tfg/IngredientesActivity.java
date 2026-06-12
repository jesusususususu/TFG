package com.example.tfg;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngredientesActivity extends AppCompatActivity {

    private RecyclerView recyclerFiltrado;
    private RecetaAdapter adapter;
    private List<Receta> listaRecetas;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredientes);

        recyclerFiltrado = findViewById(R.id.recyclerFiltrado);
        recyclerFiltrado.setLayoutManager(new LinearLayoutManager(this));

        db = AppDatabase.getInstance(this);

        listaRecetas = RecetasData.obtenerRecetas();

        aplicarAlgoritmoInteligente();

        findViewById(R.id.btnVolverMenu)
                .setOnClickListener(v -> finish());
    }

    private void aplicarAlgoritmoInteligente() {

        List<IngredienteAlmacen> almacen =
                db.ingredienteDao().obtenerTodo();

        Map<String, Double> miAlmacenMap = new HashMap<>();

        for (IngredienteAlmacen ing : almacen) {

            if (ing.getNombre() != null) {

                miAlmacenMap.put(
                        ing.getNombre().toLowerCase().trim(),
                        (double) ing.getCantidad()
                );
            }
        }

        Map<Receta, Double> viabilidadRecetas = new HashMap<>();

        for (Receta receta : listaRecetas) {

            Map<String, Double> requisitos =
                    receta.getMapaIngredientes();

            if (requisitos.isEmpty()) {

                viabilidadRecetas.put(receta, 0.0);
                continue;
            }

            double totalIngredientes = requisitos.size();
            double puntos = 0.0;

            for (Map.Entry<String, Double> req : requisitos.entrySet()) {

                String nombreReq = req.getKey();
                double cantidadNecesaria = req.getValue();

                if (miAlmacenMap.containsKey(nombreReq)) {

                    double cantidadTengo =
                            miAlmacenMap.get(nombreReq);

                    if (cantidadTengo >= cantidadNecesaria) {
                        puntos += 1.0;
                    } else {
                        puntos += cantidadTengo / cantidadNecesaria;
                    }
                }
            }

            double porcentaje =
                    puntos / totalIngredientes;

            viabilidadRecetas.put(receta, porcentaje);
        }

        Collections.sort(
                listaRecetas,
                (r1, r2) ->
                        Double.compare(
                                viabilidadRecetas.get(r2),
                                viabilidadRecetas.get(r1)
                        )
        );

        adapter = new RecetaAdapter(listaRecetas);
        recyclerFiltrado.setAdapter(adapter);
    }
}