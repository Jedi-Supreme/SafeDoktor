package com.softedge.care_assist.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.softedge.care_assist.R;

public class Affiliates_adapter extends ArrayAdapter {

    public Affiliates_adapter(@NonNull Context context, String[] facilities) {
        super(context, R.layout.row_affiliate_list ,facilities);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_affiliate_list, parent, false);
        }

        String affiliate = String.valueOf(getItem(position));

        TextView tv_fac_name = convertView.findViewById(R.id.tv_facility_fullname);
        tv_fac_name.setText(affiliate);

        return convertView;
    }
}
