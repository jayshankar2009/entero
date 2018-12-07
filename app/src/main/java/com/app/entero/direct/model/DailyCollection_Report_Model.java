package com.app.entero.direct.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyCollection_Report_Model {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private List<DailyCollection_Report_Model> result = null;


   /* @SerializedName("PaymentDate")
    @Expose
    private String paymentDate;*/

    @SerializedName("PaymentDetails")
    @Expose
    private List<DailyCollection_Report_Model> paymentDetails = null;

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public List<DailyCollection_Report_Model> getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(List<DailyCollection_Report_Model> paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
    @SerializedName("CreatedBy")
    @Expose
    private Integer createdBy;
    @SerializedName("StockiestID")
    @Expose
    private Integer stockiestID;
    @SerializedName("ChemistID")
    @Expose
    private Integer chemistID;
    @SerializedName("Chemist_Legal_Name")
    @Expose
    private String chemistLegalName;
    @SerializedName("PaymentDate")
    @Expose
    private String paymentDate;
    @SerializedName("PaymentTime")
    @Expose
    private String paymentTime;
    @SerializedName("Amount")
    @Expose
    private Integer amount;
    @SerializedName("PaymentMode")
    @Expose
    private String paymentMode;
    @SerializedName("Bank")
    @Expose
    private String bank;
    @SerializedName("ChequeNo")
    @Expose
    private String chequeNo;
    @SerializedName("ChequeAmt")
    @Expose
    private Integer chequeAmt;
    @SerializedName("ChequeDate")
    @Expose
    private String chequeDate;
    @SerializedName("MicrCode")
    @Expose
    private String micrCode;
    @SerializedName("Narration")
    @Expose
    private String narration;
    @SerializedName("Comments")
    @Expose
    private String comments;
   /* @SerializedName("Status")
    @Expose
    private Object status;*/
    @SerializedName("Role_ID")
    @Expose
    private Integer roleID;

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getStockiestID() {
        return stockiestID;
    }

    public void setStockiestID(Integer stockiestID) {
        this.stockiestID = stockiestID;
    }

    public Integer getChemistID() {
        return chemistID;
    }

    public void setChemistID(Integer chemistID) {
        this.chemistID = chemistID;
    }

    public String getChemistLegalName() {
        return chemistLegalName;
    }

    public void setChemistLegalName(String chemistLegalName) {
        this.chemistLegalName = chemistLegalName;
    }

    /*public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }*/

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public Integer getChequeAmt() {
        return chequeAmt;
    }

    public void setChequeAmt(Integer chequeAmt) {
        this.chequeAmt = chequeAmt;
    }

    public String getChequeDate() {
        return chequeDate;
    }

    public void setChequeDate(String chequeDate) {
        this.chequeDate = chequeDate;
    }

    public String getMicrCode() {
        return micrCode;
    }

    public void setMicrCode(String micrCode) {
        this.micrCode = micrCode;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

   /* public String getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }*/

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
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

    public List<DailyCollection_Report_Model> getResult() {
        return result;
    }

    public void setResult(List<DailyCollection_Report_Model> result) {
        this.result = result;
    }
}
