package com.softedge.safedoktor.models;

import com.softedge.safedoktor.models.PatientPackage.Address;
import com.softedge.safedoktor.models.PatientPackage.Biography;
import com.softedge.safedoktor.models.PatientPackage.ContactPerson;
import com.softedge.safedoktor.models.PatientPackage.MedicalHistory;
import com.softedge.safedoktor.models.PatientPackage.Physicals;

public class Patient {

    private Biography biography;
    private Address address;
    private Physicals physicals;
    private ContactPerson contactPerson;
    private MedicalHistory medicalHistory;


}
