package com.curso.androidt.contextmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends Activity {
    ListView listViewTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewTareas = (ListView) findViewById(R.id.tareasListView);

        //String[] version
        //String[] datos = new String[]{"Comer", "Dormir", "..."};
        //ListAdapter la = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);


        List<Tarea> listTareas = new ArrayList<Tarea>();
        listTareas.add(new Tarea("Tarea 1", "Esta es la tarea 1", 10, new Date()));
        listTareas.add(new Tarea("Tarea 2", "Esta es la tarea 2", 20, new Date()));
        listTareas.add(new Tarea("Tarea 3", "Esta es la tarea 3", 30, new Date()));
        listTareas.add(new Tarea("Tarea 4", "Esta es la tarea 4", 40, new Date()));

        ListAdapter listTareaAdapter = new TareasAdapter(listTareas, R.layout.tarea_list_item, this);


        listViewTareas.setAdapter(listTareaAdapter);

        registerForContextMenu(listViewTareas);
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

    //Context Menu

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.tareasListView) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            int position = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
            //Con listViewTareas como atributo de clase ya es necesaria esta linea
            //Tarea tarea = (Tarea) ((AdapterView) v).getAdapter().getItem(position);
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
                //Con listViewTareas como atributo de clase ya es necesaria esta linea
                //Tarea tarea = (Tarea)((AdapterView)item.getActionView()).getAdapter().getItem(position);
                Tarea tarea = (Tarea)listViewTareas.getAdapter().getItem(position);
                Toast.makeText(this, "Item pulsado " + tarea.getTitulo(), Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onContextItemSelected(item);
    }
}
