package com.softedge.safedoktor.models.swaggerModels.body;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static com.softedge.safedoktor.utilities.AppConstants.*;

@Entity(
        tableName = TABLE_DOCTORS,
        indices = {@Index(value = KEY_ID)}
)
public class Doctor {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = KEY_ID)
    private String id;

    @ColumnInfo(name = KEY_TITLE)
    private String title;

    @ColumnInfo(name = KEY_FIRSTNAME)
    private String firstName;

    @ColumnInfo(name = KEY_LASTNAME)
    private String lastName;

    @ColumnInfo(name = KEY_OTHER_NAME)
    private String otherName;

    @ColumnInfo(name = KEY_GENDER)
    private String gender;

    @ColumnInfo(name = KEY_EMAIL)
    private String email;

    @ColumnInfo(name = KEY_DOCTOR_PHOTO)
    private String doctorPhoto;


    public Doctor(@NonNull String id, String title, String firstName, String lastName, String otherName, String gender, String email, String doctorPhoto) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherName = otherName;
        this.id = id;
        this.gender = gender;
        this.email = email;
        this.doctorPhoto = doctorPhoto;
    }

    public String getFullName(){

        return firstName.toUpperCase() + " " + lastName.toUpperCase();
    }

    public String getDocName(){
        return title + " " + getFullName();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDoctorPhoto() {
        return doctorPhoto;
    }

    public void setDoctorPhoto(String doctorPhoto) {
        this.doctorPhoto = doctorPhoto;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }
}
