package com.softedge.safedoktor.models.swaggerModels.response;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity
public class rConfirmedAppointmentContent{
    @SerializedName("appointment")
    @Expose
    private AppointmentModel appointment;
    @SerializedName("details")
    @Expose
    private List<DetailModel> details = null;
    @SerializedName("payments")
    @Expose
    private List<PaymentModel> payments = null;
    @SerializedName("notifications")
    @Expose
    private List<NotificationModel> notifications = null;
    @SerializedName("notes")
    @Expose
    private String notes;

    public AppointmentModel getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentModel appointment) {
        this.appointment = appointment;
    }

    public List<DetailModel> getDetails() {
        return details;
    }

    public void setDetails(List<DetailModel> details) {
        this.details = details;
    }

    public List<PaymentModel> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentModel> payments) {
        this.payments = payments;
    }

    public List<NotificationModel> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationModel> notifications) {
        this.notifications = notifications;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
