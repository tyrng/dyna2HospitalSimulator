package entities;

import java.io.Serializable;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class Person implements Serializable{

    private String firstname;
    private String lastname;
    private String gender; 
    private int genderNumber;           // [0-male, 1-female]

    public Person() {
    }


    public Person(String firstname, String lastname, int genderNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.genderNumber = genderNumber;
        setGender(this.genderNumber);
    }
    
    private void setGender(int genderNumber) {
        switch (genderNumber) {
            case 0:
                gender = "Male";
                break;
            case 1:
                gender = "Female";
                break;
            default:
                break;
        }
    }

    
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    
    
    public String getFullName() {
        String fullname = lastname + " " + firstname;
        return fullname;
    }

//    @Override
//    public int compareTo(Person o) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Full Name: " + getFullName() + "\nGender: " + getGender() + "\n";
    }

    
}
