package com.example.unidad3_mediarecordervideo;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_video);

        videoView = (VideoView) findViewById(R.id.videoview);
        Intent ver = getIntent();
        String path = ver.getStringExtra("archivo");
        Log.d("camino2", path);

        Uri videoUri = Uri.parse(path);
        videoView.setVideoURI(videoUri);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });
    }

}
