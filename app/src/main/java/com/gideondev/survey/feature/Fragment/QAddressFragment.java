package com.gideondev.survey.feature.Fragment;

import static com.gideondev.survey.feature.main.view.MainActivity.custom3;
import static com.gideondev.survey.feature.main.view.MainActivity.homeAddress;
import static com.gideondev.survey.feature.main.view.MainActivity.latitude;
import static com.gideondev.survey.feature.main.view.MainActivity.longitude;

import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.gideondev.survey.MyLocation;
import com.gideondev.survey.MyLocation.LocationResult;
import com.gideondev.survey.PreferenUtil.PreferenUtil;
import com.gideondev.survey.R;
import com.gideondev.survey.feature.GPSService;
import com.gideondev.survey.feature.ProgressBarHandler;
import com.gideondev.survey.feature.SnackBarUtils;
import com.gideondev.survey.feature.main.view.MainActivity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class QAddressFragment extends Fragment  {

  private Button button_continue, button_previous;
  private TextView textview_q_title;
  private EditText editText_answer;
  private LocationManager locationManager;
  private String address = null;
  private GPSService mGPSService;
  private ProgressBar progressBar;
  private PreferenUtil preferenUtil;

  public QAddressFragment() {
    // Required empty public constructor
  }

  public static QAddressFragment newInstance(String param1, String param2) {
    QAddressFragment fragment = new QAddressFragment();
//    Bundle args = new Bundle();
//    args.putString(ARG_PARAM1, param1);
//    args.putString(ARG_PARAM2, param2);
//    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @SuppressLint("ResourceType")
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    ViewGroup rootView = (ViewGroup) inflater.inflate(
        R.layout.fragment_qaddress, container, false);
     preferenUtil = PreferenUtil.getInstant(getContext());
    button_continue = (Button) rootView.findViewById(R.id.button_next);
    button_previous = (Button) rootView.findViewById(R.id.button_prev);
    textview_q_title = (TextView) rootView.findViewById(R.id.textview_q_title);
    editText_answer = (EditText) rootView.findViewById(R.id.editText_answer);
    progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar_cyclic);
    progressBar.setVisibility(View.VISIBLE);
    showInputMethod();
 //   button_continue.setEnabled(false);
    //   editText_answer.setText(getCurrentDate());
    textview_q_title.setText(getContext().getResources().getString(R.string.q_address));
//    homeAddress
    button_continue.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        homeAddress = editText_answer.getText().toString().trim();
        if (homeAddress != null &&!homeAddress.isEmpty()) {
          preferenUtil.savedata("address",editText_answer.getText().toString());
          ((MainActivity) getActivity()).go_to_next();
        } else {
          SnackBarUtils.setErrorMsg(getActivity(), "Answer field cannot be empty");
        }
      }
    });

    button_previous.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //      Answers.getInstance().put_answer(textview_q_title.getText().toString(), editText_answer.getText().toString().trim());
        ((MainActivity) getActivity()).go_to_prev();
      }
    });
    return rootView;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    initModel();
  }

  public void showInputMethod() {
    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
  }

  private void initModel() {
//    LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
//    if (ActivityCompat.checkSelfPermission(getContext(), permission.ACCESS_FINE_LOCATION)
//        != PackageManager.PERMISSION_GRANTED
//        && ActivityCompat.checkSelfPermission(getContext(), permission.ACCESS_COARSE_LOCATION)
//        != PackageManager.PERMISSION_GRANTED) {
//      // TODO: Consider calling
//      //    ActivityCompat#requestPermissions
//      // here to request the missing permissions, and then overriding
//      //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//      //                                          int[] grantResults)
//      // to handle the case where the user grants the permission. See the documentation
//      // for ActivityCompat#requestPermissions for more details.
//      return;
//    }
//    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//     longitude = location.getLongitude();
//     latitude = location.getLatitude();

    LocationResult locationResult = new LocationResult(){
      @Override
      public void gotLocation(Location location){
        //Got the location!
        if(location != null){
          longitude = location.getLongitude();
          latitude = location.getLatitude();
          preferenUtil.savedata("long",String.valueOf(location.getLongitude()));
          preferenUtil.savedata("lat",String.valueOf(location.getLatitude()));
          address = getCompleteAddressString(latitude, longitude);
        }

        getActivity().runOnUiThread(new Runnable() {

          @Override
          public void run() {

            if (editText_answer != null) {
              if (address != null && !address.trim().isEmpty()){
                preferenUtil.savedata("address",editText_answer.getText().toString());
                editText_answer.setText(address);
              }

            }

//        if (button_continue != null){
//          button_continue.setEnabled(true);
//        }
            if (progressBar != null) {
              progressBar.setVisibility(View.GONE);
            }

          }
        });


      }
    };
    MyLocation myLocation = new MyLocation();
    myLocation.getLocation(getContext(), locationResult);
   // lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);

  }

  private final LocationListener locationListener = new LocationListener() {
    public void onLocationChanged(Location location) {
      longitude = location.getLongitude();
      latitude = location.getLatitude();
      address = getCompleteAddressString(latitude, longitude);
      if (editText_answer != null) {
        editText_answer.setText(address);
      }

      if (button_continue != null){
        button_continue.setEnabled(true);
      }
      if (progressBar != null) {
        progressBar.setVisibility(View.GONE);
      }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
  };



//  @Override
//  public void onLocationChanged(Location location) {
//    double latitude = location.getLatitude();
//    double longitude = location.getLongitude();
//    MainActivity.longitude = location.getLongitude();
//    MainActivity.latitude = location.getLatitude();
//    //  address = mGPSService.getLocationAddress(latitude,longitude);
//    address = getCompleteAddressString(latitude, longitude);
//    if (editText_answer != null) {
//      editText_answer.setText(address);
//    }
//
//    if (button_continue != null){
//      button_continue.setEnabled(true);
//    }
//    if (progressBar != null) {
//      progressBar.setVisibility(View.GONE);
//    }
//  }

//  @Override
//  public void onStatusChanged(String provider, int status, Bundle extras) {
//
//  }
//
//  @Override
//  public void onProviderEnabled(String provider) {
//
//  }
//
//  @Override
//  public void onProviderDisabled(String provider) {
//
//  }

  private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
    String strAdd = "";
    if (getContext() != null){
      Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
      try {
        List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
        if (addresses != null) {
          Address returnedAddress = addresses.get(0);
          StringBuilder strReturnedAddress = new StringBuilder("");

          for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
            strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
          }
          strAdd = strReturnedAddress.toString();
          Log.w("My Current loction ", strReturnedAddress.toString());
        } else {
          Log.w("My Current  address", "No Address returned!");
        }
      } catch (Exception e) {
        e.printStackTrace();
        Log.w("My  loction address", "Canont get Address!");
      }
    }
    return strAdd;
  }
}
