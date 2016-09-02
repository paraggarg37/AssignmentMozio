package com.mozio.mozioassignment.library;

import com.mozio.mozioassignment.Models.Patient;
import com.mozio.mozioassignment.Models.Symptoms;

import java.util.ArrayList;

/**
 * Created by PG on 03/09/16.
 */
public class ToddsSyndrome extends Diseases {



    public ToddsSyndrome(String name, ArrayList<Symptoms> symptoms) {
        super(name, symptoms);
    }

    @Override
    public int patientHasDisease(Patient patient) {


        int l = symptoms.size()+2;
        int count = 0;

        for(Symptoms s: symptoms){
            if(patient.hasSymptom(s)){
                count++;
            }
        }

        if(patient.getAge().contentEquals("1-15")){
            count++;
        }
        if(patient.getGender().contentEquals("Male")){
            count++;
        }

        System.out.println("count is "+count);
        System.out.println("l is "+l);

        return count*100/l;
    }
}
