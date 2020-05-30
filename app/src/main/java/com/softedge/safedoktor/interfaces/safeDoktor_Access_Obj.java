package com.softedge.safedoktor.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.softedge.safedoktor.models.swaggerModels.Appointment;
import com.softedge.safedoktor.models.swaggerModels.BookingsList;
import com.softedge.safedoktor.models.swaggerModels.PatientModel;
import com.softedge.safedoktor.models.swaggerModels.response.*;

import java.util.List;

import static com.softedge.safedoktor.utilities.AppConstants.*;

@Dao
public interface safeDoktor_Access_Obj {

    //=========================================PATIENT==============================================
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addPatient(PatientModel swagPatient);

    @Query("SELECT * FROM " + TABLE_PATIENTS + " WHERE " + KEY_PATIENT_ID + " = :patientid ")
    PatientModel getpatient(int patientid);
    //=========================================PATIENT==============================================


    //=========================================APPOINTMENT==========================================
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAppointment(Appointment appointment);

    @Query("SELECT * FROM " + TABLE_APPT + " WHERE " + KEY_PATIENT_ID + " = :PatientId")
    LiveData<List<Appointment>> appointments(int PatientId);

    @Query("SELECT * FROM " + TABLE_APPT + " WHERE " + KEY_PATIENT_ID + " = :patientID AND " + KEY_STATUS_ID + " = :statusId ORDER BY " + KEY_BOOKING_DATE + " DESC")
    LiveData<List<Appointment>> appointmentByStatus(int patientID, int statusId);
    //=========================================APPOINTMENT==========================================


    //============================================DETAILS===========================================
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addDetails(rAppt_Details appointment_details);

    @Query("SELECT * FROM " + TABLET_APPT_DETAILS + " WHERE " + KEY_BOOKING_ID + " = :bookingId")
    LiveData<rAppt_Details> getDetails(int bookingId);
    //============================================DETAILS===========================================

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addPayments(rPayment appointment_payments);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addNotifications(rNotifications appointment_notifications);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addBooking(BookingsList booking);
}
