package com.app.entero.direct.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Salesman_CustomerList_Model implements Serializable {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("entityChemistList")
    @Expose
    private List<Salesman_CustomerList_Model> entityChemistList = null;

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

    public List<Salesman_CustomerList_Model> getEntityChemistList() {
        return entityChemistList;
    }

    public void setEntityChemistList(List<Salesman_CustomerList_Model> entityChemistList) {
        this.entityChemistList = entityChemistList;
    }
    @SerializedName("outstandingBal")
    @Expose
    private String outstandingBal;
    @SerializedName("Chemist_ID")
    @Expose
    private String chemistID;
    @SerializedName("_ID")
    @Expose
    private String iD;
    @SerializedName("Stockist_ID")
    @Expose
    private String stockistID;
    @SerializedName("ERP_Code")
    @Expose
    private String eRPCode;
    @SerializedName("Chemist_Name")
    @Expose
    private String chemistName;
    @SerializedName("Chemist_Legal_Name")
    @Expose
    private String chemistLegalName;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("Latitude")
    @Expose
    private String latitude;
    @SerializedName("Longitude")
    @Expose
    private String longitude;
    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("DLNumber")
    @Expose
    private String dLNumber;
    @SerializedName("tin_vat_cst")
    @Expose
    private String tinVatCst;
    @SerializedName("Pincode")
    @Expose
    private String pincode;
    @SerializedName("Tel_No")
    @Expose
    private String telNo;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("RefERPCustomerID")
    @Expose
    private String refERPCustomerID;
    @SerializedName("DLExpiry")
    @Expose
    private String dLExpiry;
    @SerializedName("creditDays")
    @Expose
    private String creditDays;
    @SerializedName("creditLimit")
    @Expose
    private String creditLimit;
    @SerializedName("allowCredit")
    @Expose
    private String allowCredit;
    @SerializedName("GSTNumber")
    @Expose
    private String gSTNumber;
    @SerializedName("isBlocked")
    @Expose
    private String isBlocked;
    @SerializedName("Block_Reason")
    @Expose
    private String blockReason;
    @SerializedName("DataMode")
    @Expose
    private String dataMode;
    @SerializedName("CreatedOn")
    @Expose
    private String createdOn;

    public String getOutstandingBal() {
        return outstandingBal;
    }

    public void setOutstandingBal(String outstandingBal) {
        this.outstandingBal = outstandingBal;
    }

    public String getChemistID() {
        return chemistID;
    }

    public void setChemistID(String chemistID) {
        this.chemistID = chemistID;
    }

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

    public String getERPCode() {
        return eRPCode;
    }

    public void setERPCode(String eRPCode) {
        this.eRPCode = eRPCode;
    }

    public String getChemistName() {
        return chemistName;
    }

    public void setChemistName(String chemistName) {
        this.chemistName = chemistName;
    }

    public String getChemistLegalName() {
        return chemistLegalName;
    }

    public void setChemistLegalName(String chemistLegalName) {
        this.chemistLegalName = chemistLegalName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getDLNumber() {
        return dLNumber;
    }

    public void setDLNumber(String dLNumber) {
        this.dLNumber = dLNumber;
    }

    public String getTinVatCst() {
        return tinVatCst;
    }

    public void setTinVatCst(String tinVatCst) {
        this.tinVatCst = tinVatCst;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getRefERPCustomerID() {
        return refERPCustomerID;
    }

    public void setRefERPCustomerID(String refERPCustomerID) {
        this.refERPCustomerID = refERPCustomerID;
    }

    public String getDLExpiry() {
        return dLExpiry;
    }

    public void setDLExpiry(String dLExpiry) {
        this.dLExpiry = dLExpiry;
    }

    public String getCreditDays() {
        return creditDays;
    }

    public void setCreditDays(String creditDays) {
        this.creditDays = creditDays;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getAllowCredit() {
        return allowCredit;
    }

    public void setAllowCredit(String allowCredit) {
        this.allowCredit = allowCredit;
    }

    public String getGSTNumber() {
        return gSTNumber;
    }

    public void setGSTNumber(String gSTNumber) {
        this.gSTNumber = gSTNumber;
    }

    public String getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(String isBlocked) {
        this.isBlocked = isBlocked;
    }

    public String getBlockReason() {
        return blockReason;
    }

    public void setBlockReason(String blockReason) {
        this.blockReason = blockReason;
    }

    public String getDataMode() {
        return dataMode;
    }

    public void setDataMode(String dataMode) {
        this.dataMode = dataMode;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }



}
