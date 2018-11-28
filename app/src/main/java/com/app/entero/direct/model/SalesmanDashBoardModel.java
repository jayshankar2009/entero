package com.app.entero.direct.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SalesmanDashBoardModel implements Serializable{
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("entityChemListData")
    @Expose
    private List<SalesmanDashBoardModel> entityChemListData = null;
    @SerializedName("entityCountSalesmanData")
    @Expose
    private List<SalesmanDashBoardModel> entityCountSalesmanData = null;
    @SerializedName("assignedDeliveryCount")
    @Expose
    private String assignedDeliveryCount;
    @SerializedName("assignedcustomerCount")
    @Expose
    private String assignedcustomerCount;
    @SerializedName("totalPaymentCollection")
    @Expose
    private String totalPaymentCollection;

    public String getOutStanding() {
        return outStanding;
    }

    public void setOutStanding(String outStanding) {
        this.outStanding = outStanding;
    }

    public String getTotalAmnt() {
        return totalAmnt;
    }

    public void setTotalAmnt(String totalAmnt) {
        this.totalAmnt = totalAmnt;
    }

    public String getEcid() {
        return ecid;
    }

    public void setEcid(String ecid) {
        this.ecid = ecid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @SerializedName("OutstandingAmt")
    @Expose
    private String outStanding;

    @SerializedName("TotalAmt")
    @Expose
    private String totalAmnt;
    @SerializedName("ECID")
    @Expose
    private String ecid;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("StokistID")
    @Expose
    private String stokistID;
    @SerializedName("RouteID")
    @Expose
    private String routeID;
    @SerializedName("UserID")
    @Expose
    private String userID;
    @SerializedName("Chemist_ID")
    @Expose
    private String chemistID;
    @SerializedName("Chemist_Legal_Name")
    @Expose
    private String chemistLegalName;

    public String getChemistAddress() {
        return chemistAddress;
    }

    public void setChemistAddress(String chemistAddress) {
        this.chemistAddress = chemistAddress;
    }

    public String getChemistCity() {
        return chemistCity;
    }

    public void setChemistCity(String chemistCity) {
        this.chemistCity = chemistCity;
    }

    public String getChemistErpCode() {
        return chemistErpCode;
    }

    public void setChemistErpCode(String chemistErpCode) {
        this.chemistErpCode = chemistErpCode;
    }

    @SerializedName("Address")
    @Expose
    private String chemistAddress;
    @SerializedName("City")
    @Expose
    private String chemistCity;
    @SerializedName("ChemistERPCode")
    @Expose
    private String chemistErpCode;

    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;

    public String getStokistID() {
        return stokistID;
    }

    public void setStokistID(String stokistID) {
        this.stokistID = stokistID;
    }

    public String getRouteID() {
        return routeID;
    }

    public void setRouteID(String routeID) {
        this.routeID = routeID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getChemistID() {
        return chemistID;
    }

    public void setChemistID(String chemistID) {
        this.chemistID = chemistID;
    }

    public String getChemistLegalName() {
        return chemistLegalName;
    }

    public void setChemistLegalName(String chemistLegalName) {
        this.chemistLegalName = chemistLegalName;
    }

    public String getAssignedDeliveryCount() {
        return assignedDeliveryCount;
    }

    public void setAssignedDeliveryCount(String assignedDeliveryCount) {
        this.assignedDeliveryCount = assignedDeliveryCount;
    }

    public Object getAssignedcustomerCount() {
        return assignedcustomerCount;
    }

    public void setAssignedcustomerCount(String assignedcustomerCount) {
        this.assignedcustomerCount = assignedcustomerCount;
    }

    public String getTotalPaymentCollection() {
        return totalPaymentCollection;
    }

    public void setTotalPaymentCollection(String totalPaymentCollection) {
        this.totalPaymentCollection = totalPaymentCollection;
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

    public List<SalesmanDashBoardModel> getEntityChemListData() {
        return entityChemListData;
    }

    public void setEntityChemListData(List<SalesmanDashBoardModel> entityChemListData) {
        this.entityChemListData = entityChemListData;
    }

    public List<SalesmanDashBoardModel> getEntityCountSalesmanData() {
        return entityCountSalesmanData;
    }

    public void setEntityCountSalesmanData(List<SalesmanDashBoardModel> entityCountSalesmanData) {
        this.entityCountSalesmanData = entityCountSalesmanData;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}

