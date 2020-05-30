package com.softedge.safedoktor.models.swaggerModels.body;

import androidx.room.Entity;

import static com.softedge.safedoktor.utilities.AppConstants.*;

@Entity(tableName = TABLE_TIME_SLOTS)
public class TimeSlot
{
    private String date;
    private String doctorid;
    private String doctorname;
    private String starttime;
    private String endtime;
    private int roasterid;
    private int slotid;
    private int statusid;
    private int serviceid;
    private int specialityid;

    private String specialtytext;
    private String bookingnumber;

    public TimeSlot() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public int getRoasterid() {
        return roasterid;
    }

    public void setRoasterid(int roasterid) {
        this.roasterid = roasterid;
    }

    public int getSlotid() {
        return slotid;
    }

    public void setSlotid(int slotid) {
        this.slotid = slotid;
    }

    public int getStatusid() {
        return statusid;
    }

    public void setStatusid(int statusid) {
        this.statusid = statusid;
    }

    public int getServiceid() {
        return serviceid;
    }

    public void setServiceid(int serviceid) {
        this.serviceid = serviceid;
    }

    public int getSpecialityid() {
        return specialityid;
    }

    public void setSpecialityid(int specialityid) {
        this.specialityid = specialityid;
    }

    public String getSpecialtytext() {
        return specialtytext;
    }

    public void setSpecialtytext(String specialtytext) {
        this.specialtytext = specialtytext;
    }

    public String getBookingnumber() {
        return bookingnumber;
    }

    public void setBookingnumber(String bookingnumber) {
        this.bookingnumber = bookingnumber;
    }
}
