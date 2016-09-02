package com.mozio.mozioassignment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mozio.mozioassignment.Activities.DetectDiseaseActivity;
import com.mozio.mozioassignment.Globals;
import com.mozio.mozioassignment.Models.Patient;
import com.mozio.mozioassignment.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by PG on 03/09/16.
 */
public class PatientsAdapter extends RecyclerView.Adapter<PatientsAdapter.MyViewHolder> {

    private ArrayList<Patient> patientsList;

    private Context mContext = null;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_list_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Patient data = patientsList.get(position);
        holder.name.setText(data.getName());
        holder.age.setText("Age : "+data.getAge());
        holder.gender.setText("Gender : "+data.getGender());
    }




    @Override
    public int getItemCount() {
        return patientsList.size();
    }

    public PatientsAdapter(ArrayList<Patient> data, Context context) {
        this.patientsList = (ArrayList<Patient>) data.clone();
        this.mContext = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name,age,gender;
        public RelativeLayout container;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            age = (TextView) view.findViewById(R.id.age);
            gender = (TextView) view.findViewById(R.id.gender);
            container = (RelativeLayout)view.findViewById(R.id.container);
            container.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            Patient data = patientsList.get(pos);
            Intent i = new Intent(mContext,DetectDiseaseActivity.class);
            i.putExtra(Globals.PATIENT_KEY,data.getData());
            mContext.startActivity(i);
        }

    }






}

