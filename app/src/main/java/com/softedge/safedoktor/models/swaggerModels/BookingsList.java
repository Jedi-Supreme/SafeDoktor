package com.softedge.safedoktor.models.swaggerModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

import static com.softedge.safedoktor.utilities.AppConstants.*;

@Entity(tableName = TABLE_BOOKING_LIST, primaryKeys = {KEY_BOOKING_ID,KEY_PATIENT_ID})
public class BookingsList {

    @ColumnInfo(name = KEY_BOOKING_ID)
    private int bookingId;

    @ColumnInfo(name = KEY_PATIENT_ID)
    private int patientId;

    @ColumnInfo(name = KEY_BOOKING_DATE)
    private String bookingdate;

    public BookingsList(int bookingId, int patientId, String bookingdate) {
        this.bookingId = bookingId;
        this.patientId = patientId;
        this.bookingdate = bookingdate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getBookingdate() {
        return bookingdate;
    }

    public void setBookingdate(String bookingdate) {
        this.bookingdate = bookingdate;
    }
}
