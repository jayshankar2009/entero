package com.app.entero.direct.model;

public class Sales {

    String id_;
    String customer_sales_qty;
    String sales_return_qty;
    String customer_sales_amount;
    String sales_return_amount;
    String toral_amount;

    public Sales(String id_, String customer_sales_qty, String sales_return_qty,
                 String customer_sales_amount, String sales_return_amount, String toral_amount) {
        this.id_ = id_;
        this.customer_sales_qty = customer_sales_qty;
        this.sales_return_qty = sales_return_qty;
        this.customer_sales_amount = customer_sales_amount;
        this.sales_return_amount = sales_return_amount;
        this.toral_amount = toral_amount;

    }

    public String getId_() {
        return id_;
    }

    public void setId_(String id_) {
        this.id_ = id_;
    }

    public String getCustomer_sales_qty() {
        return customer_sales_qty;
    }

    public void setCustomer_sales_qty(String customer_sales_qty) {
        this.customer_sales_qty = customer_sales_qty;
    }

    public String getSales_return_qty() {
        return sales_return_qty;
    }

    public void setSales_return_qty(String sales_return_qty) {
        this.sales_return_qty = sales_return_qty;
    }

    public String getCustomer_sales_amount() {
        return customer_sales_amount;
    }

    public void setCustomer_sales_amount(String customer_sales_amount) {
        this.customer_sales_amount = customer_sales_amount;
    }

    public String getSales_return_amount() {
        return sales_return_amount;
    }

    public void setSales_return_amount(String sales_return_amount) {
        this.sales_return_amount = sales_return_amount;
    }

    public String getToral_amount() {
        return toral_amount;
    }

    public void setToral_amount(String toral_amount) {
        this.toral_amount = toral_amount;
    }
}
