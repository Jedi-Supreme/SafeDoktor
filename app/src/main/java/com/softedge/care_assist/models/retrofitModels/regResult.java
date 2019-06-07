package com.softedge.care_assist.models.retrofitModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class regResult {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("patientid")
    @Expose
    private String patientId;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
