package com.softedge.safedoktor.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.api.SwaggerCalls;
import com.softedge.safedoktor.databases.appDB;
import com.softedge.safedoktor.models.swaggerModels.ApptBooking;
import com.softedge.safedoktor.models.swaggerModels.PatientModel;
import com.softedge.safedoktor.models.swaggerModels.body.Doctor;
import com.softedge.safedoktor.models.swaggerModels.body.Specialties;
import com.softedge.safedoktor.models.swaggerModels.body.TimeSlot;
import com.softedge.safedoktor.utilities.AppConstants;
import com.softedge.safedoktor.utilities.common_code;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;

import static com.softedge.safedoktor.utilities.AppConstants.*;

public class VirtualAppt_Activity extends AppCompatActivity implements TabHost.OnTabChangeListener, RadioGroup.OnCheckedChangeListener {

    TabHost appt_tabhost;
    final static String INFO_TAG = "Info";
    final static String PAYMENT_TAG = "Payment";
    final static String SUMMARY_TAG = "Summary";

    //=============================BOOKING PARAM==========================
    int chatTypeId;
    String momoNetworkId;
    String doctorId;
    int serviceId;
    int slotId;
    int service_fee;
    //=============================BOOKING PARAM==========================

    WeakReference<VirtualAppt_Activity> weak_apptmake;
    ConstraintLayout const_Virtual_appt;

    //    DatePicker app_date_picker;
    private AlertDialog slots_dialog;
    private AlertDialog fee_dialog;
    private AlertDialog submission_dialog;

    RadioGroup rbg_appt_specialties, rbg_appt_doctors, rbg_appt_dates, rbg_appt_times, rbg_appt_constype, rbg_appt_momotype;

    appDB appDb;

    PatientModel patient;
    ApptBooking booking;

    TextView //tv_spec_view,
            tv_docs_view;

    TextView tv_sum_specialty, tv_sum_doctor,
            tv_sum_date, tv_sum_time, tv_sum_cons_type,
            tv_sum_mm_type, tv_sum_mobile_number, tv_sum_fee, tv_sum_patName;
    TextInputEditText et_mm_number, et_mm_fee;

    //==========================================ON CREATE===========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appt_make);

        weak_apptmake = new WeakReference<>(this);
        appDb = appDB.getInstance(weak_apptmake.get());

        patient = common_code.currentUser(weak_apptmake.get());
        //--------------------------------------HOME BUTTON ON APP BAR------------------------------
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //--------------------------------------HOME BUTTON ON APP BAR------------------------------

        //------------------------------------------TAB HOST----------------------------------------
        appt_tabhost = findViewById(R.id.appt_tabhost);
        appt_tabhost.setup();

        TabHost.TabSpec infoSpec = appt_tabhost.newTabSpec(INFO_TAG);
        TabHost.TabSpec paymentSpec = appt_tabhost.newTabSpec(PAYMENT_TAG);
        TabHost.TabSpec sumSpec = appt_tabhost.newTabSpec(SUMMARY_TAG);

        infoSpec.setIndicator(INFO_TAG);
        paymentSpec.setIndicator(PAYMENT_TAG);
        sumSpec.setIndicator(SUMMARY_TAG);

        infoSpec.setContent(R.id.appt_info_view);
        paymentSpec.setContent(R.id.appt_payment_view);
        sumSpec.setContent(R.id.appt_sum_view);

        appt_tabhost.addTab(infoSpec);
        appt_tabhost.addTab(paymentSpec);
        appt_tabhost.addTab(sumSpec);

        appt_tabhost.getTabWidget().setEnabled(false);
        //------------------------------------------TAB HOST----------------------------------------

//        app_date_picker = findViewById(R.id.appt_date_picker);

        tv_docs_view = findViewById(R.id.tv_docs_view);
        tv_sum_specialty = findViewById(R.id.tv_appt_sum_spec);
        tv_sum_doctor = findViewById(R.id.tv_appt_sum_doc);
        tv_sum_date = findViewById(R.id.tv_appt_sum_date);
        tv_sum_time = findViewById(R.id.tv_appt_sum_time);
        tv_sum_mm_type = findViewById(R.id.tv_appt_sum_mm);
        tv_sum_mobile_number = findViewById(R.id.tv_appt_sum_numb);
        tv_sum_fee = findViewById(R.id.tv_appt_sum_fee);
        tv_sum_cons_type = findViewById(R.id.tv_appt_sum_type);
        tv_sum_patName = findViewById(R.id.tv_sum_patName);

        et_mm_number = findViewById(R.id.et_mm_number);
        et_mm_fee = findViewById(R.id.et_mm_fee);

        const_Virtual_appt = findViewById(R.id.const_virtual_appt);

        rbg_appt_specialties = findViewById(R.id.rbg_appt_specialties);
        rbg_appt_doctors = findViewById(R.id.rbg_appt_doctors);
        rbg_appt_dates = findViewById(R.id.rbg_appt_dates);
        rbg_appt_times = findViewById(R.id.rbg_appt_times);
        rbg_appt_constype = findViewById(R.id.rbg_appt_cons_type);
        rbg_appt_momotype = findViewById(R.id.rbg_appt_momo_type);

        //Limit
//        Calendar endDate = Calendar.getInstance();
//        endDate.add(Calendar.DAY_OF_MONTH, 7);
//
//        Calendar startDate = Calendar.getInstance();
//
//        app_date_picker.setMinDate(startDate.getTimeInMillis());
//        app_date_picker.updateDate(startDate.get(Calendar.YEAR),startDate.get(Calendar.MONTH),startDate.get(Calendar.DAY_OF_MONTH));
//
//        startDate.add(Calendar.DAY_OF_MONTH, 7);
//        app_date_picker.setMaxDate(startDate.getTimeInMillis());

        tv_docs_view.setOnClickListener(v -> common_code.toDocProfile(weak_apptmake.get()));

        rbg_appt_constype.setOnCheckedChangeListener(weak_apptmake.get());
        rbg_appt_momotype.setOnCheckedChangeListener(weak_apptmake.get());
        //TODO Load time slots
        //loadSlots();

    }
    //==========================================ON CREATE===========================================

    //--------------------------------------------OVERRIDE METHODS----------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabChanged(String tabId) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (group.getId()) {

            case R.id.rbg_appt_specialties:
//                Toast.makeText(weak_apptmake.get(),"specialty id:", Toast.LENGTH_SHORT).show();
                serviceId = checkedId;

                if (common_code.isInternetConnected(weak_apptmake.get())) {
                    loadSlotsBySpecialtyDiag(checkedId);
                } else {
                    common_code.Mysnackbar(const_Virtual_appt, "Please Check your internet connection and try again", Snackbar.LENGTH_SHORT).show();
                }

                break;

            case R.id.rbg_appt_doctors:
                doctorId = common_code.rebuildDoctorId(String.valueOf(checkedId)) + checkedId;
//                Toast.makeText(weak_apptmake.get(), doctorid + rbg_appt_specialties.getCheckedRadioButtonId(), Toast.LENGTH_SHORT).show();
                refreshDates(doctorId, rbg_appt_specialties.getCheckedRadioButtonId());
                break;

            case R.id.rbg_appt_dates:
                doctorId = common_code.rebuildDoctorId(String.valueOf(rbg_appt_doctors.getCheckedRadioButtonId())) + rbg_appt_doctors.getCheckedRadioButtonId();
                try {
                    String date = ((RadioButton) rbg_appt_dates.findViewById(checkedId)).getText().toString();
                    refreshTimes(common_code.reverseDate(date), doctorId);
                } catch (Exception ignored) {
                }
                break;

            case R.id.rbg_appt_times:
                slotId = rbg_appt_times.getCheckedRadioButtonId();
//                Log.e("TimeSlot Id",String.valueOf(rbg_appt_times.getCheckedRadioButtonId()));
                break;

            case R.id.rbg_appt_momo_type:
            case R.id.rbg_appt_cons_type:
                setNetwork_ChatTypeId(checkedId);
                break;

        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        et_mm_number.setText(patient.getPhonenumber());
        refreshSpecialties();
    }

    //--------------------------------------------OVERRIDE METHODS----------------------------------

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-CLICK LISTENER=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    public void Appointment_make(View view) {

        switch (view.getId()) {

            case R.id.bt_appt_info_next:
                checkInfoSelections();
                break;

            case R.id.bt_appt_pay_next:
                checkPaymentSelections();
                break;

            case R.id.bt_appt_pay_prev:
                appt_tabhost.setCurrentTabByTag(INFO_TAG);
                break;

            case R.id.bt_appt_sum_next:
                submitBooking();
//                dialer_intent();
                //TODO complete appointment booking process
                break;

            case R.id.bt_appt_sum_prev:
                appt_tabhost.setCurrentTabByTag(PAYMENT_TAG);
                break;
        }
    }
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-CLICK LISTENER=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    //-----------------------------------------DEFINED METHODS--------------------------------------
    void loadSlotsBySpecialtyDiag(int specialtyId) {

        String notification = "Fetching Slots Please wait...";

        slots_dialog = new AlertDialog.Builder(weak_apptmake.get()).create();

        View diagView = LayoutInflater.from(weak_apptmake.get()).inflate(R.layout.diag_load_slots, const_Virtual_appt, false);
        slots_dialog.setView(diagView);

        TextView tv_diag_notifiation = diagView.findViewById(R.id.tv_diag_notification);
        slots_dialog.setCancelable(false);

        tv_diag_notifiation.setText(notification);

        SwaggerCalls.getSpecialtyTimeSlots(const_Virtual_appt, specialtyId);
        slots_dialog.show();
    }

    void loadServiceFee(int serviceId, int chatTypeId) {

        String notification = "Fetching Service fee Please wait...";

        fee_dialog = new AlertDialog.Builder(weak_apptmake.get()).create();

        View diagView = LayoutInflater.from(weak_apptmake.get()).inflate(R.layout.diag_load_slots, const_Virtual_appt, false);
        fee_dialog.setView(diagView);

        TextView tv_diag_notification = diagView.findViewById(R.id.tv_diag_notification);
        fee_dialog.setCancelable(false);

        tv_diag_notification.setText(notification);

        SwaggerCalls.loadServiceFee(const_Virtual_appt, chatTypeId, serviceId);
        fee_dialog.show();
    }

    void refreshSpecialties() {

        appDb.safeDoktorAccessObj().getAllSpecialties().observe(weak_apptmake.get(), specialties -> {

            for (Specialties specialty : specialties) {
                RadioButton rb_spec = (RadioButton) LayoutInflater.from(weak_apptmake.get()).inflate(R.layout.inc_normal_rb, const_Virtual_appt, false);
                rb_spec.setText(specialty.getName());
                rb_spec.setId(specialty.getId());
                //rb_spec.setOnCheckedChangeListener(weak_apptmake.get());
//                Drawable drawable = common_code.roundedImage(weak_apptmake.get());
//                rb_spec.setCompoundDrawablesWithIntrinsicBounds(null,drawable,null,null);
                rbg_appt_specialties.addView(rb_spec);
                rbg_appt_specialties.setOnCheckedChangeListener(weak_apptmake.get());
            }


            //rec_appt_specialties.setAdapter(appointmentsAdapter);

        });


    }

    void refreshDoctors(List<Doctor> specDoctors) {


        for (Doctor doc : specDoctors) {
            String strId = doc.getId();

            Log.e("refdoc", "Name: " + doc.getFullName() + " UserID: " + doc.getId());

            RadioButton rb_doc = (RadioButton) LayoutInflater.from(weak_apptmake.get()).inflate(R.layout.inc_img_rb, const_Virtual_appt, false);

            try {
                int id = Integer.parseInt(strId.substring(strId.length() - 8));
                rb_doc.setId(id);
            } catch (Exception ignored) {
            }

            rb_doc.setText(doc.getFullName());

            if (!doc.getDoctorPhoto().isEmpty()) {

                Glide.with(weak_apptmake.get())
                        .asBitmap()
                        .load(common_code.displayImage(doc.getDoctorPhoto())).into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Bitmap bitmap = Bitmap.createScaledBitmap(resource, 200, 200, false);

                        rb_doc.setCompoundDrawablesWithIntrinsicBounds(null, RoundedBitmapDrawableFactory.create(getResources(), bitmap), null, null);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
            } else {
                Drawable drawable = common_code.roundedImage(weak_apptmake.get());
                rb_doc.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
            }

            //rb_doc.setOnCheckedChangeListener(weak_apptmake.get());
            rbg_appt_doctors.addView(rb_doc);
            rbg_appt_doctors.setOnCheckedChangeListener(weak_apptmake.get());
        }
        //rec_appt_specialties.setAdapter(appointmentsAdapter);


    }

    void refreshDates(String doctorId, int specialtyId) {

        rbg_appt_dates.removeAllViews();
        rbg_appt_times.removeAllViews();

        appDb.safeDoktorAccessObj().getSlotDateString(doctorId, specialtyId, common_code.addDayDate(0)).observe(weak_apptmake.get(), strings -> {
            for (int index = 0; index < strings.size(); index++) {
                RadioButton rb_spec = (RadioButton) LayoutInflater.from(weak_apptmake.get()).inflate(R.layout.inc_normal_rb, const_Virtual_appt, false);

                Log.e("date b4", strings.get(index));
                rb_spec.setText(common_code.readableDate(strings.get(index)));
                rb_spec.setId(dateRbId(doctorId, index));
                //rb_spec.setOnCheckedChangeListener(weak_apptmake.get());
//                Drawable drawable = common_code.roundedImage(weak_apptmake.get());
//                rb_spec.setCompoundDrawablesWithIntrinsicBounds(null,drawable,null,null);
                rbg_appt_dates.addView(rb_spec);
                rbg_appt_dates.setOnCheckedChangeListener(weak_apptmake.get());
                //refreshTimes(timeSlots);
            }
        });
    }

    int dateRbId(String doctorId, int index) {

        String strId = doctorId + index;

        try {
            return Integer.parseInt(strId.substring(strId.length() - 8));
        } catch (Exception e) {
            Log.e("RB iD", e.toString());
            return 0;
        }
    }

    void refreshTimes(String date, String doctorId) {

        rbg_appt_times.removeAllViews();

        appDb.safeDoktorAccessObj().getSlotsByDate(date).observe(weak_apptmake.get(), timeSlots -> {

            for (TimeSlot timeslot : timeSlots) {

//                Log.e(doctorId,timeslot.getDoctorid());

                if (timeslot.getDoctorid().equals(doctorId) && timeslot.getSpecialityid() == rbg_appt_specialties.getCheckedRadioButtonId()) {
                    RadioButton rb_spec = (RadioButton) LayoutInflater.from(weak_apptmake.get()).inflate(R.layout.inc_normal_rb, const_Virtual_appt, false);

                    String time = common_code.readableTime(timeslot.getStarttime()) + " - " + common_code.readableTime(timeslot.getEndtime());
                    rb_spec.setText(time);
                    rb_spec.setId(timeslot.getSlotid());

                    rbg_appt_times.addView(rb_spec);
                    rbg_appt_times.setOnCheckedChangeListener(weak_apptmake.get());
                }
            }

        });

    }

    public void dismissDialog(AlertDialog dialog) {
        if (dialog != null) {
            dialog.dismiss();
//            getDoctorsForSpecialtySlots(rbg_appt_specialties.getCheckedRadioButtonId());
        }

        //refreshDoctors();
    }

    public void dismissSubmitDialog() {
        if (submission_dialog != null) {
            submission_dialog.dismiss();
        }
    }

    public void getDoctorsForSpecialtySlots(int specialtyId) {

        //SwaggerCalls.getSpecialtyTimeSlots(const_Virtual_appt,specialtyId);

        dismissDialog(slots_dialog);

        appDb.safeDoktorAccessObj().distinctDoctorIds(specialtyId, common_code.addDayDate(0)).observe(weak_apptmake.get(), strings -> {

            Log.e("distinct doc", "String: " + strings.size());

            appDb.safeDoktorAccessObj().getDoctorById(strings).observe(weak_apptmake.get(), doctors -> {

                if (doctors.size() > 0) {
                    refreshDoctors(doctors);
                } else {
                    removeViews_uncheck(new RadioGroup[]{rbg_appt_doctors, rbg_appt_dates, rbg_appt_times});
                }
            });


        });
    }

    void removeViews_uncheck(RadioGroup[] radioGroups) {

        for (RadioGroup radioGroup : radioGroups) {
            radioGroup.removeAllViews();
            radioGroup.clearCheck();
        }
    }

    void checkInfoSelections() {

        if (rbg_appt_specialties.getCheckedRadioButtonId() == NO_SELECTION) {
            Log.e("specialtyid", String.valueOf(rbg_appt_specialties.getCheckedRadioButtonId()));
            common_code.Mysnackbar(const_Virtual_appt, "Choose Specialty", Snackbar.LENGTH_SHORT).show();

        } else if (rbg_appt_doctors.getCheckedRadioButtonId() == NO_SELECTION) {
            common_code.Mysnackbar(const_Virtual_appt, "Choose Appointment Doctor", Snackbar.LENGTH_SHORT).show();

        } else if (rbg_appt_dates.getCheckedRadioButtonId() == NO_SELECTION) {
            common_code.Mysnackbar(const_Virtual_appt, "Pick Date", Snackbar.LENGTH_SHORT).show();

        } else if (rbg_appt_times.getCheckedRadioButtonId() == NO_SELECTION) {
            common_code.Mysnackbar(const_Virtual_appt, "Select consultation time", Snackbar.LENGTH_SHORT).show();

        } else {
            Log.e("specialtyid", String.valueOf(rbg_appt_specialties.getCheckedRadioButtonId()));
            appt_tabhost.setCurrentTabByTag(PAYMENT_TAG);
        }
    }

    void checkPaymentSelections() {

        if (rbg_appt_constype.getCheckedRadioButtonId() == NO_SELECTION) {
            common_code.Mysnackbar(const_Virtual_appt, "Choose Consultation type", Snackbar.LENGTH_SHORT).show();

        } else if (rbg_appt_momotype.getCheckedRadioButtonId() == NO_SELECTION) {
            common_code.Mysnackbar(const_Virtual_appt, "Choose Payment network", Snackbar.LENGTH_SHORT).show();

        } else if (et_mm_number.getText() == null || et_mm_number.getText().toString().isEmpty()) {
            common_code.Mysnackbar(const_Virtual_appt, "Enter Mobile Wallet Number", Snackbar.LENGTH_SHORT).show();

        } else {
            //Created booking object
            booking = new ApptBooking(
                    chatTypeId,
                    doctorId,
                    momoNetworkId,
                    patient.getPatientid(),
                    serviceId,
                    slotId,
                    et_mm_number.getText().toString()
            );
            setSummaryValues();
            appt_tabhost.setCurrentTabByTag(SUMMARY_TAG);
        }
    }

    void setNetwork_ChatTypeId(int viewId) {

        switch (viewId) {

            case R.id.rb_mm_mtn:
                momoNetworkId = MOMO_MTN;
                break;

            case R.id.rb_mm_voda:
                momoNetworkId = MOMO_VODAFONE;
                break;

            case R.id.rb_mm_airtigo:
                momoNetworkId = MOMO_AIRTIGO;
                break;

            case R.id.rb_cons_txt:
                chatTypeId = AppConstants.CHAT_TYPE_TEXT;
                loadServiceFee(serviceId, chatTypeId);
                break;

            case R.id.rb_cons_audio:
                chatTypeId = CHAT_TYPE_AUDIO;
                loadServiceFee(serviceId, chatTypeId);
                break;

            case R.id.rb_cons_video:
                chatTypeId = CHAT_TYPE_VIDEO;
                loadServiceFee(serviceId, chatTypeId);
                break;
        }
    }

    void setSummaryValues() {

        String specialtyName, time, date, doctor, chatType, patientName, serviceFee;

        specialtyName = ((RadioButton) rbg_appt_specialties.findViewById(rbg_appt_specialties.getCheckedRadioButtonId())).getText().toString();
        time = ((RadioButton) rbg_appt_times.findViewById(rbg_appt_times.getCheckedRadioButtonId())).getText().toString();
        date = ((RadioButton) rbg_appt_dates.findViewById(rbg_appt_dates.getCheckedRadioButtonId())).getText().toString();
        doctor = ((RadioButton) rbg_appt_doctors.findViewById(rbg_appt_doctors.getCheckedRadioButtonId())).getText().toString();
        chatType = ((RadioButton) rbg_appt_constype.findViewById(rbg_appt_constype.getCheckedRadioButtonId())).getText().toString();
        serviceFee = Objects.requireNonNull(et_mm_fee.getText()).toString();
        patientName = patient.getFirstname() + " " + patient.getLastname();


        tv_sum_doctor.setText(doctor);
        tv_sum_date.setText(date);
        tv_sum_time.setText(time);
        tv_sum_cons_type.setText(chatType);
        tv_sum_specialty.setText(specialtyName);
        tv_sum_patName.setText(patientName);
        tv_sum_fee.setText(serviceFee);

        tv_sum_mm_type.setText(momoNetworkId);
        tv_sum_mobile_number.setText(Objects.requireNonNull(et_mm_number.getText()).toString());

    }

    public void setServiceFee(int fee) {

        et_mm_fee.setText(String.valueOf(fee));
        service_fee = fee;
        dismissDialog(fee_dialog);
    }

    //TODO SEND PAYMENT REQUEST TO REDDE AND DIAL RETURNED OTP CODE
    // if payment succeeds, submit booking object

    void submitBooking() {
        //TODO Call slyde pay api from here



//        String notification = "Booking appointment Please wait...";
//
//        submission_dialog = new AlertDialog.Builder(weak_apptmake.get()).create();
//
//        View diagView = LayoutInflater.from(weak_apptmake.get()).inflate(R.layout.diag_load_slots, const_Virtual_appt, false);
//        submission_dialog.setView(diagView);
//
//        TextView tv_diag_notification = diagView.findViewById(R.id.tv_diag_notification);
//        submission_dialog.setCancelable(false);
//
//        tv_diag_notification.setText(notification);
//
//        submission_dialog.show();
//
//        if (booking != null){
//            SwaggerCalls.loadBookAndPay(const_Virtual_appt,booking,service_fee,((RadioButton) rbg_appt_constype.findViewById(rbg_appt_constype.getCheckedRadioButtonId())).getText().toString());
//        }else {
//            Toast.makeText(weak_apptmake.get(),"Booking empty", Toast.LENGTH_SHORT).show();
//        }


    }

    public void navigateToWebViewActivity(String orderCode,String payToken){
        Intent intent = new Intent(weak_apptmake.get(),SlydePay.class);
        intent.putExtra("url","https://app.slydepay.com/paylive/detailsnew.aspx?pay_token=" + payToken);
        intent.putExtra("orderCode",orderCode);
        intent.putExtra("payToken",payToken);
        startActivity(intent);
    }

    @SuppressLint("MissingPermission")
    void dialer_intent(String reddeUssd) {

        Intent intent = new Intent(Intent.ACTION_CALL);
//        String checkcredit = "*124" + Uri.encode("#");
        intent.setData(Uri.parse("tel:" + reddeUssd));

        if (common_code.checkPermissionForPhone(weak_apptmake.get())) {

            startActivity(intent);
        }else {
            common_code.requestPermissionForPhone(this);
        }

    }

    //-----------------------------------------DEFINED METHODS--------------------------------------
}