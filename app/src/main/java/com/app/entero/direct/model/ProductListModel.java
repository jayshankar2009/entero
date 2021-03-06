
package com.app.entero.direct.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductListModel implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("productList")
    @Expose
    private ArrayList<ProductListModel> productList;

    @SerializedName("Product_ID")
    @Expose
    private String Product_ID;


    @SerializedName("Itemcode")
    @Expose
    private String Itemcode;

    @SerializedName("Itemname")
    @Expose
    private String Itemname;

    @SerializedName("Mrp")
    @Expose
    private String Mrp;

    @SerializedName("Rate")
    @Expose
    private String Rate;

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

    @SerializedName("Stk_id")
    @Expose
    private String Stk_id;

    public String getStk_id() {
        return Stk_id;
    }

    public void setStk_id(String stk_id) {
        Stk_id = stk_id;
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

    public ArrayList<ProductListModel> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<ProductListModel> productList) {
        this.productList = productList;
    }

    public String getProduct_ID() {
        return Product_ID;
    }

    public void setProduct_ID(String product_ID) {
        Product_ID = product_ID;
    }

    public String getItemcode() {
        return Itemcode;
    }

    public void setItemcode(String itemcode) {
        Itemcode = itemcode;
    }

    public String getItemname() {
        return Itemname;
    }

    public void setItemname(String itemname) {
        Itemname = itemname;
    }

    public String getMrp() {
        return Mrp;
    }

    public void setMrp(String mrp) {
        Mrp = mrp;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
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
}
