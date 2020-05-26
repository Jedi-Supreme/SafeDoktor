package com.softedge.safedoktor.models.swaggerModels;

import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.util.List;

import static com.softedge.safedoktor.utilities.StringConstants.*;


@Entity(tableName = TABLE_APPT, primaryKeys = {KEY_BOOKING_ID,KEY_PATIENT_ID,KEY_DOCTOR_ID})
public class Appointmenttbl{

    public Appointmenttbl() {
    }


    public Appointmenttbl(
            String bookingdate, Integer bookingid, String bookingnumber, String doctorname, Integer consultationchattypeid,
            String createtime, String createuserid, String doctoruserid, Integer patientid, String servertime,
            Integer sourceid, String statusdate, Integer statusid, String updatetime, String updateuserid, String doctorphoto, Integer remind) {

        this.bookingdate = bookingdate;
        this.bookingid = bookingid;
        this.bookingnumber = bookingnumber;
        this.doctorname = doctorname;
        this.consultationchattypeid = consultationchattypeid;

        this.createtime = createtime;
        this.createuserid = createuserid;
        this.doctoruserid = doctoruserid;
        this.patientid = patientid;
        this.servertime = servertime;

        this.sourceid = sourceid;
        this.statusdate = statusdate;
        this.statusid = statusid;
        this.updatetime = updatetime;
        this.updateuserid = updateuserid;
        this.doctorphoto = doctorphoto;
        this.remind=remind;
    }

    
    @ColumnInfo(name=KEY_BOOKING_DATE)
    private String bookingdate;

    @ColumnInfo(name=KEY_BOOKING_ID)
    private Integer bookingid;

    @ColumnInfo(name=KEY_BOOKING_NUMB)
    private String bookingnumber;

    @ColumnInfo(name=KEY_DOCTOR_NAME)
    private  String doctorname;

    @ColumnInfo(name=KEY_CONS_TYPE)
    private Integer consultationchattypeid;

    @ColumnInfo(name=KEY_CREATE_TIME)
    private String createtime;

    @ColumnInfo(name=KEY_CREATE_USER_ID)
    private String createuserid;

    @ColumnInfo(name=KEY_DOCTOR_ID)
    private String doctoruserid;

    @ColumnInfo(name=KEY_PATIENT_ID)
    private Integer patientid;

    @ColumnInfo(name=KEY_SERVER_TIME)
    private String servertime;

    @ColumnInfo(name=KEY_SOURCE_ID)
    private Integer sourceid;

    @ColumnInfo(name=KEY_STATUS_DATE)
    private String statusdate;

    @ColumnInfo(name=KEY_STATUS_ID)
    private Integer statusid;

    @ColumnInfo(name=KEY_UPDATE_TIME)
    private String updatetime;

    @ColumnInfo(name=KEY_UPDATE_USER_ID)
    private String updateuserid;

    @ColumnInfo(name=KEY_DOCTOR_PHOTO)
    private String doctorphoto;

    @ColumnInfo(name=KEY_REMIND)
    private Integer remind;

    public Integer isRemind() {
        return remind;
    }

    public void setRemind(Integer remind) {
        this.remind = remind;
    }

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

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public String getBookingnumber() {
        return bookingnumber;
    }

    public void setBookingnumber(String bookingnumber) {
        this.bookingnumber = bookingnumber;
    }

    public Integer getConsultationchattypeid() {
        return consultationchattypeid;
    }

    public void setConsultationchattypeid(Integer consultationchattypeid) {
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

    public Integer getPatientid() {
        return patientid;
    }

    public void setPatientid(Integer patientid) {
        this.patientid = patientid;
    }

    public String getServertime() {
        return servertime;
    }

    public void setServertime(String servertime) {
        this.servertime = servertime;
    }

    public Integer getSourceid() {
        return sourceid;
    }

    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }

    public String getStatusdate() {
        return statusdate;
    }

    public void setStatusdate(String statusdate) {
        this.statusdate = statusdate;
    }

    public Integer getStatusid() {
        return statusid;
    }

    public void setStatusid(Integer statusid) {
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
