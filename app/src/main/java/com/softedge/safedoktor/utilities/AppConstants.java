package com.softedge.safedoktor.utilities;

public class AppConstants {

    public static String SAFE_DOCTOR_SENDER_ID  = "SAFE DOKTOR";

    //-------------------------------APPT STATUS-----------------------------------
    public static final int APPT_STATUS_INPROGRESS = 7;
    public static final int APPT_STATUS_COMPLETED = 3;
    public static final int APPT_STATUS_BOOKED = 8;
    //-------------------------------APPT STATUS-----------------------------------

    //------------------------------------CHAT TYPE--------------------------------
    public static final int CHAT_TYPE_TEXT = 1;
    public static final int CHAT_TYPE_AUDIO = 2;
    public static final int CHAT_TYPE_VIDEO = 3;
    //------------------------------------CHAT TYPE--------------------------------

    //--------------------------------------TIME SLOTS-----------------------------

    //--------------------------------------TIME SLOTS-----------------------------

    //---------------------------------SERVICE CONTENT-----------------------------
    public static final String KEY_AGE_GROUP_ID = "agegroupid";
    public static final String KEY_ALLOW_COPAY = "allowcopay";
    public static final String KEY_IS_ACTIVE = "isactive";
    public static final String KEY_NAME = "name";
    public static final String KEY_SERVICE_CAT_ID = "servicecategoryid";
    public static final String KEY_SERVICE_SUB_CAT = "servicesubcategoryid";
    public static final String KEY_SERVICE_TYPE_ID = "servicetypeid";
    public static final String KEY_ = "";
    //---------------------------------SERVICE CONTENT-----------------------------

    //---------------------------------SESSION MANAGEMENT--------------------------
    public static final String IS_LOGIN="isloggedin";
    public static final String NOTI_STATUS="notistatus";
    public static final String KEY_PAT_STATUS_ID = "patientstatusid";
    public static final String KEY_TOKEN_TYPE = "tokentype";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_CREATED_DATE = "datecreated";
    public static final String KEY_EXPIRES_IN = "expires";
    public static final String KEY_PATIENT_IMAGE = "patientimage";
    //---------------------------------SESSION MANAGEMENT--------------------------

    //-----------------------------------TABLES------------------------------------
    public static final String TABLE_BOOKING_LIST = "BOOKINGS";
    public static final String TABLE_APPT = "APPOINTMENTS";
    public static final String TABLET_APPT_DETAILS = "APPOINTMENT_DETAILS";
    public static final String TABLE_PATIENTS = "PATIENTS";
    public static final String TABLE_NOTIFICATIONS = "NOTIFICATIONS";
    public static final String TABLE_SERVICE_CONTENT = "SERVICE_CONTENT";
    public static final String TABLE_PAYMENTS = "PAYMENTS";
    public static final String TABLE_TIME_SLOTS = "TIME_SLOTS";
    //-----------------------------------TABLES-------------------------------------

    //--------------------------------------NOTIFICATIONS---------------------------
    public static final String KEY_NOTIFICATION_ID = "notificationid";
    public static final String KEY_NOTIFICATION_DATE = "notificationdate";
    public static final String KEY_DELIVERY_STATUS_ID = "deliverystatusid";
    public static final String KEY_DELIVERY_SOURCE_ID = "deliverysourceid";
    //--------------------------------------NOTIFICATIONS---------------------------

    //---------------------------------------PAYMENT--------------------------------
    public static final String KEY_PAYMENT_ID = "paymentid";
    public static final String KEY_AMOUNT_PAID = "amountpaid";
    public static final String KEY_AMOUNT_TENDERED = "amounttendered";
    public static final String KEY_CHANGE_AMOUNT = "changeamount";
    public static final String KEY_CURRENCY_ID = "currencyid";
    public static final String KEY_INVOICE_ID = "invoiceid";
    public static final String KEY_PAYMENT_TIME = "paymenttime";
    public static final String KEY_RECIEPT_ID = "receiptid";
    public static final String KEY_TOTAL_BILL = "totalbill";
    public static final String KEY_USER_ID = "userid";
//    public static final String KEY_ = "";
    //---------------------------------------PAYMENT--------------------------------

    //---------------------------CONFIRMED-APPOINTMENTS-----------------------------
    public static final String KEY_APPOINTMENT = "appointment";
    public static final String KEY_DETAILS = "details";
    public static final String KEY_PAYMENTS = "payments";
    public static final String KEY_NOTIFICATIONS = "notifications";
    public static final String KEY_NOTES = "notes";
    //public static final String KEY_ = "";
    //---------------------------CONFIRMED-APPOINTMENTS-----------------------------

    //---------------------------------------APPT-DETAILS---------------------------
    public static final String KEY_END_TIME = "endtime";
    public static final String KEY_REASON_ID = "reasonid";
    public static final String KEY_SERVICE_FEE = "servicefee";
    public static final String KEY_SERVICE_ID = "serviceid";
    public static final String KEY_SERVICE_PLACE_ID = "serviceplaceid";
    public static final String KEY_SLOT_ID = "slotid";
    public static final String KEY_START_TIME = "starttime";
//    public static final String KEY_ = "";
    //---------------------------------------APPT-DETAILS---------------------------

    //---------------------------APPOINTMENTS---------------------------------------
    public static final String KEY_BOOKING_DATE = "bookingdate";
    public static final String KEY_BOOKING_ID = "bookingid";
    public static final String KEY_BOOKING_NUMB = "bookingnumber";
    public static final String KEY_DOCTOR_NAME = "doctorname";
    public static final String KEY_CONS_TYPE = "consultationchattypeid";
    public static final String KEY_CREATE_USER_ID = "createuserid";
    public static final String KEY_CREATE_TIME = "createtime";
    public static final String KEY_DOCTOR_ID = "doctoruserid";
    public static final String KEY_PATIENT_ID = "patientid";
    public static final String KEY_SERVER_TIME = "servertime";
    public static final String KEY_SOURCE_ID = "sourceid";
    public static final String KEY_STATUS_DATE = "statusdate";
    public static final String KEY_STATUS_ID = "statusid";
    public static final String KEY_UPDATE_TIME = "updatetime";
    public static final String KEY_UPDATE_USER_ID = "updateuserid";
    public static final String KEY_DOCTOR_PHOTO = "doctorphoto";
    public static final String KEY_REMIND = "remind";
    //public static final String KEY_ = "";
    //---------------------------APPOINTMENTS--------------------------------------

    //---------------------------PERSON--------------------------------------------
    public static final String KEY_ID = "id";
    public static final String KEY_FIRSTNAME = "firstname";
    public static final String KEY_LASTNAME = "lastname";
    public static final String KEY_DOB = "dateofbirth";
    public static final String KEY_ACC_NUMBER = "accountnumber";
    public static final String KEY_PHONE_NUMBER = "phonenumber";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_BLOOD_GROUP = "bloodgroupid";
    public static final String KEY_DENOMIATION_ID = "denominationid";
    public static final String KEY_DISTRICT_ID = "districtid";
    public static final String KEY_EDU_LEVEL = "educationallevelid";
    public static final String KEY_ETHNICITY = "ethnicityid";
    public static final String KEY_HEIGHT = "height";
    public static final String KEY_HOMEDISTRICT = "homedistrictid";
    public static final String KEY_HOME_REGION = "homeregionid";
    public static final String KEY_HOME_STREET = "homestreet";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_RELIGION = "religion";
    public static final String KEY_GENDER_GROUP_ID = "gendergroupid";
    public static final String KEY_MODIFICATION_ID = "modification_id";
    public static final String KEY_ACC_TYPE = "account_type"; //patient or employee
    //---------------------------PERSON--------------------------------------------

    public static final String KEY_FULL_TOKEN = "login_token";
    public static final String KEY_OPD_NUMBER = "opd_number";
    public static final String KEY_CATEGORY = "patient_category";




}
