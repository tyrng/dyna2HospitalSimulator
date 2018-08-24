/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynahospital.GUI;

import adt.LinearDoublyLinkedList;
import dynahospital.DynaHospital;
import entities.Doctor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class DoctorListTable {

    private SimpleDateFormat format = new SimpleDateFormat("HHH:mm:dd:ss");
    private JTable table = new JTable(getTableModel());

    public DoctorListTable() {
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JFrame frame = new JFrame();
        frame.add(new JScrollPane(table));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setState(frame.NORMAL);
        frame.pack();
        frame.setLocationRelativeTo(null); //Centers frame. Must follow pack()
        frame.setTitle("List of Doctors");

        Timer timer = new Timer(1000/DynaHospital.howMuchFasterFromRealTimeInSecond, new TimerListener());
        timer.start();
        frame.setVisible(true);
    }

    private TableModel getTableModel() {
        String[] cols = {"ID", "Full Name", "Category", "Employment Status", "Working Status"};

        Object[][] data = new Object[DynaHospital.doctorList.getNumberOfEntries()][cols.length];

        for (int n = 0; n < DynaHospital.doctorList.getNumberOfEntries(); n++) {
            try {
                data[n][0] = DynaHospital.doctorList.getEntry(n + 1).getFullId();
                data[n][1] = DynaHospital.doctorList.getEntry(n + 1).getFullName();
                data[n][2] = DynaHospital.doctorList.getEntry(n + 1).getCategory();
                data[n][3] = DynaHospital.doctorList.getEntry(n + 1).getEmploymentStatus();
                data[n][4] = DynaHospital.doctorList.getEntry(n + 1).getWorkingStatus();
            } catch (Exception ex) {
                break;
            }
        }

        DefaultTableModel model = new DefaultTableModel(data, cols);
        return model;
    }

//    private Object[] getNewRow() {
//
//        return new Object[]{(cal.getTime()), isSecondsEven};
//    }
    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] cols = {"ID", "Full Name", "Category", "Employment Status", "Working Status"};
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(DynaHospital.doctorList.getNumberOfEntries());
            for (int i = 0; i < DynaHospital.doctorList.getNumberOfEntries(); i++) {
                Object[] data = new Object[cols.length];
                for (int n = 0; n < cols.length; n++) {
                    try {

                        data[0] = DynaHospital.doctorList.getEntry(i + 1).getFullId();
                        data[1] = DynaHospital.doctorList.getEntry(i + 1).getFullName();
                        data[2] = DynaHospital.doctorList.getEntry(i + 1).getCategory();
                        data[3] = DynaHospital.doctorList.getEntry(i + 1).getEmploymentStatus();
                        data[4] = DynaHospital.doctorList.getEntry(i + 1).getWorkingStatus();
                    } catch (Exception ex) {
                        break;
                    }
                    Object[] row = data;
                    try {
                        model.setValueAt(row[n], i, n);
                    } catch (Exception ex) {
                        break;
                    }
                }
            }
        }
    }

}
