package com.example.camposdegolf;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyHolder>{
    private List<Encapsulador> entradas;

    public Adaptador(List<Encapsulador> entradas) {
        this.entradas = entradas;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.carta, parent, false);
        MyHolder mvh = new MyHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.imagen.setImageResource(entradas.get(position).getImagen());
        holder.identificador.setText(entradas.get(position).getIdentificador());
        holder.nombre.setText(entradas.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return entradas.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        public ImageView imagen;
        public TextView identificador;
        public TextView nombre;

        public MyHolder(@NonNull View vista) {
            super(vista);

            imagen = (ImageView) vista.findViewById(R.id.imagen);
            identificador = (TextView) vista.findViewById(R.id.carta_identificador);
            nombre = (TextView) vista.findViewById(R.id.carta_nombre);
        }
    }
}
