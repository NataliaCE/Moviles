package com.example.practica3;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VerVideo extends AppCompatActivity {

    VideoView videoView;

    /**
     * Se ejecuta al iniciar la aplicación
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_video);

        videoView = (VideoView) findViewById(R.id.videoview);
        //El Intent recibe el path en el que se ha grabado el vídeo
        Intent ver = getIntent();
        String path = ver.getStringExtra("archivo");

        Uri videoUri = Uri.parse(path);
        videoView.setVideoURI(videoUri);

        //Reproduce el video
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });
    }
}
