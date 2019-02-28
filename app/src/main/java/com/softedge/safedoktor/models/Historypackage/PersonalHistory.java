package com.softedge.safedoktor.models.Historypackage;

public class PersonalHistory extends History {

    public static final String TABLE = "PERSONAL";

    public PersonalHistory() {
    }

    public PersonalHistory(String user_fireID, String state, String remarks, int question_number, String lastUpdated) {
        super(user_fireID, state, remarks, question_number, lastUpdated);
    }
}
