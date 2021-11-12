/**
 * Ejercicio 8 - Bases de datos
 *
 * @author Natalia Canudas
 *
 */
package com.example.ejercicio08_basesdedatos;

import android.content.Context;
import android.content.Intent;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

    /**
     * Se le llama cuando la actividad está comenzando
     *
     * @param savedInstanceState - Contiene los datos de la actividad si se ha cerrado previamente.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultar);

        reciclador = (RecyclerView) findViewById(R.id.recycler);
        btnVolver = (Button) findViewById(R.id.btn_volverConsulta);

        //Se crea o abre la base de datos
        db = openOrCreateDatabase("MiMusica", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS MiMusica(Grupo VARCHAR, Disco VARCHAR);");

        List<Encapsulador> datos = new ArrayList<>();

        //Se coloca un cursor al principio de la tabla que va recogiendo los datos hasta que llega al final
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

        //Se añade un Listener para cuando se seleccione un elemento del RecyclerView
        reciclador.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector  = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent event){
                    return true;
                }
            });

            /**
             * Al tocar un elemento el RecyclerView, se hace crea un Intent que abre la actividad Actualizar.java
             * y envía el nombre del grupo.
             *
             * @param rv
             * @param e
             * @return false
             */
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View hijo = rv.findChildViewUnder(e.getX(), e.getY());
                if (hijo != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(hijo);
                    Intent actualizar = new Intent(getApplicationContext(), Actualizar.class);
                    actualizar.putExtra("grupo", datos.get(position).getGrupo());
                    startActivity(actualizar);
                }

                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

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

}
