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
public class Ward implements Comparable<Ward> {

    private Patient treatedPatient;
//    private long expectedDuration;
    private long startTick;
    private long endTick;

    public Ward(Patient treatedPatient, long startTick) {
        this.treatedPatient = treatedPatient;
        this.startTick = startTick;
        makeEndTick();
    }

    public void makeEndTick() {
        Random r1 = new Random(System.nanoTime() + PatientDriver.getTicks());
        int random = 120 + r1.nextInt(301);

        this.endTick = this.startTick + (long) random * 60 * 1000;

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

    @Override
    public int compareTo(Ward o) {
        int priority = 0;
        if (startTick < o.startTick) {
            priority = -1;
        } else if (startTick > o.startTick) {
            priority = 1;
        }
        return priority;
    }

}
