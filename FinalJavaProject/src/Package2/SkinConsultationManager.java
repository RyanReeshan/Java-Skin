package Package2;

import Package1.Doctor;

import java.io.IOException;
import java.util.ArrayList;

public interface SkinConsultationManager {
    void addDoctor();
    void viewElements();

    void deleteDoctor();

    void DoctorSortingComparator();

    void saveData() throws IOException;

    void readData() throws IOException;

    ArrayList<Doctor> getArray();
}

