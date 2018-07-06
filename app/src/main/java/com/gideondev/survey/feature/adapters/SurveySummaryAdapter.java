package com.gideondev.survey.feature.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.gideondev.survey.FormModel;
import com.gideondev.survey.R;
import java.util.List;

/**
 * Created by Enny on 20/10/17.
 */

public class SurveySummaryAdapter
    extends RecyclerView.Adapter<SurveySummaryAdapter.ViewHolder>  {

    private List<FormModel> mFolderList;
   private SummarySurveyListner mListener;

    private Context mContext;

    public SurveySummaryAdapter(Context context, List<FormModel> folde, SummarySurveyListner listner) {
        mContext = context;
        this.mFolderList = folde;
        this.mListener = listner;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View noteView;
        noteView = inflater.inflate(R.layout.item_survey_summary_row, parent, false);
        return new ViewHolder(noteView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      final FormModel model = mFolderList.get(position);
        holder.mVolunteerName.setText("Volunteer name:" + " " + model.getVolunteerName());
        holder.mFamilyName.setText("Person interviewed:" + " " +model.getNameOfPersonInterviewed());
        holder.mItemView.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View v) {
            mListener.onClick(model);
          }
        });
    }


    @Override
    public int getItemCount() {
        return null == mFolderList ? 0 : mFolderList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView mFamilyName;
        AppCompatTextView mVolunteerName;
        View mItemView;

        ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mFamilyName = (AppCompatTextView) itemView.findViewById(R.id.family_name);
            mVolunteerName = (AppCompatTextView) itemView.findViewById(R.id.volunteer_name);
        }

    }
}
