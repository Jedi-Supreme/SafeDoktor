
package com.softedge.safedoktor.models.swaggerModels.response.onlinevisits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Systemreviews {

    @SerializedName("clinicalsystem")
    @Expose
    private Clinicalsystem clinicalsystem;
    @SerializedName("clinicalsystemid")
    @Expose
    private int clinicalsystemid;
    @SerializedName("consultationid")
    @Expose
    private int consultationid;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("doctoruserid")
    @Expose
    private String doctoruserid;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("symptom")
    @Expose
    private Symptom symptom;
    @SerializedName("symptomsid")
    @Expose
    private int symptomsid;

    public Clinicalsystem getClinicalsystem() {
        return clinicalsystem;
    }

    public void setClinicalsystem(Clinicalsystem clinicalsystem) {
        this.clinicalsystem = clinicalsystem;
    }

    public int getClinicalsystemid() {
        return clinicalsystemid;
    }

    public void setClinicalsystemid(int clinicalsystemid) {
        this.clinicalsystemid = clinicalsystemid;
    }

    public int getConsultationid() {
        return consultationid;
    }

    public void setConsultationid(int consultationid) {
        this.consultationid = consultationid;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getDoctoruserid() {
        return doctoruserid;
    }

    public void setDoctoruserid(String doctoruserid) {
        this.doctoruserid = doctoruserid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Symptom getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }

    public int getSymptomsid() {
        return symptomsid;
    }

    public void setSymptomsid(int symptomsid) {
        this.symptomsid = symptomsid;
    }

}
