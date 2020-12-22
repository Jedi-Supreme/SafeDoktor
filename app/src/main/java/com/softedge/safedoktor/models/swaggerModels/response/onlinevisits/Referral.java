
package com.softedge.safedoktor.models.swaggerModels.response.onlinevisits;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Referral {

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
    @SerializedName("documentmimetype")
    @Expose
    private String documentmimetype;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("referraldoctorname")
    @Expose
    private String referraldoctorname;
    @SerializedName("referraldocument")
    @Expose
    private List<String> referraldocument = null;
    @SerializedName("referralpurpose")
    @Expose
    private String referralpurpose;
    @SerializedName("refferedprovider")
    @Expose
    private Refferedprovider refferedprovider;
    @SerializedName("refferedproviderid")
    @Expose
    private String refferedproviderid;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("updatetime")
    @Expose
    private String updatetime;
    @SerializedName("updateuserid")
    @Expose
    private String updateuserid;

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

    public String getDocumentmimetype() {
        return documentmimetype;
    }

    public void setDocumentmimetype(String documentmimetype) {
        this.documentmimetype = documentmimetype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReferraldoctorname() {
        return referraldoctorname;
    }

    public void setReferraldoctorname(String referraldoctorname) {
        this.referraldoctorname = referraldoctorname;
    }

    public List<String> getReferraldocument() {
        return referraldocument;
    }

    public void setReferraldocument(List<String> referraldocument) {
        this.referraldocument = referraldocument;
    }

    public String getReferralpurpose() {
        return referralpurpose;
    }

    public void setReferralpurpose(String referralpurpose) {
        this.referralpurpose = referralpurpose;
    }

    public Refferedprovider getRefferedprovider() {
        return refferedprovider;
    }

    public void setRefferedprovider(Refferedprovider refferedprovider) {
        this.refferedprovider = refferedprovider;
    }

    public String getRefferedproviderid() {
        return refferedproviderid;
    }

    public void setRefferedproviderid(String refferedproviderid) {
        this.refferedproviderid = refferedproviderid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
