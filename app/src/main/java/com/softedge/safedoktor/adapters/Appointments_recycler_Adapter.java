package com.softedge.safedoktor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.databases.appDB;
import com.softedge.safedoktor.models.swaggerModels.Appointment;
import com.softedge.safedoktor.utilities.AppConstants;
import com.softedge.safedoktor.utilities.common_code;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Appointments_recycler_Adapter extends RecyclerView.Adapter {

    private List<Appointment> appointments;
    private WeakReference<AppCompatActivity> weak_activity;

    public Appointments_recycler_Adapter(AppCompatActivity activity, List<Appointment> apointment_list) {
        weak_activity = new WeakReference<>(activity);
        this.appointments = apointment_list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_appointment_list, parent, false);
        return new appointments_list_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((appointments_list_holder) holder).bind_views(appointments.get(position));

    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public class appointments_list_holder extends RecyclerView.ViewHolder implements View.OnClickListener {

//        TextView tv_deprow_fullname, tv_deprow_dob, tv_deprow_opdID;
//        SafeDB safe_db;

        TextView tv_booking_number, tv_appt_endtime,tv_appt_starttime,tv_appt_doctorName,tv_appt_date;
        ImageView iv_appt_chat_typ;
        CircleImageView iv_appt_doct_pic;
        ImageButton ib_appt_more;
        CardView card_appt_list_item;

        appDB appDatabase;
        Appointment glbAppointment;

        appointments_list_holder(View itemView) {
            super(itemView);

           // tv_deprow_fullname = itemView.findViewById(R.id.tv_dep_fullname);
           // tv_deprow_dob = itemView.findViewById(R.id.tv_deprow_dob);
          //  tv_deprow_opdID = itemView.findViewById(R.id.tv_dep_opdID);

            tv_booking_number = itemView.findViewById(R.id.tv_bookingno);
            tv_appt_date = itemView.findViewById(R.id.tv_appointment_date);
            tv_appt_starttime = itemView.findViewById(R.id.tv_appt_start_time);
            tv_appt_endtime = itemView.findViewById(R.id.tv_appt_end_time);
            tv_appt_doctorName = itemView.findViewById(R.id.tv_appoint_doc_name);

            iv_appt_chat_typ = itemView.findViewById(R.id.iv_chat_type_img);
            iv_appt_doct_pic = itemView.findViewById(R.id.iv_appt_doct_pic);

            ib_appt_more = itemView.findViewById(R.id.ib_appt_more);
            card_appt_list_item = itemView.findViewById(R.id.card_appt_list_item);

            itemView.setOnClickListener(this);
            card_appt_list_item.setOnClickListener(this);
            ib_appt_more.setOnClickListener(this);
            iv_appt_doct_pic.setOnClickListener(this);

            WeakReference<Context> weak_mcontext = new WeakReference<>(itemView.getContext());

            appDatabase = appDB.getInstance(weak_mcontext.get());
            //safe_db = new SafeDB(weak_mcontext.get(), null);

        }

        void bind_views(final Appointment appointment) {

            glbAppointment = appointment;
            appDatabase.safeDoktorAccessObj().getDetails(appointment.getBookingid()).observe(weak_activity.get(), rAppt_details -> {
                tv_appt_starttime.setText(common_code.readableTime(rAppt_details.getStarttime()));
                tv_appt_endtime.setText(common_code.readableTime(rAppt_details.getEndtime()));
                tv_appt_date.setText(common_code.readableDate(appointment.getBookingdate()));
            });

            if (appointment.getDoctorphoto() != null){
                Glide.with(weak_activity.get()).load(common_code.displayImage(appointment.getDoctorphoto())).centerCrop().into(iv_appt_doct_pic);
            }else {
                Glide.with(weak_activity.get()).load(weak_activity.get().getResources().getDrawable(R.drawable.generic_avatar)).centerCrop().into(iv_appt_doct_pic);
            }

            tv_appt_doctorName.setText(appointment.getDoctorname());
            tv_booking_number.setText(appointment.getBookingnumber());

            switch (appointment.getConsultationchattypeid()){
                case AppConstants.CHAT_TYPE_TEXT:
                    iv_appt_chat_typ.setImageDrawable(weak_activity.get().getResources().getDrawable(R.drawable.message_square,null));
                    break;
                case AppConstants.CHAT_TYPE_AUDIO:
                    iv_appt_chat_typ.setImageDrawable(weak_activity.get().getResources().getDrawable(R.drawable.ic_phone_deepgrey_24dp,null));
                    break;
                case AppConstants.CHAT_TYPE_VIDEO:
                    iv_appt_chat_typ.setImageDrawable(weak_activity.get().getResources().getDrawable(R.drawable.video,null));
                    break;
            }

            if (appointment.getStatusid() != AppConstants.APPT_STATUS_BOOKED){
                ib_appt_more.setVisibility(View.INVISIBLE);
            }

//            SimpleDateFormat row_date_format = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
//            SimpleDateFormat carewex_reg_format = new SimpleDateFormat(common_code.regDateformat,Locale.getDefault());
//
//            try {
//                Date date =  carewex_reg_format.parse(dependant.getDate_of_birth());
//
//                if (date != null) {
//                    String dateOfBirth = row_date_format.format(date);
//                    tv_deprow_dob.setText(dateOfBirth);
//                }
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//            String[] gender_arr = weak_activity.get().getResources().getStringArray(R.array.gender_Arr);
//            tv_deprow_opdID.setText(dependant.getDepend_opd_id());
//
//            String fullname = dependant.getDepend_firstname() + " " + dependant.getDepend_lastname();
//            tv_deprow_fullname.setText(fullname);

        }

        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.card_appt_list_item:
                    //Toast.makeText(weak_activity.get(),"Card clicked",Toast.LENGTH_SHORT).show();
                    if (glbAppointment != null){
                        startConsultation(glbAppointment);
                    }
                    break;
                case R.id.ib_appt_more:
                    Toast.makeText(weak_activity.get(),"More clicked",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.iv_appt_doct_pic:
                    Toast.makeText(weak_activity.get(),"Doctor picture clicked",Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        void startConsultation(Appointment appointment){
            Calendar calendar = Calendar.getInstance();
            Date today = calendar.getTime();

            if (today.before(common_code.getDateObject(appointment.getBookingdate()))){

                common_code.Mysnackbar(card_appt_list_item,"Consultation is not yet due", Snackbar.LENGTH_SHORT).show();

            }else if (today.after(common_code.getDateObject(appointment.getBookingdate()))){

                common_code.Mysnackbar(card_appt_list_item,"Consultation time already elapsed", Snackbar.LENGTH_SHORT).show();

            }else {
                //TODO START CONSULTATION HERE
            }
        }
    }

}
