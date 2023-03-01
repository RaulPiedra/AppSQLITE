package com.terfezio.appsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "helpdesk.db";
    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USUARIO_TABLE = "CREATE TABLE " + HelpDeskContract.Usuario.TABLE_NAME + "("
                + HelpDeskContract.Usuario.COLUMN_NAME_USUARIO + " TEXT, "
                + HelpDeskContract.Usuario.COLUMN_NAME_APELLIDOS + " TEXT, "
                + HelpDeskContract.Usuario.COLUMN_NAME_DNI + " TEXT PRIMARY KEY, "
                + HelpDeskContract.Usuario.COLUMN_NAME_USUARIO + " TEXT, "
                + HelpDeskContract.Usuario.COLUMN_NAME_USUARIO + " TEXT, "
                + HelpDeskContract.Usuario.COLUMN_NAME_FOTO + " TEXT);";

        String CREATE_INCIDENCIA_TABLE = "CREATE TABLE " + HelpDeskContract.Usuario.TABLE_NAME + "("
                + HelpDeskContract.Incidencia.COLUMN_NAME_DNI + " TEXT PRIMARY KEY, "
                + HelpDeskContract.Incidencia.COLUMN_NAME_FECHA_INCIDENCIA + " TEXT, "
                + HelpDeskContract.Incidencia.COLUMN_NAME_OBSERVACIONES + " TEXT , "
                + HelpDeskContract.Incidencia.COLUMN_NAME_DNI_INFORMATICO + " TEXT, "
                + HelpDeskContract.Incidencia.COLUMN_NAME_ESTADO_INCIDENCIA + " TEXT, "
                + HelpDeskContract.Incidencia.COLUMN_NAME_FECHA_RESOLUCION + " TEXT, "
                + HelpDeskContract.Incidencia.COLUMN_NAME_OBSERVACIONES_INFORMATICO + " TEXT);";

        db.execSQL(CREATE_USUARIO_TABLE);
        db.execSQL(CREATE_INCIDENCIA_TABLE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + HelpDeskContract.Usuario.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + HelpDeskContract.Incidencia.TABLE_NAME);

        onCreate(db);
    }
}
