package com.companymanager.entity;


public class DeptInfo {

  private long depId;
  private String depName;
  private String depInfo;
  private String posName;
  private int posId;

  public String getPosName() {
    return posName;
  }

  public void setPosName(String posName) {
    this.posName = posName;
  }

  public int getPosId() {
    return posId;
  }

  public void setPosId(int posId) {
    this.posId = posId;
  }

  public long getDepId() {
    return depId;
  }

  public void setDepId(long depId) {
    this.depId = depId;
  }


  public String getDepName() {
    return depName;
  }

  public void setDepName(String depName) {
    this.depName = depName;
  }


  public String getDepInfo() {
    return depInfo;
  }

  public void setDepInfo(String depInfo) {
    this.depInfo = depInfo;
  }

}
