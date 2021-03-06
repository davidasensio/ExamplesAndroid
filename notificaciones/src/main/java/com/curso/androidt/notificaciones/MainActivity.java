package com.curso.androidt.notificaciones;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

            Notification.Builder builder = new Notification.Builder(this);

            Intent intent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setTicker("Nueva notificacion") //Texto que se muestra y desaparece en la barra
                    .setContentInfo("content info")
                    .setVibrate(new long[]{1, 3, 1, 3}) //vibracion 1 seg suena, 3 seg para, 1 seg suena...
                    .setContentTitle("Titulo")
                    .setContentText("Cuerpo de la notificacion")
                    .setAutoCancel(false) //Se quita cuando le pinchas
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_btn_speak_now))
                    .setContentIntent(pendingIntent)
            .setProgress(100,0,false)
                //.addPerson()  //Muestra un contacto
            ;
            Notification notification = builder.build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(1, notification);


            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
