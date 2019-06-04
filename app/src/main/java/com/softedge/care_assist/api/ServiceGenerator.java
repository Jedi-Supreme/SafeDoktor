package com.softedge.care_assist.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final String BASEURL = "http://carewex.newcrystalhealth.org";

    private static Retrofit.Builder retroBuilder =
            new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofitObj = retroBuilder.build();

    public static <S> S createService(Class<S> serviceClass){

        return retrofitObj.create(serviceClass);
    }
}
