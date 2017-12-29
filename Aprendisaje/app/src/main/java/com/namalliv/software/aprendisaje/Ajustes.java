package com.namalliv.software.aprendisaje;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.Hashtable;
import java.util.Map;

public class Ajustes extends AppCompatActivity {
    private Toolbar mToolBar;
    private Button azul;
    private Button rojo;
    private Button verde;

    Hashtable<Integer, Integer> listas = new Hashtable<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolBar = toolbar;
        setSupportActionBar(toolbar);


        azul = (Button)findViewById(R.id.buttonAzul);
        rojo = (Button)findViewById(R.id.buttonRojo);
        verde = (Button) findViewById(R.id.buttonVerde);

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void guardarPreferencias(int color){
        SharedPreferences mPreferencias = getSharedPreferences("ToolbarColor", MODE_PRIVATE);
        SharedPreferences.Editor mEditar = mPreferencias.edit();
        mEditar.putInt("Color", color);
        mEditar.apply();
    }

    private void guardarTamañoLetra(int letra){
        SharedPreferences mPreferencias = getSharedPreferences("TamañLetra", MODE_PRIVATE);
        SharedPreferences.Editor mEditar = mPreferencias.edit();
        mEditar.putInt("Letra", letra);
        mEditar.apply();
    }

    private void guardarPreferencias2(int color2){
        SharedPreferences mPreferencias = getSharedPreferences("ToolbarColor2", MODE_PRIVATE);
        SharedPreferences.Editor mEditar = mPreferencias.edit();
        mEditar.putInt("Color2", color2);
        mEditar.apply();
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

    public Object colorOcuro(Integer colorClaro){
        listas.put(R.color.azulPrimary, R.color.azulPrimaryDark);
        listas.put(R.color.rojoPrimary, R.color.rojoPrimaryDark);
        listas.put(R.color.verdePrimary, R.color.verdePrimaryDark);
        //Set<Integer> keys = listas.entrySet();
        for (Map.Entry entry : listas.entrySet()) {
            if (colorClaro == entry.getKey()){
                return entry.getValue();

            }
        }
        return R.color.colorPrimaryDark;
    }

    public void cambialo(View view) {
        int id = view.getId();

        switch (id){
            case R.id.buttonAzul:
                int primary = ContextCompat.getColor(Ajustes.this, R.color.azulPrimary);
                int secundario = ContextCompat.getColor(Ajustes.this, R.color.azulPrimaryDark);
                mToolBar.setBackgroundColor(primary);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(secundario);
                }
                guardarPreferencias(primary);
                guardarPreferencias2(secundario);

                break;
            case R.id.buttonVerde:
                primary = ContextCompat.getColor(Ajustes.this, R.color.verdePrimary);
                secundario = ContextCompat.getColor(Ajustes.this, R.color.verdePrimaryDark);
                mToolBar.setBackgroundColor(primary);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(secundario);
                }
                guardarPreferencias(primary);
                guardarPreferencias2(secundario);
                break;
            case R.id.buttonRojo:
                primary = ContextCompat.getColor(Ajustes.this, R.color.rojoPrimary);
                secundario = ContextCompat.getColor(Ajustes.this, R.color.rojoPrimaryDark);
                mToolBar.setBackgroundColor(primary);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(secundario);
                }
                guardarPreferencias(primary);
                guardarPreferencias2(secundario);
                break;
            case R.id.cambios:
                primary = ContextCompat.getColor(Ajustes.this, R.color.colorPrimary);
                secundario = ContextCompat.getColor(Ajustes.this, R.color.colorPrimaryDark);
                mToolBar.setBackgroundColor(primary);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(secundario);
                }
                guardarPreferencias(primary);
                guardarPreferencias2(secundario);
                break;
        }
    }
}
