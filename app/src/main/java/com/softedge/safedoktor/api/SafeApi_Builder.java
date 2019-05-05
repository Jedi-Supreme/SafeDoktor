package com.softedge.safedoktor.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SafeApi_Builder {

    //public static final String BASE_URL = "carewex.newcrystalhealth.org:";

    private String safeURL;

    public SafeApi_Builder(String safeURL) {
        this.safeURL = safeURL;
    }

    Retrofit retrofit = new Retrofit.Builder()

            .baseUrl(safeURL)

            .addConverterFactory(GsonConverterFactory.create())

            .build();

    public String getURL() {
        return safeURL;
    }

    public void setURL(String safeURL) {
        this.safeURL = safeURL;
    }
}
