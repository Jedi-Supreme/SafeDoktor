package com.softedge.safedoktor;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.softedge.safedoktor.models.PatientPackage.Biography;

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

    public static Biography patientFromBundle(Bundle patbundle){

        Biography new_biography;

        new_biography = new Biography(
                patbundle.getString(Biography.FIRSTNAME),
                patbundle.getString(Biography.LASTNAME),
                patbundle.getInt(Biography.GENDER),
                patbundle.getString(Biography.COUNTRY_CODE),
                patbundle.getString(Biography.MOBILE_NUMBER),
                patbundle.getString(Biography.EMAIL),
                patbundle.getString(Biography.DATE_OF_BIRTH)
        );

        return new_biography;
    }
}


