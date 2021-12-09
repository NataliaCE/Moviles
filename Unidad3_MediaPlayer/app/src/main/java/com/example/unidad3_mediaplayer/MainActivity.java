package com.example.unidad3_mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button reproducir, parar, pausar, reanudar;
    int posicion = 0;
    boolean estaParado = false;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reproducir = findViewById(R.id.btn_s1);
        parar = findViewById(R.id.btn_s2);
        pausar = findViewById(R.id.btn_s3);
        reanudar = findViewById(R.id.btn_s4);

        mediaPlayer = MediaPlayer.create(this, R.raw.epicsound);

        reproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(estaParado) {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.epicsound);
                    estaParado = false;
                }
                mediaPlayer.start();
            }
        });

        pausar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                posicion = mediaPlayer.getCurrentPosition();
            }
        });

        parar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                estaParado = true;
            }
        });

        reanudar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.seekTo(posicion);
                mediaPlayer.start();
            }
        });
    }
}