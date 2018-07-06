package com.gideondev.survey.internal.di.module;

import android.app.Activity;

/**
 * Created by Enny  on 29/11/2016.
 */
//@Module(includes = ApplicationModule.class)
public class ProjectModule {

  private final Activity activity;

  public ProjectModule(Activity activity) {
    this.activity = activity;
  }

//  @Provides
//  @PerActivity
//  Activity activity() {
//    return this.activity;
//  }
//
//  @Provides
//  HomePresenter provideHomePresenter() {
//    return new HomePresenterImpl();
//  }
//
//  @Provides
//  RadioListPresenter provideRadioListPresenter() {
//    return new RadioListPresenterImpl();
//  }
//
//
//  @Provides
//  PlayingRadioPresenter providePlayingRadioPresenter() {
//    return new PlayingRadioPresenterImpl();
//  }
//
//  @Provides
//  RadioFavoriteListPresenter provideRadioFavoriteListPresenter() {
//    return new RadioFavoriteListPresenterImpl();
//  }
//
//  @Provides
//  UserResetPasswordPresenter provideUserResetPasswordPresenter() {
//    return new UserResetPasswordPresenterImpl();
//  }
//
//  @Provides
//  UserLoginPresenter provideUserLoginPresenter() {
//    return new UserLoginPresenterImpl();
//  }
//
//  @Provides
//  UserSignUpPresenter provideUserSignUpPresenter() {
//    return new UserSignUpPresenterImpl();
//  }

}

