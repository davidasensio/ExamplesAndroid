package com.curso.androidt.dialogos;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends Activity {

    DialogoFragment dialogoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Deprecated (Por acoplamiento del dialogo a la actividad). A partir del API de fragmentos, los dialogs se hacen con fragmentos
        //showDialog(1);

        dialogoFragment = new DialogoFragment();
        dialogoFragment.setNegativeButtonListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Se pulsó el No", Toast.LENGTH_LONG).show();
            }
        });
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

            dialogoFragment.show(getFragmentManager(), "miDialogo");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Deprecated
    /*
    @Override
    protected Dialog onCreateDialog(int id) {
        return super.onCreateDialog(id);
    }
    */
}
