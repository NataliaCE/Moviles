package com.example.calculadoradeprimos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Definición de elementos
    TextView tituloTxt;
    TextView explicacionTxt;
    TextView posicionTxt;
    TextView resultadoTxt;
    EditText posicionInput;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tituloTxt = (TextView) findViewById(R.id.tituloText);
        explicacionTxt = (TextView) findViewById(R.id.explicacionText);
        posicionTxt = (TextView) findViewById(R.id.posicionText);
        resultadoTxt = (TextView) findViewById(R.id.resultadoText);
        boton = (Button) findViewById(R.id.boton);
        posicionInput = (EditText) findViewById(R.id.posicionInput);
    }

    //Lista para almacenar los primos que ya se han encontrado
    static ArrayList<Integer> lista = new ArrayList<Integer>();

    public void click(View v) {
        //Si la lista está vacía, se inicializa con el primer número primo
        if(lista.size() == 0) {
            lista.add(2);
        }
        int posicion = Integer.parseInt(posicionInput.getText().toString());
        resultadoTxt.setText(String.valueOf(calcular(posicion)));
    }

    public static int calcular(int pos) {
        // Si el número ya se ha encontrado, se busca en la lista
        if(lista.size() > pos) {
            return lista.get(pos - 1);

        } else {
            // Si no se ha encontrado, se sigue buscando a partir del último de la lista
            int numero = lista.get(lista.size()-1);
            int contador = lista.size(); // Posicion en la que se comienza a buscar
            boolean numeroPrimo = false;

            while(contador != pos) {
                numeroPrimo = false;
                while(!numeroPrimo) {
                    numero++;
                    numeroPrimo = esPrimo(numero);
                    if(numeroPrimo) {
                        contador++;
                        lista.add(numero);
                    }
                }
            }
            return numero;
        }
    }

    public static boolean esPrimo(int numero) {
        boolean primo = true;
        for(int i = 2; i <= numero/2; i++) {
            if(numero%i == 0) {
                primo = false;
                break;
            }
        }
        return primo;
    }
}