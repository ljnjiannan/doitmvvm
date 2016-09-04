package com.nannan.doit.bindingadapter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.nannan.doit.widget.swiperecyclerview.DefaultItemTouchHelpCallback;
import com.nannan.doit.widget.swiperecyclerview.DefaultItemTouchHelper;

/**
 * @author ljnjiannan
 * @since 16/8/26.
 */

public class RecyclerViewBindingAdapter {

  @BindingAdapter("swipeItemTouch")
  public static void setSwipeItemTouch(RecyclerView recyclerView,
      DefaultItemTouchHelpCallback.OnItemTouchCallbackListener listener) {
    DefaultItemTouchHelper itemTouchHelper = new DefaultItemTouchHelper(listener);
    itemTouchHelper.attachToRecyclerView(recyclerView);

    //itemTouchHelper.setDragEnable(true);
    itemTouchHelper.setSwipeEnable(true);
  }

}
