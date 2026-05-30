package com.example.tfg;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleRecetaActivity extends AppCompatActivity {

    TextView nombre, ingredientes, pasos, tiempo;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_receta);

        nombre = findViewById(R.id.txtNombreDetalle);
        ingredientes = findViewById(R.id.txtIngredientes);
        pasos = findViewById(R.id.txtPasos);
        tiempo = findViewById(R.id.txtTiempoDetalle);
        imagen = findViewById(R.id.imgDetalle);

        Receta receta = (Receta) getIntent().getSerializableExtra("receta");

        if (receta != null) {
            nombre.setText(receta.getNombre());
            ingredientes.setText(receta.getIngredientes());
            pasos.setText(receta.getPasos());
            tiempo.setText(receta.getTiempoPreparacion() + " min");
            imagen.setImageResource(receta.getImagenResurce());
        }
    }
}