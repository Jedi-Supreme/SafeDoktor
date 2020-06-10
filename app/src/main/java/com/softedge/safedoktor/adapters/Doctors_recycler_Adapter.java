package com.softedge.safedoktor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.databases.appDB;
import com.softedge.safedoktor.models.swaggerModels.body.Doctor;
import com.softedge.safedoktor.utilities.common_code;

import java.lang.ref.WeakReference;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Doctors_recycler_Adapter extends RecyclerView.Adapter {

    private List<Doctor> doctors;
    private WeakReference<AppCompatActivity> weak_activity;

    public Doctors_recycler_Adapter(AppCompatActivity activity, List<Doctor> doctors) {
        weak_activity = new WeakReference<>(activity);
        this.doctors = doctors;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_doctors_list, parent, false);
        return new appointments_list_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((appointments_list_holder) holder).bind_views(doctors.get(position));

    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public class appointments_list_holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_doc_doctorName,tv_doc_email;
        CircleImageView iv_doc_doct_pic;
        CardView card_doc_list_item;

        appDB appDatabase;

        appointments_list_holder(View itemView) {
            super(itemView);

            tv_doc_doctorName = itemView.findViewById(R.id.tv_doc_doc_name);
            tv_doc_email = itemView.findViewById(R.id.tv_doc_doc_email);

            iv_doc_doct_pic = itemView.findViewById(R.id.iv_doc_doct_pic);
            card_doc_list_item = itemView.findViewById(R.id.card_doc_list_item);

            itemView.setOnClickListener(this);

            WeakReference<Context> weak_mcontext = new WeakReference<>(itemView.getContext());

            appDatabase = appDB.getInstance(weak_mcontext.get());
            //safe_db = new SafeDB(weak_mcontext.get(), null);

        }

        void bind_views(final Doctor doctor) {


            if (!doctor.getDoctorPhoto().isEmpty()){
                Glide.with(weak_activity.get()).load(common_code.displayImage(doctor.getDoctorPhoto())).centerCrop().into(iv_doc_doct_pic);
            }else {
                Glide.with(weak_activity.get()).load(R.drawable.generic_avatar).centerCrop().into(iv_doc_doct_pic);
            }

            tv_doc_doctorName.setText(doctor.getDocName());
            tv_doc_email.setText(doctor.getEmail());

        }

        @Override
        public void onClick(View v) {

        }

    }

}
