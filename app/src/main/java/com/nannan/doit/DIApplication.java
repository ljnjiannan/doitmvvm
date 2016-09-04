package com.nannan.doit;

import android.app.Application;

import com.nannan.doit.model.DaoMaster;
import com.nannan.doit.model.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * @author ljnjiannan
 * @since 16/8/22.
 */

public class DIApplication extends Application {

  public static final boolean ENCRYPTED = false;

  public static final String DB_NAME="db_doit";
  public static final String DB_NAME_ENCRYPTED="db_doit_encryted";

  private DaoSession daoSession;

  @Override
  public void onCreate() {
    super.onCreate();

    DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,ENCRYPTED ? DB_NAME_ENCRYPTED : DB_NAME);
    Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
    daoSession = new DaoMaster(db).newSession();
  }

  public DaoSession getDaoSession() {
    return daoSession;
  }

}
