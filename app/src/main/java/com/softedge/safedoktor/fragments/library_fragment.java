package com.softedge.safedoktor.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.softedge.safedoktor.R;

public class library_fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_content_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        TabHost lib_tabhost = view.findViewById(R.id.content_fraghost);
        lib_tabhost.setup();

        String firstAid = getResources().getString(R.string.first_aid_txt);
        String healthlib = getResources().getString(R.string.health_posts_txt);

        TabHost.TabSpec first_aidSpec = lib_tabhost.newTabSpec(firstAid);
        TabHost.TabSpec health_libSpec = lib_tabhost.newTabSpec(healthlib);

        first_aidSpec.setContent(R.id.content_frag_recycler);
        health_libSpec.setContent(R.id.content_frag_recycler);

        first_aidSpec.setIndicator(firstAid);
        health_libSpec.setIndicator(healthlib);

        lib_tabhost.addTab(first_aidSpec);
        lib_tabhost.addTab(health_libSpec);

    }

}
