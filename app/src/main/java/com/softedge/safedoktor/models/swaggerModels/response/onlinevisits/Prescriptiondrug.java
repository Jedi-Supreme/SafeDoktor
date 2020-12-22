
package com.softedge.safedoktor.models.swaggerModels.response.onlinevisits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prescriptiondrug {

    @SerializedName("amount")
    @Expose
    private int amount;
    @SerializedName("consultation")
    @Expose
    private Consultation consultation;
    @SerializedName("consultationid")
    @Expose
    private int consultationid;
    @SerializedName("createtime")
    @Expose
    private String createtime;
    @SerializedName("createuserid")
    @Expose
    private String createuserid;
    @SerializedName("dosage")
    @Expose
    private int dosage;
    @SerializedName("drug")
    @Expose
    private Drug drug;
    @SerializedName("drugid")
    @Expose
    private String drugid;
    @SerializedName("durationid")
    @Expose
    private int durationid;
    @SerializedName("durationunit")
    @Expose
    private Durationunit durationunit;
    @SerializedName("durationvalue")
    @Expose
    private int durationvalue;
    @SerializedName("frequency")
    @Expose
    private Frequency frequency;
    @SerializedName("frequencyid")
    @Expose
    private int frequencyid;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("isrefill")
    @Expose
    private boolean isrefill;
    @SerializedName("issued")
    @Expose
    private String issued;
    @SerializedName("medistatus")
    @Expose
    private int medistatus;
    @SerializedName("numberofrefills")
    @Expose
    private int numberofrefills;
    @SerializedName("prescribedqty")
    @Expose
    private int prescribedqty;
    @SerializedName("prescriptiontime")
    @Expose
    private String prescriptiontime;
    @SerializedName("presstatus")
    @Expose
    private int presstatus;
    @SerializedName("provider")
    @Expose
    private Provider provider;
    @SerializedName("providerid")
    @Expose
    private String providerid;
    @SerializedName("providerremarks")
    @Expose
    private String providerremarks;
    @SerializedName("qtygiven")
    @Expose
    private int qtygiven;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("servertime")
    @Expose
    private String servertime;
    @SerializedName("startdate")
    @Expose
    private String startdate;
    @SerializedName("unitfee")
    @Expose
    private int unitfee;
    @SerializedName("updatetime")
    @Expose
    private String updatetime;
    @SerializedName("updateuserid")
    @Expose
    private String updateuserid;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public int getConsultationid() {
        return consultationid;
    }

    public void setConsultationid(int consultationid) {
        this.consultationid = consultationid;
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

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public String getDrugid() {
        return drugid;
    }

    public void setDrugid(String drugid) {
        this.drugid = drugid;
    }

    public int getDurationid() {
        return durationid;
    }

    public void setDurationid(int durationid) {
        this.durationid = durationid;
    }

    public Durationunit getDurationunit() {
        return durationunit;
    }

    public void setDurationunit(Durationunit durationunit) {
        this.durationunit = durationunit;
    }

    public int getDurationvalue() {
        return durationvalue;
    }

    public void setDurationvalue(int durationvalue) {
        this.durationvalue = durationvalue;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public int getFrequencyid() {
        return frequencyid;
    }

    public void setFrequencyid(int frequencyid) {
        this.frequencyid = frequencyid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsrefill() {
        return isrefill;
    }

    public void setIsrefill(boolean isrefill) {
        this.isrefill = isrefill;
    }

    public String getIssued() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public int getMedistatus() {
        return medistatus;
    }

    public void setMedistatus(int medistatus) {
        this.medistatus = medistatus;
    }

    public int getNumberofrefills() {
        return numberofrefills;
    }

    public void setNumberofrefills(int numberofrefills) {
        this.numberofrefills = numberofrefills;
    }

    public int getPrescribedqty() {
        return prescribedqty;
    }

    public void setPrescribedqty(int prescribedqty) {
        this.prescribedqty = prescribedqty;
    }

    public String getPrescriptiontime() {
        return prescriptiontime;
    }

    public void setPrescriptiontime(String prescriptiontime) {
        this.prescriptiontime = prescriptiontime;
    }

    public int getPresstatus() {
        return presstatus;
    }

    public void setPresstatus(int presstatus) {
        this.presstatus = presstatus;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getProviderid() {
        return providerid;
    }

    public void setProviderid(String providerid) {
        this.providerid = providerid;
    }

    public String getProviderremarks() {
        return providerremarks;
    }

    public void setProviderremarks(String providerremarks) {
        this.providerremarks = providerremarks;
    }

    public int getQtygiven() {
        return qtygiven;
    }

    public void setQtygiven(int qtygiven) {
        this.qtygiven = qtygiven;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getServertime() {
        return servertime;
    }

    public void setServertime(String servertime) {
        this.servertime = servertime;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public int getUnitfee() {
        return unitfee;
    }

    public void setUnitfee(int unitfee) {
        this.unitfee = unitfee;
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
