package com.gideondev.survey.PreferenUtil;

import android.content.SharedPreferences;

public class AppPreferences {
    public static final int IMGUR = 4000;
    private static final SharedPreferences prefs = null;

    static {
      //  prefs = PreferenceManager.getDefaultSharedPreferences(AppApplication.get());
    }

    public static int getInt(String key, int defValue) {
        return prefs.getInt(key, defValue);
    }


    public static void putInt(String key, int value) {
        prefs.edit().putInt(key, value).apply();
    }
}
