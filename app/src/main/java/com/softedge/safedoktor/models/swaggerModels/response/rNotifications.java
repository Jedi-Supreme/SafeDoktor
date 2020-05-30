package com.softedge.safedoktor.models.swaggerModels.response;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.softedge.safedoktor.utilities.AppConstants.*;

@Entity(tableName = TABLE_NOTIFICATIONS, primaryKeys = {KEY_ID,KEY_BOOKING_ID})
public class rNotifications{

    public rNotifications() {
    }

    @Ignore
    public rNotifications(int id, int notificationid, int bookingid, String notificationdate, int deliverystatusid, int deliverysourceid, String notes, String userid, String servertime) {
        this.id = id;
        this.notificationid = notificationid;
        this.bookingid = bookingid;
        this.notificationdate = notificationdate;
        this.deliverystatusid = deliverystatusid;
        this.deliverysourceid = deliverysourceid;
        this.notes = notes;
        this.userid = userid;
        this.servertime = servertime;
    }

    @ColumnInfo(name = KEY_ID)
    @SerializedName(KEY_ID)
    @Expose
    private int id;
    
    @ColumnInfo(name = KEY_NOTIFICATION_ID)
    @SerializedName(KEY_NOTIFICATION_ID)
    @Expose
    private int notificationid;
    
    @ColumnInfo(name = KEY_BOOKING_ID)
    @SerializedName(KEY_BOOKING_ID)
    @Expose
    private int bookingid;
    
    @ColumnInfo(name = KEY_NOTIFICATION_DATE)
    @SerializedName(KEY_NOTIFICATION_DATE)
    @Expose
    private String notificationdate;
    
    @ColumnInfo(name = KEY_DELIVERY_STATUS_ID)
    @SerializedName(KEY_DELIVERY_STATUS_ID)
    @Expose
    private int deliverystatusid;
    
    @ColumnInfo(name = KEY_DELIVERY_SOURCE_ID)
    @SerializedName(KEY_DELIVERY_SOURCE_ID)
    @Expose
    private int deliverysourceid;
    
    @ColumnInfo(name = KEY_NOTES)
    @SerializedName(KEY_NOTES)
    @Expose
    private String notes;
    
    @ColumnInfo(name = KEY_USER_ID)
    @SerializedName(KEY_USER_ID)
    @Expose
    private String userid;
    
    @ColumnInfo(name = KEY_SERVER_TIME)
    @SerializedName(KEY_SERVER_TIME)
    @Expose
    private String servertime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNotificationid() {
        return notificationid;
    }

    public void setNotificationid(int notificationid) {
        this.notificationid = notificationid;
    }

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public String getNotificationdate() {
        return notificationdate;
    }

    public void setNotificationdate(String notificationdate) {
        this.notificationdate = notificationdate;
    }

    public int getDeliverystatusid() {
        return deliverystatusid;
    }

    public void setDeliverystatusid(int deliverystatusid) {
        this.deliverystatusid = deliverystatusid;
    }

    public int getDeliverysourceid() {
        return deliverysourceid;
    }

    public void setDeliverysourceid(int deliverysourceid) {
        this.deliverysourceid = deliverysourceid;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getServertime() {
        return servertime;
    }

    public void setServertime(String servertime) {
        this.servertime = servertime;
    }
}
