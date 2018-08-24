/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import dynahospital.PatientDriver;
import java.util.Random;

/**
 *
 * @author ongty
 */
public class Treatment {
    private Doctor treatingDoctor;
    private Patient treatedPatient;
//    private long expectedDuration;
    private long startTick;
    private long endTick;

    public Treatment(Doctor treatingDoctor, Patient treatedPatient, long startTick) {
        this.treatingDoctor = treatingDoctor;
        this.treatedPatient = treatedPatient;
        this.startTick = startTick;
        makeEndTick();
    }
    
    public void makeEndTick() {
        int triage = this.treatedPatient.getTriage();
        
        Random r1 = new Random(System.nanoTime() + PatientDriver.getTicks());
        Random r2 = new Random(System.nanoTime() + PatientDriver.getTicks()*2);
        int random1 = r1.nextInt(101);
        
        int random2 = 0; // expected duration in minutes
        
        if (triage == 5) {      //OUTPATIENT DIAGNOSIS PROBABILITY
            if (random1 <= 5){      //5% of doctor diagnosis
                random2 = 1 + r2.nextInt(9);      //Duration between 1 - 10 minutes
            } else if (random1 <= 23){      //18%
                random2 = 10 + r2.nextInt(3);      //10 - 12 mins
            } else if (random1 <= 50) {      //27%
                random2 = 13 + r2.nextInt(4);      //13-16 mins
            } else if (random1 <= 74) {      //24%
                random2 = 17 + r2.nextInt(4);      //17-20 mins
            } else if (random1 <= 85) {      //11% 
                random2 = 21 + r2.nextInt(4);      //21-24 mins
            } else {      //15%
                random2 = 25 + r2.nextInt(6);      //25 - 30 mins
            }
        } else if (triage == 1 ) {      //TRIAGE LEVEL 1
            if (random1 <= 41) {    //41% of patients who survive
                random2 = 1 + r2.nextInt(16);      //Duration between 1-16 minutes
            } else {      //Rest of patients 
                random2 = 16 + r2.nextInt(20);      //16 - 35 minutes
            }
        } else if (triage == 2 ) {      //TRIAGE LEVEL 2
            if (random1 <= 17){     //17%
                random2 = 110 + r2.nextInt(119); //operating duration between 110 - 228 minutes
            } else if (random1 <= 33){      //16%
                random2 = 114 + r2.nextInt(105);
            } else if (random1 <= 46){      //13%
                random2 = 92 + r2.nextInt(37);
            } else if (random1 <= 57){      //11%
                random2 = 90 + r2.nextInt(65);
            } else if (random1 <= 66){      //95
                random2 = 112 + r2.nextInt(115);
            } else if (random1 <= 75){      //9%
                random2 = 99 + r2.nextInt(97);
            } else if (random1 <= 83){      //8%
                random2 = 97 + r2.nextInt(75);
            } else if (random1 <= 88){      //55
                random2 = 106 + r2.nextInt(113);
            } else if (random1 <= 92){      //45
                random2 = 118 + r2.nextInt(98);
            } else if (random1 <= 95){      //3%
                random2 = 148 + r2.nextInt(132);
            } else if (random1 <= 98){      //3%
                random2 = 107 + r2.nextInt(78);
            } else{      //2%
                random2 = 141 + r2.nextInt(143);
            }
           
        } else if (triage == 3){      //TRIAGE LEVEL 3
            random2 = 5 + r2.nextInt(26);       //Duration 5 - 30 minutes
        } else if (triage == 4){      //TRIAGE LEVEL 4
            random2 = 30 + r2.nextInt(91);      //Duration 30 - 120 minutes
        }
        
        this.endTick = this.startTick + (long)random2*60*1000;
        
    }

    public Doctor getTreatingDoctor() {
        return treatingDoctor;
    }

    public void setTreatingDoctor(Doctor treatingDoctor) {
        this.treatingDoctor = treatingDoctor;
    }

    public Patient getTreatedPatient() {
        return treatedPatient;
    }

    public void setTreatedPatient(Patient treatedPatient) {
        this.treatedPatient = treatedPatient;
    }

    public long getStartTime() {
        return startTick;
    }

    public void setStartTime(long startTick) {
        this.startTick = startTick;
    }

    public long getEndTime() {
        return endTick;
    }

    public void setEndTime(long endTick) {
        this.endTick = endTick;
    }
    
    
    
    
    
    
}
