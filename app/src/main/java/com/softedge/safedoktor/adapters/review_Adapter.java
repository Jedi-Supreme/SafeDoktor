package com.softedge.safedoktor.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.softedge.safedoktor.R;
import com.softedge.safedoktor.databases.SafeDB;
import com.softedge.safedoktor.models.fireModels.HistoryPackage.History;
import com.softedge.safedoktor.models.fireModels.Review_class;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class review_Adapter extends RecyclerView.Adapter {

    private String[] questions_arr;

    private ArrayList<Review_class> reviews;

    public review_Adapter(String[] questions_arr, ArrayList<Review_class> reviews) {
        this.questions_arr = questions_arr;
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_review_doctor, parent, false);
        return new question_list_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((question_list_holder) holder).bind_views(questions_arr[position], reviews.get(position));
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

        void bind_views(final String question, Review_class review) {
            tv_question.setText(question);

        }

    }


}
