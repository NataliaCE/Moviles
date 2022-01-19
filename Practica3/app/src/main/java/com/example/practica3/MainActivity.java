package com.example.practica3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    static Boolean visitaEsp = false;
    static Boolean visitaPor = false;
    static Boolean visitaFra  = false;
    ImageView espana, portugal, francia;

    /**
     * Se ejecuta al iniciar la aplicación
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        espana = (ImageView) findViewById(R.id.spain);
        portugal = (ImageView) findViewById(R.id.portugal);
        francia = (ImageView) findViewById(R.id.francia);

        //Permisos
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, 0);

        }

        while(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }

        //LISTENERS
        espana.setOnClickListener(new View.OnClickListener() {
            //Inicia la activity del pais, pasando el pais seleccionado como dato del Intent
            @Override
            public void onClick(View view) {
                Intent pais = new Intent(getApplicationContext(), Pais.class);
                pais.putExtra("pais", "espana");
                startActivity(pais);
            }
        });

        espana.setOnLongClickListener(new View.OnLongClickListener() {
            /*
            Si el pais ha sido visitado crea un circulo verde, si no crea uno rojo,
            También hace una animación.
             */
            @Override
            public boolean onLongClick(View view) {
                if(!visitaEsp) {
                    rojo(espana);
                } else {
                    verde(espana);
                }
                animacion(espana);
                return true;
            }
        });

        portugal.setOnClickListener(new View.OnClickListener() {
            //Inicia la activity del pais, pasando el pais seleccionado como dato del Intent
            @Override
            public void onClick(View view) {
                Intent pais = new Intent(getApplicationContext(), Pais.class);
                pais.putExtra("pais", "portugal");
                startActivity(pais);
            }
        });

        portugal.setOnLongClickListener(new View.OnLongClickListener() {
            /*
            Si el pais ha sido visitado crea un circulo verde, si no crea uno rojo,
            También hace una animación.
             */
            @Override
            public boolean onLongClick(View view) {
                if(!visitaPor) {
                    rojo(portugal);
                } else {
                    verde(portugal);
                }
                animacion(portugal);
                return true;
            }
        });

        francia.setOnClickListener(new View.OnClickListener() {
            //Inicia la activity del pais, pasando el pais seleccionado como dato del Intent
            @Override
            public void onClick(View view) {
                Intent pais = new Intent(getApplicationContext(), Pais.class);
                pais.putExtra("pais", "francia");
                startActivity(pais);
            }
        });

        francia.setOnLongClickListener(new View.OnLongClickListener() {
            /*
            Si el pais ha sido visitado crea un circulo verde, si no crea uno rojo,
            También hace una animación.
             */
            @Override
            public boolean onLongClick(View view) {
                if(!visitaFra) {
                    rojo(francia);
                } else {
                    verde(francia);
                }
                animacion(francia);
                return true;
            }
        });
    }

    /**
     * Ejecuta una animación de subir y bajar sobre la bandera introducida como parámetro.
     * @param bandera - Representa la bandera seleccionada
     */
    public void animacion(ImageView bandera) {
        ObjectAnimator subir = ObjectAnimator.ofFloat(bandera, "translationY", -50f);
        subir.setDuration(500);
        subir.setStartDelay(50);
        subir.start();

        ObjectAnimator bajar = ObjectAnimator.ofFloat(bandera, "translationY", 3f);
        bajar.setDuration(500);
        bajar.setStartDelay(600);
        bajar.start();
    }

    /**
     * Dibuja un círculo verde sobre la bandera introducida en el parámetro
     * @param imagen
     */
    public void verde(ImageView imagen) {
        Bitmap bitmap = Bitmap.createBitmap(imagen.getWidth(), imagen.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        imagen.draw(c);

        Paint p = new Paint();
        p.setColor(Color.GREEN);
        c.drawCircle(imagen.getWidth()/6, imagen.getHeight()/6, imagen.getHeight()/8, p);
        imagen.setImageBitmap(bitmap);
    }

    /**
     * Dibuja un círculo rojo sobre la bandera introducida en el parámetro
     * @param imagen
     */
    public void rojo(ImageView imagen) {
        Bitmap bitmap = Bitmap.createBitmap(imagen.getWidth(), imagen.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        imagen.draw(c);

        Paint p = new Paint();
        p.setColor(Color.RED);
        c.drawCircle(imagen.getWidth()/6, imagen.getHeight()/6, imagen.getHeight()/8, p);
        imagen.setImageBitmap(bitmap);
    }
}