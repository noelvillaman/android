package com.namalliv.software.aprendisaje;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.namalliv.software.aprendisaje.model.Contenido;
import com.viralypatel.sharedpreferenceshelper.lib.SharedPreferencesHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

import flepsik.github.com.progress_ring.ProgressRingView;

public class Pruebas extends AppCompatActivity {
    private Contenido contenido = new Contenido();

    private SharedPreferencesHelper sph;

    private Timer timer;
    private long timeToStop;

    ProgressRingView progressRingView;
    ProgressRingView secondProgress;

    private List<RadioButton> radioButtons = new ArrayList<>();
    private TextView pregunta;
    private TextView preguntaNo;
    private RadioGroup radioGroup;
    private ImageView fPrueba;
    private Button elegir;


    List<Integer> fotos;

    TextView congratulacion;
    TextView textoCirculo;


    private String mRespuesta;
    private int nota = 0;
    private int noPregunta = 0;


    String[] preguntas = repuestasHechas();


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sph = new SharedPreferencesHelper(this, "myappprefs"); // this will create a named shared preference file


        progressRingView = (ProgressRingView) findViewById(R.id.third);
        progressRingView.setVisibility(View.GONE);

        textoCirculo = (TextView) findViewById(R.id.felicidades);
        textoCirculo.setVisibility(View.GONE);

        secondProgress = (ProgressRingView) findViewById(R.id.second);

        congratulacion = (TextView) findViewById(R.id.congra1);
        congratulacion.setVisibility(View.GONE);

        elegir = (Button) findViewById(R.id.button);

        llenaFotos();

        pregunta = (TextView) findViewById(R.id.textView3);
        pregunta.setText(contenido.getPregunta(0));

        //Numero de pregunta
        preguntaNo = (TextView) findViewById(R.id.textView2);
        preguntaNo.setText("1" + "/" + Integer.toString(contenido.cantidadDePreguntas()));

        setRadioButtons();
    }

    public void startProgressRingView() {

        float progreso = porcentage();
        Log.i("MYNOTA", Float.toString(progreso * 100));
        float micolor = progreso * 100;
        Log.i("MYNOTA", Float.toString(micolor));
        preguntaNo.setVisibility(View.GONE);
        radioGroup.setVisibility(View.GONE);
        pregunta.setVisibility(View.GONE);
        fPrueba.setVisibility(View.GONE);

        progressRingView.setVisibility(View.VISIBLE);
        textoCirculo.setVisibility(View.VISIBLE);
        congratulacion.setVisibility(View.VISIBLE);
        if(micolor < 40){
            congratulacion.setText("¡Ése fue un buen intento!");
        }
        if(micolor >= 40 && micolor < 70){
            congratulacion.setText("¡Wow! ¡Qué bueno que estás progresando!");
        }
        if(micolor > 70){
            congratulacion.setText("¡Eso es lo que llama saber!");
        }

        textoCirculo.setText(formatS(progreso) + "%");

        progressRingView.cornerEdges(false);
        /*progressRingView.setProgress(.99f);*/
        progressRingView.setProgress(progreso);
        progressRingView.setAnimated(false);
        progressRingView.setRingWidth(20);
        progressRingView.setAnimationDuration(ProgressRingView.DEFAULT_ANIMATION_DURATION);
        if (micolor < 40) {

            progressRingView.setBackgroundColor(Color.parseColor("#F29F8E"));
            progressRingView.fillProgress(true);
            progressRingView.setProgressFillColor(Color.parseColor("#F76156"));
            progressRingView.setProgressColor(Color.RED);
            progressRingView.setBackgroundProgressColor(Color.LTGRAY);
        }
        if (micolor >= 40 && micolor < 70) {
            progressRingView.setBackgroundColor(Color.parseColor("#FFFF8D"));
            progressRingView.fillProgress(true);
            progressRingView.setProgressFillColor(Color.parseColor("#FFAB00"));
            progressRingView.setProgressColor(Color.YELLOW);
            progressRingView.setBackgroundProgressColor(Color.LTGRAY);

        }

        elegir.setText("Otra vez");

        elegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pruebas.this, Pruebas.class);
                startActivity(intent);
            }
        });

    }


    public float porcentage() {
        float notaper = ((float) nota / (float) contenido.cantidadDePreguntas());
        return notaper;
    }

    public String formatS(float notaAqui) {
        notaAqui = notaAqui * 100;

        return String.format("%.0f", notaAqui);

    }

    public void llenaFotos() {
        fotos = new ArrayList<>();

        fotos.add(R.drawable.prueba1);
        fotos.add(R.drawable.prueba2);
        fotos.add(R.drawable.prueba3);
        fotos.add(R.drawable.prueba4);
        fotos.add(R.drawable.prueba5);
        fotos.add(R.drawable.prueba6);
        fotos.add(R.drawable.prueba7);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prueba, menu);*/
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setRadioButtons() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        radioButtons.add((RadioButton) findViewById(R.id.radioButton));
        radioButtons.add((RadioButton) findViewById(R.id.radioButton4));
        radioButtons.add((RadioButton) findViewById(R.id.radioButton2));
        radioButtons.add((RadioButton) findViewById(R.id.radioButton3));

        Collections.shuffle(radioButtons);

        setRadioButtonsTexto();

        //contador();

        int secundario1 = ContextCompat.getColor(this, R.color.colorPrimaryDark);


        if (buscaOscuro() != secundario1){
            toolbar.setBackgroundColor(buscaOscuro());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(buscaClaro());
            }
        }

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

    public void setRadioButtonsTexto() {

        radioButtons.get(0).setText(preguntas[0]);
        radioButtons.get(0).setTextSize(20);

        radioButtons.get(1).setText(preguntas[1]);
        radioButtons.get(1).setTextSize(20);

        radioButtons.get(2).setText(preguntas[2]);
        radioButtons.get(2).setTextSize(20);

        radioButtons.get(3).setText(preguntas[3]);
        radioButtons.get(3).setTextSize(20);
    }

    public void setPicture(int pruebaNo) {

        fPrueba = (ImageView) findViewById(R.id.fotoPrueba);
        if (pruebaNo != 0 || pruebaNo != 5 || pruebaNo != 8 || pruebaNo != 9 || pruebaNo != 10 || pruebaNo != 11 || pruebaNo != 20) {
            fPrueba.setVisibility(View.GONE);
        }
        switch (pruebaNo) {
            case 5:
                fPrueba.setImageResource(R.drawable.prueba2);
                fPrueba.setVisibility(View.VISIBLE);
                break;
            case 8:
                fPrueba.setImageResource(R.drawable.prueba3);
                fPrueba.setVisibility(View.VISIBLE);
                break;
            case 9:
                fPrueba.setImageResource(R.drawable.prueba4);
                fPrueba.setVisibility(View.VISIBLE);
                break;
            case 10:
                fPrueba.setImageResource(R.drawable.prueba5);
                fPrueba.setVisibility(View.VISIBLE);
                break;
            case 11:
                fPrueba.setImageResource(R.drawable.prueba6);
                fPrueba.setVisibility(View.VISIBLE);
                break;
            case 20:
                fPrueba.setImageResource(R.drawable.prueba7);
                fPrueba.setVisibility(View.VISIBLE);
                break;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String[] repuestasHechas() {
        String[] todas = new String[4];

        todas[0] = contenido.getOpcione1(noPregunta);
        todas[1] = contenido.getOpcione2(noPregunta);
        todas[2] = contenido.getOpcione3(noPregunta);
        todas[3] = contenido.getOpcione4(noPregunta);


        return todas;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void selecciona(View view) {
        //Button elegir = (Button) findViewById(R.id.button);
        radioGroup = (RadioGroup) findViewById(R.id.myradioGroup);


        if (radioGroup.getCheckedRadioButtonId() != -1) {
            int id = radioGroup.getCheckedRadioButtonId();
            View radioButton = radioGroup.findViewById(id);
            int radioId = radioGroup.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) radioGroup.getChildAt(radioId);
            String selection = (String) btn.getText();


            if (selection == contenido.optenCorrecta(noPregunta)) {
                //Toast.makeText(this, "Que bien " + noPregunta, Toast.LENGTH_SHORT).show();
                noPregunta++;
                nota++;
                pregunta.setText(contenido.getPregunta(noPregunta));
                preguntaNo.setText(Integer.toString(noPregunta + 1) + "/" + Integer.toString(contenido.cantidadDePreguntas()));
                if (noPregunta < 22) {
                    setPicture(noPregunta);
                    preguntas = repuestasHechas();
                    pregunta.setText(contenido.getPregunta(noPregunta));
                    setRadioButtonsTexto();
                    radioGroup.clearCheck();

                } else {
                    startProgressRingView();
                }

            } else {
                //Toast.makeText(this, "No bueno para nada " + noPregunta, Toast.LENGTH_SHORT).show();
                noPregunta++;
                preguntaNo.setText(Integer.toString(noPregunta + 1) + "/" + Integer.toString(contenido.cantidadDePreguntas()));
                if (noPregunta < 22) {
                    setPicture(noPregunta);
                    preguntas = repuestasHechas();
                    pregunta.setText(contenido.getPregunta(noPregunta));
                    setRadioButtonsTexto();
                    radioGroup.clearCheck();
                } else {
                    startProgressRingView();
                }
            }
        } else {
            Toast.makeText(this, "Tienes que hacer una elección", Toast.LENGTH_SHORT).show();
        }

    }
}
