package com.softedge.safedoktor.api;

import com.softedge.safedoktor.models.retrofitModels.retroPatient;
import com.softedge.safedoktor.models.retrofitModels.retroToken;
import com.softedge.safedoktor.models.retrofitModels.retro_patSearch;
import com.softedge.safedoktor.models.retrofitModels.token_ReqBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SafeClient {

    //fetch token
    @FormUrlEncoded
    @POST("http://carewex.newcrystalhealth.org:8280/auth/realms/carewex/protocol/openid-connect/token")
    Call<retroToken>getToken(
            @Field(token_ReqBody.PASSWORD) String password,
            @Field(token_ReqBody.USERNAME) String username,
            @Field(token_ReqBody.GRANT_TYPE) String grant_type,
            @Field(token_ReqBody.CLIENT_SECRET) String client_secret,
            @Field(token_ReqBody.CLIENT_ID) String client_id
    );

    //search patient
    @POST("/patient/search")
    Call<List<retroPatient>> patResults_list(@Body retro_patSearch search_body);



}
