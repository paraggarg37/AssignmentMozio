package com.mozio.mozioassignment.Models;

import android.util.Log;

/**
 * Created by PG on 02/09/16.
 */
public class Symptoms {

    String name;
    String value;

    public Symptoms(){}
    public Symptoms(String name,String value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public boolean equals(final Object obj) {

        if(obj instanceof Symptoms){
            Symptoms o = (Symptoms)obj;
            if(o.name.equalsIgnoreCase(this.name) && o.value.equalsIgnoreCase(this.value)) return true;
        }

        return false;
    }
}
