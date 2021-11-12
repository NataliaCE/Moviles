/**
 * Ejercicio 8 - Bases de datos
 *
 * @author Natalia Canudas
 *
 */
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

    /**
     * Se le llama cuando la actividad está comenzando
     *
     * @param savedInstanceState - Contiene los datos de la actividad si se ha cerrado previamente.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAnyadir = (Button) findViewById(R.id.btn_anyadir);
        btnBorrar = (Button) findViewById(R.id.btn_eliminar);
        btnConsultar = (Button) findViewById(R.id.btn_consultar);
    }

    /**
     * Se ejecuta al hacer click en el botón Añadir.
     * Lleva a la actividad Anyadir.java
     *
     * @param v
     */
    public void clickAnyadir(View v) {
        Intent anyadir = new Intent(this, Anyadir.class);
        startActivity(anyadir);
    }

    /**
     * Se ejecuta al hacer click en el botón Borrar
     * Lleva a la actividad Borrar.java
     *
     * @param v
     */
    public void clickBorrar(View v) {
        Intent borrar = new Intent(this, Borrar.class);
        startActivity(borrar);
    }

    /**
     * Se ejecuta al hacer click en el botón Consultar
     * Lleva a la actividad Consultar.java
     *
     * @param v
     */
    public void clickConsulta(View v) {
        Intent consultar = new Intent(this, Consultar.class);
        startActivity(consultar);
    }
}