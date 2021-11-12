package com.example.ejercicio08_basesdedatos;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Borrar extends AppCompatActivity {

    EditText txtDisco, txtGrupo;
    Button btnBorrar, btnVolver;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrar);

        btnVolver = (Button) findViewById(R.id.btn_volverBorrar);
        btnBorrar = (Button) findViewById(R.id.btn_borrar);
        txtDisco = (EditText) findViewById(R.id.et_discoAnyadir);
        txtGrupo = (EditText) findViewById(R.id.et_grupoAnyadir);

        db = openOrCreateDatabase("MiMusica", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS MiMusica(Grupo VARCHAR, Disco VARCHAR);");
    }

    public void clickVolver(View v) {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }

    public void Eliminar(View v) {
        db.execSQL("DELETE FROM MiMusica WHERE Grupo = '" + txtGrupo.getText().toString() +
                "' AND Disco = '" + txtDisco.getText().toString() + "'");

        Toast.makeText(this, "Se borró el disco " + txtDisco.getText().toString(),
                Toast.LENGTH_LONG).show();
    }
}
