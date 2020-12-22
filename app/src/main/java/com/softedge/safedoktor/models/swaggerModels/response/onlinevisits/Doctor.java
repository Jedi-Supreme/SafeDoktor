
package com.softedge.safedoktor.models.swaggerModels.response.onlinevisits;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Doctor {

    @SerializedName("accesslevelpermissions")
    @Expose
    private List<Accesslevelpermission> accesslevelpermissions = null;
    @SerializedName("accountexpirydate")
    @Expose
    private String accountexpirydate;
    @SerializedName("biography")
    @Expose
    private String biography;
    @SerializedName("createtime")
    @Expose
    private String createtime;
    @SerializedName("createuserid")
    @Expose
    private String createuserid;
    @SerializedName("credentialsexpired")
    @Expose
    private boolean credentialsexpired;
    @SerializedName("dateofbirth")
    @Expose
    private String dateofbirth;
    @SerializedName("defaultlanguage")
    @Expose
    private Defaultlanguage defaultlanguage;
    @SerializedName("defaultlanguageid")
    @Expose
    private int defaultlanguageid;
    @SerializedName("disabledtime")
    @Expose
    private String disabledtime;
    @SerializedName("emailaddress")
    @Expose
    private String emailaddress;
    @SerializedName("enabled")
    @Expose
    private boolean enabled;
    @SerializedName("expired")
    @Expose
    private boolean expired;
    @SerializedName("failedpasswordattemptcount")
    @Expose
    private int failedpasswordattemptcount;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("forcepasswordchange")
    @Expose
    private boolean forcepasswordchange;
    @SerializedName("gendergroup")
    @Expose
    private Gendergroup gendergroup;
    @SerializedName("gendergroupid")
    @Expose
    private int gendergroupid;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("lastLogintime")
    @Expose
    private String lastLogintime;
    @SerializedName("lastPasswordchangedtime")
    @Expose
    private String lastPasswordchangedtime;
    @SerializedName("lastlockedouttime")
    @Expose
    private String lastlockedouttime;
    @SerializedName("lastlogintime")
    @Expose
    private String lastlogintime;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("locked")
    @Expose
    private boolean locked;
    @SerializedName("othername")
    @Expose
    private String othername;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("passwordanswer")
    @Expose
    private String passwordanswer;
    @SerializedName("passwordhint")
    @Expose
    private String passwordhint;
    @SerializedName("passwordquestion")
    @Expose
    private String passwordquestion;
    @SerializedName("primarycellno")
    @Expose
    private String primarycellno;
    @SerializedName("residentcountry")
    @Expose
    private Residentcountry residentcountry;
    @SerializedName("residentcountryid")
    @Expose
    private int residentcountryid;
    @SerializedName("role")
    @Expose
    private Role role;
    @SerializedName("title")
    @Expose
    private Title title;
    @SerializedName("titleid")
    @Expose
    private int titleid;
    @SerializedName("uendergroupid")
    @Expose
    private int uendergroupid;
    @SerializedName("updatetime")
    @Expose
    private String updatetime;
    @SerializedName("updateuserid")
    @Expose
    private String updateuserid;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("userprofileid")
    @Expose
    private int userprofileid;
    @SerializedName("usertype")
    @Expose
    private Usertype usertype;
    @SerializedName("usertypeid")
    @Expose
    private int usertypeid;

    public List<Accesslevelpermission> getAccesslevelpermissions() {
        return accesslevelpermissions;
    }

    public void setAccesslevelpermissions(List<Accesslevelpermission> accesslevelpermissions) {
        this.accesslevelpermissions = accesslevelpermissions;
    }

    public String getAccountexpirydate() {
        return accountexpirydate;
    }

    public void setAccountexpirydate(String accountexpirydate) {
        this.accountexpirydate = accountexpirydate;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
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

    public boolean isCredentialsexpired() {
        return credentialsexpired;
    }

    public void setCredentialsexpired(boolean credentialsexpired) {
        this.credentialsexpired = credentialsexpired;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public Defaultlanguage getDefaultlanguage() {
        return defaultlanguage;
    }

    public void setDefaultlanguage(Defaultlanguage defaultlanguage) {
        this.defaultlanguage = defaultlanguage;
    }

    public int getDefaultlanguageid() {
        return defaultlanguageid;
    }

    public void setDefaultlanguageid(int defaultlanguageid) {
        this.defaultlanguageid = defaultlanguageid;
    }

    public String getDisabledtime() {
        return disabledtime;
    }

    public void setDisabledtime(String disabledtime) {
        this.disabledtime = disabledtime;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public int getFailedpasswordattemptcount() {
        return failedpasswordattemptcount;
    }

    public void setFailedpasswordattemptcount(int failedpasswordattemptcount) {
        this.failedpasswordattemptcount = failedpasswordattemptcount;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public boolean isForcepasswordchange() {
        return forcepasswordchange;
    }

    public void setForcepasswordchange(boolean forcepasswordchange) {
        this.forcepasswordchange = forcepasswordchange;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastLogintime() {
        return lastLogintime;
    }

    public void setLastLogintime(String lastLogintime) {
        this.lastLogintime = lastLogintime;
    }

    public String getLastPasswordchangedtime() {
        return lastPasswordchangedtime;
    }

    public void setLastPasswordchangedtime(String lastPasswordchangedtime) {
        this.lastPasswordchangedtime = lastPasswordchangedtime;
    }

    public String getLastlockedouttime() {
        return lastlockedouttime;
    }

    public void setLastlockedouttime(String lastlockedouttime) {
        this.lastlockedouttime = lastlockedouttime;
    }

    public String getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(String lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordanswer() {
        return passwordanswer;
    }

    public void setPasswordanswer(String passwordanswer) {
        this.passwordanswer = passwordanswer;
    }

    public String getPasswordhint() {
        return passwordhint;
    }

    public void setPasswordhint(String passwordhint) {
        this.passwordhint = passwordhint;
    }

    public String getPasswordquestion() {
        return passwordquestion;
    }

    public void setPasswordquestion(String passwordquestion) {
        this.passwordquestion = passwordquestion;
    }

    public String getPrimarycellno() {
        return primarycellno;
    }

    public void setPrimarycellno(String primarycellno) {
        this.primarycellno = primarycellno;
    }

    public Residentcountry getResidentcountry() {
        return residentcountry;
    }

    public void setResidentcountry(Residentcountry residentcountry) {
        this.residentcountry = residentcountry;
    }

    public int getResidentcountryid() {
        return residentcountryid;
    }

    public void setResidentcountryid(int residentcountryid) {
        this.residentcountryid = residentcountryid;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public int getTitleid() {
        return titleid;
    }

    public void setTitleid(int titleid) {
        this.titleid = titleid;
    }

    public int getUendergroupid() {
        return uendergroupid;
    }

    public void setUendergroupid(int uendergroupid) {
        this.uendergroupid = uendergroupid;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserprofileid() {
        return userprofileid;
    }

    public void setUserprofileid(int userprofileid) {
        this.userprofileid = userprofileid;
    }

    public Usertype getUsertype() {
        return usertype;
    }

    public void setUsertype(Usertype usertype) {
        this.usertype = usertype;
    }

    public int getUsertypeid() {
        return usertypeid;
    }

    public void setUsertypeid(int usertypeid) {
        this.usertypeid = usertypeid;
    }

}
