package com.app.entero;

import android.app.Application;

import com.app.entero.direct.database.models.DaoMaster;
import com.app.entero.direct.database.models.DaoSession;
import com.app.entero.direct.database.models.DbOpenHelper;

import org.greenrobot.greendao.query.QueryBuilder;

public class EnteroApp extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        daoSession = new DaoMaster( new DbOpenHelper(this, "entero-db").getWritableDatabase()).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
