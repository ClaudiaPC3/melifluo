package com.example.claudia.melifluo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Button ingresar, nu_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ingresar = (Button)findViewById(R.id.Ingresar);
        nu_usuario = (Button)findViewById(R.id.NUsuario);


        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bus = new Intent(MainActivity.this, Menu.class);
                startActivity(bus);
            }
        });

        nu_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent conf = new Intent(MainActivity.this, Perfil_Crear.class);
                startActivity(conf);
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}
