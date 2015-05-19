package com.curso.androidt.parserxml;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Xml;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by androidt on 19/05/2015.
 */
public class DownloadXmlQuakes extends AsyncTask<String, Void, List<Quake>> {

    public final static String URL_QUAKES = "http://miriadna.com/desctopwalls/images/max/Rock-climber.jpg";

    private ListView listViewQuakes;
    private ProgressDialog progressDialog;

    public DownloadXmlQuakes(ListView listViewQuakes, ProgressDialog progressDialog) {
        this.listViewQuakes = listViewQuakes;
        this.progressDialog = progressDialog;
    }

    //Metodo que se ejecuta en otro hilo
    @Override
    protected List<Quake> doInBackground(String... params) {
        InputStream is = null;
        List<Quake> listQuakes = null;
        try {
            URL url = new URL(params[0]);
            URLConnection conn = url.openConnection();
            is = conn.getInputStream();

            return parseXml(is); //Como minimo se devuelve una lista vacía


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
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
        return null; //Una forma de decir que ha habido error
    }

    //Metodos que se ejecutan en el hilo principal
    @Override
    protected void onPreExecute() { //Sirve para inicializar lo que haga falta
        super.onPreExecute();

        //Inicializar progress dialog
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Downloading and parsing quakes...");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(List<Quake> quakes) { //devuelve lo que devuelva doInBackground
        super.onPostExecute(quakes);

        if (quakes != null) {
            //Insert in BBDD or ListView
            ((ArrayAdapter<Quake>)listViewQuakes.getAdapter()).addAll(quakes);

            //Repaint the listView
            ((ArrayAdapter<Quake>)listViewQuakes.getAdapter()).notifyDataSetChanged();
        }
        progressDialog.hide();
    }


    private List<Quake> parseXml(InputStream is) throws XmlPullParserException {
        LinkedList<Quake> result = new LinkedList<>();

        XmlPullParser parser = Xml.newPullParser();
        int event = parser.getEventType();

        while(event != XmlPullParser.END_DOCUMENT) {

        }


        return result;
    }


}
