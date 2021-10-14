package com.example.unidad2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    static int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recorrer();
        anadeHijos();
        anadeCheck();
    }

    public void recorrer() {
        View v;
        Button b;
        TextView tv;
        LinearLayout g = (LinearLayout) findViewById(R.id.linear1);
        for (int i = 0; i < g.getChildCount(); i++) {
            v = g.getChildAt(i);
            Log.d("hijos", "objeto: "+v.toString());
            Log.d("clase", v.getClass().getSimpleName());

            if (v.getClass().getSimpleName().equals("MaterialButton")) {
                b=(Button) v;
                b.setText(R.string.hecho);
            }
            else if(v.getClass().getSimpleName().equals("MaterialTextView")) {
                tv = (TextView) v;
                tv.setText(R.string.hecho);
            }
        }

    }

    public void anadeHijos() {
        LinearLayout g = (LinearLayout) findViewById(R.id.linear1);
        Button b;

        for (int i =0; i<2;i++) {
            b=new Button (this);
            b.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            b.setText(getString(R.string.texto) + i);
            b.setId(View.generateViewId());

            b.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    TextView tv = (TextView) findViewById(R.id.textView1);
                    tv.setText(getString(R.string.pulsado) + contador++);
                }
            });

            g.addView(b,i);
        }
    }

    public void anadeCheck() {
        LinearLayout l = (LinearLayout) findViewById(R.id.linear1);
        CheckBox chkRojo = (CheckBox) findViewById(R.id.checkRojo);
        CheckBox chkAzul = (CheckBox) findViewById(R.id.checkAzul);
        CheckBox chkAmarillo = (CheckBox) findViewById(R.id.checkAmarillo);

        chkRojo.setOnCheckedChangeListener(this);
        chkAzul.setOnCheckedChangeListener(this);
        chkAmarillo.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton cb, boolean b) {
        TextView tv = (TextView) findViewById(R.id.textView1);
        String roj = "Rojo: ";
        String azu = "Azul: ";
        String ama = "Amarillo: ";
        switch (cb.getId()) {
            case R.id.checkRojo:
                tv.setText(roj + b);
                break;
            case R.id.checkAzul:
                tv.setText(azu + b);
                break;
            case R.id.checkAmarillo:
                tv.setText(ama + b);
                break;
        }
    }
}

