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
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class OutpatientPriorityQueueTable {

    private SimpleDateFormat format = new SimpleDateFormat("HHH:mm:dd:ss");
    private JTable table = new JTable(getTableModel());

    public OutpatientPriorityQueueTable() {
        table.setSize(2000, 2000);
        JFrame frame = new JFrame();
        frame.add(new JScrollPane(table));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); //Centers frame. Must follow pack()
        frame.setState(frame.NORMAL);
        frame.setTitle("Priority Queue (OUTPATIENT)");

        Timer timer = new Timer(1000/DynaHospital.howMuchFasterFromRealTimeInSecond, new TimerListener());
        timer.start();
        frame.setVisible(true);
    }

    private TableModel getTableModel() {

        String[] cols = {"No.", "Full Name", "Sickness", "Triage", "Status", "Arrival Time", "Waiting Time"};

        Object[][] data = new Object[DynaHospital.outPatientPriorityQueue.getLength()][cols.length];

        for (int n = 0; n < DynaHospital.outPatientPriorityQueue.getLength(); n++) {
            try {
                data[n][0] = DynaHospital.outPatientPriorityQueue.getEntry(n + 1).getNumber();
                data[n][1] = DynaHospital.outPatientPriorityQueue.getEntry(n + 1).getFullName();
                data[n][2] = DynaHospital.outPatientPriorityQueue.getEntry(n + 1).getSickness();
                data[n][3] = DynaHospital.outPatientPriorityQueue.getEntry(n + 1).getTriage();
                data[n][4] = DynaHospital.outPatientPriorityQueue.getEntry(n + 1).getStatus();

                Date date = new Date(DynaHospital.outPatientPriorityQueue.getEntry(n + 1).getArrivalTime());
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
                String formattedDate = sdf.format(date);

                data[n][5] = formattedDate;

                Date date2 = new Date(DynaHospital.outPatientPriorityQueue.getEntry(n + 1).getWaitingTime());
                SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
                sdf2.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
                String formattedDate2 = sdf2.format(date2);

                data[n][6] = formattedDate2;
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
            String[] cols = {"No.", "Full Name", "Sickness", "Triage", "Status", "Arrival Time", "Waiting Time"};
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(DynaHospital.outPatientPriorityQueue.getLength());
            for (int i = 0; i < DynaHospital.outPatientPriorityQueue.getLength(); i++) {
                Object[] data = new Object[cols.length];
                for (int n = 0; n < cols.length; n++) {
                    try {
                        data[0] = DynaHospital.outPatientPriorityQueue.getEntry(i + 1).getNumber();
                        data[1] = DynaHospital.outPatientPriorityQueue.getEntry(i + 1).getFullName();
                        data[2] = DynaHospital.outPatientPriorityQueue.getEntry(i + 1).getSickness();
                        data[3] = DynaHospital.outPatientPriorityQueue.getEntry(i + 1).getTriage();
                        data[4] = DynaHospital.outPatientPriorityQueue.getEntry(i + 1).getStatus();

                        Date date = new Date(DynaHospital.outPatientPriorityQueue.getEntry(i + 1).getArrivalTime());
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
                        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
                        String formattedDate = sdf.format(date);

                        data[5] = formattedDate;

                        Date date2 = new Date(DynaHospital.outPatientPriorityQueue.getEntry(i + 1).getWaitingTime());
                        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
                        sdf2.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
                        String formattedDate2 = sdf2.format(date2);

                        data[6] = formattedDate2;
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
