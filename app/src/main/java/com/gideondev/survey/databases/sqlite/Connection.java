package com.gideondev.survey.databases.sqlite;

import android.content.Context;
import com.gideondev.survey.FormModel;
import com.j256.ormlite.dao.Dao;

public class Connection extends DatabaseHelper {

  private static Connection instance;
  // the DAO object we use to access the UserModel table
  private Dao<FormModel, Integer> formDao  = null;

  public Connection(Context context) {
    super(context);
  }

  public static Connection getInstance(Context context) {
    if (instance == null) {
      instance = new Connection(context);
    }
    return instance;
  }


  /**
   * Returns the Database Access Object (DAO) for our TweetFriendModel class. It will
   * create it or just give the cached value.
   */
  public Dao<FormModel, Integer> getFormDataDao()
      throws java.sql.SQLException {
    if ( formDao == null) {
      formDao = getDao(FormModel.class);
    }
    return formDao;
  }


  /**
   * Close the database connections and clear any cached DAOs.
   */
  @Override
  public void close() {
    super.close();
    formDao = null;
  }
}
