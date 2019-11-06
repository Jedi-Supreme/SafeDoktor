package com.softedge.care_assist.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.softedge.care_assist.R;

public class partners_fragment extends Fragment implements TabHost.OnTabChangeListener {

    private final static String HOSPITALS = "hospitals";
    private final static String PHARMS = "pharmacy";
    private final static String SPECIALS = "specials";
    private final static String DIAGCENTER = "diagCenter";
    private final static String CHAD = "chad";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_partners_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        TabHost part_tabhost = view.findViewById(R.id.part_tabhost);
        part_tabhost.setup();

        String hospitals = getResources().getString(R.string.lbl_hospitals);
        String specialists = getResources().getString(R.string.lbl_specialists);
        String pharmacies = getResources().getString(R.string.lbl_pharmacies);
        String diagCenter = getResources().getString(R.string.lbl_diagnostic);
        String chad = getString(R.string.chad);

        TabHost.TabSpec hospitalSpec = part_tabhost.newTabSpec(HOSPITALS);
        TabHost.TabSpec specialSpec = part_tabhost.newTabSpec(SPECIALS);
        TabHost.TabSpec pharmSpec = part_tabhost.newTabSpec(PHARMS);
        TabHost.TabSpec diagSpec = part_tabhost.newTabSpec(DIAGCENTER);
        TabHost.TabSpec chadSpec = part_tabhost.newTabSpec(CHAD);


        hospitalSpec.setIndicator(hospitals);
        specialSpec.setIndicator(specialists);
        pharmSpec.setIndicator(pharmacies);
        diagSpec.setIndicator(diagCenter);
        chadSpec.setIndicator(chad);

        hospitalSpec.setContent(R.id.tab_recycler);
        specialSpec.setContent(R.id.tab_recycler);
        pharmSpec.setContent(R.id.tab_recycler);
        diagSpec.setContent(R.id.tab_recycler);
        chadSpec.setContent(R.id.tab_recycler);

        part_tabhost.addTab(hospitalSpec);
        part_tabhost.addTab(specialSpec);
        part_tabhost.addTab(pharmSpec);
        part_tabhost.addTab(diagSpec);
        part_tabhost.addTab(chadSpec);

        part_tabhost.setOnTabChangedListener(this);

    }

    @Override
    public void onTabChanged(String tabId) {

    }
}
