package com.softedge.safedoktor.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.softedge.safedoktor.R;
import com.softedge.safedoktor.common_code;
import com.softedge.safedoktor.models.fireModels.PatientPackage.ContactPerson;
import com.softedge.safedoktor.models.fireModels.Review_class;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class review_Adapter extends RecyclerView.Adapter {

    private String[] questions_arr;
    private ViewGroup view_group;

    public ArrayList<Review_class> getReviews() {
        return reviews;
    }

    private ArrayList<Review_class> reviews;

    public review_Adapter(String[] questions_arr, @Nullable ArrayList<Review_class> reviews) {
        this.questions_arr = questions_arr;
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_review_doctor, parent, false);

        view_group = parent;
        return new review_list_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (reviews != null){
            ((review_list_holder) holder).bind_views(questions_arr[position], reviews.get(position));
        }else {
            ((review_list_holder) holder).bind_views(questions_arr[position], null);
        }
    }

    @Override
    public int getItemCount() {
        return questions_arr.length;
    }

    public class review_list_holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_question;
        TextInputEditText et_review_ans;
        WeakReference<Context> weak_mcontext;
        String sup_doc;

        review_list_holder(View itemView) {
            super(itemView);

            tv_question = itemView.findViewById(R.id.tv_review_qn);
            et_review_ans = itemView.findViewById(R.id.et_review_answer);
            et_review_ans.setOnClickListener(this);
            weak_mcontext = new WeakReference<>(itemView.getContext());

            sup_doc = weak_mcontext.get().getResources().getString(R.string.whatsup_doc);

        }

        void bind_views(final String question, Review_class review) {

            String fullQn = String.valueOf(getAdapterPosition()+1) + ") " +sup_doc + " " + question;

            tv_question.setText(fullQn);

            if (review != null){
                switch (review.getReview_rating()){

                    case common_code.REV_LOWEST:
                        et_review_ans.setText(weak_mcontext.get().getResources().getString(R.string.rev_lowest));
                        break;

                    case common_code.REV_LOW:
                        et_review_ans.setText(weak_mcontext.get().getResources().getString(R.string.rev_low));
                        break;

                    case common_code.REV_MEDIUM:
                        et_review_ans.setText(weak_mcontext.get().getResources().getString(R.string.rev_medium));
                        break;

                    case common_code.REV_HIGH:
                        et_review_ans.setText(weak_mcontext.get().getResources().getString(R.string.rev_high));
                        break;

                    case common_code.REV_HIGHEST:
                        et_review_ans.setText(weak_mcontext.get().getResources().getString(R.string.rev_highest));
                        break;
                }
            }
        }

        void review_ans_dialog(){

            AlertDialog rev_builder = new AlertDialog.Builder(weak_mcontext.get()).create();
            //final AlertDialog contactdialog = new android.support.v7.app.AlertDialog.Builder(weakcontact.get()).create();

            rev_builder.setCancelable(true);

            View rev_ansView = LayoutInflater.from(weak_mcontext.get())
                    .inflate(R.layout.frag_rev_ans, view_group, false);

            RadioGroup rdg_revans = rev_ansView.findViewById(R.id.rdg_rev_answers);


            Button bt_ans_submit = rev_ansView.findViewById(R.id.bt_pop_submit);

            rev_builder.setView(rev_ansView);

            bt_ans_submit.setOnClickListener(v -> {

            });

            rev_builder.show();
        }

        @Override
        public void onClick(View v) {
            review_ans_dialog();
        }
    }


}
