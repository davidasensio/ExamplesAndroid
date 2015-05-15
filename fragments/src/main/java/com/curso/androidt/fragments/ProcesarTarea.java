package com.curso.androidt.fragments;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by androidt on 15/05/2015.
 */
public class ProcesarTarea {

    private Activity activity;

    public ProcesarTarea(Activity activity) {
        this.activity = activity;
    }

    public void procesar(Tarea tarea) {
        //Discriminar si estamos en SmartPhone o en Tablet
        DetalleFragment fragmentDetalle = (DetalleFragment) activity.getFragmentManager().findFragmentById(R.id.fragment3);

        if (fragmentDetalle != null) {
            fragmentDetalle.updateByTarea(tarea);
        }else {
            Intent intent = new Intent(activity, DetalleActivity.class);
            intent.putExtra("tarea", tarea);
            activity.startActivity(intent);
        }
    }
}
