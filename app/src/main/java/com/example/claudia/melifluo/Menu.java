package com.example.claudia.melifluo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity implements View.OnClickListener{
    public Button perfil, biblioteca, busqueda, configuraciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        perfil = (Button)findViewById(R.id.perfil);
        biblioteca = (Button)findViewById(R.id.biblioteca);
        busqueda = (Button)findViewById(R.id.busqueda);
        configuraciones = (Button)findViewById(R.id.configuracion);
        Intent rec = getIntent();
        final String cuenta = rec.getStringExtra("cuenta");


        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent per = new Intent(Menu.this, Perfil2.class);
                per.putExtra("Clave", cuenta);
                startActivity(per);
            }
        });

        biblioteca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bib = new Intent(Menu.this, MiBiblioteca.class);
                startActivity(bib);
            }
        });

        busqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bus = new Intent(Menu.this, Busqueda.class);
                startActivity(bus);
            }
        });

        configuraciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent conf = new Intent(Menu.this, Configuraciones.class);
                startActivity(conf);
            }
        });



    }


    @Override
    public void onClick(View v) {

    }
}
