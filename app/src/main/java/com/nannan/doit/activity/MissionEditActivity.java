package com.nannan.doit.activity;

import android.databinding.ViewDataBinding;
import android.view.Menu;

import com.nannan.doit.R;
import com.nannan.doit.base.BaseActivity;
import com.nannan.doit.vm.MissionEditActivityVm;

/**
 * @author ljnjiannan
 * @since 16/8/22.
 */

public class MissionEditActivity extends BaseActivity<MissionEditActivityVm> {

  @Override
  protected int getContentViewId() {
    return R.layout.activity_mission_edit;
  }

  @Override
  protected void bindVariable(ViewDataBinding binding) {

  }

  @Override
  protected MissionEditActivityVm getActivityViewModel() {
    return new MissionEditActivityVm(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_home,menu);
    return true;
  }


  @Override
  protected String getActivityTitle() {
    return "新建";
  }

}
