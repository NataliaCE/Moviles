/**
 * Ejercicio 8 - Bases de datos
 *
 * @author Natalia Canudas
 *
 */

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

    /**
     * Se le llama cuando la actividad está comenzando
     *
     * @param savedInstanceState - Contiene los datos de la actividad si se ha cerrado previamente.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrar);

        btnVolver = (Button) findViewById(R.id.btn_volverBorrar);
        btnBorrar = (Button) findViewById(R.id.btn_borrar);
        txtDisco = (EditText) findViewById(R.id.et_discoBorrar);
        txtGrupo = (EditText) findViewById(R.id.et_grupoBorrar);

        //Se crea o abre la base de datos
        db = openOrCreateDatabase("MiMusica", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS MiMusica(Grupo VARCHAR, Disco VARCHAR);");
    }

    /**
     * Se ejecuta al hacer click en el botón Volver.
     * Lleva a la actividad principal.
     *
     * @param v
     */
    public void clickVolver(View v) {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }

    /**
     * Se ejecuta al hacer click en el botón Borrar.
     * Borra de la base de datos el grupo y disco escritos en los EditText.
     *
     * @param v
     */
    public void Eliminar(View v) {
        db.execSQL("DELETE FROM MiMusica WHERE Grupo = '" + txtGrupo.getText().toString() +
                "' AND Disco = '" + txtDisco.getText().toString() + "'");

        Toast.makeText(this, "Se borró el disco " + txtDisco.getText().toString(),
                Toast.LENGTH_LONG).show();

        txtDisco.setText("");
        txtGrupo.setText("");
    }
}
