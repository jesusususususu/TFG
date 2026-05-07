package com.example.tfg;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Receta> listaRecetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerRecetas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaRecetas = new ArrayList<>();

        // RECETAS DE EJEMPLO
        listaRecetas.add(new Receta(
                "Tortilla francesa",
                "Huevos, sal",
                "Batir huevos y cocinar en sartén",
                5,
                R.drawable.tortilla
        ));

        listaRecetas.add(new Receta(
                "Sandwich mixto",
                "Pan, jamón, queso",
                "Montar y tostar en sartén",
                10,
                R.drawable.sandwich
        ));

        listaRecetas.add(new Receta(
                "Ensalada rápida",
                "Lechuga, tomate, aceite",
                "Cortar y mezclar todo",
                7,
                R.drawable.ensalada
        ));

        RecetaAdapter adapter = new RecetaAdapter(listaRecetas, this);
        recyclerView.setAdapter(adapter);
    }
}