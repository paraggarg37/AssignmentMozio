package com.mozio.mozioassignment.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.mozio.mozioassignment.Globals;
import com.mozio.mozioassignment.R;

/**
 * Created by PG on 02/09/16.
 *
 * Adds a new patient
 *
 */
public class AddPatientActivity extends AppCompatActivity {


    protected Spinner mGender;
    protected Spinner mAge;
    protected Button mSave;
    protected TextView mName;
    public static final String TAG = "PATIENT_ACTIVITY";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_patient);

        Context context = getApplicationContext();

        mName = (TextView)findViewById(R.id.input_name);
        mGender = (Spinner)findViewById(R.id.gender);
        mAge = (Spinner)findViewById(R.id.age);
        mSave = (Button)findViewById(R.id.save);


        initSpinner(mGender,context,R.array.gender);
        initSpinner(mAge,context,R.array.age);

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    public void initSpinner(Spinner s, Context c, int res){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(c,
                res,R.layout.spinnertextview);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        s.setAdapter(adapter);
    }

    public void save(){

        String name = mName.getText().toString();
        String age = mAge.getSelectedItem().toString();
        String gender = mGender.getSelectedItem().toString();
        Log.d(TAG,"name :"+name+" age : "+age+" gender : "+gender);


        if(name.isEmpty()){
            mName.setError(getString(R.string.error_empty_name));
            return;
        }

        Intent i = new Intent(this,DetectDiseaseActivity.class);
        i.putExtra(Globals.NAME_KEY,name);
        i.putExtra(Globals.AGE_KEY,age);
        i.putExtra(Globals.GENDER_KEY,gender);

        startActivity(i);
        finish();
    }



}
