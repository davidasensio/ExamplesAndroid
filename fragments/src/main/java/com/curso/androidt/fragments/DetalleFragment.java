package com.curso.androidt.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleFragment extends Fragment {


    public DetalleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_detalle, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent intent = getActivity().getIntent();
        Tarea tarea = (Tarea) intent.getSerializableExtra("tarea");
        if (tarea != null){
            updateByTarea(tarea);
        }
    }

    public void updateByTarea(Tarea tarea) {
        TextView txtTitulo = (TextView) getView().findViewById(R.id.txtFragmentTitulo);
        TextView txtDescripcion = (TextView) getActivity().findViewById(R.id.txtFragmentDescripcion);
        TextView txtPrioridad = (TextView) getActivity().findViewById(R.id.txtFragmentPrioridad);
        TextView txtFecha = (TextView) getActivity().findViewById(R.id.txtFragmentFecha);

        txtTitulo.setText(tarea.getTitulo());
        txtDescripcion.setText(tarea.getDescripcion());
        txtPrioridad.setText(String.valueOf(tarea.getPrioridad()));
        txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(tarea.getFecha()));
    }


}
