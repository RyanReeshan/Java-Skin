package Package3;

import Package1.Doctor;
import Package1.Patient;
import Package2.Consultation;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class ConsultationManager {

    private ArrayList<Doctor> docList;
    LinkedList<Consultation> consultations = new LinkedList<>();
    private static int Id = 100;

    public ConsultationManager(ArrayList<Doctor> doctors) {
        this.docList = doctors;
    }

    public int addCons(Doctor doctor, Patient patient, String notes, LocalTime consulTime, int noOfHours, LocalDate consulDate, ImageIcon image) {

        double cost;

        if (isPatientFirstTime(patient.getPatientId())) {
            cost = noOfHours * 25;
        } else {
            cost = noOfHours * 15;
        }

        if (availabilityCheck(consulDate, consulTime, noOfHours, doctor.getMedicalLicenceNumber())) {
            Consultation c = new Consultation(consulDate, consulTime, noOfHours, cost, notes, image, doctor, patient);
            consultations.add(c);
            return c.getConsultationId();
        } else {
            for (Doctor d : docList) {
                if (availabilityCheck(consulDate, consulTime, noOfHours, doctor.getMedicalLicenceNumber())) {
                    Consultation c = new Consultation(consulDate, consulTime, noOfHours, cost, notes, image, doctor, patient);
                    consultations.add(c);
                    return c.getConsultationId();
                }
            }
        }
        return -1;

    }


    public boolean isPatientFirstTime(int patientID) {
        for (Consultation c : consultations) {
            if (c.getPatient().getPatientId() == (patientID)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean availabilityCheck(LocalDate consultationDate, LocalTime consultationTime, int noOfHours, int doctorLicenceNumber) {

        for (Consultation consultation : consultations) {
            if (consultation.getDocLi() == doctorLicenceNumber) {

                boolean condition1 = (consultationTime.isAfter(consultation.getTime())) && (consultationTime.isBefore(consultation.getTime().plusHours(consultation.getNoOfHours())));
                boolean condition2 = (consultationTime.plusHours(noOfHours).isAfter(consultation.getTime())) && (consultationTime.plusHours(noOfHours).isBefore(consultation.getTime().plusHours(consultation.getNoOfHours())));
                boolean condition3 = (consultationTime.isBefore(consultation.getTime()) && (consultationTime.plusHours(noOfHours).isAfter(consultation.getTime().plusHours(consultation.getNoOfHours()))));
                boolean condition4 = (consultationTime.equals(consultation.getTime()) ||
                        consultationTime.equals(consultation.getTime().plusHours(consultation.getNoOfHours())) ||
                        consultationTime.plusHours(noOfHours).equals(consultation.getTime()) ||
                        consultationTime.plusHours(noOfHours).equals(consultation.getTime().plusHours(consultation.getNoOfHours())));

                if ((consultationDate.equals(consultation.getConsulDate())) && ((condition1) || (condition2) || (condition3) || (condition4))) {
                    return false;
                }
            }
        }
        return true;
    }

    public Consultation getConsultation(int consultId) {
        System.out.println(consultId);
        for (Consultation c : consultations) {
            System.out.println(c.getConsultationId());
            if (c.getConsultationId() == consultId) {
                return c;
            }
        }
        return null;
    }
}
