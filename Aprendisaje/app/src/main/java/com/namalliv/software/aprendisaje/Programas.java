package com.namalliv.software.aprendisaje;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.namalliv.software.aprendisaje.basedatos.DataSourceProgramas;
import com.namalliv.software.aprendisaje.clases.ProgramasAdapter;
import com.namalliv.software.aprendisaje.model.Programa;

import java.util.ArrayList;
import java.util.List;

public class Programas extends AppCompatActivity {
    DataSourceProgramas dataSourcePrograma;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programas);
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
                "Duarte", "Empleado_1", "Empleado_2",  "Baseball_1", "Baseball_2", "MiArray", "Table",
                "Libro", "Matriz", "Biblioteca", "String1", "Variables", "Usuario","Apuesta","Interéscompuesto",
                "MyArray2", "Multiplicación", "Multiplicación2", "Abstracta", "Demomapa",
                "Maratón", "Arraylistobjetos", "Arraylistpalabras", "Arraylistenteros"
        };

        List<Programa> lista_programas = new ArrayList<>();
        for (int i = 0; i < myStringArray.length; i++){
            lista_programas.add(new Programa(i+1, myStringArray[i]));
        }

        dataSourcePrograma = new DataSourceProgramas(this);
        dataSourcePrograma.open();

        Long cantidadProgramas = dataSourcePrograma.programaCantidad();

        if(cantidadProgramas == 0){
            for (Programa item : lista_programas) {
                try {
                    dataSourcePrograma.crearPorgrama(item);
                } catch (SQLiteException e) {
                    e.printStackTrace();
                }
            }
        }

        List<Programa> programaDB = dataSourcePrograma.losProgramas();

        final ProgramasAdapter adapter = new ProgramasAdapter(this, lista_programas);
        final ListView listView = (ListView) findViewById(R.id.lista_programas);



        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                //String value = (String)adapter.getItemAtPosition(position);
                String selectedFromList = (listView.getItemAtPosition(position)).toString();

                Intent intent = new Intent(Programas.this, MostrarPrograma.class);

                intent.putExtra(EXTRA_MESSAGE, selectedFromList.toLowerCase());
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
