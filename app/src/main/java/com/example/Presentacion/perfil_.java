package com.example.Presentacion;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import Dominio.Usuario;
import de.hdodenhof.circleimageview.CircleImageView;
import lecho.lib.hellocharts.view.LineChartView;

public class perfil_ extends Fragment {

    private Usuario usuario;

    private TextView lbl_user_name;
    private TextView lblCorreo;
    private TextView lblFechaNacimiento;
    private TextView lblUltimoAcceso;
    private ImageButton btnEditar;
    private CircleImageView profile_image;
    private LineChartView chartHistorial;


    public perfil_() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.usuario = (Usuario) getArguments().getSerializable("usuario");
        }else{
            Log.d("es null", "es null");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil_, container, false);

        initViews(view);
        initListeners();
        setValores();

        return view;
    }

    private void initListeners() {
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), R.string.noimplementada, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setValores() {
        //LABELS
        this.lbl_user_name.setText(this.usuario.getNombre() + " " + this.usuario.getApellidos());
        this.lblCorreo.setText(this.usuario.getEmail());
        this.lblUltimoAcceso.setText(this.usuario.getUltimoAcceso());
        this.lblFechaNacimiento.setText(this.usuario.getFechaNacimiento() +
                "\t(" + this.usuario.getEdad() +  " años)");

        //Foto
        if (!usuario.getFoto().equals("")){
            this.profile_image.setImageResource(R.drawable.foto);
        }

        //GRAFICO CALCULOS
        if (usuario.getListaPasos().size()>0){
            drawChart();
        }
    }

    private void initViews(View view) {
        // Inflate the layout for this fragment
        btnEditar = view.findViewById(R.id.btnEditar);
        chartHistorial = view.findViewById(R.id.chartHistorial);
        lbl_user_name= view.findViewById(R.id.lbl_user_name);
        lblCorreo= view.findViewById(R.id.lblCorreo);
        lblFechaNacimiento = view.findViewById(R.id.lblFechaNacimiento);
        lblUltimoAcceso = view.findViewById(R.id.lblUltimoAcceso);
        profile_image = view.findViewById(R.id.profile_image);
    }

    private void drawChart(){
        //https://www.codingdemos.com/draw-android-line-chart/


    }
}