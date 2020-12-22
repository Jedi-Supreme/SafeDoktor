
package com.softedge.safedoktor.models.swaggerModels.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.softedge.safedoktor.models.swaggerModels.response.onlinevisits.*;

public class rOnlineVisits {

    @SerializedName("clinicalnotes")
    @Expose
    private List<Clinicalnote> clinicalnotes = null;

    @SerializedName("complains")
    @Expose
    private List<Complains> complains = null;

    @SerializedName("consultation")
    @Expose
    private Consultation consultation;

    @SerializedName("diagnoses")
    @Expose
    private List<Diagnoses> diagnoses = null;

    @SerializedName("documents")
    @Expose
    private List<Document> documents = null;

    @SerializedName("examsandfindings")
    @Expose
    private List<Examfinding> examsandfindings = null;

    @SerializedName("prescriptions")
    @Expose
    private List<Prescriptiondrug> prescriptions = null;

    @SerializedName("referrals")
    @Expose
    private List<Referral> referrals = null;

    @SerializedName("servicerequests")
    @Expose
    private List<Servicerequest> servicerequests = null;

    @SerializedName("systemsreviews")
    @Expose
    private List<Systemreviews> systemsreviews = null;

    public List<Clinicalnote> getClinicalnotes() {
        return clinicalnotes;
    }

    public void setClinicalnotes(List<Clinicalnote> clinicalnotes) {
        this.clinicalnotes = clinicalnotes;
    }

    public List<Complains> getComplains() {
        return complains;
    }

    public void setComplains(List<Complains> complains) {
        this.complains = complains;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public List<Diagnoses> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<Diagnoses> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<Examfinding> getExamsandfindings() {
        return examsandfindings;
    }

    public void setExamsandfindings(List<Examfinding> examsandfindings) {
        this.examsandfindings = examsandfindings;
    }

    public List<Prescriptiondrug> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescriptiondrug> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<Referral> getReferrals() {
        return referrals;
    }

    public void setReferrals(List<Referral> referrals) {
        this.referrals = referrals;
    }

    public List<Servicerequest> getServicerequests() {
        return servicerequests;
    }

    public void setServicerequests(List<Servicerequest> servicerequests) {
        this.servicerequests = servicerequests;
    }

    public List<Systemreviews> getSystemsreviews() {
        return systemsreviews;
    }

    public void setSystemsreviews(List<Systemreviews> systemsreviews) {
        this.systemsreviews = systemsreviews;
    }
}
