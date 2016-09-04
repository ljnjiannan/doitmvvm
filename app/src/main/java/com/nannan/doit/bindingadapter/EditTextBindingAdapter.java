package com.nannan.doit.bindingadapter;

import android.databinding.BindingAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.nannan.doit.vm.MissionEditActivityVm;

/**
 * @author ljnjiannan
 * @since 16/8/24.
 */

public class EditTextBindingAdapter {
  @BindingAdapter("onTextChange")
  public static void onTextChange(final EditText editText, final MissionEditActivityVm
      viewModel){
    editText.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
          viewModel.setMissionName(s.toString());
      }
    });

  }

}
