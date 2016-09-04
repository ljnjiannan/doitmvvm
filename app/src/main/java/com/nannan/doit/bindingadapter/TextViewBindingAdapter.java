package com.nannan.doit.bindingadapter;

import android.databinding.BindingAdapter;
import android.graphics.Paint;
import android.widget.TextView;

/**
 * @author ljnjiannan
 * @since 16/9/4.
 */

public class TextViewBindingAdapter {

  @BindingAdapter("deleteState")
  public static void setDeleteState(TextView textView,boolean deleted){
    if(deleted){
      textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }else {
      textView.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
    }
  }

}
