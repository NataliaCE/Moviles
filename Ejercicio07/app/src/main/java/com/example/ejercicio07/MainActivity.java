package com.example.ejercicio07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Menu ajustes = menu.addSubMenu(R.string.ajustes);
        ajustes.add(R.string.color);
        ajustes.add(R.string.letra);
        menu.add(R.string.informacion);

        return true;
    }
}