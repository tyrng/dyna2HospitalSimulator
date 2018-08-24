/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynahospital;

import entities.Doctor;

/**
 *
 * @author ongty
 */
public class EmergencyTreatmentHelper extends Thread {

    @Override
    public void run() {
        DynaHospital.outpatientDoctorsInHospital = 0;
        
        for (int n = 0; n < DynaHospital.doctorList.getNumberOfEntries(); n++) {
            try {
            Doctor c = DynaHospital.doctorList.getEntry(n + 1);
            if (c.getEmploymentStatusNumber() == 0 && c.getCategory().equals("Outpatient handler") && (c.getWorkingStatusNumber() == 0 || c.getWorkingStatusNumber() == 2)) {
                DynaHospital.outpatientDoctorsInHospital++;
            }
            } catch (Exception ex) {
                break;
            }
        }
        
    }

}
