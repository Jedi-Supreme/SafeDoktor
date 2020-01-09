package com.softedge.safedoktor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.softedge.safedoktor.R;
import com.softedge.safedoktor.activities.Contacts_dependantsActivity;
import com.softedge.safedoktor.databases.SafeDB;
import com.softedge.safedoktor.models.fireModels.Dependant_class;
import com.softedge.safedoktor.models.fireModels.PatientPackage.ContactPerson;
import com.softedge.safedoktor.utilities.common_code;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class dependants_recycler_Adapter extends RecyclerView.Adapter {

    private ArrayList<Dependant_class> dependants;
    private WeakReference<AppCompatActivity> weak_activity;

    public dependants_recycler_Adapter(AppCompatActivity activity, ArrayList<Dependant_class> dependants_list) {
        weak_activity = new WeakReference<>(activity);
        this.dependants = dependants_list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_dependants_list, parent, false);
        return new contacts_list_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((contacts_list_holder) holder).bind_views(dependants.get(position));

    }

    @Override
    public int getItemCount() {
        return dependants.size();
    }

    public class contacts_list_holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_deprow_fullname, tv_deprow_dob, tv_deprow_opdID;
        SafeDB safe_db;

        contacts_list_holder(View itemView) {
            super(itemView);

            tv_deprow_fullname = itemView.findViewById(R.id.tv_dep_fullname);
            tv_deprow_dob = itemView.findViewById(R.id.tv_deprow_dob);
            tv_deprow_opdID = itemView.findViewById(R.id.tv_dep_opdID);
            itemView.setOnClickListener(this);

            WeakReference<Context> weak_mcontext = new WeakReference<>(itemView.getContext());

            safe_db = new SafeDB(weak_mcontext.get(), null);

        }

        void bind_views(final Dependant_class dependant) {

            SimpleDateFormat row_date_format = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            SimpleDateFormat carewex_reg_format = new SimpleDateFormat(common_code.regDateformat,Locale.getDefault());

            try {
                Date date =  carewex_reg_format.parse(dependant.getDate_of_birth());

                if (date != null) {
                    String dateOfBirth = row_date_format.format(date);
                    tv_deprow_dob.setText(dateOfBirth);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String[] gender_arr = weak_activity.get().getResources().getStringArray(R.array.gender_Arr);
            tv_deprow_opdID.setText(dependant.getDepend_opd_id());

            String fullname = dependant.getDepend_firstname() + " " + dependant.getDepend_lastname();
            tv_deprow_fullname.setText(fullname);

        }

        @Override
        public void onClick(View v) {

        }
    }

}
