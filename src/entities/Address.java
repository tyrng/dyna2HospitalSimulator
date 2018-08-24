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
public class Address implements Serializable {

    private int zipCode;
    private String streetAddress;
    private String city;
    private String state;
    private String country;

    public Address() {
    }

    public Address(int zipCode, String streetAddress, String city, String state, String country) {
        this.zipCode = zipCode;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getcountry() {
        return country;
    }

    public void setcountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "\nZip Code: " + zipCode + "\nStreet Address: " + streetAddress + "\nCity: " + city + "\nState: " + state + "\nCountry: " + country;
    }

}
