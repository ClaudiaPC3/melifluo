package com.example.claudia.melifluo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

        adap = new BusqAdpat(clave);
        origen = new OrigenDLB(this);
        adap.arreglo=origen.showAll();
        adap.context=this;
        libros.setAdapter(adap);
    }
}

