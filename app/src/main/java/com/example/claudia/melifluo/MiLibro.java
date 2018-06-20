package com.example.claudia.melifluo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MiLibro extends AppCompatActivity {

    public String clave;
    public TextView titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_libro);
        Intent recper = getIntent();
        clave = recper.getStringExtra("id");
        titulo = (TextView)findViewById(R.id.titulo);
        titulo.setText(clave);
    }
}
