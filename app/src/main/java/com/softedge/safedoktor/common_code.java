package com.softedge.safedoktor;

import android.support.design.widget.Snackbar;
import android.view.View;

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
}


