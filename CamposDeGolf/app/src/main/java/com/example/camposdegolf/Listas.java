package com.example.camposdegolf;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Listas extends AppCompatActivity {

    private RecyclerView reciclador;
    private RecyclerView.Adapter adaptador;
    private RecyclerView.LayoutManager gestor;
    List<Encapsulador> datos = new ArrayList<>();

    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listas);

        Intent intent = getIntent();
        String fuente = intent.getStringExtra("fuente");

        reciclador = (RecyclerView) findViewById(R.id.recycler);

        if(fuente.equals("bd")) {
            db = openOrCreateDatabase("CamposGolf", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS CamposGolf(Identificador VARCHAR, Nombre VARCHAR);");
            Cursor c = db.rawQuery("SELECT * FROM CamposGolf", null);
            if(c.getCount() > 0) {
                while(c.moveToNext()) {
                    datos.add(new Encapsulador(R.drawable.jelly, c.getString(0), c.getString(1)));
                }
            }
            reciclador.setHasFixedSize(true);
            gestor = new LinearLayoutManager(this);
            reciclador.setLayoutManager(gestor);
            adaptador = new Adaptador(datos);
            reciclador.setAdapter(adaptador);
            c.close();

            reciclador.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

                GestureDetector gestureDetector  = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener(){
                    @Override
                    public boolean onSingleTapUp(MotionEvent event){
                        return true;
                    }
                });

                @Override
                public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                    View hijo = rv.findChildViewUnder(e.getX(), e.getY());
                    if (hijo != null && gestureDetector.onTouchEvent(e)) {
                        int position = rv.getChildAdapterPosition(hijo);
                        Intent actualizar = new Intent(getApplicationContext(), Actualizar.class);
                        actualizar.putExtra("id", datos.get(position).getIdentificador());
                        startActivity(actualizar);
                    }
                    return false;
                }

                @Override
                public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

                }

                @Override
                public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                }
            });
        }
        if(fuente.equals("web")) {
            String urlString = "https://nexo.carm.es/nexo/archivos/recursos/opendata/json/CamposdeGolf.json";

            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
                new MyTask().execute(urlString);
            }

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

        protected void onPostExecute(String result) {
            ArrayList<String> elementos = new ArrayList<String>();

            try {
                JSONArray jsonArray = new JSONArray(result);

                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObjecto = jsonArray.getJSONObject(i);
                    Log.d("[JSON]Nombre " + i, jsonObjecto.getString("Código"));
                    Log.d("[JSON]Nombre " + i, jsonObjecto.getString("Nombre"));
                    datos.add(new Encapsulador(R.drawable.jelly, jsonObjecto.getString("Código"),
                            jsonObjecto.getString("Nombre")));
                }
                reciclador.setHasFixedSize(true);
                gestor = new LinearLayoutManager(getApplicationContext());
                reciclador.setLayoutManager(gestor);
                adaptador = new Adaptador(datos);
                reciclador.setAdapter(adaptador);

                reciclador.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

                    GestureDetector gestureDetector  = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener(){
                        @Override
                        public boolean onSingleTapUp(MotionEvent event){
                            return true;
                        }
                    });

                    @Override
                    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                        View hijo = rv.findChildViewUnder(e.getX(), e.getY());
                        if (hijo != null && gestureDetector.onTouchEvent(e)) {
                            int position = rv.getChildAdapterPosition(hijo);
                            Intent actualizar = new Intent(getApplicationContext(), Actualizar.class);
                            actualizar.putExtra("id", datos.get(position).getIdentificador());
                            startActivity(actualizar);
                        }
                        return false;
                    }

                    @Override
                    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

                    }

                    @Override
                    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch(id)
        {
            case R.id.menu_insertar:
                Intent intent = new Intent(this, Insertar.class);
                startActivity(intent);
                return true;
            case R.id.menu_verificar:
                Intent verifica = new Intent(this, Verificar.class);
                startActivity(verifica);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
