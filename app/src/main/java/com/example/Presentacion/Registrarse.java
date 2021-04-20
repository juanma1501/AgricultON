package com.example.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class Registrarse extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtCorreo;
    private EditText txtFechaNacimiento;
    private EditText txtContrasena;
    private EditText txtConfirmarContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtFechaNacimiento = findViewById(R.id.txtFecha);
        txtContrasena = findViewById(R.id.txtRContrasena);
        txtConfirmarContrasena = findViewById(R.id.txtRConfirmarContrasena);

    }

    public void crear_usuario(View view){
        if (!txtNombre.getText().toString().equals("") && !txtApellido.getText().toString().equals("") && !txtCorreo.getText().toString().equals("") && !txtFechaNacimiento.getText().toString().equals("") && !txtContrasena.getText().toString().equals("") && !txtConfirmarContrasena.getText().toString().equals("")){

        }else if(txtNombre.getText().toString().equals("")){
            Toast.makeText(this, "Rellena el nombre.", Toast.LENGTH_LONG).show();
            //El teclado se abrirá automáticamente con las 3 líneas de abajo.
            txtNombre.requestFocus();
            InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(txtNombre, InputMethodManager.SHOW_IMPLICIT);
        }else if(txtApellido.getText().toString().equals("")){
            Toast.makeText(this, "Escribe tu primer apellido.", Toast.LENGTH_LONG).show();
            //El teclado se abrirá automáticamente con las 3 líneas de abajo.
            txtApellido.requestFocus();
            InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(txtApellido, InputMethodManager.SHOW_IMPLICIT);
        }else if(txtFechaNacimiento.getText().toString().equals("")){
            Toast.makeText(this, "Escribe tu fecha de nacimiento.", Toast.LENGTH_LONG).show();
            //El teclado se abrirá automáticamente con las 3 líneas de abajo.
            txtFechaNacimiento.requestFocus();
            InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(txtFechaNacimiento, InputMethodManager.SHOW_IMPLICIT);
        }else if(txtCorreo.getText().toString().equals("")){
            Toast.makeText(this, "Escribe una dirección de correo electrónico.", Toast.LENGTH_LONG).show();
            //El teclado se abrirá automáticamente con las 3 líneas de abajo.
            txtCorreo.requestFocus();
            InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(txtCorreo, InputMethodManager.SHOW_IMPLICIT);
        }else if(txtContrasena.getText().toString().equals("")){
            Toast.makeText(this, "Escribe una contraseña", Toast.LENGTH_LONG).show();
            //El teclado se abrirá automáticamente con las 3 líneas de abajo.
            txtContrasena.requestFocus();
            InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(txtContrasena, InputMethodManager.SHOW_IMPLICIT);
        }else if(txtConfirmarContrasena.getText().toString().equals("")){
            Toast.makeText(this, "Confirma la contrseña", Toast.LENGTH_LONG).show();
            //El teclado se abrirá automáticamente con las 3 líneas de abajo.
            txtConfirmarContrasena.requestFocus();
            InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(txtConfirmarContrasena, InputMethodManager.SHOW_IMPLICIT);
        }else{
            Toast.makeText(this, "Todos los campos son obligatorios, por favor, rellénalos.", Toast.LENGTH_LONG).show();
        }
    }
}