package com.softedge.safedoktor.fireModels.HistoryPackage;

import com.softedge.safedoktor.fireModels.PatientPackage.Biography;

public class History {

    public static final String LAST_UPDATED = "last_updated";
    public static final String ID = Biography.ID;
    public static final String FIRE_ID = Biography.FIREBASE_ID;
    public static final String STATE = "state";
    public static final String REMARKS = "remarks";
    public static final String QN_NUMBER = "qn_number";

    private String user_fireID;
    private String state;
    private String remarks;
    private String lastUpdated;
    private int qn_numb;

    History() {
    }

    public History(String user_fireID, String state, String remarks, int question_number, String lastUpdated) {

        this.user_fireID = user_fireID;
        this.state = state;
        this.remarks = remarks;
        this.qn_numb = question_number;
        this.lastUpdated = lastUpdated;

    }

    public String getUser_fireID() {
        return user_fireID;
    }

    public void setUser_fireID(String user_fireID) {
        this.user_fireID = user_fireID;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getQn_numb() {
        return qn_numb;
    }

    public void setQn_numb(int qn_numb) {
        this.qn_numb = qn_numb;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
