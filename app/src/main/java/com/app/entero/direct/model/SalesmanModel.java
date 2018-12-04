
package com.app.entero.direct.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SalesmanModel implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("OTP")
    @Expose
    private String OTP;


    @SerializedName("salesmanInfo")
    @Expose

    private ArrayList<SalesmanModel> salesmanInfo;


    @SerializedName("SalesmanID")
    @Expose
    private String iD;
    @SerializedName("StockistID")
    @Expose
    private String stockistID;
    @SerializedName("StockistName")
    @Expose
    private String stockistName;
    @SerializedName("ERPSalesmanID")
    @Expose
    private String eRPSalesmanID;
    @SerializedName("IMEI")
    @Expose
    private String iMEI;
    @SerializedName("MobileNO")
    @Expose
    private String mobileNO;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("Role_ID")
    @Expose
    private String roleID;
    @SerializedName("Role_Name")
    @Expose
    private String roleName;
    @SerializedName("ProfileImage")
    @Expose
    private String profileImage;
    @SerializedName("legendType")
    @Expose
    private String legendType;

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getStockistID() {
        return stockistID;
    }

    public void setStockistID(String stockistID) {
        this.stockistID = stockistID;
    }

    public String getStockistName() {
        return stockistName;
    }

    public void setStockistName(String stockistName) {
        this.stockistName = stockistName;
    }

    public String getERPSalesmanID() {
        return eRPSalesmanID;
    }

    public void setERPSalesmanID(String eRPSalesmanID) {
        this.eRPSalesmanID = eRPSalesmanID;
    }

    public String getIMEI() {
        return iMEI;
    }

    public void setIMEI(String iMEI) {
        this.iMEI = iMEI;
    }

    public String getMobileNO() {
        return mobileNO;
    }

    public void setMobileNO(String mobileNO) {
        this.mobileNO = mobileNO;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getLegendType() {
        return legendType;
    }

    public void setLegendType(String legendType) {
        this.legendType = legendType;
    }



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


    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String geteRPSalesmanID() {
        return eRPSalesmanID;
    }

    public void seteRPSalesmanID(String eRPSalesmanID) {
        this.eRPSalesmanID = eRPSalesmanID;
    }

    public String getiMEI() {
        return iMEI;
    }

    public void setiMEI(String iMEI) {
        this.iMEI = iMEI;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
