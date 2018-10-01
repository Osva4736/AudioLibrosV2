package com.osvaldovillalobosperez.audiolibrosv2.fragments;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.osvaldovillalobosperez.audiolibrosv2.AdaptadorLibros;
import com.osvaldovillalobosperez.audiolibrosv2.Aplicacion;
import com.osvaldovillalobosperez.audiolibrosv2.Libro;
import com.osvaldovillalobosperez.audiolibrosv2.MainActivity;
import com.osvaldovillalobosperez.audiolibrosv2.R;

import java.util.Vector;

public class SelectorFragment extends Fragment


{

    private Activity actividad;
    private RecyclerView recyclerView;
    private AdaptadorLibros adaptador;
    private Vector<Libro> vectorLibros;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            this.actividad = (Activity) context;
            Aplicacion app = (Aplicacion) actividad.getApplication();
            adaptador = app.getAdaptador();
            vectorLibros = app.getVectorLibros();

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_selector,
                container, false);
        recyclerView = (RecyclerView) vista.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(actividad,2));
        recyclerView.setAdapter(adaptador);
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(actividad, "Seleccionado el elemento: "
                                + recyclerView.getChildAdapterPosition(v),
                        Toast.LENGTH_SHORT).show();
                ((MainActivity) actividad).mostrarDetalle(
                        recyclerView.getChildAdapterPosition(v));
            }
        });
        return vista;
    }

}
