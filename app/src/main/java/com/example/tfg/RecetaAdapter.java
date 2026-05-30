package com.example.tfg;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.ViewHolder> {

    private List<Receta> listaRecetas;
    private Context context;

    public RecetaAdapter(List<Receta> listaRecetas, Context context) {
        this.listaRecetas = listaRecetas;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_receta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Receta receta = listaRecetas.get(position);

        holder.nombre.setText(receta.getNombre());
        holder.tiempo.setText(receta.getTiempoPreparacion() + " min");
        holder.imagen.setImageResource(receta.getImagenResurce());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetalleRecetaActivity.class);
            intent.putExtra("receta", receta);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaRecetas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, tiempo;
        ImageView imagen;

        public ViewHolder(View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.txtNombre);
            tiempo = itemView.findViewById(R.id.txtTiempo);
            imagen = itemView.findViewById(R.id.imgReceta);
        }
    }
}