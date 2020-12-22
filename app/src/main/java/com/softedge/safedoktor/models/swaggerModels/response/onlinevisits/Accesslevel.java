
package com.softedge.safedoktor.models.swaggerModels.response.onlinevisits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Accesslevel {

    @SerializedName("aclid")
    @Expose
    private String aclid;
    @SerializedName("aclmoduleid")
    @Expose
    private int aclmoduleid;
    @SerializedName("aclname")
    @Expose
    private String aclname;
    @SerializedName("canadd")
    @Expose
    private boolean canadd;
    @SerializedName("candelete")
    @Expose
    private boolean candelete;
    @SerializedName("canedit")
    @Expose
    private boolean canedit;
    @SerializedName("canprint")
    @Expose
    private boolean canprint;
    @SerializedName("canview")
    @Expose
    private boolean canview;
    @SerializedName("canviewlog")
    @Expose
    private boolean canviewlog;
    @SerializedName("createtime")
    @Expose
    private String createtime;
    @SerializedName("createuserid")
    @Expose
    private String createuserid;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("updatetime")
    @Expose
    private String updatetime;
    @SerializedName("updateuserid")
    @Expose
    private String updateuserid;

    public String getAclid() {
        return aclid;
    }

    public void setAclid(String aclid) {
        this.aclid = aclid;
    }

    public int getAclmoduleid() {
        return aclmoduleid;
    }

    public void setAclmoduleid(int aclmoduleid) {
        this.aclmoduleid = aclmoduleid;
    }

    public String getAclname() {
        return aclname;
    }

    public void setAclname(String aclname) {
        this.aclname = aclname;
    }

    public boolean isCanadd() {
        return canadd;
    }

    public void setCanadd(boolean canadd) {
        this.canadd = canadd;
    }

    public boolean isCandelete() {
        return candelete;
    }

    public void setCandelete(boolean candelete) {
        this.candelete = candelete;
    }

    public boolean isCanedit() {
        return canedit;
    }

    public void setCanedit(boolean canedit) {
        this.canedit = canedit;
    }

    public boolean isCanprint() {
        return canprint;
    }

    public void setCanprint(boolean canprint) {
        this.canprint = canprint;
    }

    public boolean isCanview() {
        return canview;
    }

    public void setCanview(boolean canview) {
        this.canview = canview;
    }

    public boolean isCanviewlog() {
        return canviewlog;
    }

    public void setCanviewlog(boolean canviewlog) {
        this.canviewlog = canviewlog;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
