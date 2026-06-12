

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

import java.util.List;
import java.util.Map;

public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder> {

    private final List<Receta> listaRecetas;
    private final Map<Integer, Integer> estadoRecetas;

    public RecetaAdapter(List<Receta> listaRecetas, Map<Integer, Integer> estadoRecetas) {
        this.listaRecetas = listaRecetas;
        this.estadoRecetas = estadoRecetas;
    }

    @NonNull
    @Override
    public RecetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_receta, parent, false);
        return new RecetaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecetaViewHolder holder, int position) {
        Receta receta = listaRecetas.get(position);

        holder.txtNombre.setText(receta.getNombre());
        holder.txtTiempo.setText("⏱️ " + receta.getTiempoPreparacion() + " min");
        holder.imgReceta.setImageResource(receta.getImagenResource());


        Integer c = estadoRecetas.get(receta.getId());
        int coincidencias = (c != null) ? c : 0;
        int totalIngredientes = receta.getMapaIngredientes().size();


        if (coincidencias == 0) {

            holder.txtEstadoIngredientes.setText("● No tienes ingredientes suficientes");
            holder.txtEstadoIngredientes.setTextColor(Color.parseColor("#F44336"));
        } else if (coincidencias == totalIngredientes) {
            holder.txtEstadoIngredientes.setText("● Puedes cocinarla");
            holder.txtEstadoIngredientes.setTextColor(Color.parseColor("#4CAF50"));
        } else {
            holder.txtEstadoIngredientes.setText("● Te faltan ingredientes");
            holder.txtEstadoIngredientes.setTextColor(Color.parseColor("#FFC107"));
        }


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetalleRecetaActivity.class);
            intent.putExtra("receta", receta);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaRecetas != null ? listaRecetas.size() : 0;
    }

    public static class RecetaViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre;
        TextView txtTiempo;
        TextView txtEstadoIngredientes;
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



