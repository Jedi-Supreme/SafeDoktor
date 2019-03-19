package com.softedge.safedoktor.models.fireModels.PatientPackage;

public class Address {

    public static String TABLE = "ADDRESS";
    public static String ID = Biography.ID;
    public static String FIREBASE_ID = Biography.FIREBASE_ID;
    public static String LOC_NAME = "location_name";
    public static String GHPOST = "ghPost_address";
    public static String LONGITUDE = "longitude";
    public static String LATITUDE = "latitude";

    private String user_fireId;
    private String loc_name;
    private String gh_post;
    private double latitude;
    private double longitude;

    Address() {
    }

    Address(String user_fireId, String gh_post) {
        this.user_fireId = user_fireId;
        this.gh_post = gh_post;
    }

    public Address(String user_fireId, String loc_name, double latitude, double longitude) {
        this.user_fireId = user_fireId;
        this.loc_name = loc_name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getUser_fireId() {
        return user_fireId;
    }

    public void setUser_fireId(String user_fireId) {
        this.user_fireId = user_fireId;
    }

    public String getLoc_name() {
        return loc_name;
    }

    public void setLoc_name(String loc_name) {
        this.loc_name = loc_name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getGh_post() {
        return gh_post;
    }

    public void setGh_post(String gh_post) {
        this.gh_post = gh_post;
    }
}
