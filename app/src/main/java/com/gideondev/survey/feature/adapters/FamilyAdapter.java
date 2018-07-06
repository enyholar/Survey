package com.gideondev.survey.feature.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.gideondev.survey.R;
import com.gideondev.survey.feature.FamilyModel;
import java.util.List;

/**
 * Created by Enny on 20/10/17.
 */

public class FamilyAdapter
    extends RecyclerView.Adapter<FamilyAdapter.ViewHolder>  {

    private List<FamilyModel> mFolderList;
    private FamilyMemListner mListener;

    private Context mContext;

    public FamilyAdapter(Context context, List<FamilyModel> folde,FamilyMemListner mListen) {
        mContext = context;
        this.mFolderList = folde;
        this.mListener = mListen;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View noteView;
        noteView = inflater.inflate(R.layout.item_row, parent, false);
        return new ViewHolder(noteView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final int p = position;
        final FamilyModel model = mFolderList.get(p);
        holder.mFamilyAgeText.setText(model.getNameDate());
        holder.deleteFam.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View v) {
            mListener.onClick(position,model);
          }
        });

        holder.editFam.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onEdit(position,model);
            }
        });
    }


    @Override
    public int getItemCount() {
        return null == mFolderList ? 0 : mFolderList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView mFamilyAgeText;
        ImageView deleteFam,editFam;
        View mItemView;

        ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mFamilyAgeText = (AppCompatTextView) itemView.findViewById(R.id.family_age);
            deleteFam = itemView.findViewById(R.id.delete_fam);
            editFam = itemView.findViewById(R.id.edit_fam);
        }

    }
}
