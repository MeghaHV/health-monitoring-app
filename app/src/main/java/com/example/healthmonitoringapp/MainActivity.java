package com.example.healthmonitoringapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText ageEditText;
    private EditText bloodPressureEditText;
    private EditText bloodGroupEditText;
    private EditText glucoseLevelEditText;
    private Button addButton;
    private ListView patientListView;
    private PatientListAdapter patientListAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        nameEditText = findViewById(R.id.nameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        bloodPressureEditText = findViewById(R.id.bloodPressureEditText);
        bloodGroupEditText = findViewById(R.id.bloodGroupEditText);
        glucoseLevelEditText = findViewById(R.id.glucoseLevelEditText);
        addButton = findViewById(R.id.addButton);
        patientListView = findViewById(R.id.patientListView);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Set up the patient list adapter
        List<Patient> patientList = getAllPatients();
        patientListAdapter = new PatientListAdapter(this, patientList);
        patientListView.setAdapter(patientListAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPatient();
            }
        });
    }

    private void addPatient() {
        String name = nameEditText.getText().toString().trim();
        String age = ageEditText.getText().toString().trim();
        String bloodPressure = bloodPressureEditText.getText().toString().trim();
        String bloodGroup = bloodGroupEditText.getText().toString().trim();
        String glucoseLevel = glucoseLevelEditText.getText().toString().trim();

        if (name.isEmpty() || age.isEmpty() || bloodPressure.isEmpty() || bloodGroup.isEmpty() || glucoseLevel.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        long result = databaseHelper.insertPatient(name, age, bloodPressure, bloodGroup, glucoseLevel);

        if (result != -1) {
            // Clear input fields
            nameEditText.getText().clear();
            ageEditText.getText().clear();
            bloodPressureEditText.getText().clear();
            bloodGroupEditText.getText().clear();
            glucoseLevelEditText.getText().clear();

            // Refresh patient list
            List<Patient> patientList = getAllPatients();
            patientListAdapter.setData(patientList);
            patientListAdapter.notifyDataSetChanged();

            Toast.makeText(this, "Patient added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to add patient", Toast.LENGTH_SHORT).show();
        }
    }

    private List<Patient> getAllPatients() {
        List<Patient> patientList = new ArrayList<>();

        Cursor cursor = databaseHelper.getAllPatients();

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
                @SuppressLint("Range") String age = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_AGE));
                @SuppressLint("Range") String bloodPressure = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_BLOOD_PRESSURE));
                @SuppressLint("Range") String bloodGroup = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_BLOOD_GROUP));
                @SuppressLint("Range") String glucoseLevel = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_GLUCOSE_LEVEL));

                Patient patient = new Patient(id, name, age, bloodPressure, bloodGroup, glucoseLevel);
                patientList.add(patient);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return patientList;
    }
}



