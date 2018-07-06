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
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.gideondev.survey.PreferenUtil.PreferenUtil;
import com.gideondev.survey.R;
import com.gideondev.survey.feature.SnackBarUtils;
import com.gideondev.survey.feature.main.view.MainActivity;

public class QCustom5Fragment extends Fragment {

  private Button button_continue, button_previous;
  private TextView textview_q_title;
  private EditText editText_answer;

  public QCustom5Fragment() {
    // Required empty public constructor
  }

  public static QCustom5Fragment newInstance(String param1, String param2) {
    QCustom5Fragment fragment = new QCustom5Fragment();
//    Bundle args = new Bundle();
//    args.putString(ARG_PARAM1, param1);
//    args.putString(ARG_PARAM2, param2);
//    fragment.setArguments(args);
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
        R.layout.fragment_qicustom5, container, false);
    final PreferenUtil preferenUtil = PreferenUtil.getInstant(getContext());
    button_continue = (Button) rootView.findViewById(R.id.button_save);
    button_previous = (Button) rootView.findViewById(R.id.button_prev);
    textview_q_title = (TextView) rootView.findViewById(R.id.textview_q_title);
    showInputMethod();
    editText_answer = (EditText) rootView.findViewById(R.id.editText_answer);
    textview_q_title.setText(getContext().getResources().getString(R.string.q_custom5));
    button_continue.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        custom5 = editText_answer.getText().toString();
        preferenUtil.savedata("c5",editText_answer.getText().toString());
//        if (!custom5.isEmpty()) {
        ((MainActivity) getActivity()).go_to_next();
//        } else {
//          SnackBarUtils.setErrorMsg(getActivity(), "Answer field cannot be empty");
//        }

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

  public void showInputMethod() {
    InputMethodManager imm = (InputMethodManager) getActivity()
        .getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
  }

}
