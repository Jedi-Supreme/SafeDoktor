package com.softedge.safedoktor.models.fireModels.PatientPackage;

public class Biography {

    public final static String TABLE = "BIOGRAPHY";
    public final static String ID = "_id";
    public final static String FIREBASE_ID = "firebase_id";
    public final static String EMAIL = "email";
    public final static String PROFILE_PIC_URL = "profile_pic_url";
    public final static String FIRSTNAME = "firstname";
    public final static String GENDER = "gender";
    public final static String LASTNAME = "lastname";
    public final static String MOBILE_NUMBER = "mobile_number";
    public final static String COUNTRY_CODE = "country_code";
    public final static String DATE_OF_BIRTH = "dob";
    public final static String MARITAL_STATUS = "marital_status";
    public final static String PASSWORD = "password";

    private String firebase_Uid;
    private String email;

    private String firstname;
    private String lastname;

    private String mobile_number;
    private String propic_url;

    private String country_code;
    private Address user_address;
    private String patientId;

    private String date_of_birth;
    private int gender;
    private int marital_state;

    public Biography(){}

    //1st account creation constructor (no email)
    public Biography(String fn, String ln, int gender, String country_code, String mobile_number, String date_of_birth){
        firstname = fn;
        lastname = ln;
        this.gender = gender;
        this.country_code = country_code;
        this.mobile_number = mobile_number;
        this.date_of_birth = date_of_birth;
    }

    //2nd account creation constructor with email
    public Biography(
            String fn, String ln,int gender, String country_code,
            String mobile_number, String email, String date_of_birth){
        firstname = fn;
        lastname = ln;
        this.gender = gender;
        this.country_code = country_code;
        this.mobile_number = mobile_number;
        this.date_of_birth = date_of_birth;
        this.email = email;
    }

    //Firebase patient constructor
    public Biography(String firebase_Uid, String fn, String ln, int gender, String country_code,
                     String mobile_number, String email, String date_of_birth){

        firstname = fn;
        lastname = ln;
        this.firebase_Uid = firebase_Uid;
        this.gender = gender;
        this.country_code = country_code;
        this.mobile_number = mobile_number;
        this.date_of_birth = date_of_birth;
        this.email = email;
    }

    //Firebase married patient constructor
    public Biography(String firebase_Uid, String fn, String ln, int gender, String country_code,
                     String mobile_number, String email, String date_of_birth, int marital_state){

        firstname = fn;
        lastname = ln;
        this.firebase_Uid = firebase_Uid;
        this.gender = gender;
        this.country_code = country_code;
        this.mobile_number = mobile_number;
        this.date_of_birth = date_of_birth;
        this.email = email;
        this.marital_state = marital_state;
    }

    //full patient bio constructor
    public Biography(String firebase_Uid, String fn, String ln, int gender, String country_code,
                     String mobile_number, String email, String date_of_birth, int marital_state, String propic_url){

        firstname = fn;
        lastname = ln;
        this.firebase_Uid = firebase_Uid;
        this.gender = gender;
        this.country_code = country_code;
        this.mobile_number = mobile_number;
        this.date_of_birth = date_of_birth;
        this.email = email;
        this.marital_state = marital_state;
        this.propic_url = propic_url;
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

    public Address getUser_address() {
        return user_address;
    }

    public void setUser_address(Address user_address) {
        this.user_address = user_address;
    }

    public String getFirebase_Uid() {
        return firebase_Uid;
    }

    public void setFirebase_Uid(String firebase_Uid) {
        this.firebase_Uid = firebase_Uid;
    }

    public String getPropic_url() {
        return propic_url;
    }

    public void setPropic_url(String propic_url) {
        this.propic_url = propic_url;
    }

    public int getMarital_state() {
        return marital_state;
    }

    public void setMarital_state(int marital_state) {
        this.marital_state = marital_state;
    }
}
