package com.example.medicon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Hospitales> lista;

    public ListAdapter(Context context, ArrayList<Hospitales> lista){
        this.context = context;
        this.lista = lista;
    }
    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Hospitales Item = (Hospitales) getItem(i);

        view = LayoutInflater.from(context).inflate(R.layout.lista, null);
        TextView nombre = view.findViewById(R.id.title);
        TextView distancia = view.findViewById(R.id.subtitle);
        Button ir = view.findViewById(R.id.irpe);
        nombre.setText(Item.nombre);
        distancia.setText(Item.distacia);

        ir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,Hospital.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("LON",Item.lon);
                intent.putExtra("LAT",Item.lat);
                v.getContext().startActivity(intent);

                Toast.makeText(context, Item.lat+" "+Item.lon, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
