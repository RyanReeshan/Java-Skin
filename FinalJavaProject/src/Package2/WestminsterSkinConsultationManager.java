package Package2;

import Package1.Doctor;

import javax.print.Doc;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class WestminsterSkinConsultationManager implements SkinConsultationManager{

    ArrayList<Doctor> doc = new ArrayList<>();   //Creating an arraylist to add doctor's details to the system.

    Scanner input = new Scanner(System.in);

    //............Add Doctors..................

    public void addDoctor(){
        if (doc.size()==10){
            System.out.println("\nSorry Doctors are Already Fulled :( ");
            return;
        }
        getInputDetailsOfDoctors();
    }

    //Getting doctor's name, surname, dateOfBirth, mobileNumber, licenseNumber, specialisation inputs from the user.
    public void getInputDetailsOfDoctors(){


        System.out.println("\nPlease Fill Out Doctor's Details Below.\n");

        try {
            doctorDetail("Name");
            String name = input.next();

            doctorDetail("Surname");
            String surname = input.next();

            //System.out.println("(yyyy-mm-dd)");
            doctorDetail("Date Of Birth" + " (yyyy-mm-dd)" );
            String dob = input.next();
            LocalDate dateOfBirth = LocalDate.parse(dob);

            int mobileNumber = passedInt("Mobile Number");

            int medicalLicenseNumber = passedInt("Medical License Number");

            doctorDetail("Specialisation");
            String specialization = input.next();

            Doctor doctor = new Doctor(name, surname, dateOfBirth, mobileNumber, medicalLicenseNumber, specialization);
            doc.add(doctor);

        }catch (Exception e){
            System.out.println("Invalid Input is Given :(");
        }



    }
    private int passedInt(String note){
        int number = 0;
        while(true){
            try{
                Scanner input = new Scanner(System.in);
                doctorDetail(note);
                number = input.nextInt();
                break;
            }catch(Exception e){
                System.out.println("Invalid Input is Given :(");
            }
        }
        return number;
    }

    //Creating a method to take Doctor's details.
    public String doctorDetail(String detail){
        System.out.print("Enter Doctor's " + detail + " : " );
        return detail;
    }


    //..............View Doctors............

    public void viewElements(){
        System.out.println("........Doctors Details........");
        for (Doctor doctor : doc){
            System.out.println();
            displayDoctorDetails(doctor);
            System.out.println();

        }
        System.out.println("There are " + doc.size() + " Doctor/s in the Consultation.");
    }
    private void displayDoctorDetails(Doctor doctor){
        System.out.println("Name           : " + doctor.getName());
        System.out.println("Surname        : " + doctor.getSurname());
        System.out.println("Date of Birth  : " + doctor.getDateOfBirth());
        System.out.println("Mobile Number  : " + doctor.getMobileNumber());
        System.out.println("License Number : " + doctor.getMedicalLicenceNumber());
        System.out.println("Specialisation : " + doctor.getSpecialisation());
        System.out.println();
    }


    //Deleting Doctors from the System.
    public void deleteDoctor(){

        if (doc.size() == 0){  //Check array size with the doctors list.
            System.out.println("There aren't any Doctors in the Consultation :(");
            return;
        }

        try{
            System.out.print("Enter Doctor's License Number that you want to delete : ");
            int number = input.nextInt();



            for (Doctor doctor : doc){
                if (number != doctor.getMedicalLicenceNumber()){
                    System.out.println("Can't match your Input with the Doctor's License Number :(");
                    break;
                }else {
                    doc.remove(doctor);
                    System.out.println("* Successfully Removed :)");
                    System.out.println("* There are " + doc.size() + " Doctors Left in the Consultation.\n");
                    System.out.println("\n| Deleted Doctor's Details are Below |");
                    displayDoctorDetails(doctor);
                    return;
                }


            }

        }catch (Exception e){
            System.out.println("Enter a Number :(");
        }


    }


    public void DoctorSortingComparator() {

        System.out.println("\nDoctors Details By Alphabetically Order.\n");
        Collections.sort(doc, new DoctorSortingComparator());
        for (Doctor doctor : doc){
            displayDoctorDetails(doctor);
        }
    }


    //Save into doctors
    public void saveData() throws IOException {

        File file = new File("consultationData.txt");

        FileOutputStream fout = new FileOutputStream(file, true);
        ObjectOutputStream objout = new ObjectOutputStream(fout);


        int size = doc.size();
        for (int i = 0; i < size; i++){
            objout.writeObject(doc.get(i));
        }
        objout.close();


    }

    //Read into file
    public void readData() {
        try{
            FileInputStream fin = new FileInputStream("consultationData.txt");
            ObjectInputStream objin = new ObjectInputStream(fin);

            boolean endOfFileReached = false;

            while (!endOfFileReached){
                try {
                    Doctor d = (Doctor) objin.readObject();
                    doc.add(d);
                }
                catch (EOFException e){
                    endOfFileReached = true;
                }
            }
        }catch (ClassNotFoundException e) {
            System.out.println("InvalidClassException " + e.getMessage());
        }catch (IOException e){
            System.out.println("IOException " + e.getMessage());
        }

    }

    public String [] getDoctorsList(){
        String [] list = new String[10];
        for(int i= 0; i< doc.size();i++){
            list[i] = "Dr."+doc.get(i).getName() + "-" + doc.get(i).getMedicalLicenceNumber();
        }
        return list;
    }

    public ArrayList<Doctor> getArray(){
        return doc;
    }

    public int searchDoctor(int medicalLicenceNumber){
        for(Doctor d : doc){
            if(d.getMedicalLicenceNumber() == (medicalLicenceNumber)){
                return doc.indexOf(d);
            }
        }

        return -1;
    }

    public Doctor getDoc(int medicalLicenceNumber){
        return doc.get(searchDoctor(medicalLicenceNumber));
    }

}

