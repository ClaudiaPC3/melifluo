package com.example.claudia.melifluo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
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

public class MiLibro extends AppCompatActivity {

    public String clave;
    public TextView titulo, autore, fpub, edicion, editoria, categ, encuad;
    public ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_libro);
        Intent recper = getIntent();
        clave = recper.getStringExtra("id");
        titulo = (TextView)findViewById(R.id.titulo);
        autore = (TextView)findViewById(R.id.autores);
        fpub = (TextView)findViewById(R.id.publicacion);
        edicion = (TextView)findViewById(R.id.numero_edicion);
        categ = (TextView)findViewById(R.id.categoria);
        editoria = (TextView)findViewById(R.id.editorial);
        encuad = (TextView)findViewById(R.id.encuadernado);
        imagen = (ImageView)findViewById(R.id.imagen);
        String url  = "http://192.168.0.25/Programas/R_Libro_G.php?id=" + clave;
        JsonArrayRequest peticion = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            titulo.setText(""+response.getString(0));
                            //autore.setText(""+response.getString(3));
                            Picasso.get().load(""+response.getString(1)).into(imagen);
                            fpub.setText(""+response.getString(2));
                            edicion.setText(""+response.getString(3));
                            editoria.setText(""+response.getString(4));
                            encuad.setText(""+response.getString(5));
                            //categ.setText(""+response.getString(6));
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }

                ,new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MiLibro.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
        );
        RequestQueue x= Volley. newRequestQueue(MiLibro.this);
        x.add(peticion);
    }
}
