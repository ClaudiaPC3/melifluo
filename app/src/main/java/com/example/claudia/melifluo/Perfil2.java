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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Perfil2 extends AppCompatActivity {
    public TextView Nombre, Apellido_P, Apellido_M, Edad, Descr, FechaU;
    public ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil2);
        Nombre = (TextView)findViewById(R.id.nombre_usuario);
        Apellido_P = (TextView)findViewById(R.id.apellidoP_usuario);
        Apellido_M = (TextView)findViewById(R.id.apellidoM_usuario);
        Edad = (TextView)findViewById(R.id.edad);
        Descr = (TextView)findViewById(R.id.desc);
        FechaU = (TextView)findViewById(R.id.fecha_union);
        img = (ImageView)findViewById(R.id.pefimg);
        Intent recper = getIntent();
        String clave = recper.getStringExtra("Clave");
        String url  = "http://192.168.0.25/Programas/M_Usuario.php?Valor=" + clave;
        JsonArrayRequest peticion = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            Nombre.setText(""+response.getString(1));
                            Apellido_P.setText(""+response.getString(2));
                            Apellido_M.setText(""+response.getString(3));
                            FechaU.setText(""+response.getString(7));
                            Edad.setText(""+response.getString(4));
                            Descr.setText(""+response.getString(5));
                            Picasso.get().load(""+response.getString(6)).into(img);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }

                ,new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Perfil2.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
        );
        RequestQueue x= Volley. newRequestQueue(Perfil2.this);
        x.add(peticion);


    }
}
