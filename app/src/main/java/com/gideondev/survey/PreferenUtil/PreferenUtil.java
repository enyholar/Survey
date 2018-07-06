package com.gideondev.survey.PreferenUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Tinh Vu on 27/09/2016.
 */

public class PreferenUtil {


  public static final String IMAGENAME = "Image_name";


  private static Context mContext;
  @SuppressLint("StaticFieldLeak")
  private static PreferenUtil mInstant = null;

  private PreferenUtil(Context context) {
    mContext = context;
  }

  public static PreferenUtil getInstant(Context context) {
    if (mInstant == null) {
      mInstant = new PreferenUtil(context);
      PreferenceManager.getDefaultSharedPreferences(context)
          .registerOnSharedPreferenceChangeListener(mListener);
    }
    return mInstant;
  }


  private static SharedPreferences.OnSharedPreferenceChangeListener mListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
  };


  public void saveImage(String image_name) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
    sharedPreferencesEditor.putString(IMAGENAME, image_name);
    sharedPreferencesEditor.apply();
  }

  public String getImageName() {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    return sharedPreferences.getString(IMAGENAME, "");
  }

  public void savedata(String key, String data) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
    sharedPreferencesEditor.putString(key, data);
    sharedPreferencesEditor.apply();
  }

  public String getData(String key) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    return sharedPreferences.getString(key, "");
  }



}
