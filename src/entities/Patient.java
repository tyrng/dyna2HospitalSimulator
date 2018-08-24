package entities;

import java.util.PriorityQueue;
import dynahospital.DynaHospital;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ThreadLocalRandom;

//    enum Outpatient {
//        DIARRHEA, FEVER, NECK_PAIN, HEADACHE
//    }
//    enum Emergency {
//        ASTHMA, EPILEPSY, CHRONIC_KIDNEY, LUNG_CANCER
//    }
//    enum PriorityLevel {
//        VERY_HIGH, HIGH, NORMAL, LOW, VERY_LOW
//    }
public class Patient extends Person implements Comparable<Patient> {

    /* Data Variable */
    public static int nextNumber = 1001;
    private int number;
    private String sickness;
    private int triage;      // [1,2,3,4,5] - [very high, high, normal, low, very low]
    private int status;      // [0,1,2,3,4] - [In Hospital,Treated, In Treatment, Dismissed,Dead] 
    private String statusName; //[In Hospital,Treated, In Treatment, Dismissed,Dead] 
//    private String category;    // *AUTOMATION* System assign as "emergency" or "outpatient", according to variable sickness.
    private long arrivalTime;    // *AUTOMATION* System assign certain time, according to _____.
    private long waitingTime;   // *
    private long endTime;

    public Patient() {

    }

    public Patient(String firstname, String lastname, int genderNumber, String sickness) {
        super(firstname, lastname, genderNumber);
        this.sickness = sickness;
        this.number = nextNumber;
        nextNumber++;
        if (nextNumber > 9999){
            nextNumber = 1001;
        }
        this.arrivalTime = DynaHospital.startTime * 1000;
        this.waitingTime = 0;
        setTriage(this.sickness);
        status = 0;
        statusName = "In Hospital";
    }

    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
//        this.arrivalTime = instant.toEpochMilli() * 1000 + ThreadLocalRandom.current().nextInt(1, (int)DynaHospital.runDuration/1000000)*1000000; // change after implement time
    }

//    public void adjustArrivalTime(long ticks) {
//        this.arrivalTime = ticks;
////        this.arrivalTime = instant.toEpochMilli() * 1000 + ThreadLocalRandom.current().nextInt(1, (int)DynaHospital.runDuration/1000000)*1000000; // change after implement time
//    }     
    private void setTriage(String sickness) {
        for (int n = 0; n < DynaHospital.sicknessList.getNumberOfEntries(); n++) {
            if (sickness.equals(DynaHospital.sicknessList.getEntry(n + 1).getName())) {
                this.triage = DynaHospital.sicknessList.getEntry(n + 1).getTriage();
            }
        }
    }

    public static int getNextNumber() {
        return nextNumber;
    }

    public int getTriage() {
        return triage;
    }

    public void setTriage(int triage) {
        this.triage = triage;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int compareTo(Patient other) {
        int priority = 0;
        if (triage < other.triage) {
            priority = -1;
        } else if (triage > other.triage) {
            priority = 1;
        } else { // triage == other.triage
            if (arrivalTime < other.arrivalTime) {
                priority = -1;
            } else if (arrivalTime > other.arrivalTime) {
                priority = 1;
            }
        }
        return priority;
    }

    public String toString() {

        Date date1 = new Date(getArrivalTime());
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        sdf1.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String formattedDate1 = sdf1.format(date1);

        Date date2 = new Date(getWaitingTime());
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        sdf2.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
        String formattedDate2 = sdf2.format(date2);

        Date date3 = new Date(getEndTime());
        SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        sdf3.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String formattedDate3 = sdf3.format(date3);

        return this.getNumber() + "\t" + getFullName() + "\t\t" + formattedDate1 + "\t\t" + formattedDate2 + "\t\t" + formattedDate3 + "\t\t" + getStatusName() + "\t\t" + getTriage() + "\t" + getSickness() + "\n";
    }

    public String getSickness() {
        return sickness;
    }

    public void setSickness(String sickness) {
        this.sickness = sickness;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) { // [0,1,2,3,4] - [In Hospital,Treated, In Treatment, Dismissed,Dead] 
        this.status = status;
        
        if (this.status == 0){
            this.statusName = "In Hospital";
        } else if (this.status == 1){
            this.statusName = "Treated";
        } else if (this.status == 2){
            this.statusName = "In Treatment";
        } else if (this.status == 3){
            this.statusName = "Dismiss";
        } else if (this.status == 4){
            this.statusName = "Dead";
        }
        
        
    }
    
    

    public long getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(long waitingTime) {
        this.waitingTime = waitingTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

}
