package com.example.claudia.melifluo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Busqueda extends AppCompatActivity {
    public TextView T1, T2, T3;
    public String clave;
    public Button B1, B2, B3;
    public ImageView Im1, Im2, Im3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        T1=(TextView)findViewById(R.id.nombre_l1);
        T2=(TextView)findViewById(R.id.nombre_l2);
        T3=(TextView)findViewById(R.id.nombre_l3);
        B1=(Button)findViewById(R.id.grdr_l1);
        B2=(Button)findViewById(R.id.grdr_l2);
        B3=(Button)findViewById(R.id.grdr_l3);
        Im1=(ImageView)findViewById(R.id.imagen_l1);
        Im2=(ImageView)findViewById(R.id.imagen_l2);
        Im3=(ImageView)findViewById(R.id.imagen_l3);
        Intent recper = getIntent();
        clave = recper.getStringExtra("Clave");
        String url  = "http://192.168.0.25/Programas/M_Libro_Bus.php?Valor=0";
        JsonArrayRequest peticion = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            T1.setText(""+response.getString(0));
                            Picasso.get().load(""+response.getString(1)).into(Im1);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }
                ,new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Busqueda.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
        );
        url  = "http://192.168.0.25/Programas/M_Libro_Bus.php?Valor=1";
        JsonArrayRequest peticion2 = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            T2.setText(""+response.getString(0));
                            Picasso.get().load(""+response.getString(1)).into(Im2);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }
                ,new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Busqueda.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
        );
        url  = "http://192.168.0.25/Programas/M_Libro_Bus.php?Valor=2";
        JsonArrayRequest peticion3 = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            T3.setText(""+response.getString(0));
                            Picasso.get().load(""+response.getString(1)).into(Im3);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }
                ,new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Busqueda.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
        );
        RequestQueue x= Volley. newRequestQueue(Busqueda.this);
        x.add(peticion);
        x.add(peticion2);
        x.add(peticion3);

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CargarDatos().execute("http://192.168.0.25/Programas/Guardar_L.php?usuario="+clave+"&libro=0");
            }
        });

        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CargarDatos().execute("http://192.168.0.25/Programas/Guardar_L.php?usuario="+clave+"&libro=1");
            }
        });

        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CargarDatos().execute("http://192.168.0.25/Programas/Guardar_L.php?usuario="+clave+"&libro=2");
            }
        });
    }

    private class CargarDatos extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getApplicationContext(), "Se guardo el libro en tu biblioteca correctamente", Toast.LENGTH_LONG).show();

        }
    }

    private String downloadUrl(String myurl) throws IOException {
        Log.i("URL",""+myurl);
        myurl = myurl.replace(" ","%20");
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("respuesta", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
}

