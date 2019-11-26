package com.softedge.safedoktor.models.fireModels.HistoryPackage;

public class SocialHistory extends History {

    public static final String TABLE = "SOCIAL";

    public SocialHistory() {
    }

    public SocialHistory(String user_fireID, String state, String remarks, int question_number, String lastUpdated) {
        super(user_fireID, state, remarks, question_number, lastUpdated);
    }
}
