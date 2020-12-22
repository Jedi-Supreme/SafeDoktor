
package com.softedge.safedoktor.models.swaggerModels.response.onlinevisits;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Serviceprovider {

    @SerializedName("accountnumber")
    @Expose
    private String accountnumber;
    @SerializedName("apikey")
    @Expose
    private String apikey;
    @SerializedName("country")
    @Expose
    private Country country;
    @SerializedName("countryid")
    @Expose
    private int countryid;
    @SerializedName("createtime")
    @Expose
    private String createtime;
    @SerializedName("createuserid")
    @Expose
    private String createuserid;
    @SerializedName("dateestablished")
    @Expose
    private String dateestablished;
    @SerializedName("districtid")
    @Expose
    private int districtid;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("facilitylevel")
    @Expose
    private Facilitylevel facilitylevel;
    @SerializedName("facilitylevelid")
    @Expose
    private int facilitylevelid;
    @SerializedName("facilitytype")
    @Expose
    private Facilitytype facilitytype;
    @SerializedName("facilitytypeid")
    @Expose
    private int facilitytypeid;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("healthfacilitycode")
    @Expose
    private String healthfacilitycode;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("ipaddress")
    @Expose
    private String ipaddress;
    @SerializedName("language")
    @Expose
    private Language language;
    @SerializedName("languageid")
    @Expose
    private int languageid;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("logo")
    @Expose
    private List<String> logo = null;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("motto")
    @Expose
    private String motto;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ownershiptype")
    @Expose
    private Ownershiptype ownershiptype;
    @SerializedName("ownershiptypeid")
    @Expose
    private int ownershiptypeid;
    @SerializedName("port")
    @Expose
    private int port;
    @SerializedName("postaladdress")
    @Expose
    private String postaladdress;
    @SerializedName("regionid")
    @Expose
    private int regionid;
    @SerializedName("servertime")
    @Expose
    private String servertime;
    @SerializedName("telno")
    @Expose
    private String telno;
    @SerializedName("themeid")
    @Expose
    private int themeid;
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
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("website")
    @Expose
    private String website;

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getCountryid() {
        return countryid;
    }

    public void setCountryid(int countryid) {
        this.countryid = countryid;
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

    public String getDateestablished() {
        return dateestablished;
    }

    public void setDateestablished(String dateestablished) {
        this.dateestablished = dateestablished;
    }

    public int getDistrictid() {
        return districtid;
    }

    public void setDistrictid(int districtid) {
        this.districtid = districtid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Facilitylevel getFacilitylevel() {
        return facilitylevel;
    }

    public void setFacilitylevel(Facilitylevel facilitylevel) {
        this.facilitylevel = facilitylevel;
    }

    public int getFacilitylevelid() {
        return facilitylevelid;
    }

    public void setFacilitylevelid(int facilitylevelid) {
        this.facilitylevelid = facilitylevelid;
    }

    public Facilitytype getFacilitytype() {
        return facilitytype;
    }

    public void setFacilitytype(Facilitytype facilitytype) {
        this.facilitytype = facilitytype;
    }

    public int getFacilitytypeid() {
        return facilitytypeid;
    }

    public void setFacilitytypeid(int facilitytypeid) {
        this.facilitytypeid = facilitytypeid;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getHealthfacilitycode() {
        return healthfacilitycode;
    }

    public void setHealthfacilitycode(String healthfacilitycode) {
        this.healthfacilitycode = healthfacilitycode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public int getLanguageid() {
        return languageid;
    }

    public void setLanguageid(int languageid) {
        this.languageid = languageid;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getLogo() {
        return logo;
    }

    public void setLogo(List<String> logo) {
        this.logo = logo;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ownershiptype getOwnershiptype() {
        return ownershiptype;
    }

    public void setOwnershiptype(Ownershiptype ownershiptype) {
        this.ownershiptype = ownershiptype;
    }

    public int getOwnershiptypeid() {
        return ownershiptypeid;
    }

    public void setOwnershiptypeid(int ownershiptypeid) {
        this.ownershiptypeid = ownershiptypeid;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPostaladdress() {
        return postaladdress;
    }

    public void setPostaladdress(String postaladdress) {
        this.postaladdress = postaladdress;
    }

    public int getRegionid() {
        return regionid;
    }

    public void setRegionid(int regionid) {
        this.regionid = regionid;
    }

    public String getServertime() {
        return servertime;
    }

    public void setServertime(String servertime) {
        this.servertime = servertime;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public int getThemeid() {
        return themeid;
    }

    public void setThemeid(int themeid) {
        this.themeid = themeid;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}
