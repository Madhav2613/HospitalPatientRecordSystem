package com.hosp.records;

public class Prescription {
    private String patientId;
    private String medication;
    private String dosage;
    private String instructions;
    private String date;

    public Prescription(String patientId, String medication, String dosage, String instructions, String date) {
        this.patientId = patientId;
        this.medication = medication;
        this.dosage = dosage;
        this.instructions = instructions;
        this.date = date;
    }

    public String getPatientId() { return patientId; }
    public String getMedication() { return medication; }
    public String getDosage() { return dosage; }
    public String getInstructions() { return instructions; }
    public String getDate() { return date; }

    public String toCSV() {
        return String.join(",", patientId, medication, dosage, instructions, date);
    }

    @Override
    public String toString() {
        return medication + " (" + dosage + ") - " + instructions + " on " + date;
    }
}
