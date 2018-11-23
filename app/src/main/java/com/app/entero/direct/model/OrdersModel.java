package com.app.entero.direct.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrdersModel implements Serializable {

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

    @SerializedName("orderDateTime")
    @Expose
    private String orderDateTime;

    @SerializedName("invoiceDateTime")
    @Expose
    private String invoiceDateTime;

    @SerializedName("orderAmount")
    @Expose
    private String orderAmount;

    @SerializedName("invoicId")
    @Expose
    private String invoicId;

    @SerializedName("invoiceDate")
    @Expose
    private String invoiceDate;

    @SerializedName("invoiceAmount")
    @Expose
    private String invoiceAmount;

    public String getInvoiceDateTime() {
        return invoiceDateTime;
    }

    public void setInvoiceDateTime(String invoiceDateTime) {
        this.invoiceDateTime = invoiceDateTime;
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

    public String getChemistName() {
        return chemistName;
    }

    public void setChemistName(String chemistName) {
        this.chemistName = chemistName;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getInvoicId() {
        return invoicId;
    }

    public void setInvoicId(String invoicId) {
        this.invoicId = invoicId;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }
}
