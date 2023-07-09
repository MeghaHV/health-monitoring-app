package com.example.healthmonitoringapp;

public class Patient {
    private int id;
    private String name;
    private String age;
    private String bloodPressure;
    private String bloodGroup;
    private String glucoseLevel;

    public Patient(int id, String name, String age, String bloodPressure, String bloodGroup, String glucoseLevel) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.bloodPressure = bloodPressure;
        this.bloodGroup = bloodGroup;
        this.glucoseLevel = glucoseLevel;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getGlucoseLevel() {
        return glucoseLevel;
    }
}
