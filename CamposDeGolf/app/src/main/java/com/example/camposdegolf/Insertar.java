package com.example.camposdegolf;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Insertar extends AppCompatActivity {

    EditText identificador, nombre;
    Button btnInsertar;
    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertar);

        btnInsertar = (Button) findViewById(R.id.btn_insertar);
        identificador = (EditText) findViewById(R.id.et_identificador);
        nombre = (EditText) findViewById(R.id.et_nombre);

        Log.d("cosa", "comienza");
        db = openOrCreateDatabase("CamposGolf", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS CamposGolf(Identificador VARCHAR, Nombre VARCHAR);");
        Log.d("cosa", "abro base");
    }

    public void clickInsertarBD(View v) {
        db.execSQL("INSERT INTO CamposGolf VALUES ('" + identificador.getText().toString() + "', '" +
                nombre.getText().toString() + "')");
        Intent vuelta = new Intent(this, Listas.class);
        vuelta.putExtra("fuente", "bd");
        startActivity(vuelta);
    }
}

