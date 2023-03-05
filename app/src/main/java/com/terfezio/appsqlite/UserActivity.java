package com.terfezio.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    ListView listViewUsuario;
    List<Usuario> usuarios;
    HelpDeskHelper helpDeskHelper;
    SQLiteDatabase sqLiteDatabase;
    AdaptadorUsuario adaptadorUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        listViewUsuario = findViewById(R.id.listViewUsuario);
        cargarUsuarios();


        listViewUsuario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                int LAUNCH_SECOND_ACTIVITY = 1;
                Intent intent = new Intent(getApplicationContext(), EditarUsuarioActivity.class);
                Usuario usuario = usuarios.get(position);
                intent.putExtra("usuario", usuario);
                startActivityForResult(intent, LAUNCH_SECOND_ACTIVITY);
            }
        });
    }
    public void cargarUsuarios() {

        helpDeskHelper = new HelpDeskHelper(getApplicationContext());
        sqLiteDatabase = helpDeskHelper.getReadableDatabase();
        usuarios = new ArrayList<>();

        String[] projection = {
                HelpDeskContract.UsuarioEntry.COLUMN_NAME_NOMBRE,
                HelpDeskContract.UsuarioEntry.COLUMN_NAME_APELLIDOS,
                HelpDeskContract.UsuarioEntry.COLUMN_NAME_DNI,
                HelpDeskContract.UsuarioEntry.COLUMN_NAME_USUARIO,
                HelpDeskContract.UsuarioEntry.COLUMN_NAME_PASS,
                HelpDeskContract.UsuarioEntry.COLUMN_NAME_FOTO,
                HelpDeskContract.UsuarioEntry.COLUMN_NAME_PERFIL
        };
        Cursor cursor = sqLiteDatabase.query(
                HelpDeskContract.UsuarioEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null

        );
        while (cursor.moveToNext()) {
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(HelpDeskContract.UsuarioEntry.COLUMN_NAME_NOMBRE));
            String apellidos = cursor.getString(cursor.getColumnIndexOrThrow(HelpDeskContract.UsuarioEntry.COLUMN_NAME_APELLIDOS));
            String dni = cursor.getString(cursor.getColumnIndexOrThrow(HelpDeskContract.UsuarioEntry.COLUMN_NAME_DNI));
            String usuario = cursor.getString(cursor.getColumnIndexOrThrow(HelpDeskContract.UsuarioEntry.COLUMN_NAME_USUARIO));
            String pass = cursor.getString(cursor.getColumnIndexOrThrow(HelpDeskContract.UsuarioEntry.COLUMN_NAME_PASS));
            byte[] bytes = cursor.getBlob(cursor.getColumnIndexOrThrow(HelpDeskContract.UsuarioEntry.COLUMN_NAME_FOTO));
            int perfil = cursor.getInt(cursor.getColumnIndexOrThrow(HelpDeskContract.UsuarioEntry.COLUMN_NAME_PERFIL));

            if(bytes.length == 1) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.user);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                bytes = stream.toByteArray();
            }
            Usuario usuario1 = new Usuario(nombre, apellidos, dni, usuario, pass, perfil, bytes);
            usuarios.add(usuario1);
        }
        cursor.close();
        AdaptadorUsuario adaptadorUsuario = new AdaptadorUsuario(this, usuarios);
        listViewUsuario.setAdapter(adaptadorUsuario);
        helpDeskHelper.close();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        cargarUsuarios();
    } //onActivityResult
}