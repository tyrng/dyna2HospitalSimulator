/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynahospital;

import static dynahospital.DynaHospital.wardList;
import entities.Patient;
import entities.Ward;

/**
 *
 * @author ongty
 */
public class WardDriver extends Thread {

    private static long ticks;

    public WardDriver() {
        WardDriver.ticks = DynaHospital.startTime;
    }

    @Override
    public void run() {

        for (int n = 0; n < wardList.getSize(); n++) {
            Ward w = wardList.getEntry(n + 1);
            if (w.getEndTime() <= ticks * 1000) {
                w.getTreatedPatient().setStatus(1);
                w.getTreatedPatient().setEndTime(ticks * 1000);
                DynaHospital.endPatientList.add(w.getTreatedPatient());
                wardList.deleteAtPos(n + 1);
                break;
            }
        }
        ticks += 1;
    }

    public void add(Patient p, long ticks) {
        if (wardList.getSize() <= 6) {
            wardList.insert(new Ward(p, ticks));
        }
    }

}
