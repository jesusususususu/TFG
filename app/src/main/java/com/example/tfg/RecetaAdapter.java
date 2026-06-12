package com.example.tfg;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder> {

    private final List<Receta> listaRecetas;

    public RecetaAdapter(List<Receta> listaRecetas) {
        this.listaRecetas = listaRecetas;
    }

    @NonNull
    @Override
    public RecetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receta, parent, false);
        return new RecetaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecetaViewHolder holder, int position) {
        Receta receta = listaRecetas.get(position);

        holder.txtNombre.setText(receta.getNombre());
        holder.txtTiempo.setText("⏱️ " + receta.getTiempoPreparacion() + " min");
        holder.imgReceta.setImageResource(receta.getImagenResurce());

        // Algoritmo para calcular las coincidencias con Room y colorear la etiqueta
        AppDatabase db = AppDatabase.getInstance(holder.itemView.getContext());

        // Obtenemos los ingredientes actuales de la base de datos
        List<IngredienteAlmacen> almacen = db.ingredienteDao().obtenerTodo();

        Map<String, Double> almacenMap = new HashMap<>();

        for (IngredienteAlmacen i : almacen) {
            if (i.getNombre() != null) {
                almacenMap.put(
                        i.getNombre().toLowerCase().trim(),
                        (double) i.getCantidad()
                );
            }
        }

        int totalRequisitos = receta.getMapaIngredientes().size();
        int ingredientesCompletos = 0;
        int ingredientesParciales = 0;

        for (Map.Entry<String, Double> req : receta.getMapaIngredientes().entrySet()) {
            String nombreReq = req.getKey();
            double cantidadNecesaria = req.getValue();

            if (almacenMap.containsKey(nombreReq)) {
                if (almacenMap.get(nombreReq) >= cantidadNecesaria) {
                    ingredientesCompletos++;
                } else {
                    ingredientesParciales++;
                }
            }
        }

        // Aplicamos los textos y colores exactos que propusimos
        if (totalRequisitos == 0) {
            holder.txtEstadoIngredientes.setText("● Sin requisitos especificados");
            holder.txtEstadoIngredientes.setTextColor(Color.parseColor("#757575")); // Gris
        } else if (ingredientesCompletos == totalRequisitos) {
            holder.txtEstadoIngredientes.setText("● ¡Puedes cocinarla ya!");
            holder.txtEstadoIngredientes.setTextColor(Color.parseColor("#4CAF50")); // Verde
        } else if (ingredientesCompletos > 0 || ingredientesParciales > 0) {
            holder.txtEstadoIngredientes.setText("● Te faltan ingredientes o cantidad");
            holder.txtEstadoIngredientes.setTextColor(Color.parseColor("#FF9800")); // Naranja
        } else {
            holder.txtEstadoIngredientes.setText("● Sin ingredientes en el almacén");
            holder.txtEstadoIngredientes.setTextColor(Color.parseColor("#F44336")); // Rojo
        }

        // Clic en la tarjeta para abrir la actividad de detalles de la receta
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetalleRecetaActivity.class);
            intent.putExtra("receta", receta);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaRecetas.size();
    }

    public static class RecetaViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtTiempo, txtEstadoIngredientes;
        ImageView imgReceta;

        public RecetaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtTiempo = itemView.findViewById(R.id.txtTiempo);
            txtEstadoIngredientes = itemView.findViewById(R.id.txtEstadoIngredientes);
            imgReceta = itemView.findViewById(R.id.imgReceta);
        }
    }
}