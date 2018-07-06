package com.gideondev.survey.feature.Fragment;

import static com.gideondev.survey.feature.main.view.MainActivity.custom1;
import static com.gideondev.survey.feature.main.view.MainActivity.custom2;
import static com.gideondev.survey.feature.main.view.MainActivity.custom3;
import static com.gideondev.survey.feature.main.view.MainActivity.custom4;
import static com.gideondev.survey.feature.main.view.MainActivity.custom5;
import static com.gideondev.survey.feature.main.view.MainActivity.dateVisited;
import static com.gideondev.survey.feature.main.view.MainActivity.familyMember;
import static com.gideondev.survey.feature.main.view.MainActivity.familyName;
import static com.gideondev.survey.feature.main.view.MainActivity.family_number;
import static com.gideondev.survey.feature.main.view.MainActivity.homeAddress;
import static com.gideondev.survey.feature.main.view.MainActivity.latitude;
import static com.gideondev.survey.feature.main.view.MainActivity.longitude;
import static com.gideondev.survey.feature.main.view.MainActivity.nameOfpersonInterviewed;
import static com.gideondev.survey.feature.main.view.MainActivity.numOfCattleMilked;
import static com.gideondev.survey.feature.main.view.MainActivity.numOfCattleOwned;
import static com.gideondev.survey.feature.main.view.MainActivity.numOfcattleheifer;
import static com.gideondev.survey.feature.main.view.MainActivity.surveyId;
import static com.gideondev.survey.feature.main.view.MainActivity.volunteerName;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.gideondev.survey.MultimediaModel;
import com.gideondev.survey.PreferenUtil.PreferenUtil;
import com.gideondev.survey.R;
import com.gideondev.survey.feature.SnackBarUtils;
import com.gideondev.survey.feature.adapters.FamilyAdapter;
import com.gideondev.survey.feature.adapters.MultimediaAttachAdapter;
import com.gideondev.survey.feature.adapters.MultimediaAttachListner;
import com.gideondev.survey.feature.main.view.MainActivity;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QMultimediaFragment extends Fragment {
  private Button button_continue,button_previous;
  private TextView textview_q_title;
  private EditText editText_answer;
  private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;
  ImageView buttonCamera , btnVideo;
  private MultimediaAttachAdapter adapter;
  private List<MultimediaModel> mArrayList = new ArrayList<>();
  private RecyclerView mRecyclerview;
  private PreferenUtil mPrefenUtils;

  public QMultimediaFragment() {
    // Required empty public constructor
  }

  public static QMultimediaFragment newInstance(String param1, String param2) {
    QMultimediaFragment fragment = new QMultimediaFragment();
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {

    }
  }

  @SuppressLint("ResourceType")
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    ViewGroup rootView = (ViewGroup) inflater.inflate(
        R.layout.fragment_multimedia, container, false);
     mPrefenUtils = PreferenUtil.getInstant(getContext());
    mRecyclerview = (RecyclerView) rootView.findViewById(R.id.my_list);
    button_continue = (Button) rootView.findViewById(R.id.button_next);
    button_previous = (Button) rootView.findViewById(R.id.button_prev);
    buttonCamera = (ImageView) rootView.findViewById(R.id.btn_camera);
    btnVideo = (ImageView) rootView.findViewById(R.id.btn_video);
    textview_q_title = (TextView) rootView.findViewById(R.id.textview_q_title);
    textview_q_title.setText(getContext().getResources().getString(R.string.q_image));

    buttonCamera.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String parent_path = Environment.getExternalStorageDirectory().getPath() + "/Survey/";
        File fileParent = new File(parent_path);
        if (!fileParent.exists()) {
          fileParent.mkdirs();
        }
        File folder = new File(Environment.getExternalStorageDirectory() + "/Survey/Gallery/");
        if (!folder.exists()) {
          folder.mkdirs();
        }

        File folderImage = new File(Environment.getExternalStorageDirectory() + "/Survey/Gallery/");
        if (!folderImage.exists()) {
          folderImage.mkdirs();
        }
        if(!surveyId.isEmpty()){
          File folderImageEnd = new File(Environment.getExternalStorageDirectory() + "/Survey/Gallery/" + surveyId);
          if (!folderImageEnd.exists()) {
            folderImageEnd.mkdirs();
          }
          String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
          mPrefenUtils.saveImage(timeStamp +".jpg");
          File image = new File(folderImageEnd, timeStamp + ".jpg");
          Uri uriSavedImage;
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uriSavedImage = FileProvider.getUriForFile(getContext(),
                "com.gideondev.survey.fileProvider",
               image);
          } else {
            uriSavedImage = Uri.fromFile(image);
          }
//          Uri uriSavedImage = Uri.fromFile(image);
          intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uriSavedImage);
          startActivityForResult(intent,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }

      }
    });

    buttonCamera.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String parent_path = Environment.getExternalStorageDirectory().getPath() + "/Survey/";
        File fileParent = new File(parent_path);
        if (!fileParent.exists()) {
          fileParent.mkdirs();
        }
        File folder = new File(Environment.getExternalStorageDirectory() + "/Survey/Gallery/");
        if (!folder.exists()) {
          folder.mkdirs();
        }

        File folderImage = new File(Environment.getExternalStorageDirectory() + "/Survey/Gallery/");
        if (!folderImage.exists()) {
          folderImage.mkdirs();
        }
        if(!surveyId.isEmpty()){
          File folderImageEnd = new File(Environment.getExternalStorageDirectory() + "/Survey/Gallery/" + surveyId);
          if (!folderImageEnd.exists()) {
            folderImageEnd.mkdirs();
          }
          String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
          mPrefenUtils.saveImage(timeStamp +".jpg");
          File image = new File(folderImageEnd, timeStamp + ".jpg");
          Uri uriSavedImage;
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uriSavedImage = FileProvider.getUriForFile(getContext(),
                "com.gideondev.survey.fileProvider",
                image);
          } else {
            uriSavedImage = Uri.fromFile(image);
          }
        //  Uri uriSavedImage = Uri.fromFile(image);
          if(uriSavedImage != null){
            intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uriSavedImage);
            startActivityForResult(intent,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
          }

        }

      }
    });

    btnVideo.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {

        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        String parent_path = Environment.getExternalStorageDirectory().getPath() + "/Survey/";
        File fileParent = new File(parent_path);
        if (!fileParent.exists()) {
          fileParent.mkdirs();
        }
        File folder = new File(Environment.getExternalStorageDirectory() + "/Survey/Gallery/");
        if (!folder.exists()) {
          folder.mkdirs();
        }

        File folderImage = new File(Environment.getExternalStorageDirectory() + "/Survey/Gallery/");
        if (!folderImage.exists()) {
          folderImage.mkdirs();
        }
        if(surveyId != null &&!surveyId.isEmpty()){
          File folderImageEnd = new File(Environment.getExternalStorageDirectory() + "/Survey/Gallery/" + surveyId);
          if (!folderImageEnd.exists()) {
            folderImageEnd.mkdirs();
          }
          String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
          mPrefenUtils.saveImage(timeStamp +".mp4");
          File image = new File(folderImageEnd, timeStamp + ".mp4");
          Uri uriSavedImage;
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uriSavedImage = FileProvider.getUriForFile(getContext(),
                "com.gideondev.survey.fileProvider",
                image);
          } else {
            uriSavedImage = Uri.fromFile(image);
          }
          if(uriSavedImage != null){
            intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uriSavedImage);
            startActivityForResult(intent,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
          }

        }

      }
    });
    button_continue.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
//        if (familyName != null &&!familyName.isEmpty()&& dateVisited != null && !dateVisited.isEmpty()
//            && homeAddress != null && !homeAddress.isEmpty() && volunteerName != null && !volunteerName.isEmpty()
//            && numOfCattleOwned != null && !numOfCattleOwned.isEmpty()&& numOfcattleheifer != null && !numOfcattleheifer.isEmpty()
//            && numOfCattleMilked != null && !numOfCattleMilked.isEmpty() && nameOfpersonInterviewed != null &&  !nameOfpersonInterviewed.isEmpty()
//            && surveyId != null && !surveyId.isEmpty()){
//          ((MainActivity) getActivity()).saveData(familyName,latitude,longitude,dateVisited,homeAddress,family_number,volunteerName
//              ,numOfCattleOwned,numOfcattleheifer,numOfCattleMilked,nameOfpersonInterviewed,familyMember,custom1,custom2,custom3,custom4,custom5,surveyId);


        ((MainActivity) getActivity()).saveData(
            mPrefenUtils.getData("fname"),
            latitude,
            longitude,
            mPrefenUtils.getData("date"),
            mPrefenUtils.getData("address"),
            mPrefenUtils.getData("fnum"),
            mPrefenUtils.getData("vname")
            ,mPrefenUtils.getData("cowned"),
            mPrefenUtils.getData("cheif"),
            mPrefenUtils.getData("cmilk"),
            mPrefenUtils.getData("pinterview"),
            familyMember,
            mPrefenUtils.getData("c1"),
            mPrefenUtils.getData("c2"),
            mPrefenUtils.getData("c3"),
            mPrefenUtils.getData("c4"),
            mPrefenUtils.getData("c5"),
            mPrefenUtils.getData("id"));
         if(familyMember != null && !familyMember.isEmpty() && familyMember.size()>0){
           familyMember.clear();
         }
          family_number = null;
         if (getActivity() != null){
           getActivity().finish();
         }

//        }else {
//          SnackBarUtils.setErrorMsg(getActivity(),"Answer field cannot be empty");
//        }
      }
    });

    button_previous.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        //      Answers.getInstance().put_answer(textview_q_title.getText().toString(), editText_answer.getText().toString().trim());
        ((MainActivity) getActivity()).go_to_prev();
      }
    });
    return rootView;
  }


  private void initModel(){
    adapter = new MultimediaAttachAdapter(getContext(), mArrayList, new MultimediaAttachListner() {
      @Override
      public void onClick(int pos, MultimediaModel model) {
        File fdelete = new File(model.getPath(), model.getName());
        if (fdelete.exists()) {
          if (fdelete.delete()) {
            mArrayList.remove(pos);
            adapter.notifyDataSetChanged();
            Toast.makeText(getContext(), "deleted successfully", Toast.LENGTH_LONG).show();
          } else {
            Toast.makeText(getContext(), " fail", Toast.LENGTH_LONG).show();
          }

      }
    }
    });
    mRecyclerview.setAdapter(adapter);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    initModel();
    super.onActivityCreated(savedInstanceState);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
      if (resultCode == Activity.RESULT_OK) {
        String name =  mPrefenUtils.getImageName();
        File file = new File(Environment.getExternalStorageDirectory().getPath() +"/Survey/Gallery/" + surveyId + "/", name);
        String dir = file.getParent();
        MultimediaModel multimediaModel = new MultimediaModel(mPrefenUtils.getImageName(),dir);
        mArrayList.add(multimediaModel);
        adapter.notifyDataSetChanged();
      }
    }
  }

}
