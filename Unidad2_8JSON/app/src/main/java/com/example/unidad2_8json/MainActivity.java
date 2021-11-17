package com.example.unidad2_8json;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String urlString = "https://servicios.ine.es/wstempus/js/es/DATOS_TABLA/43491?tip=AM";

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
            new MyTask().execute(urlString);
        }
    }

    class MyTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0];
            HttpURLConnection urlConnection = null;
            URL url = null;

            try {
                url = new URL(urlString);
                urlConnection = (HttpURLConnection) url.openConnection();
                int codResp = urlConnection.getResponseCode();
                urlConnection.setConnectTimeout(20000);
                urlConnection.setReadTimeout(5000);
                urlConnection.setRequestMethod("GET");

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                StringBuilder result = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (ProtocolException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                urlConnection.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            ArrayList<String> elementos = new ArrayList<String>();

            try {
                JSONArray jsonArray = new JSONArray(result);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObjecto = jsonArray.getJSONObject(i);
                    //Log.d("[JSON]Nombre " + i, jsonObjecto.getString("Nombre"));

                    JSONArray arrayDatos = new JSONArray(jsonObjecto.getString("Data"));
                    JSONObject datos = arrayDatos.getJSONObject(0);
                    //Log.d("[JSON]Valor " + i, datos.getString("Valor"));

                    String elemento = jsonObjecto.getString("Nombre") + "\n\t\t" +
                            datos.getString("Valor");
                    elementos.add(elemento);
                }
                ArrayAdapter<String> adaptador;
                ListView l = (ListView) findViewById(R.id.lista);
                adaptador = new ArrayAdapter<String>(getApplicationContext(), R.layout.fila, elementos);
                l.setAdapter(adaptador);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}