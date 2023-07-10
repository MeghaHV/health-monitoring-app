package com.example.healthmonitoringapp;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "health_monitoring.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DatabaseContract.PatientEntry.TABLE_NAME + " (" +
                    DatabaseContract.PatientEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DatabaseContract.PatientEntry.COLUMN_NAME + " TEXT," +
                    DatabaseContract.PatientEntry.COLUMN_AGE + " TEXT," +
                    DatabaseContract.PatientEntry.COLUMN_BLOOD_PRESSURE + " TEXT," +
                    DatabaseContract.PatientEntry.COLUMN_BLOOD_GROUP + " TEXT," +
                    DatabaseContract.PatientEntry.COLUMN_GLUCOSE_LEVEL + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DatabaseContract.PatientEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
