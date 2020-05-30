package com.softedge.safedoktor.models.swaggerModels.response;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.softedge.safedoktor.models.swaggerModels.Appointment;

import java.util.List;

import static com.softedge.safedoktor.utilities.AppConstants.*;

public class rAppt_Content {

    @ColumnInfo(name = KEY_APPOINTMENT)
    @SerializedName(KEY_APPOINTMENT)
    @Expose
    private Appointment appointment;

    @ColumnInfo(name = KEY_DETAILS)
    @SerializedName(KEY_DETAILS)
    @Expose
    private List<rAppt_Details> details;

    @ColumnInfo(name = KEY_PAYMENTS)
    @SerializedName(KEY_PAYMENTS)
    @Expose
    private List<rPayment> payments;

    @ColumnInfo(name = KEY_NOTIFICATIONS)
    @SerializedName(KEY_NOTIFICATIONS)
    @Expose
    private List<rNotifications> notifications;

    @ColumnInfo(name = KEY_NOTES)
    @SerializedName(KEY_NOTES)
    @Expose
    private String notes;

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public List<rAppt_Details> getDetails() {
        return details;
    }

    public void setDetails(List<rAppt_Details> details) {
        this.details = details;
    }

    public List<rPayment> getPayments() {
        return payments;
    }

    public void setPayments(List<rPayment> payments) {
        this.payments = payments;
    }

    public List<rNotifications> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<rNotifications> notifications) {
        this.notifications = notifications;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
