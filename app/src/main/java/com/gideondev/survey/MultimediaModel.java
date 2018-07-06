package com.gideondev.survey;

/**
 * Created by ENNY on 6/28/2018.
 */
public class MultimediaModel {
  String name;
  String path;

  public MultimediaModel(String name, String path) {
    this.name = name;
    this.path = path;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}
