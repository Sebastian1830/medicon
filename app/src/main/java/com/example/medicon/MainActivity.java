package com.example.medicon;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class    MainActivity extends AppCompatActivity {

    TextView lblregistrar;
    Button btningresar;

    SQLite_OpenHelper helper = new SQLite_OpenHelper(this,"Medicon",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblregistrar = (TextView) findViewById(R.id.lblregistrar);
        btningresar = (Button) findViewById(R.id.btnlogin);

        lblregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Registrar.class);
                startActivity(i);
            }
        });

        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtusu = (EditText) findViewById(R.id.txtuser);
                EditText txtpass = (EditText) findViewById(R.id.txtpass);

                try{
                    Cursor cursor = helper.login(txtusu.getText().toString(), txtpass.getText().toString());
                    if(cursor.getCount()>0){
                        Intent i = new Intent(getApplicationContext(),Home.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(getApplicationContext(),"Usuario y/o Contraseña Incorrectos",Toast.LENGTH_LONG).show();
                    }
                    txtusu.setText("");
                    txtpass.setText("");
                    txtusu.findFocus();
                } catch (SQLException e){


                }



            }
        });

    }
}
