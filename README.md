# Hospital Patient Record System (Java Mini Project)

A console-based hospital management system built using **Core Java**, demonstrating:
- Object-Oriented Programming (Encapsulation, Inheritance, Polymorphism)
- Exception Handling
- File I/O (CSV-based storage)
- Packages & Modular Structure
- Arrays of Objects & Objects as Arguments
- Loops and Conditional Statements
- Menu-driven Interface

---

## 🚀 Features

### 👨‍⚕️ Patient Management
- Add **InPatients** and **OutPatients**
- View patient details by ID  
- Update patient name  
- Calculate hospital charges  
- Generate discharge summary  

### 🧑‍🔬 Doctor Management
- Assign doctor to patient  
- Track doctor availability  

### 💊 Prescription Management
- Add prescription to patient  
- Store prescription details persistently  

### 💾 File Handling (CSV)
- Automatically load records on startup  
- Save all changes to CSV files:
  - `patients.csv`
  - `doctors.csv`
  - `prescriptions.csv`

---

## 📂 Project Structure

HospitalProject/
├── src/
│ └── com/hosp/
│ ├── system/
│ ├── records/
│ ├── staff/
│ ├── io/
│ └── exceptions/
└── out/ # Compiled .class files

---

## 🎯 Concepts Used (Matches Mini-Project Requirements)

- **P1: System Interface** → Menu-driven, while loop  
- **P2: Data Persistence** → File I/O (CSV)  
- **P3: Error Handling** → Exception handling (custom exceptions included)  
- **P4: Encapsulation** → Private fields + getters/setters  
- **P5: Inheritance** → `InPatient` and `OutPatient` extend `Patient`  
- **P6: Polymorphism** → Overridden `calculateCost()`  
- **P7: Array of Objects** → Stores doctors & patients  
- **P8: Objects as Arguments** → Passing `Patient`, `Prescription` objects  
- **P9: Conditionals** → All menu operations  
- **P10: Packages** → Fully organized project  

---

## 🏃 How to Run

### 1. Compile
Run this command in project root:

```bash
javac -d out src/com/hosp/**/*.java
