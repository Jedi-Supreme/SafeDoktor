package com.softedge.safedoktor.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.softedge.safedoktor.activities.Contacts_dependantsActivity;
import com.softedge.safedoktor.activities.OpdCardActivity;
import com.softedge.safedoktor.models.fireModels.Dependant_class;
import com.softedge.safedoktor.models.fireModels.HistoryPackage.History;
import com.softedge.safedoktor.models.fireModels.PatientPackage.Biography;
import com.softedge.safedoktor.models.fireModels.PatientPackage.ContactPerson;
import com.softedge.safedoktor.models.fireModels.PatientPackage.Physicals;
import com.softedge.safedoktor.models.fireModels.Review_class;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.utilities.common_code;

import java.util.ArrayList;

public class SafeDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "care_assist.db";

    private Context mContext;

    public SafeDB(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Patient bio table
        String bioQuery = "CREATE TABLE IF NOT EXISTS " + Biography.TABLE + " ( " +
                Biography.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Biography.FIREBASE_ID + " TEXT UNIQUE, " +
                Biography.OPD_ID + " TEXT UNIQUE, " +
                Biography.FIRSTNAME + " TEXT, " +
                Biography.LASTNAME + " TEXT, " +
                Biography.GENDER + " INTEGER, " +
                Biography.COUNTRY_CODE + " TEXT, " +
                Biography.MOBILE_NUMBER + " TEXT, " +
                Biography.EMAIL + " TEXT, " +
                Biography.DATE_OF_BIRTH + " TEXT, " +
                Biography.PROFILE_PIC_URL + " TEXT DEFAULT \"\", " + //updated later
                Biography.MARITAL_STATUS + " TEXT);"; // updated later
        db.execSQL(bioQuery);

        //Patient physicals and genetics table
        String physicalsQuery = "CREATE TABLE IF NOT EXISTS " + Physicals.TABLE + " ( " +
                Physicals.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Physicals.FIREBASE_ID + " TEXT UNIQUE, " +
                Physicals.LAST_UPDATED + " TEXT, " +
                Physicals.BLOOD_GROUP + " TEXT, " +
                Physicals.HEIGHT + " INTEGER, " +
                Physicals.WEIGHT + " INTEGER);";
        db.execSQL(physicalsQuery);

        //Patient contacts table
        String contactpersQuery = "CREATE TABLE IF NOT EXISTS " + ContactPerson.TABLE + " ( " +
                ContactPerson.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ContactPerson.FIRE_ID + " TEXT , " +
                ContactPerson.FULL_NAME + " TEXT UNIQUE, " +
                ContactPerson.EMAIL + " TEXT, " +
                ContactPerson.MOBILE_NUMBER + " TEXT UNIQUE, " +
                ContactPerson.ADDRESS + " TEXT, " +
                ContactPerson.RELATION + " INTEGER);";
        db.execSQL(contactpersQuery);

        String reviewQery = "CREATE TABLE IF NOT EXISTS " + Review_class.TABLE + "( " +
                Review_class.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Review_class.DOCTOR_ID + " TEXT UNIQUE, " +
                Review_class.COMMENT + " TEXT);";
        db.execSQL(reviewQery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Biography.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ContactPerson.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Review_class.TABLE);

        onCreate(db);
    }

    //========================================BIOGRAPHY=============================================
    //add patient
    public void addPat_bio(Biography biography) {

        ContentValues bio_values = new ContentValues();

        bio_values.put(Biography.FIREBASE_ID, biography.getFirebase_Uid());
        bio_values.put(Biography.FIRSTNAME, biography.getFirstname());
        bio_values.put(Biography.LASTNAME, biography.getLastname());
        bio_values.put(Biography.OPD_ID, biography.getOpd_Id());
        bio_values.put(Biography.GENDER, biography.getGender());
        bio_values.put(Biography.COUNTRY_CODE, biography.getCountry_code());
        bio_values.put(Biography.MOBILE_NUMBER, biography.getMobile_number());
        bio_values.put(Biography.EMAIL, biography.getEmail());
        bio_values.put(Biography.MARITAL_STATUS,biography.getMarital_state());
        bio_values.put(Biography.DATE_OF_BIRTH, biography.getDate_of_birth());
        bio_values.put(Biography.PROFILE_PIC_URL, biography.getPropic_url());

        SQLiteDatabase sqDB = getWritableDatabase();

        try {
            sqDB.insertOrThrow(Biography.TABLE, null, bio_values);

            if (mContext instanceof OpdCardActivity) {
                ((OpdCardActivity) mContext).loadLocal_data();
            }

        } catch (Exception ignored) {
        }
    }

    //update patient
    public void updatePat_bio(Biography biography) {

        ContentValues bio_values = new ContentValues();

        bio_values.put(Biography.FIREBASE_ID, biography.getFirebase_Uid());
        bio_values.put(Biography.FIRSTNAME, biography.getFirstname());
        bio_values.put(Biography.LASTNAME, biography.getLastname());
        bio_values.put(Biography.OPD_ID, biography.getOpd_Id());
        bio_values.put(Biography.GENDER, biography.getGender());
        bio_values.put(Biography.COUNTRY_CODE, biography.getCountry_code());
        bio_values.put(Biography.MOBILE_NUMBER, biography.getMobile_number());
        bio_values.put(Biography.EMAIL, biography.getEmail());
        bio_values.put(Biography.DATE_OF_BIRTH, biography.getDate_of_birth());
        bio_values.put(Biography.MARITAL_STATUS, biography.getMarital_state());
        bio_values.put(Biography.PROFILE_PIC_URL, biography.getPropic_url());

        SQLiteDatabase sqDB = getWritableDatabase();

        sqDB.update(Biography.TABLE, bio_values, Biography.FIREBASE_ID + " = ? ", new String[]{biography.getFirebase_Uid()});

    }

    //---------------------------------fetch patient data for local use-----------------------------
    private Cursor appUserCursor(String firebaseID) {

        SQLiteDatabase sqDB = getReadableDatabase();

        String userQuery = "SELECT * FROM " + Biography.TABLE + " WHERE " + Biography.FIREBASE_ID + " = \"" + firebaseID + "\"";

        return sqDB.rawQuery(userQuery, null);

    } //get cursor from DB

    public Biography local_appUser(String firebaseID) {

        Biography app_user_bio;

        Cursor c = appUserCursor(firebaseID);
        c.moveToFirst();

        app_user_bio = new Biography(
                c.getString(c.getColumnIndexOrThrow(Biography.FIREBASE_ID)),
                c.getString(c.getColumnIndexOrThrow(Biography.OPD_ID)),
                c.getString(c.getColumnIndexOrThrow(Biography.FIRSTNAME)),
                c.getString(c.getColumnIndexOrThrow(Biography.LASTNAME)),
                c.getInt(c.getColumnIndexOrThrow(Biography.GENDER)),
                c.getString(c.getColumnIndexOrThrow(Biography.COUNTRY_CODE)),
                c.getString(c.getColumnIndexOrThrow(Biography.MOBILE_NUMBER)),
                c.getString(c.getColumnIndexOrThrow(Biography.EMAIL)),
                c.getString(c.getColumnIndexOrThrow(Biography.DATE_OF_BIRTH)),
                c.getInt(c.getColumnIndexOrThrow(Biography.MARITAL_STATUS)),
                c.getString(c.getColumnIndexOrThrow(Biography.PROFILE_PIC_URL))
        );

        c.close();

        return app_user_bio;

    } //create patient object from cursor
    //---------------------------------fetch patient data for local use-----------------------------
    //========================================BIOGRAPHY=============================================

    //==========================================CONTACT=============================================
    //add contact
    public boolean addContact(ContactPerson contactPerson) {

        ContentValues contPers_val = new ContentValues();

        contPers_val.put(ContactPerson.FIRE_ID, contactPerson.getUser_fireID());
        contPers_val.put(ContactPerson.FULL_NAME, contactPerson.getFullname());
        contPers_val.put(ContactPerson.EMAIL, contactPerson.getEmail());
        contPers_val.put(ContactPerson.ADDRESS, contactPerson.getAddress());
        contPers_val.put(ContactPerson.RELATION, contactPerson.getRelation());
        contPers_val.put(ContactPerson.MOBILE_NUMBER, contactPerson.getNumber());

        SQLiteDatabase sqDB = getWritableDatabase();
        try {
            sqDB.insertOrThrow(ContactPerson.TABLE, null, contPers_val);
            return true;
        } catch (SQLiteConstraintException constraint) {
            updateContact(contactPerson);
            return false;
        }
    }

    //update emergency contact
    public void updateContact(ContactPerson contactPerson) {

        ContentValues contPers_val = new ContentValues();

        contPers_val.put(ContactPerson.FIRE_ID, contactPerson.getUser_fireID());
        contPers_val.put(ContactPerson.FULL_NAME, contactPerson.getFullname());
        contPers_val.put(ContactPerson.EMAIL, contactPerson.getEmail());
        contPers_val.put(ContactPerson.RELATION, contactPerson.getRelation());
        contPers_val.put(ContactPerson.ADDRESS, contactPerson.getAddress());
        contPers_val.put(ContactPerson.MOBILE_NUMBER, contactPerson.getNumber());

        SQLiteDatabase sqDB = getWritableDatabase();
        try {
            sqDB.update(ContactPerson.TABLE, contPers_val,
                    ContactPerson.FIRE_ID + " = ? AND " + ContactPerson.RELATION + " = ? ",
                    new String[]{contactPerson.getUser_fireID(), String.valueOf(contactPerson.getRelation())});
        }catch (Exception ignored){}
    }

    //delete contact
    public void deleteContact(ContactPerson contactPerson) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        sqLiteDatabase.execSQL("DELETE FROM " + ContactPerson.TABLE + " WHERE "
                + ContactPerson.RELATION + " = \"" + contactPerson.getRelation() + "\" AND "
                + ContactPerson.FIRE_ID + " = \"" + contactPerson.getUser_fireID() + "\"");

        save_Online(contactsList(contactPerson.getUser_fireID()),contactPerson.getUser_fireID());

    }

    //--------------------------------------SAVE TO ONLINE DB-----------------------------------
    private void save_Online(ArrayList<ContactPerson> fireContacts, String fireID) {

        DatabaseReference all_users_ref = FirebaseDatabase.getInstance()
                .getReference(mContext.getResources().getString(R.string.all_users));

        //save user details to All_Users/Contacts/Uid
        all_users_ref.child(ContactPerson.TABLE).child(fireID)
                .setValue(fireContacts);
    }
    //--------------------------------------SAVE TO ONLINE DB-----------------------------------

    //fetch list of contacts
    public ArrayList<ContactPerson> contactsList(String fireID) {

        ArrayList<ContactPerson> contacts_list = new ArrayList<>();

        SQLiteDatabase sqDB = getReadableDatabase();

        String persQuery = "SELECT * FROM " + ContactPerson.TABLE + " WHERE " + ContactPerson.FIRE_ID + " = \"" + fireID + "\"";

        Cursor cont_Cursor = sqDB.rawQuery(persQuery, null);

        while (cont_Cursor.moveToNext()) {

            ContactPerson person = new ContactPerson(
                    cont_Cursor.getString(cont_Cursor.getColumnIndexOrThrow(ContactPerson.FIRE_ID)),
                    cont_Cursor.getString(cont_Cursor.getColumnIndexOrThrow(ContactPerson.FULL_NAME)),
                    cont_Cursor.getString(cont_Cursor.getColumnIndexOrThrow(ContactPerson.EMAIL)),
                    cont_Cursor.getString(cont_Cursor.getColumnIndexOrThrow(ContactPerson.MOBILE_NUMBER)),
                    "",
                    cont_Cursor.getInt(cont_Cursor.getColumnIndexOrThrow(ContactPerson.RELATION))
            );

            contacts_list.add(person);
        }

        cont_Cursor.close();
        return contacts_list;
    }
    //==========================================CONTACT=============================================

    //========================================DEPENDANTS============================================
    public void createDependants_Table(){
        SQLiteDatabase sqdb = getWritableDatabase();

        String sql = "CREATE TABLE IF NOT EXISTS " + Dependant_class.TABLE + "( " +
                Dependant_class.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Dependant_class.PARENT_FIRE_ID + " TEXT, " +
                Dependant_class.OPD_ID + " TEXT UNIQUE, " +
                Dependant_class.FIRSTNAME + " TEXT, " +
                Dependant_class.LASTNAME + " TEXT, " +
                Dependant_class.MOBILE_NUMBER + " TEXT, " +
                Dependant_class.DATE_OF_BIRTH + " TEXT, " +
                Dependant_class.GENDER + " INTEGER, " +
                Dependant_class.MARITAL_STATUS + " INTEGER );";

        sqdb.execSQL(sql);
    }

    //add dependants
    public void addDependant(Dependant_class dependant_class){

        ContentValues dependants_val = new ContentValues();

        dependants_val.put(Dependant_class.PARENT_FIRE_ID, dependant_class.getParent_fireID());
        dependants_val.put(Dependant_class.OPD_ID, dependant_class.getDepend_opd_id());
        dependants_val.put(Dependant_class.FIRSTNAME,dependant_class.getDepend_firstname());
        dependants_val.put(Dependant_class.LASTNAME,dependant_class.getDepend_lastname());
        dependants_val.put(Dependant_class.DATE_OF_BIRTH,dependant_class.getDate_of_birth());
        dependants_val.put(Dependant_class.GENDER, dependant_class.getGender());
        dependants_val.put(Dependant_class.MOBILE_NUMBER, dependant_class.getMobile_number());
        dependants_val.put(Dependant_class.MARITAL_STATUS, dependant_class.getMarital_state());

        SQLiteDatabase sqDB = getWritableDatabase();

        try {
            sqDB.insertOrThrow(Dependant_class.TABLE,null,dependants_val);
            Toast.makeText(mContext,"saving worked",Toast.LENGTH_LONG).show();
        }catch (Exception insertError){
            //Catch error to create table and retry insertion
            Toast.makeText(mContext,insertError.toString(),Toast.LENGTH_LONG).show();
        }


    }

    public void deleteDependant(String opd_number){
        SQLiteDatabase DB = getWritableDatabase();

        DB.delete(Dependant_class.TABLE,Dependant_class.OPD_ID + " =?",new String[]{opd_number});
    }

    private Cursor dependant_cursor(String firebase_id){

        SQLiteDatabase sqDb = getReadableDatabase();

        return sqDb.rawQuery("SELECT * FROM " + Dependant_class.TABLE,null);
    }

    public ArrayList<Dependant_class> all_dependants(String firebase_id){
        ArrayList<Dependant_class> dependants_list = new ArrayList<>();

        Cursor c = dependant_cursor(firebase_id);

        while (c.moveToNext()){
            Dependant_class depend = new Dependant_class(
                c.getString(c.getColumnIndexOrThrow(Dependant_class.OPD_ID)),
                c.getString(c.getColumnIndexOrThrow(Dependant_class.PARENT_FIRE_ID)),
                c.getString(c.getColumnIndexOrThrow(Dependant_class.FIRSTNAME)),
                c.getString(c.getColumnIndexOrThrow(Dependant_class.LASTNAME)),
                c.getString(c.getColumnIndexOrThrow(Dependant_class.MOBILE_NUMBER)),
                c.getString(c.getColumnIndexOrThrow(Dependant_class.DATE_OF_BIRTH)),
                c.getInt(c.getColumnIndexOrThrow(Dependant_class.GENDER)),
                c.getInt(c.getColumnIndexOrThrow(Dependant_class.MARITAL_STATUS))
            );

            dependants_list.add(depend);
        }

        return dependants_list;
    }
    //========================================DEPENDANTS============================================

    //=======================================HEALTH HISTORY=========================================
    public void createHistoryTable(String historyName, String fireUID){

        SQLiteDatabase db = getWritableDatabase();
        String tableName = historyName + "_" + fireUID;

        String medHistory = "CREATE TABLE IF NOT EXISTS " + tableName + "( " +
                History.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                History.FIRE_ID + " TEXT , " +
                History.LAST_UPDATED + " TEXT, " +
                History.STATE + " TEXT, " +
                History.REMARKS + " TEXT, " +
                History.QN_NUMBER + " INTEGER UNIQUE);";
        db.execSQL(medHistory);
    }

    //add medical history
    public void addMedicalHistory(String historyName, History history) {

        ContentValues history_values = new ContentValues();

        history_values.put(History.FIRE_ID, history.getUser_fireID());
        history_values.put(History.STATE, history.getState());
        history_values.put(History.REMARKS, history.getRemarks());
        history_values.put(History.LAST_UPDATED,history.getLastUpdated());
        history_values.put(History.QN_NUMBER, history.getQn_numb());

        SQLiteDatabase sqDB = getWritableDatabase();

        String tableName = historyName + "_" + history.getUser_fireID();

        try {
            sqDB.insertOrThrow(tableName, null, history_values);
        } catch (SQLiteConstraintException ignored) {
            updateMedicalHistory(historyName,history);
        }
    }

    private void updateMedicalHistory(String historyName, History history) {

        ContentValues history_values = new ContentValues();

        history_values.put(History.FIRE_ID, history.getUser_fireID());
        history_values.put(History.STATE, history.getState());
        history_values.put(History.REMARKS, history.getRemarks());
        history_values.put(History.LAST_UPDATED,history.getLastUpdated());
        history_values.put(History.QN_NUMBER, history.getQn_numb());

        SQLiteDatabase sqDB = getWritableDatabase();

        String tableName = historyName + "_" + history.getUser_fireID();

        sqDB.update(tableName, history_values, History.FIRE_ID + " = ? AND " + History.QN_NUMBER + " =? ",
                new String[]{history.getUser_fireID(), String.valueOf(history.getQn_numb())});
    }

    //fetch list of answers per history type
    public ArrayList<History> fetchAllHistory(String historyName, String fire_id) {

        ArrayList<History> allhistory = new ArrayList<>();
        SQLiteDatabase sqDB = getReadableDatabase();
        String tableName = historyName + "_" + fire_id;

        String query = "SELECT * FROM " + tableName;

        Cursor medCursor = sqDB.rawQuery(query, null);

        while (medCursor.moveToNext()) {

            History history = new History(
                    medCursor.getString(medCursor.getColumnIndexOrThrow(History.FIRE_ID)),
                    medCursor.getString(medCursor.getColumnIndexOrThrow(History.STATE)),
                    medCursor.getString(medCursor.getColumnIndexOrThrow(History.REMARKS)),
                    medCursor.getInt(medCursor.getColumnIndexOrThrow(History.QN_NUMBER)),
                    medCursor.getString(medCursor.getColumnIndexOrThrow(History.LAST_UPDATED))
            );

            allhistory.add(history);
        }

        medCursor.close();

        return allhistory;
    }
    //=======================================HEALTH HISTORY========================================= `

    //===========================================REVIEWS============================================

    //add review
    public void addReview(Review_class review) {

        ContentValues review_values = new ContentValues();

        review_values.put(Review_class.DOCTOR_ID, review.getDoctor_id());
        review_values.put(Review_class.COMMENT, review.getComment());

        SQLiteDatabase sqDB = getWritableDatabase();

        try {
            sqDB.insertOrThrow(Review_class.TABLE, null, review_values);
            createDocRevTable(review);
        } catch (SQLiteConstraintException ignored) {
            updateReview(review);
        }
    }

    private void updateReview(Review_class review) {

        ContentValues review_values = new ContentValues();

        review_values.put(Review_class.DOCTOR_ID, review.getDoctor_id());
        review_values.put(Review_class.COMMENT, review.getComment());

        SQLiteDatabase sqDB = getWritableDatabase();

        sqDB.update(Review_class.TABLE, review_values, Review_class.DOCTOR_ID + " = ?",
                new String[]{review.getDoctor_id()});
    }

    private void createDocRevTable(Review_class review){

        SQLiteDatabase db = getWritableDatabase();

        String docRev = "CREATE TABLE IF NOT EXISTS " + review.getDoctor_id() + "( " +
                Review_class.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Review_class.QNS_NUMBER + " INTEGER UNIQUE, " +
                Review_class.RATING + " INTEGER);";
        db.execSQL(docRev);

        addDocRevAns(review);
    }

    private void addDocRevAns(Review_class review){

        ContentValues rev_ans_values = new ContentValues();

        SQLiteDatabase db = getWritableDatabase();
        rev_ans_values.put(Review_class.QNS_NUMBER, review.getQn_number());
        rev_ans_values.put(Review_class.RATING, review.getReview_rating());

        try {
            db.insertOrThrow(review.getDoctor_id(),null,rev_ans_values);
        }catch (SQLiteConstraintException ignored){
            updateDocRevAns(review);
        }

    }

    private void updateDocRevAns(Review_class review){

        ContentValues rev_ans_values = new ContentValues();

        SQLiteDatabase db = getWritableDatabase();
        rev_ans_values.put(Review_class.QNS_NUMBER, review.getQn_number());
        rev_ans_values.put(Review_class.RATING, review.getReview_rating());

        db.update(Review_class.TABLE,rev_ans_values, Review_class.QNS_NUMBER + " = ?",
                new String[]{String.valueOf(review.getQn_number())});
    }

    //fetch review answers per doctor
    public ArrayList<Integer> fetchRevAnswers(String doctor_id) {

        ArrayList<Integer> allrev_ans = new ArrayList<>();
        SQLiteDatabase sqDB = getReadableDatabase();

        String query = "SELECT * FROM " + doctor_id;

        Cursor rev_ansCursor = sqDB.rawQuery(query, null);

        while (rev_ansCursor.moveToNext()) {
            allrev_ans.add(rev_ansCursor.getInt(rev_ansCursor.getColumnIndexOrThrow(Review_class.RATING)));
        }

        rev_ansCursor.close();

        return allrev_ans;
    }
    //===========================================REVIEWS============================================

}
