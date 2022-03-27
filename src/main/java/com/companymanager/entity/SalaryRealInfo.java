package com.companymanager.entity;


public class SalaryRealInfo {

  private long id;
  private String empId;
  private long salBasic;
  private long salMerits;
  private long utilMark;
  private long utilEatSub;
  private long utilTrafficSub;
  private long lateSum;
  private long exitSum;
  private long workOvertime;
  private java.sql.Date salDate;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }


  public long getSalBasic() {
    return salBasic;
  }

  public void setSalBasic(long salBasic) {
    this.salBasic = salBasic;
  }


  public long getSalMerits() {
    return salMerits;
  }

  public void setSalMerits(long salMerits) {
    this.salMerits = salMerits;
  }


  public long getUtilMark() {
    return utilMark;
  }

  public void setUtilMark(long utilMark) {
    this.utilMark = utilMark;
  }


  public long getUtilEatSub() {
    return utilEatSub;
  }

  public void setUtilEatSub(long utilEatSub) {
    this.utilEatSub = utilEatSub;
  }


  public long getUtilTrafficSub() {
    return utilTrafficSub;
  }

  public void setUtilTrafficSub(long utilTrafficSub) {
    this.utilTrafficSub = utilTrafficSub;
  }


  public long getLateSum() {
    return lateSum;
  }

  public void setLateSum(long lateSum) {
    this.lateSum = lateSum;
  }


  public long getExitSum() {
    return exitSum;
  }

  public void setExitSum(long exitSum) {
    this.exitSum = exitSum;
  }


  public long getWorkOvertime() {
    return workOvertime;
  }

  public void setWorkOvertime(long workOvertime) {
    this.workOvertime = workOvertime;
  }


  public java.sql.Date getSalDate() {
    return salDate;
  }

  public void setSalDate(java.sql.Date salDate) {
    this.salDate = salDate;
  }

}
