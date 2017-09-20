package com.miramicodigo.studyjam_customlistview;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    /**
     *
     * @author Gustavo Lizarraga
     * @date 20/09/2017
     *
     * */

    private ListView lvDatos;
    private Activity activity;
    private CustomAdapter adaptador;
    private ArrayList<Pokemon> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvDatos = (ListView) findViewById(R.id.lvLista);
        activity = this;
        datos = new ArrayList<Pokemon>();
        llenarArrayList();
        adaptador = new CustomAdapter(activity, datos);
        lvDatos.setAdapter(adaptador);
        lvDatos.setOnItemClickListener(this);
    }

    public void llenarArrayList() {
        String[] titulo = getResources().getStringArray(R.array.nombre);
        String[] subtitulo = getResources().getStringArray(R.array.tipos);
        TypedArray imagenes = getResources().obtainTypedArray(R.array.image);
        for(int i=0; i < titulo.length; i++) {
            Pokemon temp = new Pokemon(
                    titulo[i], subtitulo[i], imagenes.getResourceId(i, -1)
            );
            datos.add(temp);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Pokemon temp = datos.get(position);
        Intent intent = new Intent(
                getApplicationContext(),
                DetalleActivity.class
        );
        intent.putExtra("poke", temp);
        startActivity(intent);
    }
}

