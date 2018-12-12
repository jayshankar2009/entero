package com.app.entero.direct.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PDCModel {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private List<PDCModel> result = null;

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

    public List<PDCModel> getResult() {
        return result;
    }

    public void setResult(List<PDCModel> result) {
        this.result = result;
    }
    @SerializedName("CreatedBy")
    @Expose
    private Integer createdBy;
    @SerializedName("StockiestID")
    @Expose
    private Integer stockiestID;
    @SerializedName("ChemistID")
    @Expose
    private String chemistID;
    @SerializedName("Chemist_Legal_Name")
    @Expose
    private String chemistLegalName;
    @SerializedName("ERP_CODE")
    @Expose
    private String eRPCODE;
    @SerializedName("PDCDetails")
    @Expose
    private List<PDCModel> pDCDetails = null;

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

    public String getERPCODE() {
        return eRPCODE;
    }

    public void setERPCODE(String eRPCODE) {
        this.eRPCODE = eRPCODE;
    }

    public List<PDCModel> getPDCDetails() {
        return pDCDetails;
    }

    public void setPDCDetails(List<PDCModel> pDCDetails) {
        this.pDCDetails = pDCDetails;
    }

    @SerializedName("Doc_no")
    @Expose
    private String docNo;
    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("PaymentMode")
    @Expose
    private String paymentMode;
    @SerializedName("PaymentDate")
    @Expose
    private String paymentDate;
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



    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
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


}
