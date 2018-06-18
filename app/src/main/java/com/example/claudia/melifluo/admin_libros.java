package com.example.claudia.melifluo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class admin_libros extends AppCompatActivity {

    public Button select_autores, select_cat;
    public TextView es_autores, es_cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_libros);

        select_autores = (Button)findViewById(R.id.pre_select_autores);
        select_cat = (Button)findViewById(R.id.pre_select_categoria);
        es_autores = (TextView)findViewById(R.id.select_autores);
        es_cat = (TextView)findViewById(R.id.select_categoria);

    }
}
