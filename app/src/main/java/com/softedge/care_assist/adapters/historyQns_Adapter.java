package com.softedge.care_assist.adapters;

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

import com.softedge.care_assist.databases.SafeDB;
import com.softedge.care_assist.models.fireModels.HistoryPackage.History;
import com.softedge.care_assist.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class historyQns_Adapter extends RecyclerView.Adapter {

    private String[] questions_arr;

    public ArrayList<History> getHistories() {
        return histories;
    }

    private ArrayList<History> histories;

    public historyQns_Adapter(String[] questions_arr, ArrayList<History> histories) {
        this.questions_arr = questions_arr;
        this.histories = histories;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_history_list, parent, false);
        return new question_list_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((question_list_holder) holder).bind_views(questions_arr[position],histories.get(position));
    }

    @Override
    public int getItemCount() {
        return questions_arr.length;
    }

    public class question_list_holder extends RecyclerView.ViewHolder
            implements CompoundButton.OnCheckedChangeListener, TextWatcher {

        TextView tv_question;
        SafeDB safe_db;
        SwitchCompat switchAnswers;
        TextInputEditText et_qn_remark;

        question_list_holder(View itemView) {
            super(itemView);

            tv_question = itemView.findViewById(R.id.tv_history_qns);
            et_qn_remark = itemView.findViewById(R.id.et_qn_remarks);
            et_qn_remark.addTextChangedListener(this);
            switchAnswers = itemView.findViewById(R.id.switch_answer);
            switchAnswers.setOnCheckedChangeListener(this);

            WeakReference<Context> weak_mcontext = new WeakReference<>(itemView.getContext());

            safe_db = new SafeDB(weak_mcontext.get(), null);

        }

        void bind_views(final String question, History history) {
            tv_question.setText(question);
            switchAnswers.setChecked(Boolean.valueOf(history.getState()));

            if (!history.getRemarks().isEmpty()){
                et_qn_remark.setText(history.getRemarks());
            }
        }


        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            int adapterpos = getAdapterPosition();
            histories.get(adapterpos).setState(String.valueOf(isChecked));

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            int adapterpos = getAdapterPosition();
            histories.get(adapterpos).setRemarks(et_qn_remark.getText().toString());
        }
    }


}
