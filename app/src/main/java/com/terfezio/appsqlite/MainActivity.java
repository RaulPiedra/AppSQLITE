package com.terfezio.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirListaUsuarios(View view) {
        Intent intent = new Intent(view.getContext(), UserActivity.class);
        startActivity(intent);
    }

    public void abrirCrearUsuario(View view) {
        Intent intent = new Intent(view.getContext(), RegistroUsuarioActivity.class);
        startActivity(intent);
    }

    public void cargarIncidencias(View view) {
        Intent intent = new Intent(view.getContext(), IncidenciaActivity.class);
        startActivity(intent);
    }
}