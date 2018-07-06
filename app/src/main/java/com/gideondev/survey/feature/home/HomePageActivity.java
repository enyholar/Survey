package com.gideondev.survey.feature.home;

import android.Manifest;
import android.Manifest.permission;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Environment;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import com.gideondev.survey.FormModel;
import com.gideondev.survey.R;
import com.gideondev.survey.databases.dao.FormDAO;
import com.gideondev.survey.databases.dao.SqliteDAOFactory;
import com.gideondev.survey.feature.SQLiteToExcel;
import com.gideondev.survey.feature.main.view.MainActivity;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {
  private DrawerLayout mDrawerLayout;
  Handler navDrawerRunnable = new Handler();
  private ActionBarDrawerToggle mDrawerToggle;
  private Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home_page);
    mDrawerLayout = findViewById(R.id.draw);
    iniActionbar();
    TextView txtField = findViewById(R.id.txt1);
    final NavigationView navView = findViewById(R.id.nav_view);
    Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/welcome.ttf");
    txtField.setTypeface(tf, Typeface.BOLD);
    navDrawerRunnable.postDelayed(new Runnable() {
      @Override
      public void run() {
        setupDrawerContent(navView);
        setupNavigationIcons(navView);
      }
    }, 700);
    initNavigationDrawer();
    if (!hasPermissions(this, permission.ACCESS_FINE_LOCATION) && !hasPermissions(this, permission.ACCESS_COARSE_LOCATION) && !hasPermissions(this, permission.WRITE_EXTERNAL_STORAGE)){
      requestPermission();
    }
     findViewById(R.id.btn_new_survey).setOnClickListener(new OnClickListener() {
       @Override
       public void onClick(View v) {
         Intent intent = new Intent(HomePageActivity.this, MainActivity.class);
         startActivity(intent);
       }
     });

    findViewById(R.id.btn_exit).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });

    findViewById(R.id.btn_view_summary).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(HomePageActivity.this, SurveySummaryActivity.class);
        startActivity(intent);
      }
    });

    findViewById(R.id.btn_show_map).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(HomePageActivity.this, LocationTagActivity.class);
        startActivity(intent);
      }
    });


    findViewById(R.id.btn_convert).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/Survey/";
        File file = new File(directory_path);
        if (!file.exists()) {
          file.mkdirs();
        }
        // Export SQLite DB as EXCEL FILE
        SQLiteToExcel sqliteToExcel = new SQLiteToExcel(getApplicationContext(), "survey.DB", directory_path);
        sqliteToExcel.exportAllTables("surveySummary.xls", new SQLiteToExcel.ExportListener() {
          @Override
          public void onStart() {

          }

          @Override
          public void onCompleted(String filePath) {
            Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
//            Utils.showSnackBar(view, "Successfully Exported");
          }

          @Override
          public void onError(Exception e) {

          }
        });
      }
    });
//        SQLiteToExcel sqliteToExcel = new SQLiteToExcel(getApplicationContext(), "helloworld.db", "");
//        sqliteToExcel..exportSingleTable();
//      }
//    });

  }

  @Override
  public void onBackPressed() {
    if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
      mDrawerLayout.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }

  }

  private void iniActionbar() {
     toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("Home");

  }

  private void initNavigationDrawer() {
    mDrawerToggle =
        new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
            R.string.app_name) {

          @Override
          public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
          }

          @Override
          public void onDrawerClosed(View drawerView) {
            super.onDrawerClosed(drawerView);
          }
        }; // Drawer Toggle Object Made
    mDrawerLayout.setDrawerListener(mDrawerToggle);
    mDrawerToggle.syncState();
    
  }


  private void setupDrawerContent(NavigationView navigationView) {
    navigationView.setNavigationItemSelectedListener(
        new NavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(final MenuItem menuItem) {
            updatePosition(menuItem);
            return true;

          }
        });
  }

  private void setupNavigationIcons(NavigationView navigationView) {
      navigationView.getMenu().findItem(R.id.nav_survey).setIcon(R.drawable.ic_question_answer_black_24dp);
      navigationView.getMenu().findItem(R.id.nav_map)
          .setIcon(R.drawable.ic_map_black_24dp);
      navigationView.getMenu().findItem(R.id.nav_export_excel).setIcon(R.drawable.ic_library_books_black_24dp);
      navigationView.getMenu().findItem(R.id.nav_summary).setIcon(R.drawable.ic_library_books_black_24dp);
      navigationView.getMenu().findItem(R.id.nav_exit).setIcon(R.drawable.ic_exit_to_app_black_24dp);

    }


  private void updatePosition(final MenuItem menuItem) {
    //  runnable = null;

    switch (menuItem.getItemId()) {
      case R.id.nav_survey:
        Intent intentSurvey = new Intent(HomePageActivity.this, MainActivity.class);
        startActivity(intentSurvey);
        break;
      case R.id.nav_summary:
        Intent intent = new Intent(HomePageActivity.this, SurveySummaryActivity.class);
        startActivity(intent);
        break;
      case R.id.nav_map:
        Intent intentMap = new Intent(HomePageActivity.this, LocationTagActivity.class);
        startActivity(intentMap);
        break;
      case R.id.nav_export_excel:
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/Survey/";
        File file = new File(directory_path);
        if (!file.exists()) {
          file.mkdirs();
        }
        // Export SQLite DB as EXCEL FILE
        SQLiteToExcel sqliteToExcel = new SQLiteToExcel(getApplicationContext(), "survey.DB", directory_path);
        sqliteToExcel.exportAllTables("surveySummary.xls", new SQLiteToExcel.ExportListener() {
          @Override
          public void onStart() {

          }

          @Override
          public void onCompleted(String filePath) {
            Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
//            Utils.showSnackBar(view, "Successfully Exported");
          }

          @Override
          public void onError(Exception e) {

          }
        });

        break;
      case R.id.nav_exit:
        finish();
        break;


    }

//    if (runnable != null) {
//      menuItem.setChecked(true);
//      mDrawerLayout.closeDrawers();
//      Handler handler = new Handler();
//      handler.postDelayed(new Runnable() {
//        @Override
//        public void run() {
//          runnable.run();
//        }
//      }, 350);
//    }
  }


  private void requestPermission(){
    ActivityCompat.requestPermissions(HomePageActivity.this,
        new String[]{permission.ACCESS_FINE_LOCATION,permission.ACCESS_COARSE_LOCATION,permission.WRITE_EXTERNAL_STORAGE},
        1);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode,
      String permissions[], int[] grantResults) {
    switch (requestCode) {
      case 1: {

        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

          // permission was granted, yay! Do the
          // contacts-related task you need to do.
        } else {

          // permission denied, boo! Disable the
          // functionality that depends on this permission.
          Toast.makeText(HomePageActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
        return;
      }

      // other 'case' lines to check for other
      // permissions this app might request
    }
  }

  public static boolean hasPermissions(Context context, String... permissions) {
    if (context != null && permissions != null) {
      for (String permission : permissions) {
        if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
          return false;
        }
      }
    }
    return true;
  }

}
