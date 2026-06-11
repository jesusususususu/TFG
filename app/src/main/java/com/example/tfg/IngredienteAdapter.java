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


    public interface OnLongClickListener {
        void onLongClick(IngredienteAlmacen ingrediente);
    }


    public void setOnLongClickListener(OnLongClickListener listener) {
        this.longClickListener = listener;
    }


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


        holder.tvNombre.setText(item.getNombre());


        String infoCantidad = item.getCantidad() + " " + item.getUnidad();
        holder.tvCantidadUnidad.setText(infoCantidad);


        holder.ivFoto.setImageResource(item.getImagenResurce());


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


    public void setIngredientes(List<IngredienteAlmacen> nuevosIngredientes) {
        this.listaIngredientes = nuevosIngredientes;
        notifyDataSetChanged();
    }


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