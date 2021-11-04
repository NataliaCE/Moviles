/**
 * Ejercicio 07 - Menús
 *
 * @author Natalia Canudas
 */
package com.example.ejercicio07;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

public class MainActivity extends AppCompatActivity {

    /**
     * Se le llama cuando la actividad está comenzando
     *
     * @param savedInstanceState - Contiene los datos de la actividad si se ha cerrado previamente.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Inicializa el contenido del menú de opciones
     *
     * @param menu - Menú en el que se van a colocar los elementos
     * @return true - Siempre devuelve verdadero
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu ajustes = menu.addSubMenu(R.string.ajustes);
        MenuItem color = ajustes.add(R.string.color);
        MenuItem letra = ajustes.add(R.string.letra);
        MenuItem información = menu.add(R.string.informacion);

        return true;
    }

}