package com.app.entero.direct.model;

/**
 * Created by admin on 10/24/2018.
 */

public class Visitplanmodal {


   private String id;
    private String name;
    private String address;

    public Visitplanmodal(String idd, String name, String address){

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
    }
}
