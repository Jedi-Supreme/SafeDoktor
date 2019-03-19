package com.softedge.safedoktor.models.fireModels.PatientPackage;

import com.softedge.safedoktor.models.fireModels.HistoryPackage.History;

public class Physicals {

    public final static String TABLE = "PHYSICALS";
    public final static String ID = Biography.ID;
    public final static String LAST_UPDATED = History.LAST_UPDATED;
    public final static String FIREBASE_ID = Biography.FIREBASE_ID;
    public final static String BLOOD_GROUP = "blood_group";
    public final static String HEIGHT = "height";
    public final static String WEIGHT = "weight";

    private String user_fireID;
    private String lastUpdated;
    private int blood_group;
    private double height;
    private double weight;

    Physicals() {
    }

    public Physicals(String user_fireID, int blood_group, double height, double weight, String lastUpdated) {
        this.user_fireID = user_fireID;
        this.blood_group = blood_group;
        this.height = height;
        this.weight = weight;
        this.lastUpdated = lastUpdated;
    }

    public String getUser_fireID() {
        return user_fireID;
    }

    public void setUser_fireID(String user_fireID) {
        this.user_fireID = user_fireID;
    }

    public int getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(int blood_group) {
        this.blood_group = blood_group;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
