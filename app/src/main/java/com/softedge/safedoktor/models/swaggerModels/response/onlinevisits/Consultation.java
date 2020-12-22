
package com.softedge.safedoktor.models.swaggerModels.response.onlinevisits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Consultation {

    @SerializedName("appointmentid")
    @Expose
    private int appointmentid;
    @SerializedName("cancelled")
    @Expose
    private boolean cancelled;
    @SerializedName("cancelledid")
    @Expose
    private String cancelledid;
    @SerializedName("cancelledtime")
    @Expose
    private String cancelledtime;
    @SerializedName("chatid")
    @Expose
    private int chatid;
    @SerializedName("conendtime")
    @Expose
    private String conendtime;
    @SerializedName("conrequestid")
    @Expose
    private int conrequestid;
    @SerializedName("consultationtypeid")
    @Expose
    private int consultationtypeid;
    @SerializedName("contime")
    @Expose
    private String contime;
    @SerializedName("createtime")
    @Expose
    private String createtime;
    @SerializedName("createuserid")
    @Expose
    private String createuserid;
    @SerializedName("doctor")
    @Expose
    private Doctor doctor;
    @SerializedName("doctorid")
    @Expose
    private String doctorid;
    @SerializedName("doctorremarks")
    @Expose
    private String doctorremarks;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("outcomeid")
    @Expose
    private int outcomeid;
    @SerializedName("patient")
    @Expose
    private Patient patient;
    @SerializedName("patientage")
    @Expose
    private String patientage;
    @SerializedName("patientid")
    @Expose
    private int patientid;
    @SerializedName("reviewdate")
    @Expose
    private String reviewdate;
    @SerializedName("servertime")
    @Expose
    private String servertime;
    @SerializedName("updatetime")
    @Expose
    private String updatetime;
    @SerializedName("updateuserid")
    @Expose
    private String updateuserid;

    public int getAppointmentid() {
        return appointmentid;
    }

    public void setAppointmentid(int appointmentid) {
        this.appointmentid = appointmentid;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public String getCancelledid() {
        return cancelledid;
    }

    public void setCancelledid(String cancelledid) {
        this.cancelledid = cancelledid;
    }

    public String getCancelledtime() {
        return cancelledtime;
    }

    public void setCancelledtime(String cancelledtime) {
        this.cancelledtime = cancelledtime;
    }

    public int getChatid() {
        return chatid;
    }

    public void setChatid(int chatid) {
        this.chatid = chatid;
    }

    public String getConendtime() {
        return conendtime;
    }

    public void setConendtime(String conendtime) {
        this.conendtime = conendtime;
    }

    public int getConrequestid() {
        return conrequestid;
    }

    public void setConrequestid(int conrequestid) {
        this.conrequestid = conrequestid;
    }

    public int getConsultationtypeid() {
        return consultationtypeid;
    }

    public void setConsultationtypeid(int consultationtypeid) {
        this.consultationtypeid = consultationtypeid;
    }

    public String getContime() {
        return contime;
    }

    public void setContime(String contime) {
        this.contime = contime;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(String createuserid) {
        this.createuserid = createuserid;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public String getDoctorremarks() {
        return doctorremarks;
    }

    public void setDoctorremarks(String doctorremarks) {
        this.doctorremarks = doctorremarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOutcomeid() {
        return outcomeid;
    }

    public void setOutcomeid(int outcomeid) {
        this.outcomeid = outcomeid;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getPatientage() {
        return patientage;
    }

    public void setPatientage(String patientage) {
        this.patientage = patientage;
    }

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public String getReviewdate() {
        return reviewdate;
    }

    public void setReviewdate(String reviewdate) {
        this.reviewdate = reviewdate;
    }

    public String getServertime() {
        return servertime;
    }

    public void setServertime(String servertime) {
        this.servertime = servertime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdateuserid() {
        return updateuserid;
    }

    public void setUpdateuserid(String updateuserid) {
        this.updateuserid = updateuserid;
    }

}
