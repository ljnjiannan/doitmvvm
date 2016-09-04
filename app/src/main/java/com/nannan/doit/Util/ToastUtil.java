package com.nannan.doit.Util;

import android.content.Context;
import android.widget.Toast;

/**
 * @author ljnjiannan
 * @since 16/8/24.
 */

public final class ToastUtil {

  private ToastUtil() {
  }

  private static void show(Context context, int resId, int duration) {
    Toast.makeText(context, resId, duration).show();
  }

  private static void show(Context context, String message, int duration) {
    Toast.makeText(context, message, duration).show();
  }

  public static void showShort(Context context, int resId) {
    show(context, resId, Toast.LENGTH_SHORT);
  }

  public static void showShort(Context context, String message) {
    show(context, message, Toast.LENGTH_SHORT);
  }

  public static void showLong(Context context, int resId) {
    show(context, resId, Toast.LENGTH_LONG);
  }

  public static void showLong(Context context, String message) {
    show(context, message, Toast.LENGTH_LONG);
  }
}