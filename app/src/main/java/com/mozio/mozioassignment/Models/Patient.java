package com.mozio.mozioassignment.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.gson.Gson;
import com.mozio.mozioassignment.Helper.MySQLiteHelper;

import org.w3c.dom.Comment;

import java.util.ArrayList;

/**
 * Created by PG on 02/09/16.
 */
public class Patient {

    public String name;
    public String age;
    public String gender;
    public ArrayList<Symptoms> symptoms;

    public Patient(){
        if(symptoms==null){
            symptoms = new ArrayList<>();
        }
    }

    public Patient(String name,String gender,String age){
        this.name = name;
        this.gender = gender;
        this.age = age;
        if(symptoms==null){
            symptoms = new ArrayList<>();
        }
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public void addSymptom(Symptoms s){
        symptoms.add(s);
    }

    public void removeSymptom(Symptoms s){
        if(hasSymptom(s)){
            symptoms.remove(s);
        }
    }

    public boolean hasSymptom(Symptoms s){
        return symptoms.contains(s);
    }

    public String getData(){
        Gson gson = new Gson();
        String data = gson.toJson(this,Patient.class);
        return data;
    }

    public static Patient fromData(String data){
        Gson gson = new Gson();
        return gson.fromJson(data,Patient.class);
    }


    public void save(Context context){

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME,getName());
        values.put(MySQLiteHelper.COLUMN_DATA,getData());

        SQLiteDatabase database = getDatabase(context);
        database.delete(MySQLiteHelper.TABLE,MySQLiteHelper.COLUMN_NAME+"='"+getName()+"'",null);
        database.insert(MySQLiteHelper.TABLE, null,
                values);

    }

    public static SQLiteDatabase getDatabase(Context context){
        MySQLiteHelper dbHelper = new MySQLiteHelper(context);
        SQLiteDatabase database;
        database = dbHelper.getWritableDatabase();
        return database;
    }

    public static ArrayList<Patient> getALLPatients(Context context){
        String[] allColumns = { MySQLiteHelper.COLUMN_ID,
                MySQLiteHelper.COLUMN_NAME,MySQLiteHelper.COLUMN_DATA, };


        ArrayList<Patient> patients = new ArrayList<>();

        Cursor cursor = getDatabase(context).query(MySQLiteHelper.TABLE,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Patient patient = cursorToPatient(cursor);
            patients.add(patient);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        return patients;
    }

    @Override
    public String toString() {
        return getData();
    }

    public static Patient cursorToPatient(Cursor cursor){
        String data = cursor.getString(2);
        return fromData(data);
    }
}
