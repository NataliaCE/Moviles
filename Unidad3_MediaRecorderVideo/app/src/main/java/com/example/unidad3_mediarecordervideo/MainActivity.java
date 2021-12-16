package com.example.unidad3_mediarecordervideo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SurfaceHolder.Callback {

    File videoFile;
    MediaRecorder recorder;
    SurfaceHolder holder;
    Boolean recording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Pide permisos de cámara y grabación de audio
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, 0);

        }

        // En la siguiente ruta será almacenado el vídeo que vamos a grabar
        ContextWrapper cw = new ContextWrapper(this);
        videoFile = new File(cw.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "video.mp4");

        //Inicializaremos la pantalla para que la ponga en horizontal
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

        //Podremos hacer clic en la superficie para comenzar a grabar o parar de grabar
        cameraView.setClickable(true);
        cameraView.setOnClickListener(this);
    }

    private void initRecorder() {
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        CamcorderProfile cpHigh = CamcorderProfile
                .get(CamcorderProfile.QUALITY_HIGH);
        recorder.setProfile(cpHigh);
        recorder.setOutputFile(videoFile.getAbsolutePath());
    }

    private void prepareRecorder() {
        //conseguimos visualizar en directo lo que estamos grabando
        recorder.setPreviewDisplay(holder.getSurface());

        try {
            recorder.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            finish();
        } catch (IOException e) {
            e.printStackTrace();
            finish();
        }
    }

    //Hacemos clic en la SurfaceView para comenzar o terminar la grabación de vídeo.
    public void onClick(View v) {
        if (recording) {
            recorder.stop();
            recording = false;

        } else {
            recording = true;
            recorder.start();
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        prepareRecorder();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        if (recording) {
            recorder.stop();
            recording = false;
        }
        recorder.release();
        finish();
    }

    public void ClickVerVideo(View v) {
        Intent ver = new Intent(this, VerVideo.class);
        ver.putExtra("archivo", videoFile.getAbsolutePath());
        Log.d("camino1", videoFile.getAbsolutePath());
        startActivity(ver);
    }
}