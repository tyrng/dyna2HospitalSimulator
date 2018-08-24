package entities;

import java.io.Serializable;

/**
 * private clockIn; // Clock-In = '0900' or '9', for display only? private
 * clockOut; // Clock-Out = '1700' or '17', for display only? // NOT CONFIRM *
 * feature to track waiting time to be view by patient / new patient before
 * reaching their turn
 *
 * @author iEatPotato
 */
public class Doctor<T> extends Person implements Serializable{ //implements ListInterface<T>

    /* Data Variable */
    private char code = 'D';
    private static int nextId = 1001;
    private int id;
    private int phoneNumber;            // Phone Number      = +01463803.
    private int workingStatusNumber;    // Working Status    = [ (0)available,(1) unavailable,(2)duty ]  - [ *AUTOMATION* work hours , according to clock in & out. ]
    private int employmentStatusNumber; // Employment Status = [ (0)employed, (1)retired, (2)resigned ].       
    private String category;            // Category          = [outpatient handlers, emergency handlers].
    private String workingStatus;       // *AUTOMATION* according to variables workingStatusNumber.
    private String employmentStatus;    // *AUTOMATION* according to variables employmentStatusNumber.
    private Address address;            // Aggregation.

    /*  Initialize Constructor  */
    public Doctor() {

    }

    /*  Constructor */
    public Doctor(String firstname, String lastname, int genderNumber, int phoneNumber, int workingStatusNumber, int employmentStatusNumber, String category, Address address) {
        super(firstname, lastname, genderNumber);
        this.phoneNumber = phoneNumber;
        this.workingStatusNumber = workingStatusNumber;
        this.employmentStatusNumber = employmentStatusNumber;
        this.category = category;
        this.address = address;
        this.id = nextId++;
        
        setWorkingStatusNumber(workingStatusNumber);        // e.g - if variable workingStatusNumber = 0, then variable status will be AUTO-SET as available according to the workingStatusNumber, *refer to comment stated as variable workingStatusNumber above *
        setEmploymentStatusNumber(employmentStatusNumber); // e.g - if variable employmentStatusNumber = 0, then variable status will be AUTO-SET as employed according to the employmentStatusNumber, *refer to comment stated as variable employmeneStatus above*
    }

    /*  temporary contructor (testing)  */
    public Doctor(String firstname, String lastname, int gender) {
        super(firstname, lastname, gender);
        this.id = nextId++;
    }

    /*  ID  */
    public int getId() {
        return id;
    }

    /*  PhoneNumber */
    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /*  WorkingStatusNumber */
    public int getWorkingStatusNumber() {
        return workingStatusNumber;
    }

    public void setWorkingStatusNumber(int workingStatusNumber) {
        switch (workingStatusNumber) {
            case 0:
                workingStatus = "available";
                break;
            case 1:
                workingStatus = "unavailable";
                break;
//            case 2:
//                workingStatus = "break";
//                break;
            case 2:
                workingStatus = "duty";
                break;
            default:
                break;
        }
        this.workingStatusNumber = workingStatusNumber;
    }

    /*  EmploymentStatusNumber */
    public int getEmploymentStatusNumber() {
        return employmentStatusNumber;
    }

    public void setEmploymentStatusNumber(int employmentStatusNumber) {
        switch (employmentStatusNumber) {
            case 0:
                employmentStatus = "employed";
                break;
            case 1:
                employmentStatus = "retired";
                break;
            case 2:
                employmentStatus = "resigned";
                break;
            default:
                break;
        }
        this.employmentStatusNumber = employmentStatusNumber;
    }

    /*  WorkingStatus && EmploymentStatus */
    public String getWorkingStatus() {
        return workingStatus;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    /*  Category  */
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /*  Address */
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    /*  code + id  = FullId */
    public String getFullId() {
        String fullId = code + "" + id;
        return fullId;
    }

    /* Display Doctor details*/
    @Override
    public String toString() {
        return "Id: " + getFullId() + "\n" + super.toString() + "Phone Number: 60" + phoneNumber  + "\nWorking Status: " + workingStatus + "\nEmployment Status: " + employmentStatus + "\nCategory: " + category + address + "\nWorkingStatusNumber(temp): " + workingStatusNumber + "\nEmploymentStatusNumber(temp): " + employmentStatusNumber + "\n";
    }

    /* (testing) display string*/
//    @Override
//    public String toString() {
//        return "ID : " + getFullId() + "\nFull Name: " + getFullName();
//    }
}
