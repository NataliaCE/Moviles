package com.example.unidad3_mediaplayervideo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.inline.InlineContentView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = new MediaPlayer();
        SurfaceView superficie = (SurfaceView) findViewById(R.id.superficie);

        //Se obtiene el objeto SurfaceHolder a partir del SurfaceView
        SurfaceHolder holder = superficie.getHolder();
        holder.addCallback(this);

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        //Se invoca al crearse la superficie
        //Asignar el archivo de video
        try {
            mediaPlayer.setDisplay(holder);
            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse("android.resource://" +
                    getPackageName() + "/" + R.raw.video));
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IllegalArgumentException e) {
            Log.d("MEDIA_PLAYER", e.getMessage());
        } catch (IllegalStateException e) {
            Log.d("MEDIA_PLAYER", e.getMessage());
        } catch (IOException e) {
            Log.d("MEDIA_PLAYER", e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        //Se invoca cuando se destruye la superficie
        //Lo aprovechamos para liberar los recursos asociados al MediaPlayer
        mediaPlayer.release();
    }
}