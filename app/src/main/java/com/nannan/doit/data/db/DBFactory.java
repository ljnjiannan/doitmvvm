package com.nannan.doit.data.db;

import android.app.Activity;

import com.nannan.doit.Util.DatabaseUtil;
import com.nannan.doit.model.DaoSession;
import com.nannan.doit.model.MissionModel;
import com.nannan.doit.model.MissionModelDao;

import org.greenrobot.greendao.rx.RxDao;
import org.greenrobot.greendao.rx.RxQuery;

/**
 * @author ljnjiannan
 * @since 16/9/4.
 */

public class DBFactory {

  private static final Object LOCK = new Object();
  private static RxQuery<MissionModel> missionModelRxQuery;
  private static RxDao<MissionModel, Long> missionModelLongRxDao;

  private static DaoSession getDaoSession(Activity activity) {
    synchronized (LOCK) {
      return DatabaseUtil.getDaoSession(activity);
    }
  }

  public static RxQuery<MissionModel> getMissionModelQuery(Activity activity) {
    synchronized (LOCK) {
      if (missionModelRxQuery == null) {
        missionModelRxQuery = getDaoSession(activity).getMissionModelDao().queryBuilder().orderAsc(
            MissionModelDao.Properties.State, MissionModelDao.Properties.ModifyTime,
            MissionModelDao.Properties.AddTime).rx();
      }
      return missionModelRxQuery;
    }
  }

  public static RxDao<MissionModel, Long> getMissionModelDao(Activity activity) {
    synchronized (LOCK) {
      if (missionModelLongRxDao == null) {
        missionModelLongRxDao = getDaoSession(activity).getMissionModelDao().rx();
      }
      return missionModelLongRxDao;
    }
  }

}
