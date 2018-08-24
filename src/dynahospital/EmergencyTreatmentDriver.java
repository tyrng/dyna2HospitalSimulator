/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynahospital;

import adt.CircularSinglyLinkedList;
import adt.LinearDoublyLinkedList;
import adt.PriorityCircularLinkedQueue;
import adt.SortedLinkedList;
import entities.Doctor;
import entities.Patient;
import entities.Treatment;
import entities.Ward;
import java.util.Random;

/**
 *
 * @author ongty
 */
public class EmergencyTreatmentDriver extends Thread {

    private CircularSinglyLinkedList<Treatment> emergencyTreatmentList;
    private PriorityCircularLinkedQueue<Patient> emergencyPriorityQueue;
    private LinearDoublyLinkedList<Doctor> doctorList;
    private SortedLinkedList<Ward> wardList;
    private static long ticks;

    private static int erCount;
    private static int oCount;

    public EmergencyTreatmentDriver(CircularSinglyLinkedList<Treatment> emergencyTreatmentList, LinearDoublyLinkedList<Doctor> doctorList, PriorityCircularLinkedQueue<Patient> emergencyPriorityQueue, SortedLinkedList<Ward> wardList) {
        this.emergencyTreatmentList = emergencyTreatmentList;
        this.doctorList = doctorList;
        this.emergencyPriorityQueue = emergencyPriorityQueue;
        this.wardList = wardList;
        EmergencyTreatmentDriver.ticks = DynaHospital.startTime; // simulation time starting from real start time
        erCount = 0;
        oCount = 0;
    }

    @Override
    public void run() {

//        System.out.println("test");
        if (emergencyPriorityQueue.isEmpty() == false && emergencyTreatmentList.getSize() < 5) {
            erCount = 0;
            oCount = 0;
            if (!doctorList.isEmpty()) {
                for (int n = 0; n < doctorList.getNumberOfEntries(); n++) {
                    Doctor currentDoctor = doctorList.getEntry(n + 1);

                    if (currentDoctor.getEmploymentStatusNumber() == 0 && currentDoctor.getCategory().equals("Emergency handler") && currentDoctor.getWorkingStatusNumber() == 0) {
                        erCount++;
                    } else if (currentDoctor.getEmploymentStatusNumber() == 0 && currentDoctor.getCategory().equals("Outpatient handler") && currentDoctor.getWorkingStatusNumber() == 2) {
                        oCount++;
                    }
                }

                for (int n = doctorList.getNumberOfEntries(); n > 0; n--) {
                    Doctor currentDoctor = doctorList.getEntry(n);

                    if (currentDoctor.getEmploymentStatusNumber() == 0 && currentDoctor.getCategory().equals("Emergency handler") && currentDoctor.getWorkingStatusNumber() == 0) {
//                        
                        Patient currentPatient = emergencyPriorityQueue.dequeue();
//                        System.out.println(currentPatient);
                        emergencyTreatmentList.insertAtEnd(new Treatment(currentDoctor, currentPatient, ticks * 1000));
                        currentDoctor.setWorkingStatusNumber(2);
                        currentPatient.setStatus(2);
                        erCount--;
                        break;
                    } else if (erCount <= 0 && oCount < 3 && currentDoctor.getEmploymentStatusNumber() == 0 && currentDoctor.getCategory().equals("Outpatient handler") && currentDoctor.getWorkingStatusNumber() == 0) {
//                        if (DynaHospital.outpatientDoctorsInHospital > 3) {
                        Patient currentPatient = emergencyPriorityQueue.dequeue();
                        emergencyTreatmentList.insertAtEnd(new Treatment(currentDoctor, currentPatient, ticks * 1000));
                        currentDoctor.setWorkingStatusNumber(2);
                        currentPatient.setStatus(2);
//                            System.out.println(currentDoctor);
//                            DynaHospital.outpatientDoctorsInHospital = 0;
                        oCount++;
                        break;
                    }

                }

            }

        }
//        System.out.println(ticks);
        Random generate = new Random(System.nanoTime() + ticks);

        for (int n = 0;
                n < emergencyTreatmentList.getSize();
                n++) {
            Treatment currentTreatment = emergencyTreatmentList.getEntry(n + 1);

            if (currentTreatment.getEndTime() <= ticks * 1000) {
                currentTreatment.getTreatingDoctor().setWorkingStatusNumber(0);
                if (currentTreatment.getTreatedPatient().getTriage() == 4) {
                    currentTreatment.getTreatedPatient().setStatus(4);
                    currentTreatment.getTreatedPatient().setEndTime(ticks * 1000);
                    DynaHospital.endPatientList.add(currentTreatment.getTreatedPatient());
                    emergencyTreatmentList.deleteAtPos(n + 1);
                } else if (currentTreatment.getTreatedPatient().getTriage() == 2) {
                    if (DynaHospital.wardList.getSize() < 70) {
                        wardList.insert(new Ward(currentTreatment.getTreatedPatient(), ticks * 1000));
                        emergencyTreatmentList.deleteAtPos(n + 1);
                    } else {
                        currentTreatment.getTreatedPatient().setStatus(3);
                        currentTreatment.getTreatedPatient().setEndTime(ticks * 1000);
                        DynaHospital.endPatientList.add(currentTreatment.getTreatedPatient());
                        emergencyTreatmentList.deleteAtPos(n + 1);
                    }
                } else if (currentTreatment.getTreatedPatient().getTriage() == 1) {
                    if ((currentTreatment.getEndTime() - currentTreatment.getStartTime()) < 1020000 && generate.nextInt(101) <= 41) {
                        currentTreatment.getTreatedPatient().setStatus(4);
                        currentTreatment.getTreatedPatient().setEndTime(ticks * 1000);
                        DynaHospital.endPatientList.add(currentTreatment.getTreatedPatient());
                        emergencyTreatmentList.deleteAtPos(n + 1);
                    } else if (generate.nextInt(101) <= 12) {
                        currentTreatment.getTreatedPatient().setStatus(4);          // declare dead (12% chance)
                        currentTreatment.getTreatedPatient().setEndTime(ticks * 1000);
                        DynaHospital.endPatientList.add(currentTreatment.getTreatedPatient());
                        emergencyTreatmentList.deleteAtPos(n + 1);
                    } else {
                        if (DynaHospital.wardList.getSize() < 70) {
                            wardList.insert(new Ward(currentTreatment.getTreatedPatient(), ticks * 1000));
                            emergencyTreatmentList.deleteAtPos(n + 1);
                        } else {
                            currentTreatment.getTreatedPatient().setStatus(3);
                            currentTreatment.getTreatedPatient().setEndTime(ticks * 1000);
                            DynaHospital.endPatientList.add(currentTreatment.getTreatedPatient());
                            emergencyTreatmentList.deleteAtPos(n + 1);
                        }
                    }
                } else if (generate.nextInt(101) <= 5) {
                    currentTreatment.getTreatedPatient().setStatus(3);
                    currentTreatment.getTreatedPatient().setEndTime(ticks * 1000);
                    DynaHospital.endPatientList.add(currentTreatment.getTreatedPatient());
                    emergencyTreatmentList.deleteAtPos(n + 1);
                } else {
                    currentTreatment.getTreatedPatient().setStatus(1);
                    currentTreatment.getTreatedPatient().setEndTime(ticks * 1000);
                    DynaHospital.endPatientList.add(currentTreatment.getTreatedPatient());
                    emergencyTreatmentList.deleteAtPos(n + 1);
                }
                break;
            }

        }

        ticks += 1;
    }

}
