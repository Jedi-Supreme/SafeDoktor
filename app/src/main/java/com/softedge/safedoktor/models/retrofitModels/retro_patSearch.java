package com.softedge.safedoktor.models.retrofitModels;

public class retro_patSearch {

    private String firstName;
    private String lastName;
    private String gender;
    private String patientId;

    public retro_patSearch(String firstName, String lastName, String gender, String patientId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
