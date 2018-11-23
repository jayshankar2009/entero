
package com.app.entero.direct.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OffersModel implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("msg")
    @Expose
    private String msg;

    @SerializedName("img")
    @Expose
    private String img;

    @SerializedName("tabName")
    @Expose
    private String tabName;

    @SerializedName("tabDes")
    @Expose
    private String tabDes;

    @SerializedName("quantitly")
    @Expose
    private String quantitly;

    @SerializedName("distributerName")
    @Expose
    private String distributerName;

    public String getDistributerName() {
        return distributerName;
    }

    public void setDistributerName(String distributerName) {
        this.distributerName = distributerName;
    }



    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getTabDes() {
        return tabDes;
    }

    public void setTabDes(String tabDes) {
        this.tabDes = tabDes;
    }

    public String getQuantitly() {
        return quantitly;
    }

    public void setQuantitly(String quantitly) {
        this.quantitly = quantitly;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
