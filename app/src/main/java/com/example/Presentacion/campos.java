package com.example.Presentacion;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import Dominio.Usuario;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link campos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class campos extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Usuario usuario;


    public campos() {
        // Required empty public constructor
    }
    
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
        View view = inflater.inflate(R.layout.fragment_campos, container, false);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng jaen = new LatLng(38, -3.9);
        mMap.addMarker(new MarkerOptions()
                .position(jaen)
                .title("Campo de fresas en Jaén.").icon(BitmapDescriptorFactory.fromResource(R.drawable.fresa)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(jaen));

        LatLng canarias = new LatLng(28.083439410354543, -16.531918106948382);
        mMap.addMarker(new MarkerOptions()
                .position(canarias)
                .title("Plantación de plataneros en Tenerife.").icon(BitmapDescriptorFactory.fromResource(R.drawable.platanos)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(canarias));

        LatLng logrono = new LatLng(42.30907656340716, -2.3781702636315174);
        mMap.addMarker(new MarkerOptions()
                .position(logrono)
                .title("Viñedo en Hoyo La Covaza, La Rioja.").icon(BitmapDescriptorFactory.fromResource(R.drawable.uvas)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(logrono));

        LatLng valencia = new LatLng(39.64327589080071, -0.3309975213378209);
        mMap.addMarker(new MarkerOptions()
                .position(valencia)
                .title("Naranjos en Sagunto, Valencia.").icon(BitmapDescriptorFactory.fromResource(R.drawable.naranja)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(valencia));

        LatLng Jerte = new LatLng(40.22175897137271, -5.753504197195042);
        mMap.addMarker(new MarkerOptions()
                .position(Jerte)
                .title("Cerezos en Jerte.").icon(BitmapDescriptorFactory.fromResource(R.drawable.cereza)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Jerte));
    }
}