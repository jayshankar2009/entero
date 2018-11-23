package com.app.entero.direct.network;


import com.app.entero.direct.model.LoginModel;
import com.app.entero.direct.model.SalesmanModel;
import com.google.gson.JsonObject;

import java.util.LinkedHashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiCallService {

    @POST
    @FormUrlEncoded
    Observable<LoginModel> login(@Header("Authorization") String token, @Url String url, @FieldMap LinkedHashMap<String, String> linkedHashMap);

    @POST
    @FormUrlEncoded
    Observable<Object> getHomeData(@Url String url, @FieldMap LinkedHashMap<String, String> linkedHashMap);

    @POST
    @FormUrlEncoded
    Observable<SalesmanModel> get_SalesmandetailsByIMEI(@Header("Authorization") String token, @Url String url, @FieldMap LinkedHashMap<String, String> linkedHashMap);

    @POST
    @FormUrlEncoded
    Observable<Object> get_Token(@Url String url, @FieldMap LinkedHashMap<String, String> linkedHashMap);


}


