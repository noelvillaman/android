package com.namalliv.software.pruebasdejava;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.namalliv.software.pruebasdejava.contenido.Contenido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

import static com.namalliv.software.pruebasdejava.R.animator.animator;

public class PruebaActivity extends AppCompatActivity {
    private Contenido contenido = new Contenido();

    private Timer timer;
    private long timeToStop;

    private List<RadioButton> radioButtons = new ArrayList<>();
    private TextView pregunta;
    private TextView preguntaNo;
    private TextView calificacion;
    private RadioGroup radioGroup;
    private ImageView fPrueba;
    private Button elegir;
    private DonutProgress donutProgress;
    ObjectAnimator animador;

    private Handler handler;

    List<Integer> fotos;

    ProgressBar barraProgreso;
    TextView congratulacion;
    private Handler mHandler = new Handler();
    private int mProgressStatus=0;


    private String mRespuesta;
    private int nota = 0;
    private int noPregunta = 0;


    String[] preguntas = repuestasHechas();


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        donutProgress = (DonutProgress) findViewById(R.id.dunot_progress);
        donutProgress.setVisibility(View.GONE);



        congratulacion = (TextView) findViewById(R.id.congra1);
        congratulacion.setVisibility(View.GONE);

        elegir = (Button)findViewById(R.id.button);

        llenaFotos();

        pregunta = (TextView) findViewById(R.id.textView3);
        pregunta.setText(contenido.getPregunta(0));

        //Calificacion
        calificacion = (TextView) findViewById(R.id.textView2);
        calificacion.setText("0");

        //Numero de pregunta
        preguntaNo = (TextView) findViewById(R.id.textView);
        preguntaNo.setText("1" + "/" + Integer.toString(contenido.cantidadDePreguntas()));

        setRadioButtons();
    }

    public int porcentage(){
        //float notaper = (float)nota/(float)contenido.cantidadDePreguntas() / 100;
        float notaper = (float)nota/(float)contenido.cantidadDePreguntas() * 100;
        return (int) notaper;
    }

    public void llenaFotos(){
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prueba, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setRadioButtons() {


        radioButtons.add((RadioButton) findViewById(R.id.radioButton));
        radioButtons.add((RadioButton) findViewById(R.id.radioButton4));
        radioButtons.add((RadioButton) findViewById(R.id.radioButton2));
        radioButtons.add((RadioButton) findViewById(R.id.radioButton3));

        Collections.shuffle(radioButtons);

        setRadioButtonsTexto();

        //contador();


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
        if (pruebaNo != 0 || pruebaNo != 5 || pruebaNo != 8 || pruebaNo != 9 || pruebaNo != 10 || pruebaNo != 11 || pruebaNo != 20){
            fPrueba.setVisibility(View.GONE);
        }
        switch (pruebaNo){
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

    // Implementing Fisher–Yates shuffle
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void shuffleArray(RadioButton[] ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            RadioButton a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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

    /*@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void contador(){
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                calificacion.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                calificacion.setText("done!");
                cogeAhi();
            }
        }.start();
    }*/


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void cogeAhi(){
        noPregunta++;
        pregunta.setText(contenido.getPregunta(noPregunta));
        preguntaNo.setText(Integer.toString(noPregunta + 1) + "/" + Integer.toString(contenido.cantidadDePreguntas()));
        if (noPregunta < 22) {
            setPicture(noPregunta);
            preguntas = repuestasHechas();
            pregunta.setText(contenido.getPregunta(noPregunta));
            setRadioButtonsTexto();
            radioGroup.clearCheck();

            setRadioButtons();
        } else {
            congratulacion.setVisibility(View.VISIBLE);

        }
    }

    public void muestraBarra(){
        preguntaNo.setVisibility(View.GONE);
        calificacion.setVisibility(View.GONE);
        radioGroup.setVisibility(View.GONE);
        pregunta.setVisibility(View.GONE);
        fPrueba.setVisibility(View.GONE);
        //animador = findViewById(animator);

        congratulacion.setVisibility(View.VISIBLE);
//        barraProgreso.setVisibility(View.VISIBLE);
        donutProgress.setVisibility(View.VISIBLE);

        nota = porcentage();

        //Log.i("DEBUG", Integer.toString(nota));

       /* new Thread(new Runnable() {
            public void run() {
                final int presentage=0;
                while (mProgressStatus < nota) {
                    mProgressStatus += 1;
                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
//                            barraProgreso.setProgress(mProgressStatus);
                            congratulacion.setText(""+mProgressStatus+"%" + "\n" + "Qué bien. Sigue ahí");

                        }
                    });
                    try {
                        Thread.sleep(50);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();*/

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        boolean a = false;
                        if (a) {
                            ObjectAnimator anim = ObjectAnimator.ofInt(donutProgress, "progress", 0, 10);
                            anim.setInterpolator(new DecelerateInterpolator());
                            //anim.setIntValues();
                            anim.setDuration(5000);
                            anim.start();

                        } else {
                            AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(PruebaActivity.this, animator);
                            set.setInterpolator(new DecelerateInterpolator());
                            set.setTarget(donutProgress);
                            set.start();
                        }
                    }
                });
            }
        }, 0, 2000);
        congratulacion.setText(""+nota+"%" + "\n" + "Qué bien. Sigue ahí");
        cancelacion();

        //handler.postDelayed(timer, 1000);
    }



    public void  cancelacion(){
        if(timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;

        }

        elegir = (Button) findViewById(R.id.button);
        elegir.setText("Otra Vez");

        elegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PruebaActivity.this, PruebaActivity.class);
                startActivity(intent);
            }
        });
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
                calificacion.setText(Integer.toString(nota + 1));
                Toast.makeText(this, "Que bien " + noPregunta , Toast.LENGTH_SHORT).show();
                noPregunta++;
                nota++;
                pregunta.setText(contenido.getPregunta(noPregunta));
                preguntaNo.setText(Integer.toString(noPregunta + 1) + "/" + Integer.toString(contenido.cantidadDePreguntas()));
                if (noPregunta < 2) {
                    setPicture(noPregunta);
                    preguntas = repuestasHechas();
                    pregunta.setText(contenido.getPregunta(noPregunta));
                    setRadioButtonsTexto();
                    radioGroup.clearCheck();

                    //setRadioButtons();
                } else {
                    muestraBarra();
                }
                //cogeAhi();
               // contador();
            } else {
                Toast.makeText(this, "No bueno para nada " + noPregunta , Toast.LENGTH_SHORT).show();
                noPregunta++;
                preguntaNo.setText(Integer.toString(noPregunta + 1) + "/" + Integer.toString(contenido.cantidadDePreguntas()));
                if (noPregunta < 2) {
                    setPicture(noPregunta);
                    preguntas = repuestasHechas();
                    pregunta.setText(contenido.getPregunta(noPregunta));
                    setRadioButtonsTexto();
                    radioGroup.clearCheck();
                } else {
                    muestraBarra();
                }
                //cogeAhi();
                //contador();

            }
        } else {
            Toast.makeText(this, "Tienes que hacer una elección", Toast.LENGTH_SHORT).show();
        }

    }
}
