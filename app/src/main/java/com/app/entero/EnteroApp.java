package com.app.entero;

import android.app.Application;
import android.os.Environment;

import com.app.entero.direct.database.models.DaoMaster;
import com.app.entero.direct.database.models.DaoSession;
import com.app.entero.direct.database.models.DbOpenHelper;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;

public class EnteroApp extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
     /*   File path = new File(Environment.getExternalStorageDirectory(), "entero-db");
        path.getParentFile().mkdirs();*/
       daoSession = new DaoMaster( new DbOpenHelper(this, "entero-db").getWritableDatabase()).newSession();
       // daoSession = new DaoMaster( new DbOpenHelper(this, path.getAbsolutePath()).getWritableDatabase()).newSession();

    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
