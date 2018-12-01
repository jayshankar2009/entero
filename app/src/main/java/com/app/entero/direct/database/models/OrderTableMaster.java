package com.app.entero.direct.database.models;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "order_table_master")
public class OrderTableMaster {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "stockiest_id")
    private String stockiest_id;

    @Property(nameInDb = "doc_no")
    private String doc_no;

    @Property(nameInDb = "remarks")
    private String remarks;


    @Property(nameInDb = "created_date")
    private String created_date;

    @Property(nameInDb = "salesman")
    private String salesman;

    @Property(nameInDb = "chemist")
    private String chemist;

    @Generated(hash = 865807515)
    public OrderTableMaster(Long id, String stockiest_id, String doc_no,
            String remarks, String created_date, String salesman, String chemist) {
        this.id = id;
        this.stockiest_id = stockiest_id;
        this.doc_no = doc_no;
        this.remarks = remarks;
        this.created_date = created_date;
        this.salesman = salesman;
        this.chemist = chemist;
    }

    @Generated(hash = 2029230661)
    public OrderTableMaster() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockiest_id() {
        return this.stockiest_id;
    }

    public void setStockiest_id(String stockiest_id) {
        this.stockiest_id = stockiest_id;
    }

    public String getDoc_no() {
        return this.doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreated_date() {
        return this.created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getSalesman() {
        return this.salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public String getChemist() {
        return this.chemist;
    }

    public void setChemist(String chemist) {
        this.chemist = chemist;
    }





}
