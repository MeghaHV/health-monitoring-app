package com.example.healthmonitoringapp;

import android.provider.BaseColumns;

public final class DatabaseContract {
    private DatabaseContract() {}

    public static class PatientEntry implements BaseColumns {
        public static final String TABLE_NAME = "patient";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_BLOOD_PRESSURE = "blood_pressure";
        public static final String COLUMN_BLOOD_GROUP = "blood_group";
        public static final String COLUMN_GLUCOSE_LEVEL = "glucose_level";
    }
}
