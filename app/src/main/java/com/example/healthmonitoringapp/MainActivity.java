package com.example.healthmonitoringapp;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText nameEditText, ageEditText, bloodPressureEditText, bloodGroupEditText, glucoseLevelEditText;
    private Button saveButton, retrieveButton;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        nameEditText = findViewById(R.id.nameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        bloodPressureEditText = findViewById(R.id.bloodPressureEditText);
        bloodGroupEditText = findViewById(R.id.bloodGroupEditText);
        glucoseLevelEditText = findViewById(R.id.glucoseLevelEditText);

        saveButton = findViewById(R.id.saveButton);
        Button viewTableButton = findViewById(R.id.viewTableButton);
        viewTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PatientTableActivity.class);
                startActivity(intent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePatientData();
            }
        });
    }

    private void savePatientData() {
        String name = nameEditText.getText().toString().trim();
        String age = ageEditText.getText().toString().trim();
        String bloodPressure = bloodPressureEditText.getText().toString().trim();
        String bloodGroup = bloodGroupEditText.getText().toString().trim();
        String glucoseLevel = glucoseLevelEditText.getText().toString().trim();

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.PatientEntry.COLUMN_NAME, name);
        values.put(DatabaseContract.PatientEntry.COLUMN_AGE, age);
        values.put(DatabaseContract.PatientEntry.COLUMN_BLOOD_PRESSURE, bloodPressure);
        values.put(DatabaseContract.PatientEntry.COLUMN_BLOOD_GROUP, bloodGroup);
        values.put(DatabaseContract.PatientEntry.COLUMN_GLUCOSE_LEVEL, glucoseLevel);

        long newRowId = db.insert(DatabaseContract.PatientEntry.TABLE_NAME, null, values);
        if (newRowId != -1) {
            Toast.makeText(this, "Patient data saved", Toast.LENGTH_SHORT).show();
            clearInputFields();
        } else {
            Toast.makeText(this, "Failed to save patient data", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearInputFields() {
        nameEditText.setText("");
        ageEditText.setText("");
        bloodPressureEditText.setText("");
        bloodGroupEditText.setText("");
        glucoseLevelEditText.setText("");
    }
}
