package com.softedge.safedoktor.models.swaggerModels;

import com.softedge.safedoktor.utilities.AppConstants;

public class ApptBooking {
    private int consultationchattypeid;
    private String doctorid;
    private String mobilemoneynetworkid;
    private int patientid;
    private int reasonid = 1;
    private int serviceid;
    private int slotid;
    private String walletnumber;
    private String vodafonetoken = "";
    private int consultationtypeid = AppConstants.CONS_TYPE_SCHEDULE;

    public ApptBooking() {
    }

    public ApptBooking(
            int consultationchattypeid, String doctorid, String mobilemoneynetworkid,
            int patientid, int serviceid, int slotid, String walletnumber) {
        this.consultationchattypeid = consultationchattypeid;
        this.doctorid = doctorid;
        this.mobilemoneynetworkid = mobilemoneynetworkid;
        this.patientid = patientid;
        this.serviceid = serviceid;
        this.slotid = slotid;
        this.walletnumber = walletnumber;
    }

    public int getConsultationchattypeid() {
        return consultationchattypeid;
    }

    public void setConsultationchattypeid(int consultationchattypeid) {
        this.consultationchattypeid = consultationchattypeid;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public String getMobilemoneynetworkid() {
        return mobilemoneynetworkid;
    }

    public void setMobilemoneynetworkid(String mobilemoneynetworkid) {
        this.mobilemoneynetworkid = mobilemoneynetworkid;
    }

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public int getReasonid() {
        return reasonid;
    }

    public void setReasonid(int reasonid) {
        this.reasonid = reasonid;
    }

    public int getServiceid() {
        return serviceid;
    }

    public void setServiceid(int serviceid) {
        this.serviceid = serviceid;
    }

    public int getSlotid() {
        return slotid;
    }

    public void setSlotid(int slotid) {
        this.slotid = slotid;
    }

    public String getWalletnumber() {
        return walletnumber;
    }

    public void setWalletnumber(String walletnumber) {
        this.walletnumber = walletnumber;
    }

    public String getVodafonetoken() {
        return vodafonetoken;
    }

    public void setVodafonetoken(String vodafonetoken) {
        this.vodafonetoken = vodafonetoken;
    }

    public int getConsultationtypeid() {
        return consultationtypeid;
    }

    public void setConsultationtypeid(int consultationtypeid) {
        this.consultationtypeid = consultationtypeid;
    }
}
