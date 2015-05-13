package com.curso.androidt.viewadapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Collection;
import java.util.List;

/**
 * Created by androidt on 13/05/2015.
 */
public class TareasAdapter extends BaseAdapter {


    private List<Tarea> datos;

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return getDatos().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public List<Tarea> getDatos() {
        return datos;
    }

    public void setDatos(List<Tarea> datos) {
        this.datos = datos;
    }
}
