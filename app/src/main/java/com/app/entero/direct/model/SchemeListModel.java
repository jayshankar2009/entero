
package com.app.entero.direct.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SchemeListModel implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("schemeInfo")
    @Expose
    private ArrayList<SchemeListModel> schemeList;

    @SerializedName("Product_ID")
    @Expose
    private String Product_ID;

    @SerializedName("StockistID")
    @Expose
    private String StockistID;

    @SerializedName("StcokistName")
    @Expose
    private String StcokistName;

    @SerializedName("ProdcutName")
    @Expose
    private String ProdcutName;

    @SerializedName("StartDate")
    @Expose
    private String StartDate;

    @SerializedName("EndDate")
    @Expose
    private String EndDate;

    @SerializedName("Stock")
    @Expose
    private String Stock;

    @SerializedName("MfgCode")
    @Expose
    private String MfgCode;

    @SerializedName("MfgName")
    @Expose
    private String MfgName;

    @SerializedName("Image_path")
    @Expose
    private String Image_path;

    @SerializedName("Packsize")
    @Expose
    private String Packsize;

    @SerializedName("Scheme")
    @Expose
    private String Scheme;


    @SerializedName("PercentScheme")
    @Expose
    private String PercentScheme;

    @SerializedName("LegendMode")
    @Expose
    private String LegendMode;

    @SerializedName("ColorCode")
    @Expose
    private String ColorCode;

    @SerializedName("HalfScheme")
    @Expose
    private String HalfScheme;

    @SerializedName("MinQty")
    @Expose
    private String MinQty;

    @SerializedName("MaxQty")
    @Expose
    private String MaxQty;

    @SerializedName("BoxSize")
    @Expose
    private String BoxSize;


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

    public ArrayList<SchemeListModel> getschemeList() {
        return schemeList;
    }

    public void setschemeList(ArrayList<SchemeListModel> schemeList) {
        this.schemeList = schemeList;
    }

    public String getProduct_ID() {
        return Product_ID;
    }

    public void setProduct_ID(String product_ID) {
        Product_ID = product_ID;
    }

    public ArrayList<SchemeListModel> getSchemeList() {
        return schemeList;
    }

    public void setSchemeList(ArrayList<SchemeListModel> schemeList) {
        this.schemeList = schemeList;
    }

    public String getStockistID() {
        return StockistID;
    }

    public void setStockistID(String stockistID) {
        StockistID = stockistID;
    }

    public String getStock() {
        return Stock;
    }

    public void setStock(String stock) {
        Stock = stock;
    }

    public String getMfgCode() {
        return MfgCode;
    }

    public void setMfgCode(String mfgCode) {
        MfgCode = mfgCode;
    }

    public String getMfgName() {
        return MfgName;
    }

    public void setMfgName(String mfgName) {
        MfgName = mfgName;
    }

    public String getImage_path() {
        return Image_path;
    }

    public void setImage_path(String image_path) {
        Image_path = image_path;
    }

    public String getPacksize() {
        return Packsize;
    }

    public void setPacksize(String packsize) {
        Packsize = packsize;
    }

    public String getScheme() {
        return Scheme;
    }

    public void setScheme(String scheme) {
        Scheme = scheme;
    }

    public String getPercentScheme() {
        return PercentScheme;
    }

    public void setPercentScheme(String percentScheme) {
        PercentScheme = percentScheme;
    }

    public String getLegendMode() {
        return LegendMode;
    }

    public void setLegendMode(String legendMode) {
        LegendMode = legendMode;
    }

    public String getColorCode() {
        return ColorCode;
    }

    public void setColorCode(String colorCode) {
        ColorCode = colorCode;
    }

    public String getHalfScheme() {
        return HalfScheme;
    }

    public void setHalfScheme(String halfScheme) {
        HalfScheme = halfScheme;
    }

    public String getMinQty() {
        return MinQty;
    }

    public void setMinQty(String minQty) {
        MinQty = minQty;
    }

    public String getMaxQty() {
        return MaxQty;
    }

    public void setMaxQty(String maxQty) {
        MaxQty = maxQty;
    }

    public String getBoxSize() {
        return BoxSize;
    }

    public void setBoxSize(String boxSize) {
        BoxSize = boxSize;
    }

    public String getStcokistName() {
        return StcokistName;
    }

    public void setStcokistName(String stcokistName) {
        StcokistName = stcokistName;
    }

    public String getProdcutName() {
        return ProdcutName;
    }

    public void setProdcutName(String prodcutName) {
        ProdcutName = prodcutName;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }
}
