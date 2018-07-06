package com.gideondev.survey.feature.main.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.gideondev.survey.FormModel;
import com.gideondev.survey.R;
import com.gideondev.survey.base.BaseActionbarActivity;
import com.gideondev.survey.base.presenter.Presenter;
import com.gideondev.survey.databases.dao.FormDAO;
import com.gideondev.survey.databases.dao.SqliteDAOFactory;
import com.gideondev.survey.databinding.ActivityMainBinding;
import com.gideondev.survey.feature.Fragment.QAddressFragment;
import com.gideondev.survey.feature.Fragment.QCustom1Fragment;
import com.gideondev.survey.feature.Fragment.QCustom2Fragment;
import com.gideondev.survey.feature.Fragment.QCustom3Fragment;
import com.gideondev.survey.feature.Fragment.QCustom4Fragment;
import com.gideondev.survey.feature.Fragment.QCustom5Fragment;
import com.gideondev.survey.feature.Fragment.QFamilyMemberFragment;
import com.gideondev.survey.feature.Fragment.QMultimediaFragment;
import com.gideondev.survey.feature.Fragment.QNameOfPersonInterviewedFragment;
import com.gideondev.survey.feature.Fragment.QNumOfCattleCurrentlyBeingMilkedFragment;
import com.gideondev.survey.feature.Fragment.QNumOfCattleDerivedFromHeiferFragment;
import com.gideondev.survey.feature.Fragment.QNumOfCattleOwnedFragment;
import com.gideondev.survey.feature.Fragment.QSurveyIdFragment;
import com.gideondev.survey.feature.Fragment.QVisitDateFragment;
import com.gideondev.survey.feature.Fragment.QFamilyNameFragment;
import com.gideondev.survey.feature.Fragment.QVolunteerNameFragment;
import com.gideondev.survey.feature.adapters.AdapterFragmentQ;
import com.gideondev.survey.feature.home.HomePageActivity;
import com.gideondev.survey.feature.main.presenter.view.MainPresenter;
import com.gideondev.survey.feature.main.presenter.view.MainView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class MainActivity extends BaseActionbarActivity implements MainView {

  MainPresenter mPresenter;
  private ActivityMainBinding binding;
  private LocationManager locationManager;
  private ViewPager mPager;
  public static String familyName = null;
  public static String family_number = null;
  public static String dateVisited = null;
  public static String homeAddress = null;
  public static String volunteerName = null;
  public static String numOfCattleOwned = null;
  public static String numOfcattleheifer = null;
  public static String numOfCattleMilked = null;
  public static String custom1 = null;
  public static String custom2 = null;
  public static String custom3 = null;
  public static String custom4 = null;
  public static String custom5 = null;
  public static String nameOfpersonInterviewed = null;
  public static String surveyId = null;
  public static Double latitude = null;
  public static Double longitude = null;
  public static List<String> familyMember = new ArrayList<>();
  private FormDAO mFormDao;

  @Override
  public void initView() {

  }

  @Override
  public void initModel() {
    mFormDao = new FormDAO(getContext(),
        new SqliteDAOFactory(getContext()).getConnection());
//    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
  }

  @Override
  public Presenter getPresenter() {
    return mPresenter;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    iniActionbar();
    setUpFragment();
    initModel();
  }

  private void iniActionbar() {
  Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("Question");
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  private void setUpFragment() {
    final ArrayList<Fragment> arraylist_fragments = new ArrayList<>();

    QSurveyIdFragment frag = new QSurveyIdFragment();
    arraylist_fragments.add(frag);

    QVolunteerNameFragment fragVolunter = new QVolunteerNameFragment();
    arraylist_fragments.add(fragVolunter);

    QVisitDateFragment fragAge = new QVisitDateFragment();
    arraylist_fragments.add(fragAge);

    QNameOfPersonInterviewedFragment fragInterviewed = new QNameOfPersonInterviewedFragment();
    arraylist_fragments.add(fragInterviewed);

    QAddressFragment fragAddress = new QAddressFragment();
    arraylist_fragments.add(fragAddress);

    QFamilyNameFragment fragFamilyName = new QFamilyNameFragment();
    arraylist_fragments.add(fragFamilyName);

    QFamilyMemberFragment fragFAM = new QFamilyMemberFragment();
    arraylist_fragments.add(fragFAM);

    QNumOfCattleOwnedFragment fragCattleOwned = new QNumOfCattleOwnedFragment();
    arraylist_fragments.add(fragCattleOwned);

    QNumOfCattleCurrentlyBeingMilkedFragment fragCattleBinMilked = new QNumOfCattleCurrentlyBeingMilkedFragment();
    arraylist_fragments.add(fragCattleBinMilked);

    QNumOfCattleDerivedFromHeiferFragment fragCattleHeifer = new QNumOfCattleDerivedFromHeiferFragment();
    arraylist_fragments.add(fragCattleHeifer);

    QCustom1Fragment fragCustom1 = new QCustom1Fragment();
    arraylist_fragments.add(fragCustom1);

    QCustom2Fragment fragCustom2 = new QCustom2Fragment();
    arraylist_fragments.add(fragCustom2);

    QCustom3Fragment fragCustom3 = new QCustom3Fragment();
    arraylist_fragments.add(fragCustom3);

    QCustom4Fragment fragCustom4 = new QCustom4Fragment();
    arraylist_fragments.add(fragCustom4);

    QCustom5Fragment fragCustom5 = new QCustom5Fragment();
    arraylist_fragments.add(fragCustom5);

    QMultimediaFragment fragMultimedia = new QMultimediaFragment();
    arraylist_fragments.add(fragMultimedia);
    mPager = (ViewPager) findViewById(R.id.pager);
    AdapterFragmentQ mPagerAdapter = new AdapterFragmentQ(getSupportFragmentManager(),
        arraylist_fragments);
    mPager.setAdapter(mPagerAdapter);


  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        Intent homeIntent = new Intent(this, HomePageActivity.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
        finish();
    }
    return super.onOptionsItemSelected(item);
  }

  public void saveData(String familyName,
      Double latit,
      Double longit,
      String dateVisited,
      String homeAddress,
      String numOfFamMem,
      String volunteerName,
      String numOfCattleOwned,
      String numOfcattleheifer,
      String numOfCattleMilked,
      String nameOfpersonInterviewed,
      List<String> familyMember, String cus1, String cus2, String cus3, String cus4, String cus5,String surveyID) {
    FormDAO mFormDao = new FormDAO(this,
        new SqliteDAOFactory(this).getConnection());
    String joined = null;
    if (familyMember != null && familyMember.size() > 0){

      joined =   StringUtils.join(familyMember,",");
    //  joined = String.join(",", familyMember);

    }
    FormModel model = new FormModel(familyName,latit,longit,homeAddress,dateVisited,numOfFamMem,volunteerName,nameOfpersonInterviewed
    ,numOfCattleOwned,numOfCattleMilked,numOfcattleheifer,joined,cus1,cus2,cus3,cus4,cus5,surveyID);
    mFormDao.addForm(model);
  }

  public void go_to_next() {
    mPager.setCurrentItem(mPager.getCurrentItem() + 1);
  }

  public void go_to_prev() {
    mPager.setCurrentItem(mPager.getCurrentItem() - 1);
  }

  @Override
  public void onBackPressed() {
    if (mPager.getCurrentItem() == 0) {
      super.onBackPressed();
    } else {
      mPager.setCurrentItem(mPager.getCurrentItem() - 1);
    }
  }

  @Override
  protected void injectInjector() {

  }

  @Override
  public Activity getActivity() {
    return this;
  }

  @Override
  public void showLineLoading() {

  }

  @Override
  public void hideLineLoading() {

  }

  @Override
  public void showError(String message) {

  }

  @Override
  public Context getContext() {
    return this;
  }


  public String getLocationFromAddress(String strAddress) {

    Geocoder coder = new Geocoder(getContext());
    List<Address> address;

    try {
      address = coder.getFromLocationName(strAddress, 1);
      if (address == null) {
        return null;
      }
      Address location = address.get(0);
      double lat = location.getLatitude();
      double lng = location.getLongitude();

      return lat + "," + lng;
    } catch (Exception e) {
      return null;
    }
  }

  public static String getLocationName(Activity activity, Location location) {

    String name = null;

    if (location != null) {
      Geocoder geocoder = new Geocoder(activity);
      try {
        Address address = geocoder
            .getFromLocation(location.getLatitude(), location.getLongitude(), 1).get(0);
        name = address.getThoroughfare();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return name;
  }
}
