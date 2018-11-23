
package com.app.entero.direct.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SalesmanModel implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("salesmanInfo")
    @Expose
    private ArrayList<SalesmanModel> salesmanInfo;


    @SerializedName("_ID")
    @Expose
    private String salemanId;



    @SerializedName("ClientID")
    @Expose
    private String ClientID;



    @SerializedName("ERPSalesmanID")
    @Expose
    private String ERPSalesmanID;


    @SerializedName("IMEI")
    @Expose
    private String IMEI;



    @SerializedName("MobileNO")
    @Expose
    private String MobileNO;



    @SerializedName("Email")
    @Expose
    private String Email;



    @SerializedName("FullName")
    @Expose
    private String FullName;


    @SerializedName("Role_ID")
    @Expose
    private String Role_ID;

    @SerializedName("Role_Name")
    @Expose
    private String Role_Name;


    @SerializedName("ProfileImage")
    @Expose
    private String ProfileImage;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<SalesmanModel> getSalesmanInfo() {
        return salesmanInfo;
    }

    public void setSalesmanInfo(ArrayList<SalesmanModel> salesmanInfo) {
        this.salesmanInfo = salesmanInfo;
    }

    public String getSalemanId() {
        return salemanId;
    }

    public void setSalemanId(String salemanId) {
        this.salemanId = salemanId;
    }

    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public String getERPSalesmanID() {
        return ERPSalesmanID;
    }

    public void setERPSalesmanID(String ERPSalesmanID) {
        this.ERPSalesmanID = ERPSalesmanID;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getMobileNO() {
        return MobileNO;
    }

    public void setMobileNO(String mobileNO) {
        MobileNO = mobileNO;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getRole_ID() {
        return Role_ID;
    }

    public void setRole_ID(String role_ID) {
        Role_ID = role_ID;
    }

    public String getRole_Name() {
        return Role_Name;
    }

    public void setRole_Name(String role_Name) {
        Role_Name = role_Name;
    }

    public String getProfileImage() {
        return ProfileImage;
    }

    public void setProfileImage(String profileImage) {
        ProfileImage = profileImage;
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
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }
}
