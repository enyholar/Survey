package com.gideondev.survey.feature.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import com.gideondev.survey.FormModel;
import com.gideondev.survey.R;
import com.gideondev.survey.databinding.ActivitySummarySurveyDetailsBinding;

public class SummarySurveyDetailsActivity extends AppCompatActivity {

  private  FormModel formModel;
  private ActivitySummarySurveyDetailsBinding binding;
  Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_summary_survey_details);
    iniActionbar();
    setSupportActionBar(toolbar);
    getData();
    initSetUpView(formModel);
  }

  private void getData(){
    Intent myIntent = getIntent(); // this is just for example purpose
   formModel = (FormModel) myIntent.getSerializableExtra("model");
  }

  private void iniActionbar() {
    toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("Survey details");
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        Intent homeIntent = new Intent(this, SurveySummaryActivity.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
        finish();
    }
    return super.onOptionsItemSelected(item);
  }

  private void initSetUpView(FormModel formModel) {
    if (formModel != null){
      binding.txtDetailsAddress.setText("Address:" + " " +formModel.getAddress());
      binding.txtDetailsCattledOwned.setText("Cattle owned by family:" + " " +formModel.getNumberOfCattleOwnedByFamily());
      binding.txtDetailsDateVisited.setText("Date survey taken:" + " " +formModel.getVisitDate());
      binding.txtDetailsCattleHeifer.setText("Number of cattle heifer:" + " " +formModel.getNumberOfCattleDerived());
      binding.txtDetailsMemberNumber.setText("Number of family member:" + " " +formModel.getNumberOfFamilyMember());
      binding.txtDetailsPersonInterview.setText("Name of person interview:" + " " +formModel.getNameOfPersonInterviewed());
      binding.txtDetailsVolunteerName.setText("Volunteer name:" + " " +formModel.getVolunteerName());
      binding.txtDetailsFamilyName.setText("Family name:" + " " +formModel.getFamilyName());
      binding.txtDetailsCattleMilked.setText("Number of cattle milked:" + " " +formModel.getNumberOfCattleCurrentlyBeingMilked());
      binding.txtDetailsMemberFamily.setText("Family information:" + " " +formModel.getFamilyInfo());
    }
  }

}
