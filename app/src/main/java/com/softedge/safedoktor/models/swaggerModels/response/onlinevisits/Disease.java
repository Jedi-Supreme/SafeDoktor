
package com.softedge.safedoktor.models.swaggerModels.response.onlinevisits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Disease {

    @SerializedName("agegroup")
    @Expose
    private Agegroup agegroup;
    @SerializedName("agegroupid")
    @Expose
    private int agegroupid;
    @SerializedName("classid")
    @Expose
    private int classid;
    @SerializedName("complaintno")
    @Expose
    private int complaintno;
    @SerializedName("createtime")
    @Expose
    private String createtime;
    @SerializedName("createuserid")
    @Expose
    private String createuserid;
    @SerializedName("diseaseclass")
    @Expose
    private Diseaseclass diseaseclass;
    @SerializedName("gendergroup")
    @Expose
    private Gendergroup gendergroup;
    @SerializedName("gendergroupid")
    @Expose
    private int gendergroupid;
    @SerializedName("icdid")
    @Expose
    private String icdid;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("isactive")
    @Expose
    private boolean isactive;
    @SerializedName("ischronic")
    @Expose
    private boolean ischronic;
    @SerializedName("maindiseaseid")
    @Expose
    private String maindiseaseid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("requiresinves")
    @Expose
    private boolean requiresinves;
    @SerializedName("requiresurgery")
    @Expose
    private boolean requiresurgery;
    @SerializedName("servertime")
    @Expose
    private String servertime;
    @SerializedName("treatmentguide")
    @Expose
    private String treatmentguide;
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

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public int getComplaintno() {
        return complaintno;
    }

    public void setComplaintno(int complaintno) {
        this.complaintno = complaintno;
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

    public Diseaseclass getDiseaseclass() {
        return diseaseclass;
    }

    public void setDiseaseclass(Diseaseclass diseaseclass) {
        this.diseaseclass = diseaseclass;
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

    public String getIcdid() {
        return icdid;
    }

    public void setIcdid(String icdid) {
        this.icdid = icdid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public boolean isIschronic() {
        return ischronic;
    }

    public void setIschronic(boolean ischronic) {
        this.ischronic = ischronic;
    }

    public String getMaindiseaseid() {
        return maindiseaseid;
    }

    public void setMaindiseaseid(String maindiseaseid) {
        this.maindiseaseid = maindiseaseid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRequiresinves() {
        return requiresinves;
    }

    public void setRequiresinves(boolean requiresinves) {
        this.requiresinves = requiresinves;
    }

    public boolean isRequiresurgery() {
        return requiresurgery;
    }

    public void setRequiresurgery(boolean requiresurgery) {
        this.requiresurgery = requiresurgery;
    }

    public String getServertime() {
        return servertime;
    }

    public void setServertime(String servertime) {
        this.servertime = servertime;
    }

    public String getTreatmentguide() {
        return treatmentguide;
    }

    public void setTreatmentguide(String treatmentguide) {
        this.treatmentguide = treatmentguide;
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
