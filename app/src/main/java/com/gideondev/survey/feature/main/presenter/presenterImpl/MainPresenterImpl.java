package com.gideondev.survey.feature.main.presenter.presenterImpl;


import android.support.annotation.NonNull;
import com.gideondev.survey.databases.dao.FormDAO;
import com.gideondev.survey.databases.dao.SqliteDAOFactory;
import com.gideondev.survey.feature.main.presenter.view.MainPresenter;
import com.gideondev.survey.feature.main.presenter.view.MainView;

/**
 * Created by ${ENNY} on 10/19/2017.
 */

public class MainPresenterImpl implements MainPresenter {

  private MainView mView;
  private FormDAO mFormDao;


  @Override
  public void resume() {

  }

  @Override
  public void pause() {

  }

  public void saveSurveyTaken(String familyName, Long latitude, Long longitude,
      String address,
      String visitDate,
      int numberOfFamilyMember,
      String volunteerName,
      String nameOfPersonInterviewed,
      int numberOfCattleOwnedByFamily,
      int numberOfCattleCurrentlyBeingMilked,
      int numberOfCattleDerived) {

  }

  @Override
  public void destroy() {

  }

  @Override
  public void setView(@NonNull MainView view) {
    this.mView = view;
  }

  @Override
  public void Start() {
    mFormDao = new FormDAO(mView.getContext(),
        new SqliteDAOFactory(mView.getContext()).getConnection());
  }
}
