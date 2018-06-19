package com.example.claudia.melifluo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BusqAdpat extends BaseAdapter{

    public ArrayList<BusqPojo> arreglo;
    public Context context;
    public LayoutInflater inflater;

    @Override
    public int getCount() {
        return arreglo.size();
    }

    @Override
    public Object getItem(int i) {
        return arreglo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return arreglo.get(i).BPclv;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        inflater=LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.busq_libr, null);
        TextView ttl = (TextView) v.findViewById(R.id.nombre_l);
        ttl.setText(arreglo.get(i).BPttl);
        ImageView img = (ImageView) v.findViewById(R.id.imagen_l);
        Picasso.get().load(arreglo.get(i).BPimg).into(img);
        return null;
    }
}
