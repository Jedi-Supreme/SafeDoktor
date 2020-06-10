package com.softedge.safedoktor.models.swaggerModels.body;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

import static com.softedge.safedoktor.utilities.AppConstants.*;

@Entity(
        tableName = TABLE_DOCTOR_SPECIALTIES,
        primaryKeys = {KEY_SPECIALTY_ID,KEY_DOCTOR_ID},
        indices = {@Index(value = KEY_SPECIALTY_ID), @Index(value = KEY_DOCTOR_ID)}
)
public class Doctor_Specialty {

    @ColumnInfo(name = KEY_SPECIALTY_ID)
    private int specialtyId;

    @NonNull
    @ColumnInfo(name = KEY_DOCTOR_ID)
    private String doctorId;

    @ColumnInfo(name = KEY_SPECIALTY_NAME)
    private String specialtyName;

    public Doctor_Specialty(int specialtyId, @NonNull String doctorId, String specialtyName) {
        this.specialtyId = specialtyId;
        this.doctorId = doctorId;
        this.specialtyName = specialtyName;
    }

    public int getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(int specialtyId) {
        this.specialtyId = specialtyId;
    }

    @NonNull
    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(@NonNull String doctorId) {
        this.doctorId = doctorId;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }
}
