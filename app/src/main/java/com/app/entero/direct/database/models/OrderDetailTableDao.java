package com.app.entero.direct.database.models;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "order_deatil_table".
*/
public class OrderDetailTableDao extends AbstractDao<OrderDetailTable, Long> {

    public static final String TABLENAME = "order_deatil_table";

    /**
     * Properties of entity OrderDetailTable.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Stockiest_id = new Property(1, String.class, "stockiest_id", false, "stockiest_id");
        public final static Property Doc_no = new Property(2, String.class, "doc_no", false, "doc_no");
        public final static Property Product_ID = new Property(3, String.class, "Product_ID", false, "product_id");
        public final static Property Itemcode = new Property(4, String.class, "Itemcode", false, "Itemcode");
        public final static Property Itemname = new Property(5, String.class, "Itemname", false, "Itemname");
        public final static Property Mrp = new Property(6, String.class, "Mrp", false, "Mrp");
        public final static Property Rate = new Property(7, String.class, "Rate", false, "Rate");
        public final static Property Stock = new Property(8, String.class, "Stock", false, "Stock");
        public final static Property MfgCode = new Property(9, String.class, "MfgCode", false, "MfgCode");
        public final static Property MfgName = new Property(10, String.class, "MfgName", false, "MfgName");
        public final static Property Image_path = new Property(11, String.class, "Image_path", false, "Image_path");
        public final static Property Packsize = new Property(12, String.class, "Packsize", false, "Packsize");
        public final static Property Scheme = new Property(13, String.class, "Scheme", false, "Scheme");
        public final static Property PercentScheme = new Property(14, String.class, "PercentScheme", false, "PercentScheme");
        public final static Property LegendMode = new Property(15, String.class, "LegendMode", false, "LegendMode");
        public final static Property ColorCode = new Property(16, String.class, "ColorCode", false, "ColorCode");
        public final static Property HalfScheme = new Property(17, String.class, "HalfScheme", false, "HalfScheme");
        public final static Property MinQty = new Property(18, String.class, "MinQty", false, "MinQty");
        public final static Property MaxQty = new Property(19, String.class, "MaxQty", false, "MaxQty");
        public final static Property BoxSize = new Property(20, String.class, "BoxSize", false, "BoxSize");
        public final static Property Quantity = new Property(21, String.class, "Quantity", false, "Quantity");
        public final static Property Stk_id = new Property(22, String.class, "Stk_id", false, "Stk_id");
    }


    public OrderDetailTableDao(DaoConfig config) {
        super(config);
    }
    
    public OrderDetailTableDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"order_deatil_table\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"stockiest_id\" TEXT," + // 1: stockiest_id
                "\"doc_no\" TEXT," + // 2: doc_no
                "\"product_id\" TEXT," + // 3: Product_ID
                "\"Itemcode\" TEXT," + // 4: Itemcode
                "\"Itemname\" TEXT," + // 5: Itemname
                "\"Mrp\" TEXT," + // 6: Mrp
                "\"Rate\" TEXT," + // 7: Rate
                "\"Stock\" TEXT," + // 8: Stock
                "\"MfgCode\" TEXT," + // 9: MfgCode
                "\"MfgName\" TEXT," + // 10: MfgName
                "\"Image_path\" TEXT," + // 11: Image_path
                "\"Packsize\" TEXT," + // 12: Packsize
                "\"Scheme\" TEXT," + // 13: Scheme
                "\"PercentScheme\" TEXT," + // 14: PercentScheme
                "\"LegendMode\" TEXT," + // 15: LegendMode
                "\"ColorCode\" TEXT," + // 16: ColorCode
                "\"HalfScheme\" TEXT," + // 17: HalfScheme
                "\"MinQty\" TEXT," + // 18: MinQty
                "\"MaxQty\" TEXT," + // 19: MaxQty
                "\"BoxSize\" TEXT," + // 20: BoxSize
                "\"Quantity\" TEXT," + // 21: Quantity
                "\"Stk_id\" TEXT);"); // 22: Stk_id
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"order_deatil_table\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, OrderDetailTable entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String stockiest_id = entity.getStockiest_id();
        if (stockiest_id != null) {
            stmt.bindString(2, stockiest_id);
        }
 
        String doc_no = entity.getDoc_no();
        if (doc_no != null) {
            stmt.bindString(3, doc_no);
        }
 
        String Product_ID = entity.getProduct_ID();
        if (Product_ID != null) {
            stmt.bindString(4, Product_ID);
        }
 
        String Itemcode = entity.getItemcode();
        if (Itemcode != null) {
            stmt.bindString(5, Itemcode);
        }
 
        String Itemname = entity.getItemname();
        if (Itemname != null) {
            stmt.bindString(6, Itemname);
        }
 
        String Mrp = entity.getMrp();
        if (Mrp != null) {
            stmt.bindString(7, Mrp);
        }
 
        String Rate = entity.getRate();
        if (Rate != null) {
            stmt.bindString(8, Rate);
        }
 
        String Stock = entity.getStock();
        if (Stock != null) {
            stmt.bindString(9, Stock);
        }
 
        String MfgCode = entity.getMfgCode();
        if (MfgCode != null) {
            stmt.bindString(10, MfgCode);
        }
 
        String MfgName = entity.getMfgName();
        if (MfgName != null) {
            stmt.bindString(11, MfgName);
        }
 
        String Image_path = entity.getImage_path();
        if (Image_path != null) {
            stmt.bindString(12, Image_path);
        }
 
        String Packsize = entity.getPacksize();
        if (Packsize != null) {
            stmt.bindString(13, Packsize);
        }
 
        String Scheme = entity.getScheme();
        if (Scheme != null) {
            stmt.bindString(14, Scheme);
        }
 
        String PercentScheme = entity.getPercentScheme();
        if (PercentScheme != null) {
            stmt.bindString(15, PercentScheme);
        }
 
        String LegendMode = entity.getLegendMode();
        if (LegendMode != null) {
            stmt.bindString(16, LegendMode);
        }
 
        String ColorCode = entity.getColorCode();
        if (ColorCode != null) {
            stmt.bindString(17, ColorCode);
        }
 
        String HalfScheme = entity.getHalfScheme();
        if (HalfScheme != null) {
            stmt.bindString(18, HalfScheme);
        }
 
        String MinQty = entity.getMinQty();
        if (MinQty != null) {
            stmt.bindString(19, MinQty);
        }
 
        String MaxQty = entity.getMaxQty();
        if (MaxQty != null) {
            stmt.bindString(20, MaxQty);
        }
 
        String BoxSize = entity.getBoxSize();
        if (BoxSize != null) {
            stmt.bindString(21, BoxSize);
        }
 
        String Quantity = entity.getQuantity();
        if (Quantity != null) {
            stmt.bindString(22, Quantity);
        }
 
        String Stk_id = entity.getStk_id();
        if (Stk_id != null) {
            stmt.bindString(23, Stk_id);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, OrderDetailTable entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String stockiest_id = entity.getStockiest_id();
        if (stockiest_id != null) {
            stmt.bindString(2, stockiest_id);
        }
 
        String doc_no = entity.getDoc_no();
        if (doc_no != null) {
            stmt.bindString(3, doc_no);
        }
 
        String Product_ID = entity.getProduct_ID();
        if (Product_ID != null) {
            stmt.bindString(4, Product_ID);
        }
 
        String Itemcode = entity.getItemcode();
        if (Itemcode != null) {
            stmt.bindString(5, Itemcode);
        }
 
        String Itemname = entity.getItemname();
        if (Itemname != null) {
            stmt.bindString(6, Itemname);
        }
 
        String Mrp = entity.getMrp();
        if (Mrp != null) {
            stmt.bindString(7, Mrp);
        }
 
        String Rate = entity.getRate();
        if (Rate != null) {
            stmt.bindString(8, Rate);
        }
 
        String Stock = entity.getStock();
        if (Stock != null) {
            stmt.bindString(9, Stock);
        }
 
        String MfgCode = entity.getMfgCode();
        if (MfgCode != null) {
            stmt.bindString(10, MfgCode);
        }
 
        String MfgName = entity.getMfgName();
        if (MfgName != null) {
            stmt.bindString(11, MfgName);
        }
 
        String Image_path = entity.getImage_path();
        if (Image_path != null) {
            stmt.bindString(12, Image_path);
        }
 
        String Packsize = entity.getPacksize();
        if (Packsize != null) {
            stmt.bindString(13, Packsize);
        }
 
        String Scheme = entity.getScheme();
        if (Scheme != null) {
            stmt.bindString(14, Scheme);
        }
 
        String PercentScheme = entity.getPercentScheme();
        if (PercentScheme != null) {
            stmt.bindString(15, PercentScheme);
        }
 
        String LegendMode = entity.getLegendMode();
        if (LegendMode != null) {
            stmt.bindString(16, LegendMode);
        }
 
        String ColorCode = entity.getColorCode();
        if (ColorCode != null) {
            stmt.bindString(17, ColorCode);
        }
 
        String HalfScheme = entity.getHalfScheme();
        if (HalfScheme != null) {
            stmt.bindString(18, HalfScheme);
        }
 
        String MinQty = entity.getMinQty();
        if (MinQty != null) {
            stmt.bindString(19, MinQty);
        }
 
        String MaxQty = entity.getMaxQty();
        if (MaxQty != null) {
            stmt.bindString(20, MaxQty);
        }
 
        String BoxSize = entity.getBoxSize();
        if (BoxSize != null) {
            stmt.bindString(21, BoxSize);
        }
 
        String Quantity = entity.getQuantity();
        if (Quantity != null) {
            stmt.bindString(22, Quantity);
        }
 
        String Stk_id = entity.getStk_id();
        if (Stk_id != null) {
            stmt.bindString(23, Stk_id);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public OrderDetailTable readEntity(Cursor cursor, int offset) {
        OrderDetailTable entity = new OrderDetailTable( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // stockiest_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // doc_no
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // Product_ID
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // Itemcode
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // Itemname
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // Mrp
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // Rate
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // Stock
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // MfgCode
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // MfgName
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // Image_path
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // Packsize
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // Scheme
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // PercentScheme
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // LegendMode
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // ColorCode
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // HalfScheme
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // MinQty
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // MaxQty
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // BoxSize
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // Quantity
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22) // Stk_id
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, OrderDetailTable entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStockiest_id(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDoc_no(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setProduct_ID(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setItemcode(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setItemname(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setMrp(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setRate(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setStock(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setMfgCode(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setMfgName(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setImage_path(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setPacksize(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setScheme(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setPercentScheme(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setLegendMode(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setColorCode(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setHalfScheme(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setMinQty(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setMaxQty(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setBoxSize(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setQuantity(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setStk_id(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(OrderDetailTable entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(OrderDetailTable entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(OrderDetailTable entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
