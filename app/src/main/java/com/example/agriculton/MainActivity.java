package com.example.agriculton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtContrasena;
    private AdminSQLiteOpenHelper admin;
    private SQLiteDatabase BD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtContrasena = (EditText)findViewById(R.id.txtContrasena);

        //Conexión a la BBDD
        admin = new AdminSQLiteOpenHelper(this, "BD", null, 1);
        BD = admin.getWritableDatabase();
    }

    public void IniciarSesion(View view){

        String email = txtEmail.getText().toString();
        String contrasena = txtContrasena.getText().toString();

        if(!email.equals("") || !contrasena.equals("")){
            Cursor consulta = BD.rawQuery(
                    "select * from Usuarios where email = '" + email + "' and contrasena = '" + contrasena + "'", null);
            if (consulta.moveToFirst()) {
                Intent intent = new Intent(this, Perfil.class);
                //intent.putExtra("nombre", nombre) esto es para pasar el nombre a la otra activity;
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, "Email o contraseña incorrectos", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Rellena el email y la contraseña", Toast.LENGTH_LONG).show();
            //El teclado se abrirá automáticamente con las 3 líneas de abajo.
            txtEmail.requestFocus();
            InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(txtEmail, InputMethodManager.SHOW_IMPLICIT);
        }
    }
}