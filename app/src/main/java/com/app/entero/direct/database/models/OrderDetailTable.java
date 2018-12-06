package com.app.entero.direct.database.models;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "order_deatil_table")
public class OrderDetailTable {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "stockiest_id")
    private String stockiest_id;

    @Property(nameInDb = "doc_no")
    private String doc_no;

    @Property(nameInDb = "product_id")
    private String Product_ID;

    @Property(nameInDb = "Itemcode")
    private String Itemcode;

    @Property(nameInDb = "Itemname")
    private String Itemname;

    @Property(nameInDb = "Mrp")
    private String Mrp;

    @Property(nameInDb = "Rate")
    private String Rate;

    @Property(nameInDb = "Stock")
    private String Stock;

    @Property(nameInDb = "MfgCode")
    private String MfgCode;

    @Property(nameInDb = "MfgName")
    private String MfgName;

    @Property(nameInDb = "Image_path")
    private String Image_path;

    @Property(nameInDb = "Packsize")
    private String Packsize;

    @Property(nameInDb = "Scheme")
    private String Scheme;

    @Property(nameInDb = "PercentScheme")
    private String PercentScheme;

    @Property(nameInDb = "LegendMode")
    private String LegendMode;

    @Property(nameInDb = "ColorCode")
    private String ColorCode;

    @Property(nameInDb = "HalfScheme")
    private String HalfScheme;

    @Property(nameInDb = "MinQty")
    private String MinQty;

    @Property(nameInDb = "MaxQty")
    private String MaxQty;

    @Property(nameInDb = "BoxSize")
    private String BoxSize;

    @Property(nameInDb = "Quantity")
    private String Quantity;

    @Property(nameInDb = "Stk_id")
    private String Stk_id;

    @Generated(hash = 393866322)
    public OrderDetailTable(Long id, String stockiest_id, String doc_no,
            String Product_ID, String Itemcode, String Itemname, String Mrp,
            String Rate, String Stock, String MfgCode, String MfgName,
            String Image_path, String Packsize, String Scheme, String PercentScheme,
            String LegendMode, String ColorCode, String HalfScheme, String MinQty,
            String MaxQty, String BoxSize, String Quantity, String Stk_id) {
        this.id = id;
        this.stockiest_id = stockiest_id;
        this.doc_no = doc_no;
        this.Product_ID = Product_ID;
        this.Itemcode = Itemcode;
        this.Itemname = Itemname;
        this.Mrp = Mrp;
        this.Rate = Rate;
        this.Stock = Stock;
        this.MfgCode = MfgCode;
        this.MfgName = MfgName;
        this.Image_path = Image_path;
        this.Packsize = Packsize;
        this.Scheme = Scheme;
        this.PercentScheme = PercentScheme;
        this.LegendMode = LegendMode;
        this.ColorCode = ColorCode;
        this.HalfScheme = HalfScheme;
        this.MinQty = MinQty;
        this.MaxQty = MaxQty;
        this.BoxSize = BoxSize;
        this.Quantity = Quantity;
        this.Stk_id = Stk_id;
    }

    @Generated(hash = 1953615676)
    public OrderDetailTable() {
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

    public String getProduct_ID() {
        return this.Product_ID;
    }

    public void setProduct_ID(String Product_ID) {
        this.Product_ID = Product_ID;
    }

    public String getItemcode() {
        return this.Itemcode;
    }

    public void setItemcode(String Itemcode) {
        this.Itemcode = Itemcode;
    }

    public String getItemname() {
        return this.Itemname;
    }

    public void setItemname(String Itemname) {
        this.Itemname = Itemname;
    }

    public String getMrp() {
        return this.Mrp;
    }

    public void setMrp(String Mrp) {
        this.Mrp = Mrp;
    }

    public String getRate() {
        return this.Rate;
    }

    public void setRate(String Rate) {
        this.Rate = Rate;
    }

    public String getStock() {
        return this.Stock;
    }

    public void setStock(String Stock) {
        this.Stock = Stock;
    }

    public String getMfgCode() {
        return this.MfgCode;
    }

    public void setMfgCode(String MfgCode) {
        this.MfgCode = MfgCode;
    }

    public String getMfgName() {
        return this.MfgName;
    }

    public void setMfgName(String MfgName) {
        this.MfgName = MfgName;
    }

    public String getImage_path() {
        return this.Image_path;
    }

    public void setImage_path(String Image_path) {
        this.Image_path = Image_path;
    }

    public String getPacksize() {
        return this.Packsize;
    }

    public void setPacksize(String Packsize) {
        this.Packsize = Packsize;
    }

    public String getScheme() {
        return this.Scheme;
    }

    public void setScheme(String Scheme) {
        this.Scheme = Scheme;
    }

    public String getPercentScheme() {
        return this.PercentScheme;
    }

    public void setPercentScheme(String PercentScheme) {
        this.PercentScheme = PercentScheme;
    }

    public String getLegendMode() {
        return this.LegendMode;
    }

    public void setLegendMode(String LegendMode) {
        this.LegendMode = LegendMode;
    }

    public String getColorCode() {
        return this.ColorCode;
    }

    public void setColorCode(String ColorCode) {
        this.ColorCode = ColorCode;
    }

    public String getHalfScheme() {
        return this.HalfScheme;
    }

    public void setHalfScheme(String HalfScheme) {
        this.HalfScheme = HalfScheme;
    }

    public String getMinQty() {
        return this.MinQty;
    }

    public void setMinQty(String MinQty) {
        this.MinQty = MinQty;
    }

    public String getMaxQty() {
        return this.MaxQty;
    }

    public void setMaxQty(String MaxQty) {
        this.MaxQty = MaxQty;
    }

    public String getBoxSize() {
        return this.BoxSize;
    }

    public void setBoxSize(String BoxSize) {
        this.BoxSize = BoxSize;
    }

    public String getQuantity() {
        return this.Quantity;
    }

    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
    }

    public String getStk_id() {
        return this.Stk_id;
    }

    public void setStk_id(String Stk_id) {
        this.Stk_id = Stk_id;
    }


}
