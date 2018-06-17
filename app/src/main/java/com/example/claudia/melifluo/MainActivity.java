package com.example.claudia.melifluo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText usuario;
    public Button ingresar, nu_usuario;
    public String cuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = (EditText)findViewById(R.id.Cuenta);
        ingresar = (Button)findViewById(R.id.Ingresar);
        nu_usuario = (Button)findViewById(R.id.NUsuario);


        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cuenta=usuario.getText().toString();
                Intent dato = new Intent(MainActivity.this, Menu.class);
                dato.putExtra("cuenta", cuenta);
                startActivity(dato);
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
