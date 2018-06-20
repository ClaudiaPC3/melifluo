package com.example.claudia.melifluo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class admin_autores extends AppCompatActivity {

    public Button au_limpiar, au_delete, au_update, au_create, au_read;
    public EditText au_id, au_nombre, au_apellidoP, au_apellidoM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_autores);

        au_delete = (Button)findViewById(R.id.au_eliminar);
        au_update = (Button)findViewById(R.id.au_modificar);
        au_create = (Button)findViewById(R.id.au_agregar);
        au_read = (Button)findViewById(R.id.au_consultar);
        au_limpiar = (Button)findViewById(R.id.au_clean);

        au_id = (EditText)findViewById(R.id.au_id);
        au_nombre = (EditText)findViewById(R.id.au_name);
        au_apellidoP = (EditText)findViewById(R.id.au_app);
        au_apellidoM = (EditText)findViewById(R.id.au_apm);

        au_limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });

        au_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clave;
                clave = au_id.getText().toString();
                new CargarDatos().execute("http://192.168.0.25/Programas/Administrador/Autor.php?accion=D&id="+clave+"&nombre=xx&apellidop=xx&apellidom=xx");
                Toast.makeText(getApplicationContext(), "Se ha eliminado correctamente", Toast.LENGTH_LONG).show();
                limpiar();
            }
        });

        au_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clave, nombre, apellidoP, apellidoM;
                clave = au_id.getText().toString();
                nombre = au_nombre.getText().toString();
                apellidoP = au_apellidoP.getText().toString();
                apellidoM = au_apellidoM.getText().toString();

                new CargarDatos().execute("http://192.168.0.25/Programas/Administrador/Autor.php?accion=U&id="+clave+"&nombre="+nombre+"&apellidop="+apellidoP+"&apellidom="+apellidoM);
                Toast.makeText(getApplicationContext(), "Se ha actualizado correctamente", Toast.LENGTH_LONG).show();

            }
        });

        au_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clave, nombre, apellidoP, apellidoM;
                clave = au_id.getText().toString();
                nombre = au_nombre.getText().toString();
                apellidoP = au_apellidoP.getText().toString();
                apellidoM = au_apellidoM.getText().toString();

                new CargarDatos().execute("http://192.168.0.25/Programas/Administrador/Autor.php?accion=C&id="+clave+"&nombre="+nombre+"&apellidop="+apellidoP+"&apellidom="+apellidoM);
                Toast.makeText(getApplicationContext(), "Se ha creado correctamente", Toast.LENGTH_LONG).show();
            }
        });

        au_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Clave;
                Clave = au_id.getText().toString();
                String url = "http://192.168.0.25/Programas/Administrador/R_Autor.php?id="+Clave;
                limpiar();
                JsonArrayRequest peticion = new JsonArrayRequest(
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                try {
                                    au_id.setText("" + response.getString(0));
                                    au_nombre.setText("" + response.getString(1));
                                    au_apellidoP.setText("" + response.getString(2));
                                    au_apellidoM.setText("" + response.getString(3));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(admin_autores.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                );
                RequestQueue x = Volley.newRequestQueue(admin_autores.this);
                x.add(peticion);
            }
        });
    }

    public void limpiar(){
        au_id.setText("");
        au_nombre.setText("");
        au_apellidoM.setText("");
        au_apellidoP.setText("");
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

