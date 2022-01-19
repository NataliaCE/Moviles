package com.example.practica3;

import android.content.ContextWrapper;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;

public class Pais extends AppCompatActivity {

    Button mapa, grabarAudio, playAudio, grabarVideo, marcar;
    MediaRecorder mediaRecorder = null;
    boolean grabandoAudio = false;
    File audioFile;
    String pais;

    /**
     * Se ejecuta al iniciar la aplicación
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pais);

        //Asignación de botones
        mapa = (Button) findViewById(R.id.btn_mapa);
        grabarAudio = (Button) findViewById(R.id.btn_grabarAudio);
        playAudio = (Button) findViewById(R.id.btn_playAudio);
        grabarVideo = (Button) findViewById(R.id.btn_grabarVideo);
        marcar= (Button) findViewById(R.id.btn_marcar);

        //Recibe el pais seleccionado
        Intent main = getIntent();
        pais = main.getStringExtra("pais");

        //LISTENERS

        //Comienza y detiene la grabación de audio
        grabarAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!grabandoAudio) { //Si no está grabando, graba
                    grabandoAudio = true;
                    Toast.makeText(getApplicationContext(), "Grabando", Toast.LENGTH_LONG).show();
                    ContextWrapper cw = new ContextWrapper(getApplicationContext());
                    //Define el archivo en el que se va a guardar la gravación
                    audioFile = new File(cw.getExternalFilesDir(Environment.DIRECTORY_MUSIC),"audio.3gp");

                    //Configura la grabación
                    mediaRecorder = new MediaRecorder();
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mediaRecorder.setOutputFile(audioFile.getAbsolutePath());
                    try {
                        mediaRecorder.prepare(); //Prepara la grabación
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mediaRecorder.start(); //Comienza la grabación

                } else { //Si está grabando, deja de grabar
                    grabandoAudio = false;
                    Toast.makeText(getApplicationContext(), "Fin de grabación", Toast.LENGTH_LONG).show();

                    mediaRecorder.stop();
                    mediaRecorder.release();
                    mediaRecorder = null;
                }

            }
        });

        //Reproduce el audio que se ha grabado
        playAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Toast.makeText(getApplicationContext(), "Reproduciendo", Toast.LENGTH_LONG).show();
                    MediaPlayer mediaPlayer = new MediaPlayer();

                    //Define la fuente del audio
                    mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(audioFile.getAbsolutePath()));
                    mediaPlayer.prepare();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.start(); //Empieza a reproducir
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //Inicia la actividad de grabar
        grabarVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent video = new Intent(getApplicationContext(), GrabarVideo.class);
                startActivity(video);
            }
        });

        //Inicia la actividad del mapa y envia el pais como dato
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapa = new Intent(getApplicationContext(), Mapa.class);
                mapa.putExtra("pais", pais);
                startActivity(mapa);
            }
        });

        // Cambia el estado de visitado de un pais
        marcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (pais) {
                    case "espana":
                        MainActivity.visitaEsp = visitar(MainActivity.visitaEsp);
                        break;
                    case "portugal":
                        MainActivity.visitaPor = visitar(MainActivity.visitaPor);
                        break;
                    case "francia":
                        MainActivity.visitaFra = visitar(MainActivity.visitaFra);
                        break;
                }
            }
        });
    }

    /**
     * Cambia el estado de visitado de un pais
     * @param visitado
     */
    public boolean visitar(boolean visitado) {
        if(visitado) {
            Toast.makeText(getApplicationContext(), "No visitado", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(getApplicationContext(), "Visitado", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

}
