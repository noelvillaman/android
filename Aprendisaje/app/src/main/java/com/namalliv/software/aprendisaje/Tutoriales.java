package com.namalliv.software.aprendisaje;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.namalliv.software.aprendisaje.clases.TutorialesAdapter;
import com.namalliv.software.aprendisaje.model.Tutorial;

import java.util.ArrayList;
import java.util.List;

public class Tutoriales extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutoriales);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int secundario1 = ContextCompat.getColor(this, R.color.colorPrimaryDark);


        if (buscaOscuro() != secundario1){
            toolbar.setBackgroundColor(buscaOscuro());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(buscaClaro());
            }
        }


        String[] myStringArray = {
                "Introducción", "Programa", "Expresión if", "Clases", "Interfaz",
                "Array", "ArrayList", "Map", "Try y Catch", "Overload método",
                "Override método", "Acceso", "Clase abstracta", "Main", "variables",
                "Matriz", "Palabras reservadas", "Métodos", "Método toString", "Circuito for",
                "Circuito while", "Circuito do-while", "Circuito foreach", "Condición switch"};

        List<Tutorial> lista_programas = new ArrayList<>();
        for (int i = 0; i < myStringArray.length; i++){
            lista_programas.add(new Tutorial(i+1, myStringArray[i]));
        }

        final TutorialesAdapter adapter = new TutorialesAdapter(this, lista_programas);
        final ListView listView = (ListView) findViewById(R.id.lista_tutoriales);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                String selectedFromList = (listView.getItemAtPosition(position)).toString();
                // assuming string and if you want to get the value on click of list item
                // do what you intend to do on click of listview row
                //Toast.makeText(Tutoriales.this, "Usted eligio " + selectedFromList, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Tutoriales.this, MostrarTutoriales.class);
                intent.putExtra(EXTRA_MESSAGE, selectedFromList);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private int buscaOscuro(){
        SharedPreferences mPreferencias = getSharedPreferences("ToolbarColor", MODE_PRIVATE);
        int color = mPreferencias.getInt("Color", ContextCompat.getColor(this, R.color.colorPrimaryDark));
        return color;
    }

    private int buscaClaro(){
        SharedPreferences mPreferencias = getSharedPreferences("ToolbarColor2", MODE_PRIVATE);
        int color = mPreferencias.getInt("Color2", ContextCompat.getColor(this, R.color.colorPrimary));
        return color;
    }

}
