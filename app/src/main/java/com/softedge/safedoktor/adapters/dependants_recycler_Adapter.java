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

import java.lang.ref.WeakReference;
import java.util.ArrayList;

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

    //TODO finish dependanta Ddpter

    public class contacts_list_holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_relation, tv_contact_fullname, tv_contact_delete;
        SafeDB safe_db;

        contacts_list_holder(View itemView) {
            super(itemView);

            tv_relation = itemView.findViewById(R.id.tv_contact_rel);
            tv_contact_fullname = itemView.findViewById(R.id.tv_contact_fullname);
            tv_contact_delete = itemView.findViewById(R.id.tv_contact_delete);
            itemView.setOnClickListener(this);

            WeakReference<Context> weak_mcontext = new WeakReference<>(itemView.getContext());

            safe_db = new SafeDB(weak_mcontext.get(), null);

        }

        void bind_views(final Dependant_class dependant) {

            conper = contact;
            String[] rel_arr = weak_activity.get().getResources().getStringArray(R.array.relations);

            tv_relation.setText(rel_arr[contact.getRelation()]);
            tv_contact_fullname.setText(contact.getFullname());

            tv_contact_delete.setOnClickListener(v -> {

                safe_db.deleteContact(contact);

                ((Contacts_dependantsActivity) weak_activity.get()).refresh_contacts_list();

            });

        }

        @Override
        public void onClick(View v) {
            ((Contacts_dependantsActivity) weak_activity.get()).ContactPersonDialog(conper);
        }
    }

}
