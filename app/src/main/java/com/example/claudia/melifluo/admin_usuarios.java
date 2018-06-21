package com.example.claudia.melifluo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import java.text.DateFormat;
import java.util.Calendar;

public class admin_usuarios extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    public Button us_limpiar, us_delete, us_update, us_create, us_read, calendar;
    public EditText descripcion, us_ap_m, us_ap_p, us_nombre, us_url, us_id;
    public TextView cumple;
    public CheckBox us_admin;
    public String fecha="0000-00-00", Admin="0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_usuarios);


        us_delete = (Button)findViewById(R.id.us_eliminar);
        us_update = (Button)findViewById(R.id.us_modificar);
        us_create = (Button)findViewById(R.id.us_agregar);
        us_read = (Button)findViewById(R.id.us_consultar);
        calendar = (Button)findViewById(R.id.us_calendario);
        us_limpiar = (Button)findViewById(R.id.us_clean);

        descripcion = (EditText)findViewById(R.id.us_desc);
        us_ap_p = (EditText)findViewById(R.id.us_app);
        us_ap_m = (EditText)findViewById(R.id.us_apm);
        us_nombre = (EditText)findViewById(R.id.us_name);
        us_url = (EditText)findViewById(R.id.us_url);
        us_id = (EditText)findViewById(R.id.us_id);

        cumple = (TextView) findViewById(R.id.us_nac);
        us_admin = (CheckBox)findViewById(R.id.us_administrador);

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date");
                cumple.setText(fecha);
            }
        });

        us_limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });

        us_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(us_admin.isChecked())
                    Admin = "1";
                if(us_admin.isChecked()==false)
                    Admin = "0";
            }
        });

        us_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clave;
                clave = us_id.getText().toString();
                new CargarDatos().execute("http://192.168.0.25/Programas/Administrador/Usuario.php?accion=D&clave="+clave+"&img=xx&nombre=xx&fecha=xx&apellidop=xx&apellidom=xx&Desc=xx&priv=xx");
                Toast.makeText(getApplicationContext(), "Se ha eliminado correctamente", Toast.LENGTH_LONG).show();
                limpiar();
            }
        });

        us_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Urlimg, NombreMod, ApellidoP, ApellidoM, DescMod, Clave="";
                Urlimg = us_url.getText().toString();
                NombreMod = us_nombre.getText().toString();
                ApellidoP = us_ap_p.getText().toString();
                ApellidoM = us_ap_m.getText().toString();
                DescMod = descripcion.getText().toString();
                Clave = us_id.getText().toString();
                if(Urlimg.isEmpty())
                    Urlimg="-";
                if(DescMod.isEmpty())
                    DescMod="-";
                new CargarDatos().execute("http://192.168.0.25/Programas/Administrador/Usuario.php?accion=U&clave="+Clave+"&img="+Urlimg+"&nombre="+NombreMod
                        +"&fecha="+fecha+"&apellidop="+ApellidoP+"&apellidom="+ApellidoM+"&desc="+DescMod+"&priv="+Admin);
                Toast.makeText(getApplicationContext(), "Se ha actualizado correctamente", Toast.LENGTH_LONG).show();

            }
        });

        us_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Urlimg, NombreMod, ApellidoP, ApellidoM, DescMod, Clave="";
                Urlimg = us_url.getText().toString();
                NombreMod = us_nombre.getText().toString();
                ApellidoP = us_ap_p.getText().toString();
                ApellidoM = us_ap_m.getText().toString();
                DescMod = descripcion.getText().toString();
                Clave = us_id.getText().toString();
                if(Urlimg.isEmpty())
                    Urlimg="-";
                if(DescMod.isEmpty())
                    DescMod="-";
                new CargarDatos().execute("http://192.168.0.25/Programas/Administrador/Usuario.php?accion=C&clave="+Clave+"&img="+Urlimg+"&nombre="+NombreMod
                        +"&fecha="+fecha+"&apellidop="+ApellidoP+"&apellidom="+ApellidoM+"&desc="+DescMod+"&priv="+Admin);

                Toast.makeText(getApplicationContext(), "Se ha creado correctamente", Toast.LENGTH_LONG).show();
            }
        });



     us_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String clave, admini;
                clave = us_id.getText().toString();
        String url  = "http://192.168.0.25/Programas/M_Usuario.php?Valor=" + clave;
                JsonArrayRequest peticion = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            us_nombre.setText(""+response.getString(1));
                            us_ap_p.setText(""+response.getString(2));
                            us_ap_m.setText(""+response.getString(3));
                            cumple.setText(""+response.getString(4));
                            descripcion.setText(""+response.getString(5));
                            us_url.setText(""+response.getString(6));
                            Admin = response.getString(8);
                            if ((Admin == "0")) {
                                if (us_admin.isChecked()) {
                                    us_admin.toggle();
                                }
                            }
                            if(us_admin.isChecked()== false&& Admin == "1")
                                us_admin.toggle();
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }

                ,new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(admin_usuarios.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
        );
        RequestQueue x= Volley. newRequestQueue(admin_usuarios.this);
        x.add(peticion);
            }
        });

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
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getApplicationContext(), "El usuario se modifico", Toast.LENGTH_LONG).show();

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

    public void limpiar(){
        us_id.setText("");
        us_nombre.setText("");
        descripcion.setText("");
        us_ap_p.setText("");
        us_ap_m.setText("");
        us_url.setText("");
        cumple.setText("");
        fecha = "0000-00-00";
        if(us_admin.isChecked())
            us_admin.toggle();
        Admin = "0";
    }
}
