package com.gideondev.survey.feature.Fragment;

import static com.gideondev.survey.feature.main.view.MainActivity.nameOfpersonInterviewed;
import static com.gideondev.survey.feature.main.view.MainActivity.surveyId;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.gideondev.survey.PreferenUtil.PreferenUtil;
import com.gideondev.survey.R;
import com.gideondev.survey.feature.SnackBarUtils;
import com.gideondev.survey.feature.main.view.MainActivity;

public class QSurveyIdFragment extends Fragment {
  private Button button_continue,button_previous;
  private TextView textview_q_title;
  private EditText editText_answer;

  public QSurveyIdFragment() {
    // Required empty public constructor
  }

  public static QSurveyIdFragment newInstance(String param1, String param2) {
    QSurveyIdFragment fragment = new QSurveyIdFragment();
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
        R.layout.fragment_qsurvey_id, container, false);
    final PreferenUtil preferenUtil = PreferenUtil.getInstant(getContext());
    button_continue = (Button) rootView.findViewById(R.id.button_next);
    button_previous = (Button) rootView.findViewById(R.id.button_prev);
    textview_q_title = (TextView) rootView.findViewById(R.id.textview_q_title);
    editText_answer = (EditText) rootView.findViewById(R.id.editText_answer);
 //   editText_answer.requestFocus();
//   getActivity().getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_VISIBLE);

    showInputMethod();
//    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//    imm.showSoftInput(editText_answer, InputMethodManager.SHOW_IMPLICIT);
    textview_q_title.setText(getContext().getResources().getString(R.string.q_survey_id));
    button_continue.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        surveyId = editText_answer.getText().toString().trim();
        if (surveyId != null &&!surveyId.isEmpty()){
          preferenUtil.savedata("id",editText_answer.getText().toString().trim());
          ((MainActivity) getActivity()).go_to_next();
        }else {
          SnackBarUtils.setErrorMsg(getActivity(),"Answer field cannot be empty");
        }
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

  public void showInputMethod() {
    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
  }

}
