
package com.softedge.safedoktor.models.swaggerModels.response.onlinevisits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clinicalnote {

    @SerializedName("cancelled")
    @Expose
    private boolean cancelled;
    @SerializedName("cancelledid")
    @Expose
    private String cancelledid;
    @SerializedName("cancelledtime")
    @Expose
    private String cancelledtime;
    @SerializedName("clinicalnotes")
    @Expose
    private String clinicalnotes;
    @SerializedName("comtime")
    @Expose
    private String comtime;
    @SerializedName("consultationid")
    @Expose
    private int consultationid;
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
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("servertime")
    @Expose
    private String servertime;
    @SerializedName("updatetime")
    @Expose
    private String updatetime;
    @SerializedName("updateuserid")
    @Expose
    private String updateuserid;

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

    public String getClinicalnotes() {
        return clinicalnotes;
    }

    public void setClinicalnotes(String clinicalnotes) {
        this.clinicalnotes = clinicalnotes;
    }

    public String getComtime() {
        return comtime;
    }

    public void setComtime(String comtime) {
        this.comtime = comtime;
    }

    public int getConsultationid() {
        return consultationid;
    }

    public void setConsultationid(int consultationid) {
        this.consultationid = consultationid;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
