package com.example.admin.bmicalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by admin on 2015/06/23.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "BMICalculator.db";
    static final int DB_VERSION = 1;
    static final String TABLE = "history";
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + "("
                    + "_id integer primary key autoincrement,"
                    + "height double,"
                    + "weight double,"
                    + "bmi double,"
                    + "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ");";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS" + TABLE;

    public MySQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public static void insert(SQLiteDatabase db, double height, double weight, double bmi) {
        ContentValues cv = new ContentValues();

        cv.put("height", height);
        cv.put("weight", weight);
        cv.put("bmi", bmi);

        long id = db.insert("history", null, cv);
        if (id < 0) {
            Log.e("Database", "Insert ERROR");
        } else {
            Log.e("Database", "Insert Succes");
        }
    }
}
