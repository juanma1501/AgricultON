package com.example.Presentacion;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Dominio.Paso;
import Dominio.Usuario;
import Persistencia.ConectorDB;
import de.hdodenhof.circleimageview.CircleImageView;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class perfil_ extends Fragment {

    private Usuario usuario;

    private TextView lbl_user_name;
    private TextView lblCorreo;
    private TextView lblFechaNacimiento;
    private TextView lblUltimoAcceso;
    private ImageButton btnEditar;
    private Button btnIntroducirPasos;
    private CircleImageView profile_image;
    private LineChartView chartHistorial;
    private ConectorDB conectorDB;


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

        //Conexión a la BBDD
        conectorDB = new ConectorDB(getActivity().getApplicationContext());

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

        btnIntroducirPasos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Coger la última fecha y generar otra que sea 3 días después y si es nula coger la de hoy dd/mm/aa
                ArrayList<Paso> aux = usuario.getListaPasos();
                Paso paso = aux.get(aux.size() - 1);
                String fecha = paso.getFecha();
                String dia = fecha.substring(0,2);
                Log.d("dia", "El dia es " + dia);
                String fechaNueva;
                int diaNuevo = Integer.parseInt(dia);


                if (diaNuevo + 3 > 30) {
                    diaNuevo = 1;
                    int end = fecha.length();
                    fechaNueva = "0" + diaNuevo + fecha.substring(2, end);
                }else if(diaNuevo + 3 < 10) {
                    diaNuevo+=3;
                    int end = fecha.length();
                    fechaNueva = "0" + diaNuevo + fecha.substring(2, end);

                }else{
                    diaNuevo = diaNuevo + 3;
                    int end = fecha.length();
                    fechaNueva = diaNuevo + fecha.substring(2, end);
                }

                Paso p = new Paso(usuario.getIdUser(), (int)(Math.random()*(10000-1500+1)), fechaNueva);
                conectorDB.insertarPaso(p);
                usuario.nuevoPaso(p);
                Log.d("La fecha nueva es: ", "es"+ fechaNueva);
                drawChart();

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
        btnIntroducirPasos = view.findViewById(R.id.btnAnadirPasos);
    }

    private void drawChart(){

        //https://www.codingdemos.com/draw-android-line-chart/
        ArrayList<Paso> pasos = usuario.getListaPasos();
        Log.d("DEBUG_Pasos", pasos.size()+" pasos");

        int[] yAxisData = {0, 5, 10, 15, 20, 25, 30, 35, 40, 45};

        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();


        Line line = new Line(yAxisValues).setColor(getResources().getColor(R.color.colorTitulos));

        //add Axis and Y-Axis data inside yAxisValues and axisValues lists
        for (int i = 0; i < pasos.size(); i++) {
            axisValues.add(i, new AxisValue(i).setLabel(pasos.get(i).getFecha()));
        }

        for (int i = 0; i < pasos.size(); i++) {
            Log.d("DEBUG_Pasos", "Pasos "+i+": "+pasos.get(i).getPasos());

            yAxisValues.add(new PointValue(i, pasos.get(i).getPasos()));
        }

        List lines = new ArrayList();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        axis.setTextSize(13);
        axis.setTextColor(getResources().getColor(R.color.colorAccent));
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        yAxis.setName("Pasos");
        yAxis.setTextColor(getResources().getColor(R.color.colorAccent));
        yAxis.setTextSize(13);
        data.setAxisYLeft(yAxis);

        chartHistorial.setLineChartData(data);
        Viewport viewport = new Viewport(chartHistorial.getMaximumViewport());

        int max = 0;

        for (int i = 0; i < pasos.size(); i++) {
            if(usuario.getListaPasos().get(i).getPasos() >= max){
                max = usuario.getListaPasos().get(i).getPasos();
            }

        }

        viewport.top = max + 5;
        viewport.bottom = 0;
        chartHistorial.setMaximumViewport(viewport);
        chartHistorial.setCurrentViewport(viewport);

    }
}