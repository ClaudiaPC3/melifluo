package com.example.claudia.melifluo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
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

public class MiBiblioteca extends AppCompatActivity {
    public ImageView imgs1, imgs2, imgs3, imgs4, imgs5, imgs0;
    public String filas;
    public Integer fila=0, total=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_biblioteca);
        final ImageView[] imgs = new ImageView[6];
        imgs0=(ImageView)findViewById(R.id.book1);
        imgs1=(ImageView)findViewById(R.id.book2);
        imgs2=(ImageView)findViewById(R.id.book3);
        imgs3=(ImageView)findViewById(R.id.book4);
        imgs4=(ImageView)findViewById(R.id.book5);
        imgs5=(ImageView)findViewById(R.id.book6);

        Intent recper = getIntent();
        String clave = recper.getStringExtra("Clave");
        filas= recper.getStringExtra("Filas");
        String url = "http://192.168.0.25//programas/M_Libros_G.php?clave="+clave+"&num=0";

            JsonArrayRequest peticionfil = new JsonArrayRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                Picasso.get().load("" + response.getString(1)).into(imgs0);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }
            );
            url = "http://192.168.0.25//programas/M_Libros_G.php?clave="+clave+"&num=1";

        JsonArrayRequest peticionfil1 = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Picasso.get().load("" + response.getString(1)).into(imgs1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

         url = "http://192.168.0.25//programas/M_Libros_G.php?clave="+clave+"&num=2";
        JsonArrayRequest peticionfil2 = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Picasso.get().load("" + response.getString(1)).into(imgs2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

         url = "http://192.168.0.25//programas/M_Libros_G.php?clave="+clave+"&num=3";

        JsonArrayRequest peticionfil3 = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Picasso.get().load("" + response.getString(1)).into(imgs3);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

         url = "http://192.168.0.25//programas/M_Libros_G.php?clave="+clave+"&num=4";
        JsonArrayRequest peticionfil4 = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Picasso.get().load("" + response.getString(1)).into(imgs4);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        url = "http://192.168.0.25//programas/M_Libros_G.php?clave="+clave+"&num=5";
        JsonArrayRequest peticionfil5 = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Picasso.get().load("" + response.getString(1)).into(imgs5);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

            RequestQueue x = Volley.newRequestQueue(MiBiblioteca.this);
            x.add(peticionfil);
            x.add(peticionfil2);
            x.add(peticionfil3);
            x.add(peticionfil4);
            x.add(peticionfil1);
            x.add(peticionfil5);

        }
    }

