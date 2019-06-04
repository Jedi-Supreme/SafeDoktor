package com.softedge.care_assist.activities;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.softedge.care_assist.R;

public class TOS_Activity extends AppCompatActivity {

    WebView tos_web;
    ProgressBar probar_toc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tos);

        tos_web = findViewById(R.id.wbv_tos);
        probar_toc = findViewById(R.id.probar_toc);

        tos_web.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                // Do something on page loading started
                // Visible the progressbar
                probar_toc.setVisibility(View.VISIBLE);
            }

        });

        /*
            WebView
                A View that displays web pages. This class is the basis upon which you can roll your
                own web browser or simply display some online content within your Activity. It uses
                the WebKit rendering engine to display web pages and includes methods to navigate
                forward and backward through a history, zoom in and out, perform text searches and more.

            WebChromeClient
                 WebChromeClient is called when something that might impact a browser UI happens,
                 for instance, progress updates and JavaScript alerts are sent here.
        */
        tos_web.setWebChromeClient(new WebChromeClient(){
            /*
                public void onProgressChanged (WebView view, int newProgress)
                    Tell the host application the current progress of loading a page.

                Parameters
                    view : The WebView that initiated the callback.
                    newProgress : Current page loading progress, represented by an integer
                        between 0 and 100.
            */
            public void onProgressChanged(WebView view, int newProgress){
                // Update the progress bar with page loading progress
                probar_toc.setProgress(newProgress);
                if(newProgress == 100){
                    // Hide the progressbar
                    probar_toc.setVisibility(View.GONE);
                }
            }
        });

        // Render the web page
        String terms_url = "https://nchs-care-assistant.firebaseapp.com";
        tos_web.loadUrl(terms_url);


        if (tos_web.getProgress() >= 100){
            probar_toc.setVisibility(View.GONE);
        }

    }

    @Override
    public void onBackPressed() {
        super.finish();
    }

    @Override
    protected void onDestroy() {
        tos_web.clearCache(true);
        super.onDestroy();

    }
}
