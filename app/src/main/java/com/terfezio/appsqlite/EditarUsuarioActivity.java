package com.terfezio.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class EditarUsuarioActivity extends AppCompatActivity {
    private EditText editTextNombre2;
    private EditText editTextApellidos2;
    private EditText editTextDni2;
    private EditText editTextUsuario2;
    private EditText editTextPassword2;
    private ImageView imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);

        editTextNombre2 = findViewById(R.id.editTextNombre2);
        editTextApellidos2 = findViewById(R.id.editTextApellidos2);
        editTextDni2 = findViewById(R.id.editTextDni2);
        editTextUsuario2 = findViewById(R.id.editTextUsuario2);
        editTextPassword2 = findViewById(R.id.editTextTextPassword2);
        imageView3 = findViewById(R.id.imageView3);

        Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        editTextNombre2.setText(usuario.getNombre());
        editTextApellidos2.setText(usuario.getApellidos());
        editTextDni2.setText(usuario.getDni());
        editTextUsuario2.setText(usuario.getUsuario());
        editTextPassword2.setText(usuario.getPass());
        Bitmap bitmap = BitmapFactory.decodeByteArray(usuario.getFoto(),0, usuario.getFoto().length);
        imageView3.setImageBitmap(bitmap);


    }

    public void borrarUsuario(View view) {
        HelpDeskHelper helpDeskHelper = new HelpDeskHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase = helpDeskHelper.getWritableDatabase();

        String dni = String.valueOf(editTextDni2.getText());

        String selection = HelpDeskContract.UsuarioEntry.COLUMN_NAME_DNI + " = ?";
        String[] selectionArgs = {dni};
        sqLiteDatabase.delete(HelpDeskContract.UsuarioEntry.TABLE_NAME, selection, selectionArgs);
        editTextNombre2.setText(null);
        editTextApellidos2.setText(null);
        editTextDni2.setText(null);
        editTextUsuario2.setText(null);
        editTextPassword2.setText(null);
        imageView3.setImageBitmap(null);

        Toast.makeText(this, "Usuario borrado", Toast.LENGTH_SHORT).show();

        sqLiteDatabase.close();
    }
}