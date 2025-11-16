package com.hosp.records;

public class OutPatient extends Patient {
    private static final double CONSULT_FEE = 500.0;

    public OutPatient(String id, String name, int age, String diagnosis, String admissionDate) {
        super(id, name, age, diagnosis, admissionDate);
    }

    @Override
    public double calculateCost() {
        return CONSULT_FEE;
    }

    @Override
    public String toCSV() {
        // roomNo and daysAdmitted empty for outpatient
        return String.join(",", getId(), getName(), String.valueOf(getAge()), "OUT",
                getDiagnosis(), getAdmissionDate(), "", "");
    }

    @Override
    public String toString() {
        return "OutPatient{" + getId() + ", " + getName() + "}";
    }
}
