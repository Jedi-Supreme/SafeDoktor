package com.softedge.safedoktor.models.fireModels.HistoryPackage;

public class SurgicalHistory extends History {

    public static final String TABLE = "SURGICAL";

    public SurgicalHistory() {
    }

    SurgicalHistory(String user_fireID, String state, String remarks, int question_number, String lastUpdated) {
        super(user_fireID, state, remarks, question_number, lastUpdated);
    }
}
