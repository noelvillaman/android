package com.namalliv.software.aprendisaje.clases;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.namalliv.software.aprendisaje.R;
import com.namalliv.software.aprendisaje.model.Pregunta;

import java.util.List;

/**
 * Created by NoelBonni on 4/9/2017.
 */

public class PreguntaAdapter extends ArrayAdapter {
    List<Pregunta> mProgramaLista;
    LayoutInflater mInflator;
    private Typeface tf;
    private Typeface tf2;

    private int countview = 0;

    Drawable[] colors;

    public PreguntaAdapter(Context context, List<Pregunta> objects) {
        super(context, R.layout.numero_actividad, objects);
        colors = new Drawable[]{context.getResources().getDrawable(R.drawable.circle),context.getResources().getDrawable(R.drawable.circle2),
                context.getResources().getDrawable(R.drawable.circle3),context.getResources().getDrawable(R.drawable.circle4),
                context.getResources().getDrawable(R.drawable.circle5),};
        mProgramaLista = objects;
        mInflator = LayoutInflater.from(context);
        this.tf = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf"); //initialize typeface here.
        this.tf2 = Typeface.createFromAsset(context.getAssets(), "fonts/RobotoCondensed-Bold.ttf"); //initialize typeface here.
    }

    @NonNull
    @Override
    public View getView(int posistion, View converView, ViewGroup parent){
        if(converView == null){
            converView = mInflator.inflate(R.layout.numero_actividad, parent, false);
        }
        TextView numero = (TextView)converView.findViewById(R.id.numero);
        TextView actividad = (TextView)converView.findViewById(R.id.actividad);
        numero.setTypeface(tf);
        actividad.setTypeface(tf2);
        //numero.setTextColor(Color.parseColor("#212121"));
        actividad.setTextColor(Color.parseColor("#393a3f"));


        Pregunta pregunta = mProgramaLista.get(posistion);
        char inicial = pregunta.getPregunta().charAt(0);
        inicial = Character.toUpperCase(inicial);

        //numero.setText(Integer.toString(pregunta.getId()));
        numero.setText(inicial+"");
        actividad.setText(pregunta.getPregunta());

        numero.setBackground(colors[countview]);

        countview++;
        if (countview == 5){
            countview = 0;
        }

        return converView;
    }
}
