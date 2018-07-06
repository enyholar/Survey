package com.gideondev.survey.feature;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by ENNY on 6/19/2018.
 */
public class SnackBarUtils {

  public static void setErrorMsg(Activity activity, String error){
    View parentLayout = activity.findViewById(android.R.id.content);
    Snackbar.make(parentLayout, error, Snackbar.LENGTH_LONG)
        .setActionTextColor(activity.getResources().getColor(android.R.color.holo_red_light ))
        .show();
  }

}
