package com.namalliv.software.aprendisaje;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class PrincipalActividad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_actividad);

        int secundario1 = ContextCompat.getColor(this, R.color.colorPrimaryDark);

        TextView colorTexto = (TextView) findViewById(R.id.aprende);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (buscaOscuro() != secundario1){
            toolbar.setBackgroundColor(buscaOscuro());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(buscaClaro());
            }
        }

        colorTexto.setTextColor(buscaClaro());
        setSupportActionBar(toolbar);

    }

    public void openTutorial(View view) {
        int viewid = view.getId();
        switch (viewid){
            case R.id.imageView1:
                Intent intent6 = new Intent(PrincipalActividad.this, Tutoriales.class);
                startActivity(intent6);
                break;
            case R.id.imageView2:
                Intent intent7 = new Intent(PrincipalActividad.this, Programas.class);
                startActivity(intent7);
                break;
            case R.id.imageView3:
                Intent intent8 = new Intent(PrincipalActividad.this, PreguntasImportantes.class);
                startActivity(intent8);
                break;
            case R.id.imageView4:
                Intent intent9 = new Intent(PrincipalActividad.this, Pruebas.class);
                startActivity(intent9);
                break;
            case R.id.imageView5:
                Intent intent10 = new Intent(PrincipalActividad.this, Ajustes.class);
                startActivity(intent10);
                break;
            case R.id.imageView6:
                compartir("hoy");
                break;
        }

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


    private void compartir(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
    }

    private void compartir(String hoy){
        try {
            Intent enviarIntent = new Intent(Intent.ACTION_SEND);
            enviarIntent.setType("text/plain");
            enviarIntent.putExtra(Intent.EXTRA_SUBJECT, "Hymno");
            String sAux = "\nPermítanme recomendarles esta applicación\n\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=com.namalliv.software.aprendisaje \n\n";
            enviarIntent.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(enviarIntent, getResources().getText(R.string.send_to)));
        } catch(Exception e) {
            //e.toString();
        }
    }
}
