import Package2.SkinConsultationManager;
import Package2.WestminsterSkinConsultationManager;
import Package3.Application;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);

        WestminsterSkinConsultationManager skin = new WestminsterSkinConsultationManager();

        boolean flag = true;
        while (true){

            System.out.println("\n           Welcome to Westminster Consultation       ");

            System.out.println("""
                |------------------------------------------------------|
                |                                                      |
                |                A -> Add Doctors.                     |
                |                B -> Delete Doctors.                  |
                |                C -> Sort Doctors By Surname.         |
                |                D -> Save File.                       |
                |                E -> Read File.                       |
                |                F -> Exit.                            |
                |                G -> GUI.                             |
                |                H -> View Doctors.                    |
                |                                                      |
                |------------------------------------------------------|""");

            System.out.print("Please Select an Option : ");
            String option = input.next();
            System.out.println();
            //option.toLowerCase();

            switch (option){
                case "A", "a" :
                    skin.addDoctor();
                    break;

                case "B", "b" :
                    skin.deleteDoctor();
                    //Delete doctor
                    break;

                case "C", "c" :
                    skin.DoctorSortingComparator();
                    //Sort doctors by name
                    break;

                case "D", "d" :
                    skin.saveData();
                    //Save file
                    break;

                case "E", "e" :
                    skin.readData();
                    //Read file
                    break;

                case "F", "f" :
                    flag = false;
                    System.out.println("""
                            \nHave a Great Day and Good Bye !
                                    Thank You :)
                            """);
                    return;

                case "G", "g" :
                    //Application app = new Application(skin.getArray(),skin);
                    //Gui
                    Application app = new Application(skin.getArray(), skin);
                    break;

                case "H", "h" :
                    skin.viewElements();
                    //view doctors
                    break;

                default:
                    System.out.println("""
                        \nSorry, You have entered an Invalid Input :(
                               .....Please Try Again.....
                        """);

            }

        }

    }

}
