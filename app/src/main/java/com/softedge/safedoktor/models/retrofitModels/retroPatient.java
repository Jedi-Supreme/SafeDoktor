package com.softedge.safedoktor.models.retrofitModels;

import com.google.gson.annotations.SerializedName;

public class retroPatient {

    @SerializedName("patientId")
    String patientId;

    @SerializedName("firstName")
    String firstname;

    @SerializedName("lastName")
    String lastName;

    @SerializedName("Date_of_Birth")
    String dateOfBirth;

    @SerializedName("Gender")
    String gender;

    @SerializedName("Marital_Status")
    String maritalStatua;

    @SerializedName("Current_Facility")
    String currentfacility;

}
