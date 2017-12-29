package com.namalliv.software.aprendisaje;

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

public class MostrarPruebas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_pruebas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int secundario1 = ContextCompat.getColor(this, R.color.colorPrimaryDark);


        if (buscaOscuro() != secundario1){
            toolbar.setBackgroundColor(buscaOscuro());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(buscaClaro());
            }
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        openFile();

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

    private void openFile()
    {
        String tema = "Prueba Uno";
        TextView temas = (TextView) findViewById(R.id.tema_prueba);
        temas.setText(tema);
        temas.setTextSize(25);

        temas.setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        try {
            InputStream is = getAssets().open("pruebas/prueba1.txt");
            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String text = new String(buffer);


            TextView tv = (TextView) findViewById(R.id.texto_prueba);
            tv.setText(text);
            tv.setTextSize(20);
            tv.setTextColor(Color.parseColor("#000000"));
        } catch (IOException e) {
            // Should never happen!
            throw new RuntimeException(e);
        }

    }

}
