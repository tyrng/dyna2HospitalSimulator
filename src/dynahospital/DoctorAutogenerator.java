/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynahospital;

import adt.LinearDoublyLinkedList;
import entities.Address;
import entities.Doctor;
import entities.Patient;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class DoctorAutogenerator {

    public static void run() {
        Scanner input = new Scanner(System.in);
        LinearDoublyLinkedList<Doctor> doctorStorage = new LinearDoublyLinkedList<>();

        int phoneBackNumber = 9635804;                                // Initialize to "9635804", then it will be increment "22034" for each doctor(s) in the loop below. *FORMULA PhoneNumber = PhoneFrontNumber + PhoneBackNumber - (e.g) 014 + 9635804 = 014 9635804*.
        int count = 0;

        System.out.print("How many Outpatient Doctor Handler ?\n>> "); // User input How Many Doctor's Category Outpatient Handler.
        int outpatientNumber = input.nextInt();
        int tempOutpatientNumber = outpatientNumber;
        System.out.print("How many Emergency Doctor Handler ?\n>> ");  // User input How Many Doctor's Category Emergency Handler.
        int emergencyNumber = input.nextInt();
        int tempEmergencyNumber = emergencyNumber;
        System.out.print("How many retired ?\n>> ");                   // User input How Many Doctor's EmploymentStatus Retired.
        int retired = input.nextInt();
        int tempRetired = retired;
        System.out.print("How many resigned ?\n>> ");                  // User input How Many Doctor's EmploymentStatus Resigned.
        int resigned = input.nextInt();
        int tempResigned = resigned;

        /*  Loop End According to User's Inputs from above.  */
        while (count < (outpatientNumber + emergencyNumber + resigned + retired)) {

            /* Randomized Doctor's Details Section*/
            String[] randomFirstName = {"Yong Hua", "Chee Yao", "Han Zong", "Yu Di", "Chek Wei", "Wil Son", "Wai Kah", "Qing Wei", "Hong Zhi", "Zhen Qiang", "Mun Hong", "Leong Guan", "Jia Ying", "Qing Wei", "Jon Sen", "Tun Ying", "Kek Keong", "Harn Zhan", "Gyu Gyu", "Jun Jun", "Ee Edgar"};
            String[] randomLastName = {"Poh", "Teh", "Tan", "Tee", "Soh", "Yeok", "Soo", "Ong", "Lim", "Potato", "Die", "Foo", "Gan", "Gun", "Goh", "Pang", "Lin", "Sam", "Yeap", "Yup", "Yip", "Yeoh", "Yow", "Ooi", "Khoo", "Kwaii"};
            String[] randomStreetAddress = {"Jalan Tilted Towers", "Jalan Potato", "Jalan Tomato", "Jalan Dusty Divot", "Jalan Anarchy Arcres", "Jalan Fatal Fields", "Jalan Flush Factory", "Jalan Greasy Grove", "Jalan Haunted Hills", "Jalan Junk Junction", "Jalan Lazy Links", "Jalan Lonely Lodge", "Jalan Loot Lake", "Jalan Moisty Mire", "Jalan Paradise Palms", "Jalan Pleasant Park", "Jalan Retail Row"};
            String[] randomZipCode = {"15300", "17000", "53300", "56000", "68100", "50621", "50622", "50644", "50770"};
            String[] randomPhoneFrontNumber = {"014", "016", "018", "012", "010", "011"};
            String[] randomResignedRetired = {"Emergency handler", "Outpatient handler"};

            String firstName = randomFirstName[new Random().nextInt(randomFirstName.length)];                                     // randomzied First Name         - (e.g) "Poh ", "Teh ", ...
            String lastName = randomLastName[new Random().nextInt(randomLastName.length)];                                        // randomzied Last Name          - (e.g) "Yong Hua", "Chee Yao", ...
            String streetAddress = randomStreetAddress[new Random().nextInt(randomStreetAddress.length)];                         // randomized Street Address     - (e.g) Jalan Cheras, Jalan Ahmad
            int zipCode = Integer.parseInt(randomZipCode[new Random().nextInt(randomZipCode.length)]);                            // randomized Zip Code           - (e.g) 17000, 15300, ...
            int phoneFrontNumber = Integer.parseInt(randomPhoneFrontNumber[new Random().nextInt(randomPhoneFrontNumber.length)]); // randomized Phone Front Number - (e.g) 014, 016, ...
            phoneBackNumber += 22034;                                                                                             // phoneBackNumber => 9635804
            String phoneNumberString = phoneFrontNumber + "" + phoneBackNumber;                                                   // 014 + 9635804   => 014 9635804
            int phoneNumber = Integer.parseInt(phoneNumberString);
            String country = "Malaysia";                                                                                          // set variable coutnry to "Malaysia"
            String city = "";                                                                                                     // initialize variable city
            String state = "";                                                                                                    // initialize variable state

            // City need to depend on Zip Code  
            switch (zipCode) {
                case 15300:
                    city = "Pasir Mas";
                    state = "Kelantan";
                    break;
                case 17000:
                    city = "Kota Bharu";
                    state = "Kelantan";
                    break;
                case 53300:
                    city = "Setapak";
                    state = "Selangor";
                    break;
                case 56000:
                    city = "Cheras";
                    state = "Selangor";
                    break;
                case 68100:
                    city = "Batu Caves";
                    state = "Selangor";
                    break;
                case 50621:
                case 50622:
                case 50644:
                case 50770:
                    city = "Kuala Lumpur";
                    state = "Selangor";
                    break;
                default:
                    break;
            } // end switch

            /*  REFERENCE Doctor doctor = new Doctor(String firstName, String lastName, int phoneNo, int workingStatus, int employmentStatus, String category, Address address); */
            Address address = new Address(zipCode, streetAddress, city, state, country);

            if (tempOutpatientNumber > 0) {
                Doctor d = new Doctor(firstName, lastName, new Random().nextInt(1), phoneNumber, 0, 0, "Outpatient handler", address); // Generate Doctor's Category as Outpatient Handler according to user's ínput number above.
                tempOutpatientNumber--;
                doctorStorage.add(d);
            } else if (tempEmergencyNumber > 0) {
                Doctor d = new Doctor(firstName, lastName, new Random().nextInt(1),phoneNumber, 0, 0, "Emergency handler", address); // Generate Doctor's Category as Emergency Handler according to user's ínput number above.
                tempEmergencyNumber--;
                doctorStorage.add(d);
            } else if (tempRetired > 0) {
                Doctor d = new Doctor(firstName, lastName, new Random().nextInt(1),phoneNumber, 1, 1, randomResignedRetired[new Random().nextInt(randomResignedRetired.length)], address); // Generate Doctor's Employment Status as Retired according to user's input number above.
                tempRetired--;
                doctorStorage.add(d);
            } else if (tempResigned > 0) {
                Doctor d = new Doctor(firstName, lastName, new Random().nextInt(1),phoneNumber, 1, 2, randomResignedRetired[new Random().nextInt(randomResignedRetired.length)], address); // Generate Doctor's Employment Status as Resigned according to user's input number above.
                tempResigned--;
                doctorStorage.add(d);
            }

            count++; // loop again 
        }

        /* Write To File Section */
        try {
            FileOutputStream f = new FileOutputStream(new File("Doctor.bin"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            o.writeObject(doctorStorage);

            // Close Write File
            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }

        /* Read From File Section */
        try {
            FileInputStream fi = new FileInputStream(new File("Doctor.bin"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            LinearDoublyLinkedList<Doctor> tmpStorage = (LinearDoublyLinkedList<Doctor>) oi.readObject();

            // Display Doctor's Details in console
            System.out.println(tmpStorage + "\n");

            // Close Read File
            oi.close();
            fi.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

/* user-input (nothing)
        System.out.print("First Name : ");
        String firstName = "";
        firstName += input.nextLine();
        System.out.print("Last  Name : ");
        String lastName = "";
        lastName += input.nextLine();
        System.out.print("Contact No.: ");
        int contactNo = input.nextInt();
        System.out.print("Zip Code   : ");
        int zipCode = input.nextInt();
        input.nextLine();
        System.out.print("Street Address:  ");
        String street = "";
        street += input.nextLine();
        System.out.print("City:  ");
        String city = "";
        city += input.nextLine();
        System.out.print("State:  ");
        String state = "";
        state += input.nextLine();
        System.out.print("Country:  ");
        String country = "";
        country += input.nextLine();
 */

 /*  menu lists
    public static void doctorModule() {
//        Address address = new Address(12,"1234", "123", "123", "123");
//        Doctor doctor = new Doctor(014, "available", "outpatient", address," yong", "hua");
//        Doctor doctor1 = new Doctor(014, "available", "outpatient", address," yong", "hua");
//        System.out.println(doctor);
//        System.out.println(doctor1);

        Scanner input = new Scanner(System.in);
        int menu;
        do {
            System.out.println("\n\n\n\n");
            System.out.println("=========== Doctor Module ==========");
            System.out.println("1. Register Doctor");
            System.out.println("2. Return to Menu");
            System.out.println("========= End-Of-Doctor Module ==========\n");
            System.out.println("Enter your choice as shown above");
            System.out.print(">> ");
            menu = input.nextInt();
            switch (menu) {
                case 1:
                    registerDoctor(); //empty
                    break;
                case 2:
                    return;

            } // end switch
        } while (menu != 2);

    }// end doctorModule
 */
