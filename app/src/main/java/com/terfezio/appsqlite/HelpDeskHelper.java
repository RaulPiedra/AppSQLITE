package com.terfezio.appsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class HelpDeskHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "helpdesk.db";
    public HelpDeskHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USUARIO_TABLE = "CREATE TABLE " + HelpDeskContract.UsuarioEntry.TABLE_NAME + "("
                + HelpDeskContract.UsuarioEntry.COLUMN_NAME_NOMBRE + " TEXT, "
                + HelpDeskContract.UsuarioEntry.COLUMN_NAME_APELLIDOS + " TEXT, "
                + HelpDeskContract.UsuarioEntry.COLUMN_NAME_DNI + " TEXT PRIMARY KEY, "
                + HelpDeskContract.UsuarioEntry.COLUMN_NAME_USUARIO + " TEXT, "
                + HelpDeskContract.UsuarioEntry.COLUMN_NAME_PASS + " TEXT, "
                + HelpDeskContract.UsuarioEntry.COLUMN_NAME_PERFIL + " INT, "
                + HelpDeskContract.UsuarioEntry.COLUMN_NAME_FOTO + " TEXT);";

        String CREATE_INCIDENCIA_TABLE = "CREATE TABLE " + HelpDeskContract.UsuarioEntry.TABLE_NAME + "("
                + HelpDeskContract.IncidenciaEntry.COLUMN_NAME_DNI + " TEXT PRIMARY KEY, "
                + HelpDeskContract.IncidenciaEntry.COLUMN_NAME_FECHA_INCIDENCIA + " TEXT, "
                + HelpDeskContract.IncidenciaEntry.COLUMN_NAME_OBSERVACIONES + " TEXT , "
                + HelpDeskContract.IncidenciaEntry.COLUMN_NAME_DNI_INFORMATICO + " TEXT, "
                + HelpDeskContract.IncidenciaEntry.COLUMN_NAME_ESTADO_INCIDENCIA + " TEXT, "
                + HelpDeskContract.IncidenciaEntry.COLUMN_NAME_FECHA_RESOLUCION + " TEXT, "
                + HelpDeskContract.IncidenciaEntry.COLUMN_NAME_OBSERVACIONES_INFORMATICO + " TEXT);";

        db.execSQL(CREATE_USUARIO_TABLE);
        db.execSQL(CREATE_INCIDENCIA_TABLE);

        ContentValues values = new ContentValues();
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_APELLIDOS, "Perez Perez");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_DNI, "44444K");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_NOMBRE, "Pepe");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_USUARIO, "pepe");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_PASS, "pepe123");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_PERFIL, 1);
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_FOTO, "ruta/fotoPepe");
        db.insert(HelpDeskContract.UsuarioEntry.TABLE_NAME, null, values);

        ContentValues values2 = new ContentValues();
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_APELLIDOS, "Pardo Sanchez");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_DNI, "44445K");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_NOMBRE, "Juan");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_USUARIO, "juan");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_PASS, "juan123");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_PERFIL, 1);
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_FOTO, "ruta/fotoJuan");
        db.insert(HelpDeskContract.UsuarioEntry.TABLE_NAME, null, values2);

        Incidencia incidencia = new Incidencia("44445K", "21/2/2022", "observacion", "445556L", "activa", "31/12/2023", "obs. Inf.");
        db.insert(HelpDeskContract.IncidenciaEntry.TABLE_NAME, null, incidencia.toContentValues());

        Incidencia incidencia2 = new Incidencia("44446K", "21/2/2022", "observacion", "445556L", "activa", "31/12/2023", "nd");
        db.insert(HelpDeskContract.IncidenciaEntry.TABLE_NAME, null, incidencia2.toContentValues());





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + HelpDeskContract.UsuarioEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + HelpDeskContract.IncidenciaEntry.TABLE_NAME);

        onCreate(db);
    }
}
