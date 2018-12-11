package com.app.entero.direct.network;


import com.app.entero.direct.model.AllOrderModel;
import com.app.entero.direct.model.AllOrderSecondModel;
import com.app.entero.direct.model.DailyCollection_Report_Model;
import com.app.entero.direct.model.DataModel;
import com.app.entero.direct.model.DataModel;
import com.app.entero.direct.model.LoginModel;
import com.app.entero.direct.model.OffersModel;
import com.app.entero.direct.model.ProductListModel;
import com.app.entero.direct.model.ProductsModel;
import com.app.entero.direct.model.SalesmanDashBoardModel;
import com.app.entero.direct.model.SalesmanModel;
import com.app.entero.direct.model.Salesman_CustomerList_Model;
import com.app.entero.direct.model.SchemeListModel;
import com.app.entero.direct.model.StockistModel;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.LinkedHashMap;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
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

    @POST
    @FormUrlEncoded
    Observable<StockistModel> getStockistList(@Header("Authorization") String token, @Url String url, @FieldMap LinkedHashMap<String, String> linkedHashMap);

    @POST
    @FormUrlEncoded
    Observable<ProductListModel> getProductlist(@Header("Authorization") String token, @Url String url, @FieldMap LinkedHashMap<String, String> linkedHashMap);

    @POST
    @FormUrlEncoded
    Observable<SalesmanDashBoardModel> getSalesmanDashBoard(@Header("Authorization") String token, @Url String url, @FieldMap LinkedHashMap<String,String> linkedHashMap);

    @POST
    @FormUrlEncoded
    Observable<Salesman_CustomerList_Model> getSalesmanCustomerList(@Header("Authorization") String token, @Url String url, @FieldMap LinkedHashMap<String,String> linkedHashMap);
    @POST
    @FormUrlEncoded
    Observable<ProductsModel> getProductList(@Header("Authorization") String token, @Url String url, @FieldMap LinkedHashMap<String, String> linkedHashMap);

    @POST
    @FormUrlEncoded
    Observable<SchemeListModel> getAPP_SchemeforChemist(@Header("Authorization") String token,@Url String url, @FieldMap LinkedHashMap<String, String> linkedHashMap);

    @POST
    @FormUrlEncoded
    Observable<SalesmanModel> getOTPCode(@Header("Authorization") String token, @Url String url, @FieldMap LinkedHashMap<String, String> linkedHashMap);

    @POST
    @FormUrlEncoded
    Observable<AllOrderSecondModel> getAllOrderSecond(@Header("Authorization") String token, @Url String url, @FieldMap JsonObject jsonObject);

    @POST
    Observable<AllOrderModel> getAllOrder(@Header("Authorization") String token, @Url String url, @Body JsonObject jsonObject);
    @POST
    Observable<AllOrderSecondModel> getAllOrderSecond(@Header("Authorization") String token, @Url String url, @Body JsonObject jsonObject);

    @POST
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<DataModel> app_place_order(@Header("Authorization") String token, @Url String url, @Body RequestBody linkedHashMap);


    @POST
    @FormUrlEncoded
    Observable<OffersModel> getOfferList(@Header("Authorization") String token, @Url String url, @FieldMap LinkedHashMap<String, String> linkedHashMap);
    Observable<DailyCollection_Report_Model> getDailyCollection(@Header("Authorization") String token, @Url String url, @Body JsonObject jsonObject);

}


}


