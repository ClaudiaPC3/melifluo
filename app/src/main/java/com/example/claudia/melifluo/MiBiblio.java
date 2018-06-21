package com.example.claudia.melifluo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
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
        gridle = (GridView) findViewById(R.id.Gridy);
        adape = new MBAdapt(clave);
        orgn = new OrigenMB(this, clave);
        adape.arregl = orgn.showAll();
        adape.context = this;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gridle.setAdapter(adape);

        gridle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                libro_pojo x=(libro_pojo) parent.getItemAtPosition(position);
                Intent segunda = new Intent(MiBiblio.this, MiLibro.class);
                segunda.putExtra("id", x.LPclv);
                startActivity(segunda);
            }
        });

    }
}