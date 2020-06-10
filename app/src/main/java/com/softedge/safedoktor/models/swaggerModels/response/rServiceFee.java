package com.softedge.safedoktor.models.swaggerModels.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.softedge.safedoktor.utilities.AppConstants.*;

public class rServiceFee {
    
    @SerializedName(KEY_AGE_GROUP_ID)
    @Expose
    private int agegroupid;
    
    @SerializedName("consultationtyid")
    @Expose
    private int consultationtyid;
    
    @SerializedName(KEY_CREATE_TIME)
    @Expose
    private String createtime;
    
    @SerializedName(KEY_CREATE_USER_ID)
    @Expose
    private String createuserid;
    
    @SerializedName("currencyid")
    @Expose
    private int currencyid;
    
    @SerializedName("effectivedate")
    @Expose
    private String effectivedate;
    
    @SerializedName(KEY_FEE)
    @Expose
    private int fee;
    
    @SerializedName("feetypeid")
    @Expose
    private int feetypeid;
    
    @SerializedName(KEY_GENDER_GROUP_ID)
    @Expose
    private int gendergroupid;
    
    @SerializedName(KEY_ID)
    @Expose
    private int id;
    
    @SerializedName("isactive")
    @Expose
    private Boolean isactive;
    
    @SerializedName(KEY_SERVER_TIME)
    @Expose
    private String servertime;
    
    @SerializedName(KEY_SERVICE_ID)
    @Expose
    private int serviceid;
    
    @SerializedName(KEY_SERVICE_TYPE_ID)
    @Expose
    private int servicetypeid;
    
    @SerializedName("sponsorid")
    @Expose
    private int sponsorid;
    
    @SerializedName(KEY_UPDATE_TIME)
    @Expose
    private String updatetime;
    
    @SerializedName(KEY_UPDATE_USER_ID)
    @Expose
    private String updateuserid;

    public int getAgegroupid() {
        return agegroupid;
    }

    public void setAgegroupid(int agegroupid) {
        this.agegroupid = agegroupid;
    }

    public int getConsultationtyid() {
        return consultationtyid;
    }

    public void setConsultationtyid(int consultationtyid) {
        this.consultationtyid = consultationtyid;
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

    public int getCurrencyid() {
        return currencyid;
    }

    public void setCurrencyid(int currencyid) {
        this.currencyid = currencyid;
    }

    public String getEffectivedate() {
        return effectivedate;
    }

    public void setEffectivedate(String effectivedate) {
        this.effectivedate = effectivedate;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getFeetypeid() {
        return feetypeid;
    }

    public void setFeetypeid(int feetypeid) {
        this.feetypeid = feetypeid;
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

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public String getServertime() {
        return servertime;
    }

    public void setServertime(String servertime) {
        this.servertime = servertime;
    }

    public int getServiceid() {
        return serviceid;
    }

    public void setServiceid(int serviceid) {
        this.serviceid = serviceid;
    }

    public int getServicetypeid() {
        return servicetypeid;
    }

    public void setServicetypeid(int servicetypeid) {
        this.servicetypeid = servicetypeid;
    }

    public int getSponsorid() {
        return sponsorid;
    }

    public void setSponsorid(int sponsorid) {
        this.sponsorid = sponsorid;
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
