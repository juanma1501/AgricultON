package com.example.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class grid_item extends AppCompatActivity {

    private TextView txtTitulo;
    private TextView txtDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_item);

        txtTitulo = findViewById(R.id.txtTitulo);
        txtDescripcion = findViewById((R.id.txtDescripcion));

        Intent intent = getIntent();
        String titulo =intent.getStringExtra("nombre");
        String descripcion =intent.getStringExtra("descripcion");

        txtTitulo.setText(titulo);
        txtDescripcion.setText(descripcion);

        //enable back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}