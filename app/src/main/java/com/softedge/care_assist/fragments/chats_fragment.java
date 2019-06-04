package com.softedge.care_assist.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.softedge.care_assist.R;

public class chats_fragment extends Fragment {

    TabHost chat_tabhost;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_content_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chat_tabhost = view.findViewById(R.id.content_fraghost);
        chat_tabhost.setup();

        String chats = getResources().getString(R.string.chat);
        String adverts = getResources().getString(R.string.adverts);

        TabHost.TabSpec first_aidSpec = chat_tabhost.newTabSpec(chats);
        TabHost.TabSpec health_libSpec = chat_tabhost.newTabSpec(adverts);

        first_aidSpec.setContent(R.id.content_frag_recycler);
        health_libSpec.setContent(R.id.content_frag_recycler);

        first_aidSpec.setIndicator(chats);
        health_libSpec.setIndicator(adverts);

        chat_tabhost.addTab(first_aidSpec);
        chat_tabhost.addTab(health_libSpec);
    }
}
