package com.namalliv.software.aprendisaje.model;

import android.content.ContentValues;

import com.namalliv.software.aprendisaje.basedatos.BaseDatos;

/**
 * Created by NoelBonni on 4/8/2017.
 */

public class Programa {
    private int id;
    private String programa;

    public Programa() {
    }

    public Programa(int id, String programa) {
        this.id = id;
        this.programa = programa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public ContentValues aProgramas()
    {
        ContentValues preguntas = new ContentValues(2);
        preguntas.put(BaseDatos.PROGRAMA_ID, id);
        preguntas.put(BaseDatos.PROGRAMA, programa);

        return  preguntas;
    }

    @Override
    public String toString() {
        return programa;
    }
}
