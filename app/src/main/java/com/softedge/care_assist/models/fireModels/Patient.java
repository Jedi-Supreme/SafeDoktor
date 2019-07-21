package com.softedge.care_assist.models.fireModels;

import com.softedge.care_assist.models.fireModels.PatientPackage.Address;
import com.softedge.care_assist.models.fireModels.PatientPackage.Biography;
import com.softedge.care_assist.models.fireModels.PatientPackage.ContactPerson;
import com.softedge.care_assist.models.fireModels.PatientPackage.MedicalHistory;
import com.softedge.care_assist.models.fireModels.PatientPackage.Physicals;

public class Patient {

    private String patientId;
    private Biography biography;
    private Address address;
    private Physicals physicals;
    private ContactPerson contactPerson;
    private MedicalHistory medicalHistory;

    Patient(){}

    Patient(Biography biography){
        this.biography = biography;
    }

    public Patient(Biography biography, Address address, Physicals physicals){
        this.biography = biography;
        this.address = address;
        this.physicals = physicals;
    }

    public Patient(Biography biography, Address address, Physicals physicals, ContactPerson contactPerson){
        this.biography = biography;
        this.address = address;
        this.physicals = physicals;
        this.contactPerson = contactPerson;
    }

    Patient(Biography biography, Address address, Physicals physicals,
            ContactPerson contactPerson,MedicalHistory medicalHistory){
        this.biography = biography;
        this.address = address;
        this.physicals = physicals;
        this.contactPerson = contactPerson;
        this.medicalHistory = medicalHistory;
    }

    public Biography getBiography() {
        return biography;
    }

    public void setBiography(Biography biography) {
        this.biography = biography;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Physicals getPhysicals() {
        return physicals;
    }

    public void setPhysicals(Physicals physicals) {
        this.physicals = physicals;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

}
