package com.softedge.safedoktor.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SafeDoktorService {

//    private static final String BASEURL = "http://159.89.231.79:1350/";
    private static final String secureURL = "https://safedoktorapi.newcrystalhealth.org";

    //TODO handle retrofit timeout error

    private static Retrofit.Builder retroBuilder =
            new Retrofit.Builder()
                    .baseUrl(secureURL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofitObj = retroBuilder.build();

    public static <S> S createService(Class<S> serviceClass){

        return retrofitObj.create(serviceClass);
    }
}
