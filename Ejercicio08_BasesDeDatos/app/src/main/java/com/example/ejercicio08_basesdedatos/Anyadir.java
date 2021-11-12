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
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Anyadir extends AppCompatActivity {

    EditText txtDisco, txtGrupo;
    Button btnAnyadir, btnVolver;
    SQLiteDatabase db;

    /**
     * Se le llama cuando la actividad está comenzando
     *
     * @param savedInstanceState - Contiene los datos de la actividad si se ha cerrado previamente.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anyadir);

        btnVolver = (Button) findViewById(R.id.btn_volverAnyadir);
        btnAnyadir = (Button) findViewById(R.id.btn_anyadirAnyadir);
        txtDisco = (EditText) findViewById(R.id.et_discoAnyadir);
        txtGrupo = (EditText) findViewById(R.id.et_grupoAnyadir);

        //Se abre o crea la base de datos
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
     * Se ejecuta al hacer click en el botón Añadir.
     * Añade el grupo y disco escritos en los EditText a la base de datos.
     *
     * @param v
     */
    public void clickAnyadirBD(View v) {
        db.execSQL("INSERT INTO MiMusica VALUES ('" + txtGrupo.getText().toString() + "', '" +
                txtDisco.getText().toString() + "')");

        Toast.makeText(this, "Se añadió el disco " + txtDisco.getText().toString(),
                Toast.LENGTH_LONG).show();

        txtDisco.setText("");
        txtGrupo.setText("");
    }


}
