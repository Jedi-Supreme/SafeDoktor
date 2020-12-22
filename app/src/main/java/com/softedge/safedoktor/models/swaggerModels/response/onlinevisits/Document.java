
package com.softedge.safedoktor.models.swaggerModels.response.onlinevisits;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Document {

    @SerializedName("cancelled")
    @Expose
    private boolean cancelled;
    @SerializedName("cancelledid")
    @Expose
    private String cancelledid;
    @SerializedName("cancelledtime")
    @Expose
    private String cancelledtime;
    @SerializedName("condocument")
    @Expose
    private List<String> condocument = null;
    @SerializedName("consultationid")
    @Expose
    private int consultationid;
    @SerializedName("contime")
    @Expose
    private String contime;
    @SerializedName("createtime")
    @Expose
    private String createtime;
    @SerializedName("createuserid")
    @Expose
    private String createuserid;
    @SerializedName("doctitle")
    @Expose
    private String doctitle;
    @SerializedName("doctor")
    @Expose
    private Doctor doctor;
    @SerializedName("doctorid")
    @Expose
    private String doctorid;
    @SerializedName("doctype")
    @Expose
    private int doctype;
    @SerializedName("documentmimetype")
    @Expose
    private String documentmimetype;
    @SerializedName("documenttype")
    @Expose
    private Documenttype documenttype;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("remarks")
    @Expose
    private String remarks;
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

    public List<String> getCondocument() {
        return condocument;
    }

    public void setCondocument(List<String> condocument) {
        this.condocument = condocument;
    }

    public int getConsultationid() {
        return consultationid;
    }

    public void setConsultationid(int consultationid) {
        this.consultationid = consultationid;
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

    public String getDoctitle() {
        return doctitle;
    }

    public void setDoctitle(String doctitle) {
        this.doctitle = doctitle;
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

    public int getDoctype() {
        return doctype;
    }

    public void setDoctype(int doctype) {
        this.doctype = doctype;
    }

    public String getDocumentmimetype() {
        return documentmimetype;
    }

    public void setDocumentmimetype(String documentmimetype) {
        this.documentmimetype = documentmimetype;
    }

    public Documenttype getDocumenttype() {
        return documenttype;
    }

    public void setDocumenttype(Documenttype documenttype) {
        this.documenttype = documenttype;
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
