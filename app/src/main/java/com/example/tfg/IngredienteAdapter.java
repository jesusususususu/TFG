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
    private OnLongClickListener longClickListener;

    // 1. Interfaz para el clic largo
    public interface OnLongClickListener {
        void onLongClick(IngredienteAlmacen ingrediente);
    }

    // 2. Método para asignar el listener desde la Activity
    public void setOnLongClickListener(OnLongClickListener listener) {
        this.longClickListener = listener;
    }

    // 3. Constructor
    public IngredienteAdapter(List<IngredienteAlmacen> listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
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

        // Ponemos el nombre
        holder.tvNombre.setText(item.getNombre());

        // Combinamos Cantidad y Unidad (ej: "500 Gramos")
        String infoCantidad = item.getCantidad() + " " + item.getUnidad();
        holder.tvCantidadUnidad.setText(infoCantidad);

        // Imagen
        holder.ivFoto.setImageResource(item.getImagenResurce());

        // Evento de clic largo para borrar
        holder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onLongClick(item);
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return (listaIngredientes != null) ? listaIngredientes.size() : 0;
    }

    // Método para refrescar la lista
    public void setIngredientes(List<IngredienteAlmacen> nuevosIngredientes) {
        this.listaIngredientes = nuevosIngredientes;
        notifyDataSetChanged();
    }

    // 4. Clase ViewHolder corregida con tus IDs del XML
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvCantidadUnidad;
        ImageView ivFoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreIngrediente);
            tvCantidadUnidad = itemView.findViewById(R.id.tvCantidadUnidad);
            ivFoto = itemView.findViewById(R.id.ivIngrediente);
        }
    }
}




