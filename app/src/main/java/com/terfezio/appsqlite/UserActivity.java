package com.terfezio.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    ListView listViewUsuario;
    List<Usuario> usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        listViewUsuario = findViewById(R.id.listViewUsuario);

        Usuario usuario = new Usuario("pepe", "perez", "44c", "pepe", "123", true, "src/foto");
        Usuario usuario2 = new Usuario("pepe", "perez", "44c", "pepe", "123", true, "src/foto");
        usuarios = new ArrayList<>();
        usuarios.add(usuario);
        usuarios.add(usuario2);
        AdaptadorUsuario adaptadorUsuario = new AdaptadorUsuario(this, usuarios);
        listViewUsuario.setAdapter(adaptadorUsuario);
    }
}