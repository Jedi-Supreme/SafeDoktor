package com.softedge.safedoktor.models.retrofitModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class pat_List {

    @SerializedName("patients")
    private List<retroPatient> patientslist;

    public void setPatientslist(List<retroPatient> patientslist) {
        this.patientslist = patientslist;
    }

    public List<retroPatient> getPatientslist() {
        return patientslist;
    }
}
