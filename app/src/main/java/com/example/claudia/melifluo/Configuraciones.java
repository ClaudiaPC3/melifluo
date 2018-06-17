package com.example.claudia.melifluo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Configuraciones extends AppCompatActivity {

    public Button mod_usuario, admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuraciones);

        mod_usuario = (Button)findViewById(R.id.mod_us);

        mod_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent per = new Intent(Configuraciones.this, Perfil_Modificar.class);
                startActivity(per);
            }
        });

        admin = (Button)findViewById(R.id.admin);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent admins = new Intent(Configuraciones.this, admin_menu.class);
                startActivity(admins);
            }
        });
    }
}
