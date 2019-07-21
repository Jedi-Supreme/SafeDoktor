package com.softedge.care_assist.models.fireModels.PatientPackage;

public class Address {

    public static String TABLE = "ADDRESS";
    public static String ID = Biography.ID;
    public static String FIREBASE_ID = Biography.FIREBASE_ID;
    public static String LOC_ADDRESS = "loc_address";
    public static String GHPOST = "ghPost_address";
    public static String LONGITUDE = "longitude";
    public static String LATITUDE = "latitude";

    private String user_fireId;
    private String loc_address;

    public Address() {
    }

    public Address(String user_fireId, String loc_address) {
        this.user_fireId = user_fireId;
        this.loc_address = loc_address;
    }

    public String getUser_fireId() {
        return user_fireId;
    }

    public void setUser_fireId(String user_fireId) {
        this.user_fireId = user_fireId;
    }

    public String getLoc_address() {
        return loc_address;
    }

    public void setLoc_address(String loc_address) {
        this.loc_address = loc_address;
    }
}
