package com.example.unidad3_mediarecordervideo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SurfaceHolder.Callback {

    File videoFile;
    MediaRecorder recorder;
    SurfaceHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Pide permisos de cámara y grabación de audio
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
                        PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) !=
                        PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO}, 0);

        }

        //Se define ruta en la que se almacena la grabación
        ContextWrapper cw = new ContextWrapper(this);
        videoFile = new File(cw.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "video.mp4");

        //Poner pantalla en horizontal
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //inicializaremos el MediaRecorder para poder hacer la grabación
        recorder = new MediaRecorder();
        initRecorder();
        setContentView(R.layout.activity_main);

        //Usaremos el SurfaceView/SurfaceHolder para poder visualizar lo que se está grabando.
        SurfaceView cameraView = (SurfaceView) findViewById(R.id.superficie);
        holder = cameraView.getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void onClick(View view) {

    }
}