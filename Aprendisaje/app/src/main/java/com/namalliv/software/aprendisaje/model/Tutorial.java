package com.namalliv.software.aprendisaje.model;

import android.content.ContentValues;

import com.namalliv.software.aprendisaje.basedatos.BaseDatos;

/**
 * Created by NoelBonni on 4/8/2017.
 */

public class Tutorial {
    private int id;
    private String tutorial;

    public Tutorial(){}

    public Tutorial(int id, String tutorial){
        this.id = id;
        this.tutorial = tutorial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTutorial() {
        return tutorial;
    }

    public void setTutorial(String tutorial) {
        this.tutorial = tutorial;
    }

    public ContentValues aTutoriales()
    {
        ContentValues preguntas = new ContentValues(2);
        preguntas.put(BaseDatos.TURORIAL_ID, id);
        preguntas.put(BaseDatos.TUTORIAL, tutorial);

        return  preguntas;
    }

    @Override
    public String toString() {
        return tutorial;
    }
}
