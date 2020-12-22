
package com.softedge.safedoktor.models.swaggerModels.response.onlinevisits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Patient {

    @SerializedName("accountnumber")
    @Expose
    private String accountnumber;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("bloodgroup")
    @Expose
    private Bloodgroup bloodgroup;
    @SerializedName("bloodgroupid")
    @Expose
    private int bloodgroupid;
    @SerializedName("cellphoneno")
    @Expose
    private String cellphoneno;
    @SerializedName("createtime")
    @Expose
    private String createtime;
    @SerializedName("createuserid")
    @Expose
    private String createuserid;
    @SerializedName("dateofbirth")
    @Expose
    private String dateofbirth;
    @SerializedName("denomination")
    @Expose
    private Denomination denomination;
    @SerializedName("denominationid")
    @Expose
    private int denominationid;
    @SerializedName("districtid")
    @Expose
    private int districtid;
    @SerializedName("educationallevel")
    @Expose
    private Educationallevel educationallevel;
    @SerializedName("educationallevelid")
    @Expose
    private int educationallevelid;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("ethnicity")
    @Expose
    private Ethnicity ethnicity;
    @SerializedName("ethnicityid")
    @Expose
    private int ethnicityid;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("gendergroup")
    @Expose
    private Gendergroup gendergroup;
    @SerializedName("gendergroupid")
    @Expose
    private int gendergroupid;
    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("homedistrictid")
    @Expose
    private int homedistrictid;
    @SerializedName("homeregionid")
    @Expose
    private int homeregionid;
    @SerializedName("homestreet")
    @Expose
    private String homestreet;
    @SerializedName("hometelno")
    @Expose
    private String hometelno;
    @SerializedName("hometown")
    @Expose
    private Hometown hometown;
    @SerializedName("hometownid")
    @Expose
    private int hometownid;
    @SerializedName("identificationtype")
    @Expose
    private Identificationtype identificationtype;
    @SerializedName("identificationtypeid")
    @Expose
    private int identificationtypeid;
    @SerializedName("idnumber")
    @Expose
    private String idnumber;
    @SerializedName("isactive")
    @Expose
    private boolean isactive;
    @SerializedName("islocked")
    @Expose
    private boolean islocked;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("locationlatitude")
    @Expose
    private String locationlatitude;
    @SerializedName("locationlongitude")
    @Expose
    private String locationlongitude;
    @SerializedName("maritalstatus")
    @Expose
    private Maritalstatus maritalstatus;
    @SerializedName("maritalstatusid")
    @Expose
    private int maritalstatusid;
    @SerializedName("middlename")
    @Expose
    private String middlename;
    @SerializedName("nationality")
    @Expose
    private Nationality nationality;
    @SerializedName("nationalityid")
    @Expose
    private int nationalityid;
    @SerializedName("occupation")
    @Expose
    private Occupation occupation;
    @SerializedName("occupationid")
    @Expose
    private int occupationid;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("patientcategorytypeid")
    @Expose
    private int patientcategorytypeid;
    @SerializedName("patientid")
    @Expose
    private int patientid;
    @SerializedName("patientstatusid")
    @Expose
    private int patientstatusid;
    @SerializedName("phonenumber")
    @Expose
    private String phonenumber;
    @SerializedName("regionid")
    @Expose
    private int regionid;
    @SerializedName("registrationstatus")
    @Expose
    private String registrationstatus;
    @SerializedName("registrationtime")
    @Expose
    private String registrationtime;
    @SerializedName("religionid")
    @Expose
    private int religionid;
    @SerializedName("servertime")
    @Expose
    private String servertime;
    @SerializedName("smsalert")
    @Expose
    private boolean smsalert;
    @SerializedName("streetaddress")
    @Expose
    private String streetaddress;
    @SerializedName("titleid")
    @Expose
    private int titleid;
    @SerializedName("town")
    @Expose
    private Town town;
    @SerializedName("townid")
    @Expose
    private int townid;
    @SerializedName("updatetime")
    @Expose
    private String updatetime;
    @SerializedName("updateuserid")
    @Expose
    private String updateuserid;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("weight")
    @Expose
    private int weight;
    @SerializedName("workdistrictid")
    @Expose
    private int workdistrictid;
    @SerializedName("workregionid")
    @Expose
    private int workregionid;
    @SerializedName("workstreet")
    @Expose
    private String workstreet;
    @SerializedName("worktelno")
    @Expose
    private String worktelno;
    @SerializedName("worktown")
    @Expose
    private Worktown worktown;
    @SerializedName("worktownid")
    @Expose
    private int worktownid;

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

    public Bloodgroup getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(Bloodgroup bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public int getBloodgroupid() {
        return bloodgroupid;
    }

    public void setBloodgroupid(int bloodgroupid) {
        this.bloodgroupid = bloodgroupid;
    }

    public String getCellphoneno() {
        return cellphoneno;
    }

    public void setCellphoneno(String cellphoneno) {
        this.cellphoneno = cellphoneno;
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

    public Denomination getDenomination() {
        return denomination;
    }

    public void setDenomination(Denomination denomination) {
        this.denomination = denomination;
    }

    public int getDenominationid() {
        return denominationid;
    }

    public void setDenominationid(int denominationid) {
        this.denominationid = denominationid;
    }

    public int getDistrictid() {
        return districtid;
    }

    public void setDistrictid(int districtid) {
        this.districtid = districtid;
    }

    public Educationallevel getEducationallevel() {
        return educationallevel;
    }

    public void setEducationallevel(Educationallevel educationallevel) {
        this.educationallevel = educationallevel;
    }

    public int getEducationallevelid() {
        return educationallevelid;
    }

    public void setEducationallevelid(int educationallevelid) {
        this.educationallevelid = educationallevelid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Ethnicity getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(Ethnicity ethnicity) {
        this.ethnicity = ethnicity;
    }

    public int getEthnicityid() {
        return ethnicityid;
    }

    public void setEthnicityid(int ethnicityid) {
        this.ethnicityid = ethnicityid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHomedistrictid() {
        return homedistrictid;
    }

    public void setHomedistrictid(int homedistrictid) {
        this.homedistrictid = homedistrictid;
    }

    public int getHomeregionid() {
        return homeregionid;
    }

    public void setHomeregionid(int homeregionid) {
        this.homeregionid = homeregionid;
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

    public Hometown getHometown() {
        return hometown;
    }

    public void setHometown(Hometown hometown) {
        this.hometown = hometown;
    }

    public int getHometownid() {
        return hometownid;
    }

    public void setHometownid(int hometownid) {
        this.hometownid = hometownid;
    }

    public Identificationtype getIdentificationtype() {
        return identificationtype;
    }

    public void setIdentificationtype(Identificationtype identificationtype) {
        this.identificationtype = identificationtype;
    }

    public int getIdentificationtypeid() {
        return identificationtypeid;
    }

    public void setIdentificationtypeid(int identificationtypeid) {
        this.identificationtypeid = identificationtypeid;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public boolean isIslocked() {
        return islocked;
    }

    public void setIslocked(boolean islocked) {
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

    public Maritalstatus getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(Maritalstatus maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public int getMaritalstatusid() {
        return maritalstatusid;
    }

    public void setMaritalstatusid(int maritalstatusid) {
        this.maritalstatusid = maritalstatusid;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public int getNationalityid() {
        return nationalityid;
    }

    public void setNationalityid(int nationalityid) {
        this.nationalityid = nationalityid;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public int getOccupationid() {
        return occupationid;
    }

    public void setOccupationid(int occupationid) {
        this.occupationid = occupationid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPatientcategorytypeid() {
        return patientcategorytypeid;
    }

    public void setPatientcategorytypeid(int patientcategorytypeid) {
        this.patientcategorytypeid = patientcategorytypeid;
    }

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public int getPatientstatusid() {
        return patientstatusid;
    }

    public void setPatientstatusid(int patientstatusid) {
        this.patientstatusid = patientstatusid;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getRegionid() {
        return regionid;
    }

    public void setRegionid(int regionid) {
        this.regionid = regionid;
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

    public int getReligionid() {
        return religionid;
    }

    public void setReligionid(int religionid) {
        this.religionid = religionid;
    }

    public String getServertime() {
        return servertime;
    }

    public void setServertime(String servertime) {
        this.servertime = servertime;
    }

    public boolean isSmsalert() {
        return smsalert;
    }

    public void setSmsalert(boolean smsalert) {
        this.smsalert = smsalert;
    }

    public String getStreetaddress() {
        return streetaddress;
    }

    public void setStreetaddress(String streetaddress) {
        this.streetaddress = streetaddress;
    }

    public int getTitleid() {
        return titleid;
    }

    public void setTitleid(int titleid) {
        this.titleid = titleid;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public int getTownid() {
        return townid;
    }

    public void setTownid(int townid) {
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWorkdistrictid() {
        return workdistrictid;
    }

    public void setWorkdistrictid(int workdistrictid) {
        this.workdistrictid = workdistrictid;
    }

    public int getWorkregionid() {
        return workregionid;
    }

    public void setWorkregionid(int workregionid) {
        this.workregionid = workregionid;
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

    public Worktown getWorktown() {
        return worktown;
    }

    public void setWorktown(Worktown worktown) {
        this.worktown = worktown;
    }

    public int getWorktownid() {
        return worktownid;
    }

    public void setWorktownid(int worktownid) {
        this.worktownid = worktownid;
    }

}
