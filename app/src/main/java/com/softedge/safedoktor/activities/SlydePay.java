package com.softedge.safedoktor.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.softedge.safedoktor.R;

public class SlydePay extends AppCompatActivity {

    WebView wv_slyde_pay;

    // merchant / API key
    public static String MERCHANT_KEY = "1593792493018";

    // email or mobile number associated with the merchant account
    public static String EMAIL_OR_MOBILE_NUMBER = "jedidiah.hit@newcrystalhealth.org";

    // callback url
    public static String CALLBACK_URL = "https://safedoktor.newcrystalhealth.org/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slyde_pay);

        wv_slyde_pay = findViewById(R.id.wv_slyde_pay);
    }

}
