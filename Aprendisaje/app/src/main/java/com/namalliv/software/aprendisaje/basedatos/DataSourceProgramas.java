package com.namalliv.software.aprendisaje.basedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.namalliv.software.aprendisaje.model.Programa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NoelBonni on 4/8/2017.
 */

public class DataSourceProgramas {
    public static final String LOGTAG = "HIMNOS_INFO";

    SQLiteOpenHelper dbhelper;
    SQLiteDatabase database;
    private Context mContext;

    public static final String[] allColumns = {
            BaseDatos.PROGRAMA_ID,
            BaseDatos.PROGRAMA
    };

    public DataSourceProgramas(Context context){
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

    public Programa crearPorgrama(Programa programa){
        ContentValues values = programa.aProgramas();
        values.put(BaseDatos.PROGRAMA_ID, programa.getId());
        values.put(BaseDatos.PROGRAMA, programa.getPrograma());

        database.insert(BaseDatos.TABLA_PROGRAMA, null, values);

        return programa;
    }

    public Long programaCantidad()
    {
        return DatabaseUtils.queryNumEntries(database, BaseDatos.TABLA_PROGRAMA);
    }

    public List<Programa> losProgramas(){
        List<Programa> listaProgramas = new ArrayList<>();
        Cursor cursor = database.query(BaseDatos.TABLA_PROGRAMA, allColumns,
                null,  null, null, null, null);
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                Programa programa = new Programa();
                programa.setId(cursor.getInt(cursor.getColumnIndex(BaseDatos.PROGRAMA_ID)));
                programa.setPrograma(cursor.getString(cursor.getColumnIndex(BaseDatos.PROGRAMA)));

                listaProgramas.add(programa);
            }
        }
        return listaProgramas;
    }

    public Cursor getId(String where){
        Cursor c = database.query(BaseDatos.TABLA_PROGRAMA, new String[] {BaseDatos.PROGRAMA_ID},where,null,null,null,null);
        if (c != null) c.moveToFirst();
        return c;
    }
}
