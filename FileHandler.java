package com.hosp.io;

import com.hosp.records.*;
import com.hosp.staff.Doctor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    // Save patients array to CSV file
    public static void savePatients(Patient[] patients, int count, String path) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write("id,name,age,type,diagnosis,admissionDate,roomNo,daysAdmitted");
            bw.newLine();
            for (int i = 0; i < count; i++) {
                if (patients[i] != null) {
                    bw.write(patients[i].toCSV());
                    bw.newLine();
                }
            }
        }
    }

    // Load patients from CSV into list (caller should convert to array)
    public static List<Patient> loadPatients(String path) throws IOException {
        List<Patient> list = new ArrayList<>();
        File f = new File(path);
        if (!f.exists()) return list;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String header = br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",", -1); // keep empty fields
                String id = tokens[0], name = tokens[1], ageS = tokens[2], type = tokens[3];
                String diagnosis = tokens[4], admissionDate = tokens[5];
                int age = ageS.isEmpty() ? 0 : Integer.parseInt(ageS);
                if ("IN".equalsIgnoreCase(type)) {
                    int room = tokens[6].isEmpty() ? 0 : Integer.parseInt(tokens[6]);
                    int days = tokens[7].isEmpty() ? 0 : Integer.parseInt(tokens[7]);
                    list.add(new InPatient(id, name, age, diagnosis, admissionDate, room, days));
                } else {
                    list.add(new OutPatient(id, name, age, diagnosis, admissionDate));
                }
            }
        }
        return list;
    }

    public static void saveDoctors(Doctor[] doctors, int count, String path) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write("id,name,specialization,available");
            bw.newLine();
            for (int i = 0; i < count; i++) {
                if (doctors[i] != null) {
                    bw.write(doctors[i].toCSV());
                    bw.newLine();
                }
            }
        }
    }

    public static List<Doctor> loadDoctors(String path) throws IOException {
        List<Doctor> list = new ArrayList<>();
        File f = new File(path);
        if (!f.exists()) return list;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] t = line.split(",", -1);
                String id = t[0], name = t[1], spec = t[2];
                boolean avail = t.length > 3 && "1".equals(t[3]);
                list.add(new Doctor(id, name, spec, avail));
            }
        }
        return list;
    }

    public static void savePrescriptions(List<String> csvLines, String path) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write("patientId,medication,dosage,instructions,date");
            bw.newLine();
            for (String line : csvLines) {
                bw.write(line);
                bw.newLine();
            }
        }
    }
}
