package com.softedge.safedoktor.models.fireModels;


import com.softedge.safedoktor.models.fireModels.PatientPackage.Biography;

public class Dependant_class {

    public final static String TABLE = "DEPENDANTS";
    public final static String PARENT_FIRE_ID = Biography.FIREBASE_ID;
    public final static String ID = "_id";
    public final static String OPD_ID = "opd_id";
    public final static String FIRSTNAME = "firstname";
    public final static String GENDER = "gender";
    public final static String LASTNAME = "lastname";
    public final static String MOBILE_NUMBER = "mobile_number";
    public final static String DATE_OF_BIRTH = "dob";
    public final static String MARITAL_STATUS = "marital_status";

    private String depend_opd_id;
    private String parent_fireID;

    private String depend_firstname;
    private String depend_lastname;

    private String mobile_number;
    private String date_of_birth;

    private int gender;
    private int marital_state;

    public Dependant_class(String depend_opd_id, String parent_fireID, String depend_firstname, String depend_lastname, String mobile_number, String date_of_birth, int gender, int marital_state) {
        this.depend_opd_id = depend_opd_id;
        this.parent_fireID = parent_fireID;
        this.depend_firstname = depend_firstname;
        this.depend_lastname = depend_lastname;
        this.mobile_number = mobile_number;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.marital_state = marital_state;
    }

    public String getDepend_opd_id() {
        return depend_opd_id;
    }

    public void setDepend_opd_id(String depend_opd_id) {
        this.depend_opd_id = depend_opd_id;
    }

    public String getDepend_firstname() {
        return depend_firstname;
    }

    public void setDepend_firstname(String depend_firstname) {
        this.depend_firstname = depend_firstname;
    }

    public String getDepend_lastname() {
        return depend_lastname;
    }

    public void setDepend_lastname(String depend_lastname) {
        this.depend_lastname = depend_lastname;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getMarital_state() {
        return marital_state;
    }

    public void setMarital_state(int marital_state) {
        this.marital_state = marital_state;
    }

    public String getParent_fireID() {
        return parent_fireID;
    }

    public void setParent_fireID(String parent_fireID) {
        this.parent_fireID = parent_fireID;
    }
}
