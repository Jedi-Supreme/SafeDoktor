package com.softedge.safedoktor.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.softedge.safedoktor.models.swaggerModels.Appointment;
import com.softedge.safedoktor.models.swaggerModels.BookingsList;
import com.softedge.safedoktor.models.swaggerModels.PatientModel;
import com.softedge.safedoktor.models.swaggerModels.body.Doctor;
import com.softedge.safedoktor.models.swaggerModels.body.Doctor_Specialty;
import com.softedge.safedoktor.models.swaggerModels.body.Specialties;
import com.softedge.safedoktor.models.swaggerModels.body.TimeSlot;
import com.softedge.safedoktor.models.swaggerModels.response.*;

import java.sql.Time;
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

    //=======================================SPECIALTIES============================================
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addSpecialties(Specialties specialty);

    @Query("SELECT * FROM " + TABLE_SPECIALTIES)
    LiveData<List<Specialties>> getAllSpecialties();
    //=======================================SPECIALTIES============================================

    //==================================DOCTOR PROFILE==============================================

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addDoctor(Doctor doctor);

    @Query("SELECT * FROM " + TABLE_DOCTORS + " WHERE " + KEY_ID + " IN (:ids)")
    LiveData<List<Doctor>> getDoctorById(List<String> ids);

    @Query("SELECT DISTINCT * FROM " + TABLE_DOCTORS + " ORDER BY " + KEY_FIRSTNAME + " ASC")
    LiveData<List<Doctor>> getAllDoctors();
    //==================================DOCTOR PROFILE==============================================

    //====================================DOCTOR SPECIALTY==========================================
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addDoc_Specs(Doctor_Specialty doctorSpecialty);
    //====================================DOCTOR SPECIALTY==========================================

    //======================================TIME SLOTS==============================================
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addTimeSlots(TimeSlot timeSlot);

    @Query("DELETE FROM " + TABLE_TIME_SLOTS + " WHERE " + KEY_SLOT_ID + " = :slotId")
    void deleteTimeSlot(int slotId);

    @Query("SELECT * FROM " + TABLE_TIME_SLOTS + " WHERE " + KEY_DATE + " = :date")
    LiveData<List<TimeSlot>> getSlotsByDate(String date);

    @Query("SELECT DISTINCT (" + KEY_DOCTOR_ID + ") FROM " + TABLE_TIME_SLOTS + " WHERE " + KEY_SPECIALTY_ID + " = :specialtyId AND " + KEY_DATE + " >= :date" )
    LiveData<List<String>> distinctDoctorIds(int specialtyId,String date);

    @Query("SELECT DISTINCT (" + KEY_DATE + "), "
            + KEY_ROASTER_ID + ","
            + KEY_SLOT_ID + ","
            + KEY_START_TIME + ","
            + KEY_END_TIME + ","
            + KEY_STATUS_ID + ","
            + KEY_SERVICE_ID + ","
            + KEY_SPECIALTY_ID + " FROM " + TABLE_TIME_SLOTS + " WHERE " + KEY_DOCTOR_ID + " = :doctorId AND " + KEY_SPECIALTY_ID + " = :specialtyId")
    LiveData<List<TimeSlot>> getTimeSlotDates(String doctorId, int specialtyId);

    @Query("SELECT DISTINCT (" + KEY_DATE + ")" + " FROM " + TABLE_TIME_SLOTS + " WHERE " + KEY_DOCTOR_ID + " = :doctorId AND " + KEY_SPECIALTY_ID + " = :specialtyId AND " + KEY_DATE + " >= :date")
    LiveData<List<String>> getSlotDateString(String doctorId, int specialtyId, String date);

    @Query("SELECT * FROM " + TABLE_TIME_SLOTS + " WHERE " + KEY_DOCTOR_ID + " = :doctorId AND " + KEY_SPECIALTY_ID + " = :specialtyId AND " + KEY_DATE + " = :date")
    LiveData<List<TimeSlot>> getTimeSlotsTime(String doctorId, int specialtyId, String date);
    //======================================TIME SLOTS==============================================

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addPayments(rPayment appointment_payments);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addNotifications(rNotifications appointment_notifications);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addBooking(BookingsList booking);
}
