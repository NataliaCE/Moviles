package com.example.practica2_hipotenochas;

import static android.widget.LinearLayout.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crearLayout(8, 8);
    }

    public void crearLayout(int filas, int columnas) {
        int[][] matriz = new int[filas][columnas];
        for(int i = 0; i < filas; i++) {
            for(int j = 0; j < columnas; j++) {
                matriz[i][j] = (int) Math.floor(Math.random()*2-1);
            }
        }

        LinearLayout layoutPadre = (LinearLayout) findViewById(R.id.linearVertical);
        for(int i = 0; i < filas; i++) {
            LinearLayout layoutFila = new LinearLayout(MainActivity.this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            layoutFila.setLayoutParams(params);
            layoutFila.setOrientation(HORIZONTAL);
            layoutPadre.addView(layoutFila);
            for(int j = 0; j < columnas; j++) {

                LinearLayout.LayoutParams paramBoton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                paramBoton.weight = 1;

                int[] tag = {matriz[i][j],i, j};
                if(matriz[i][j] == 0) {
                    Button b = new Button(MainActivity.this);
                    b.setLayoutParams(paramBoton);
                    b.setTag(tag);
                    b.setId(View.generateViewId());
                    b.setText("0");
                    b.setOnClickListener(clickNormal);
                    b.setOnLongClickListener(clickLargo);
                    layoutFila.addView(b);
                }else{
                    ImageButton ib = new ImageButton(MainActivity.this);
                    ib.setLayoutParams(paramBoton);
                    ib.setTag(tag);
                    ib.setId(View.generateViewId());
                    ib.setScaleType(ImageView.ScaleType.FIT_CENTER); //Android escala la imagen
                    ib.setAdjustViewBounds(true); //Ajusta los bordes
                    ib.setOnClickListener(clickNormal);
                    ib.setOnLongClickListener(clickLargo);
                    layoutFila.addView(ib);
                }
            }
        }

    }

    OnClickListener clickNormal = new OnClickListener() {
        @Override
        public void onClick(View view) {
            int[] tag = (int[]) view.getTag();
            //int tag = (int) view.getTag();
            if(tag[0] == 0) {
                Button b = (Button) findViewById(view.getId());
                b.setText("^.^");
            } else {
                ImageButton ib = (ImageButton) findViewById(view.getId());
                ib.setImageResource(R.drawable.bomba);
            }
        }
    };

    OnLongClickListener clickLargo = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            //int tag = (int) view.getTag();
            int[] tag = (int[]) view.getTag();
            if(tag[0] == 0) {
                Button b = (Button) findViewById(view.getId());
                b.setText("T.T");
            } else {
                ImageButton ib = (ImageButton) findViewById(view.getId());
                ib.setImageResource(R.drawable.bandera_roja);
            }
            return true;
        }
    };


}