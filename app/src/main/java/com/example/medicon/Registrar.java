package com.example.medicon;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import OpenHelper.SQLite_OpenHelper;

public class Registrar extends AppCompatActivity {

    private Button btnRegisUsu;
    private EditText txtfullname, txtemail, txtcontraseña, txtconfpass;
    private FirebaseAuth mAuth;

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
        mAuth = FirebaseAuth.getInstance();

        btnRegisUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = txtfullname.getText().toString();
                final String email = txtemail.getText().toString();
                final String password = txtcontraseña.getText().toString();
                final String passwordconf = txtconfpass.getText().toString();

                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || passwordconf.isEmpty()){
                    showMessage("Porfavor verifica los datos ingresados");
                }else{
                    CreateUserAccount(username,email,password);
                }

            }
        });


        /*btnRegisUsu.setOnClickListener(new View.OnClickListener() {
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
        });*/

    }

    private void CreateUserAccount(final String username, String email, String password) {

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            showMessage("Usuario Registrado");
                            updateUI();
                            //updateUserInfo(username,mAuth.getCurrentUser());
                        }else {
                            showMessage("Error al Registrar el Usuario" + task.getException().getMessage());
                        }
                    }
                });

    }

    private void updateUI() {
        Intent i =new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }

    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();

    }
}
