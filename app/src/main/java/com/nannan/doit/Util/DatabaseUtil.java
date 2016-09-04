package com.nannan.doit.Util;

import android.app.Activity;

import com.nannan.doit.DIApplication;
import com.nannan.doit.model.DaoSession;

import java.util.List;

/**
 * @author ljnjiannan
 * @since 16/8/22.
 */

public class DatabaseUtil<T> {

  public static DaoSession getDaoSession(Activity activity){
    return ((DIApplication)activity.getApplication()).getDaoSession();
  }

  public List<T> orderList(List<T> list){

    return list;
  }

}
