package com.softedge.safedoktor.models.swaggerModels.response;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.softedge.safedoktor.utilities.AppConstants.*;

@Entity(tableName = TABLET_APPT_DETAILS, indices = {@Index(value = KEY_ID, unique = true)})
public class rAppt_Details{

    public rAppt_Details() {
    }

    @Ignore
    public rAppt_Details(
            int bookingid, String createtime, String createuserid, String endtime, int id,
            String notes, int reasonid, String servertime, int servicefee, int serviceid,
            int serviceplaceid, int slotid, String starttime, String statusdate, int statusid,
            String updatetime, String updateuserid) {

        this.id = id;        
        this.bookingid = bookingid;
        this.createtime = createtime;
        this.createuserid = createuserid;
        this.endtime = endtime;       

        this.notes = notes;
        this.reasonid = reasonid;
        this.servertime = servertime;
        this.servicefee = servicefee;
        this.serviceid = serviceid;

        this.serviceplaceid = serviceplaceid;
        this.slotid = slotid;
        this.starttime = starttime;
        this.statusdate = statusdate;
        this.statusid = statusid;

        this.updatetime = updatetime;
        this.updateuserid = updateuserid;
    }

    @ColumnInfo(name = KEY_BOOKING_ID)
    @SerializedName(KEY_BOOKING_ID)
    @Expose
    @PrimaryKey
    private int bookingid;

    @ColumnInfo(name = KEY_CREATE_TIME)
    @SerializedName(KEY_CREATE_TIME)
    @Expose
    private String createtime;

    @ColumnInfo(name = KEY_CREATE_USER_ID)
    @SerializedName(KEY_CREATE_USER_ID)
    @Expose
    private String createuserid;

    @ColumnInfo(name = KEY_END_TIME)
    @SerializedName(KEY_END_TIME)
    @Expose
    private String endtime;

    @ColumnInfo(name = KEY_ID)
    @SerializedName(KEY_ID)
    @Expose
    private int id;

    @ColumnInfo(name = KEY_NOTES)
    @SerializedName(KEY_NOTES)
    @Expose
    private String notes;

    @ColumnInfo(name = KEY_REASON_ID)
    @SerializedName(KEY_REASON_ID)
    @Expose
    private int reasonid;

    @ColumnInfo(name = KEY_SERVER_TIME)
    @SerializedName(KEY_SERVER_TIME)
    @Expose
    private String servertime;

    @ColumnInfo(name = KEY_SERVICE_FEE)
    @SerializedName(KEY_SERVICE_FEE)
    @Expose
    private int servicefee;

    @ColumnInfo(name = KEY_SERVICE_ID)
    @SerializedName(KEY_SERVICE_ID)
    @Expose
    private int serviceid;

    @ColumnInfo(name = KEY_SERVICE_PLACE_ID)
    @SerializedName(KEY_SERVICE_PLACE_ID)
    @Expose
    private int serviceplaceid;

    @ColumnInfo(name = KEY_SLOT_ID)
    @SerializedName(KEY_SLOT_ID)
    @Expose
    private int slotid;

    @ColumnInfo(name = KEY_START_TIME)
    @SerializedName(KEY_START_TIME)
    @Expose
    private String starttime;

    @ColumnInfo(name = KEY_STATUS_DATE)
    @SerializedName(KEY_STATUS_DATE)
    @Expose
    private String statusdate;

    @ColumnInfo(name = KEY_STATUS_ID)
    @SerializedName(KEY_STATUS_ID)
    @Expose
    private int statusid;

    @ColumnInfo(name = KEY_UPDATE_TIME)
    @SerializedName(KEY_UPDATE_TIME)
    @Expose
    private String updatetime;

    @ColumnInfo(name = KEY_UPDATE_USER_ID)
    @SerializedName(KEY_UPDATE_USER_ID)
    @Expose
    private String updateuserid;

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
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

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getReasonid() {
        return reasonid;
    }

    public void setReasonid(int reasonid) {
        this.reasonid = reasonid;
    }

    public String getServertime() {
        return servertime;
    }

    public void setServertime(String servertime) {
        this.servertime = servertime;
    }

    public int getServicefee() {
        return servicefee;
    }

    public void setServicefee(int servicefee) {
        this.servicefee = servicefee;
    }

    public int getServiceid() {
        return serviceid;
    }

    public void setServiceid(int serviceid) {
        this.serviceid = serviceid;
    }

    public int getServiceplaceid() {
        return serviceplaceid;
    }

    public void setServiceplaceid(int serviceplaceid) {
        this.serviceplaceid = serviceplaceid;
    }

    public int getSlotid() {
        return slotid;
    }

    public void setSlotid(int slotid) {
        this.slotid = slotid;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getStatusdate() {
        return statusdate;
    }

    public void setStatusdate(String statusdate) {
        this.statusdate = statusdate;
    }

    public int getStatusid() {
        return statusid;
    }

    public void setStatusid(int statusid) {
        this.statusid = statusid;
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
