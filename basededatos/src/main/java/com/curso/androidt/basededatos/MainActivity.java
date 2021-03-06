package com.curso.androidt.basededatos;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Date;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TerremotosSQLiteOpenHelper helperDB = new TerremotosSQLiteOpenHelper(this,"TerremotosDB", null, getResources().getInteger(R.integer.database_version));
        SQLiteDatabase db = helperDB.getWritableDatabase();
        TerremotoDaoImpl terremotoDao = new TerremotoDaoImpl(db);

        Terremoto terremoto = new Terremoto(
                "Uno",
                "Terrmoto 1",
                "",
                5.5f,
                new Date(),
                33.5f,
                0.12f
        );
        terremotoDao.insertar(terremoto);

        Terremoto uno = terremotoDao.consultar("Uno");
        Toast.makeText(this, uno.toString(), Toast.LENGTH_LONG).show();
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
