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
 * DAO for table "order_table_master".
*/
public class OrderTableMasterDao extends AbstractDao<OrderTableMaster, Long> {

    public static final String TABLENAME = "order_table_master";

    /**
     * Properties of entity OrderTableMaster.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Stockiest_id = new Property(1, String.class, "stockiest_id", false, "stockiest_id");
        public final static Property Doc_no = new Property(2, String.class, "doc_no", false, "doc_no");
        public final static Property Remarks = new Property(3, String.class, "remarks", false, "remarks");
        public final static Property Created_date = new Property(4, String.class, "created_date", false, "created_date");
        public final static Property Salesman = new Property(5, String.class, "salesman", false, "salesman");
        public final static Property Chemist = new Property(6, String.class, "chemist", false, "chemist");
    }


    public OrderTableMasterDao(DaoConfig config) {
        super(config);
    }
    
    public OrderTableMasterDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"order_table_master\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"stockiest_id\" TEXT," + // 1: stockiest_id
                "\"doc_no\" TEXT," + // 2: doc_no
                "\"remarks\" TEXT," + // 3: remarks
                "\"created_date\" TEXT," + // 4: created_date
                "\"salesman\" TEXT," + // 5: salesman
                "\"chemist\" TEXT);"); // 6: chemist
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"order_table_master\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, OrderTableMaster entity) {
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
 
        String remarks = entity.getRemarks();
        if (remarks != null) {
            stmt.bindString(4, remarks);
        }
 
        String created_date = entity.getCreated_date();
        if (created_date != null) {
            stmt.bindString(5, created_date);
        }
 
        String salesman = entity.getSalesman();
        if (salesman != null) {
            stmt.bindString(6, salesman);
        }
 
        String chemist = entity.getChemist();
        if (chemist != null) {
            stmt.bindString(7, chemist);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, OrderTableMaster entity) {
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
 
        String remarks = entity.getRemarks();
        if (remarks != null) {
            stmt.bindString(4, remarks);
        }
 
        String created_date = entity.getCreated_date();
        if (created_date != null) {
            stmt.bindString(5, created_date);
        }
 
        String salesman = entity.getSalesman();
        if (salesman != null) {
            stmt.bindString(6, salesman);
        }
 
        String chemist = entity.getChemist();
        if (chemist != null) {
            stmt.bindString(7, chemist);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public OrderTableMaster readEntity(Cursor cursor, int offset) {
        OrderTableMaster entity = new OrderTableMaster( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // stockiest_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // doc_no
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // remarks
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // created_date
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // salesman
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // chemist
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, OrderTableMaster entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStockiest_id(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDoc_no(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setRemarks(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCreated_date(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSalesman(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setChemist(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(OrderTableMaster entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(OrderTableMaster entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(OrderTableMaster entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
