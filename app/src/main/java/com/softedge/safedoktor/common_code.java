package com.softedge.safedoktor;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.softedge.safedoktor.models.Patient;

public class common_code {

    public static Snackbar Mysnackbar(View parent_view, String message, int lenght){

        final Snackbar snackbar =  Snackbar.make(parent_view,message,lenght);
        snackbar.setActionTextColor(parent_view.getContext().getResources().getColor(R.color.colorPrimary));
        snackbar.setAction("Close", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });

        return snackbar;
    }

    public static Patient patientFromBundle(Bundle patbundle){

        Patient new_patient;

        new_patient = new Patient(
                patbundle.getString(Patient.FIRSTNAME),
                patbundle.getString(Patient.LASTNAME),
                patbundle.getInt(Patient.GENDER),
                patbundle.getString(Patient.COUNTRY_CODE),
                patbundle.getInt(Patient.MOBILE_NUMBER),
                patbundle.getString(Patient.EMAIL),
                patbundle.getString(Patient.DATE_OF_BIRTH)
        );

        return new_patient;
    }
}


