package com.app.entero.direct.model;

public class DeliveryModel {
    public DeliveryModel(String strInvoiceNo, String items, String packets, String boxes) {
        this.strInvoiceNo = strInvoiceNo;
        this.items = items;
        this.packets = packets;
        this.boxes = boxes;
    }

    public String getStrInvoiceNo() {
        return strInvoiceNo;
    }

    public void setStrInvoiceNo(String strInvoiceNo) {
        this.strInvoiceNo = strInvoiceNo;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getPackets() {
        return packets;
    }

    public void setPackets(String packets) {
        this.packets = packets;
    }

    public String getBoxes() {
        return boxes;
    }

    public void setBoxes(String boxes) {
        this.boxes = boxes;
    }

    String strInvoiceNo,items,packets,boxes;
}
