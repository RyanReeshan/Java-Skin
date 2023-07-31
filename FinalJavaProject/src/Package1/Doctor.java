package Package1;

import java.awt.*;
import java.io.Serializable;
import java.time.LocalDate;

public class Doctor extends Person implements Serializable {
    private int medicalLicenceNumber;      //Doctor's attributes.
    private String specialisation;


    //Created a constructor to fill doctors information.

    public Doctor(String name, String surname, LocalDate dateOfBirth, int mobileNumber, int medicalLicenceNumber, String specialisation){
        super(name,surname, dateOfBirth,mobileNumber);
        this.medicalLicenceNumber = medicalLicenceNumber;
        this.specialisation = specialisation;
    }

    //Created getters and setters

    public int getMedicalLicenceNumber() {
        return medicalLicenceNumber;
    }

    public void setMedicalLicenceNumber(int medicalLicenceNumber) {
        this.medicalLicenceNumber = medicalLicenceNumber;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }
}
