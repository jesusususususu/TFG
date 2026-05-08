package com.example.tfg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class IngredienteAdapter extends RecyclerView.Adapter<IngredienteAdapter.ViewHolder> {

    private List<IngredienteAlmacen> listaIngredientes;

    public IngredienteAdapter(List<IngredienteAlmacen> lista) {
        this.listaIngredientes = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingrediente_almacen, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IngredienteAlmacen item = listaIngredientes.get(position);
        holder.tvNombre.setText(item.getNombre());
        holder.tvDetalle.setText(item.getCantidad() + " " + item.getUnidad());
        holder.ivImagen.setImageResource(item.getImagenResource());
    }

    @Override
    public int getItemCount() {
        return listaIngredientes.size();
    }

    // Método para actualizar la lista cuando añadimos algo nuevo
    public void setIngredientes(List<IngredienteAlmacen> nuevosIngredientes) {
        this.listaIngredientes = nuevosIngredientes;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvDetalle;
        ImageView ivImagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreIngrediente);
            tvDetalle = itemView.findViewById(R.id.tvCantidadUnidad);
            ivImagen = itemView.findViewById(R.id.ivIngrediente);
        }
    }
}