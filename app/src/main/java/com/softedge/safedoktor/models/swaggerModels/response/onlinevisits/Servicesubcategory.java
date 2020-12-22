
package com.softedge.safedoktor.models.swaggerModels.response.onlinevisits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Servicesubcategory {

    @SerializedName("createtime")
    @Expose
    private String createtime;
    @SerializedName("createuserid")
    @Expose
    private String createuserid;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("isactive")
    @Expose
    private boolean isactive;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("servertime")
    @Expose
    private String servertime;
    @SerializedName("servicecategoryid")
    @Expose
    private int servicecategoryid;
    @SerializedName("synctime")
    @Expose
    private String synctime;
    @SerializedName("updatetime")
    @Expose
    private String updatetime;
    @SerializedName("updateuserid")
    @Expose
    private String updateuserid;

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

    public String getServertime() {
        return servertime;
    }

    public void setServertime(String servertime) {
        this.servertime = servertime;
    }

    public int getServicecategoryid() {
        return servicecategoryid;
    }

    public void setServicecategoryid(int servicecategoryid) {
        this.servicecategoryid = servicecategoryid;
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
