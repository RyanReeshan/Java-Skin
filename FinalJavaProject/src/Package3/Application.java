package Package3;

import Package1.Doctor;
import Package2.DoctorSortingComparator;
import Package2.SkinConsultationManager;
import Package2.WestminsterSkinConsultationManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Application {
    JFrame frame;
    WestminsterSkinConsultationManager skin;
    ConsultationManager manage;
    JButton b1;
    JButton review;
    JButton sort;

    JTable table;
    ArrayList<Doctor> docArray;


    public Application(ArrayList<Doctor> docArray, WestminsterSkinConsultationManager skin) {
        this.manage = new ConsultationManager(docArray);
        this.docArray = docArray;
        this.skin = skin;
        frame = new JFrame("Westminster Consultation");

        createTable();
        appointmentsTable();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1200,650);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void createTable(){

        skin.DoctorSortingComparator();

        String[] name = {"Name", "Surname", "Date Of Birth", "Mobile Number", "License Number", "Specialization"};
        table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(name);
        table.setModel(model);
        table.setBackground(Color.CYAN);
        Object[] object = new Object[6];

        for (Doctor docD : docArray){
            object[0] = docD.getName();
            object[1] = docD.getSurname();
            object[2] = docD.getDateOfBirth();
            object[3] = docD.getMobileNumber();
            object[4] = docD.getMedicalLicenceNumber();
            object[5] = docD.getSpecialisation();
            model.addRow(object);

        }

        JScrollPane pane = new JScrollPane(table);

        pane.setBounds(10,10,730,250);
        frame.add(pane);

    }

    public void appointmentsTable() {
        JScrollPane pane = new JScrollPane();
        pane.setBounds(780, 10, 370, 500);

        b1 = new JButton("Add Consultation");
        b1.setBounds(70,320,250,20);
        b1.setBackground(Color.CYAN);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddConsultation(docArray,skin,manage);
            }
        });

        review= new JButton("Review");
        review.setBounds(70,200,250,20);
        review.setBackground(Color.CYAN);
        review.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Review(manage);
            }
        });

        sort = new JButton("Sort");
        sort.setBounds(70,80,250,20);
        sort.setBackground(Color.CYAN);
        sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
                table.setRowSorter(sorter);

                ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
                sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));//set column one as sort key and ascending order
                sorter.setSortKeys(sortKeys);
                table.repaint();//refresh the table after sorting
                JOptionPane.showMessageDialog(null, "Successfully sorted doctors according to their names", "westminster",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        pane.setLayout(null);
        pane.add(b1);
        pane.add(sort);
        pane.add(review);
        frame.add(pane);

    }



}

