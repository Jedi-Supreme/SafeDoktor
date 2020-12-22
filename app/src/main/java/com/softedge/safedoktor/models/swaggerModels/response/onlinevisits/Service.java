
package com.softedge.safedoktor.models.swaggerModels.response.onlinevisits;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Service {

    @SerializedName("agegroup")
    @Expose
    private Agegroup agegroup;
    @SerializedName("agegroupid")
    @Expose
    private int agegroupid;
    @SerializedName("allowcopay")
    @Expose
    private boolean allowcopay;
    @SerializedName("createtime")
    @Expose
    private String createtime;
    @SerializedName("createuserid")
    @Expose
    private String createuserid;
    @SerializedName("fees")
    @Expose
    private List<Fee> fees = null;
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
    @SerializedName("servicecategory")
    @Expose
    private Servicecategory servicecategory;
    @SerializedName("servicecategoryid")
    @Expose
    private int servicecategoryid;
    @SerializedName("serviceplace")
    @Expose
    private Serviceplace serviceplace;
    @SerializedName("serviceplaceid")
    @Expose
    private int serviceplaceid;
    @SerializedName("servicesubcategory")
    @Expose
    private Servicesubcategory servicesubcategory;
    @SerializedName("servicesubcategoryid")
    @Expose
    private int servicesubcategoryid;
    @SerializedName("servicetype")
    @Expose
    private Servicetype servicetype;
    @SerializedName("servicetypeid")
    @Expose
    private int servicetypeid;
    @SerializedName("unit")
    @Expose
    private Unit unit;
    @SerializedName("unitid")
    @Expose
    private int unitid;
    @SerializedName("unitvalue")
    @Expose
    private int unitvalue;
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

    public boolean isAllowcopay() {
        return allowcopay;
    }

    public void setAllowcopay(boolean allowcopay) {
        this.allowcopay = allowcopay;
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

    public List<Fee> getFees() {
        return fees;
    }

    public void setFees(List<Fee> fees) {
        this.fees = fees;
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

    public Servicecategory getServicecategory() {
        return servicecategory;
    }

    public void setServicecategory(Servicecategory servicecategory) {
        this.servicecategory = servicecategory;
    }

    public int getServicecategoryid() {
        return servicecategoryid;
    }

    public void setServicecategoryid(int servicecategoryid) {
        this.servicecategoryid = servicecategoryid;
    }

    public Serviceplace getServiceplace() {
        return serviceplace;
    }

    public void setServiceplace(Serviceplace serviceplace) {
        this.serviceplace = serviceplace;
    }

    public int getServiceplaceid() {
        return serviceplaceid;
    }

    public void setServiceplaceid(int serviceplaceid) {
        this.serviceplaceid = serviceplaceid;
    }

    public Servicesubcategory getServicesubcategory() {
        return servicesubcategory;
    }

    public void setServicesubcategory(Servicesubcategory servicesubcategory) {
        this.servicesubcategory = servicesubcategory;
    }

    public int getServicesubcategoryid() {
        return servicesubcategoryid;
    }

    public void setServicesubcategoryid(int servicesubcategoryid) {
        this.servicesubcategoryid = servicesubcategoryid;
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int getUnitid() {
        return unitid;
    }

    public void setUnitid(int unitid) {
        this.unitid = unitid;
    }

    public int getUnitvalue() {
        return unitvalue;
    }

    public void setUnitvalue(int unitvalue) {
        this.unitvalue = unitvalue;
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
