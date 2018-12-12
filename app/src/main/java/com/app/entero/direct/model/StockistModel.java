
package com.app.entero.direct.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class StockistModel implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("ClientID")
    @Expose
    private String ClientID;

    @SerializedName("entityStockistList")
    @Expose
    private ArrayList<StockistModel> entityStockistList;

    @SerializedName("Client_LegalName")
    @Expose
    private String Client_LegalName;

    @SerializedName("ClientTypeID")
    @Expose
    private String ClientTypeID;

    @SerializedName("Client_Email")
    @Expose
    private String Client_Email;

    @SerializedName("Accepted_id")
    @Expose
    private String Accepted_id;

    @SerializedName("Is_Locked")
    @Expose
    private String Is_Locked;

    @SerializedName("lockunlock")
    @Expose
    private String lockunlock;


    @SerializedName("Client_Address")
    @Expose
    private String Client_Address;

    @SerializedName("StateID")
    @Expose
    private String StateID;


    @SerializedName("CityID")
    @Expose
    private String CityID;


    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("PinCode")
    @Expose
    private String PinCode;


    @SerializedName("URL")
    @Expose
    private String URL;


    @SerializedName("MobileNo")
    @Expose
    private String MobileNo;

    @SerializedName("Product_Count")
    @Expose
    private String Product_Count;

    @SerializedName("Outstanding")
    @Expose
    private String Outstanding;

    public String getClient_Address() {
        return Client_Address;
    }

    public void setClient_Address(String client_Address) {
        Client_Address = client_Address;
    }

    public String getStateID() {
        return StateID;
    }

    public void setStateID(String stateID) {
        StateID = stateID;
    }

    public String getCityID() {
        return CityID;
    }

    public void setCityID(String cityID) {
        CityID = cityID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPinCode() {
        return PinCode;
    }

    public void setPinCode(String pinCode) {
        PinCode = pinCode;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getProduct_Count() {
        return Product_Count;
    }

    public void setProduct_Count(String product_Count) {
        Product_Count = product_Count;
    }

    public String getOutstanding() {
        return Outstanding;
    }

    public void setOutstanding(String outstanding) {
        Outstanding = outstanding;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public String getClient_LegalName() {
        return Client_LegalName;
    }

    public void setClient_LegalName(String client_LegalName) {
        Client_LegalName = client_LegalName;
    }

    public String getClientTypeID() {
        return ClientTypeID;
    }

    public void setClientTypeID(String clientTypeID) {
        ClientTypeID = clientTypeID;
    }

    public String getClient_Email() {
        return Client_Email;
    }

    public void setClient_Email(String client_Email) {
        Client_Email = client_Email;
    }

    public String getAccepted_id() {
        return Accepted_id;
    }

    public void setAccepted_id(String accepted_id) {
        Accepted_id = accepted_id;
    }

    public String getIs_Locked() {
        return Is_Locked;
    }

    public void setIs_Locked(String is_Locked) {
        Is_Locked = is_Locked;
    }

    public String getLockunlock() {
        return lockunlock;
    }

    public void setLockunlock(String lockunlock) {
        this.lockunlock = lockunlock;
    }

    public ArrayList<StockistModel> getEntityStockistList() {
        return entityStockistList;
    }

    public void setEntityStockistList(ArrayList<StockistModel> entityStockistList) {
        this.entityStockistList = entityStockistList;
    }
}
