package com.curso.androidt.viewadapter;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listViewTareas = (ListView) findViewById(R.id.tareasListView);

        //String[] version
        //String[] datos = new String[]{"Comer", "Dormir", "..."};
        //ListAdapter la = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);


        List<Tarea> listTareas = new ArrayList<Tarea>();
        listTareas.add(new Tarea("Tarea 1", "Esta es la tarea 1", 10, new Date()));
        listTareas.add(new Tarea("Tarea 2", "Esta es la tarea 2", 20, new Date()));
        listTareas.add(new Tarea("Tarea 3", "Esta es la tarea 3", 30, new Date()));
        listTareas.add(new Tarea("Tarea 4", "Esta es la tarea 4", 40, new Date()));

        ListAdapter listTareaAdapter = new TareasAdapter(listTareas,R.layout.tarea_list_item,this);


        listViewTareas.setAdapter(listTareaAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
