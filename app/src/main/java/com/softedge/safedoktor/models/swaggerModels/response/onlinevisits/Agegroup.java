
package com.softedge.safedoktor.models.swaggerModels.response.onlinevisits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Agegroup {

    @SerializedName("agegroupcategory")
    @Expose
    private Agegroupcategory agegroupcategory;
    @SerializedName("agegroupcategoryid")
    @Expose
    private int agegroupcategoryid;
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
    @SerializedName("lowerageunit")
    @Expose
    private Lowerageunit lowerageunit;
    @SerializedName("lowerageunitid")
    @Expose
    private int lowerageunitid;
    @SerializedName("lowertype")
    @Expose
    private Lowertype lowertype;
    @SerializedName("lowertypeid")
    @Expose
    private int lowertypeid;
    @SerializedName("lowervalue")
    @Expose
    private int lowervalue;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("servertime")
    @Expose
    private String servertime;
    @SerializedName("updatetime")
    @Expose
    private String updatetime;
    @SerializedName("updateuserid")
    @Expose
    private String updateuserid;
    @SerializedName("upperageunit")
    @Expose
    private Upperageunit upperageunit;
    @SerializedName("upperageunitid")
    @Expose
    private int upperageunitid;
    @SerializedName("uppertype")
    @Expose
    private Uppertype uppertype;
    @SerializedName("uppertypeid")
    @Expose
    private int uppertypeid;
    @SerializedName("uppervalue")
    @Expose
    private int uppervalue;

    public Agegroupcategory getAgegroupcategory() {
        return agegroupcategory;
    }

    public void setAgegroupcategory(Agegroupcategory agegroupcategory) {
        this.agegroupcategory = agegroupcategory;
    }

    public int getAgegroupcategoryid() {
        return agegroupcategoryid;
    }

    public void setAgegroupcategoryid(int agegroupcategoryid) {
        this.agegroupcategoryid = agegroupcategoryid;
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

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public Lowerageunit getLowerageunit() {
        return lowerageunit;
    }

    public void setLowerageunit(Lowerageunit lowerageunit) {
        this.lowerageunit = lowerageunit;
    }

    public int getLowerageunitid() {
        return lowerageunitid;
    }

    public void setLowerageunitid(int lowerageunitid) {
        this.lowerageunitid = lowerageunitid;
    }

    public Lowertype getLowertype() {
        return lowertype;
    }

    public void setLowertype(Lowertype lowertype) {
        this.lowertype = lowertype;
    }

    public int getLowertypeid() {
        return lowertypeid;
    }

    public void setLowertypeid(int lowertypeid) {
        this.lowertypeid = lowertypeid;
    }

    public int getLowervalue() {
        return lowervalue;
    }

    public void setLowervalue(int lowervalue) {
        this.lowervalue = lowervalue;
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

    public Upperageunit getUpperageunit() {
        return upperageunit;
    }

    public void setUpperageunit(Upperageunit upperageunit) {
        this.upperageunit = upperageunit;
    }

    public int getUpperageunitid() {
        return upperageunitid;
    }

    public void setUpperageunitid(int upperageunitid) {
        this.upperageunitid = upperageunitid;
    }

    public Uppertype getUppertype() {
        return uppertype;
    }

    public void setUppertype(Uppertype uppertype) {
        this.uppertype = uppertype;
    }

    public int getUppertypeid() {
        return uppertypeid;
    }

    public void setUppertypeid(int uppertypeid) {
        this.uppertypeid = uppertypeid;
    }

    public int getUppervalue() {
        return uppervalue;
    }

    public void setUppervalue(int uppervalue) {
        this.uppervalue = uppervalue;
    }

}
