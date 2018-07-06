package com.gideondev.survey.feature.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.gideondev.survey.FormModel;
import com.gideondev.survey.MultimediaModel;
import com.gideondev.survey.R;
import java.util.List;

/**
 * Created by Enny on 20/10/17.
 */

public class MultimediaAttachAdapter
    extends RecyclerView.Adapter<MultimediaAttachAdapter.ViewHolder>  {

    private List<MultimediaModel> mMultimediaList;

   private MultimediaAttachListner mListener;

    private Context mContext;

    public MultimediaAttachAdapter(Context context, List<MultimediaModel> folde, MultimediaAttachListner mLis) {
        mContext = context;
        this.mMultimediaList = folde;
        this.mListener = mLis;

    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View noteView;
        noteView = inflater.inflate(R.layout.item_camera, parent, false);
        return new ViewHolder(noteView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
      final MultimediaModel model = mMultimediaList.get(position);
        holder.mAttachedName.setText( model.getName());
        holder.mBtnDelete.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View v) {
            mListener.onClick(position,model);
          }
        });
    }


    @Override
    public int getItemCount() {
        return null == mMultimediaList ? 0 : mMultimediaList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView mFamilyName;
        AppCompatTextView mAttachedName;
      ImageView mBtnDelete;
        View mItemView;

        ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
          mAttachedName = (AppCompatTextView) itemView.findViewById(R.id.multi_name);
          mBtnDelete = (ImageView) itemView.findViewById(R.id.img_delete);
        }

    }
}
