package com.terfezio.appsqlite;

import android.provider.BaseColumns;

public final class HelpDeskContract {

        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private HelpDeskContract() {}

        /* Inner class that defines the table contents */
        public static class Usuario implements BaseColumns {
            public static final String TABLE_NAME = "usuario";
            public static final String COLUMN_NAME_NOMBRE = "nombre";
            public static final String COLUMN_NAME_APELLIDOS = "apellidos";
            public static final String COLUMN_NAME_DNI = "dni";
            public static final String COLUMN_NAME_USUARIO = "usuario";
            public static final String COLUMN_NAME_PASS = "pass";
            public static final String COLUMN_NAME_FOTO = "foto";
            public static final String COLUMN_NAME_PERFIL = "perfil";
        }
        public static class Incidencia implements BaseColumns {
            public static final String TABLE_NAME = "incidencia";
            public static final String COLUMN_NAME_DNI = "dni";
            public static final String COLUMN_NAME_FECHA_INCIDENCIA = "fecha_incidencia";
            public static final String COLUMN_NAME_OBSERVACIONES = "observaciones";
            public static final String COLUMN_NAME_DNI_INFORMATICO = "dni_informatico";
            public static final String COLUMN_NAME_ESTADO_INCIDENCIA = "estado_incidencia";
            public static final String COLUMN_NAME_FECHA_RESOLUCION = "fecha_resolucion";
            public static final String COLUMN_NAME_OBSERVACIONES_INFORMATICO = "observaciones_informatico";
        }
    }

