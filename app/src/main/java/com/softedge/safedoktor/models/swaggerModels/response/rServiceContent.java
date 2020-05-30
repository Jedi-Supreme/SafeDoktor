package com.softedge.safedoktor.models.swaggerModels.response;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.softedge.safedoktor.utilities.AppConstants.*;

@Entity(tableName = TABLE_SERVICE_CONTENT)
public class rServiceContent {

    @ColumnInfo(name = KEY_AGE_GROUP_ID)
    @SerializedName(KEY_AGE_GROUP_ID)
    @Expose
    private int agegroupid;

    @ColumnInfo(name = KEY_ALLOW_COPAY)
    @SerializedName(KEY_ALLOW_COPAY)
    @Expose
    private Boolean allowcopay;

    @ColumnInfo(name = KEY_CREATE_TIME)
    @SerializedName(KEY_CREATE_TIME)
    @Expose
    private String createtime;

    @ColumnInfo(name = KEY_CREATE_USER_ID)
    @SerializedName(KEY_CREATE_USER_ID)
    @Expose
    private String createuserid;

    @ColumnInfo(name = KEY_GENDER_GROUP_ID)
    @SerializedName(KEY_GENDER_GROUP_ID)
    @Expose
    private int gendergroupid;

    @ColumnInfo(name = KEY_ID)
    @SerializedName(KEY_ID)
    @Expose
    private int id;

    @ColumnInfo(name = KEY_IS_ACTIVE)
    @SerializedName(KEY_IS_ACTIVE)
    @Expose
    private Boolean isactive;

    @ColumnInfo(name = KEY_NAME)
    @SerializedName(KEY_NAME)
    @Expose
    private String name;

    @ColumnInfo(name = KEY_PAT_STATUS_ID)
    @SerializedName(KEY_PAT_STATUS_ID)
    @Expose
    private int patientstatusid;

    @ColumnInfo(name = KEY_SERVER_TIME)
    @SerializedName(KEY_SERVER_TIME)
    @Expose
    private String servertime;

    @ColumnInfo(name = KEY_SERVICE_CAT_ID)
    @SerializedName(KEY_SERVICE_CAT_ID)
    @Expose
    private int servicecategoryid;

    @ColumnInfo(name = KEY_SERVICE_PLACE_ID)
    @SerializedName(KEY_SERVICE_PLACE_ID)
    @Expose
    private int serviceplaceid;

    @ColumnInfo(name = KEY_SERVICE_SUB_CAT)
    @SerializedName(KEY_SERVICE_SUB_CAT)
    @Expose
    private int servicesubcategoryid;

    @ColumnInfo(name = KEY_SERVICE_TYPE_ID)
    @SerializedName(KEY_SERVICE_TYPE_ID)
    @Expose
    private int servicetypeid;

    @ColumnInfo(name = KEY_UPDATE_TIME)
    @SerializedName(KEY_UPDATE_TIME)
    @Expose
    private String updatetime;

    @ColumnInfo(name = KEY_UPDATE_USER_ID)
    @SerializedName(KEY_UPDATE_USER_ID)
    @Expose
    private String updateuserid;

    public rServiceContent() {
    }

    public int getAgegroupid() {
        return agegroupid;
    }

    public void setAgegroupid(int agegroupid) {
        this.agegroupid = agegroupid;
    }

    public Boolean getAllowcopay() {
        return allowcopay;
    }

    public void setAllowcopay(Boolean allowcopay) {
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

    public int getServicecategoryid() {
        return servicecategoryid;
    }

    public void setServicecategoryid(int servicecategoryid) {
        this.servicecategoryid = servicecategoryid;
    }

    public int getServiceplaceid() {
        return serviceplaceid;
    }

    public void setServiceplaceid(int serviceplaceid) {
        this.serviceplaceid = serviceplaceid;
    }

    public int getServicesubcategoryid() {
        return servicesubcategoryid;
    }

    public void setServicesubcategoryid(int servicesubcategoryid) {
        this.servicesubcategoryid = servicesubcategoryid;
    }

    public int getServicetypeid() {
        return servicetypeid;
    }

    public void setServicetypeid(int servicetypeid) {
        this.servicetypeid = servicetypeid;
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
