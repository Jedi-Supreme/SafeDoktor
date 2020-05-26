package com.softedge.safedoktor.models.swaggerModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

import static com.softedge.safedoktor.utilities.StringConstants.*;
@Entity(tableName = TABLE_PATIENTS,indices = {@Index(value = KEY_PHONE_NUMBER, unique = true)})
public class PatientModel {

    public PatientModel() {
    }

    @ColumnInfo(name = KEY_PATIENT_ID)
    @Expose
    @PrimaryKey()
    private Integer patientid;

    @ColumnInfo(name = KEY_FIRSTNAME)
    @Expose
    private String firstname;

    @ColumnInfo(name = KEY_LASTNAME)
    @Expose
    private String lastname;

    @ColumnInfo(name = "phoneno")
    @Expose
    private String phoneno;

    @ColumnInfo(name = KEY_DOB)
    @Expose
    private String dateofbirth;

    @ColumnInfo(name = KEY_ACC_NUMBER)
    @Expose
    private String accountnumber;

    @ColumnInfo(name = KEY_ADDRESS)
    @Expose
    private String address;

    @ColumnInfo(name = KEY_BLOOD_GROUP)
    @Expose
    private Integer bloodgroupid;

    @ColumnInfo(name = KEY_CREATE_TIME)
    @Expose
    private String createtime;

    @ColumnInfo(name = KEY_CREATE_USERID)
    @Expose
    private String createuserid;

    @ColumnInfo(name = KEY_DENOMIATION_ID)
    @Expose
    private Integer denominationid;

    @ColumnInfo(name = KEY_DISTRICT_ID)
    @Expose
    private Integer districtid;

    @ColumnInfo(name = KEY_EDU_LEVEL)
    @Expose
    private Integer educationallevelid;

    @ColumnInfo(name = KEY_EMAIL)
    @Expose
    private String email;
    
    @ColumnInfo(name = KEY_ETHNICITY)
    @Expose
    private Integer ethnicityid;

    @ColumnInfo(name = KEY_GENDER)
    @Expose
    private Integer gendergroupid;

    @ColumnInfo(name = KEY_HEIGHT)
    @Expose
    private Double height;

    @ColumnInfo(name = KEY_HOMEDISTRICT)
    @Expose
    private Integer homedistrictid;

    @ColumnInfo(name = KEY_HOME_REGION)
    @Expose
    private Integer homeregionid;

    @ColumnInfo(name = KEY_HOME_STREET)
    @Expose
    private String homestreet;

    @ColumnInfo(name = "hometelno")
    @Expose
    private String hometelno;

    @ColumnInfo(name = "hometownid")
    @Expose
    private Integer hometownid;

    @ColumnInfo(name = "identificationtypeid")
    @Expose
    private Integer identificationtypeid;

    @ColumnInfo(name = "idnumber")
    @Expose
    private String idnumber;

    @ColumnInfo(name = "isactive")
    @Expose
    private Boolean isactive;

    @ColumnInfo(name = "islocked")
    @Expose
    private Boolean islocked;

    @ColumnInfo(name = "locationlatitude")
    @Expose
    private String locationlatitude;

    @ColumnInfo(name = "locationlongitude")
    @Expose
    private String locationlongitude;

    @ColumnInfo(name = "maritalstatusid")
    @Expose
    private Integer maritalstatusid;

    @ColumnInfo(name = "middlename")
    @Expose
    private String middlename;

    @ColumnInfo(name = "nationalityid")
    @Expose
    private Integer nationalityid;

    @ColumnInfo(name = "occupationid")
    @Expose
    private Integer occupationid;

    @ColumnInfo(name = "password")
    @Expose
    private String password;

    @ColumnInfo(name = "patientcategorytypeid")
    @Expose
    private Integer patientcategorytypeid;

    @ColumnInfo(name = "patientstatusid")
    @Expose
    private Integer patientstatusid;

    @ColumnInfo(name = KEY_PHONE_NUMBER)
    @Expose
    private String phonenumber;

    @ColumnInfo(name = "regionid")
    @Expose
    private Integer regionid;

    @ColumnInfo(name = "registrationstatus")
    @Expose
    private String registrationstatus;

    @ColumnInfo(name = "registrationtime")
    @Expose
    private String registrationtime;

    @ColumnInfo(name = "religionid")
    @Expose
    private Integer religionid;

    @ColumnInfo(name = "servertime")
    @Expose
    private String servertime;

    @ColumnInfo(name = "smsalert")
    @Expose
    private Boolean smsalert;

    @ColumnInfo(name = "streetaddress")
    @Expose
    private String streetaddress;

    @ColumnInfo(name = "titleid")
    @Expose
    private Integer titleid;

    @ColumnInfo(name = "townid")
    @Expose
    private Integer townid;

    @ColumnInfo(name = "updatetime")
    @Expose
    private String updatetime;

    @ColumnInfo(name = "updateuserid")
    @Expose
    private String updateuserid;

    @ColumnInfo(name = "username")
    @Expose
    private String username;

    @ColumnInfo(name = "weight")
    @Expose
    private Double weight;

    @ColumnInfo(name = "workdistrictid")
    @Expose
    private Integer workdistrictid;

    @ColumnInfo(name = "workregionid")
    @Expose
    private Integer workregionid;

    @ColumnInfo(name = "workstreet")
    @Expose
    private String workstreet;

    @ColumnInfo(name = "worktelno")
    @Expose
    private String worktelno;

    @ColumnInfo(name = "worktownid")
    @Expose
    private Integer worktownid;

    public Integer getPatientid() {
        return patientid;
    }

    public void setPatientid(Integer patientid) {
        this.patientid = patientid;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Integer getGendergroupid() {
        return gendergroupid;
    }

    public void setGendergroupid(Integer gendergroupid) {
        this.gendergroupid = gendergroupid;
    }


    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getHomestreet() {
        return homestreet;
    }

    public void setHomestreet(String homestreet) {
        this.homestreet = homestreet;
    }

    public String getHometelno() {
        return hometelno;
    }

    public void setHometelno(String hometelno) {
        this.hometelno = hometelno;
    }



    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public Boolean getIslocked() {
        return islocked;
    }

    public void setIslocked(Boolean islocked) {
        this.islocked = islocked;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLocationlatitude() {
        return locationlatitude;
    }

    public void setLocationlatitude(String locationlatitude) {
        this.locationlatitude = locationlatitude;
    }

    public String getLocationlongitude() {
        return locationlongitude;
    }

    public void setLocationlongitude(String locationlongitude) {
        this.locationlongitude = locationlongitude;
    }


    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }


    public String getRegistrationstatus() {
        return registrationstatus;
    }

    public void setRegistrationstatus(String registrationstatus) {
        this.registrationstatus = registrationstatus;
    }

    public String getRegistrationtime() {
        return registrationtime;
    }

    public void setRegistrationtime(String registrationtime) {
        this.registrationtime = registrationtime;
    }

    public String getServertime() {
        return servertime;
    }

    public void setServertime(String servertime) {
        this.servertime = servertime;
    }

    public Boolean getSmsalert() {
        return smsalert;
    }

    public void setSmsalert(Boolean smsalert) {
        this.smsalert = smsalert;
    }

    public String getStreetaddress() {
        return streetaddress;
    }

    public void setStreetaddress(String streetaddress) {
        this.streetaddress = streetaddress;
    }


    public Integer getTownid() {
        return townid;
    }

    public void setTownid(Integer townid) {
        this.townid = townid;
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





    public String getWorkstreet() {
        return workstreet;
    }

    public void setWorkstreet(String workstreet) {
        this.workstreet = workstreet;
    }

    public String getWorktelno() {
        return worktelno;
    }

    public void setWorktelno(String worktelno) {
        this.worktelno = worktelno;
    }

    public Integer getBloodgroupid() {
        return bloodgroupid;
    }

    public void setBloodgroupid(Integer bloodgroupid) {
        this.bloodgroupid = bloodgroupid;
    }

    public Integer getDenominationid() {
        return denominationid;
    }

    public void setDenominationid(Integer denominationid) {
        this.denominationid = denominationid;
    }

    public Integer getDistrictid() {
        return districtid;
    }

    public void setDistrictid(Integer districtid) {
        this.districtid = districtid;
    }

    public Integer getEducationallevelid() {
        return educationallevelid;
    }

    public void setEducationallevelid(Integer educationallevelid) {
        this.educationallevelid = educationallevelid;
    }

    public Integer getEthnicityid() {
        return ethnicityid;
    }

    public void setEthnicityid(Integer ethnicityid) {
        this.ethnicityid = ethnicityid;
    }

    public Integer getHomedistrictid() {
        return homedistrictid;
    }

    public void setHomedistrictid(Integer homedistrictid) {
        this.homedistrictid = homedistrictid;
    }

    public Integer getHomeregionid() {
        return homeregionid;
    }

    public void setHomeregionid(Integer homeregionid) {
        this.homeregionid = homeregionid;
    }

    public Integer getHometownid() {
        return hometownid;
    }

    public void setHometownid(Integer hometownid) {
        this.hometownid = hometownid;
    }

    public Integer getIdentificationtypeid() {
        return identificationtypeid;
    }

    public void setIdentificationtypeid(Integer identificationtypeid) {
        this.identificationtypeid = identificationtypeid;
    }

    public Integer getMaritalstatusid() {
        return maritalstatusid;
    }

    public void setMaritalstatusid(Integer maritalstatusid) {
        this.maritalstatusid = maritalstatusid;
    }

    public Integer getNationalityid() {
        return nationalityid;
    }

    public void setNationalityid(Integer nationalityid) {
        this.nationalityid = nationalityid;
    }

    public Integer getOccupationid() {
        return occupationid;
    }

    public void setOccupationid(Integer occupationid) {
        this.occupationid = occupationid;
    }

    public Integer getPatientcategorytypeid() {
        return patientcategorytypeid;
    }

    public void setPatientcategorytypeid(Integer patientcategorytypeid) {
        this.patientcategorytypeid = patientcategorytypeid;
    }

    public Integer getPatientstatusid() {
        return patientstatusid;
    }

    public void setPatientstatusid(Integer patientstatusid) {
        this.patientstatusid = patientstatusid;
    }

    public Integer getRegionid() {
        return regionid;
    }

    public void setRegionid(Integer regionid) {
        this.regionid = regionid;
    }

    public Integer getReligionid() {
        return religionid;
    }

    public void setReligionid(Integer religionid) {
        this.religionid = religionid;
    }

    public Integer getTitleid() {
        return titleid;
    }

    public void setTitleid(Integer titleid) {
        this.titleid = titleid;
    }

    public Integer getWorkdistrictid() {
        return workdistrictid;
    }

    public void setWorkdistrictid(Integer workdistrictid) {
        this.workdistrictid = workdistrictid;
    }

    public Integer getWorkregionid() {
        return workregionid;
    }

    public void setWorkregionid(Integer workregionid) {
        this.workregionid = workregionid;
    }

    public Integer getWorktownid() {
        return worktownid;
    }

    public void setWorktownid(Integer worktownid) {
        this.worktownid = worktownid;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
}
