package com.example.healthmonitoringapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "health_monitoring.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "patient";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_BLOOD_PRESSURE = "blood_pressure";
    public static final String COLUMN_BLOOD_GROUP = "blood_group";
    public static final String COLUMN_GLUCOSE_LEVEL = "glucose_level";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_AGE + " TEXT," +
                COLUMN_BLOOD_PRESSURE + " TEXT," +
                COLUMN_BLOOD_GROUP + " TEXT," +
                COLUMN_GLUCOSE_LEVEL + " TEXT" +
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }

    public long insertPatient(String name, String age, String bloodPressure, String bloodGroup, String glucoseLevel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AGE, age);
        values.put(COLUMN_BLOOD_PRESSURE, bloodPressure);
        values.put(COLUMN_BLOOD_GROUP, bloodGroup);
        values.put(COLUMN_GLUCOSE_LEVEL, glucoseLevel);

        long result = db.insert(TABLE_NAME, null, values);
        db.close();

        return result;
    }

    public Cursor getAllPatients() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }
}
