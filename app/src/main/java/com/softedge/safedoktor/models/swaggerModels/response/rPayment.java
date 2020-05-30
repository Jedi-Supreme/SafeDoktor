package com.softedge.safedoktor.models.swaggerModels.response;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.softedge.safedoktor.utilities.AppConstants.*;

@Entity(tableName = TABLE_PAYMENTS)
public class rPayment {

    public rPayment() {
    }

    @Ignore
    public rPayment(
            int paymentid, int amountpaid, int amounttendered, int bookingid, double changeamount, int currencyid,
            int invoiceid, int patientid, String paymenttime, int receiptid, int totalbill, String userid) {

        this.paymentid = paymentid;
        this.amountpaid = amountpaid;
        this.amounttendered = amounttendered;
        this.bookingid = bookingid;
        this.changeamount = changeamount;
        this.currencyid = currencyid;
        this.invoiceid = invoiceid;
        this.patientid = patientid;
        this.paymenttime = paymenttime;
        this.receiptid = receiptid;
        this.totalbill = totalbill;
        this.userid = userid;
    }

    @ColumnInfo(name = KEY_PAYMENT_ID)
    @SerializedName(KEY_PAYMENT_ID)
    @Expose
    @PrimaryKey
    private int paymentid;

    @ColumnInfo(name = KEY_AMOUNT_PAID)
    @SerializedName(KEY_AMOUNT_PAID)
    @Expose
    private int amountpaid;

    @ColumnInfo(name = KEY_AMOUNT_TENDERED)
    @SerializedName(KEY_AMOUNT_TENDERED)
    @Expose
    private int amounttendered;

    @ColumnInfo(name = KEY_BOOKING_ID)
    @SerializedName(KEY_BOOKING_ID)
    @Expose
    private int bookingid;

    @ColumnInfo(name = KEY_CHANGE_AMOUNT)
    @SerializedName(KEY_CHANGE_AMOUNT)
    @Expose
    private double changeamount;

    @ColumnInfo(name = KEY_CURRENCY_ID)
    @SerializedName(KEY_CURRENCY_ID)
    @Expose
    private int currencyid;

    @ColumnInfo(name = KEY_INVOICE_ID)
    @SerializedName(KEY_INVOICE_ID)
    @Expose
    private int invoiceid;

    @ColumnInfo(name = KEY_PATIENT_ID)
    @SerializedName(KEY_PATIENT_ID)
    @Expose
    private int patientid;

    @ColumnInfo(name = KEY_PAYMENT_TIME)
    @SerializedName(KEY_PAYMENT_TIME)
    @Expose
    private String paymenttime;

    @ColumnInfo(name = KEY_RECIEPT_ID)
    @SerializedName(KEY_RECIEPT_ID)
    @Expose
    private int receiptid;

    @ColumnInfo(name = KEY_TOTAL_BILL)
    @SerializedName(KEY_TOTAL_BILL)
    @Expose
    private int totalbill;

    @ColumnInfo(name = KEY_USER_ID)
    @SerializedName(KEY_USER_ID)
    @Expose
    private String userid;

    public int getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(int paymentid) {
        this.paymentid = paymentid;
    }

    public int getAmountpaid() {
        return amountpaid;
    }

    public void setAmountpaid(int amountpaid) {
        this.amountpaid = amountpaid;
    }

    public int getAmounttendered() {
        return amounttendered;
    }

    public void setAmounttendered(int amounttendered) {
        this.amounttendered = amounttendered;
    }

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public double getChangeamount() {
        return changeamount;
    }

    public void setChangeamount(double changeamount) {
        this.changeamount = changeamount;
    }

    public int getCurrencyid() {
        return currencyid;
    }

    public void setCurrencyid(int currencyid) {
        this.currencyid = currencyid;
    }

    public int getInvoiceid() {
        return invoiceid;
    }

    public void setInvoiceid(int invoiceid) {
        this.invoiceid = invoiceid;
    }

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public String getPaymenttime() {
        return paymenttime;
    }

    public void setPaymenttime(String paymenttime) {
        this.paymenttime = paymenttime;
    }

    public int getReceiptid() {
        return receiptid;
    }

    public void setReceiptid(int receiptid) {
        this.receiptid = receiptid;
    }

    public int getTotalbill() {
        return totalbill;
    }

    public void setTotalbill(int totalbill) {
        this.totalbill = totalbill;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
