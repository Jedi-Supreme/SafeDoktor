package com.softedge.safedoktor.models;

public class Patient {

    public final static String TABLE = "PATIENT";
    public final static String FIREBASE_ID = "firebase_id";
    public final static String EMAIL = "email";
    public final static String FIRSTNAME = "firstname";
    public final static String GENDER = "gender";
    public final static String LASTNAME = "lastname";
    public final static String MOBILE_NUMBER = "mobile_number";
    public final static String COUNTRY_CODE = "country_code";
    public final static String DATE_OF_BIRTH = "dob";
    public final static String PASSWORD = "password";



    private String firebase_Uid;
    private String email;

    private String firstname;
    private String lastname;

    private int mobile_number;
    private String propic_url;

    private String country_code;
    private String address;

    private double latitude;
    private double longitutde;

    private String date_of_birth;
    private int gender;

    public Patient(){}

    public Patient(String fn, String ln, int gender,String country_code,int mobile_number,String date_of_birth){
        firstname = fn;
        lastname = ln;
        this.gender = gender;
        this.country_code = country_code;
        this.mobile_number = mobile_number;
        this.date_of_birth = date_of_birth;
    }

    public Patient(String fn, String ln,int gender,String country_code,int mobile_number, String email, String date_of_birth){
        firstname = fn;
        lastname = ln;
        this.gender = gender;
        this.country_code = country_code;
        this.mobile_number = mobile_number;
        this.date_of_birth = date_of_birth;
        this.email = email;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(int mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
