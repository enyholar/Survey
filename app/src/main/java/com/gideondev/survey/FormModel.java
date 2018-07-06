package com.gideondev.survey;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.io.Serializable;

/**
 * Created by ENNY on 6/3/2018.
 */
@DatabaseTable(tableName = "tbl_form")
public class FormModel implements Serializable {
  @DatabaseField(generatedId = true)
  int id;
  @DatabaseField
  String familyName;
  @DatabaseField
  Double latitude;
  @DatabaseField
  Double longitude;
  @DatabaseField
  String address;
  @DatabaseField
  String visitDate;
  @DatabaseField
  String numberOfFamilyMember;
  @DatabaseField
  String familyInfo;
  @DatabaseField
  String volunteerName;
  @DatabaseField
  String custom1;
  @DatabaseField
  String custom2;
  @DatabaseField
  String custom3;
  @DatabaseField
  String custom4;
  @DatabaseField
  String custom5;
  @DatabaseField
  String nameOfPersonInterviewed;
  @DatabaseField
  String numberOfCattleOwnedByFamily;
  @DatabaseField
  String numberOfCattleCurrentlyBeingMilked;
  @DatabaseField
  String numberOfCattleDerived;
  @DatabaseField
  String surveyId;
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public String getNumberOfFamilyMember() {
    return numberOfFamilyMember;
  }

  public void setNumberOfFamilyMember(String numberOfFamilyMember) {
    this.numberOfFamilyMember = numberOfFamilyMember;
  }

  public String getFamilyInfo() {
    return familyInfo;
  }

  public void setFamilyInfo(String familyInfo) {
    this.familyInfo = familyInfo;
  }

  public String getNumberOfCattleOwnedByFamily() {
    return numberOfCattleOwnedByFamily;
  }

  public void setNumberOfCattleOwnedByFamily(String numberOfCattleOwnedByFamily) {
    this.numberOfCattleOwnedByFamily = numberOfCattleOwnedByFamily;
  }

  public String getNumberOfCattleCurrentlyBeingMilked() {
    return numberOfCattleCurrentlyBeingMilked;
  }

  public void setNumberOfCattleCurrentlyBeingMilked(String numberOfCattleCurrentlyBeingMilked) {
    this.numberOfCattleCurrentlyBeingMilked = numberOfCattleCurrentlyBeingMilked;
  }

  public String getNumberOfCattleDerived() {
    return numberOfCattleDerived;
  }

  public void setNumberOfCattleDerived(String numberOfCattleDerived) {
    this.numberOfCattleDerived = numberOfCattleDerived;
  }

  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getVisitDate() {
    return visitDate;
  }

  public void setVisitDate(String visitDate) {
    this.visitDate = visitDate;
  }


  public String getVolunteerName() {
    return volunteerName;
  }

  public void setVolunteerName(String volunteerName) {
    this.volunteerName = volunteerName;
  }

  public String getNameOfPersonInterviewed() {
    return nameOfPersonInterviewed;
  }

  public void setNameOfPersonInterviewed(String nameOfPersonInterviewed) {
    this.nameOfPersonInterviewed = nameOfPersonInterviewed;
  }

  public String getCustom1() {
    return custom1;
  }

  public void setCustom1(String custom1) {
    this.custom1 = custom1;
  }

  public String getCustom2() {
    return custom2;
  }

  public void setCustom2(String custom2) {
    this.custom2 = custom2;
  }

  public String getCustom3() {
    return custom3;
  }

  public void setCustom3(String custom3) {
    this.custom3 = custom3;
  }

  public String getCustom4() {
    return custom4;
  }

  public void setCustom4(String custom4) {
    this.custom4 = custom4;
  }

  public String getCustom5() {
    return custom5;
  }

  public void setCustom5(String custom5) {
    this.custom5 = custom5;
  }

  public String getSurveyId() {
    return surveyId;
  }

  public void setSurveyId(String surveyId) {
    this.surveyId = surveyId;
  }

  public FormModel() {

  }

  public FormModel(String familyName, Double latitude, Double longitude, String address,
      String visitDate, String numberOfFamilyMember, String volunteerName,
      String nameOfPersonInterviewed, String numberOfCattleOwnedByFamily,
      String numberOfCattleCurrentlyBeingMilked, String numberOfCattleDerived,String famInfo,
      String cus1, String cus2, String cus3, String cus4, String cus5,String survey) {
    this.familyName = familyName;
    this.latitude = latitude;
    this.longitude = longitude;
    this.address = address;
    this.visitDate = visitDate;
    this.numberOfFamilyMember = numberOfFamilyMember;
    this.volunteerName = volunteerName;
    this.nameOfPersonInterviewed = nameOfPersonInterviewed;
    this.numberOfCattleOwnedByFamily = numberOfCattleOwnedByFamily;
    this.numberOfCattleCurrentlyBeingMilked = numberOfCattleCurrentlyBeingMilked;
    this.numberOfCattleDerived = numberOfCattleDerived;
    this.familyInfo = famInfo;
    this.custom1 = cus1;
    this.custom2 = cus2;
    this.custom3 = cus3;
    this.custom4 = cus4;
    this.custom5 = cus5;
    this.surveyId = survey;
  }

  @Override
  public String toString() {
    return "FormModel{" +
        "id='" + id + '\'' +
        ", familyName='" + familyName + '\'' +
        ", latitude=" + latitude +
        ", longitude=" + longitude +
        ", address='" + address + '\'' +
        ", visitDate='" + visitDate + '\'' +
        ", numberOfFamilyMember=" + numberOfFamilyMember +
        ", volunteerName='" + volunteerName + '\'' +
        ", nameOfPersonInterviewed='" + nameOfPersonInterviewed + '\'' +
        ", numberOfCattleOwnedByFamily=" + numberOfCattleOwnedByFamily +
        ", numberOfCattleCurrentlyBeingMilked=" + numberOfCattleCurrentlyBeingMilked +
        ", numberOfCattleDerived=" + numberOfCattleDerived +
        '}';
  }
}
