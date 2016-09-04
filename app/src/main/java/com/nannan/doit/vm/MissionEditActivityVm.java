package com.nannan.doit.vm;

import android.app.Activity;
import android.view.MenuItem;

import com.nannan.doit.Util.DateUtil;
import com.nannan.doit.base.BaseActivityViewModel;
import com.nannan.doit.data.db.DBFactory;
import com.nannan.doit.model.MissionModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author ljnjiannan
 * @since 16/8/24.
 */

public class MissionEditActivityVm extends BaseActivityViewModel {

  public String Tag = "MissionEditActivityVm";

  private Activity mActivity;
  private String missionName = "";

  public String getMissionName() {
    return missionName;
  }

  public void setMissionName(String missionName) {
    this.missionName = missionName;
  }

  public MissionEditActivityVm(Activity activity) {
    mActivity = activity;
  }

  @Override
  protected void onOptionsItemSelected(MenuItem item) {
    if (!missionName.isEmpty()) {
      MissionModel model = new MissionModel();
      model.setTitle(missionName);
      model.setCateId(1);
      model.setAddTime(DateUtil.getCurrentTime());
      DBFactory.getMissionModelDao(mActivity)
          .insert(model)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(missionModel -> {
            mActivity.finish();
          }, throwable -> {
          });

    }
  }
}
