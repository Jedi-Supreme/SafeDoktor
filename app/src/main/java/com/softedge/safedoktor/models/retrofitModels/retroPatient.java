package com.softedge.safedoktor.models.retrofitModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class retroPatient {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("firstName")
    @Expose
    private String firstname;

    @SerializedName("lastName")
    @Expose
    private String lastName;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("currentOccupation")
    @Expose
    private String currentOccupation;

    @SerializedName("nationality")
    @Expose
    private String nationality;

    @SerializedName("birthDate")
    @Expose
    private String dateOfBirth;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("maritalStatus")
    @Expose
    private String maritalStatus;

    @SerializedName("birthPlace")
    @Expose
    private String birthplace;

    @SerializedName("maidenName")
    @Expose
    private String maidenName;

    @SerializedName("middleName")
    @Expose
    private String middleName;

    @SerializedName("patientId")
    @Expose
    private String patientId;

    @SerializedName("religion")
    @Expose
    private String religion;

    public retroPatient(String id, String title, String firstname, String lastName, String phoneNumber,
                        String email, String currentOccupation, String nationality, String dateOfBirth,
                        String gender, String maritalStatus, String birthplace, String maidenName,
                        String middleName, String patientId, String religion) {
        this.id = id;
        this.firstname = firstname;
        this.lastName = lastName;
        this.title = title;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.currentOccupation = currentOccupation;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender.toUpperCase();
        this.maritalStatus = maritalStatus.toUpperCase();
        this.birthplace = birthplace;
        this.maidenName = maidenName;
        this.middleName = middleName;
        this.patientId = patientId;
        this.religion = religion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrentOccupation() {
        return currentOccupation;
    }

    public void setCurrentOccupation(String currentOccupation) {
        this.currentOccupation = currentOccupation;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }
}
