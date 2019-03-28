package com.softedge.safedoktor.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.softedge.safedoktor.R;

public class OnlineVisits_adapter extends RecyclerView.Adapter {

    //TODO constrctor to take list of online visits and bind

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new onlineVisits_holder
                (LayoutInflater.from(parent.getContext()).inflate(R.layout.row_onlinevisits_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class onlineVisits_holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView iv_docpic;
        View online_state_indicator;
        LinearLayout linear_options;
        TextView
                tv_date, tv_ov_type,
                tv_toggle, tv_complaints,
                tv_diagnosis, tv_examsFindings,
                tv_referrals, tv_prescriptions, tv_ov_docname;

        onlineVisits_holder(View itemView) {
            super(itemView);

            linear_options = itemView.findViewById(R.id.linear_ov_options);
            iv_docpic = itemView.findViewById(R.id.iv_ov_docpic);
            online_state_indicator = itemView.findViewById(R.id.view_online_status);

            tv_date = itemView.findViewById(R.id.tv_ov_date);
            tv_ov_docname = itemView.findViewById(R.id.tv_ov_doc_name);
            tv_ov_type = itemView.findViewById(R.id.tv_ov_type);

            tv_toggle = itemView.findViewById(R.id.tv_visit_toggle_more);
            tv_toggle.setOnClickListener(this);

            tv_complaints  = itemView.findViewById(R.id.tv_ov_complaints);
            tv_complaints.setOnClickListener(this);

            tv_diagnosis = itemView.findViewById(R.id.tv_ov_diag);
            tv_diagnosis.setOnClickListener(this);

            tv_examsFindings  = itemView.findViewById(R.id.tv_ov_exams);
            tv_examsFindings.setOnClickListener(this);

            tv_referrals = itemView.findViewById(R.id.tv_ov_ref);
            tv_referrals.setOnClickListener(this);

            tv_prescriptions = itemView.findViewById(R.id.tv_ov_pres);
            tv_prescriptions.setOnClickListener(this);

        }

        void bind_views() {

        }

        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.tv_visit_toggle_more:

                    if (linear_options.getVisibility() == View.GONE){
                        linear_options.setVisibility(View.VISIBLE);
                        tv_toggle.setBackground(itemView.getContext().getDrawable(R.drawable.ic_keyb_arrow_up_deepgrey_24dp));

                    }else {
                        linear_options.setVisibility(View.GONE);
                        tv_toggle.setBackground(itemView.getContext().getDrawable(R.drawable.ic_keyb_arrow_down_deepgrey_24dp));
                    }

                    break;

                case R.id.tv_ov_complaints:
                    break;

                case R.id.tv_ov_diag:
                    break;

                case R.id.tv_ov_exams:
                    break;

                case R.id.tv_ov_ref:
                    break;

                case R.id.tv_ov_pres:
                    break;
            }

        }
    }
}
