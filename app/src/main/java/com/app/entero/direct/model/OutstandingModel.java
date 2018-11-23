package com.app.entero.direct.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OutstandingModel implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("msg")
    @Expose
    private String msg;

    @SerializedName("chemistName")
    @Expose
    private String chemistName;

    @SerializedName("billID")
    @Expose
    private String billID;

    @SerializedName("billDate")
    @Expose
    private String billDate;

    @SerializedName("pendingDays")
    @Expose
    private String pendingDays;

    @SerializedName("billItems")
    @Expose
    private String billItems;

    @SerializedName("grandTotal")
    @Expose
    private String grandTotal;

    @SerializedName("discount")
    @Expose
    private String discount;

    @SerializedName("finalAmount")
    @Expose
    private String finalAmount;

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

    public String getChemistName() {
        return chemistName;
    }

    public void setChemistName(String chemistName) {
        this.chemistName = chemistName;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getPendingDays() {
        return pendingDays;
    }

    public void setPendingDays(String pendingDays) {
        this.pendingDays = pendingDays;
    }

    public String getBillItems() {
        return billItems;
    }

    public void setBillItems(String billItems) {
        this.billItems = billItems;
    }

    public String getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(String finalAmount) {
        this.finalAmount = finalAmount;
    }
}
