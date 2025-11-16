package com.hosp.records;

public class InPatient extends Patient {
    private int roomNo;
    private int daysAdmitted;
    private static final double ROOM_RATE = 1500.0; // example

    public InPatient(String id, String name, int age, String diagnosis, String admissionDate,
                     int roomNo, int daysAdmitted) {
        super(id, name, age, diagnosis, admissionDate);
        this.roomNo = roomNo;
        this.daysAdmitted = daysAdmitted;
    }

    public int getRoomNo() { return roomNo; }
    public void setRoomNo(int roomNo) { this.roomNo = roomNo; }

    public int getDaysAdmitted() { return daysAdmitted; }
    public void setDaysAdmitted(int daysAdmitted) { this.daysAdmitted = daysAdmitted; }

    @Override
    public double calculateCost() {
        return ROOM_RATE * daysAdmitted; // simplistic
    }

    @Override
    public String toCSV() {
        return String.join(",", getId(), getName(), String.valueOf(getAge()), "IN",
                getDiagnosis(), getAdmissionDate(), String.valueOf(roomNo), String.valueOf(daysAdmitted));
    }

    @Override
    public String toString() {
        return "InPatient{" + getId() + ", " + getName() + ", room=" + roomNo + ", days=" + daysAdmitted + "}";
    }
}
