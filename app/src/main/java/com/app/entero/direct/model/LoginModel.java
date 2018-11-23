
package com.app.entero.direct.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginModel implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("doc_id")
    @Expose
    private String docId;
    @SerializedName("patient_id")
    @Expose
    private String patientId;
    @SerializedName("patient_dob")
    @Expose
    private String patientDob;
    @SerializedName("patient_date")
    @Expose
    private String patientDate;
    @SerializedName("facility")
    @Expose
    private String facility;
    @SerializedName("patient_name")
    @Expose
    private String patientName;
    @SerializedName("physician_name")
    @Expose
    private String physicianName;
    @SerializedName("patient_wound_date")
    @Expose
    private String patientWoundDate;
    @SerializedName("wound")
    @Expose
    private String wound;

    @SerializedName("facility_id")
    @Expose
    private String facility_id;

    @SerializedName("credential")
    @Expose
    private String credential;

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getFacility_id() {
        return facility_id;
    }

    public void setFacility_id(String facility_id) {
        this.facility_id = facility_id;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientDob() {
        return patientDob;
    }

    public void setPatientDob(String patientDob) {
        this.patientDob = patientDob;
    }

    public String getPatientDate() {
        return patientDate;
    }

    public void setPatientDate(String patientDate) {
        this.patientDate = patientDate;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPhysicianName() {
        return physicianName;
    }

    public void setPhysicianName(String physicianName) {
        this.physicianName = physicianName;
    }

    public String getPatientWoundDate() {
        return patientWoundDate;
    }

    public void setPatientWoundDate(String patientWoundDate) {
        this.patientWoundDate = patientWoundDate;
    }

    public String getWound() {
        return wound;
    }

    public void setWound(String wound) {
        this.wound = wound;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @SerializedName("picture")

    @Expose
    private String picture;
    @SerializedName("phone")
    @Expose
    private String phone;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
