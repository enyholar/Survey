package com.gideondev.survey.base;


import android.app.Application;
import android.support.multidex.MultiDexApplication;
import com.gideondev.survey.internal.di.component.ApplicationComponent;

/**
 * Created by Enny on 29/11/2016.
 */

public class AppApplication
    extends MultiDexApplication {
    private static AppApplication application;
    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

//        initApplication();

    //    AppEventsLogger.activateApp(this);
    }


    private void initFont() {
    }

//    private void initApplication() {
//        mComponent = DaggerApplicationComponent.builder()
//                .applicationModule(new ApplicationModule(this))
//                .build();
//        mComponent.inject(this);
//    }


    public static AppApplication get() {
        return application;
    }





}
