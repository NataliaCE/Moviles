package com.example.unidad2_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creamos la lista de starks para el menú contextual
        TextView starks= (TextView) findViewById(R.id.listaStarks);
        registerForContextMenu(starks);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch(id)
        {
            case R.id.ajustes:
                Toast.makeText(getApplicationContext(), "Se ha pulsado Ajustes", Toast.LENGTH_LONG).show();
                return true;

            case R.id.contacto:
                Toast.makeText(getApplicationContext(), "Se ha pulsado Contactos", Toast.LENGTH_LONG).show();
                return true;

            case R.id.idioma:
                Toast.makeText(getApplicationContext(), "Se ha pulsado Idioma", Toast.LENGTH_LONG).show();
                return true;

            case R.id.tema:
                Toast.makeText(getApplicationContext(), "Se ha pulsado Tema", Toast.LENGTH_LONG).show();
                return true;

            case R.id.sonido:
                Toast.makeText(getApplicationContext(), "Se ha pulsado Sonido", Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.starks, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected (MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.matar:
                Toast.makeText(getApplicationContext(), "Hemos matado a alguien.", Toast.LENGTH_LONG).show();
                return true;
            case R.id.sanar:
                Toast.makeText(getApplicationContext(), "Hemos sanado a alguien.", Toast.LENGTH_LONG).show();
                return true;
            case R.id.enviarmensaje:
                Toast.makeText(getApplicationContext(), "Hemos enviado un mensaje a alguien.", Toast.LENGTH_LONG).show();
                return true;
        }

        return  super.onContextItemSelected(item);
    }


}