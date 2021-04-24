package com.example.Presentacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import Dominio.Usuario;
import Persistencia.ConectorDB;

public class Registrarse extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtCorreo;
    private EditText txtFechaNacimiento;
    private EditText txtContrasena;
    private EditText txtConfirmarContrasena;
    private ConectorDB conectorDB;
    ConstraintLayout rellayRegistrar;
    private Button btnRegistrar;
    private AppCompatTextView textViewLinkLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        initViews();
        initListeners();

        //enable back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario nuevoUser = getDatos();
                Log.d("aqui", "aqui");
                if (nuevoUser != null) {
                    finish();
                }
            }
        });

        txtFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        textViewLinkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }

    private void initViews() {
        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtFechaNacimiento = findViewById(R.id.txtFecha);
        txtContrasena = findViewById(R.id.txtRContrasena);
        txtConfirmarContrasena = findViewById(R.id.txtRConfirmarContrasena);
        btnRegistrar = findViewById(R.id.btnCrearCuenta);
        conectorDB = new ConectorDB(this);
        rellayRegistrar = (ConstraintLayout) findViewById(R.id.rellayRegistrar);
        textViewLinkLogin = (AppCompatTextView) findViewById(R.id.textViewLinkLogin);
    }


    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate = twoDigits(day) + "/" + twoDigits(month + 1) + "/" + year;
                txtFechaNacimiento.setText(selectedDate);
            }
        });
        newFragment.show(this.getSupportFragmentManager(), "datePicker");
    }

    private String twoDigits(int n) {
        return (n <= 9) ? ("0" + n) : String.valueOf(n);
    }

    public Usuario getDatos() {
        String email, contrasena, nombre, apellido, fechaNacimiento;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atención");
        builder.setPositiveButton("OK", null);
        email = txtCorreo.getText().toString();
        if (!txtNombre.getText().toString().equals("") && !txtApellido.getText().toString().equals("") && !txtCorreo.getText().toString().equals("") && !txtFechaNacimiento.getText().toString().equals("") && !txtContrasena.getText().toString().equals("") && !txtConfirmarContrasena.getText().toString().equals("")) {
            if (email.equals("")) Log.d("es null", "es null");
            int id = conectorDB.checkUser(email);
            if (id > 0) {
                builder.setMessage("Ya hay un usuario registado con el correo " + email);
                builder.create();
                builder.show();
                return null;
            } else {
                if ((txtContrasena.getText().toString().trim().length() > 0) &&
                        (txtConfirmarContrasena.getText().toString().trim().length() > 0)) {
                    if (!txtContrasena.getText().toString().equals(txtConfirmarContrasena.getText().toString())) {
                        builder.setMessage("Las contraseñas no coinciden");
                        builder.create();
                        builder.show();
                        return null;
                    } else {
                        fechaNacimiento = txtFechaNacimiento.getText().toString();
                        contrasena = txtContrasena.getText().toString();
                        nombre = txtNombre.getText().toString();
                        apellido = txtApellido.getText().toString();

                        Usuario usuario = new Usuario(email, contrasena, nombre, apellido, fechaNacimiento, "user1");
                        conectorDB.insertarUsuario(usuario);
                        return usuario;
                    }
                }
            }
        } else if (txtNombre.getText().toString().equals("")) {
            Toast.makeText(this, "Rellena el nombre.", Toast.LENGTH_LONG).show();
            //El teclado se abrirá automáticamente con las 3 líneas de abajo.
            txtNombre.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(txtNombre, InputMethodManager.SHOW_IMPLICIT);
            return null;
        } else if (txtApellido.getText().toString().equals("")) {
            Toast.makeText(this, "Escribe tu primer apellido.", Toast.LENGTH_LONG).show();
            //El teclado se abrirá automáticamente con las 3 líneas de abajo.
            txtApellido.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(txtApellido, InputMethodManager.SHOW_IMPLICIT);
            return null;
        } else if (txtFechaNacimiento.getText().toString().equals("")) {
            Toast.makeText(this, "Escribe tu fecha de nacimiento.", Toast.LENGTH_LONG).show();
            //El teclado se abrirá automáticamente con las 3 líneas de abajo.
            txtFechaNacimiento.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(txtFechaNacimiento, InputMethodManager.SHOW_IMPLICIT);
            return null;
        } else if (txtCorreo.getText().toString().equals("")) {
            Toast.makeText(this, "Escribe una dirección de correo electrónico.", Toast.LENGTH_LONG).show();
            //El teclado se abrirá automáticamente con las 3 líneas de abajo.
            txtCorreo.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(txtCorreo, InputMethodManager.SHOW_IMPLICIT);
            return null;
        } else if (txtContrasena.getText().toString().equals("")) {
            Toast.makeText(this, "Escribe una contraseña", Toast.LENGTH_LONG).show();
            //El teclado se abrirá automáticamente con las 3 líneas de abajo.
            txtContrasena.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(txtContrasena, InputMethodManager.SHOW_IMPLICIT);
            return null;
        } else if (txtConfirmarContrasena.getText().toString().equals("")) {
            Toast.makeText(this, "Confirma la contrseña", Toast.LENGTH_LONG).show();
            //El teclado se abrirá automáticamente con las 3 líneas de abajo.
            txtConfirmarContrasena.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(txtConfirmarContrasena, InputMethodManager.SHOW_IMPLICIT);
            return null;
        } else {
            Toast.makeText(this, "Todos los campos son obligatorios, por favor, rellénalos.", Toast.LENGTH_LONG).show();
            return null;
        }
        return null;
    }
}