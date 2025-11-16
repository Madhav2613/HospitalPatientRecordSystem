package com.hosp.staff;

public class Doctor {
    private String id;
    private String name;
    private String specialization;
    private boolean available;

    public Doctor(String id, String name, String specialization, boolean available) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.available = available;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public String toCSV() {
        return String.join(",", id, name, specialization, available ? "1" : "0");
    }

    @Override
    public String toString() {
        return name + " (" + specialization + ") - " + (available ? "Available" : "Busy");
    }
}
