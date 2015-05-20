package com.curso.androidt.basededatos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by androidt on 20/05/2015.
 */
public class TerremotoDaoImpl implements Dao<String, Terremoto>, TerremotoDao {

    private static final String TABLA = "TERREMOTOS";
    private static final String CAMPO_TITULO = "TITULO";
    private static final String CAMPO_ID = "ID";
    private static final String CAMPO_LINK = "LINK";
    private static final String CAMPO_FECHA = "FECHA";
    private static final String CAMPO_MAGNITUD = "MAGNITUD";
    private static final String CAMPO_LATITUD = "LATITUD";
    private static final String CAMPO_LONGITUD = "LONGITUD";

    private SQLiteDatabase db;

    public TerremotoDaoImpl(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;
    }

    @Override
    public void insertar(Terremoto entidad) {
        db.insert(TABLA, CAMPO_TITULO, terremotoToContentValues(entidad));
    }


    @Override
    public void borrar(Terremoto entidad) {
        String whereClause = CAMPO_ID + " = ?";
        String[] whereArgs = new String[]{entidad.getId()};
        db.delete(TABLA, whereClause, whereArgs);
    }

    @Override
    public void update(Terremoto entidad) {
        String whereClause = CAMPO_ID + " = ?";
        String[] whereArgs = new String[]{entidad.getId()};
        db.update(TABLA, terremotoToContentValues(entidad), whereClause, whereArgs);
    }

    @Override
    public Terremoto consultar(String id) {
        String[] columns = null; //Si null -> todas las columnas
        String whereClause = CAMPO_ID + " = ?";
        String[] whereArgs = new String[]{id};

        Cursor cursor = db.query(TABLA, columns, whereClause, whereArgs, null, null, null);
        return cursorToTerremotos(cursor).get(0);
    }

    @Override
    public List<Terremoto> consultar() {
        String[] columns = null;

        Cursor cursor = db.query(TABLA, columns, null, null, null, null, null);
        List<Terremoto> listTerremotos = cursorToTerremotos(cursor);
        return listTerremotos;
    }

    @Override
    public List<Terremoto> consultar(Float magnitud, Date fecha) {
        String[] columns = null; //Si null -> todas las columnas
        String whereClause = CAMPO_MAGNITUD + " >= ? AND FECHA >= Date(?)";
        String[] whereArgs = new String[]{String.valueOf(magnitud), dateToString(fecha)};

        Cursor cursor = db.query(TABLA, columns, whereClause, whereArgs, null, null, null);
        return cursorToTerremotos(cursor);
    }

    private List<Terremoto> cursorToTerremotos(Cursor cursor) {
        LinkedList<Terremoto> result = new LinkedList<>();
        if (cursor.moveToFirst()) {
            do {
                Terremoto terremoto = new Terremoto(
                        cursor.getString(cursor.getColumnIndex(CAMPO_ID)),
                        cursor.getString(cursor.getColumnIndex(CAMPO_TITULO)),
                        cursor.getString(cursor.getColumnIndex(CAMPO_LINK)),
                        cursor.getFloat(cursor.getColumnIndex(CAMPO_MAGNITUD)),
                        stringToDate(cursor.getString(cursor.getColumnIndex(CAMPO_FECHA))),
                        cursor.getFloat(cursor.getColumnIndex(CAMPO_LATITUD)),
                        cursor.getFloat(cursor.getColumnIndex(CAMPO_LONGITUD))
                );
                result.add(terremoto);
            } while (cursor.moveToNext());
        }
        return result;
    }

    private ContentValues terremotoToContentValues(Terremoto entidad) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CAMPO_ID, entidad.getId());
        contentValues.put(CAMPO_TITULO, entidad.getTitle());
        contentValues.put(CAMPO_LINK, entidad.getLink());
        contentValues.put(CAMPO_FECHA, dateToString(entidad.getDate()));
        contentValues.put(CAMPO_MAGNITUD, entidad.getMagnitude());
        contentValues.put(CAMPO_LATITUD, entidad.getLatitude());
        contentValues.put(CAMPO_LONGITUD, entidad.getLongitude());

        return contentValues;
    }

    private String dateToString(Date fecha) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(fecha);
    }

    private Date stringToDate(String fecha) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
