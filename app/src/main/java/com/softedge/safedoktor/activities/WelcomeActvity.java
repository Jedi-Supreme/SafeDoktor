package com.softedge.safedoktor.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextSwitcher;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.softedge.safedoktor.R;
import com.softedge.safedoktor.utilities.common_code;

public class WelcomeActvity extends AppCompatActivity {


    TextSwitcher ts_intro_texts, ts_intro_titles;
    ImageSwitcher is_intro_images;

    RadioGroup rdg_intro_selections;

    Button bt_intro_skip, bt_intro_next;

    int intro_index = 0;

    int[] images_Arr;

    String[] intro_texts_arr;
    String[] intro_titles_arr;

    //=========================================ON CREATE============================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ts_intro_texts = findViewById(R.id.ts_intro_texts);
        ts_intro_titles = findViewById(R.id.ts_intro_titles);
        is_intro_images = findViewById(R.id.is_intro_images);

        rdg_intro_selections = findViewById(R.id.rdg_intro_pointer);

        bt_intro_skip = findViewById(R.id.bt_intro_skip);
        bt_intro_next = findViewById(R.id.bt_intro_next);

        intro_texts_arr = getResources().getStringArray(R.array.intro_texts_arr);
        intro_titles_arr = getResources().getStringArray(R.array.intro_titles_arr);
        images_Arr = new int[]{
                R.drawable.nchs_app_logo,
                R.drawable.onboarding_reg,
                R.drawable.onboarding_phone,
                R.drawable.onboarding_text,
                R.drawable.onboarding_smile
        };

        ts_intro_titles.setFactory(() -> {
            TextView tv_intro = new TextView(getApplicationContext());
            tv_intro.setTextSize(20);
            tv_intro.setTextColor(getResources().getColor(R.color.deep_blue_grey));
            tv_intro.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            return tv_intro;
        });


        ts_intro_texts.setFactory(() -> {
            TextView tv_intro = new TextView(getApplicationContext());
            tv_intro.setTextSize(16);
            tv_intro.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            return tv_intro;
        });

        is_intro_images.setFactory(() -> {
            ImageView iv_images = new ImageView(getApplicationContext());
            iv_images.setScaleType(ImageView.ScaleType.FIT_XY);
            iv_images.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            return iv_images;
        });

        try {
            present_intro(intro_index);
        } catch (Exception intro_error) {
            common_code.Mysnackbar(findViewById(R.id.const_intro_layout), intro_error.toString(), Snackbar.LENGTH_INDEFINITE).show();
        }

        rdg_intro_selections.setOnCheckedChangeListener((group, checkedId) -> {

            switch (rdg_intro_selections.getCheckedRadioButtonId()) {

                case R.id.rb1:
                    intro_index = 0;
                    present_intro(intro_index);
                    break;

                case R.id.rb2:
                    intro_index = 1;
                    present_intro(intro_index);
                    break;

                case R.id.rb3:
                    intro_index = 2;
                    present_intro(intro_index);
                    break;

                case R.id.rb4:
                    intro_index = 3;
                    present_intro(intro_index);
                    break;

                case R.id.rb5:
                    intro_index = 4;
                    present_intro(intro_index);
                    break;
            }
        });

        bt_intro_next.setOnClickListener(v -> {
            try {
                present_intro(++intro_index);
            } catch (Exception ignored) {
                tologin();
            }
        });

        bt_intro_skip.setOnClickListener(v -> tologin());


    }
    //=========================================ON CREATE============================================

    void present_intro(int index) {
        ts_intro_titles.setText(intro_titles_arr[index]);
        ts_intro_texts.setText(intro_texts_arr[index]);
        is_intro_images.setImageDrawable(getResources().getDrawable(images_Arr[index]));

        RadioButton rb_checked = (RadioButton) rdg_intro_selections.getChildAt(index);
        rb_checked.setChecked(true);
    }

    void tologin() {
        SharedPreferences safe_pref = getSharedPreferences(getResources().getString(R.string.safe_pref_name), MODE_PRIVATE);
        SharedPreferences.Editor pref_editor = safe_pref.edit();
        pref_editor.putBoolean(getResources().getString(R.string.first_run_prefkey), false).apply();
        Intent login_intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(login_intent);
    }

}
