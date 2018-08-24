/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynahospital;

import adt.LinearDoublyLinkedList;
import entities.Sickness;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class SicknessMaintainance implements Serializable {

    static Scanner input = new Scanner(System.in);

    static LinearDoublyLinkedList<Sickness> sicknessStorage;

    public static void run() {

        int menu;
        do {
            System.out.println("================ Sickness Menu ==============");
            System.out.println("1. Register New Sickness");
            System.out.println("2. Display All Sicknesses");
            System.out.println("3. Delete A Sickness");
            System.out.println("4. Back to Main Menu");
            System.out.println("============= End-Of-Menu ==========\n");
            System.out.println("Enter your choice as shown above");
            System.out.print(">> ");
            menu = input.nextInt();

            if (menu == 1) {
                createSickness();
            } else if (menu == 2) {
                displaySicknesses();
            } else if (menu == 3) {
                deleteSickness();
            }

        } while (menu < 4 && menu > 0);

    }

    private static void createSickness() {

        System.out.print("Name of sickness ?\n>> "); // User input How Many Doctor's Category Outpatient Handler.
        input.nextLine();
        String sicknessInput = input.nextLine().toLowerCase();

        System.out.print("Triage level of the sickness ?\n>> ");  // User input How Many Doctor's Category Emergency Handler.
        int triageInput = input.nextInt();

        Sickness newSickness = new Sickness(sicknessInput, triageInput);

        /* Read From File Section */
        try {
            FileInputStream fi = new FileInputStream(new File("Sickness.bin"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            sicknessStorage = (LinearDoublyLinkedList<Sickness>) oi.readObject();

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

        if (sicknessStorage == null) {
            sicknessStorage = new LinearDoublyLinkedList<Sickness>();
        }
        sicknessStorage.add(newSickness);

        /* Write To File Section */
        try {
            FileOutputStream f = new FileOutputStream(new File("Sickness.bin"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            o.writeObject(sicknessStorage);

            // Close Write File
            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }

    }

    private static void displaySicknesses() {

        /* Read From File Section */
        try {
            FileInputStream fi = new FileInputStream(new File("Sickness.bin"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            sicknessStorage = (LinearDoublyLinkedList<Sickness>) oi.readObject();

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

        System.out.println(sicknessStorage + "\n");

    }

    private static void deleteSickness() {
        System.out.print("Name of sickness ?\n>> "); // User input How Many Doctor's Category Outpatient Handler.
        input.nextLine();
        String sicknessInput = input.nextLine().toLowerCase();

        System.out.print("Triage level of the sickness ?\n>> ");  // User input How Many Doctor's Category Emergency Handler.
        int triageInput = input.nextInt();

        /* Read From File Section */
        try {
            FileInputStream fi = new FileInputStream(new File("Sickness.bin"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            sicknessStorage = (LinearDoublyLinkedList<Sickness>) oi.readObject();

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

        for (int i = 0; i < sicknessStorage.getNumberOfEntries(); i++) {
            Sickness s = sicknessStorage.getEntry(i + 1);
            if (s.getName().equals(sicknessInput) && s.getTriage() == triageInput) {
                sicknessStorage.remove(i + 1);
            }
        }

        /* Write To File Section */
        try {
            FileOutputStream f = new FileOutputStream(new File("Sickness.bin"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            o.writeObject(sicknessStorage);

            // Close Write File
            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }

    }

}
