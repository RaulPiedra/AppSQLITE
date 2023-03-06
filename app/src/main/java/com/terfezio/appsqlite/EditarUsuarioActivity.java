package com.terfezio.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
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
    private CheckBox checkBox2;
    private ImageView imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);

        editTextNombre2 = findViewById(R.id.editTextNombre2);
        editTextApellidos2 = findViewById(R.id.editTextApellidos2);
        editTextDni2 = findViewById(R.id.editTextDni2);
        editTextDni2.setKeyListener(null);
        editTextUsuario2 = findViewById(R.id.editTextUsuario2);
        editTextPassword2 = findViewById(R.id.editTextTextPassword2);
        imageView3 = findViewById(R.id.imageView3);
        checkBox2 = findViewById(R.id.checkBox2);

        Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        editTextNombre2.setText(usuario.getNombre());
        editTextApellidos2.setText(usuario.getApellidos());
        editTextDni2.setText(usuario.getDni());
        editTextUsuario2.setText(usuario.getUsuario());
        editTextPassword2.setText(usuario.getPass());
        Bitmap bitmap = BitmapFactory.decodeByteArray(usuario.getFoto(),0, usuario.getFoto().length);
        imageView3.setImageBitmap(bitmap);

        if (usuario.getPerfil() == 1) checkBox2.setChecked(true);


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

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", -1);
        setResult(Activity.RESULT_OK, returnIntent);

        finish();

    }

    public void modificarUsuario(View view) {
        HelpDeskHelper helpDeskHelper = new HelpDeskHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase = helpDeskHelper.getWritableDatabase();

        String nombre = editTextNombre2.getText().toString();
        String apellidos = editTextApellidos2.getText().toString();
        String dni = editTextDni2.getText().toString();
        String usuario = editTextUsuario2.getText().toString();
        String password = editTextPassword2.getText().toString();

        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView3.getDrawable();
        Bitmap imageBitmap = bitmapDrawable.getBitmap();

        byte[] foto = new byte[0];
        if (imageBitmap != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            foto = stream.toByteArray();
        }

        ContentValues values = new ContentValues();
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_NOMBRE, nombre);
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_APELLIDOS, apellidos);
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_USUARIO, usuario);
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_PASS, password);
        values.put(HelpDeskContract.UsuarioEntry.COLUMN_NAME_FOTO, foto);


        String selection = HelpDeskContract.UsuarioEntry.COLUMN_NAME_DNI + " LIKE ?";
        String[] selectionArgs = {dni};
        sqLiteDatabase.update(HelpDeskContract.UsuarioEntry.TABLE_NAME, values, selection, selectionArgs);
        editTextNombre2.setText(null);
        editTextApellidos2.setText(null);
        editTextDni2.setText(null);
        editTextUsuario2.setText(null);
        editTextPassword2.setText(null);
        imageView3.setImageBitmap(null);

        Toast.makeText(this, "Usuario modificado", Toast.LENGTH_SHORT).show();

        sqLiteDatabase.close();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", -1);
        setResult(Activity.RESULT_OK, returnIntent);

        finish();
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
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView3.setImageBitmap(imageBitmap);
        }
    }
}