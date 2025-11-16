package com.hosp.system;

import com.hosp.records.*;
import com.hosp.staff.Doctor;
import com.hosp.exceptions.RecordNotFoundException;
import com.hosp.exceptions.InvalidInputException;

import java.util.Scanner;

public class MainApp {
    private static HospitalSystem hs = new HospitalSystem();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        hs.loadAll(); // try load existing CSVs
        boolean running = true;
        while (running) {
            printMenu();
            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1": addPatientMenu(); break;
                    case "2": viewPatientById(); break;
                    case "3": updatePatientName(); break;
                    case "4": assignDoctor(); break;
                    case "5": addPrescription(); break;
                    case "6": dischargePatient(); break;
                    case "7": hs.saveAll(); break;
                    case "8": running = false; hs.saveAll(); break;
                    default: System.out.println("Invalid option. Try again."); break;
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        System.out.println("Exiting. Bye!");
    }

    private static void printMenu() {
        System.out.println("\n=== Hospital Menu ===");
        System.out.println("1. Add Patient (In/Out)");
        System.out.println("2. View Patient by ID");
        System.out.println("3. Update Patient Name");
        System.out.println("4. Assign Doctor");
        System.out.println("5. Add Prescription");
        System.out.println("6. Generate Discharge Summary");
        System.out.println("7. Save Records");
        System.out.println("8. Exit");
        System.out.print("Choose option: ");
    }

    private static void addPatientMenu() throws InvalidInputException {
        System.out.print("Type (IN/OUT): ");
        String type = sc.nextLine().trim().toUpperCase();
        System.out.print("ID: "); String id = sc.nextLine().trim();
        System.out.print("Name: "); String name = sc.nextLine().trim();
        System.out.print("Age: "); int age = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Diagnosis: "); String diag = sc.nextLine().trim();
        System.out.print("Admission Date(YYYY-MM-DD): "); String date = sc.nextLine().trim();

        if ("IN".equals(type)) {
            System.out.print("Room no: "); int room = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Days admitted: "); int days = Integer.parseInt(sc.nextLine().trim());
            hs.addPatient(new InPatient(id, name, age, diag, date, room, days));
        } else {
            hs.addPatient(new OutPatient(id, name, age, diag, date));
        }
        System.out.println("Patient added.");
    }

    private static void viewPatientById() {
        try {
            System.out.print("Enter ID: ");
            String id = sc.nextLine().trim();
            Patient p = hs.findPatientById(id);
            System.out.println("Found: " + p);
            System.out.println("Estimated cost: " + p.calculateCost());
        } catch (RecordNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void updatePatientName() {
        try {
            System.out.print("ID: "); String id = sc.nextLine().trim();
            System.out.print("New name: "); String newName = sc.nextLine().trim();
            hs.updatePatientName(id, newName);
            System.out.println("Updated.");
        } catch (RecordNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void assignDoctor() {
        try {
            System.out.print("Patient ID: "); String pid = sc.nextLine().trim();
            System.out.print("Doctor ID: "); String did = sc.nextLine().trim();
            hs.assignDoctor(pid, did);
        } catch (RecordNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void addPrescription() {
        try {
            System.out.print("Patient ID: "); String pid = sc.nextLine().trim();
            System.out.print("Medication: "); String med = sc.nextLine().trim();
            System.out.print("Dosage: "); String dosage = sc.nextLine().trim();
            System.out.print("Instructions: "); String instr = sc.nextLine().trim();
            System.out.print("Date: "); String date = sc.nextLine().trim();
            Prescription p = new Prescription(pid, med, dosage, instr, date);
            hs.addPrescription(p);
            System.out.println("Prescription added.");
        } catch (RecordNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void dischargePatient() {
        try {
            System.out.print("Patient ID: "); String id = sc.nextLine().trim();
            String summary = hs.dischargePatient(id);
            System.out.println(summary);
        } catch (RecordNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
