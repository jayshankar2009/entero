package com.app.entero.direct.model;

public class Salesman_Product_Search_Model {
    public Salesman_Product_Search_Model(String productName, String mnfctName, String pckSize, String ptr, String mrp) {
        this.productName = productName;
        this.mnfctName = mnfctName;
        this.pckSize = pckSize;
        this.ptr = ptr;
        this.mrp = mrp;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMnfctName() {
        return mnfctName;
    }

    public void setMnfctName(String mnfctName) {
        this.mnfctName = mnfctName;
    }

    public String getPckSize() {
        return pckSize;
    }

    public void setPckSize(String pckSize) {
        this.pckSize = pckSize;
    }

    public String getPtr() {
        return ptr;
    }

    public void setPtr(String ptr) {
        this.ptr = ptr;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    String productName,mnfctName,pckSize,ptr,mrp;
}
