package com.companymanager.entity;


public class SalaryInfo {

  private String empId; //工号
  private long sarBasic; //基本工资
  private long sarMerits; //绩效工资
  private long id;  //主键
  private long salMeritsPrecent;  //当月绩效百分比


  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }


  public long getSarBasic() {
    return sarBasic;
  }

  public void setSarBasic(long sarBasic) {
    this.sarBasic = sarBasic;
  }


  public long getSarMerits() {
    return sarMerits;
  }

  public void setSarMerits(long sarMerits) {
    this.sarMerits = sarMerits;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getSalMeritsPrecent() {
    return salMeritsPrecent;
  }

  public void setSalMeritsPrecent(long salMeritsPrecent) {
    this.salMeritsPrecent = salMeritsPrecent;
  }

}
