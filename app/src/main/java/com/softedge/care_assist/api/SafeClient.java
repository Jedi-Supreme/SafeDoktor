package com.softedge.care_assist.api;

import android.support.annotation.RawRes;

import com.softedge.care_assist.models.retrofitModels.regResult;
import com.softedge.care_assist.models.retrofitModels.retroEmployee;
import com.softedge.care_assist.models.retrofitModels.retroPatient;
import com.softedge.care_assist.models.retrofitModels.retroToken;
import com.softedge.care_assist.models.retrofitModels.retro_patSearch;
import com.softedge.care_assist.models.retrofitModels.pat_List;
import com.softedge.care_assist.models.retrofitModels.token_ReqBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

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
    @POST("http://carewex.newcrystalhealth.org:8180/carewex/rest/patient/search/")
    Call<pat_List> patResults_list(
            @Header("Authorization") String credentials,
            @Body retro_patSearch search_body);

    //Get employee
    @GET("http://carewex.newcrystalhealth.org:8180/carewex/rest/employees/account/{username}")
    Call<retroEmployee> employee(
            @Header("Authorization") String credentials,
            @Path("username") String username
    );

    //register patient
    @POST("http://carewex.newcrystalhealth.org:8180/carewex/rest/consumer/sync/patient/{employeeId}")
    Call<regResult> patReg(
            @Header("Authorization") String credentials,
            @Body retroPatient patient,
            @Path("employeeId") String employeeId
    );


}
