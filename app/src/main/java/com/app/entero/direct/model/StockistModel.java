
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
