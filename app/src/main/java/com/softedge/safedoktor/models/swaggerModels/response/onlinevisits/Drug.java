
package com.softedge.safedoktor.models.swaggerModels.response.onlinevisits;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Drug {

    @SerializedName("agegroup")
    @Expose
    private Agegroup agegroup;
    @SerializedName("agegroupid")
    @Expose
    private int agegroupid;
    @SerializedName("baseunitid")
    @Expose
    private int baseunitid;
    @SerializedName("baseunitquantity")
    @Expose
    private int baseunitquantity;
    @SerializedName("classid")
    @Expose
    private int classid;
    @SerializedName("clazz")
    @Expose
    private drugClazz drugClazz;
    @SerializedName("defaultmethod")
    @Expose
    private Defaultmethod defaultmethod;
    @SerializedName("defaultmethodid")
    @Expose
    private int defaultmethodid;
    @SerializedName("determineprescriptionquantity")
    @Expose
    private boolean determineprescriptionquantity;
    @SerializedName("drugid")
    @Expose
    private String drugid;
    @SerializedName("extendedname")
    @Expose
    private String extendedname;
    @SerializedName("fees")
    @Expose
    private List<Fee> fees = null;
    @SerializedName("gendergroup")
    @Expose
    private Gendergroup gendergroup;
    @SerializedName("gendergroupid")
    @Expose
    private int gendergroupid;
    @SerializedName("isactive")
    @Expose
    private boolean isactive;
    @SerializedName("isexpirable")
    @Expose
    private boolean isexpirable;
    @SerializedName("itemtype")
    @Expose
    private Itemtype itemtype;
    @SerializedName("itemtypeid")
    @Expose
    private int itemtypeid;
    @SerializedName("manufacturer")
    @Expose
    private Manufacturer manufacturer;
    @SerializedName("manufacturerid")
    @Expose
    private int manufacturerid;
    @SerializedName("maximumprescriptionquantity")
    @Expose
    private int maximumprescriptionquantity;
    @SerializedName("medicationinstructions")
    @Expose
    private String medicationinstructions;
    @SerializedName("mininumprescriptionquantity")
    @Expose
    private int mininumprescriptionquantity;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("picture")
    @Expose
    private List<String> picture = null;
    @SerializedName("prescriptionquantityperbasenuit")
    @Expose
    private int prescriptionquantityperbasenuit;
    @SerializedName("prescriptionunitid")
    @Expose
    private int prescriptionunitid;
    @SerializedName("prescriptionunitquantity")
    @Expose
    private int prescriptionunitquantity;
    @SerializedName("presentation")
    @Expose
    private Presentation presentation;
    @SerializedName("presentationid")
    @Expose
    private int presentationid;
    @SerializedName("sharable")
    @Expose
    private boolean sharable;
    @SerializedName("stocktype")
    @Expose
    private Stocktype stocktype;
    @SerializedName("stocktypeid")
    @Expose
    private int stocktypeid;
    @SerializedName("strengthid")
    @Expose
    private int strengthid;
    @SerializedName("strengthvalue")
    @Expose
    private int strengthvalue;
    @SerializedName("unitmeasure")
    @Expose
    private Unitmeasure unitmeasure;

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

    public int getBaseunitid() {
        return baseunitid;
    }

    public void setBaseunitid(int baseunitid) {
        this.baseunitid = baseunitid;
    }

    public int getBaseunitquantity() {
        return baseunitquantity;
    }

    public void setBaseunitquantity(int baseunitquantity) {
        this.baseunitquantity = baseunitquantity;
    }

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public drugClazz getDrugClazz() {
        return drugClazz;
    }

    public void setDrugClazz(drugClazz drugClazz) {
        this.drugClazz = drugClazz;
    }

    public Defaultmethod getDefaultmethod() {
        return defaultmethod;
    }

    public void setDefaultmethod(Defaultmethod defaultmethod) {
        this.defaultmethod = defaultmethod;
    }

    public int getDefaultmethodid() {
        return defaultmethodid;
    }

    public void setDefaultmethodid(int defaultmethodid) {
        this.defaultmethodid = defaultmethodid;
    }

    public boolean isDetermineprescriptionquantity() {
        return determineprescriptionquantity;
    }

    public void setDetermineprescriptionquantity(boolean determineprescriptionquantity) {
        this.determineprescriptionquantity = determineprescriptionquantity;
    }

    public String getDrugid() {
        return drugid;
    }

    public void setDrugid(String drugid) {
        this.drugid = drugid;
    }

    public String getExtendedname() {
        return extendedname;
    }

    public void setExtendedname(String extendedname) {
        this.extendedname = extendedname;
    }

    public List<Fee> getFees() {
        return fees;
    }

    public void setFees(List<Fee> fees) {
        this.fees = fees;
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

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public boolean isIsexpirable() {
        return isexpirable;
    }

    public void setIsexpirable(boolean isexpirable) {
        this.isexpirable = isexpirable;
    }

    public Itemtype getItemtype() {
        return itemtype;
    }

    public void setItemtype(Itemtype itemtype) {
        this.itemtype = itemtype;
    }

    public int getItemtypeid() {
        return itemtypeid;
    }

    public void setItemtypeid(int itemtypeid) {
        this.itemtypeid = itemtypeid;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getManufacturerid() {
        return manufacturerid;
    }

    public void setManufacturerid(int manufacturerid) {
        this.manufacturerid = manufacturerid;
    }

    public int getMaximumprescriptionquantity() {
        return maximumprescriptionquantity;
    }

    public void setMaximumprescriptionquantity(int maximumprescriptionquantity) {
        this.maximumprescriptionquantity = maximumprescriptionquantity;
    }

    public String getMedicationinstructions() {
        return medicationinstructions;
    }

    public void setMedicationinstructions(String medicationinstructions) {
        this.medicationinstructions = medicationinstructions;
    }

    public int getMininumprescriptionquantity() {
        return mininumprescriptionquantity;
    }

    public void setMininumprescriptionquantity(int mininumprescriptionquantity) {
        this.mininumprescriptionquantity = mininumprescriptionquantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

    public int getPrescriptionquantityperbasenuit() {
        return prescriptionquantityperbasenuit;
    }

    public void setPrescriptionquantityperbasenuit(int prescriptionquantityperbasenuit) {
        this.prescriptionquantityperbasenuit = prescriptionquantityperbasenuit;
    }

    public int getPrescriptionunitid() {
        return prescriptionunitid;
    }

    public void setPrescriptionunitid(int prescriptionunitid) {
        this.prescriptionunitid = prescriptionunitid;
    }

    public int getPrescriptionunitquantity() {
        return prescriptionunitquantity;
    }

    public void setPrescriptionunitquantity(int prescriptionunitquantity) {
        this.prescriptionunitquantity = prescriptionunitquantity;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }

    public int getPresentationid() {
        return presentationid;
    }

    public void setPresentationid(int presentationid) {
        this.presentationid = presentationid;
    }

    public boolean isSharable() {
        return sharable;
    }

    public void setSharable(boolean sharable) {
        this.sharable = sharable;
    }

    public Stocktype getStocktype() {
        return stocktype;
    }

    public void setStocktype(Stocktype stocktype) {
        this.stocktype = stocktype;
    }

    public int getStocktypeid() {
        return stocktypeid;
    }

    public void setStocktypeid(int stocktypeid) {
        this.stocktypeid = stocktypeid;
    }

    public int getStrengthid() {
        return strengthid;
    }

    public void setStrengthid(int strengthid) {
        this.strengthid = strengthid;
    }

    public int getStrengthvalue() {
        return strengthvalue;
    }

    public void setStrengthvalue(int strengthvalue) {
        this.strengthvalue = strengthvalue;
    }

    public Unitmeasure getUnitmeasure() {
        return unitmeasure;
    }

    public void setUnitmeasure(Unitmeasure unitmeasure) {
        this.unitmeasure = unitmeasure;
    }

}
