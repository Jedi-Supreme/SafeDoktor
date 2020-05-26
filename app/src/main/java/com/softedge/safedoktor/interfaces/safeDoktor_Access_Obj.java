package com.softedge.safedoktor.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.softedge.safedoktor.models.swaggerModels.PatientModel;

import static com.softedge.safedoktor.utilities.StringConstants.*;

@Dao
public interface safeDoktor_Access_Obj {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addPatient(PatientModel swagPatient);

    @Query("SELECT * FROM " + TABLE_PATIENTS + " WHERE " + KEY_PATIENT_ID + " = :patientid AND "  + KEY_PHONE_NUMBER + " = :phonenumber")
    PatientModel getpatient(String patientid, String phonenumber);


}
