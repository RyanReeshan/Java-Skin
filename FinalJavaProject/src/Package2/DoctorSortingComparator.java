package Package2;

import Package1.Doctor;

import java.util.Comparator;

public class DoctorSortingComparator implements Comparator<Doctor> {

    @Override
    public int compare(Doctor o1, Doctor o2) {
        //int NameCompare = o1.getSurName().compareTo(o2.getSurName());
        //int MedicalNumCompare = o1.getLicenseNumber().
        return o1.getSurname().compareTo(o2.getSurname());

    }
}
