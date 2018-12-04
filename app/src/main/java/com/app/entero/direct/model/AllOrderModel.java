package com.app.entero.direct.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AllOrderModel implements Serializable{
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("OrderData")
    @Expose
    private List<AllOrderModel> orderData = null;
    @SerializedName("Transaction_No")
    @Expose
    private String transactionNo;
    @SerializedName("ClientID")
    @Expose
    private String clientID;
    @SerializedName("Doc_Date")
    @Expose
    private String docDate;
    @SerializedName("Comments")
    @Expose
    private String comments;
    @SerializedName("Items")
    @Expose
    private String items;
    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("Status")
    @Expose
    private String Status1;

    @SerializedName("IsDownloaded")
    @Expose
    private String isDownloaded;
    @SerializedName("RefOrderID")
    @Expose
    private String refOrderID;
    @SerializedName("ERP_Code")
    @Expose
    private String eRPCode;
    @SerializedName("Chemist_Legal_Name")
    @Expose
    private String chemistLegalName;
    @SerializedName("status_title")
    @Expose
    private String statusTitle;

    @SerializedName("color_code")
    @Expose
    private String colorCode;



    public String getStatusTitle() {
        return statusTitle;
    }

    public void setStatusTitle(String statusTitle) {
        this.statusTitle = statusTitle;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }


    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    public String getIsDownloaded() {
        return isDownloaded;
    }

    public void setIsDownloaded(String isDownloaded) {
        this.isDownloaded = isDownloaded;
    }

    public String getRefOrderID() {
        return refOrderID;
    }

    public void setRefOrderID(String refOrderID) {
        this.refOrderID = refOrderID;
    }

    public String getERPCode() {
        return eRPCode;
    }

    public void setERPCode(String eRPCode) {
        this.eRPCode = eRPCode;
    }

    public String getChemistLegalName() {
        return chemistLegalName;
    }

    public void setChemistLegalName(String chemistLegalName) {
        this.chemistLegalName = chemistLegalName;
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

    public List<AllOrderModel> getOrderData() {
        return orderData;
    }

    public void setOrderData(List<AllOrderModel> orderData) {
        this.orderData = orderData;
    }

    public String getStatus1() {
        return Status1;
    }

    public void setStatus1(String status1) {
        Status1 = status1;
    }
}
