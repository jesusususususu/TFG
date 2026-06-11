package com.example.tfg;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AlmacenActivity extends AppCompatActivity {

    private AppDatabase db;
    private EditText etNombre, etCantidad, etUnidad;
    private Button btnAnadir;
    private RecyclerView rvAlmacen;

    private IngredienteAdapter adapter;
    private List<IngredienteAlmacen> listaIngredientes = new ArrayList<>();


    private final ExecutorService databaseExecutor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almacen);


        db = AppDatabase.getInstance(this);

        etNombre = findViewById(R.id.etNombre);
        etCantidad = findViewById(R.id.etCantidad);
        etUnidad = findViewById(R.id.etUnidad);
        btnAnadir = findViewById(R.id.btnAnadirReal);
        rvAlmacen = findViewById(R.id.rvAlmacen);

        rvAlmacen.setLayoutManager(new LinearLayoutManager(this));
        adapter = new IngredienteAdapter(listaIngredientes);
        rvAlmacen.setAdapter(adapter);

        adapter.setOnLongClickListener(ingrediente -> {
            new AlertDialog.Builder(this)
                    .setTitle("Eliminar producto")
                    .setMessage("¿Quieres eliminar " + ingrediente.getNombre() + " del almacén?")
                    .setPositiveButton("Eliminar", (dialog, which) -> {
                        databaseExecutor.execute(() -> {
                            db.ingredienteDao().eliminar(ingrediente);
                            actualizarLista();
                            runOnUiThread(() ->
                                    Toast.makeText(this, "Eliminado correctamente", Toast.LENGTH_SHORT).show()
                            );
                        });
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });

        actualizarLista();

        btnAnadir.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String cantStr = etCantidad.getText().toString().trim();
            String unidad = etUnidad.getText().toString().trim();

            if (!nombre.isEmpty() && !cantStr.isEmpty()) {
                int cantidad = Integer.parseInt(cantStr);
                databaseExecutor.execute(() -> {

                    int imagenPorDefecto = R.drawable.ic_ingrediente_generic;

                    IngredienteAlmacen nuevo = new IngredienteAlmacen(nombre, cantidad, unidad, imagenPorDefecto);
                    db.ingredienteDao().insertar(nuevo);
                    runOnUiThread(() -> {
                        Toast.makeText(this, "Añadido: " + nombre, Toast.LENGTH_SHORT).show();
                        limpiarFormulario();
                        actualizarLista();
                    });
                });
            } else {
                Toast.makeText(this, "Completa los campos", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btnVolverMenu).setOnClickListener(v -> finish());
    }

    private void actualizarLista() {
        databaseExecutor.execute(() -> {
            List<IngredienteAlmacen> listaDesdeDB = db.ingredienteDao().obtenerTodo();
            runOnUiThread(() -> adapter.setIngredientes(listaDesdeDB));
        });
    }

    private void limpiarFormulario() {
        etNombre.setText("");
        etCantidad.setText("");
        etUnidad.setText("");
        etNombre.requestFocus();
    }
}