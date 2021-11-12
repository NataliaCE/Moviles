package com.example.ejercicio08_basesdedatos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyHolder>{

    private List<Encapsulador> musica;

    public Adaptador(List<Encapsulador> musica) {
        this.musica = musica;
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
        holder.grupo.setText(musica.get(position).getGrupo());
        holder.disco.setText(musica.get(position).getDisco());
    }

    @Override
    public int getItemCount() {
        return musica.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        public TextView grupo;
        public TextView disco;

        public MyHolder(View vista) {
            super(vista);
            grupo = (TextView) vista.findViewById(R.id.tv_grupoCarta);
            disco = (TextView) vista.findViewById(R.id.tv_discoCarta);
        }
    }

}
