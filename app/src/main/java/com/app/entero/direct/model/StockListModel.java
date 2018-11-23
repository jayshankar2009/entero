
package com.app.entero.direct.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StockListModel implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("stockListId")
    @Expose
    private String stockListId;

    @SerializedName("img")
    @Expose
    private String img;

    @SerializedName("stockListName")
    @Expose
    private String stockListName;

    @SerializedName("stockListDes")
    @Expose
    private String stockListDes;

    @SerializedName("stockListquantitly")
    @Expose
    private String stockListquantitly;

    @SerializedName("stockListaddress")
    @Expose
    private String stockListAddress;

    @SerializedName("stockListprice")
    @Expose
    private String stockListPrice;

    public String getStockListAddress() {
        return stockListAddress;
    }

    public void setStockListAddress(String stockListAddress) {
        this.stockListAddress = stockListAddress;
    }

    public String getStockListPrice() {
        return stockListPrice;
    }

    public void setStockListPrice(String stockListPrice) {
        this.stockListPrice = stockListPrice;
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

    public String getStockListId() {
        return stockListId;
    }

    public void setStockListId(String stockListId) {
        this.stockListId = stockListId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStockListName() {
        return stockListName;
    }

    public void setStockListName(String stockListName) {
        this.stockListName = stockListName;
    }

    public String getStockListDes() {
        return stockListDes;
    }

    public void setStockListDes(String stockListDes) {
        this.stockListDes = stockListDes;
    }

    public String getStockListquantitly() {
        return stockListquantitly;
    }

    public void setStockListquantitly(String stockListquantitly) {
        this.stockListquantitly = stockListquantitly;
    }
}
