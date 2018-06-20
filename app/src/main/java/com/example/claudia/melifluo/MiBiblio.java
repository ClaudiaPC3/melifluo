package com.example.claudia.melifluo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.GridView;
import android.widget.ListView;

//String url = "http://192.168.0.25//programas/M_Libros_G.php?clave="+clave+"&num=0";
public class MiBiblio extends AppCompatActivity {
    public GridView gridle;
    public MBAdapt adape;
    public OrigenMB orgn;
    public String clave;
    public int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_biblio);
        Intent recper = getIntent();
        clave = recper.getStringExtra("Clave");
        gridle=(GridView) findViewById(R.id.Gridy);
        adape = new MBAdapt(clave);
        orgn = new OrigenMB(this, clave);
        adape.arregl=orgn.showAll();
        adape.context=this;
        gridle.setAdapter(adape);

    }
}