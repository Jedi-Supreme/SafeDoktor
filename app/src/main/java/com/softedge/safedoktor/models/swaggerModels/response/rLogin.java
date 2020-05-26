package com.softedge.safedoktor.models.swaggerModels.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.softedge.safedoktor.models.swaggerModels.PatientModel;

public class rLogin {

    @SerializedName("accessToken")
    @Expose
    private rToken accessToken;

    @SerializedName("patient")
    @Expose
    private PatientModel patient;

    public rToken getTokenModel() {
        return accessToken;
    }

    public void setTokenModel(rToken accessToken) {
        this.accessToken = accessToken;
    }

    public PatientModel getPatient() {
        return patient;
    }

    public void setPatient(PatientModel patient) {
        this.patient = patient;
    }
}
