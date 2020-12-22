
package com.softedge.safedoktor.models.swaggerModels.response.onlinevisits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Servicerequest {

    @SerializedName("clinialspecialtyid")
    @Expose
    private int clinialspecialtyid;
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
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("requestedquantity")
    @Expose
    private int requestedquantity;
    @SerializedName("requesttime")
    @Expose
    private String requesttime;
    @SerializedName("servertime")
    @Expose
    private String servertime;
    @SerializedName("service")
    @Expose
    private Service service;
    @SerializedName("serviceid")
    @Expose
    private int serviceid;
    @SerializedName("serviceprovider")
    @Expose
    private Serviceprovider serviceprovider;
    @SerializedName("serviceproviderid")
    @Expose
    private String serviceproviderid;
    @SerializedName("specialty")
    @Expose
    private Specialty specialty;
    @SerializedName("updatetime")
    @Expose
    private String updatetime;
    @SerializedName("updateuserid")
    @Expose
    private String updateuserid;

    public int getClinialspecialtyid() {
        return clinialspecialtyid;
    }

    public void setClinialspecialtyid(int clinialspecialtyid) {
        this.clinialspecialtyid = clinialspecialtyid;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getRequestedquantity() {
        return requestedquantity;
    }

    public void setRequestedquantity(int requestedquantity) {
        this.requestedquantity = requestedquantity;
    }

    public String getRequesttime() {
        return requesttime;
    }

    public void setRequesttime(String requesttime) {
        this.requesttime = requesttime;
    }

    public String getServertime() {
        return servertime;
    }

    public void setServertime(String servertime) {
        this.servertime = servertime;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public int getServiceid() {
        return serviceid;
    }

    public void setServiceid(int serviceid) {
        this.serviceid = serviceid;
    }

    public Serviceprovider getServiceprovider() {
        return serviceprovider;
    }

    public void setServiceprovider(Serviceprovider serviceprovider) {
        this.serviceprovider = serviceprovider;
    }

    public String getServiceproviderid() {
        return serviceproviderid;
    }

    public void setServiceproviderid(String serviceproviderid) {
        this.serviceproviderid = serviceproviderid;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
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
