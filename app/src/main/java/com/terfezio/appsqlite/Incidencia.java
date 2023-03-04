package com.terfezio.appsqlite;

import android.content.ContentValues;

public class Incidencia {
    private String dni;
    private String fechaIncidencia;
    private String observaciones;
    private String dniInformatico;
    private String estadoIncidencia;
    private String fechaResolucion;
    private String observacionesInformatico;

    public Incidencia() {
    }

    public Incidencia(String dni, String fechaIncidencia, String observaciones, String dniInformatico, String estadoIncidencia, String fechaResolucion, String observacionesInformatico) {
        this.dni = dni;
        this.fechaIncidencia = fechaIncidencia;
        this.observaciones = observaciones;
        this.dniInformatico = dniInformatico;
        this.estadoIncidencia = estadoIncidencia;
        this.fechaResolucion = fechaResolucion;
        this.observacionesInformatico = observacionesInformatico;
    }
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(HelpDeskContract.IncidenciaEntry.COLUMN_NAME_DNI, dni);
        values.put(HelpDeskContract.IncidenciaEntry.COLUMN_NAME_FECHA_INCIDENCIA, fechaIncidencia);
        values.put(HelpDeskContract.IncidenciaEntry.COLUMN_NAME_OBSERVACIONES, observaciones);
        values.put(HelpDeskContract.IncidenciaEntry.COLUMN_NAME_DNI_INFORMATICO, dniInformatico);
        values.put(HelpDeskContract.IncidenciaEntry.COLUMN_NAME_ESTADO_INCIDENCIA, estadoIncidencia);
        values.put(HelpDeskContract.IncidenciaEntry.COLUMN_NAME_FECHA_RESOLUCION, fechaResolucion);
        values.put(HelpDeskContract.IncidenciaEntry.COLUMN_NAME_OBSERVACIONES_INFORMATICO, observacionesInformatico);
        return values;

    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFechaIncidencia() {
        return fechaIncidencia;
    }

    public void setFechaIncidencia(String fechaIncidencia) {
        this.fechaIncidencia = fechaIncidencia;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDniInformatico() {
        return dniInformatico;
    }

    public void setDniInformatico(String dniInformatico) {
        this.dniInformatico = dniInformatico;
    }

    public String getEstadoIncidencia() {
        return estadoIncidencia;
    }

    public void setEstadoIncidencia(String estadoIncidencia) {
        this.estadoIncidencia = estadoIncidencia;
    }

    public String getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(String fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public String getObservacionesInformatico() {
        return observacionesInformatico;
    }

    public void setObservacionesInformatico(String observacionesInformatico) {
        this.observacionesInformatico = observacionesInformatico;
    }
}
