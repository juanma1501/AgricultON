package com.example.Presentacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import Dominio.Usuario;

public class PaginaPrincipal extends AppCompatActivity {

    private BottomNavigationView navigation;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal);

        Bundle bundle=getIntent().getExtras();
        this.usuario = (Usuario) bundle.getSerializable("usuario");

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(navListener);
        lanzarPerfilFragment();

    }

    private void lanzarPerfilFragment(){
        Fragment fragment;
        Bundle bundle;
        fragment = new perfil_();
        bundle =  new Bundle();
        bundle.putSerializable("usuario",usuario);
        fragment.setArguments(bundle);
        loadFragment(fragment);
    }

    private void lanzarCamposFragment(){
        Fragment fragment;
        Bundle bundle;
        fragment = new campos();
        bundle =  new Bundle();
        bundle.putSerializable("usuario",usuario);
        fragment.setArguments(bundle);
        loadFragment(fragment);
    }

    private void lanzarCultivosFragment(){
        Fragment fragment;
        Bundle bundle;
        fragment = new cultivos();
        bundle =  new Bundle();
        bundle.putSerializable("usuario",usuario);
        fragment.setArguments(bundle);
        loadFragment(fragment);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.btnPerfil:
                    lanzarPerfilFragment();
                    return true;
                case R.id.btnCampos:
                    lanzarCultivosFragment();
                    return true;
                case R.id.btnNoticias:
                    lanzarCamposFragment();
                    return true;
            }
            return false;
        }
    };

    //Método para desplegar menú overflow
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    //Método para asignar las funciones correspondientes a las opciones.
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.btnConfiguracion){
            Toast.makeText(PaginaPrincipal.this,R.string.noimplementada,Toast.LENGTH_SHORT).show();
        } else if (id == R.id.btnCerrarSesion){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.btnAcercaDe){
            Intent intent = new Intent(this, acercaDe.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}