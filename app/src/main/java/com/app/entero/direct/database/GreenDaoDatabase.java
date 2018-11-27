package com.app.entero.direct.database;

import android.content.Context;
import android.support.annotation.NonNull;


import com.app.entero.direct.database.models.DaoMaster;
import com.app.entero.direct.database.models.DaoSession;

import org.greenrobot.greendao.database.Database;


public class GreenDaoDatabase {

    private static DaoSession mDaoSession;
    public static final boolean ENCRYPTED = true;

    private GreenDaoDatabase() {
    }

    private static class SingletonHolder {
        private static final GreenDaoDatabase INSTANCE = new GreenDaoDatabase();
    }

    public static GreenDaoDatabase getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void init(Context context) {
        init(context, "db");
    }

    public void init(@NonNull Context context, @NonNull String dbName) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context.getApplicationContext(), ENCRYPTED ? "notes-db-encrypted" : "articles-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        if (null == mDaoSession) {
            throw new NullPointerException("green db has not been initialized");
        }
        return mDaoSession;
    }
}
