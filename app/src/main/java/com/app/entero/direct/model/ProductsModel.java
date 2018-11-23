package com.app.entero.direct.model;

public class ProductsModel {
    public ProductsModel(String strPrdct, String strQty) {
        this.strImg = strImg;
        this.strPrdct = strPrdct;
        this.strQty = strQty;
        this.strCircle = strCircle;
    }

    public String getStrImg() {
        return strImg;
    }

    public void setStrImg(String strImg) {
        this.strImg = strImg;
    }

    public String getStrPrdct() {
        return strPrdct;
    }

    public void setStrPrdct(String strPrdct) {
        this.strPrdct = strPrdct;
    }

    public String getStrQty() {
        return strQty;
    }

    public void setStrQty(String strQty) {
        this.strQty = strQty;
    }

    public String getStrCircle() {
        return strCircle;
    }

    public void setStrCircle(String strCircle) {
        this.strCircle = strCircle;
    }

    String strImg,strPrdct,strQty,strCircle;
}
