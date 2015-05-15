package com.curso.androidt.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListadoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListadoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListadoFragment extends Fragment {

    ListView listViewTareas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listado, container, false); //False hace que no aplique las caracteristicas del padre
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listViewTareas = (ListView) getActivity().findViewById(R.id.listViewTareas);

        List<Tarea> listTareas = new ArrayList<Tarea>();
        listTareas.add(new Tarea("Tarea 1", "Esta es la tarea 1", 10, new Date()));
        listTareas.add(new Tarea("Tarea 2", "Esta es la  tarea 2", 20, new Date()));
        listTareas.add(new Tarea("Tarea 3", "Esta es la tarea 3", 30, new Date()));
        listTareas.add(new Tarea("Tarea 4", "Esta es la tarea 4", 40, new Date()));

        ListAdapter listTareaAdapter = new TareasAdapter(listTareas, R.layout.tarea_list_item, getActivity());


        listViewTareas.setAdapter(listTareaAdapter);

        listViewTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tarea tarea = (Tarea)listViewTareas.getAdapter().getItem(position);
                updateDetalle(tarea);
            }
        });

        registerForContextMenu(listViewTareas);
    }

    public void updateDetalle(Tarea tarea) {
        //((MainActivity)getActivity()).procesarTarea(tarea);
        new ProcesarTarea(getActivity()).procesar(tarea);
    }

    //Context Menu


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.listViewTareas) {
            getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
            int position = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
            Tarea tarea = (Tarea)listViewTareas.getAdapter().getItem(position);

            menu.setHeaderIcon(android.R.drawable.btn_star);
            menu.setHeaderTitle(tarea.getTitulo());
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                int position = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position;
                Tarea tarea = (Tarea)listViewTareas.getAdapter().getItem(position);
                updateDetalle(tarea);


                //Toast.makeText(this, "Item pulsado " + tarea.getTitulo(), Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onContextItemSelected(item);
    }


}
