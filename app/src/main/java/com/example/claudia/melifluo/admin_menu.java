package com.example.claudia.melifluo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin_menu extends AppCompatActivity {

    public Button a_autores, a_libros, a_categorias, a_encuadernado, a_pais, a_editorial, a_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_menu);

        a_usuario = (Button)findViewById(R.id.admin_usuarios);
        a_autores = (Button)findViewById(R.id.admin_autores);
        //a_libros = (Button)findViewById(R.id.admin_libro);
        a_categorias = (Button)findViewById(R.id.admin_categoria);
        a_editorial = (Button)findViewById(R.id.admin_editoriales);
        a_encuadernado = (Button)findViewById(R.id.admin_encuadernados);
        a_pais = (Button)findViewById(R.id.admin_pais);

        a_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent us = new Intent(admin_menu.this, admin_usuarios.class);
                startActivity(us);
            }
        });

        a_autores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent autor = new Intent(admin_menu.this, admin_autores.class);
                startActivity(autor);
            }
        });

       /* a_libros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lib1 = new Intent(admin_menu.this, admin_libros.class);
                startActivity(lib1);
            }
        });*/

        a_categorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cat1 = new Intent(admin_menu.this, admin_categorias.class);
                startActivity(cat1);
            }
        });

        a_encuadernado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent enc1 = new Intent(admin_menu.this, admin_encuadernado.class);
                startActivity(enc1);
            }
        });

        a_editorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ed1 = new Intent(admin_menu.this, admin_editorial.class);
                startActivity(ed1);
            }
        });

        a_pais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pais1 = new Intent(admin_menu.this, admin_pais.class);
                startActivity(pais1);
            }
        });


    }
}

