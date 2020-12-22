
package com.softedge.safedoktor.models.swaggerModels.response.onlinevisits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Servicecategory {

    @SerializedName("agegroup")
    @Expose
    private Agegroup agegroup;
    @SerializedName("agegroupid")
    @Expose
    private int agegroupid;
    @SerializedName("createtime")
    @Expose
    private String createtime;
    @SerializedName("createuserid")
    @Expose
    private String createuserid;
    @SerializedName("gendergroup")
    @Expose
    private Gendergroup gendergroup;
    @SerializedName("gendergroupid")
    @Expose
    private int gendergroupid;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("isactive")
    @Expose
    private boolean isactive;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("patientstatusid")
    @Expose
    private int patientstatusid;
    @SerializedName("servertime")
    @Expose
    private String servertime;
    @SerializedName("servicetype")
    @Expose
    private Servicetype servicetype;
    @SerializedName("servicetypeid")
    @Expose
    private int servicetypeid;
    @SerializedName("synctime")
    @Expose
    private String synctime;
    @SerializedName("updatetime")
    @Expose
    private String updatetime;
    @SerializedName("updateuserid")
    @Expose
    private String updateuserid;

    public Agegroup getAgegroup() {
        return agegroup;
    }

    public void setAgegroup(Agegroup agegroup) {
        this.agegroup = agegroup;
    }

    public int getAgegroupid() {
        return agegroupid;
    }

    public void setAgegroupid(int agegroupid) {
        this.agegroupid = agegroupid;
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

    public Gendergroup getGendergroup() {
        return gendergroup;
    }

    public void setGendergroup(Gendergroup gendergroup) {
        this.gendergroup = gendergroup;
    }

    public int getGendergroupid() {
        return gendergroupid;
    }

    public void setGendergroupid(int gendergroupid) {
        this.gendergroupid = gendergroupid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPatientstatusid() {
        return patientstatusid;
    }

    public void setPatientstatusid(int patientstatusid) {
        this.patientstatusid = patientstatusid;
    }

    public String getServertime() {
        return servertime;
    }

    public void setServertime(String servertime) {
        this.servertime = servertime;
    }

    public Servicetype getServicetype() {
        return servicetype;
    }

    public void setServicetype(Servicetype servicetype) {
        this.servicetype = servicetype;
    }

    public int getServicetypeid() {
        return servicetypeid;
    }

    public void setServicetypeid(int servicetypeid) {
        this.servicetypeid = servicetypeid;
    }

    public String getSynctime() {
        return synctime;
    }

    public void setSynctime(String synctime) {
        this.synctime = synctime;
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
