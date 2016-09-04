package com.nannan.doit.adapter;

import android.support.annotation.NonNull;

import me.tatarka.bindingcollectionadapter.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter.ItemViewArg;

/**
 * @author ljnjiannan
 * @since 16/8/27.
 */

public class MyRecyclerViewAdapter<T> extends BindingRecyclerViewAdapter<T> {

  public MyRecyclerViewAdapter(
      @NonNull ItemViewArg<T> arg) {
    super(arg);
  }
}