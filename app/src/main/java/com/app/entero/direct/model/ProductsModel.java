package com.app.entero.direct.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductsModel implements Serializable {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("productList")
    @Expose
    private List<ProductsModel> productList = null;

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

    public List<ProductsModel> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductsModel> productList) {
        this.productList = productList;
    }
    @SerializedName("Product_ID")
    @Expose
    private String productID;
    @SerializedName("Itemcode")
    @Expose
    private String itemcode;
    @SerializedName("Itemname")
    @Expose
    private String itemname;
    @SerializedName("Mrp")
    @Expose
    private String mrp;
    @SerializedName("Rate")
    @Expose
    private String rate;
    @SerializedName("Stock")
    @Expose
    private String stock;
    @SerializedName("MfgCode")
    @Expose
    private String mfgCode;
    @SerializedName("MfgName")
    @Expose
    private String mfgName;
    @SerializedName("Image_path")
    @Expose
    private String imagePath;
    @SerializedName("Packsize")
    @Expose
    private String packsize;
    @SerializedName("Scheme")
    @Expose
    private String scheme;
    @SerializedName("PercentScheme")
    @Expose
    private String percentScheme;
    @SerializedName("LegendMode")
    @Expose
    private Integer legendMode;
    @SerializedName("ColorCode")
    @Expose
    private String colorCode;
    @SerializedName("HalfScheme")
    @Expose
    private String halfScheme;
    @SerializedName("MinQty")
    @Expose
    private String minQty;
    @SerializedName("MaxQty")
    @Expose
    private String maxQty;
    @SerializedName("BoxSize")
    @Expose
    private Integer boxSize;

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getMfgCode() {
        return mfgCode;
    }

    public void setMfgCode(String mfgCode) {
        this.mfgCode = mfgCode;
    }

    public String getMfgName() {
        return mfgName;
    }

    public void setMfgName(String mfgName) {
        this.mfgName = mfgName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPacksize() {
        return packsize;
    }

    public void setPacksize(String packsize) {
        this.packsize = packsize;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getPercentScheme() {
        return percentScheme;
    }

    public void setPercentScheme(String percentScheme) {
        this.percentScheme = percentScheme;
    }

    public Integer getLegendMode() {
        return legendMode;
    }

    public void setLegendMode(Integer legendMode) {
        this.legendMode = legendMode;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getHalfScheme() {
        return halfScheme;
    }

    public void setHalfScheme(String halfScheme) {
        this.halfScheme = halfScheme;
    }

    public String getMinQty() {
        return minQty;
    }

    public void setMinQty(String minQty) {
        this.minQty = minQty;
    }

    public String getMaxQty() {
        return maxQty;
    }

    public void setMaxQty(String maxQty) {
        this.maxQty = maxQty;
    }

    public Integer getBoxSize() {
        return boxSize;
    }

    public void setBoxSize(Integer boxSize) {
        this.boxSize = boxSize;
    }
}
