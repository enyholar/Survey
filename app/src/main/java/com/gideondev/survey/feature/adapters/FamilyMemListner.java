package com.gideondev.survey.feature.adapters;

import com.gideondev.survey.MultimediaModel;
import com.gideondev.survey.feature.FamilyModel;

/**
 * Created by ENNY on 6/12/2018.
 */
public interface FamilyMemListner {
  void onClick(int pos, FamilyModel model);
  void onEdit(int pos, FamilyModel model);
}
