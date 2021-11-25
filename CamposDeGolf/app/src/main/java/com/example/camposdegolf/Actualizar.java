package com.example.camposdegolf;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Actualizar extends AppCompatActivity {

    SQLiteDatabase db;
    TextView identificador;
    EditText nombre;
    Button eliminar, actualizar, votar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actualizar);

        identificador = (TextView) findViewById(R.id.tv_identificadorBD);
        nombre = (EditText) findViewById((R.id.et_nombreActualizar));
        actualizar = (Button) findViewById(R.id.btn_actualizar);
        eliminar = (Button) findViewById(R.id.btn_eliminar);
        votar = (Button) findViewById(R.id.btn_votar);

        db = openOrCreateDatabase("CamposGolf", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS CamposGolf(Identificador VARCHAR, Nombre VARCHAR);");

        Intent consulta = getIntent();
        String txtIdentificador = consulta.getStringExtra("id");
        identificador.setText(txtIdentificador);
    }

    public void clickEliminar(View v) {
        db.execSQL("DELETE FROM CamposGolf WHERE Identificador = '" + identificador.getText().toString() +
                "'");
        Intent vuelta = new Intent(this, Listas.class);
        vuelta.putExtra("fuente", "bd");
        startActivity(vuelta);
    }

    public void clickActualizar(View v) {
        db.execSQL("UPDATE CamposGolf SET Nombre = '" + nombre.getText().toString() + "' WHERE Identificador = '" +
                identificador.getText().toString() + "'");
        Intent vuelta = new Intent(this, Listas.class);
        vuelta.putExtra("fuente", "bd");
        startActivity(vuelta);
    }
}
