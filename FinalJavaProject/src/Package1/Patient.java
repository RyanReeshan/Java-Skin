package Package1;

import java.io.Serializable;

import java.io.Serializable;
import java.time.LocalDate;

public class Patient extends Person implements Serializable {
    private int patientId;

    public Patient(String name, String surName, LocalDate dateOfBirth, String mobileNumber, int patientId) {
        super(name, surName, dateOfBirth, Integer.parseInt(mobileNumber));
        this.patientId = patientId;
    }

    public int getPatientId() {
        return patientId;
    }
}

