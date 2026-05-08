package com.example.tfg;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class AlmacenActivity extends AppCompatActivity {

    private AppDatabase db;
    private EditText etNombre, etCantidad, etUnidad;
    private Button btnAnadir;
    private RecyclerView rvAlmacen;

    // El "camarero" que gestiona la lista y los datos
    private IngredienteAdapter adapter;
    private List<IngredienteAlmacen> listaIngredientes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_almacen);

        // Ajuste de márgenes para el diseño
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Inicializar la base de datos
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "base-recetas").build();

        // 2. Vincular vistas del XML
        etNombre = findViewById(R.id.etNombre);
        etCantidad = findViewById(R.id.etCantidad);
        etUnidad = findViewById(R.id.etUnidad);
        btnAnadir = findViewById(R.id.btnAnadirReal);
        rvAlmacen = findViewById(R.id.rvAlmacen);

        // 3. Configurar el RecyclerView (La Lista)
        rvAlmacen.setLayoutManager(new LinearLayoutManager(this));
        adapter = new IngredienteAdapter(listaIngredientes);
        rvAlmacen.setAdapter(adapter);

        // 4. Cargar los datos que ya existen al entrar
        actualizarLista();

        // 5. Lógica del botón Añadir
        btnAnadir.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String cantStr = etCantidad.getText().toString().trim();
            String unidad = etUnidad.getText().toString().trim();

            if (!nombre.isEmpty() && !cantStr.isEmpty()) {
                int cantidad = Integer.parseInt(cantStr);

                Executors.newSingleThreadExecutor().execute(() -> {
                    // Creamos el nuevo ingrediente
                    IngredienteAlmacen nuevo = new IngredienteAlmacen(nombre, cantidad, unidad, R.drawable.tortilla);

                    // Lo guardamos en la base de datos
                    db.ingredienteDao().insertar(nuevo);

                    // Volvemos al hilo principal para actualizar la pantalla
                    runOnUiThread(() -> {
                        Toast.makeText(this, nombre + " guardado", Toast.LENGTH_SHORT).show();
                        limpiarFormulario();
                        // Refrescamos la lista para que aparezca el nuevo
                        actualizarLista();
                    });
                });
            } else {
                Toast.makeText(this, "Rellena nombre y cantidad", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para sacar los datos de Room y dárselos al Adapter
    private void actualizarLista() {
        Executors.newSingleThreadExecutor().execute(() -> {
            // Obtenemos todo de la base de datos
            List<IngredienteAlmacen> listaDesdeDB = db.ingredienteDao().obtenerTodo();

            // Le decimos al adapter que pinte los nuevos datos
            runOnUiThread(() -> {
                adapter.setIngredientes(listaDesdeDB);
            });
        });
    }

    private void limpiarFormulario() {
        etNombre.setText("");
        etCantidad.setText("");
        etUnidad.setText("");
        etNombre.requestFocus();
    }
}