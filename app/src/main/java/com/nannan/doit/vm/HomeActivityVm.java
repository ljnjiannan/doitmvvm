package com.nannan.doit.vm;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.nannan.doit.BR;
import com.nannan.doit.R;
import com.nannan.doit.Util.DateUtil;
import com.nannan.doit.Util.DensityUtil;
import com.nannan.doit.Util.LogUtil;
import com.nannan.doit.activity.MissionEditActivity;
import com.nannan.doit.base.BaseActivityViewModel;
import com.nannan.doit.data.db.DBFactory;
import com.nannan.doit.model.MissionModel;
import com.nannan.doit.widget.swiperecyclerview.DefaultItemTouchHelpCallback;

import me.tatarka.bindingcollectionadapter.ItemView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author ljnjiannan
 * @since 16/8/20.
 */

public class HomeActivityVm extends BaseActivityViewModel {

  public static final String TAG = HomeActivityVm.class.getSimpleName();

  public Activity mContext;

  public ObservableArrayList<HomeMissionListItemVm> dataList = new ObservableArrayList<>();
  public ItemView itemView = ItemView.of(BR.viewModel, R.layout.item_main_list);

  public HomeActivityVm(Activity context) {
    mContext = context;
  }


  public void reloadData() {
    dataList.clear();
    DBFactory.getMissionModelQuery(mContext).list()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(missions -> {
          for (MissionModel model : missions) {
            dataList.add(new HomeMissionListItemVm(model));
          }
        }, throwable -> {
        });
  }

  public void onClickListener(View view) {
    Intent intent = new Intent(mContext, MissionEditActivity.class);
    mContext.startActivity(intent);
  }

  @Override
  protected void onOptionsItemSelected(MenuItem item) {
    Toast.makeText(mContext, "item click", Toast.LENGTH_LONG).show();
  }

  public DefaultItemTouchHelpCallback.OnItemTouchCallbackListener onItemTouchCallbackListener
      = new DefaultItemTouchHelpCallback.OnItemTouchCallbackListener() {
    @Override
    public void onSwiped(int adapterPosition, int direction) {
      if (dataList != null) {
        MissionModel model = dataList.get(adapterPosition).missionModel;
        model.setModifyTime(DateUtil.getCurrentTime());
        if (direction == ItemTouchHelper.RIGHT) {
          //完成
          model.setDone(!model.isDone());
          dataList.remove(adapterPosition);
          if (model.isDone()) {
            dataList.add(new HomeMissionListItemVm(model));
          } else {
            dataList.add(0, new HomeMissionListItemVm(model));
          }
          //更新数据库
          DBFactory.getMissionModelDao(mContext)
              .update(model)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe((mission) -> {
                LogUtil.info(TAG, "update success");
              }, (throwable) -> {
              });
        } else {
          //删除
          dataList.remove(adapterPosition);
          //从数据库删除
          DBFactory.getMissionModelDao(mContext)
              .delete(model)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(c -> {
                  },
                  throwable -> {
                  });
        }
      }
    }

    @Override
    public boolean onMove(int srcPosition, int targetPosition) {
      if (dataList != null) {
        HomeMissionListItemVm model = dataList.get(targetPosition);
        dataList.remove(targetPosition);
        dataList.add(srcPosition, model);
        return true;
      }
      return false;
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
        float dX, float dY, int actionState, boolean isCurrentlyActive) {
      // Fade out the view as it is swiped out of the parent's bounds
      if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
        View itemView = viewHolder.itemView;
        Bitmap icon;
        Paint p = new Paint();
        if (dX < 0) {
          icon = BitmapFactory.decodeResource(mContext.getResources(),
              R.mipmap.ic_delete_white_24dp);
          // Set color for right swipe
          p.setColor(Color.RED);
          // Draw Rect with varying right side, equal to displacement dX
          c.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(),
              itemView.getRight(),
              (float) itemView.getBottom(), p);

          // Set the image icon for right swipe
          c.drawBitmap(icon, (float) itemView.getRight() - DensityUtil.dpToPx(40),
              (float) itemView.getTop() +
                  ((float) itemView.getBottom() - (float) itemView.getTop() - icon.getHeight()) / 2,
              p);
          icon.recycle();
        } else {
          p.setColor(Color.BLUE);
          c.drawRect((float) itemView.getLeft() + DensityUtil.dpToPx(0), (float) itemView.getTop(),
              dX + DensityUtil.dpToPx(0),
              (float) itemView.getBottom(), p);
        }
      }
    }
  };

}
