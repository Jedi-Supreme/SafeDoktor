package com.softedge.safedoktor.fireModels.HistoryPackage;

public class FamilyHistory extends History {

    public static final String TABLE = "FAMILY";

    public FamilyHistory() {
    }

    public FamilyHistory(String user_fireID, String state, String remarks, int question_number, String lastUpdated) {
        super(user_fireID, state, remarks, question_number, lastUpdated);
    }
}
