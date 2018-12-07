
package com.app.entero.direct.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class OffersModel implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("msg")
    @Expose
    private String msg;

    @SerializedName("img")
    @Expose
    private String img;

    @SerializedName("tabName")
    @Expose
    private String tabName;

    @SerializedName("tabDes")
    @Expose
    private String tabDes;

    @SerializedName("quantitly")
    @Expose
    private String quantitly;

    @SerializedName("distributerName")
    @Expose
    private String distributerName;

    @SerializedName("Offer_name")
    @Expose
    private String Offer_name;

    @SerializedName("entityOfferList")
    @Expose
    private ArrayList<OffersModel> entityOfferList;


    @SerializedName("Start_date")
    @Expose
    private String Start_date;


    @SerializedName("End_date")
    @Expose
    private String End_date;


    @SerializedName("Offer_image")
    @Expose
    private String Offer_image;


    @SerializedName("Product_ID")
    @Expose
    private String Product_ID;


    public String getOffer_name() {
        return Offer_name;
    }

    public void setOffer_name(String offer_name) {
        Offer_name = offer_name;
    }

    public ArrayList<OffersModel> getEntityOfferList() {
        return entityOfferList;
    }

    public void setEntityOfferList(ArrayList<OffersModel> entityOfferList) {
        this.entityOfferList = entityOfferList;
    }

    public String getStart_date() {
        return Start_date;
    }

    public void setStart_date(String start_date) {
        Start_date = start_date;
    }

    public String getEnd_date() {
        return End_date;
    }

    public void setEnd_date(String end_date) {
        End_date = end_date;
    }

    public String getOffer_image() {
        return Offer_image;
    }

    public void setOffer_image(String offer_image) {
        Offer_image = offer_image;
    }

    public String getProduct_ID() {
        return Product_ID;
    }

    public void setProduct_ID(String product_ID) {
        Product_ID = product_ID;
    }

    public String getDistributerName() {
        return distributerName;
    }

    public void setDistributerName(String distributerName) {
        this.distributerName = distributerName;
    }



    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getTabDes() {
        return tabDes;
    }

    public void setTabDes(String tabDes) {
        this.tabDes = tabDes;
    }

    public String getQuantitly() {
        return quantitly;
    }

    public void setQuantitly(String quantitly) {
        this.quantitly = quantitly;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
