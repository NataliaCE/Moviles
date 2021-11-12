package com.example.ejercicio08_basesdedatos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btnAnyadir, btnBorrar, btnConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAnyadir = (Button) findViewById(R.id.btn_anyadir);
        btnBorrar = (Button) findViewById(R.id.btn_eliminar);
        btnConsultar = (Button) findViewById(R.id.btn_consultar);
    }

    public void clickAnyadir(View v) {
        Intent anyadir = new Intent(this, Anyadir.class);
        startActivity(anyadir);
    }

    public void clickBorrar(View v) {
        Intent borrar = new Intent(this, Borrar.class);
        startActivity(borrar);
    }

    public void clickConsulta(View v) {
        Intent consultar = new Intent(this, Consultar.class);
        startActivity(consultar);
    }
}