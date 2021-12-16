package com.example.unidad3_objectanimator;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView image = (ImageView) findViewById(R.id.imageView);
        ObjectAnimator animator = ObjectAnimator.ofFloat(image, "translationY", 500f);
        animator.setDuration(3000);
        animator.setStartDelay(1000);
        animator.start();
        ObjectAnimator rotacion = ObjectAnimator.ofFloat(image, "rotation", 180);
        rotacion.setDuration(3000);
        rotacion.setStartDelay(4100);
        rotacion.start();
    }
}