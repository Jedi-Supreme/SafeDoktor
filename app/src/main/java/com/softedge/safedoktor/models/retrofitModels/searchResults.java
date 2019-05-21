package com.softedge.safedoktor.models.retrofitModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class searchResults {

    @SerializedName("patients")
    private List<retroPatient> patientslist;

    public List<retroPatient> getPatientslist() {
        return patientslist;
    }
}
