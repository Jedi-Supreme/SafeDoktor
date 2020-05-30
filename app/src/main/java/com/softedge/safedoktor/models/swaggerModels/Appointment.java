package com.softedge.safedoktor.models.swaggerModels;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.softedge.safedoktor.utilities.AppConstants.*;


@Entity(tableName = TABLE_APPT, primaryKeys = {KEY_BOOKING_ID,KEY_PATIENT_ID})
public class Appointment {

    public Appointment() {
    }

    @Ignore
    public Appointment(
            String bookingdate, int bookingid, String bookingnumber, String doctorname, int consultationchattypeid,
            String createtime, String createuserid, String doctoruserid, int patientid, String servertime,
            int sourceid, String statusdate, int statusid, String updatetime, String updateuserid, String doctorphoto) {

        this.bookingid = bookingid;
        this.bookingnumber = bookingnumber;
        this.patientid = patientid;
        this.bookingdate = bookingdate;
        this.sourceid = sourceid;
        this.statusid = statusid;

        this.statusdate = statusdate;
        this.createuserid = createuserid;
        this.createtime = createtime;
        this.updateuserid = updateuserid;
        this.updatetime = updatetime;
        this.servertime = servertime;
        this.consultationchattypeid = consultationchattypeid;

        this.doctoruserid = doctoruserid;
        this.doctorname = doctorname;
        this.doctorphoto = doctorphoto;
    }

    
    @ColumnInfo(name=KEY_BOOKING_DATE)
    @SerializedName(KEY_BOOKING_DATE)
    private String bookingdate;

    @ColumnInfo(name=KEY_BOOKING_ID)
    @SerializedName(KEY_BOOKING_ID)
    private int bookingid;

    @ColumnInfo(name=KEY_PATIENT_ID)
    @SerializedName(KEY_PATIENT_ID)
    @Expose
    private int patientid;

    @ColumnInfo(name=KEY_BOOKING_NUMB)
    @SerializedName(KEY_BOOKING_NUMB)
    @Expose
    private String bookingnumber;

    @ColumnInfo(name=KEY_DOCTOR_NAME)
    @SerializedName(KEY_DOCTOR_NAME)
    @Expose
    private  String doctorname;

    @ColumnInfo(name=KEY_CONS_TYPE)
    @SerializedName(KEY_CONS_TYPE)
    @Expose
    private int consultationchattypeid;

    @ColumnInfo(name=KEY_CREATE_TIME)
    @SerializedName(KEY_CREATE_TIME)
    @Expose
    private String createtime;

    @ColumnInfo(name=KEY_CREATE_USER_ID)
    @SerializedName(KEY_CREATE_USER_ID)
    @Expose
    private String createuserid;

    @ColumnInfo(name=KEY_DOCTOR_ID)
    @SerializedName(KEY_DOCTOR_ID)
    @Expose
    private String doctoruserid;

    @ColumnInfo(name=KEY_SERVER_TIME)
    @SerializedName(KEY_SERVER_TIME)
    @Expose
    private String servertime;

    @ColumnInfo(name=KEY_SOURCE_ID)
    @SerializedName(KEY_SOURCE_ID)
    @Expose
    private int sourceid;

    @ColumnInfo(name=KEY_STATUS_DATE)
    @SerializedName(KEY_STATUS_DATE)
    @Expose
    private String statusdate;

    @ColumnInfo(name=KEY_STATUS_ID)
    @SerializedName(KEY_STATUS_ID)
    @Expose
    private int statusid;

    @ColumnInfo(name=KEY_UPDATE_TIME)
    @SerializedName(KEY_UPDATE_TIME)
    @Expose
    private String updatetime;

    @ColumnInfo(name=KEY_UPDATE_USER_ID)
    @SerializedName(KEY_UPDATE_USER_ID)
    @Expose
    private String updateuserid;

    @ColumnInfo(name=KEY_DOCTOR_PHOTO)
    @SerializedName(KEY_DOCTOR_PHOTO)
    @Expose
    private String doctorphoto;

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getBookingdate() {
        return bookingdate;
    }

    public void setBookingdate(String bookingdate) {
        this.bookingdate = bookingdate;
    }

    @NonNull
    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(@NonNull int bookingid) {
        this.bookingid = bookingid;
    }

    public String getBookingnumber() {
        return bookingnumber;
    }

    public void setBookingnumber(String bookingnumber) {
        this.bookingnumber = bookingnumber;
    }

    public int getConsultationchattypeid() {
        return consultationchattypeid;
    }

    public void setConsultationchattypeid(int consultationchattypeid) {
        this.consultationchattypeid = consultationchattypeid;
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

    public String getDoctoruserid() {
        return doctoruserid;
    }

    public void setDoctoruserid(String doctoruserid) {
        this.doctoruserid = doctoruserid;
    }

    @NonNull
    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(@NonNull int patientid) {
        this.patientid = patientid;
    }

    public String getServertime() {
        return servertime;
    }

    public void setServertime(String servertime) {
        this.servertime = servertime;
    }

    public int getSourceid() {
        return sourceid;
    }

    public void setSourceid(int sourceid) {
        this.sourceid = sourceid;
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

    public String getDoctorphoto() {
        return doctorphoto;
    }

    public void setDoctorphoto(String doctorphoto) {
        this.doctorphoto = doctorphoto;
    }

//    public static List<Appointmenttbl> getSavedAppointment() {
//        return new Select().from(Appointmenttbl.class).orderBy("id DESC").execute();
//    }
//
//    public static void setReminder(String bookingnumber,int status){
//        List<Appointmenttbl> t=new Select().from(Appointmenttbl.class).where("bookingnumber = ?", bookingnumber).execute();
//        Log.e("setReminder",t.size()+" records");
//
//        new Update(Appointmenttbl.class)
//                .set("remind = ?",status)
//                .where("bookingnumber = ?", bookingnumber)
//                .execute();
//
//        //List<Appointmenttbl> mt=new Select().from(Appointmenttbl.class).where("bookingnumber = ?", bookingnumber).execute();
//        //mt.size();
//    }
}
