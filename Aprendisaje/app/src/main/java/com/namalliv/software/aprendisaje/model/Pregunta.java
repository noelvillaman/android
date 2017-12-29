package com.namalliv.software.aprendisaje.model;

import android.content.ContentValues;

import com.namalliv.software.aprendisaje.basedatos.BaseDatos;

/**
 * Created by NoelBonni on 4/8/2017.
 */

public class Pregunta {
    private int id;
    private String pregunta;

    public Pregunta(){}

    public Pregunta(int id, String pregunta){
        this.id = id;
        this.pregunta = pregunta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public ContentValues aPreguntas()
    {
        ContentValues preguntas = new ContentValues(2);
        preguntas.put(BaseDatos.PREGUNTA_ID, id);
        preguntas.put(BaseDatos.PREGUNTA, pregunta);

        return  preguntas;
    }

    @Override
    public String toString() {
        return pregunta;
    }
}
