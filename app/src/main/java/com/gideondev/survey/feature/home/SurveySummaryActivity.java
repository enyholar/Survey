package com.gideondev.survey.feature.home;

import static com.gideondev.survey.feature.main.view.MainActivity.familyMember;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.gideondev.survey.FormModel;
import com.gideondev.survey.R;
import com.gideondev.survey.databases.dao.FormDAO;
import com.gideondev.survey.databases.dao.SqliteDAOFactory;
import com.gideondev.survey.feature.adapters.FamilyAdapter;
import com.gideondev.survey.feature.adapters.SummarySurveyListner;
import com.gideondev.survey.feature.adapters.SurveySummaryAdapter;
import java.util.ArrayList;
import java.util.List;

public class SurveySummaryActivity extends AppCompatActivity {

  private RecyclerView recyclerView;
  private List<FormModel> formModelList = new ArrayList<>();
  private SurveySummaryAdapter adapter;
  private Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_survey_summary);
    iniActionbar();
    recyclerView = (RecyclerView) findViewById(R.id.rec_survey_summary);
    adapter = new SurveySummaryAdapter(this, formModelList, new SummarySurveyListner() {
      @Override
      public void onClick(FormModel model) {
        Intent intent = new Intent(SurveySummaryActivity.this, SummarySurveyDetailsActivity.class);
        intent.putExtra("model",model);
        startActivity(intent);
      }
    });
    recyclerView.setAdapter(adapter);

    initModel();
  }

  private void iniActionbar() {
    toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("Survey list");
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

  private void initModel() {
    FormDAO mFormDao = new FormDAO(this,
        new SqliteDAOFactory(this).getConnection());
    formModelList.addAll(mFormDao.getFormModels());
    adapter.notifyDataSetChanged();
  }
}
