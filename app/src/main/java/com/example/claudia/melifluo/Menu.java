package com.example.claudia.melifluo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import org.json.JSONObject;

public class Menu extends AppCompatActivity implements View.OnClickListener{
    public Button perfil, biblioteca, busqueda, configuraciones;
    public TextView filas;
    public String Filas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        perfil = (Button)findViewById(R.id.perfil);
        biblioteca = (Button)findViewById(R.id.biblioteca);
        busqueda = (Button)findViewById(R.id.busqueda);
        filas = (TextView)findViewById(R.id.filasta);
        configuraciones = (Button)findViewById(R.id.configuracion);
        Intent rec = getIntent();
        final String cuenta = rec.getStringExtra("cuenta");


        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent per = new Intent(Menu.this, Perfil2.class);
                per.putExtra("Clave", cuenta);
                startActivity(per);
            }
        });

        biblioteca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bib = new Intent(Menu.this, MiBiblioteca.class);
                bib.putExtra("Clave", cuenta);
                startActivity(bib);
            }
        });

        busqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bus = new Intent(Menu.this, Busqueda.class);
                bus.putExtra("Clave", cuenta);
                startActivity(bus);
            }
        });

        configuraciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent conf = new Intent(Menu.this, Configuraciones.class);
                conf.putExtra("Clave", cuenta);
                startActivity(conf);
            }
        });



    }


    @Override
    public void onClick(View v) {

    }
}
