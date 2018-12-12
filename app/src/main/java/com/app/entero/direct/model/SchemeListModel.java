
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


    @SerializedName("ClientID")
    @Expose
    private String ClientID;


    @SerializedName("Client_LegalName")
    @Expose
    private String Client_LegalName;


    @SerializedName("Client_Address")
    @Expose
    private String Client_Address;

    @SerializedName("StateID")
    @Expose
    private String StateID;


    @SerializedName("CityID")
    @Expose
    private String CityID;


    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("PinCode")
    @Expose
    private String PinCode;


    @SerializedName("URL")
    @Expose
    private String URL;


    @SerializedName("MobileNo")
    @Expose
    private String MobileNo;


    @SerializedName("Client_Email")
    @Expose
    private String Client_Email;


    @SerializedName("ClientTypeID")
    @Expose
    private String ClientTypeID;

    @SerializedName("Accepted_id")
    @Expose
    private String Accepted_id;


    @SerializedName("Is_Locked")
    @Expose
    private String Is_Locked;


    @SerializedName("lockunlock")
    @Expose
    private String lockunlock;

    @SerializedName("Product_Count")
    @Expose
    private String Product_Count;

    @SerializedName("Outstanding")
    @Expose
    private String Outstanding;

    @SerializedName("ProductID")
    @Expose
    private String ProductID;

    @SerializedName("Product_Code")
    @Expose
    private String Product_Code;

    @SerializedName("UOM")
    @Expose
    private String UOM;

    @SerializedName("Manufacturer")
    @Expose
    private String Manufacturer;

    @SerializedName("MRP")
    @Expose
    private String MRP;

    @SerializedName("PTS")
    @Expose
    private String PTS;

    @SerializedName("Rate")
    @Expose
    private String Rate;

    @SerializedName("Narco_Product_limit")
    @Expose
    private String Narco_Product_limit;

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProduct_Code() {
        return Product_Code;
    }

    public void setProduct_Code(String product_Code) {
        Product_Code = product_Code;
    }

    public String getUOM() {
        return UOM;
    }

    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getMRP() {
        return MRP;
    }

    public void setMRP(String MRP) {
        this.MRP = MRP;
    }

    public String getPTS() {
        return PTS;
    }

    public void setPTS(String PTS) {
        this.PTS = PTS;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getNarco_Product_limit() {
        return Narco_Product_limit;
    }

    public void setNarco_Product_limit(String narco_Product_limit) {
        Narco_Product_limit = narco_Product_limit;
    }

    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public String getClient_LegalName() {
        return Client_LegalName;
    }

    public void setClient_LegalName(String client_LegalName) {
        Client_LegalName = client_LegalName;
    }

    public String getClient_Address() {
        return Client_Address;
    }

    public void setClient_Address(String client_Address) {
        Client_Address = client_Address;
    }

    public String getStateID() {
        return StateID;
    }

    public void setStateID(String stateID) {
        StateID = stateID;
    }

    public String getCityID() {
        return CityID;
    }

    public void setCityID(String cityID) {
        CityID = cityID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPinCode() {
        return PinCode;
    }

    public void setPinCode(String pinCode) {
        PinCode = pinCode;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getClient_Email() {
        return Client_Email;
    }

    public void setClient_Email(String client_Email) {
        Client_Email = client_Email;
    }

    public String getClientTypeID() {
        return ClientTypeID;
    }

    public void setClientTypeID(String clientTypeID) {
        ClientTypeID = clientTypeID;
    }

    public String getAccepted_id() {
        return Accepted_id;
    }

    public void setAccepted_id(String accepted_id) {
        Accepted_id = accepted_id;
    }

    public String getIs_Locked() {
        return Is_Locked;
    }

    public void setIs_Locked(String is_Locked) {
        Is_Locked = is_Locked;
    }

    public String getLockunlock() {
        return lockunlock;
    }

    public void setLockunlock(String lockunlock) {
        this.lockunlock = lockunlock;
    }

    public String getProduct_Count() {
        return Product_Count;
    }

    public void setProduct_Count(String product_Count) {
        Product_Count = product_Count;
    }

    public String getOutstanding() {
        return Outstanding;
    }

    public void setOutstanding(String outstanding) {
        Outstanding = outstanding;
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
