package com.example.unidad2_10dialogos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button b, btnFecha, btnTiempo;
    TextView tv, tv2, tvFecha, tvTiempo;
    EditText etFecha, etTiempo;
    DatePickerDialog picker = null;
    int day;
    int month;
    int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = findViewById(R.id.boton);
        tv = findViewById(R.id.texto);
        tv2 = findViewById(R.id.texto2);
        btnFecha = findViewById(R.id.boton3);
        tvFecha = findViewById(R.id.texto3);
        etFecha = findViewById(R.id.fecha);
        btnTiempo = findViewById(R.id.boton4);
        tvTiempo = findViewById(R.id.texto4);
        etTiempo = findViewById(R.id.tiempo);

        etFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                if(day == 0) {
                    day = cldr.get(Calendar.DAY_OF_MONTH);
                    month = cldr.get(Calendar.MONTH);
                    year = cldr.get(Calendar.YEAR);
                } else {
                    day = picker.getDatePicker().getDayOfMonth();
                    month = picker.getDatePicker().getMonth();
                    year = picker.getDatePicker().getYear();
                }

                // date picker dialog
                picker = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                etFecha.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvFecha.setText("Selected Date: "+ etFecha.getText());
            }
        });

        etTiempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void clickMultiple(View v) {
        ArrayList lista = new ArrayList();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Muchas opciones")
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        //Toast.makeText(getApplicationContext(),R.string.ok, Toast.LENGTH_LONG).show();
                        tv2.setText(lista.toString());
                    }
                })
                .setMultiChoiceItems(R.array.ingredientes, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            String[] options = getResources().getStringArray(R.array.ingredientes);
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    lista.add(options[which]);
                                } else if (lista.contains(options[which])) {
                                    lista.remove(options[which]);
                                }
                            }
                        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void clickSingle(View v) {
        ArrayList lista = new ArrayList();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Solo una opción")
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        //Toast.makeText(getApplicationContext(),R.string.ok, Toast.LENGTH_LONG).show();
                        tv.setText(lista.toString());
                    }
                })
                .setSingleChoiceItems(R.array.ingredientes, 0,
                        new DialogInterface.OnClickListener() {
                            String[] options = getResources().getStringArray(R.array.ingredientes);
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                lista.clear();
                                lista.add(options[i]);
                            }
                        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}