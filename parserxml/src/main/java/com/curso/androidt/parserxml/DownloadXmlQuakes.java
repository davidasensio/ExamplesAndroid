package com.curso.androidt.parserxml;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by androidt on 19/05/2015.
 */
public class DownloadXmlQuakes extends AsyncTask<String, Void, List<Quake>> {

    private final String LOG_TAG = DownloadXmlQuakes.class.getName();

    public final static String URL_QUAKES = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.atom";

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
            ((ArrayAdapter<Quake>) listViewQuakes.getAdapter()).addAll(quakes);

            //Repaint the listView
            ((ArrayAdapter<Quake>) listViewQuakes.getAdapter()).notifyDataSetChanged();
        }
        progressDialog.hide();
    }


    private List<Quake> parseXml(InputStream is) throws XmlPullParserException, IOException {
        LinkedList<Quake> result = new LinkedList<>();

        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "UTF-8");

        int event = parser.getEventType();

        Quake quake = null;
        while (event != XmlPullParser.END_DOCUMENT) {

            String tag = parser.getName() != null ? parser.getName() :"";

            if (event == XmlPullParser.START_TAG && tag.equals("entry")) {
                quake = new Quake();
            }

            if (quake != null) {


                if (tag.equals("id") && event == XmlPullParser.START_TAG) {
                    quake.setId(parser.nextText());
                }

                if (tag.equals("title") && event == XmlPullParser.START_TAG) {
                    quake.setTitle(parser.nextText());
                }

                if (tag.equals("updated") && event == XmlPullParser.START_TAG) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                    try {
                        quake.setDate(simpleDateFormat.parse(parser.nextText()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if (tag.equals("link") && event == XmlPullParser.START_TAG) {
                    quake.setLink(parser.nextText());
                }

                if (tag.equals("point") && event == XmlPullParser.START_TAG) {
                    String[] coord = parser.nextText().split(" ");
                    quake.setLatitude(Float.valueOf(coord[0]));
                    quake.setLongitude(Float.valueOf(coord[1]));
                }


                if (tag.equals("category") && parser.getAttributeValue(null, "label").equals("Magnitude")) {
                    String magnitud = parser.getAttributeValue(null, "term");
                    quake.setMagnitude(Float.valueOf(magnitud.split(" ")[1]));

                }


                result.add(quake);
            }

            if (event == XmlPullParser.END_TAG  && tag.equals("entry")) {
                if (quake != null) {
                    result.add(quake);
                }
            }

            event = parser.next();
        }

        return result;
    }


}
