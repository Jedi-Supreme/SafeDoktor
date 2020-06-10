package com.softedge.safedoktor.models.swaggerModels.body;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static com.softedge.safedoktor.utilities.AppConstants.*;

@Entity(tableName = TABLE_TIME_SLOTS)
public class TimeSlot
{
    @ColumnInfo(name = KEY_DATE)
    private String date;

    @ColumnInfo(name = KEY_DOCTOR_ID)
    private String doctorid;

    @ColumnInfo(name = KEY_DOCTOR_NAME)
    private String doctorname;

    @ColumnInfo(name = KEY_START_TIME)
    private String starttime;

    @ColumnInfo(name = KEY_END_TIME)
    private String endtime;

    @ColumnInfo(name = KEY_ROASTER_ID)
    private int roasterid;

    @PrimaryKey
    @ColumnInfo(name = KEY_SLOT_ID)
    private int slotid;

    @ColumnInfo(name = KEY_STATUS_ID)
    private int statusid;

    @ColumnInfo(name = KEY_SERVICE_ID)
    private int serviceid;

    @ColumnInfo(name = KEY_SPECIALTY_ID)
    private int specialityid;

    @ColumnInfo(name = KEY_SPECIALTY_TEXT)
    private String specialtytext;

    @ColumnInfo(name = KEY_BOOKING_NUMB)
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
