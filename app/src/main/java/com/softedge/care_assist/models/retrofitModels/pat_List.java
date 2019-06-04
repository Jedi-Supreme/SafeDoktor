package com.softedge.care_assist.models.retrofitModels;

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
