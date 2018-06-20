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




public class OrigenDLB{

    Context mContext;

    OrigenDLB(Context context){
        mContext = context;
    }

    public ArrayList<BusqPojo>showAll(){
        final ArrayList<BusqPojo> resultado=new ArrayList<BusqPojo>();

        String url  = "http://192.168.0.25/Programas/M_Libro_Bus.php";
        JsonArrayRequest peticion = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                            BusqPojo xi;
                            int fin=5;
                        try {
                            for (int i=0; i<fin; i++) {
                                //[{"titulo":"primero", "url":"link1"}, {"titulo":"segundo", "url":"link2"}] necesita act esta linea
                                xi =  new BusqPojo();
                                xi.BPttl = ("" + response.getJSONObject(i).getString("titulo"));
                                fin = (response.getJSONObject(i).getInt("cant"));
                                xi.BPimg = ("" + response.getJSONObject(i).getString("url"));
                                xi.BPclv = ("" + response.getJSONObject(i).getString("clv"));
                                resultado.add(xi);
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }
                ,new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        RequestQueue x= Volley. newRequestQueue(mContext);
        x.add(peticion);
        return resultado;
    }
}
