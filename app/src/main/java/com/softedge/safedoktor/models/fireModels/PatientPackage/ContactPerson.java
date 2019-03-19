package com.softedge.safedoktor.models.fireModels.PatientPackage;

public class ContactPerson {

    public static final String TABLE = "CONTACTS";
    public static final String ID = Biography.ID;
    public static final String ADDRESS = "address";
    public static final String FIRE_ID = Biography.FIREBASE_ID;
    public static final String FULL_NAME = "fullname";
    public static final String EMAIL = Biography.EMAIL;
    public static final String MOBILE_NUMBER = Biography.MOBILE_NUMBER;
    public static final String RELATION = "relation";

    private String user_fireID;
    private String fullname;
    private String email;
    private String number;
    private String address;
    private int relation;

    public ContactPerson() {
    }

    public ContactPerson(
            String user_fireID, String fullname,
            String email, String mobile_number, String address, int relation) {

        this.fullname = fullname;
        this.user_fireID = user_fireID;
        this.email = email;
        this.number = mobile_number;
        this.address = address;
        this.relation = relation;
    }

    public String getUser_fireID() {
        return user_fireID;
    }

    public void setUser_fireID(String user_fireID) {
        this.user_fireID = user_fireID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
