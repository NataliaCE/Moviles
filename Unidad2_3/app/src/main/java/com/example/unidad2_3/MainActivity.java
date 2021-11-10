package com.example.unidad2_3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout ll;
    Button b;
    Persona p = new Persona("Natalia", 22, "española");

    //Definición del ActivityResultLauncher
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        //Si queremos recoger datos
                        Intent data = result.getData();
                        Bundle b = data.getExtras();
                        if(b != null) {
                            p = (Persona) data.getSerializableExtra("objeto");
                            Log.d("Vuelta: ", p.getNombre());
                            Log.d("Vuelta: ", String.valueOf(p.getEdad()));
                            Log.d("Vuelta: ", p.getNacionalidad());
                        }
                    }
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll = (LinearLayout) findViewById(R.id.layout1);
        b = (Button) findViewById(R.id.boton1);
    }

    public void click(View view) {
        Intent ejemplo = new Intent(this, Actividad2.class);
        ejemplo.putExtra("numero", 5);
        ejemplo.putExtra("decimal", 2.36);
        ejemplo.putExtra("cadena", "Esto es una cadena");
        ejemplo.putExtra("objeto", p);
        someActivityResultLauncher.launch(ejemplo);
        //startActivity(ejemplo);
    }

}




