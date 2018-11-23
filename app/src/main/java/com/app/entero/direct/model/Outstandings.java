package com.app.entero.direct.model;

public class Outstandings {

    String name;
    String version;
    int id_;
    int image;

    public Outstandings(String name, String version, int id_) {
        this.name = name;
        this.version = version;
        this.id_ = id_;

    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }


    public int getId() {
        return id_;
    }
}
