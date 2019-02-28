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
import com.softedge.safedoktor.databases.SafeDB;

import java.lang.ref.WeakReference;

public class historyQns_Adapter extends RecyclerView.Adapter {

    private String[] questions_arr;
    private WeakReference<AppCompatActivity> weak_activity;

    public historyQns_Adapter(AppCompatActivity activity, String[] questions_arr) {
        weak_activity = new WeakReference<>(activity);
        this.questions_arr = questions_arr;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_row_item, parent, false);
        return new question_list_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((question_list_holder) holder).bind_views(questions_arr[position]);
    }

    @Override
    public int getItemCount() {
        return questions_arr.length;
    }

    public class question_list_holder extends RecyclerView.ViewHolder {

        TextView tv_question;
        SafeDB safe_db;

        question_list_holder(View itemView) {
            super(itemView);

            tv_question = itemView.findViewById(R.id.tv_history_qns);

            WeakReference<Context> weak_mcontext = new WeakReference<>(itemView.getContext());

            safe_db = new SafeDB(weak_mcontext.get(), null);

        }

        void bind_views(final String question) {
            tv_question.setText(question);

        }

    }
}
