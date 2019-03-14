package com.softedge.safedoktor.fireModels.PatientPackage;

import com.softedge.safedoktor.fireModels.HistoryPackage.FamilyHistory;
import com.softedge.safedoktor.fireModels.HistoryPackage.PersonalHistory;
import com.softedge.safedoktor.fireModels.HistoryPackage.SocialHistory;
import com.softedge.safedoktor.fireModels.HistoryPackage.SurgicalHistory;

public class MedicalHistory {

    private PersonalHistory personalHistory;
    private FamilyHistory familyHistory;
    private SocialHistory socialHistory;
    private SurgicalHistory surgicalHistory;

    MedicalHistory() {
    }

    MedicalHistory(PersonalHistory personalHistory) {
        this.personalHistory = personalHistory;
    }

    MedicalHistory(PersonalHistory personalHistory, FamilyHistory familyHistory) {

        this.personalHistory = personalHistory;
        this.familyHistory = familyHistory;

    }

    MedicalHistory(PersonalHistory personalHistory, FamilyHistory familyHistory, SocialHistory socialHistory) {

        this.personalHistory = personalHistory;
        this.familyHistory = familyHistory;
        this.socialHistory = socialHistory;
    }

    MedicalHistory(PersonalHistory personalHistory, FamilyHistory familyHistory,
                   SocialHistory socialHistory, SurgicalHistory surgicalHistory) {

        this.personalHistory = personalHistory;
        this.familyHistory = familyHistory;
        this.socialHistory = socialHistory;
        this.surgicalHistory = surgicalHistory;
    }


    public PersonalHistory getPersonalHistory() {
        return personalHistory;
    }

    public void setPersonalHistory(PersonalHistory personalHistory) {
        this.personalHistory = personalHistory;
    }

    public FamilyHistory getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(FamilyHistory familyHistory) {
        this.familyHistory = familyHistory;
    }

    public SocialHistory getSocialHistory() {
        return socialHistory;
    }

    public void setSocialHistory(SocialHistory socialHistory) {
        this.socialHistory = socialHistory;
    }

    public SurgicalHistory getSurgicalHistory() {
        return surgicalHistory;
    }

    public void setSurgicalHistory(SurgicalHistory surgicalHistory) {
        this.surgicalHistory = surgicalHistory;
    }

}
