package com.curso.androidt.contextmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by androidt on 13/05/2015.
 */
public class TareasAdapter extends BaseAdapter {


    private List<Tarea> datos;
    private int resLayout;
    private Context context;

    public TareasAdapter(List<Tarea> datos, int resLayout, Context context) {
        this.datos = datos;
        this.resLayout = resLayout;
        this.context = context;
    }

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
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(resLayout, parent, false);
        }

        Tarea item = getDatos().get(position);

        TextView txtTitulo = (TextView) convertView.findViewById(R.id.txtTitulo);
        TextView txtDescripcion = (TextView) convertView.findViewById(R.id.txtDescripcion);
        TextView txtPrioridad = (TextView) convertView.findViewById(R.id.txtPrioridad);
        TextView txtFecha = (TextView) convertView.findViewById(R.id.txtFecha);

        txtTitulo.setText(item.getTitulo());
        txtDescripcion.setText(item.getDescripcion());
        txtPrioridad.setText(String.valueOf(item.getPrioridad()));
        txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(item.getFecha()));

        return convertView;
    }

    public List<Tarea> getDatos() {
        return datos;
    }

    public void setDatos(List<Tarea> datos) {
        this.datos = datos;
    }
}
