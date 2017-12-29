package com.namalliv.software.aprendisaje.model;

import android.content.ContentValues;

import com.namalliv.software.aprendisaje.basedatos.BaseDatos;

import java.util.UUID;

/**
 * Created by NoelBonni on 4/11/2017.
 */

public class Prueba {
    private String id;
    private String pregunta;
    private String eleccion1;
    private String eleccion2;
    private String eleccion3;
    private String eleccion4;
    private String correcta;
    private int calificacion;

    private String prueba;

    public Prueba(){}

    public Prueba(String id, String pregunta, String eleccion1,
                  String eleccion2, String eleccion3,
                  String eleccion4, String correcta,
                  int calificacion, String prueba) {
        if (id == null){
            id = UUID.randomUUID().toString();
        }
        this.pregunta = pregunta;
        this.eleccion1 = eleccion1;
        this.eleccion2 = eleccion2;
        this.eleccion3 = eleccion3;
        this.eleccion4 = eleccion4;
        this.correcta = correcta;
        this.calificacion = calificacion;
        this.prueba = prueba;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        pregunta = pregunta;
    }

    public String getEleccion1() {
        return eleccion1;
    }

    public void setEleccion1(String eleccion1) {
        this.eleccion1 = eleccion1;
    }

    public String getEleccion2() {
        return eleccion2;
    }

    public void setEleccion2(String eleccion2) {
        this.eleccion2 = eleccion2;
    }

    public String getEleccion3() {
        return eleccion3;
    }

    public void setEleccion3(String eleccion3) {
        this.eleccion3 = eleccion3;
    }

    public String getEleccion4() {
        return eleccion4;
    }

    public void setEleccion4(String eleccion4) {
        this.eleccion4 = eleccion4;
    }

    public String getCorrecta() {
        return correcta;
    }

    public void setCorrecta(String correcta) {
        this.correcta = correcta;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrueba() {
        return prueba;
    }

    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

    @Override
    public String toString() {
        return prueba;
    }

    public ContentValues aPrueba()
    {
        ContentValues pruebas = new ContentValues(8);
        pruebas.put(BaseDatos.PRUEBA_ID, id);
        pruebas.put(BaseDatos.PREGUNTA, pregunta);
        pruebas.put(BaseDatos.ELECCION_1, eleccion1);
        pruebas.put(BaseDatos.ELECCION_2, eleccion2);
        pruebas.put(BaseDatos.ELECCION_3, eleccion3);
        pruebas.put(BaseDatos.ELECCION_4, eleccion4);
        pruebas.put(BaseDatos.CORRECTA, correcta);
        pruebas.put(BaseDatos.CALIFICACION, calificacion);

        return  pruebas;
    }
}
