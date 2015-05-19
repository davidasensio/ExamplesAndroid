package com.curso.androidt.asynctask;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by androidt on 19/05/2015.
 */
public class DescargaImagen extends AsyncTask<String, Integer, Bitmap> {

    public final static String URL_PRUEBA_1 = "http://miriadna.com/desctopwalls/images/max/Rock-climber.jpg";
    public final static String URL_PRUEBA_2 = "https://moh4m.files.wordpress.com/2012/06/climber.jpg";

    private ImageView imageView;
    private ProgressDialog progressDialog;

    public DescargaImagen(ImageView imageView, ProgressDialog progressDialog) {
        this.imageView = imageView;
        this.progressDialog = progressDialog;
    }

    //Metodo que se ejecuta en otro hilo
    @Override
    protected Bitmap doInBackground(String... params) {
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            URL url = new URL(params[0]);
            URLConnection conn = url.openConnection();
            is = conn.getInputStream();

            int resourceSize = conn.getContentLength();

            byte[] buffer = new byte[512];
            byte[] resuource = new byte[resourceSize];

            for (int bytesTotalesLeidos = 0;bytesTotalesLeidos < resourceSize;) {
                int bytesLeidos = is.read(buffer);

                System.arraycopy(buffer,0,resuource,bytesTotalesLeidos,bytesLeidos);
                bytesTotalesLeidos += bytesLeidos;

                publishProgress((bytesTotalesLeidos * 100 / resourceSize));
            }

            bitmap = BitmapFactory.decodeByteArray(resuource, 0, resourceSize);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }

    //Metodos que se ejecutan en el hilo principal
    @Override
    protected void onPreExecute() { //Sirve para inicializar lo que haga falta
        super.onPreExecute();

        //Inicializar progress dialog
        progressDialog.setMax(100);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Cargando imagen...");
        progressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressDialog.setProgress(values[0]);



    }

    @Override
    protected void onPostExecute(Bitmap bitmap) { //devuelve lo que devuelva doInBackground
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
        progressDialog.hide();
    }


}
