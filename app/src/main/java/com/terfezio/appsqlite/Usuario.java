package com.terfezio.appsqlite;

import android.content.ContentValues;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre;
    private String apellidos;
    private String dni;
    private String usuario;
    private String pass;
    private int perfil;
    private byte[] foto;

    public Usuario() {
    }

    public Usuario(String nombre, String apellidos, String dni, String usuario, String pass, int perfil, byte[] foto) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.usuario = usuario;
        this.pass = pass;
        this.perfil = perfil;
        this.foto = foto;
    }
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_NOMBRE, nombre);
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_APELLIDOS, apellidos);
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_DNI, dni);
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_USUARIO, usuario);
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_PASS, pass);
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_PERFIL, perfil);
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_FOTO, foto);
        return values;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int isPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
