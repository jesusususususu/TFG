package com.example.tfg;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
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

        listaRecetas = RecetasData.obtenerRecetas();

        Collections.shuffle(listaRecetas);

        if (listaRecetas.size() > 3) {
            listaRecetas = new ArrayList<>(listaRecetas.subList(0, 3));
        }

        adapter = new RecetaAdapter(listaRecetas);
        recyclerRecetas.setAdapter(adapter);

        findViewById(R.id.btnVolverMenu)
                .setOnClickListener(v -> finish());
    }
}




