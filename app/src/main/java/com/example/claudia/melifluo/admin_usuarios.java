package com.example.claudia.melifluo;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class admin_usuarios extends AppCompatActivity {

    public Button us_limpiar, us_delete, us_update, us_create, us_read, calendar;
    public EditText descripcion, us_ap_m, us_ap_p, us_name, us_url, us_id;
    public TextView cumple;
    public CheckBox us_admin, us_basico;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_usuarios);

        us_delete = (Button)findViewById(R.id.us_eliminar);
        us_update = (Button)findViewById(R.id.us_modificar);
        us_create = (Button)findViewById(R.id.us_agregar);
        us_read = (Button)findViewById(R.id.us_consultar);
        calendar = (Button)findViewById(R.id.calendario);
        us_limpiar = (Button)findViewById(R.id.us_clean);

        descripcion = (EditText)findViewById(R.id.desc);
        us_ap_p = (EditText)findViewById(R.id.apellidoP_usuario);
        us_ap_m = (EditText)findViewById(R.id.apellidoM_usuario);
        us_name = (EditText)findViewById(R.id.nombre_usuario);
        us_url = (EditText)findViewById(R.id.url_imagen);
        us_id = (EditText)findViewById(R.id.id);

        cumple = (TextView) findViewById(R.id.nac);

        us_admin = (CheckBox)findViewById(R.id.administrador);
        us_basico = (CheckBox)findViewById(R.id.usuario);

    }
}


