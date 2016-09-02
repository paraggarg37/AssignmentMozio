package com.mozio.mozioassignment;

import com.mozio.mozioassignment.Models.Patient;
import com.mozio.mozioassignment.Models.Symptoms;
import com.mozio.mozioassignment.library.Diseases;
import com.mozio.mozioassignment.library.ToddsSyndrome;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {

        //Test case to validate our library code

        Patient patient = new Patient("parag","Male","1-15");
        Symptoms s1 = new Symptoms("have migraines","true");
        Symptoms s2 = new Symptoms("Use hallucinogenic drugs","true");
        patient.addSymptom(s1);
        patient.addSymptom(s2);

        ArrayList<Symptoms> symptomsArrayList = new ArrayList<>();

        symptomsArrayList.add(s1);
        symptomsArrayList.add(s2);

        Diseases d = new ToddsSyndrome("Toads Syndrome",symptomsArrayList);
        assertThat(d.patientHasDisease(patient), is(100));
    }
}