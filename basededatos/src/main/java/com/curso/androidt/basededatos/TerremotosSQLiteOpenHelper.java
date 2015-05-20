package com.curso.androidt.basededatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by androidt on 20/05/2015.
 */
public class TerremotosSQLiteOpenHelper extends SQLiteOpenHelper {

    private Context context;

    public TerremotosSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.beginTransaction();
            execScript(db, R.array.script_create_terremotos);
            db.setTransactionSuccessful(); //No es el commit. Pero marca como transaccion correcta para que se haga el commit en el endTransaction
        } finally {
            db.endTransaction();
        }
    }

    private void execScript(SQLiteDatabase db, int resourceScript) {
        String[] queries = context.getResources().getStringArray(resourceScript);

        for (String query : queries) {
            db.execSQL(query);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Todo el upgrade en una transaccion atomica
        db.beginTransaction();
        try {
            for (int i = oldVersion + 1; i <= newVersion; i++) {

                switch (i) {
                    case 1:
                        break;
                    case 2:
                        execScript(db, R.array.script_create_terremotos_upgrade_v2);
                        break;
                    case 3:
                        execScript(db, R.array.script_create_terremotos_upgrade_v3);
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
}
