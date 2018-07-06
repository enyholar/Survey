package com.gideondev.survey.feature.Fragment;

import static com.gideondev.survey.feature.main.view.MainActivity.familyName;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class QFamilyNameFragment extends Fragment {
  private Button button_continue,button_previous;
  private TextView textview_q_title;
  private EditText editText_answer;
  public QFamilyNameFragment() {
    // Required empty public constructor
  }

  public static QFamilyNameFragment newInstance() {
    QFamilyNameFragment fragment = new QFamilyNameFragment();
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
//      mParam1 = getArguments().getString(ARG_PARAM1);
//      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @SuppressLint("ResourceType")
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    ViewGroup rootView = (ViewGroup) inflater.inflate(
        R.layout.fragment_qfamily_name, container, false);
    final PreferenUtil preferenUtil = PreferenUtil.getInstant(getContext());
    button_continue = (Button) rootView.findViewById(R.id.button_next);
    button_previous = (Button) rootView.findViewById(R.id.button_prev);
    textview_q_title = (TextView) rootView.findViewById(R.id.textview_q_title);
    textview_q_title.setText(getResources().getString(R.string.q_family_name));
    editText_answer = (EditText) rootView.findViewById(R.id.editText_answer);
    showInputMethod();
    button_continue.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        String familyNames = editText_answer.getText().toString().trim();
        familyName = familyNames;
        if (familyName != null && !familyName.isEmpty()){
          preferenUtil.savedata("fname",editText_answer.getText().toString().trim());
          ((MainActivity) getActivity()).go_to_next();
        }else {
          SnackBarUtils.setErrorMsg(getActivity(),"Answer field cannot be empty");
        }

      }
    });

    button_previous.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
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
