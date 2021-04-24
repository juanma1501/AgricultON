package com.example.Presentacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;

import Dominio.Usuario;
import Persistencia.AdminSQLiteOpenHelper;
import Persistencia.ConectorDB;

public class MainActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtContrasena;
    private ConectorDB conectorDB;
    private ConstraintLayout rellayLogin;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtContrasena = (EditText)findViewById(R.id.txtContrasena);
        rellayLogin = findViewById(R.id.layoutLogin);

        //Conexión a la BBDD
        conectorDB = new ConectorDB(this);
    }

    public void IniciarSesion(View view){

        String email = txtEmail.getText().toString();
        String contrasena = txtContrasena.getText().toString();

        if(!email.equals("") && !contrasena.equals("")){
            int id = conectorDB.checkUser(email, contrasena);
            if(id<0)
            {
                Toast.makeText(MainActivity.this,
                        "Correo electrónico o contraseña incorrectos",Toast.LENGTH_SHORT).show();
                txtEmail.getText().clear();
                txtContrasena.getText().clear();
            }
            else
            {
                this.usuario = conectorDB.leerUsuario(id);
                Log.d("Debug_Login " + id,"Usuario leído:"+ usuario.toString());
                Intent i = new Intent(this, PaginaPrincipal.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("usuario", usuario);
                i.putExtras(bundle);
                startActivity(i);
                txtEmail.getText().clear();
                txtContrasena.getText().clear();
                // Snack Bar to show success message that record saved successfully
                Snackbar.make(rellayLogin, getString(R.string.login_correcto),
                        Snackbar.LENGTH_LONG).show();
                conectorDB.cerrar();
            }
        }else{
            Toast.makeText(this, "Rellena el email y la contraseña", Toast.LENGTH_LONG).show();
            //El teclado se abrirá automáticamente con las 3 líneas de abajo.
            txtEmail.requestFocus();
            InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(txtEmail, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public void Registrarse(View view){
        Intent intent = new Intent(this, Registrarse.class);
        //intent.putExtra("nombre", nombre) esto es para pasar el nombre a la otra activity;
        startActivity(intent);
    }
}