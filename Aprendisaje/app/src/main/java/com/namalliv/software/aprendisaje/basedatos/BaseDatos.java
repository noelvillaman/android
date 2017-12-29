package com.namalliv.software.aprendisaje.basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by NoelBonni on 4/8/2017.
 */

public class BaseDatos extends SQLiteOpenHelper{
        public static final String LOGTAG = "info";

        //Constants for db name and version
        private static final String DATABASE_NAME = "aprendejava.db";
        private static final int DATABASE_VERSION = 1;

        //Constants for identifying table and columns
        public static final String TABLA_TUTORIAL = "tutorial";
        public static final String TURORIAL_ID = "tutoId";
        public static final String TUTORIAL = "categoria";

        public static final String TABLA_PROGRAMA = "programa";
        public static final String PROGRAMA_ID = "programa_id";
        public static final String PROGRAMA = "programa";

        public  static final String TABLA_PRUEBA = "prueba";
        public static final String PREGUNTA = "pregunta";
        public static final String ELECCION_1 = "eleccion_1";
        public static final String ELECCION_2 = "eleccion_2";
        public static final String ELECCION_3 = "eleccion_3";
        public static final String ELECCION_4 = "eleccion_4";
        public static final String CORRECTA = "correcta";
        public static final String CALIFICACION = "calificacion";
        public static final String PRUEBA_ID = "prueba_id";



        private static final String CREAR_TUTORIAL =
                "CREATE TABLE " + TABLA_TUTORIAL + " (" +
                        TURORIAL_ID + " INTEGER PRIMARY KEY NOT NULL, "+
                        TUTORIAL + " TEXT " +
                        ")";

        private static final String CREAR_PROGRAMA =
                "CREATE TABLE " + TABLA_PROGRAMA + " (" +
                        PROGRAMA_ID + " INTEGER PRIMARY KEY NOT NULL, "+
                        PROGRAMA + " TEXT " +
                        ")";

        private static final String CREAR_PRUEBA =
                "CREATE TABLE " + TABLA_PRUEBA + " (" +
                        PRUEBA_ID + " INTEGER PRIMARY KEY NOT NULL, "+
                        PREGUNTA + " TEXT, " +
                        ELECCION_1 + " TEXT, " +
                        ELECCION_2 + " TEXT, " +
                        ELECCION_3 + " TEXT, " +
                        ELECCION_4 + " TEXT, " +
                        CORRECTA + " TEXT, " +
                        CALIFICACION + " INTEGER " +
                        ")";

        public BaseDatos(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREAR_TUTORIAL);
            db.execSQL(CREAR_PRUEBA);
            db.execSQL(CREAR_PROGRAMA);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLA_TUTORIAL);
            db.execSQL("DROP TABLE IF EXISTS " + TABLA_PROGRAMA);
            db.execSQL("DROP TABLE IF EXISTS " + TABLA_PRUEBA);
            onCreate(db);
        }
}
