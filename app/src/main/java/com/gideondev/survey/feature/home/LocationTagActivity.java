package com.gideondev.survey.feature.home;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.gideondev.survey.FormModel;
import com.gideondev.survey.databases.dao.FormDAO;
import com.gideondev.survey.databases.dao.SqliteDAOFactory;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.gideondev.survey.R;
import java.util.ArrayList;
import java.util.List;

public class LocationTagActivity extends FragmentActivity  implements OnMapReadyCallback {

  private GoogleMap googleMap;
  private MapView mapView;
  private SupportMapFragment mapFragment;
  private List<FormModel> formModelList = new ArrayList<>();
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_location_tag);
    initModel();
   // mapView = (MapView) findViewById(R.id.map);
    mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.map);
//    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//        .findFragmentById(R.id.googleMap);
    mapFragment.getMapAsync(this);
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinate, 17));
// *** Marker (Loop)
    if (formModelList != null && !formModelList.isEmpty() && formModelList.size() > 0){
      for (int i = 0; i < formModelList.size(); i++) {
//        Latitude = Double.parseDouble(location.get(i).get("Latitude").toString());
//        Longitude = Double.parseDouble(location.get(i).get("Longitude").toString());
//        String name = location.get(i).get("LocationName").toString();
        MarkerOptions marker = new MarkerOptions().position(new LatLng(formModelList.get(i).getLatitude(), formModelList.get(i).getLongitude())).title(formModelList.get(i).getAddress());
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_person_pin_circle_red_a400_24dp));
        googleMap.addMarker(marker);
      }
    }

  }

  @Override
  public void onBackPressed() {
    finish();
    super.onBackPressed();
  }

  private void initModel() {
    FormDAO mFormDao = new FormDAO(this,
        new SqliteDAOFactory(this).getConnection());
    formModelList.addAll(mFormDao.getFormModels());
  }
}
