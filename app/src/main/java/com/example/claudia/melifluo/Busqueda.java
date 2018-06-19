package com.example.claudia.melifluo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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
    public ListView libros;
    public BusqAdpat adap;
    public OrigenDLB origen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        Intent recper = getIntent();
        clave = recper.getStringExtra("Clave");
        libros=(ListView)findViewById(R.id.ListaBId);

        adap = new BusqAdpat();
        origen = new OrigenDLB();
        adap.arreglo=origen.showAll();
        adap.context=this;
        libros.setAdapter(adap);
    }
}

