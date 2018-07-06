package com.gideondev.survey.databases.dto;

import com.gideondev.survey.FormModel;
import java.util.List;

public interface FormDTO {

    boolean addForm(FormModel model);

    boolean updateForm(FormModel model);

    void deleteForm(String id);


    List<FormModel> getFormModels();

   // void onChange(long accountid);
}
