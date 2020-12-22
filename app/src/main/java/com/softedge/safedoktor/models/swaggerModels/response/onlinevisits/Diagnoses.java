
package com.softedge.safedoktor.models.swaggerModels.response.onlinevisits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Diagnoses {

    @SerializedName("cancelled")
    @Expose
    private boolean cancelled;
    @SerializedName("cancelledid")
    @Expose
    private String cancelledid;
    @SerializedName("cancelledtime")
    @Expose
    private String cancelledtime;
    @SerializedName("consultationid")
    @Expose
    private int consultationid;
    @SerializedName("consultationtime")
    @Expose
    private String consultationtime;
    @SerializedName("createtime")
    @Expose
    private String createtime;
    @SerializedName("createuserid")
    @Expose
    private String createuserid;
    @SerializedName("diagcategory")
    @Expose
    private Diagcategory diagcategory;
    @SerializedName("diagcategoryid")
    @Expose
    private int diagcategoryid;
    @SerializedName("diagmanagement")
    @Expose
    private String diagmanagement;
    @SerializedName("diagtype")
    @Expose
    private Diagtype diagtype;
    @SerializedName("diagtypeid")
    @Expose
    private int diagtypeid;
    @SerializedName("diaoutcomeid")
    @Expose
    private int diaoutcomeid;
    @SerializedName("disease")
    @Expose
    private Disease disease;
    @SerializedName("diseaseid")
    @Expose
    private String diseaseid;
    @SerializedName("doctor")
    @Expose
    private Doctor doctor;
    @SerializedName("doctorid")
    @Expose
    private String doctorid;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("isprincipal")
    @Expose
    private boolean isprincipal;
    @SerializedName("nonpharmacologicaltreatment")
    @Expose
    private String nonpharmacologicaltreatment;
    @SerializedName("outcome")
    @Expose
    private Outcome outcome;
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

    public int getConsultationid() {
        return consultationid;
    }

    public void setConsultationid(int consultationid) {
        this.consultationid = consultationid;
    }

    public String getConsultationtime() {
        return consultationtime;
    }

    public void setConsultationtime(String consultationtime) {
        this.consultationtime = consultationtime;
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

    public Diagcategory getDiagcategory() {
        return diagcategory;
    }

    public void setDiagcategory(Diagcategory diagcategory) {
        this.diagcategory = diagcategory;
    }

    public int getDiagcategoryid() {
        return diagcategoryid;
    }

    public void setDiagcategoryid(int diagcategoryid) {
        this.diagcategoryid = diagcategoryid;
    }

    public String getDiagmanagement() {
        return diagmanagement;
    }

    public void setDiagmanagement(String diagmanagement) {
        this.diagmanagement = diagmanagement;
    }

    public Diagtype getDiagtype() {
        return diagtype;
    }

    public void setDiagtype(Diagtype diagtype) {
        this.diagtype = diagtype;
    }

    public int getDiagtypeid() {
        return diagtypeid;
    }

    public void setDiagtypeid(int diagtypeid) {
        this.diagtypeid = diagtypeid;
    }

    public int getDiaoutcomeid() {
        return diaoutcomeid;
    }

    public void setDiaoutcomeid(int diaoutcomeid) {
        this.diaoutcomeid = diaoutcomeid;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public String getDiseaseid() {
        return diseaseid;
    }

    public void setDiseaseid(String diseaseid) {
        this.diseaseid = diseaseid;
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

    public boolean isIsprincipal() {
        return isprincipal;
    }

    public void setIsprincipal(boolean isprincipal) {
        this.isprincipal = isprincipal;
    }

    public String getNonpharmacologicaltreatment() {
        return nonpharmacologicaltreatment;
    }

    public void setNonpharmacologicaltreatment(String nonpharmacologicaltreatment) {
        this.nonpharmacologicaltreatment = nonpharmacologicaltreatment;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
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
