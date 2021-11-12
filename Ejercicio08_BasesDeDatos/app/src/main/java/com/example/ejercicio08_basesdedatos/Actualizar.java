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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Actualizar extends AppCompatActivity {

    TextView txtGrupo;
    EditText txtDisco;
    Button volver, actualizar;
    SQLiteDatabase db;

    /**
     * Se le llama cuando la actividad está comenzando
     *
     * @param savedInstanceState - Contiene los datos de la actividad si se ha cerrado previamente.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actualizar);

        txtGrupo = (TextView) findViewById(R.id.tv_grupoBDActualizar);
        txtDisco = (EditText) findViewById((R.id.et_discoActualizar));
        actualizar = (Button) findViewById(R.id.btn_actualizar);
        volver = (Button) findViewById(R.id.btn_volverActualizar);

        //Se abre o crea la base de datos
        db = openOrCreateDatabase("MiMusica", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS MiMusica(Grupo VARCHAR, Disco VARCHAR);");

        //El Intent recibe el nombre del grupo
        Intent consulta = getIntent();
        String grupo = consulta.getStringExtra("grupo");

        txtGrupo.setText(grupo);
    }

    /**
     * Se ejecuta al hacer click en el botón Actualizar.
     * Actualiza el nombre del disco con el introducido por el EditText
     *
     * @param v
     */
    public void clickActualizarBD(View v) {
        db.execSQL("UPDATE MiMusica SET Disco = '" + txtDisco.getText().toString() + "' WHERE Grupo = '" +
                txtGrupo.getText().toString() + "'");
        Toast.makeText(this, "Se actualizó el grupo " + txtGrupo.getText().toString(),
                Toast.LENGTH_LONG).show();
        txtDisco.setText("");
    }

    /**
     * Se ejecuta al hacer click en el botón Volver.
     * Lleva a la actividad Consultar.java
     *
     * @param v
     */
    public void clickVolverConsulta(View v) {
        Intent main = new Intent(this, Consultar.class);
        startActivity(main);
    }
}
