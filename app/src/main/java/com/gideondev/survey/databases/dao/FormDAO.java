package com.gideondev.survey.databases.dao;

import android.content.Context;
import android.support.annotation.NonNull;
import com.gideondev.survey.FormModel;
import com.gideondev.survey.databases.dto.FormDTO;
import com.gideondev.survey.databases.sqlite.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormDAO
    implements FormDTO {

    private Connection connection;
    private Context mContext;

    public FormDAO(Context context, Connection connection) {
        this.connection = connection;
        this.mContext = context;
    }


    //public  boolean checkUserTweetExist(TweetFriendModel model, long account_id) {
    //    try {
    //        return connection.getFriendDataDao().queryBuilder().where().eq("account_id", account_id).and().eq("id_tweet", model.getId_tweet()).query().size() > 0;
    //    } catch (SQLException e) {
    //        e.printStackTrace();
    //    }
    //    return false;
    //}


    @Override
    public boolean addForm(FormModel model) {
        try {

            return connection.getFormDataDao().create(model) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateForm(FormModel model) {
        try {
            return connection.getFormDataDao().update(model)==1;
        }catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void deleteForm(String id) {
        try {
            connection.getFormDataDao().deleteById(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<FormModel> getFormModels() {
        try {
            return connection.getFormDataDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    public FormModel getNoteModelByID(String id) {
        try {
            return connection.getFormDataDao().queryForId(Integer.valueOf(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  FormModel getFormModelById(@NonNull String id) {
        try {
            List<FormModel> list = connection.getFormDataDao().queryForEq("username", id);
            if (list != null && !list.isEmpty())
                return list.get(0);
        } catch (SQLException | OutOfMemoryError e) {
            e.printStackTrace();
        }
        return null;
    }

}
