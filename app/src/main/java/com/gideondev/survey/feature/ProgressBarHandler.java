package com.gideondev.survey.feature;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.gideondev.survey.R;

/**
 * Created by ${ENNY} on 7/21/2017.
 */

public class ProgressBarHandler {
    private ProgressBar mProgressBar;
    private Context mContext;

    @SuppressLint("ResourceAsColor")
    public ProgressBarHandler(Context context) {
        mContext = context;

        ViewGroup layout = (ViewGroup) ((Activity) context).findViewById(android.R.id.content).getRootView();

        mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleLarge);
      mProgressBar.getIndeterminateDrawable()
          .setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), PorterDuff.Mode.SRC_IN );
      //  mProgressBar.set(R.color.colorPrimary);
        //mProgressBar.setScaleY(0.5f);
        mProgressBar.setIndeterminate(true);

        RelativeLayout.LayoutParams params = new
            RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        RelativeLayout rl = new RelativeLayout(context);

        rl.setGravity(Gravity.CENTER);
        rl.addView(mProgressBar,100,100);

        layout.addView(rl, params);

        hide();
    }

    public void show() {
        if(mProgressBar.isEnabled()){
            mProgressBar.setVisibility(View.VISIBLE);
        }

    }

    public void hide() {
        mProgressBar.setVisibility(View.INVISIBLE);

    }
}
