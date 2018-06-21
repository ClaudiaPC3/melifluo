package com.example.claudia.melifluo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MBAdapt extends BaseAdapter {

    public ArrayList<libro_pojo> arregl;
    public Context context;
    public LayoutInflater inflater;
    public String clave;

    MBAdapt(String clv){
        clave = clv;
    }

    @Override
    public int getCount() {
        return arregl.size();
    }

    @Override
    public Object getItem(int i) {
        return arregl.get(i);
    }

    @Override
    public long getItemId(int i) {

        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.libro_bib, null);
        ImageView port = (ImageView) v.findViewById(R.id.book);
        Picasso.get().load(arregl.get(i).LPimg).into(port);
        TextView id = (TextView) v.findViewById(R.id.bookid);
        id.setText(arregl.get(i).LPclv);
        return v;
    }
}
