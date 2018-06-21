package com.example.claudia.melifluo;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class OrigenMB {

    Context context;
    String clave;
    OrigenMB(Context cntx, String clv){
        clave = clv;
        context = cntx;
    }

    public ArrayList<libro_pojo>showAll(){
        final ArrayList<libro_pojo> resultad=new ArrayList<libro_pojo>();

        String url = "http://192.168.0.25/Programas/M_Libros_G.php?clv="+clave;
        JsonArrayRequest peticion = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        libro_pojo pj;
                        int fin = 5;

                        try {
                            for (int i = 0; i < fin; i++) {
                                //[{"img":"link1", "cant":x, "clave":"id1"}, {"img":"link2", "cant":x, "clave":"id2"}]
                                pj = new libro_pojo();
                                pj.LPimg = ("" + response.getJSONObject(i).getString("img"));
                                pj.LPclv = ("" + response.getJSONObject(i).getString("clave"));
                                fin = (response.getJSONObject(i).getInt("cant"));
                                resultad.add(pj);
                            }
                        } catch (JSONException e){
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
        RequestQueue x= Volley. newRequestQueue(context);
        x.add(peticion);
        return resultad;
    }
}
