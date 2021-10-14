package com.example.aplicacion1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    TextView tv2;
    Button b;
    int numIzq = 1;
    int numDer = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializamos la variable relacionandola con el xml
        tv1 = (TextView) findViewById(R.id.izquierda); //Id de Saludo en Recursos(R)
        tv1.setText(String.valueOf(numIzq));
        tv2 = (TextView) findViewById(R.id.derecha); //Id de Saludo en Recursos(R)
        tv2.setText(String.valueOf(numDer));

        b = (Button) findViewById(R.id.boton);
        b.setText("SUMA 1");
    }

   public void click (View v) {
        numIzq++;
        numDer--;
        tv1.setText(String.valueOf(numIzq));
        tv2.setText(String.valueOf(numDer));
    }
}