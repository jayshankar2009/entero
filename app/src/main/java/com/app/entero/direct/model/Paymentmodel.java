package com.app.entero.direct.model;

/**
 * Created by admin on 10/30/2018.
 */

public class Paymentmodel {

    private String Billid;
    private String Billdate;
    private String Pendingdays;
    private String Billitems;
    private String GrandTotal;
    private String discount;
    private String finalamount;
    private String status;
    private String BalAmount;
    private boolean isChecked;


    public Paymentmodel(String Billid, String Billdate, String Pendingdays, String Billitems, String GrandTotal, String discount, String finalamount, String status, String BalAmount){

        this.Billid = Billid;
        this.Billdate = Billdate;
        this.Pendingdays = Pendingdays;
        this.Billitems = Billitems;
        this.GrandTotal = GrandTotal;
        this.discount = discount;
        this.finalamount = finalamount;
        this.status = status;
        this.BalAmount = BalAmount;


    }


    public String getBillid() {
        return Billid;
    }

    public void setBillid(String billid) {
        Billid = billid;
    }

    public String getBilldate() {
        return Billdate;
    }

    public void setBilldate(String billdate) {
        Billdate = billdate;
    }

    public String getPendingdays() {
        return Pendingdays;
    }

    public void setPendingdays(String pendingdays) {
        Pendingdays = pendingdays;
    }

    public String getBillitems() {
        return Billitems;
    }

    public void setBillitems(String billitems) {
        Billitems = billitems;
    }

    public String getGrandTotal() {
        return GrandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        GrandTotal = grandTotal;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getFinalamount() {
        return finalamount;
    }

    public void setFinalamount(String finalamount) {
        this.finalamount = finalamount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBalAmount() {
        return BalAmount;
    }

    public void setBalAmount(String balAmount) {
        BalAmount = balAmount;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }



   /* private String id;
    private String name;
    private String address;

    public Paymentmodel(String idd, String name, String address){

        id = idd;
        this.name = name;
        this.address = address;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }*/
}
