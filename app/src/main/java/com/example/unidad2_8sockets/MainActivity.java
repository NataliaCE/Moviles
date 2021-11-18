package com.example.unidad2_8sockets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    Button btnEncender, btnCliente, btnApagar;
    ServerSocket serverSocket = null;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEncender = (Button) findViewById(R.id.btnEncender);
        btnCliente = (Button) findViewById(R.id.btnCliente);
        btnApagar = (Button) findViewById(R.id.btnApagar);
        tv = (TextView) findViewById(R.id.texto);
    }

    public void clickEncender(View v) {
        new Thread(new Runnable(){
            @Override public void run() {
                try {
                    serverSocket = new ServerSocket(9092);
                    //Toast.makeText(getApplicationContext(), "Servidor encendido.", Toast.LENGTH_SHORT);
                    while(true) {
                        Socket socket = serverSocket.accept();

                        BufferedReader entrada = new BufferedReader(
                                new InputStreamReader(socket.getInputStream()));
                        String datos = entrada.readLine();
                        Log.d("mensaje", datos);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv.setText(datos);
                            }
                        });
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void clickCliente(View v) {
        new Thread(new Runnable(){
            @Override public void run() {
                try {
                    Socket msocket = new Socket("127.0.0.1", 9092);
                    BufferedWriter miEscritor = new BufferedWriter(new OutputStreamWriter(msocket.getOutputStream()));
                    miEscritor.write("texto");
                    miEscritor.flush();//Fuerza la escritura
                    miEscritor.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void clickApagar(View v) {
        try {
            serverSocket.close();
            Toast.makeText(this, "Servidor apagado.", Toast.LENGTH_SHORT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}