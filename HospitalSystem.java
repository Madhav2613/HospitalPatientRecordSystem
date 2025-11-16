package com.hosp.system;

import com.hosp.records.*;
import com.hosp.staff.Doctor;
import com.hosp.io.FileHandler;
import com.hosp.exceptions.RecordNotFoundException;
import com.hosp.exceptions.InvalidInputException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HospitalSystem {
    private static final int MAX_PATIENTS = 500;
    private static final int MAX_DOCTORS = 50;

    private Patient[] patients = new Patient[MAX_PATIENTS];
    private int patientCount = 0;

    private Doctor[] doctors = new Doctor[MAX_DOCTORS];
    private int doctorCount = 0;

    private List<String> prescriptionCSVs = new ArrayList<>();

    // Add patient (array usage)
    public void addPatient(Patient p) throws InvalidInputException {
        if (patientCount >= MAX_PATIENTS) throw new InvalidInputException("Patient capacity full");
        patients[patientCount++] = p;
    }

    // Find patient by ID
    public Patient findPatientById(String id) throws RecordNotFoundException {
        for (int i = 0; i < patientCount; i++) {
            if (patients[i] != null && patients[i].getId().equals(id)) return patients[i];
        }
        throw new RecordNotFoundException("Patient not found: " + id);
    }

    public void updatePatientName(String id, String newName) throws RecordNotFoundException {
        Patient p = findPatientById(id);
        p.setName(newName);
    }

    public void deletePatient(String id) throws RecordNotFoundException {
        boolean removed = false;
        for (int i = 0; i < patientCount; i++) {
            if (patients[i] != null && patients[i].getId().equals(id)) {
                // shift left
                for (int j = i; j < patientCount - 1; j++) patients[j] = patients[j + 1];
                patients[--patientCount] = null;
                removed = true;
                break;
            }
        }
        if (!removed) throw new RecordNotFoundException("No such patient to delete: " + id);
    }

    // Doctor management (simple)
    public void addDoctor(Doctor d) { if (doctorCount < doctors.length) doctors[doctorCount++] = d; }

    public Doctor findDoctorById(String id) throws RecordNotFoundException {
        for (int i = 0; i < doctorCount; i++) {
            if (doctors[i] != null && doctors[i].getId().equals(id)) return doctors[i];
        }
        throw new RecordNotFoundException("Doctor not found: " + id);
    }

    public void assignDoctor(String patientId, String doctorId) throws RecordNotFoundException {
        Patient p = findPatientById(patientId);
        Doctor d = findDoctorById(doctorId);
        if (!d.isAvailable()) {
            System.out.println("Doctor is currently not available.");
        } else {
            d.setAvailable(false); // mark busy; you can track assignment map if needed
            System.out.println("Assigned Dr. " + d.getName() + " to patient " + p.getName());
        }
    }

    // Add prescription (objects as arguments)
    public void addPrescription(Prescription pres) throws RecordNotFoundException {
        // check patient exists
        findPatientById(pres.getPatientId());
        prescriptionCSVs.add(pres.toCSV());
    }

    // Generate discharge summary as string
    public String dischargePatient(String id) throws RecordNotFoundException {
        Patient p = findPatientById(id);
        double cost = p.calculateCost();
        String summary = "Discharge Summary for " + p.getName() + " (ID: " + p.getId() + ")\n"
                + "Diagnosis: " + p.getDiagnosis() + "\n"
                + "Admission Date: " + p.getAdmissionDate() + "\n"
                + "Estimated Cost: " + cost + "\n";
        return summary;
    }

    // Save & load using FileHandler
    public void saveAll() {
        try {
            FileHandler.savePatients(patients, patientCount, "patients.csv");
            FileHandler.saveDoctors(doctors, doctorCount, "doctors.csv");
            FileHandler.savePrescriptions(prescriptionCSVs, "prescriptions.csv");
            System.out.println("Saved records to CSV files.");
        } catch (IOException e) {
            System.err.println("Error saving files: " + e.getMessage());
        }
    }

    public void loadAll() {
        try {
            List<com.hosp.records.Patient> list = FileHandler.loadPatients("patients.csv");
            for (int i = 0; i < list.size() && i < patients.length; i++) {
                patients[i] = list.get(i);
            }
            patientCount = Math.min(list.size(), patients.length);

            List<com.hosp.staff.Doctor> dlist = FileHandler.loadDoctors("doctors.csv");
            for (int i = 0; i < dlist.size() && i < doctors.length; i++) {
                doctors[i] = dlist.get(i);
            }
            doctorCount = Math.min(dlist.size(), doctors.length);

            System.out.println("Loaded " + patientCount + " patients and " + doctorCount + " doctors.");
        } catch (IOException e) {
            System.err.println("Error loading files (starting fresh): " + e.getMessage());
        }
    }

    // Utility getters for demo/testing
    public Patient[] getPatientsArray() { return patients; }
    public int getPatientCount() { return patientCount; }
    public Doctor[] getDoctorsArray() { return doctors; }
    public int getDoctorCount() { return doctorCount; }
}
