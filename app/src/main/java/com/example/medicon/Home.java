package com.example.medicon;

import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mikhaellopez.circularimageview.CircularImageView;


public class Home extends AppCompatActivity {

    CircularImageView btnConsultar;
    CircularImageView btnHospital;
    CircularImageView btnFarmacia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnConsultar =  findViewById(R.id.btnConsultar);
        btnHospital =  findViewById(R.id.btnHospital);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Consultar.class);
                startActivity(i);
            }
        });

        btnHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent h = new Intent(getApplicationContext(),ListaActivity.class);
                startActivity(h);
            }
        });
    }
}
