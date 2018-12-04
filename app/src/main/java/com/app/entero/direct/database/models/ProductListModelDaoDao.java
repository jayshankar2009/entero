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
 * DAO for table "productlist".
*/
public class ProductListModelDaoDao extends AbstractDao<ProductListModelDao, Long> {

    public static final String TABLENAME = "productlist";

    /**
     * Properties of entity ProductListModelDao.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Product_ID = new Property(1, String.class, "Product_ID", false, "product_id");
        public final static Property Itemcode = new Property(2, String.class, "Itemcode", false, "Itemcode");
        public final static Property Itemname = new Property(3, String.class, "Itemname", false, "Itemname");
        public final static Property Mrp = new Property(4, String.class, "Mrp", false, "Mrp");
        public final static Property Rate = new Property(5, String.class, "Rate", false, "Rate");
        public final static Property Stock = new Property(6, String.class, "Stock", false, "Stock");
        public final static Property MfgCode = new Property(7, String.class, "MfgCode", false, "MfgCode");
        public final static Property MfgName = new Property(8, String.class, "MfgName", false, "MfgName");
        public final static Property Image_path = new Property(9, String.class, "Image_path", false, "Image_path");
        public final static Property Packsize = new Property(10, String.class, "Packsize", false, "Packsize");
        public final static Property Scheme = new Property(11, String.class, "Scheme", false, "Scheme");
        public final static Property PercentScheme = new Property(12, String.class, "PercentScheme", false, "PercentScheme");
        public final static Property LegendMode = new Property(13, String.class, "LegendMode", false, "LegendMode");
        public final static Property ColorCode = new Property(14, String.class, "ColorCode", false, "ColorCode");
        public final static Property HalfScheme = new Property(15, String.class, "HalfScheme", false, "HalfScheme");
        public final static Property MinQty = new Property(16, String.class, "MinQty", false, "MinQty");
        public final static Property MaxQty = new Property(17, String.class, "MaxQty", false, "MaxQty");
        public final static Property BoxSize = new Property(18, String.class, "BoxSize", false, "BoxSize");
    }


    public ProductListModelDaoDao(DaoConfig config) {
        super(config);
    }
    
    public ProductListModelDaoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"productlist\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"product_id\" TEXT," + // 1: Product_ID
                "\"Itemcode\" TEXT," + // 2: Itemcode
                "\"Itemname\" TEXT," + // 3: Itemname
                "\"Mrp\" TEXT," + // 4: Mrp
                "\"Rate\" TEXT," + // 5: Rate
                "\"Stock\" TEXT," + // 6: Stock
                "\"MfgCode\" TEXT," + // 7: MfgCode
                "\"MfgName\" TEXT," + // 8: MfgName
                "\"Image_path\" TEXT," + // 9: Image_path
                "\"Packsize\" TEXT," + // 10: Packsize
                "\"Scheme\" TEXT," + // 11: Scheme
                "\"PercentScheme\" TEXT," + // 12: PercentScheme
                "\"LegendMode\" TEXT," + // 13: LegendMode
                "\"ColorCode\" TEXT," + // 14: ColorCode
                "\"HalfScheme\" TEXT," + // 15: HalfScheme
                "\"MinQty\" TEXT," + // 16: MinQty
                "\"MaxQty\" TEXT," + // 17: MaxQty
                "\"BoxSize\" TEXT);"); // 18: BoxSize
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"productlist\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ProductListModelDao entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String Product_ID = entity.getProduct_ID();
        if (Product_ID != null) {
            stmt.bindString(2, Product_ID);
        }
 
        String Itemcode = entity.getItemcode();
        if (Itemcode != null) {
            stmt.bindString(3, Itemcode);
        }
 
        String Itemname = entity.getItemname();
        if (Itemname != null) {
            stmt.bindString(4, Itemname);
        }
 
        String Mrp = entity.getMrp();
        if (Mrp != null) {
            stmt.bindString(5, Mrp);
        }
 
        String Rate = entity.getRate();
        if (Rate != null) {
            stmt.bindString(6, Rate);
        }
 
        String Stock = entity.getStock();
        if (Stock != null) {
            stmt.bindString(7, Stock);
        }
 
        String MfgCode = entity.getMfgCode();
        if (MfgCode != null) {
            stmt.bindString(8, MfgCode);
        }
 
        String MfgName = entity.getMfgName();
        if (MfgName != null) {
            stmt.bindString(9, MfgName);
        }
 
        String Image_path = entity.getImage_path();
        if (Image_path != null) {
            stmt.bindString(10, Image_path);
        }
 
        String Packsize = entity.getPacksize();
        if (Packsize != null) {
            stmt.bindString(11, Packsize);
        }
 
        String Scheme = entity.getScheme();
        if (Scheme != null) {
            stmt.bindString(12, Scheme);
        }
 
        String PercentScheme = entity.getPercentScheme();
        if (PercentScheme != null) {
            stmt.bindString(13, PercentScheme);
        }
 
        String LegendMode = entity.getLegendMode();
        if (LegendMode != null) {
            stmt.bindString(14, LegendMode);
        }
 
        String ColorCode = entity.getColorCode();
        if (ColorCode != null) {
            stmt.bindString(15, ColorCode);
        }
 
        String HalfScheme = entity.getHalfScheme();
        if (HalfScheme != null) {
            stmt.bindString(16, HalfScheme);
        }
 
        String MinQty = entity.getMinQty();
        if (MinQty != null) {
            stmt.bindString(17, MinQty);
        }
 
        String MaxQty = entity.getMaxQty();
        if (MaxQty != null) {
            stmt.bindString(18, MaxQty);
        }
 
        String BoxSize = entity.getBoxSize();
        if (BoxSize != null) {
            stmt.bindString(19, BoxSize);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ProductListModelDao entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String Product_ID = entity.getProduct_ID();
        if (Product_ID != null) {
            stmt.bindString(2, Product_ID);
        }
 
        String Itemcode = entity.getItemcode();
        if (Itemcode != null) {
            stmt.bindString(3, Itemcode);
        }
 
        String Itemname = entity.getItemname();
        if (Itemname != null) {
            stmt.bindString(4, Itemname);
        }
 
        String Mrp = entity.getMrp();
        if (Mrp != null) {
            stmt.bindString(5, Mrp);
        }
 
        String Rate = entity.getRate();
        if (Rate != null) {
            stmt.bindString(6, Rate);
        }
 
        String Stock = entity.getStock();
        if (Stock != null) {
            stmt.bindString(7, Stock);
        }
 
        String MfgCode = entity.getMfgCode();
        if (MfgCode != null) {
            stmt.bindString(8, MfgCode);
        }
 
        String MfgName = entity.getMfgName();
        if (MfgName != null) {
            stmt.bindString(9, MfgName);
        }
 
        String Image_path = entity.getImage_path();
        if (Image_path != null) {
            stmt.bindString(10, Image_path);
        }
 
        String Packsize = entity.getPacksize();
        if (Packsize != null) {
            stmt.bindString(11, Packsize);
        }
 
        String Scheme = entity.getScheme();
        if (Scheme != null) {
            stmt.bindString(12, Scheme);
        }
 
        String PercentScheme = entity.getPercentScheme();
        if (PercentScheme != null) {
            stmt.bindString(13, PercentScheme);
        }
 
        String LegendMode = entity.getLegendMode();
        if (LegendMode != null) {
            stmt.bindString(14, LegendMode);
        }
 
        String ColorCode = entity.getColorCode();
        if (ColorCode != null) {
            stmt.bindString(15, ColorCode);
        }
 
        String HalfScheme = entity.getHalfScheme();
        if (HalfScheme != null) {
            stmt.bindString(16, HalfScheme);
        }
 
        String MinQty = entity.getMinQty();
        if (MinQty != null) {
            stmt.bindString(17, MinQty);
        }
 
        String MaxQty = entity.getMaxQty();
        if (MaxQty != null) {
            stmt.bindString(18, MaxQty);
        }
 
        String BoxSize = entity.getBoxSize();
        if (BoxSize != null) {
            stmt.bindString(19, BoxSize);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ProductListModelDao readEntity(Cursor cursor, int offset) {
        ProductListModelDao entity = new ProductListModelDao( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // Product_ID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // Itemcode
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // Itemname
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // Mrp
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // Rate
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // Stock
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // MfgCode
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // MfgName
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // Image_path
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // Packsize
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // Scheme
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // PercentScheme
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // LegendMode
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // ColorCode
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // HalfScheme
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // MinQty
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // MaxQty
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18) // BoxSize
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ProductListModelDao entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setProduct_ID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setItemcode(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setItemname(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setMrp(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setRate(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setStock(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setMfgCode(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setMfgName(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setImage_path(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setPacksize(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setScheme(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setPercentScheme(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setLegendMode(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setColorCode(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setHalfScheme(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setMinQty(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setMaxQty(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setBoxSize(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ProductListModelDao entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ProductListModelDao entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ProductListModelDao entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
