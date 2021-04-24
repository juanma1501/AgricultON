package com.example.Presentacion;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import Dominio.Cultivo;
import Dominio.Usuario;
import Persistencia.ConectorDB;

public class cultivos extends Fragment {

    private GridView gridView;
    private ArrayList<Cultivo> cultivos;
    private FloatingActionButton btnAnadir;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText new_nombre, new_descripcion;
    private Button guardarNew, CancelarNew;
    private Usuario usuario;
    private ConectorDB conectorDB;
    private CoordinatorLayout colay;
    private CustomAdapter customAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.usuario = (Usuario) getArguments().getSerializable("usuario");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cultivos, container, false);


        initViews(view);
        cultivos = conectorDB.leerCultivos();

        customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(), grid_item.class);
                intent.putExtra("nombre", cultivos.get(i).getNombre());
                intent.putExtra("descripcion", cultivos.get(i).getDescripcion());
                startActivity(intent);
            }
        });

        btnAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewCultivoDialog();
            }
        });
        return view;
    }

    private void initViews(View view) {
        gridView = view.findViewById(R.id.gridview);
        btnAnadir = view.findViewById(R.id.btnAnadir);
        conectorDB = new ConectorDB(getActivity());
        colay = view.findViewById(R.id.colay);
    }

    public void createNewCultivoDialog(){
        dialogBuilder = new AlertDialog.Builder(this.getActivity());
        final View newCultivoPopupView = getLayoutInflater().inflate(R.layout.new_cultivo_popup, null);

        new_nombre = newCultivoPopupView.findViewById(R.id.newNombre);
        new_descripcion = newCultivoPopupView.findViewById(R.id.newDescripcion);
        guardarNew = newCultivoPopupView.findViewById(R.id.btnGuardarNew);
        CancelarNew = newCultivoPopupView.findViewById(R.id.btnCancelarNew);

        dialogBuilder.setView(newCultivoPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        guardarNew.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Define save button
                String nombre = new_nombre.getText().toString();
                String descripcion = new_descripcion.getText().toString();
                String foto = "";
                Cultivo c = new Cultivo(nombre, descripcion, foto);
                conectorDB.insertarCultivo(c, cultivos);
                customAdapter = new CustomAdapter();
                gridView.invalidateViews();
                // Snack Bar to show success message that record saved successfully
                Snackbar.make(gridView, getString(R.string.newCultivo), Snackbar.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        CancelarNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return cultivos.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.activity_row_data, null);

            TextView name = view1.findViewById(R.id.productos);
            ImageView image = view1.findViewById(R.id.fotoproductos);
            btnAnadir = view1.findViewById(R.id.btnAnadir);
            //cultivos = conectorDB.leerCultivos();

            name.setText(cultivos.get(i).getNombre());
            String foto = cultivos.get(i).getFoto();
            Log.d("FOTO CULTIVO", "LA FOTO DEL CULTIVO ES "+ foto);

            if (foto.equals("")){
                image.setImageResource(R.drawable.ic_baseline_local_florist_24);
            }else {
                image.setImageResource(getResources().getIdentifier(foto, "drawable", getActivity().getPackageName()));
            }
            return view1;
        }
    }
}