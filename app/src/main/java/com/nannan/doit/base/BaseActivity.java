package com.nannan.doit.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.nannan.doit.BR;
import com.nannan.doit.R;

/**
 * @author ljnjiannan
 * @since 16/8/22.
 */

public abstract class BaseActivity<T> extends AppCompatActivity {

  private Toolbar mToolbar;
  private ViewDataBinding binding;
  public T activityViewModel;
  public BaseMenuSelectedListener menuSelectedListener;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding= DataBindingUtil.setContentView(this,getContentViewId());
    bindVariable(binding);
    initToolBar();
    activityViewModel=getActivityViewModel();
    bindActivityViewModel(activityViewModel);
  }

  protected abstract int getContentViewId();

  protected abstract void bindVariable(ViewDataBinding binding);

  private void initToolBar(){
    mToolbar=(Toolbar) findViewById(R.id.common_toolbar);
    if (mToolbar!=null){
      setSupportActionBar(mToolbar);
      if(getActivityTitle()!=null) {
        getSupportActionBar().setTitle(getActivityTitle());
      }
    }
  }

  private void bindActivityViewModel(T viewModel){
    if(null != binding && null!= viewModel){
      binding.setVariable(BR.mViewModel,viewModel);
    }
  }


  //设置activity的ViewModel
  protected abstract T getActivityViewModel();

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if( null != activityViewModel && activityViewModel instanceof BaseActivityViewModel){
      ((BaseActivityViewModel)activityViewModel).onOptionsItemSelected(item);
    }
    return super.onOptionsItemSelected(item);
  }

  protected abstract String getActivityTitle();
}
