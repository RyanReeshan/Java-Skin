package Package3;

import Package1.Doctor;
import Package1.Patient;
import Package2.SkinConsultationManager;
import Package2.WestminsterSkinConsultationManager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class AddConsultation extends JFrame {
    JTextField name_F1;
    JTextField name_F2;
    JTextField name_F3;
    JTextField id_Field;
    JTextField name_F4;
    JTextArea name_F5;
    JTextField name_F6;
    JTextField name_F7;

    JTextField name_F8;
    JTextField name_F9;
    JTextField name_F10;
    JButton b1;
    JButton b2;
    JButton b3;
    ImageIcon image;

    JComboBox doctorsDropDown;

    JFrame frame;
    WestminsterSkinConsultationManager skin;
    ConsultationManager conController;
    //JButton b1;

    ArrayList<Doctor> docArray;

    //Creating Patient inform collection and Doctors Information.
    public AddConsultation(ArrayList<Doctor> docArray, WestminsterSkinConsultationManager skin,ConsultationManager conController) {

        this.conController = conController;
        this.docArray = docArray;
        this.skin = skin;

        JLabel name_1 = new JLabel("Enter patient's name :");
        name_F1 = new JTextField();

        name_1.setBounds(650, 10, 150, 30);
        name_F1.setBounds(840, 15, 150, 30);

        JLabel name_2 = new JLabel("Enter patient's Surname :");
        name_F2 = new JTextField();

        name_2.setBounds(650, 50, 150, 30);
        name_F2.setBounds(840, 55, 150, 30);

        JLabel name_3 = new JLabel("Enter patient's DOB :");
        name_F3 = new JTextField();

        name_3.setBounds(650, 90, 150, 30);
        name_F3.setBounds(840, 95, 150, 30);

        JLabel name_4 = new JLabel("Enter patient's Mobile number :");
        name_F4 = new JTextField();


        name_4.setBounds(650, 130, 250, 30);
        name_F4.setBounds(840, 135, 150, 30);

        JLabel idLabel = new JLabel("Enter patient's ID :");
        id_Field = new JTextField();

        idLabel.setBounds(650, 170, 250, 30);
        id_Field.setBounds(840, 175, 150, 30);

        JLabel name_5 = new JLabel("Add notes  :");
        name_F5 = new JTextArea();

        name_5.setBounds(650, 210, 250, 30);
        name_F5.setBounds(840, 215, 150, 100);


        JLabel name_6 = new JLabel("Add image  :");
        name_F6 = new JTextField();

        name_6.setBounds(650, 320, 250, 30);
        name_F6.setBounds(2500, 320, 150, 30);

        //................Back....//

        JLabel name_7 = new JLabel("Select a doctor :");
        name_F7 = new JTextField();
        doctorsDropDown = new JComboBox<>(skin.getDoctorsList());

        name_7.setBounds(20, 10, 150, 30);
        doctorsDropDown.setBounds(250, 15, 150, 30);

        JLabel name_8 = new JLabel("Enter consultation date:");
        name_F8 = new JTextField();

        name_8.setBounds(20, 50, 150, 30);
        name_F8.setBounds(250, 55, 150, 30);


        JLabel name_9 = new JLabel("Enter consultation time :");
        name_F9 = new JTextField();

        name_9.setBounds(20, 90, 150, 30);
        name_F9.setBounds(250, 95, 150, 30);

        JLabel name_10 = new JLabel("Enter number of hours :");
        name_F10 = new JTextField();

        name_10.setBounds(20, 130, 150, 30);
        name_F10.setBounds(250, 135, 150, 30);


        b1 = new JButton("Add consultation");
        b1.setBounds(839, 360, 150, 20);
        b1.setBackground(Color.CYAN);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String [] dateArr = name_F3.getText().split("\\.");
                LocalDate dateOfBirth = LocalDate.of(Integer.parseInt(dateArr[0]),Integer.parseInt(dateArr[1]),Integer.parseInt(dateArr[2]));

                Patient tempP = new Patient(name_F1.getText(),name_F2.getText(),dateOfBirth,name_F4.getText(),Integer.parseInt(id_Field.getText()));

                String [] consultDateArr = name_F8.getText().split("\\.");
                LocalDate conDate = LocalDate.of(Integer.parseInt(consultDateArr[0]),Integer.parseInt(consultDateArr[1]),Integer.parseInt(consultDateArr[2]));


                String [] timeL = name_F9.getText().split("\\.");
                LocalTime conTime = LocalTime.of(Integer.parseInt(timeL[0]), Integer.parseInt(timeL[1]));

                Doctor tempDoctor = skin.getDoc(Integer.parseInt(doctorsDropDown.getSelectedItem().toString().split("-")[1]));

                int id = conController.addCons(tempDoctor,tempP,name_F5.getText(),conTime,Integer.parseInt(name_F10.getText()),conDate,image);
                if(id < 0){
                    JOptionPane.showMessageDialog(null, "No available doctors", "westminster",JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null, "Successfully added consultation ID is : " + id, "westminster",JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        //button_1.addActionListener((ActionListener) this);

        b2 = new JButton("Check availability");
        b2.setBounds(250, 170, 150, 20);
        b2.setBackground(Color.CYAN);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String [] consultDateArr = name_F8.getText().split("\\.");
                LocalDate conDate = LocalDate.of(Integer.parseInt(consultDateArr[0]),Integer.parseInt(consultDateArr[1]),Integer.parseInt(consultDateArr[2]));


                String [] timeL = name_F9.getText().split("\\.");
                LocalTime conTime = LocalTime.of(Integer.parseInt(timeL[0]), Integer.parseInt(timeL[1]));

                Doctor tempDoctor = skin.getDoc(Integer.parseInt(doctorsDropDown.getSelectedItem().toString().split("-")[1]));

                boolean check = conController.availabilityCheck(conDate,conTime,Integer.parseInt(name_F10.getText()),tempDoctor.getMedicalLicenceNumber());

                if(check){
                    JOptionPane.showMessageDialog(null, "This doctor is available for the time slot", "westminster",JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null, "This doctor is unavailable for the time slot", "westminster",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        //button_2.addActionListener((ActionListener) this);

        b3 = new JButton("Add Image");
        b3.setBounds(839, 320, 150, 20);
        b3.setBackground(Color.CYAN);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser file = new JFileChooser();
                file.setCurrentDirectory(new File("user.home"));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images","jpg","gif","png");
                file.addChoosableFileFilter(filter);
                int result = file.showSaveDialog(null);

                if(result == JFileChooser.APPROVE_OPTION){
                    selectedFile = file.getSelectedFile();
                    path = selectedFile.getAbsolutePath();
                    image = resizeImage(path);

                } else if (result == JFileChooser.CANCEL_OPTION) {
                    System.out.println("None selected");
                }
            }
        });

        //button_3.addActionListener((ActionListener) this);


        this.add(name_1);
        this.add(name_2);
        this.add(name_3);
        this.add(name_4);
        this.add(name_5);
        this.add(name_6);
        this.add(name_7);
        this.add(name_8);
        this.add(name_9);
        this.add(name_10);
        this.add(idLabel);
        this.add(id_Field);
        this.add(name_F1);
        this.add(name_F2);
        this.add(name_F3);
        this.add(name_F4);
        this.add(name_F5);
        this.add(name_F6);
        this.add(name_F7);
        this.add(name_F8);
        this.add(name_F9);
        this.add(name_F10);
        this.add(b1);
        this.add(b2);
        this.add(b3);
        this.add(doctorsDropDown);


        this.setTitle("Westminster Skin Care Center");
        this.setSize(1200, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    String path;
    File selectedFile;

    private ImageIcon resizeImage(String imagePath){
        ImageIcon MyImage = new ImageIcon(imagePath);
        Image image = MyImage.getImage();
        Image newImage = image.getScaledInstance(480,300, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }
}

