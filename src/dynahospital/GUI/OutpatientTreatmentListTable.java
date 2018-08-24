/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynahospital.GUI;

import adt.LinearDoublyLinkedList;
import dynahospital.DynaHospital;
import dynahospital.PatientDriver;
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

public class OutpatientTreatmentListTable {

    private SimpleDateFormat format = new SimpleDateFormat("HHH:mm:dd:ss");
    private JTable table = new JTable(getTableModel());

    public OutpatientTreatmentListTable() {
        table.setSize(2000, 2000);
        JFrame frame = new JFrame();
        frame.add(new JScrollPane(table));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); //Centers frame. Must follow pack()
        frame.setState(frame.NORMAL);
        frame.setTitle("Diagnosys");

        Timer timer = new Timer(1000/DynaHospital.howMuchFasterFromRealTimeInSecond, new TimerListener());
        timer.start();
        frame.setVisible(true);
    }

    private TableModel getTableModel() {
        String[] cols = {"Patient No.", "Doctor ID", "Sickness.", "Start Time", "Expected Duration"};

        Object[][] data = new Object[DynaHospital.outpatientTreatmentList.getSize()][cols.length];

        for (int n = 0; n < DynaHospital.outpatientTreatmentList.getSize(); n++) {
            try {
                data[n][0] = DynaHospital.outpatientTreatmentList.getEntry(n + 1).getTreatedPatient().getNumber();
                data[n][1] = DynaHospital.outpatientTreatmentList.getEntry(n + 1).getTreatingDoctor().getFullId();
                data[n][1] = DynaHospital.outpatientTreatmentList.getEntry(n + 1).getTreatedPatient().getSickness();

                Date date = new Date(DynaHospital.outpatientTreatmentList.getEntry(n + 1).getStartTime());
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
                String formattedDate = sdf.format(date);

                data[n][3] = formattedDate;

                Date date2 = new Date(DynaHospital.outpatientTreatmentList.getEntry(n + 1).getEndTime() - DynaHospital.outpatientTreatmentList.getEntry(n + 1).getStartTime());
                SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
                sdf2.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
                String formattedDate2 = sdf2.format(date2);

                data[n][4] = formattedDate2;
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
            String[] cols = {"Patient No.", "Doctor ID", "Sickness.", "Start Time", "Expected Duration"};
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(DynaHospital.outpatientTreatmentList.getSize());
            for (int i = 0; i < DynaHospital.outpatientTreatmentList.getSize(); i++) {
                Object[] data = new Object[cols.length];
                for (int n = 0; n < cols.length; n++) {
                    try {
                        data[0] = DynaHospital.outpatientTreatmentList.getEntry(i + 1).getTreatedPatient().getNumber();
                        data[1] = DynaHospital.outpatientTreatmentList.getEntry(i + 1).getTreatingDoctor().getFullId();
                        data[2] = DynaHospital.outpatientTreatmentList.getEntry(i + 1).getTreatedPatient().getSickness();

                        Date date = new Date(DynaHospital.outpatientTreatmentList.getEntry(i + 1).getStartTime());
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
                        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
                        String formattedDate = sdf.format(date);

                        data[3] = formattedDate;

                        Date date2 = new Date(DynaHospital.outpatientTreatmentList.getEntry(i + 1).getEndTime() - DynaHospital.outpatientTreatmentList.getEntry(i + 1).getStartTime());
                        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
                        sdf2.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
                        String formattedDate2 = sdf2.format(date2);

                        data[4] = formattedDate2;
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
