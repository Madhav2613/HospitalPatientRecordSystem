package com.hosp.records;

public abstract class Patient {
    private String id;
    private String name;
    private int age;
    private String diagnosis;
    private String admissionDate; // simple string for demo

    public Patient(String id, String name, int age, String diagnosis, String admissionDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.diagnosis = diagnosis;
        this.admissionDate = admissionDate;
    }

    // Encapsulation: private fields + getters/setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getAdmissionDate() { return admissionDate; }
    public void setAdmissionDate(String admissionDate) { this.admissionDate = admissionDate; }

    // Polymorphism: each patient type calculates cost differently
    public abstract double calculateCost();

    // CSV helper for file I/O
    public abstract String toCSV();
}
