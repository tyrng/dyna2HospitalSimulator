package dynahospital;

import entities.Patient;
import adt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

public class PatientDriver extends Thread {

    public LinearDoublyLinkedList<Patient> patientObjectList;
    private PriorityCircularLinkedQueue<Patient> emergencyPriorityQueue;
    private PriorityCircularLinkedQueue<Patient> outPatientPriorityQueue;
    public static int totalPatientThisRun;
    public static int addedPatientThisRun;
    public static boolean day = true;
    public static long ticks;
    private Random generate;

    public PatientDriver(PriorityCircularLinkedQueue<Patient> emergencyPriorityQueue, PriorityCircularLinkedQueue<Patient> outPatientPriorityQueue) {
        this.patientObjectList = new LinearDoublyLinkedList<Patient>();
        this.emergencyPriorityQueue = emergencyPriorityQueue;
        this.outPatientPriorityQueue = outPatientPriorityQueue;
        PatientDriver.totalPatientThisRun = 0;
        PatientDriver.addedPatientThisRun = 0;
        PatientDriver.ticks = DynaHospital.startTime; // simulation time starting from real start time
        this.generate = new Random();
//    reset();
    }

    @Override
    public void run() {

        Date date = new Date(ticks * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("H", Locale.ENGLISH);
        SimpleDateFormat sdf2 = new SimpleDateFormat("u", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        sdf2.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String formattedDate = sdf.format(date);

        try {
            // day and night trigger
            if (Integer.parseInt(formattedDate) >= 8 && Integer.parseInt(formattedDate) < 22) {
                day = true;
            } else {
                day = false;
                if (!outPatientPriorityQueue.isEmpty()) {
                    outPatientPriorityQueue.clear();
                }
            }

            createPatientList();
            queuePatients();
        } catch (Exception ex) {

        }
        ticks += 1;
//        updatePatientWaitingTime();
    }

    public static int getTotalPatientThisRun() {
        return totalPatientThisRun;
    }

    public static int getAddedPatientThisRun() {
        return addedPatientThisRun;
    }

    public static long getTicks() {
        return ticks;
    }

    private void createPatientList() {
        String[] names = {"John", "Marcus", "Susan", "Henry", "Dorethy", "Timoti", "Patric", "Yong H", "CheeYa", "Hanzi", "Yu Di", "Chekwi", "WilSon", "Wai Kah", "Qing", "Hong", "Zhen", "Mun Ho", "Leong", "Jia Yi", "Qing Wi", "Jonson", "Tun Yin", "Kuek", "Hann", "Gyu Gi", "Jun Jun", "Ee Edd"};
        String[] lastNames = {"John", "Marcus", "Henry", "Dorethy", "Timoti", "Patric", "Poh", "Teh", "Tan", "Tee", "Soh", "Yeok", "Soo", "Ong", "Lim", "Potato", "Die", "Foo", "Gan", "Gun", "Goh", "Pang", "Lin", "Sam", "Yeap", "Yup", "Yip", "Yeoh", "Yow", "Ooi", "Khoo", "Kwaii"};
        ArrayList<String> sicknesses;

        if (generate.nextInt(101) < 72) {
            sicknesses = DynaHospital.triage5;
        } else {
            sicknesses = DynaHospital.triagex;
        }

        generate.setSeed(System.nanoTime() + ticks);
        int random = generate.nextInt(1000000);
        
        double percentageOfPatientArrivalPerTick = 0.5500;
        
        if (!day){
            percentageOfPatientArrivalPerTick = percentageOfPatientArrivalPerTick*3/4;
        }
        
        int patientMultiplier = (int) (percentageOfPatientArrivalPerTick * 10000); 
        
        if (random <= patientMultiplier) {  // Patient Multiplier
            String firstName = names[generate.nextInt(names.length)];
            String lastName = lastNames[generate.nextInt(lastNames.length)];
            String sickness = sicknesses.get(generate.nextInt(sicknesses.size()));

            patientObjectList.add(new Patient(firstName, lastName, generate.nextInt(1), sickness));
            totalPatientThisRun++;
//            System.out.println(totalPatientThisRun);
            patientObjectList.getEntry(totalPatientThisRun).setArrivalTime(ticks * 1000);
//            System.out.println(patientObjectList.getEntry(patientObjectList.getNumberOfEntries()) + "\n");
        }
    }

    private void queuePatients() {
        if (totalPatientThisRun > addedPatientThisRun) {
            Patient tmpPatient = patientObjectList.getEntry(totalPatientThisRun);
            if (tmpPatient.getTriage() == 5) {
                if (day) {
                    outPatientPriorityQueue.queue(tmpPatient);
                }
//                System.out.println(outPatientPriorityQueue.getEntry(outPatientPriorityQueue.getLength()) + "\n");
            } else {
                emergencyPriorityQueue.queue(tmpPatient);
//                System.out.println(emergencyPriorityQueue.getEntry(emergencyPriorityQueue.getLength()) + "\n");
            }
            addedPatientThisRun++;
        }
    }

//    public void updatePatientWaitingTime() {
//        for (int n = 0; n < patientObjectList.getNumberOfEntries(); n++) {
//            Patient p = patientObjectList.getEntry(n - 1);
//            p.setWaitingTime(ticks * 1000 - p.getArrivalTime());
//        }
//    }
}
