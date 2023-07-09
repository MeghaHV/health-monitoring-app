package com.example.healthmonitoringapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.healthmonitoringapp.R;
import java.util.List;

public class PatientListAdapter extends BaseAdapter {
    private Context context;
    private List<Patient> patientList;

    public PatientListAdapter(Context context, List<Patient> patientList) {
        this.context = context;
        this.patientList = patientList;
    }

    @Override
    public int getCount() {
        return patientList.size();
    }

    @Override
    public Object getItem(int position) {
        return patientList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.patient_item, parent, false);
        }

        Patient patient = patientList.get(position);

        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        TextView ageTextView = convertView.findViewById(R.id.ageTextView);
        TextView bloodPressureTextView = convertView.findViewById(R.id.bloodPressureTextView);
        TextView bloodGroupTextView = convertView.findViewById(R.id.bloodGroupTextView);
        TextView glucoseLevelTextView = convertView.findViewById(R.id.glucoseLevelTextView);

        nameTextView.setText(patient.getName());
        ageTextView.setText(patient.getAge());
        bloodPressureTextView.setText(patient.getBloodPressure());
        bloodGroupTextView.setText(patient.getBloodGroup());
        glucoseLevelTextView.setText(patient.getGlucoseLevel());

        return convertView;
    }

    public void setData(List<Patient> patientList) {
        this.patientList = patientList;
    }
}
