package com.namalliv.software.aprendisaje;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.namalliv.software.aprendisaje.clases.MotorJava;

public class Presentacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_presentacion);


        new Handler().postDelayed(new Thread(){
        @Override
        public void run() {
            Intent presentacion = new Intent(Presentacion.this, PrincipalActividad.class);
            startActivity(presentacion);
            Presentacion.this.finish();
        }
    }, MotorJava.HIMNO_THREAD_ESPERA);
}
}
