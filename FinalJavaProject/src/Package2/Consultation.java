package Package2;

import Package1.Doctor;
import Package1.Patient;

import java.io.Serializable;
import java.util.Date;

import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Consultation implements Serializable {
    private LocalDate consulDate;
    private LocalTime consultTime;
    private int noOfHours;
    private Double cost;
    private String notes;

    private ImageIcon image;
    private Doctor doctor;
    private Patient patient;

    private int consultationId;
    private static int idCounter = 100;
    public Consultation(LocalDate consulDate, LocalTime consultTime, int noOfHours, Double cost, String notes, ImageIcon image, Doctor doctor, Patient patient) {

        this.consulDate = consulDate;
        this.consultTime = consultTime;
        this.noOfHours = noOfHours;
        this.cost = cost;
        this.notes = notes;
        this.image = image;
        this.doctor = doctor;
        this.patient = patient;
        idCounter++;
        this.consultationId = idCounter;
        System.out.println("added success id is = " + consultationId);
    }

    public LocalDate getConsulDate() {return consulDate;}

    public void setConsulDate(LocalDate consulDate) {this.consulDate = consulDate;}

    public LocalTime getConsultTime() {return consultTime;}

    public void setConsultTime(LocalTime consultTime) {this.consultTime = consultTime;}

    public Double getCost() {return cost;}

    public void setCost(Double cost) {this.cost = cost;}

    public String getNotes() {return notes;}

    public void setNotes(String notes) {this.notes = notes;}

    public Patient getPatient(){return  patient;}

    public int getConsultationId() {
        return consultationId;
    }

    public String [] toStringArray() {
        return new String[] {"Consultation ID ='" + consultationId,
                "Doctor Name =" + doctor.getName(),
                "Patient Name =" + patient.getName(),
                "Date =" + consulDate.getYear() + "." + consulDate.getMonth() + "." + "." +consulDate.getDayOfMonth(),
                "Time =" + consultTime.getHour() + "h" + consultTime.getMinute() + "min",
                "No Of Hours =" + noOfHours,
                "Cost =" + "£" + cost + '\n',
                "Notes =" + notes};
    }

    public ImageIcon getImage() {
        return image;
    }

    public static void setIdCounter(int idCounter) {
        Consultation.idCounter = idCounter;
    }
    public int getDocLi(){
        return doctor.getMedicalLicenceNumber();
    }
    public LocalTime getTime(){
        return consultTime;
    }

    public int getNoOfHours() {
        return noOfHours;
    }

}

