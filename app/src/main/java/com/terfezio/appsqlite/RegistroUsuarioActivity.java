package com.terfezio.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class RegistroUsuarioActivity extends AppCompatActivity {
    private EditText editTextNombre;
    private EditText editTextApellidos;
    private EditText editTextDni;
    private EditText editTextUsuario;
    private EditText editTextPassword;
    private Bitmap imageBitmap;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextApellidos = findViewById(R.id.editTextApellidos);
        editTextDni = findViewById(R.id.editTextDni);
        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        imageView = findViewById(R.id.imageView2);

    }

    public void tomarFoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK && intent.hasExtra("data")) {
            Bundle extras = intent.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

    public void registrarUsuario(View view) {
        HelpDeskHelper helpDeskHelper = new HelpDeskHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase = helpDeskHelper.getWritableDatabase();

        String nombre = editTextNombre.getText().toString();
        String apellidos = editTextApellidos.getText().toString();
        String dni = editTextDni.getText().toString();
        String usuario = editTextUsuario.getText().toString();
        String password = editTextPassword.getText().toString();

        byte[] foto = new byte[0];
        if (imageBitmap != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            foto = stream.toByteArray();
        }
        

        if(nombre.matches("") || apellidos.matches("") || dni.matches("") ||
            usuario.matches("") || password.matches("") || imageBitmap == null) {
            Toast.makeText(this, "Debe introducir todos los campos", Toast.LENGTH_SHORT).show();

        }
        else {
            Usuario usuario1 = new Usuario(nombre, apellidos, dni, usuario, password, 0, foto);

            sqLiteDatabase.insert(HelpDeskContract.UsuarioEntry.TABLE_NAME, null, usuario1.toContentValues());
            editTextNombre.setText(null);
            editTextApellidos.setText(null);
            editTextDni.setText(null);
            editTextUsuario.setText(null);
            editTextPassword.setText(null);
            imageView.setImageBitmap(null);

            Toast.makeText(this, "Usuario creado", Toast.LENGTH_SHORT).show();

            sqLiteDatabase.close();
            finish();
        }


    }
}