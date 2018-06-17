package com.example.claudia.melifluo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
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

public class Busqueda extends AppCompatActivity {
    public TextView T1, T2, T3;
    public String Img1, Img2, Img3;
    public Button B1, B2, B3;
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
                            Img1=(""+response.getString(1));
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
                            Img2=(""+response.getString(1));
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
                            Img3=(""+response.getString(1));
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
    }
}
