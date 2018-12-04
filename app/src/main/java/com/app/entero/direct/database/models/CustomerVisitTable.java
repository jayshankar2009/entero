package com.app.entero.direct.database.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "customer_visit_plan")
public class CustomerVisitTable {
    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "ChemistID")
    private String ChemistID;
    @Property(nameInDb = "StokistID")
    private String StokistID;
    @Property(nameInDb = "UserID")
    private String UserID;
    @Property(nameInDb = "RouteID")
    private String RouteID;
    @Property(nameInDb = "Chemist_Legal_Name")
    private String Chemist_Legal_Name;
    @Property(nameInDb = "Address")
    private String Address;
    @Property(nameInDb = "MobileNo")
    private String MobileNo;
    @Property(nameInDb = "Email")
    private String Email;
    @Property(nameInDb = "City")
    private String City;
    @Property(nameInDb = "ChemistERPCode")
    private String ChemistERPCode;
    @Property(nameInDb = "TotalAmt")
    private String TotalAmt;
    //coment
    @Property(nameInDb = "OutstandingAmt")
    private String OutstandingAmt;
    @Generated(hash = 1834379807)
    public CustomerVisitTable(Long id, String ChemistID, String StokistID,
            String UserID, String RouteID, String Chemist_Legal_Name,
            String Address, String MobileNo, String Email, String City,
            String ChemistERPCode, String TotalAmt, String OutstandingAmt) {
        this.id = id;
        this.ChemistID = ChemistID;
        this.StokistID = StokistID;
        this.UserID = UserID;
        this.RouteID = RouteID;
        this.Chemist_Legal_Name = Chemist_Legal_Name;
        this.Address = Address;
        this.MobileNo = MobileNo;
        this.Email = Email;
        this.City = City;
        this.ChemistERPCode = ChemistERPCode;
        this.TotalAmt = TotalAmt;
        this.OutstandingAmt = OutstandingAmt;
    }
    @Generated(hash = 2106642369)
    public CustomerVisitTable() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getChemistID() {
        return this.ChemistID;
    }
    public void setChemistID(String ChemistID) {
        this.ChemistID = ChemistID;
    }
    public String getStokistID() {
        return this.StokistID;
    }
    public void setStokistID(String StokistID) {
        this.StokistID = StokistID;
    }
    public String getUserID() {
        return this.UserID;
    }
    public void setUserID(String UserID) {
        this.UserID = UserID;
    }
    public String getRouteID() {
        return this.RouteID;
    }
    public void setRouteID(String RouteID) {
        this.RouteID = RouteID;
    }
    public String getChemist_Legal_Name() {
        return this.Chemist_Legal_Name;
    }
    public void setChemist_Legal_Name(String Chemist_Legal_Name) {
        this.Chemist_Legal_Name = Chemist_Legal_Name;
    }
    public String getAddress() {
        return this.Address;
    }
    public void setAddress(String Address) {
        this.Address = Address;
    }
    public String getMobileNo() {
        return this.MobileNo;
    }
    public void setMobileNo(String MobileNo) {
        this.MobileNo = MobileNo;
    }
    public String getEmail() {
        return this.Email;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }
    public String getCity() {
        return this.City;
    }
    public void setCity(String City) {
        this.City = City;
    }
    public String getChemistERPCode() {
        return this.ChemistERPCode;
    }
    public void setChemistERPCode(String ChemistERPCode) {
        this.ChemistERPCode = ChemistERPCode;
    }
    public String getTotalAmt() {
        return this.TotalAmt;
    }
    public void setTotalAmt(String TotalAmt) {
        this.TotalAmt = TotalAmt;
    }
    public String getOutstandingAmt() {
        return this.OutstandingAmt;
    }
    public void setOutstandingAmt(String OutstandingAmt) {
        this.OutstandingAmt = OutstandingAmt;
    }
  
}
