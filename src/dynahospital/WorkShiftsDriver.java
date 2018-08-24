/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynahospital;

import static dynahospital.PatientDriver.ticks;
import entities.Doctor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author ongty
 */
public class WorkShiftsDriver extends Thread {

    public void run() {
        Date date = new Date(ticks * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("k", Locale.ENGLISH); // hour number in 24
//        SimpleDateFormat sdf2 = new SimpleDateFormat("u", Locale.ENGLISH); // day in week 1 = MONDAY
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
//        sdf2.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String formattedDate = sdf.format(date);
//        String formattedDate2 = sdf2.format(date);

        int currentHourTick = Integer.parseInt(formattedDate);

        if (currentHourTick < 8 || currentHourTick > 21) {
            for (int n = 0; n < DynaHospital.doctorList.getNumberOfEntries(); n++) {
                Doctor d = DynaHospital.doctorList.getEntry(n + 1);
                if ("Outpatient handler".equals(d.getCategory()) && d.getWorkingStatusNumber() != 2 && d.getEmploymentStatusNumber() == 0) {
                    d.setWorkingStatusNumber(1);
                }
            }
        } else if (currentHourTick == 12) {
            for (int n = 0; n < DynaHospital.doctorList.getNumberOfEntries(); n++) {
                Doctor d = DynaHospital.doctorList.getEntry(n + 1);
                if ("Outpatient handler".equals(d.getCategory()) && d.getWorkingStatusNumber() != 2 && d.getEmploymentStatusNumber() == 0) {
                    d.setWorkingStatusNumber(1);
                }
            }
        } else {
            for (int n = 0; n < DynaHospital.doctorList.getNumberOfEntries(); n++) {
                Doctor d = DynaHospital.doctorList.getEntry(n + 1);
                if ("Outpatient handler".equals(d.getCategory()) && d.getWorkingStatusNumber() != 2 && d.getEmploymentStatusNumber() == 0) {
                    d.setWorkingStatusNumber(0);
                }
            }
        }

        if (currentHourTick < 12) {
            for (int n = 0; n < DynaHospital.doctorList.getNumberOfEntries(); n += 2) {
                Doctor d = DynaHospital.doctorList.getEntry(n + 1);
                if ("Emergency handler".equals(d.getCategory()) && d.getWorkingStatusNumber() != 2 && d.getEmploymentStatusNumber() == 0) {
                    d.setWorkingStatusNumber(1);
                }
            }
            for (int n = 1; n < DynaHospital.doctorList.getNumberOfEntries(); n += 2) {
                Doctor d = DynaHospital.doctorList.getEntry(n + 1);
                if ("Emergency handler".equals(d.getCategory()) && d.getWorkingStatusNumber() != 2 && d.getEmploymentStatusNumber() == 0) {
                    d.setWorkingStatusNumber(0);
                }
            }
        } else {
            for (int n = 0; n < DynaHospital.doctorList.getNumberOfEntries(); n += 2) {
                Doctor d = DynaHospital.doctorList.getEntry(n + 1);
                if ("Emergency handler".equals(d.getCategory()) && d.getWorkingStatusNumber() != 2 && d.getEmploymentStatusNumber() == 0) {
                    d.setWorkingStatusNumber(0);
                }
            }
            for (int n = 1; n < DynaHospital.doctorList.getNumberOfEntries(); n += 2) {
                Doctor d = DynaHospital.doctorList.getEntry(n + 1);
                if ("Emergency handler".equals(d.getCategory()) && d.getWorkingStatusNumber() != 2 && d.getEmploymentStatusNumber() == 0) {
                    d.setWorkingStatusNumber(1);
                }
            }
        }

    }

}
