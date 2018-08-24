/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynahospital;

import adt.CircularSinglyLinkedList;
import adt.LinearDoublyLinkedList;
import adt.PriorityCircularLinkedQueue;
import entities.Doctor;
import entities.Patient;
import entities.Treatment;

/**
 *
 * @author ongty
 */
public class OutpatientTreatmentDriver extends Thread {

    private CircularSinglyLinkedList<Treatment> outpatientTreatmentList;
    private PriorityCircularLinkedQueue<Patient> outPatientPriorityQueue;
    private LinearDoublyLinkedList<Doctor> doctorList;
    private static long ticks;

    public OutpatientTreatmentDriver(CircularSinglyLinkedList<Treatment> outpatientTreatmentList, LinearDoublyLinkedList<Doctor> doctorList, PriorityCircularLinkedQueue<Patient> outPatientPriorityQueue) {
        this.outpatientTreatmentList = outpatientTreatmentList;
        this.doctorList = doctorList;
        this.outPatientPriorityQueue = outPatientPriorityQueue;
        OutpatientTreatmentDriver.ticks = DynaHospital.startTime; // simulation time starting from real start time
    }

    @Override
    public void run() {
        if (outPatientPriorityQueue.isEmpty() == false && outpatientTreatmentList.getSize() < 5) {

            if (!doctorList.isEmpty()) {

                for (int n = 0; n < doctorList.getNumberOfEntries(); n++) {
                    Doctor currentDoctor = doctorList.getEntry(n + 1);

                    if (currentDoctor.getEmploymentStatusNumber() == 0 && currentDoctor.getCategory().equals("Outpatient handler") && currentDoctor.getWorkingStatusNumber() == 0) {
//                        System.out.println(outPatientPriorityQueue.isEmpty());
                        Patient currentPatient = outPatientPriorityQueue.dequeue();
//                        System.out.println(currentPatient);
                        outpatientTreatmentList.insertAtEnd(new Treatment(currentDoctor, currentPatient, ticks * 1000));
                        currentDoctor.setWorkingStatusNumber(2);
                        currentPatient.setStatus(2); //Status to in treatment
                        break;
                    }

                }

            }

        }
//        System.out.println(ticks);

        for (int n = 0; n < outpatientTreatmentList.getSize(); n++) {
            Treatment currentTreatment = outpatientTreatmentList.getEntry(n + 1);

            if (currentTreatment.getEndTime() <= ticks * 1000) {
                //Print duration
                //System.out.println(((currentTreatment.getEndTime() - currentTreatment.getStartTime())/1000)/60);
                currentTreatment.getTreatingDoctor().setWorkingStatusNumber(0);
                currentTreatment.getTreatedPatient().setStatus(1);
                currentTreatment.getTreatedPatient().setEndTime(ticks*1000);
                DynaHospital.endPatientList.add(currentTreatment.getTreatedPatient());
                outpatientTreatmentList.deleteAtPos(n + 1);
            }

        }

        ticks += 1;
    }

}
