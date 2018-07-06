package com.gideondev.survey.feature.Fragment;

import static com.gideondev.survey.feature.main.view.MainActivity.custom3;
import static com.gideondev.survey.feature.main.view.MainActivity.familyMember;
import static com.gideondev.survey.feature.main.view.MainActivity.family_number;
import static com.gideondev.survey.feature.main.view.MainActivity.homeAddress;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.gideondev.survey.PreferenUtil.PreferenUtil;
import com.gideondev.survey.R;
import com.gideondev.survey.feature.FamilyModel;
import com.gideondev.survey.feature.SnackBarUtils;
import com.gideondev.survey.feature.adapters.AddFamilyAdapter;
import com.gideondev.survey.feature.adapters.FamilyAdapter;
import com.gideondev.survey.feature.adapters.FamilyMemListner;
import com.gideondev.survey.feature.main.view.MainActivity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class QFamilyMemberFragment extends Fragment {

  private AddFamilyAdapter addFamilyAdapter;
  private List<FamilyModel> familyModelList = new ArrayList<>();
  private List<String> familyInfo = new ArrayList<>();
  private RecyclerView listView;
  private Button btnAdd;
  private TextView textview_q_title;
  private FamilyAdapter familyAdapter;
  private Button button_continue;
  private Button button_previous;
  private TextView textview_q_title_num_of_fam;
  private EditText editText_answer;

  public QFamilyMemberFragment() {
    // Required empty public constructor
  }

  public static QFamilyMemberFragment newInstance(String param1, String param2) {
    QFamilyMemberFragment fragment = new QFamilyMemberFragment();

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
        R.layout.fragment_qfamily_member, container, false);
    final PreferenUtil preferenUtil = PreferenUtil.getInstant(getContext());
    listView = (RecyclerView) rootView.findViewById(R.id.my_list);
    button_continue = (Button) rootView.findViewById(R.id.button_next);
    button_previous = (Button) rootView.findViewById(R.id.button_prev);
    textview_q_title = (TextView) rootView.findViewById(R.id.textview_q_title);
    textview_q_title_num_of_fam = (TextView) rootView.findViewById(R.id.textview_q_title_num_of_fam);
    textview_q_title_num_of_fam.setText(getResources().getString(R.string.q_family_mem_no));
    editText_answer = (EditText) rootView.findViewById(R.id.editText_answer);

    showInputMethod();
    textview_q_title.setText(getResources().getString(R.string.q_family_memeber));
    btnAdd = (Button) rootView.findViewById(R.id.btn_add);
    btnAdd.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        createNewFamilyInfo();
        //addNewLayout();
      }
    });
    button_continue.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        family_number = editText_answer.getText().toString();
        preferenUtil.savedata("fnum",editText_answer.getText().toString());
 //       if (!family_number.isEmpty() && familyMember != null && familyMember.size() > 0){
          ((MainActivity) getActivity()).go_to_next();
//        }else {
//          SnackBarUtils.setErrorMsg(getActivity(),"Answer field cannot be empty");
//        }
      }
    });

    button_previous.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        ((MainActivity) getActivity()).go_to_prev();
      }
    });

    familyAdapter = new FamilyAdapter(getContext(), familyModelList, new FamilyMemListner() {
      @Override
      public void onClick(int pos, FamilyModel model) {
        familyModelList.remove(pos);
        familyMember.remove(pos);
        familyAdapter.notifyDataSetChanged();
        Toast.makeText(getContext(), "deleted successfully", Toast.LENGTH_LONG).show();
      }

      @Override
      public void onEdit(int pos, FamilyModel model) {

        EditFamilyInfo(model,pos);
      }
    });

    listView.setAdapter(familyAdapter);
    return rootView;
  }
  private void EditFamilyInfo(FamilyModel model, final int pos) {
    LayoutInflater inflater = LayoutInflater.from(getActivity());
    View dialogLayout = inflater.inflate(R.layout.row, null);
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    TextView title = new TextView(getActivity());
    final EditText mFamilyName =
        (EditText) dialogLayout.findViewById(R.id.edt_family_name);
    final EditText mFamilyAge =
        (EditText) dialogLayout.findViewById(R.id.family_age);
    mFamilyAge.setText(model.getDate());
    mFamilyName.setText(model.getName());
    title.setText(getActivity().getResources().getString(R.string.add_family));
//    mFamilyAge.setOnClickListener(new OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        DialogFragment newFragment = new DatePickerFragment();
//        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
//      }
//    });
    LinearLayout.LayoutParams lp =
        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
    title.setPadding(50, 50, 0, 0);
    title.setLayoutParams(lp);
    title.setTextSize(20);
    title.setTypeface(title.getTypeface(), Typeface.BOLD);
    title.setTextColor(getActivity().getResources()
        .getColor(R.color.colorPrimary));
    builder.setCustomTitle(title);
    builder.setView(dialogLayout);
    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

      @Override
      public void onClick(DialogInterface dialog, int which) {
        // TODO Auto-generated method stub
        dialog.dismiss();
      }
    });

    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

      @Override
      public void onClick(DialogInterface dialog, int which) {
        // TODO Auto-generated method stub
        String familyName = mFamilyName.getText().toString();
        String age = mFamilyAge.getText().toString();
        if (familyName != null && !familyName.isEmpty() && age != null && !age.isEmpty()){
          familyMember.remove(pos);
          familyModelList.remove(pos);
          familyMember.add(familyName + " " + age);
          FamilyModel model = new FamilyModel();
          model.setDate(age);
          model.setName(familyName);
          model.setNameDate(familyName + " " + age);
          familyModelList.add(model);
          familyAdapter.notifyDataSetChanged();
        }

        //    String folderName = mNewFolderEditText.getText().toString();
      }
    });

    AlertDialog customAlertDialog = builder.create();
    customAlertDialog.show();
    Button btnPositive = customAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
    Button btnNegative = customAlertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
    btnNegative.setTextColor(getContext().getResources()
        .getColor(R.color.colorPrimary));
    btnPositive.setTextColor(getContext().getResources()
        .getColor(R.color.colorPrimary));
    btnNegative.setTextSize(14);
    btnPositive.setTypeface(btnPositive.getTypeface(), Typeface.BOLD);
    btnNegative.setTypeface(btnPositive.getTypeface(), Typeface.BOLD);

    LinearLayout.LayoutParams layoutParams =
        (LinearLayout.LayoutParams) btnPositive.getLayoutParams();
    //layoutParams.weight = 5;
    btnPositive.setLayoutParams(layoutParams);
    btnNegative.setLayoutParams(layoutParams);
  }

  private void createNewFamilyInfo() {
    LayoutInflater inflater = LayoutInflater.from(getActivity());
    View dialogLayout = inflater.inflate(R.layout.row, null);
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    TextView title = new TextView(getActivity());
    final EditText mFamilyName =
        (EditText) dialogLayout.findViewById(R.id.edt_family_name);
    final EditText mFamilyAge =
        (EditText) dialogLayout.findViewById(R.id.family_age);
    title.setText(getActivity().getResources().getString(R.string.add_family));
//    mFamilyAge.setOnClickListener(new OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        DialogFragment newFragment = new DatePickerFragment();
//        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
//      }
//    });
    LinearLayout.LayoutParams lp =
        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
    title.setPadding(50, 50, 0, 0);
    title.setLayoutParams(lp);
    title.setTextSize(20);
    title.setTypeface(title.getTypeface(), Typeface.BOLD);
    title.setTextColor(getActivity().getResources()
        .getColor(R.color.colorPrimary));
    builder.setCustomTitle(title);
    builder.setView(dialogLayout);
    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

      @Override
      public void onClick(DialogInterface dialog, int which) {
        // TODO Auto-generated method stub
        dialog.dismiss();
      }
    });

    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

      @Override
      public void onClick(DialogInterface dialog, int which) {
        // TODO Auto-generated method stub
        String familyName = mFamilyName.getText().toString();
        String age = mFamilyAge.getText().toString();
        if (familyName != null && !familyName.isEmpty() && age != null && !age.isEmpty()){
          FamilyModel model = new FamilyModel();
          model.setDate(age);
          model.setName(familyName);
          model.setNameDate(familyName + " " + age);
          familyModelList.add(model);
          familyMember.add(familyName + " " + age);
          familyAdapter.notifyDataSetChanged();
        }

    //    String folderName = mNewFolderEditText.getText().toString();
      }
    });

    AlertDialog customAlertDialog = builder.create();
    customAlertDialog.show();
    Button btnPositive = customAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
    Button btnNegative = customAlertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
    btnNegative.setTextColor(getContext().getResources()
        .getColor(R.color.colorPrimary));
    btnPositive.setTextColor(getContext().getResources()
        .getColor(R.color.colorPrimary));
    btnNegative.setTextSize(14);
    btnPositive.setTypeface(btnPositive.getTypeface(), Typeface.BOLD);
    btnNegative.setTypeface(btnPositive.getTypeface(), Typeface.BOLD);

    LinearLayout.LayoutParams layoutParams =
        (LinearLayout.LayoutParams) btnPositive.getLayoutParams();
    //layoutParams.weight = 5;
    btnPositive.setLayoutParams(layoutParams);
    btnNegative.setLayoutParams(layoutParams);
  }

  public static class DatePickerFragment extends DialogFragment
      implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
      final Calendar c = Calendar.getInstance();
      int year = c.get(Calendar.YEAR);
      int month = c.get(Calendar.MONTH);
      int day = c.get(Calendar.DAY_OF_MONTH);
      DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
      dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
      return  dialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
      Calendar calendar = Calendar.getInstance();
      int currentYear = calendar.get(Calendar.YEAR);
      int age = currentYear - year;
//      mFamilyAge.setText(String.valueOf(age));
 //     btnDate.setText(ConverterDate.ConvertDate(year, month + 1, day));
    }
  }

  public void showInputMethod() {
    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
  }

//
//  private void addNewLayout() {
//    FamilyModel familyModel = new FamilyModel();
//    familyModel.setName("");
//    familyModel.setDate("");
//    familyModelList.add(familyModel);
//    if (addFamilyAdapter != null) {
//      addFamilyAdapter.notifyDataSetChanged();
//    }
//
//  }

}
