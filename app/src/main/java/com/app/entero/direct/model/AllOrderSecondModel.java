package com.app.entero.direct.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllOrderSecondModel {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("OrderData")
    @Expose
    private List<AllOrderSecondModel> orderData = null;

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

    public List<AllOrderSecondModel> getOrderData() {
        return orderData;
    }

    public void setOrderData(List<AllOrderSecondModel> orderData) {
        this.orderData = orderData;
    }

    @SerializedName("OrderNo")
    @Expose
    private String orderNo;
    @SerializedName("Product_ID")
    @Expose
    private String productID;
    @SerializedName("ItemNO")
    @Expose
    private String itemNO;
    @SerializedName("Product_Desc")
    @Expose
    private String productDesc;
    @SerializedName("Qty")
    @Expose
    private String qty;
    @SerializedName("Rate")
    @Expose
    private String rate;
    @SerializedName("Price")
    @Expose
    private String price;
    @SerializedName("Scheme")
    @Expose
    private String scheme;
    @SerializedName("Packsize")
    @Expose
    private String packsize;
    @SerializedName("Manufacturer_Name")
    @Expose
    private String manufacturerName;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getItemNO() {
        return itemNO;
    }

    public void setItemNO(String itemNO) {
        this.itemNO = itemNO;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Object getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getPacksize() {
        return packsize;
    }

    public void setPacksize(String packsize) {
        this.packsize = packsize;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

}
