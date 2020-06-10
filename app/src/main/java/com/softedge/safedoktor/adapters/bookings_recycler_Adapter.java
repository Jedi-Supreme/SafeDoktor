package com.softedge.safedoktor.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softedge.safedoktor.R;
import com.softedge.safedoktor.models.swaggerModels.body.Specialties;

import java.util.List;

import static android.media.CamcorderProfile.get;

public class bookings_recycler_Adapter extends RecyclerView.Adapter {

    private List<Specialties> specialties;

    public bookings_recycler_Adapter(List<Specialties> specialties) {
        this.specialties = specialties;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_booking_item, parent, false);
        return new specialties_list_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((specialties_list_holder) holder).bind_views(specialties.get(position));

    }

    @Override
    public int getItemCount() {
        return specialties.size();
    }

    public class specialties_list_holder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {

        RadioButton rb_booking_item;
        RadioGroup rbgroup;

        specialties_list_holder(View itemView) {
            super(itemView);

//            rb_booking_item = itemView.findViewById(R.id.rb_booking_item);
//            rb_booking_item.setOnCheckedChangeListener(this);

            rb_booking_item = new RadioButton(itemView.getContext());
            rbgroup = itemView.findViewById(R.id.rbg_booking_item);
            rbgroup.addView(rb_booking_item,getAdapterPosition());

        }

        void bind_views(final Specialties specialties) {

            rb_booking_item.setChecked(specialties.isChecked());
            rb_booking_item.setText(specialties.getName());

//            rbgroup.addView(itemView,getAdapterPosition());
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int selectedPosition = getAdapterPosition();
            specialties.get(selectedPosition).setChecked(isChecked);
        }
    }

}
