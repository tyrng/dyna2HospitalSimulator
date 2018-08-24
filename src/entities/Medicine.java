package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**@constructor Medicine*/
public class Medicine /*implements List<T>*/{
    private String MedicineId;
    private String MedicineName;
    
    // Drugs for emergency use
    private final Map<String, List<String>> drugType = new HashMap<>();
    static final String[] eDrugs = {"heroin", "caffeine", "cocaine", "alcohol", "cannabis", "tobbaco",
                                "depressant", "digoxin", "magnesium sulfate", "adrenaline", "sodium chloride"};
    
    /** @todo medicineType assigns according to patient sickness to get medicine */
    public Medicine (String MedicineId, String MedicineName) {
        this.MedicineId = MedicineId;
        this.MedicineName = MedicineName;
        medicineType();
    }
    
    /** @typedef getMedicine - get medicine from selected patient 
     @param triage Integer 
     @param sickness String
     @return String - type of drug according to sickness*/
    public String getMedicine(int triage, String sickness){
        String drug = "No Medicine";
        if(triage >= 3){
            //String commonDrug = cDrugs[new Random().nextInt(cDrugs.length)];
            drug = drugType.get(sickness).get(new Random().nextInt(drugType.get(sickness).size()));
        }else{
            drug = eDrugs[new Random().nextInt(eDrugs.length)];
        }
        return drug;
    }
    
    private void medicineType(){    
        //drugType.put("flu", new ArrayList<>(Arrays.asList("panadol", "capsule", "tablet")));
        drugType.put("diarrhea", Arrays.asList("antibiotics", "antidiarrheal ", "vitamins"));
        drugType.put("allergic", Arrays.asList("painkillers", "benadryl", "tablet", "antibiotics"));
        drugType.put("fractures", Arrays.asList("painkillers", "hydrocodone ", "naproxen"));
        drugType.put("asthma", Arrays.asList("decongestants", "antibacterials", "benadryl", "antibiotics"));
        drugType.put("shock", Arrays.asList("benadryl", "epinephrine"));
    }
    
    public void setMedicineId(String MedicineId) {
        this.MedicineId = MedicineId;
    }

    public void setMedicineName(String MedicineName) {
        this.MedicineName = MedicineName;
    }
    
    public String getMedicineId() {
        return MedicineId;
    }

    public String getMedicineName() {
        return MedicineName;
    }
}
