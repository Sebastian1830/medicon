package com.example.medicon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ListaActivity extends AppCompatActivity {

    private ListView hosp;
    private ListAdapter adapter;
    ArrayList<Hospitales> lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        hosp = findViewById(R.id.hospitales);
        getNearHospital();


    }

    private void obtenerHospitales(ArrayList<Hospitales> x){

        adapter = new ListAdapter(this,x);
        hosp.setAdapter(adapter);
    }

    private void getNearHospital(){
        lista = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(this);
        //String url ="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+lat+","+lon+"&radius=3000&type=hospital&opennow&key=AIzaSyC0miOfNBZL4Fr-IUEiQNV3YTozgTkv8Pc";
        String url ="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-8.126968,-79.031450&radius=3000&type=hospital&opennow&key=AIzaSyC0miOfNBZL4Fr-IUEiQNV3YTozgTkv8Pc";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONArray resultados = null;
                        try {
                            JSONObject object = new JSONObject(response);
                            resultados = object.getJSONArray("results");

                            ArrayList<Hospitales> intento = new ArrayList<>();
                            if(resultados.length()>0){
                                for(int n = 0; n < resultados.length();n++){
                                    JSONObject obj = resultados.getJSONObject(n);
                                    String nombre = obj.optString("name");
                                    String dir = obj.optString("vicinity");
                                    String lon  = obj.getJSONObject("geometry").getJSONObject("location").getString("lng");
                                    String lat  = obj.getJSONObject("geometry").getJSONObject("location").getString("lat");

                                    Hospitales h = new Hospitales(nombre, dir,lon,lat);
                                    intento.add(h);
                                }
                                obtenerHospitales(intento);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(ListaActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }

}
