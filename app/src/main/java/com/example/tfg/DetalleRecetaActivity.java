
package com.example.tfg;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetalleRecetaActivity extends AppCompatActivity {

    private TextView nombre, ingredientes, pasos, tiempo;
    private ImageView imagen;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_receta);

        nombre = findViewById(R.id.txtNombreDetalle);
        ingredientes = findViewById(R.id.txtListaIngredientes);
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

        // LÓGICA DEL BOTÓN VOLVER: Cierra la pantalla actual al hacer clic
        findViewById(R.id.btnVolverRecetas).setOnClickListener(v -> finish());
    }
}
