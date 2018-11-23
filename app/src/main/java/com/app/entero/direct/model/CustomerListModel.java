package com.app.entero.direct.model;

public class CustomerListModel {
    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public CustomerListModel(String invoiceNo, String product, String amount) {
        this.invoiceNo = invoiceNo;
        this.product = product;
        this.amount = amount;
    }

    String invoiceNo,product,amount;
}
