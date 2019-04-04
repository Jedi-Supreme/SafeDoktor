package com.softedge.safedoktor.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.softedge.safedoktor.R;

public class ReviewActivity extends AppCompatActivity {

    TextView tv_rev_docfull_name;
    RecyclerView rev_recy_qns;
    TextInputEditText et_rev_comment;
    Button bt_rev_submit;

    String sup_doc;

    //===============================================ON CREATE======================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        //--------------------------------------HOME BUTTON ON APP BAR------------------------------
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //--------------------------------------HOME BUTTON ON APP BAR------------------------------

        sup_doc = getResources().getString(R.string.whatsup_doc);

        tv_rev_docfull_name = findViewById(R.id.tv_docfull_name);
        rev_recy_qns = findViewById(R.id.rev_recy_qns);
        et_rev_comment = findViewById(R.id.et_rev_comment);
        bt_rev_submit = findViewById(R.id.bt_rev_submit);

        //TODO review questions adapter

    }
    //===============================================ON CREATE======================================

    //----------------------------------------OVERRIDE METHODS--------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                super.onBackPressed();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    //----------------------------------------OVERRIDE METHODS--------------------------------------


}
