package com.mozio.mozioassignment.library;

import com.mozio.mozioassignment.Models.Patient;
import com.mozio.mozioassignment.Models.Symptoms;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by PG on 02/09/16.
 *
 * our base Disease class which comparese no of symptoms and give percentage
 */
public class Diseases {

    String Name;
    ArrayList<Symptoms> symptoms;

    public Diseases(String name,ArrayList<Symptoms> symptoms){
        this.Name = name;
        this.symptoms = (ArrayList<Symptoms>) symptoms.clone();
    }


    public int patientHasDisease(Patient patient){

        //size of symptoms of current disease
        int l = symptoms.size();
        //count of matched symptoms
        int count = 0;
        for(Symptoms s: symptoms){
            if(patient.hasSymptom(s)){
                count++;
            }
        }
        return count*100/l;
    }

    public ArrayList<Symptoms> getSymptoms() {
        return symptoms;
    }

    public String getName() {
        return Name;
    }
}
