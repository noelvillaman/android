package com.namalliv.software.aprendisaje;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MostrarPreguntas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_preguntas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int primario = ContextCompat.getColor(this, R.color.colorPrimary);
        int secundario1 = ContextCompat.getColor(this, R.color.colorPrimaryDark);

        toolbar.setBackgroundColor(primario);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(secundario1);
        }

        if (buscaOscuro() != secundario1){
            toolbar.setBackgroundColor(buscaOscuro());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(buscaClaro());
            }
        }

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(PreguntasImportantes.EXTRA_MESSAGE);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        openFile(message);

    }

    public String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
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

    private void openFile(String pregunta)
    {
        String tema = pregunta;
        tema = capitalizeFirstLetter(tema);
        TextView temas = (TextView) findViewById(R.id.tema_pregunta);
        temas.setText(tema);
        temas.setTextSize(25);

        temas.setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        try {
            InputStream is = getAssets().open("preguntas/" + pregunta +".txt");
            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String text = new String(buffer);


            TextView tv = (TextView) findViewById(R.id.texto_pregunta);
            tv.setText(text);
            tv.setTextSize(20);
            tv.setTextColor(Color.parseColor("#000000"));
        } catch (IOException e) {
            // Should never happen!
            throw new RuntimeException(e);
        }

    }

}
