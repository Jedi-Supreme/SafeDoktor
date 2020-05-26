package com.softedge.safedoktor.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SafeDoktorService {

    private static final String BASEURL = "http://159.89.231.79:1350/";

    private static Retrofit.Builder retroBuilder =
            new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofitObj = retroBuilder.build();

    public static <S> S createService(Class<S> serviceClass){

        return retrofitObj.create(serviceClass);
    }
}
