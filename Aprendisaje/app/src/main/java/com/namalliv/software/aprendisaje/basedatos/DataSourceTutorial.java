package com.namalliv.software.aprendisaje.basedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.namalliv.software.aprendisaje.model.Pregunta;
import com.namalliv.software.aprendisaje.model.Tutorial;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NoelBonni on 4/8/2017.
 */

public class DataSourceTutorial {
    public static final String LOGTAG = "HIMNOS_INFO";

    SQLiteOpenHelper dbhelper;
    SQLiteDatabase database;
    private Context mContext;

    public static final String[] allColumns = {
            BaseDatos.TURORIAL_ID,
            BaseDatos.TUTORIAL
    };

    public DataSourceTutorial(Context context){
        dbhelper = new BaseDatos(context);
        this.mContext = context;
        database = dbhelper.getWritableDatabase();

    }

    public void open(){
        database = dbhelper.getWritableDatabase(); // this returns a reference to the connection to the db
    }

    public void close(){
        dbhelper.close();
    }

    public Tutorial crearTutoriales(Tutorial tutorial){
        ContentValues values = tutorial.aTutoriales();
        values.put(BaseDatos.TURORIAL_ID, tutorial.getId());
        values.put(BaseDatos.TUTORIAL, tutorial.getTutorial());

        database.insert(BaseDatos.TABLA_TUTORIAL, null, values);

        return tutorial;
    }

    public Long tutorialCantidad()
    {
        return DatabaseUtils.queryNumEntries(database, BaseDatos.TABLA_TUTORIAL);
    }

    public List<Pregunta> losTuroriales(){
        List<Pregunta> litasTutoriales = new ArrayList<>();
        Cursor cursor = database.query(BaseDatos.TABLA_TUTORIAL, allColumns,
                null,  null, null, null, null);
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                Pregunta pregunta = new Pregunta();
                pregunta.setId(cursor.getInt(cursor.getColumnIndex(BaseDatos.TURORIAL_ID)));
                pregunta.setPregunta(cursor.getString(cursor.getColumnIndex(BaseDatos.TUTORIAL)));

                litasTutoriales.add(pregunta);
            }
        }
        return litasTutoriales;
    }

    public Cursor getId(String where){
        Cursor c = database.query(BaseDatos.TABLA_TUTORIAL, new String[] {BaseDatos.TURORIAL_ID},where,null,null,null,null);
        if (c != null) c.moveToFirst();
        return c;
    }
}
