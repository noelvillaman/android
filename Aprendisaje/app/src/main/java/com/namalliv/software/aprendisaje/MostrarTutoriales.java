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

public class MostrarTutoriales extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_tutoriales);
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
        String message = intent.getStringExtra(Tutoriales.EXTRA_MESSAGE);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        openFile(message);
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

    private void openFile(String tutorial)
    {
        String tema = tutorial;
        TextView temas = (TextView) findViewById(R.id.tema_tutorial);
        temas.setText(tema);
        temas.setTextSize(25);

        temas.setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        try {
            InputStream is = getAssets().open("tutoriales/" + tutorial + ".txt");
            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String text = new String(buffer);


            TextView tv = (TextView) findViewById(R.id.texto_tutorial);
            tv.setText(text);
            tv.setTextSize(20);
            tv.setTextColor(Color.parseColor("#000000"));
        } catch (IOException e) {
            // Should never happen!
            throw new RuntimeException(e);
        }

    }

}
