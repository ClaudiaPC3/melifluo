package com.example.claudia.melifluo;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
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
import java.net.PortUnreachableException;
import java.net.URL;
import java.util.Calendar;

public class admin_libros extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    public Button calendario, libi_limpiar, libi_create, libi_update, libi_delete, libi_read;
    public TextView libi_fecha;
    public String fecha;
    public EditText libi_id, libi_url, libi_titulo, libi_numed, libi_ed, libi_enc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_libros);

        calendario = (Button)findViewById(R.id.lib_cal);
        libi_fecha = (TextView) findViewById(R.id.lib_publicacion);
        calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date");
                libi_fecha.setText(fecha);
            }
        });

        libi_limpiar = (Button)findViewById(R.id.lib_clean);
        libi_create = (Button)findViewById(R.id.lib_agregar);
        libi_update = (Button)findViewById(R.id.lib_modificar);
        libi_delete = (Button)findViewById(R.id.lib_eliminar);
        libi_read = (Button)findViewById(R.id.lib_consultar);

        libi_id = (EditText)findViewById(R.id.lib_id);
        libi_url = (EditText)findViewById(R.id.lib_url);
        libi_titulo = (EditText)findViewById(R.id.lib_titulo);
        libi_numed = (EditText)findViewById(R.id.lib_numed);
        libi_ed = (EditText)findViewById(R.id.lib_ed);
        libi_enc = (EditText)findViewById(R.id.lib_enc);


        libi_limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean();
            }
        });

        libi_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        libi_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        libi_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clave;
                clave = libi_id.getText().toString();
                new CargarDatos().execute("http://192.168.0.25/Programas/Administrador/Libro.php?accion=D&clave="+clave+"&titulo=xx&codigo=xx&imagen=xx&fecha=xx&apellidom=xx&numed=xx&encuadernado=xx&editorial=xx");
                Toast.makeText(getApplicationContext(), "Se ha eliminado correctamente", Toast.LENGTH_LONG).show();
            }
        });

        libi_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    public void clean(){
        libi_id.setText("");
        libi_url.setText("");
        libi_titulo.setText("");
        libi_numed.setText("");
        libi_ed.setText("");
        libi_enc.setText("");
        libi_fecha.setText("");
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DATE, dayOfMonth);
        if (month < 10)
            fecha = year + "-0" + month + "-";
        else
            fecha = year + "-" + month + "-";
        if (dayOfMonth < 10)
            fecha = fecha + "0" + dayOfMonth;
        else
            fecha = fecha + dayOfMonth;
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