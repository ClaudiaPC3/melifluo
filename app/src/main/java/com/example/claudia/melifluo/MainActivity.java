package com.example.claudia.melifluo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Button perfil, biblioteca, busqueda, configuraciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        perfil = (Button)findViewById(R.id.perfil);
        biblioteca = (Button)findViewById(R.id.biblioteca);
        busqueda = (Button)findViewById(R.id.busqueda);
        configuraciones = (Button)findViewById(R.id.configuracion);

        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent per = new Intent(MainActivity.this, Perfil2.class);
                startActivity(per);
            }
        });

        biblioteca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bib = new Intent(MainActivity.this, MiBiblioteca.class);
                startActivity(bib);
        }
        });

        busqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bus = new Intent(MainActivity.this, Busqueda.class);
                startActivity(bus);
            }
        });

        configuraciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent conf = new Intent(MainActivity.this, Configuraciones.class);
                startActivity(conf);
            }
        });



    }


    @Override
    public void onClick(View v) {

    }
}
