/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;

/**
 *
 * @author ongty
 */
public class Sickness implements Serializable {

    private String name;
    private int triage;

    public Sickness(String name, int triage) {
        this.name = name;
        this.triage = triage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTriage() {
        return triage;
    }

    public void setTriage(int triage) {
        this.triage = triage;
    }

    @Override
    public String toString(){
        return "Sickness Name: " + name + "\nTriage No.: " + triage + "\n";
    }
    
}
