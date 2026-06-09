package com.example.tfg;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MenuActivity extends AppCompatActivity {

    // Declaramos las tarjetas como CardView en lugar de Buttons
    private CardView cardSugeridas, cardPorIngredientes, cardAlmacen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Enlazamos con los IDs exactos del nuevo XML
        cardSugeridas = findViewById(R.id.cardSugeridas);
        cardPorIngredientes = findViewById(R.id.cardPorIngredientes);
        cardAlmacen = findViewById(R.id.cardAlmacen);

        // Configuración del clic para ir a Recetas Diarias (MainActivity)
        if (cardSugeridas != null) {
            cardSugeridas.setOnClickListener(v -> {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
            });
        }

        // Configuración del clic para ir a Búsqueda por Ingredientes (IngredientesActivity)
        if (cardPorIngredientes != null) {
            cardPorIngredientes.setOnClickListener(v -> {
                Intent intent = new Intent(MenuActivity.this, IngredientesActivity.class);
                startActivity(intent);
            });
        }

        // Configuración del clic para ir a Mi Almacén (AlmacenActivity)
        if (cardAlmacen != null) {
            cardAlmacen.setOnClickListener(v -> {
                Intent intent = new Intent(MenuActivity.this, AlmacenActivity.class);
                startActivity(intent);
            });
        }
    }
}