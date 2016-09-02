package com.mozio.mozioassignment.Activities;

import android.content.Context;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mozio.mozioassignment.Globals;
import com.mozio.mozioassignment.Models.Patient;
import com.mozio.mozioassignment.Models.Symptoms;
import com.mozio.mozioassignment.R;
import com.mozio.mozioassignment.library.Diseases;
import com.mozio.mozioassignment.library.ToddsSyndrome;

import java.util.ArrayList;

/*
*
* Currently detects one disease can be used for multiple disease also.
*
* */
public class DetectDiseaseActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {


    protected Patient patient;
    public static final String TAG = DetectDiseaseActivity.class.toString();

    TextView percentage_tv;
    Button save;
    Diseases disease;
    Context context;
    private LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_disease);
        container = (LinearLayout) findViewById(R.id.container);
        percentage_tv = (TextView) findViewById(R.id.percentage);
        save = (Button)findViewById(R.id.save);
        context = getApplicationContext();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient.save(context);
                finish();
            }
        });



        Bundle bundle;
        setTitle("Select Symptoms");
        Gson gson = new Gson();

        if(savedInstanceState == null) {
            bundle = getIntent().getExtras();

            if(bundle.getString(Globals.PATIENT_KEY)!=null){
                String data =  bundle.getString(Globals.PATIENT_KEY);
                patient = gson.fromJson(data,Patient.class);
            }
            else{

                String name = bundle.getString(Globals.NAME_KEY);
                String age = bundle.getString(Globals.AGE_KEY);
                String gender = bundle.getString(Globals.GENDER_KEY);
                patient = new Patient(name, gender, age);
            }
        }
        else{
            bundle = savedInstanceState;
            String data =  bundle.getString(Globals.PATIENT_KEY);
            patient = gson.fromJson(data,Patient.class);
        }

        ArrayList<Symptoms> symptomsArrayList = new ArrayList<>();


        Symptoms s1 = new Symptoms(getString(R.string.symptom1),"true");
        Symptoms s2 = new Symptoms(getString(R.string.symptom2),"true");

        symptomsArrayList.add(s1);
        symptomsArrayList.add(s2);

        disease= new ToddsSyndrome("Toads Syndrome",symptomsArrayList);


        addSymptom(s1,patient.hasSymptom(s1));
        addSymptom(s2,patient.hasSymptom(s2));


        String data = gson.toJson(patient,Patient.class);
        Log.d(TAG,data);

        update();

       ArrayList<Patient> p = Patient.getALLPatients(context);
       String savedJson = gson.toJson(p);

        /*this is json can be saved over a REST API can be
        * */

       Log.d(TAG,"saved json "+savedJson);



    }


    public void update(){
      int per =  disease.patientHasDisease(patient);
      percentage_tv.setText("Chances of "+disease.getName()+" is "+per+"%");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Gson gson = new Gson();
        String data = gson.toJson(patient,Patient.class);
        savedInstanceState.putString(Globals.PATIENT_KEY,data);


        super.onSaveInstanceState(savedInstanceState);
    }

    public void addSymptom(Symptoms s,boolean val){
        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(s.getName());
        checkBox.setChecked(val);
        checkBox.setTag(s);

        container.addView(checkBox);
        checkBox.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }



    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
       CheckBox c = (CheckBox)buttonView;
       Log.d(TAG,"changed "+c.getText()+" to "+isChecked);
        if(isChecked)
        patient.addSymptom((Symptoms) c.getTag());
        else
        patient.removeSymptom((Symptoms) c.getTag());

        //Log.d(TAG,"percentage of chances "+disease.patientHasDisease(patient));
        update();
    }
}
