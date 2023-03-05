package com.terfezio.appsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class IncidenciaActivity extends AppCompatActivity {
    RecyclerView recyclerViewIncidencia;
    List<Incidencia> incidenciass;
    HelpDeskHelper helpDeskHelper;
    SQLiteDatabase sqLiteDatabase;
    AdaptadorIncidencia adaptadorIncidencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidencia);

        recyclerViewIncidencia = findViewById(R.id.recyclerView);
        recyclerViewIncidencia.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        cargarIncidencias();
    }

    private void cargarIncidencias() {
        helpDeskHelper = new HelpDeskHelper(getApplicationContext());
        sqLiteDatabase = helpDeskHelper.getReadableDatabase();
        incidenciass = new ArrayList<>();
        //Incidencia incidencia1 = new Incidencia("44445K", "21/2/2022", "observacion", "445556L", "activa", "31/12/2023", "obs. Inf.");
        //incidenciass.add(incidencia1);

        String[] projection = {
                HelpDeskContract.IncidenciaEntry.COLUMN_NAME_DNI,
                HelpDeskContract.IncidenciaEntry.COLUMN_NAME_FECHA_INCIDENCIA,
                HelpDeskContract.IncidenciaEntry.COLUMN_NAME_OBSERVACIONES,
                HelpDeskContract.IncidenciaEntry.COLUMN_NAME_DNI_INFORMATICO,
                HelpDeskContract.IncidenciaEntry.COLUMN_NAME_ESTADO_INCIDENCIA,
                HelpDeskContract.IncidenciaEntry.COLUMN_NAME_FECHA_RESOLUCION,
                HelpDeskContract.IncidenciaEntry.COLUMN_NAME_OBSERVACIONES_INFORMATICO
        };
        Cursor cursor = sqLiteDatabase.query(
                HelpDeskContract.IncidenciaEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null

        );
        while (cursor.moveToNext()) {
            String dni = cursor.getString(cursor.getColumnIndexOrThrow(HelpDeskContract.IncidenciaEntry.COLUMN_NAME_DNI));
            String fechaIncidencia = cursor.getString(cursor.getColumnIndexOrThrow(HelpDeskContract.IncidenciaEntry.COLUMN_NAME_FECHA_INCIDENCIA));
            String observaciones = cursor.getString(cursor.getColumnIndexOrThrow(HelpDeskContract.IncidenciaEntry.COLUMN_NAME_OBSERVACIONES));
            String dniInformatico = cursor.getString(cursor.getColumnIndexOrThrow(HelpDeskContract.IncidenciaEntry.COLUMN_NAME_DNI_INFORMATICO));
            String estadoIncidencia = cursor.getString(cursor.getColumnIndexOrThrow(HelpDeskContract.IncidenciaEntry.COLUMN_NAME_ESTADO_INCIDENCIA));
            String fechaResolucion = cursor.getString(cursor.getColumnIndexOrThrow(HelpDeskContract.IncidenciaEntry.COLUMN_NAME_FECHA_RESOLUCION));
            String observacionesInformatico = cursor.getString(cursor.getColumnIndexOrThrow(HelpDeskContract.IncidenciaEntry.COLUMN_NAME_OBSERVACIONES_INFORMATICO));
            Incidencia incidencia = new Incidencia(dni, fechaIncidencia, observaciones, dniInformatico, estadoIncidencia, fechaResolucion
                    , observacionesInformatico);
            incidenciass.add(incidencia);
        }
        cursor.close();
        adaptadorIncidencia = new AdaptadorIncidencia(incidenciass);
        recyclerViewIncidencia.setAdapter(adaptadorIncidencia);
        helpDeskHelper.close();
    }
}