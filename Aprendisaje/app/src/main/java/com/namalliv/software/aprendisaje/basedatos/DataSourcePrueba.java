package com.namalliv.software.aprendisaje.basedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.namalliv.software.aprendisaje.model.Prueba;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NoelBonni on 4/8/2017.
 */

public class DataSourcePrueba {
    public static final String LOGTAG = "PYTHON_INFO";


    SQLiteOpenHelper dbhelper;
    private SQLiteDatabase mDatabase;
    private Context mContext;

    public static final String[] allColumns = {
            BaseDatos.PRUEBA_ID,
            BaseDatos.PREGUNTA,
            BaseDatos.ELECCION_1,
            BaseDatos.ELECCION_2,
            BaseDatos.ELECCION_3,
            BaseDatos.ELECCION_4,
            BaseDatos.CORRECTA,
            BaseDatos.CALIFICACION
    };

    public DataSourcePrueba(Context context){
        dbhelper = new BaseDatos(context);
        this.mContext = context;
        mDatabase = dbhelper.getWritableDatabase();

    }

    public void open(){
        mDatabase = dbhelper.getWritableDatabase(); // this returns a reference to the connection to the db
    }

    public void close(){
        dbhelper.close();
    }

    public Prueba crearPregunta(Prueba prueba){
        ContentValues values = prueba.aPrueba();
        values.put(BaseDatos.PRUEBA_ID, prueba.getId());
        values.put(BaseDatos.PREGUNTA, prueba.getPregunta());
        values.put(BaseDatos.ELECCION_1, prueba.getEleccion1());
        values.put(BaseDatos.ELECCION_2, prueba.getEleccion2());
        values.put(BaseDatos.ELECCION_3, prueba.getEleccion3());
        values.put(BaseDatos.ELECCION_4, prueba.getEleccion4());
        values.put(BaseDatos.CORRECTA, prueba.getCorrecta());
        values.put(BaseDatos.CALIFICACION, prueba.getCalificacion());

        mDatabase.insert(BaseDatos.TABLA_PRUEBA, null, values);

        return prueba;
    }

    public Long preguntaCantidad()
    {
        return DatabaseUtils.queryNumEntries(mDatabase, BaseDatos.TABLA_PRUEBA);
    }

    public List<Prueba> laPrueba(){
        List<Prueba> listaPrueba = new ArrayList<>();
        Cursor cursor = mDatabase.query(BaseDatos.TABLA_PRUEBA, allColumns,
                null,  null, null, null, null);
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                Prueba prueba = new Prueba();
                prueba.setId(cursor.getString(cursor.getColumnIndex(BaseDatos.PRUEBA_ID)));
                prueba.setPregunta(cursor.getString(cursor.getColumnIndex(BaseDatos.PREGUNTA)));
                prueba.setEleccion1(cursor.getString(cursor.getColumnIndex(BaseDatos.ELECCION_1)));
                prueba.setEleccion2(cursor.getString(cursor.getColumnIndex(BaseDatos.ELECCION_2)));
                prueba.setEleccion3(cursor.getString(cursor.getColumnIndex(BaseDatos.ELECCION_3)));
                prueba.setEleccion4(cursor.getString(cursor.getColumnIndex(BaseDatos.ELECCION_4)));
                prueba.setCorrecta(cursor.getString(cursor.getColumnIndex(BaseDatos.CORRECTA)));
                prueba.setCalificacion(cursor.getInt(cursor.getColumnIndex(BaseDatos.CALIFICACION)));

                listaPrueba.add(prueba);
            }
        }
        return listaPrueba;
    }

    public Cursor getId(String where){
        Cursor c = mDatabase.query(BaseDatos.TABLA_PRUEBA, new String[] {BaseDatos.PRUEBA_ID},where,null,null,null,null);
        if (c != null) c.moveToFirst();
        return c;
    }
}
