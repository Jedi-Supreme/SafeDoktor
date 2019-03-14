package com.softedge.safedoktor.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softedge.safedoktor.R;
import com.softedge.safedoktor.activities.ContactsActivity;
import com.softedge.safedoktor.databases.SafeDB;
import com.softedge.safedoktor.fireModels.PatientPackage.ContactPerson;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class contacts_recycler_Adapter extends RecyclerView.Adapter {

    private ArrayList<ContactPerson> contacts;
    private WeakReference<AppCompatActivity> weak_activity;

    public contacts_recycler_Adapter(AppCompatActivity activity, ArrayList<ContactPerson> contacts_list) {
        weak_activity = new WeakReference<>(activity);
        this.contacts = contacts_list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_contacts_item, parent, false);
        return new contacts_list_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((contacts_list_holder) holder).bind_views(contacts.get(position));

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class contacts_list_holder extends RecyclerView.ViewHolder {

        TextView tv_relation, tv_contact_fullname, tv_contact_delete;
        SafeDB safe_db;

        contacts_list_holder(View itemView) {
            super(itemView);

            tv_relation = itemView.findViewById(R.id.tv_contact_rel);
            tv_contact_fullname = itemView.findViewById(R.id.tv_contact_fullname);
            tv_contact_delete = itemView.findViewById(R.id.tv_contact_delete);

            WeakReference<Context> weak_mcontext = new WeakReference<>(itemView.getContext());

            safe_db = new SafeDB(weak_mcontext.get(), null);

        }

        void bind_views(final ContactPerson contact) {

            String[] rel_arr = weak_activity.get().getResources().getStringArray(R.array.relations);

            tv_relation.setText(rel_arr[contact.getRelation()]);
            tv_contact_fullname.setText(contact.getFullname());

            tv_contact_delete.setOnClickListener(v -> {

                safe_db.deleteContact(contact);

                ((ContactsActivity) weak_activity.get()).refresh_contacts_list();

            });

        }

    }
}
