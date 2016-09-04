package com.nannan.doit.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.nannan.doit.R;
import com.nannan.doit.base.BaseActivity;
import com.nannan.doit.vm.HomeActivityVm;

public class HomeActivity extends BaseActivity<HomeActivityVm> {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);


  }

  @Override
  protected int getContentViewId() {
    return R.layout.activity_main;
  }

  @Override
  protected void bindVariable(ViewDataBinding binding) {
  }

  @Override
  protected HomeActivityVm getActivityViewModel() {
    return new HomeActivityVm(this);
  }

  @Override
  protected String getActivityTitle() {
    return null;
  }

  @Override
  protected void onResume() {
    super.onResume();
    if(activityViewModel!=null){
      ((HomeActivityVm)activityViewModel).reloadData();
    }
  }

}
