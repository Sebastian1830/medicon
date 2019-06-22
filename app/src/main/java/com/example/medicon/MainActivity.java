package com.example.medicon;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import OpenHelper.SQLite_OpenHelper;

public class MainActivity extends AppCompatActivity {

    private EditText txtusu, txtpass;
    private TextView lblregistrar;
    private Button btningresar;
    private FirebaseAuth mAuth;

    SQLite_OpenHelper helper = new SQLite_OpenHelper(this,"Medicon",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtusu = (EditText) findViewById(R.id.txtuser);
        txtpass = (EditText) findViewById(R.id.txtpass);
        lblregistrar = (TextView) findViewById(R.id.lblregistrar);
        btningresar = (Button) findViewById(R.id.btnlogin);
        mAuth = FirebaseAuth.getInstance();

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

                final String email = txtusu.getText().toString();
                final String password = txtpass.getText().toString();

                if( email.isEmpty() || password.isEmpty()){
                    showMessage("Porfavor verifica el Usuario y/o Contraseña");
                }else{
                    singIn(email,password);
                }

            }
        });

        /*btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtusu = (EditText) findViewById(R.id.txtuser);
                txtpass = (EditText) findViewById(R.id.txtpass);

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
        });*/

    }

    private void singIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    updateUi();
                }else {
                    showMessage("Error Intente mas tarde" + task.getException().getMessage());
                }
            }
        });
    }

    private void updateUi() {
        Intent i = new Intent(getApplicationContext(),Home.class);
        startActivity(i);
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }
}
