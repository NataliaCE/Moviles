package com.example.camposdegolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnBD, btnWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBD = (Button) findViewById(R.id.btn_bd);
        btnWeb = (Button) findViewById(R.id.btn_web);
    }

    public void clickBD(View v) {
        Intent intent = new Intent(this, Listas.class);
        intent.putExtra("fuente", "bd");
        startActivity(intent);
    }

    public void clickWeb(View v) {
        Intent intent = new Intent(this, Listas.class);
        intent.putExtra("fuente", "web");
        startActivity(intent);
    }
}