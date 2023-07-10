package com.example.healthmonitoringapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PatientTableActivity extends AppCompatActivity {
    private TableLayout patientTable;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_table);

        patientTable = findViewById(R.id.patientTable);
        databaseHelper = new DatabaseHelper(this);

        displayPatientData();
    }

    private void displayPatientData() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String[] projection = {
                DatabaseContract.PatientEntry.COLUMN_NAME,
                DatabaseContract.PatientEntry.COLUMN_AGE,
                DatabaseContract.PatientEntry.COLUMN_BLOOD_PRESSURE,
                DatabaseContract.PatientEntry.COLUMN_BLOOD_GROUP,
                DatabaseContract.PatientEntry.COLUMN_GLUCOSE_LEVEL
        };

        Cursor cursor = db.query(
                DatabaseContract.PatientEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.PatientEntry.COLUMN_NAME));
            String age = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.PatientEntry.COLUMN_AGE));
            String bloodPressure = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.PatientEntry.COLUMN_BLOOD_PRESSURE));
            String bloodGroup = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.PatientEntry.COLUMN_BLOOD_GROUP));
            String glucoseLevel = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.PatientEntry.COLUMN_GLUCOSE_LEVEL));

            TableRow row = new TableRow(this);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(layoutParams);
            // Set the background drawable for the table row
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                row.setBackground(getResources().getDrawable(R.drawable.table_row_border));
            } else {
                row.setBackgroundDrawable(getResources().getDrawable(R.drawable.table_row_border));
            }



            TextView nameTextView = new TextView(this);
            nameTextView.setText(name);
            nameTextView.setPadding(8, 8, 8, 8);
            row.addView(nameTextView);
            // Set the background drawable for the table cell
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                nameTextView.setBackground(getResources().getDrawable(R.drawable.table_cell_border));
            } else {
                nameTextView.setBackgroundDrawable(getResources().getDrawable(R.drawable.table_cell_border));
            }

            TextView ageTextView = new TextView(this);
            ageTextView.setText(age);
            ageTextView.setPadding(8, 8, 8, 8);
            row.addView(ageTextView);

            TextView bloodPressureTextView = new TextView(this);
            bloodPressureTextView.setText(bloodPressure);
            bloodPressureTextView.setPadding(8, 8, 8, 8);
            row.addView(bloodPressureTextView);

            TextView bloodGroupTextView = new TextView(this);
            bloodGroupTextView.setText(bloodGroup);
            bloodGroupTextView.setPadding(8, 8, 8, 8);
            row.addView(bloodGroupTextView);

            TextView glucoseLevelTextView = new TextView(this);
            glucoseLevelTextView.setText(glucoseLevel);
            glucoseLevelTextView.setPadding(8, 8, 8, 8);
            row.addView(glucoseLevelTextView);

            patientTable.addView(row);
        }

        cursor.close();
    }
}
