package com.example.unidad2_3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Actividad2 extends AppCompatActivity {

    LinearLayout ll;
    TextView tv;
    Button b;
    Persona p = new Persona();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad2);
        ll = (LinearLayout) findViewById(R.id.linear2);
        tv = (TextView) findViewById(R.id.mensaje);
        b = (Button) findViewById(R.id.boton2);
        Intent ejemplo = getIntent();

        int numero = ejemplo.getIntExtra("numero", 0);
        double decimal = ejemplo.getDoubleExtra("decimal", 0);
        String cadena = ejemplo.getStringExtra("cadena");

        Log.d("Forma1 Numero: ", String.valueOf(numero));
        Log.d("Forma1 Decimal: ", String.valueOf(decimal));
        Log.d("Forma1 Cadena: ", cadena);

        Bundle b = ejemplo.getExtras();
        if(b != null) {
            numero = ejemplo.getIntExtra("numero", 0);
            decimal = ejemplo.getDoubleExtra("decimal", 0);
            cadena = ejemplo.getStringExtra("cadena");

            Log.d("Forma2 Numero: ", String.valueOf(numero));
            Log.d("Forma2 Decimal: ", String.valueOf(decimal));
            Log.d("Forma2 Cadena: ", cadena);
        }

        p = (Persona) ejemplo.getSerializableExtra("objeto");
        Log.d("Persona: ", p.getNombre());
        Log.d("Persona: ", String.valueOf(p.getEdad()));
        Log.d("Persona: ", p.getNacionalidad());

    }

    public void click2(View v) {
        Intent volver = new Intent(this, MainActivity.class);
        volver.putExtra("objeto", p);
        setResult(RESULT_OK, volver);
        finish();
    }

}
