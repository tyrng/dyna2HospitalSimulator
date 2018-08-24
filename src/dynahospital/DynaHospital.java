/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynahospital;

import dynahospital.GUI.DoctorListTable;
import dynahospital.GUI.EmergencyPriorityQueueTable;
import dynahospital.GUI.EmergencyTreatmentListTable;
import dynahospital.GUI.OutpatientPriorityQueueTable;
import dynahospital.GUI.OutpatientTreatmentListTable;
import adt.CircularSinglyLinkedList;
import adt.LinearDoublyLinkedList;
import adt.PriorityCircularLinkedQueue;
import adt.SortedLinkedList;
import dynahospital.GUI.TicksPrinter;
import dynahospital.GUI.WardListTable;
import entities.Doctor;
import entities.Patient;
import entities.Sickness;
import entities.Treatment;
import entities.Ward;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 *
 * @author ongty
 */
public class DynaHospital {

    public static PriorityCircularLinkedQueue<Patient> emergencyPriorityQueue;
    public static PriorityCircularLinkedQueue<Patient> outPatientPriorityQueue;
    public static CircularSinglyLinkedList<Treatment> outpatientTreatmentList;
    public static CircularSinglyLinkedList<Treatment> emergencyTreatmentList;
    public static LinearDoublyLinkedList<Doctor> doctorList;
    public static LinearDoublyLinkedList<Sickness> sicknessList;
    public static SortedLinkedList<Ward> wardList;

    public static List<Patient> endPatientList;

    public static int outpatientDoctorsInHospital;

    public static ArrayList<String> triage5;
    public static ArrayList<String> triagex;

    public static long runDuration;

    public static int realOneSecondInSimMinute;

    public static int howMuchFasterFromRealTimeInSecond;

    public static Long startTime;

    static int dayNo;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int menu;
        do {
            System.out.println("\n\n\n\n\n\n\n");
            System.out.println("       Welcome to Dyna Hospital     ");
            System.out.println("================ Menu ==============");
            System.out.println("1. Run Simulation");
            System.out.println("2. Autogenerate Doctors");
            System.out.println("3. Maintain Sicknesses");
            System.out.println("4. Exit");
            System.out.println("============= End-Of-Menu ==========\n");
            System.out.println("Enter your choice as shown above");
            System.out.print(">> ");
            menu = input.nextInt();
            switch (menu) {
                case 1:
                    Instant instant = Instant.now();
                    startTime = instant.getEpochSecond();

                    emergencyPriorityQueue = new PriorityCircularLinkedQueue<>();
                    outPatientPriorityQueue = new PriorityCircularLinkedQueue<>();
                    outpatientTreatmentList = new CircularSinglyLinkedList<>();
                    emergencyTreatmentList = new CircularSinglyLinkedList<>();
                    doctorList = new LinearDoublyLinkedList<>();
                    sicknessList = new LinearDoublyLinkedList<>();
                    endPatientList = new LinkedList<>();
                    wardList = new SortedLinkedList<>();

                    outpatientDoctorsInHospital = 0;

                    /* Read From File Section */
                    try {
                        FileInputStream fi = new FileInputStream(new File("Doctor.bin"));
                        ObjectInputStream oi = new ObjectInputStream(fi);
                        FileInputStream fi2 = new FileInputStream(new File("Sickness.bin"));
                        ObjectInputStream oi2 = new ObjectInputStream(fi2);

                        // Read objects
                        doctorList = (LinearDoublyLinkedList<Doctor>) oi.readObject();
                        sicknessList = (LinearDoublyLinkedList<Sickness>) oi2.readObject();

                        // Close Read File
                        oi.close();
                        fi.close();
                        oi2.close();
                        fi2.close();

                    } catch (FileNotFoundException e) {
                        System.out.println("File not found");
                    } catch (IOException e) {
                        System.out.println("Error initializing stream");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    triage5 = new ArrayList<String>();
                    triagex = new ArrayList<String>();

                    for (int n = 0; n < sicknessList.getNumberOfEntries(); n++) {
                        Sickness s = sicknessList.getEntry(n + 1);
                        if (s.getTriage() == 5) {
                            triage5.add(s.getName());
                        } else {
                            triagex.add(s.getName());
                        }
                    }

                    PatientDriver patientDriver = new PatientDriver(emergencyPriorityQueue, outPatientPriorityQueue);
                    OutpatientTreatmentDriver outpatientTreatmentDriver = new OutpatientTreatmentDriver(outpatientTreatmentList, doctorList, outPatientPriorityQueue);
                    EmergencyTreatmentDriver emergencyTreatmentDriver = new EmergencyTreatmentDriver(emergencyTreatmentList, doctorList, emergencyPriorityQueue, wardList);
//                    EmergencyTreatmentHelper emergencyTreatmentHelper = new EmergencyTreatmentHelper();
                    WorkShiftsDriver workShiftsDriver = new WorkShiftsDriver();
                    WardDriver wardDriver = new WardDriver();

                    Runnable setWaitingTime = new Runnable() {
                        @Override
                        public void run() {
                            for (int n = 0; n < outPatientPriorityQueue.getLength(); n++) {
                                try {
                                    Patient p2 = outPatientPriorityQueue.getEntry(n + 1);
                                    p2.setWaitingTime(patientDriver.ticks * 1000 - p2.getArrivalTime());
                                } catch (Exception ex) {
                                    break;
                                }
                            }
                            for (int n = 0; n < emergencyPriorityQueue.getLength(); n++) {
                                try {
                                    Patient p2 = emergencyPriorityQueue.getEntry(n + 1);
                                    p2.setWaitingTime(patientDriver.ticks * 1000 - p2.getArrivalTime());
                                } catch (Exception ex) {
                                    break;
                                }
                            }
                        }
                    };

                    realOneSecondInSimMinute = 3;           // 1 sec in real life is x minutes in sim
                    howMuchFasterFromRealTimeInSecond = realOneSecondInSimMinute * 60;

                    int oneDayinMinute = 1440;

                    int simulationRunDay = 60;

                    int simulationRunOneDayInRealSecond = oneDayinMinute / realOneSecondInSimMinute;

                    runDuration = simulationRunDay * simulationRunOneDayInRealSecond; // 1 day multiply with constants

                    dayNo = 0;

                    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(7);

                    int oneSecondInNano = 1000000000;

                    int taskDelay = oneSecondInNano / howMuchFasterFromRealTimeInSecond;

                    scheduler.scheduleAtFixedRate(patientDriver, 0, taskDelay, TimeUnit.NANOSECONDS); // 360 times per second (REAL 1s : SIM 3m)
                    scheduler.scheduleAtFixedRate(outpatientTreatmentDriver, 0, taskDelay, TimeUnit.NANOSECONDS); // 360 times per second (REAL 1s : SIM 3m)
//                    scheduler.scheduleAtFixedRate(emergencyTreatmentHelper, 1, taskDelay / 2, TimeUnit.NANOSECONDS); // 360 times per second (REAL 1s : SIM 3m)
                    scheduler.scheduleAtFixedRate(emergencyTreatmentDriver, 0, taskDelay, TimeUnit.NANOSECONDS); // 360 times per second (REAL 1s : SIM 3m)
                    scheduler.scheduleAtFixedRate(wardDriver, 0, taskDelay, TimeUnit.NANOSECONDS); // 360 times per second (REAL 1s : SIM 3m)
                    scheduler.scheduleAtFixedRate(workShiftsDriver, 0, taskDelay, TimeUnit.NANOSECONDS); // 360 times per second (REAL 1s : SIM 3m)
                    scheduler.scheduleAtFixedRate(setWaitingTime, 0, taskDelay, TimeUnit.NANOSECONDS); // 360 times per second (REAL 1s : SIM 3m)

                    Runnable daySummary = new Runnable() {
                        @Override
                        public void run() {

                            dayNo++;
                            System.out.println("=====================================================================================================================");
                            System.out.println("\t\t\t\t\t\tDAY " + dayNo + " START");
                            System.out.println("=====================================================================================================================");

                            System.out.print("No." + "\t" + "Full Name" + "\t\t" + "Arrival Time" + "\t" + "Wait Duration" + "\t" + "End Time" + "\t" + "Status" + "\t\t" + "Triage" + "\t" + "Sickness" + "\n");

                            for (int n = 0; n < endPatientList.size(); n++) {
                                System.out.print(endPatientList.get(n));
                            }
                            System.out.println("=====================================================================================================================");
                            System.out.println("\t\t\t\t\t\tDAY " + dayNo + " END");
                            System.out.println("=====================================================================================================================");
                            endPatientList.clear();

                            runDuration--;

                            if (runDuration == 0) {
                                System.out.println("\n\nThank You For Using Our Dyna Hospital Simulation System. Have a Good Day! :D\n");
                                System.exit(0);

                            }

                        }
                    };

                    scheduler.scheduleAtFixedRate(daySummary, simulationRunOneDayInRealSecond, simulationRunOneDayInRealSecond, TimeUnit.SECONDS);

                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            new DoctorListTable();
                            new OutpatientPriorityQueueTable();
                            new EmergencyPriorityQueueTable();
                            new EmergencyTreatmentListTable();
                            new OutpatientTreatmentListTable();
                            new WardListTable();
                            new TicksPrinter();
                        }
                    });

                    break;

                case 2:

                    DoctorAutogenerator.run();

                    break;

                case 3:

                    SicknessMaintainance.run();

                    break;

                case 4:
                    System.out.println("\n\nThank You For Using Our Dyna Hospital Simulation System. Have a Good Day! :D\n");
                    System.exit(0);
                    break;
            } // end switch

        } while (menu != 4 && menu != 1);

    }

}
