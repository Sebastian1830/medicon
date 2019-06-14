package com.example.medicon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class Registrar extends AppCompatActivity {

    Button btnRegisUsu;
    EditText txtfullname, txtemail, txtcontraseña, txtconfpass;

    SQLite_OpenHelper helper = new SQLite_OpenHelper(this,"Medicon",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        btnRegisUsu = (Button) findViewById(R.id.btnregistrar);
        txtfullname = (EditText) findViewById(R.id.txtfullname);
        txtemail = (EditText) findViewById(R.id.txtemail);
        txtcontraseña = (EditText) findViewById(R.id.txtcontraseña);
        txtconfpass = (EditText) findViewById(R.id.txtconfpass);

        btnRegisUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.AbrirDB();
                helper.insertRegUsu(
                        String.valueOf(txtfullname.getText()),
                        String.valueOf(txtemail.getText()),
                        String.valueOf(txtcontraseña.getText())
                );
                helper.cerrarDB();

                Toast.makeText(getApplicationContext(),"Registro Almacenado con Exito", Toast.LENGTH_LONG).show();

                Intent i =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

    }
}
