package com.example.ejercicio08_basesdedatos;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Consultar extends AppCompatActivity {

    Button btnVolver;
    SQLiteDatabase db;

    private RecyclerView reciclador;
    private RecyclerView.Adapter adaptador;
    private RecyclerView.LayoutManager gestor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultar);

        reciclador = (RecyclerView) findViewById(R.id.recycler);
        btnVolver = (Button) findViewById(R.id.btn_volverConsulta);

        db = openOrCreateDatabase("MiMusica", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS MiMusica(Grupo VARCHAR, Disco VARCHAR);");
        listar();

    }

    public void clickVolver(View v) {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }

    public void listar() {
        List<Encapsulador> datos = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM MiMusica", null);

        if(c.getCount() > 0) {
            while(c.moveToNext()) {
                datos.add(new Encapsulador(c.getString(0), c.getString(1)));
            }
        }

        reciclador.setHasFixedSize(true);
        gestor = new LinearLayoutManager(this);
        reciclador.setLayoutManager(gestor);

        adaptador = new Adaptador(datos);
        reciclador.setAdapter(adaptador);
        c.close();
    }

}
