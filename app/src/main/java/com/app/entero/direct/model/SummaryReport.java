package com.app.entero.direct.model;

import java.util.List;

public class SummaryReport {

    private String ClientID;

    private String Client_Code;

    private String Client_Name;

    private int TotalAmount;

    private List<Orders> Orders;

    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String ClientID) {
        this.ClientID = ClientID;
    }

    public String getClientCode() {
        return Client_Code;
    }

    public void setClientCode(String Client_Code) {
        this.Client_Code = Client_Code;
    }

    public String getClientName() {
        return Client_Name;
    }

    public void setClientName(String Client_Name) {
        this.Client_Name = Client_Name;
    }

    public Integer getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(Integer TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public List<Orders> getOrders() {
        return Orders;
    }

    public void setOrders(List<Orders> Orders) {
        this.Orders = Orders;
    }
}
