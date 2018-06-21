package com.example.claudia.melifluo;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class admin_editorial extends AppCompatActivity {

    public Button ed_limpiar, ed_delete, ed_update, ed_create, ed_read;
    public EditText ed_id, ed_nombre, ed_pa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_editorial);

        ed_delete = (Button)findViewById(R.id.ed_eliminar);
        ed_update = (Button)findViewById(R.id.ed_modificar);
        ed_create = (Button)findViewById(R.id.ed_agregar);
        ed_read = (Button)findViewById(R.id.ed_consultar);
        ed_limpiar = (Button)findViewById(R.id.ed_clean);

        ed_id = (EditText)findViewById(R.id.ed_id);
        ed_nombre = (EditText)findViewById(R.id.ed_name);
        ed_pa = (EditText) findViewById(R.id.ed_pais);

        ed_limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });

        ed_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clave;
                clave = ed_id.getText().toString();
                new CargarDatos().execute("http://192.168.0.25/Programas/Administrador/Editorial.php?accion=D&id="+clave+"&nombre=xx");
                Toast.makeText(getApplicationContext(), "Se ha eliminado correctamente", Toast.LENGTH_LONG).show();
                limpiar();

            }
        });

        ed_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clave, nombre;
                clave = ed_id.getText().toString();
                nombre = ed_nombre.getText().toString();

                new CargarDatos().execute("http://192.168.0.25/Programas/Administrador/Editorial.php?accion=U&id="+clave+"&nombre="+nombre);
                Toast.makeText(getApplicationContext(), "Se ha actualizado correctamente", Toast.LENGTH_LONG).show();

            }
        });

        ed_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clave, nombre;
                clave = ed_id.getText().toString();
                nombre = ed_nombre.getText().toString();

                new CargarDatos().execute("http://192.168.0.25/Programas/Administrador/Editorial.php?accion=C&id="+clave+"&nombre="+nombre);
                Toast.makeText(getApplicationContext(), "Se ha creado correctamente", Toast.LENGTH_LONG).show();
            }
        });

        ed_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Clave;
                Clave = ed_id.getText().toString();
                String url = "http://192.168.0.25/Programas/Administrador/R_Editorial.php?id="+Clave;
                limpiar();
                JsonArrayRequest peticion = new JsonArrayRequest(
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                try {
                                    ed_id.setText("" + response.getString(0));
                                    ed_nombre.setText("" + response.getString(1));
                                    ed_pa.setText("" + response.getString(2));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(admin_editorial.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                );
                RequestQueue x = Volley.newRequestQueue(admin_editorial.this);
                x.add(peticion);
            }
        });
    }

    public void limpiar(){
        ed_id.setText("");
        ed_nombre.setText("");
        ed_pa.setText("");
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

