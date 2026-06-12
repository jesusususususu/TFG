
package com.example.tfg;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MenuActivity extends AppCompatActivity {


    private CardView cardSugeridas, cardPorIngredientes, cardAlmacen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        cardSugeridas = findViewById(R.id.cardSugeridas);
        cardPorIngredientes = findViewById(R.id.cardPorIngredientes);
        cardAlmacen = findViewById(R.id.cardAlmacen);

        if (cardSugeridas != null) {
            cardSugeridas.setOnClickListener(v -> {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
            });
        }


        if (cardPorIngredientes != null) {
            cardPorIngredientes.setOnClickListener(v -> {
                Intent intent = new Intent(MenuActivity.this, IngredientesActivity.class);
                startActivity(intent);
            });
        }


        if (cardAlmacen != null) {
            cardAlmacen.setOnClickListener(v -> {
                Intent intent = new Intent(MenuActivity.this, AlmacenActivity.class);
                startActivity(intent);
            });
        }
    }
}