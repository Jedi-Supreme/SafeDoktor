
package com.softedge.safedoktor.models.swaggerModels.response.onlinevisits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fee {

    @SerializedName("agegroup")
    @Expose
    private Agegroup agegroup;
    @SerializedName("agegroupid")
    @Expose
    private int agegroupid;
    @SerializedName("chattype")
    @Expose
    private Chattype chattype;
    @SerializedName("consultationchattypeid")
    @Expose
    private int consultationchattypeid;
    @SerializedName("createtime")
    @Expose
    private String createtime;
    @SerializedName("createuserid")
    @Expose
    private String createuserid;
    @SerializedName("currency")
    @Expose
    private Currency currency;
    @SerializedName("currencyid")
    @Expose
    private int currencyid;
    @SerializedName("effectivedate")
    @Expose
    private String effectivedate;
    @SerializedName("emergencyfee")
    @Expose
    private int emergencyfee;
    @SerializedName("fee")
    @Expose
    private int fee;
    @SerializedName("feetype")
    @Expose
    private Feetype feetype;
    @SerializedName("feetypeid")
    @Expose
    private int feetypeid;
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
    @SerializedName("reviewfee")
    @Expose
    private int reviewfee;
    @SerializedName("servertime")
    @Expose
    private String servertime;
    @SerializedName("serviceid")
    @Expose
    private String serviceid;
    @SerializedName("servicetype")
    @Expose
    private Servicetype servicetype;
    @SerializedName("servicetypeid")
    @Expose
    private int servicetypeid;
    @SerializedName("sponsorid")
    @Expose
    private int sponsorid;
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

    public Chattype getChattype() {
        return chattype;
    }

    public void setChattype(Chattype chattype) {
        this.chattype = chattype;
    }

    public int getConsultationchattypeid() {
        return consultationchattypeid;
    }

    public void setConsultationchattypeid(int consultationchattypeid) {
        this.consultationchattypeid = consultationchattypeid;
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
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

    public int getEmergencyfee() {
        return emergencyfee;
    }

    public void setEmergencyfee(int emergencyfee) {
        this.emergencyfee = emergencyfee;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public Feetype getFeetype() {
        return feetype;
    }

    public void setFeetype(Feetype feetype) {
        this.feetype = feetype;
    }

    public int getFeetypeid() {
        return feetypeid;
    }

    public void setFeetypeid(int feetypeid) {
        this.feetypeid = feetypeid;
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

    public int getReviewfee() {
        return reviewfee;
    }

    public void setReviewfee(int reviewfee) {
        this.reviewfee = reviewfee;
    }

    public String getServertime() {
        return servertime;
    }

    public void setServertime(String servertime) {
        this.servertime = servertime;
    }

    public String getServiceid() {
        return serviceid;
    }

    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
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
