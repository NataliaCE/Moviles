package com.example.practica2_hipotenochas;

import static android.widget.LinearLayout.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    final int PRINCIPIANTE = 8;
    final int AMATEUR = 12;
    final int AVANZADO = 16;
    int tamanyo;
    int[][] matriz;
    int bombas;
    int bombasEncontradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //jugar(PRINCIPIANTE, 10);
        tamanyo = PRINCIPIANTE;
        bombas = 10;
        bombasEncontradas = 0;
        crearLayout();
    }

    public void crearLayout() {
        matriz = new int[tamanyo][tamanyo];
        int contador = bombas;
        while(contador > 0) {
            int x = (int) Math.floor(Math.random()*(tamanyo-1));
            int y = (int) Math.floor(Math.random()*(tamanyo-1));
            if(matriz[x][y] != -1) {
                matriz[x][y] = -1;
                contador--;
            }
        }

        LinearLayout layoutPadre = (LinearLayout) findViewById(R.id.linearVertical);
        layoutPadre.removeAllViews();
        for(int i = 0; i < tamanyo; i++) {
            LinearLayout layoutFila = new LinearLayout(MainActivity.this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            params.setMargins(-5, -5, -5, -5);
            layoutFila.setLayoutParams(params);
            layoutFila.setOrientation(HORIZONTAL);
            layoutPadre.addView(layoutFila);
            for(int j = 0; j < tamanyo; j++) {

                LinearLayout.LayoutParams paramBoton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                paramBoton.weight = 1;
                paramBoton.setMargins(-8, -8, -8, -8);

                /**
                 * El tag de los botones contiene un array con:
                 * 1- El valor del botón en la matriz.
                 * 2- Número de fila.
                 * 3- Número de columna
                 */
                int[] tag = {matriz[i][j],i, j};
                if(matriz[i][j] == 0) {
                    Button b = new Button(MainActivity.this);
                    b.setLayoutParams(paramBoton);
                    b.setTag(tag);
                    b.setId(View.generateViewId());
                    b.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
                    b.setOnClickListener(clickNormal);
                    b.setOnLongClickListener(clickLargo);

                    b.setTextSize(19);
                    layoutFila.addView(b);
                }else{
                    ImageButton ib = new ImageButton(MainActivity.this);
                    ib.setLayoutParams(paramBoton);
                    ib.setTag(tag);
                    ib.setId(View.generateViewId());
                    ib.setPadding(0, 0, 0, 0);
                    ib.setScaleType(ImageView.ScaleType.FIT_CENTER); //Android escala la imagen
                    ib.setAdjustViewBounds(true); //Ajusta los bordes
                    ib.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
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
            if(tag[0] == 0) {//No hay bomba
                Button b = (Button) findViewById(view.getId());
                String pista = String.valueOf(buscaBombas(tag[1], tag[2]));
                ponerColor(b, Integer.valueOf(pista));
                b.setText(pista);
                bombasEncontradas++;
            } else { //Hay bomba, derrorta
                ImageButton ib = (ImageButton) findViewById(view.getId());
                ib.setImageResource(R.drawable.bomba);
                bombasEncontradas = 0;
                mostrarAlerta(R.string.perdedor);
            }
        }
    };

    OnLongClickListener clickLargo = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            int[] tag = (int[]) view.getTag();
            if(tag[0] == 0) { //No hay bomba, derrota
                Button b = (Button) findViewById(view.getId());
                mostrarAlerta(R.string.perdedor);
                bombasEncontradas = 0;
            } else { //Hay bomba
                ImageButton ib = (ImageButton) findViewById(view.getId());
                ib.setImageResource(R.drawable.bandera_roja);
                bombasEncontradas++;
                if(bombas == bombasEncontradas) { //Victoria
                    mostrarAlerta(R.string.ganador);
                    bombasEncontradas = 0;
                }
            }
            return true;
        }
    };

    public int buscaBombas(int fila, int columna) {
        int contador = 0;
        if ((fila - 1 >= 0) && (columna - 1 >= 0) && matriz[fila - 1][columna - 1] == -1) contador++;
        if ((fila - 1 >= 0) && matriz[fila - 1][columna] == -1) contador++;
        if ((fila - 1 >= 0) && (columna + 1 < tamanyo) && matriz[fila - 1][columna + 1] == -1) contador++;
        if ((columna - 1 >= 0) && matriz[fila][columna - 1] == -1) contador++;
        if ((columna + 1 < tamanyo) && matriz[fila][columna + 1] == -1) contador++;
        if ((fila + 1 < tamanyo) && (columna - 1 >= 0) && matriz[fila + 1][columna - 1] == -1) contador++;
        if ((fila + 1 < tamanyo) && matriz[fila + 1][columna] == -1) contador++;
        if ((fila + 1 < tamanyo) && (columna + 1 < tamanyo) && matriz[fila + 1][columna + 1] == -1) contador++;
        return contador;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch(id) {
            case R.id.instrucciones:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setMessage(R.string.instrucciones_texto)
                        .setTitle(R.string.instrucciones)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // El usuario pulsa OK.
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.nuevoJuego:
                jugar(tamanyo, bombas);
                break;
            case R.id.principiante:
                jugar(PRINCIPIANTE, 10);
                break;
            case R.id.amateur:
                jugar(AMATEUR, 30);
                break;
            case R.id.avanzado:
                jugar(AVANZADO, 60);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void jugar(int dificultad, int cantidadBombas) {
        tamanyo = dificultad;
        bombas = cantidadBombas;
        bombasEncontradas = 0;
        crearLayout();
    }

    private void mostrarAlerta(int texto) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(texto)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // El usuario pulsa OK.
                        jugar(tamanyo, bombas);
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false); //Evita salir con la tecla de retroceso.
        dialog.setCanceledOnTouchOutside(false); //Evita salir pulsando fuera.
        dialog.show();
    }

    private void ponerColor(Button b, int numero) {
        switch (numero) {
            case 1:
                b.setTextColor(getResources().getColor(R.color.pista_1));
                break;
            case 2:
                b.setTextColor(getResources().getColor(R.color.pista_2));
                break;
            case 3:
                b.setTextColor(getResources().getColor(R.color.pista_3));
                break;
            case 4:
                b.setTextColor(getResources().getColor(R.color.pista_4));
                break;
            case 5:
                b.setTextColor(getResources().getColor(R.color.pista_5));
                break;
            case 6:
                b.setTextColor(getResources().getColor(R.color.pista_6));
                break;
            case 7:
                b.setTextColor(getResources().getColor(R.color.pista_7));
                break;
            case 8:
                b.setTextColor(getResources().getColor(R.color.pista_8));
                break;
        }
    }
}