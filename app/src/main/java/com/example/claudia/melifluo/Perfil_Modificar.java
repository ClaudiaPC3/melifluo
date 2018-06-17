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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;

public class Perfil_Modificar extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    public EditText urlimg, nombremod, apellidop, apellidom, descmod;
    public String fecha="0000-00-00", Urlimg, NombreMod, ApellidoP, ApellidoM, DescMod, Admin="0", Clave="", clave;
    public CheckBox priv;
    public Button cumpleanios, modi;
    public Button cumple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil__modificar);
        Intent recper = getIntent();
        clave = recper.getStringExtra("clave");
        Clave=clave;
        cumpleanios = (Button)findViewById(R.id.naci);
        urlimg = (EditText)findViewById(R.id.url_imagen);
        nombremod = (EditText)findViewById(R.id.nombre_usuario);
        apellidop = (EditText)findViewById(R.id.apellidoP_usuario);
        apellidom = (EditText)findViewById(R.id.apellidoM_usuario);
        descmod= (EditText)findViewById(R.id.desc);
        priv = (CheckBox)findViewById(R.id.administrador);
        modi = (Button)findViewById(R.id.modif);
        cumple = (Button)findViewById(R.id.nac);

        cumple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        priv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Admin = "1";
            }
        });

        modi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Urlimg = urlimg.getText().toString();
                NombreMod = nombremod.getText().toString();
                ApellidoP = apellidop.getText().toString();
                ApellidoM = apellidom.getText().toString();
                DescMod = descmod.getText().toString();
                if(Urlimg.isEmpty())
                    Urlimg="-";
                if(DescMod.isEmpty())
                    DescMod="-";
                new CargarDatos().execute("http://192.168.0.25/Programas/Modificar_Us.php?clave="+Clave+"&img="+Urlimg+"&nombre="+NombreMod
                        +"&fecha="+fecha+"&apellidop="+ApellidoP+"&apellidom="+ApellidoM+"&desc="+DescMod+"&priv="+Admin);
                Intent amonos = new Intent(Perfil_Modificar.this, Menu.class);
                amonos.putExtra("cuenta", Clave);
                startActivity(amonos);
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
}
