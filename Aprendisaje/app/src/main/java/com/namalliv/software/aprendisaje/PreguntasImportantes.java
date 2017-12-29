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

import com.namalliv.software.aprendisaje.basedatos.DataSourcePrueba;
import com.namalliv.software.aprendisaje.clases.PreguntaAdapter;
import com.namalliv.software.aprendisaje.model.Pregunta;

import java.util.ArrayList;
import java.util.List;

public class PreguntasImportantes extends AppCompatActivity {
    DataSourcePrueba mDataSourePregunta;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas_importantes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDataSourePregunta = new DataSourcePrueba(this);
        mDataSourePregunta.open();

        int secundario1 = ContextCompat.getColor(this, R.color.colorPrimaryDark);


        if (buscaOscuro() != secundario1){
            toolbar.setBackgroundColor(buscaOscuro());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(buscaClaro());
            }
        }

        String[] myStringArray = {
                "Acceso", "Arreglo", "Colecciones", "Clase", "Clase abstracta",
                "Comando", "IDE", "Interfaz", "Java", "JVM",
                "Main", "Map","Matriz","Excepción", "Overload método",
                "Override método", "Encapsulación", "Objeto", "Polimorfismo", "Private",
                "Public", "Palabras reservadas", "Protected", "Set", "Try y Catch",
                "Variable", "Atributo"};

        List<Pregunta> lista_preguntas = new ArrayList<>();
        for (int i = 0; i < myStringArray.length; i++){
            lista_preguntas.add(new Pregunta(i+1, myStringArray[i]));
        }

        Long cantidadPreguntas = mDataSourePregunta.preguntaCantidad();

        if(cantidadPreguntas == 0) {

            for (Pregunta pregunta : lista_preguntas) {
                try {
                    mDataSourePregunta.crearPregunta(pregunta);
                } catch (SQLiteException e) {
                    e.printStackTrace();
                }
            }
        }

        List<Pregunta> preguntasDB = mDataSourePregunta.lasPreguntas();

        final PreguntaAdapter adapter = new PreguntaAdapter(this, lista_preguntas);
        final ListView listView = (ListView) findViewById(R.id.lista_preguntas);

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
                //Toast.makeText(PreguntasImportantes.this, "Usted eligio " + selectedFromList, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PreguntasImportantes.this, MostrarPreguntas.class);
                intent.putExtra(EXTRA_MESSAGE, selectedFromList);
                startActivity(intent);
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDataSourePregunta.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDataSourePregunta.open();
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
