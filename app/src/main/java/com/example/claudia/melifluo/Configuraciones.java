package com.example.claudia.melifluo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Configuraciones extends AppCompatActivity {

    public Button mod_usuario, admin, credi;
    public String clave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuraciones);
        Intent recper = getIntent();
        clave = recper.getStringExtra("Clave");
        mod_usuario = (Button)findViewById(R.id.mod_us);
        credi = (Button)findViewById(R.id.btn3);
        mod_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent per = new Intent(Configuraciones.this, Perfil_Modificar.class);
                per.putExtra("clave", clave);
                startActivity(per);
            }
        });

        credi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast1 = Toast.makeText(getApplicationContext(), "15300181_Andres y 15300214_Claudia", Toast.LENGTH_LONG);

                toast1.show();
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

     public void enable_admin(){
        if(clave == ""+0){
            admin.setEnabled(true);
        }else{
            admin.setEnabled(false);
        }
     }


}
