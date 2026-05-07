package com.example.tfg;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Configuración del botón para ir a Recetas Diarias (MainActivity)
        findViewById(R.id.btnSugeridas).setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // Configuración del botón para ir a Búsqueda por Ingredientes
        findViewById(R.id.btnPorIngredientes).setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, IngredientesActivity.class);
            startActivity(intent);
        });
    }
}