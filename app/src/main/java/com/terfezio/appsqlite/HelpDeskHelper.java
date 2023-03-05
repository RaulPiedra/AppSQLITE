package com.terfezio.appsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

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
                + HelpDeskContract.UsuarioEntry.COLUMN_NAME_FOTO + " BLOB);";

        String CREATE_INCIDENCIA_TABLE = "CREATE TABLE " + HelpDeskContract.IncidenciaEntry.TABLE_NAME + "("
                + HelpDeskContract.IncidenciaEntry.COLUMN_NAME_DNI + " TEXT PRIMARY KEY, "
                + HelpDeskContract.IncidenciaEntry.COLUMN_NAME_FECHA_INCIDENCIA + " TEXT, "
                + HelpDeskContract.IncidenciaEntry.COLUMN_NAME_OBSERVACIONES + " TEXT , "
                + HelpDeskContract.IncidenciaEntry.COLUMN_NAME_DNI_INFORMATICO + " TEXT, "
                + HelpDeskContract.IncidenciaEntry.COLUMN_NAME_ESTADO_INCIDENCIA + " TEXT, "
                + HelpDeskContract.IncidenciaEntry.COLUMN_NAME_FECHA_RESOLUCION + " TEXT, "
                + HelpDeskContract.IncidenciaEntry.COLUMN_NAME_OBSERVACIONES_INFORMATICO + " TEXT);";

        db.execSQL(CREATE_USUARIO_TABLE);
        db.execSQL(CREATE_INCIDENCIA_TABLE);


        /*Bitmap image = BitmapFactory.decodeFile("app/src/main/res/drawable/user.png");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] b = stream.toByteArray();*

         */
        byte[] b = {0};

        ContentValues values = new ContentValues();

        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_APELLIDOS, "Perez Perez");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_DNI, "44444X");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_NOMBRE, "Pepe");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_USUARIO, "pepe");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_PASS, "pepe123");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_PERFIL, 0);
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_FOTO, b);
        db.insert(HelpDeskContract.UsuarioEntry.TABLE_NAME, null, values);

        ContentValues values2 = new ContentValues();

        /*values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_APELLIDOS, "Sanchez Perez");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_DNI, "44444l");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_NOMBRE, "Juan");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_USUARIO, "juan");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_PASS, "juan123");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_PERFIL, 0);
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_FOTO, b);
        db.insert(HelpDeskContract.UsuarioEntry.TABLE_NAME, null, values2);*/
        Usuario usuario = new Usuario("Juan", "Pardo", "44L", "juan", "123", 1, b);
        db.insert(HelpDeskContract.UsuarioEntry.TABLE_NAME, null, usuario.toContentValues());

        /*ContentValues values2 = new ContentValues();
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_APELLIDOS, "Pardo Sanchez");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_DNI, "44445K");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_NOMBRE, "Juan");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_USUARIO, "juan");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_PASS, "juan123");
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_PERFIL, 1);
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_FOTO, b);
        db.insert(HelpDeskContract.UsuarioEntry.TABLE_NAME, null, values2);*/

        Incidencia incidencia = new Incidencia("44445K", "21/2/2022", "observacioon", "445556L", "activa", "31/12/2023", "obs. Inf.");
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
