package com.companymanager.entity;


public class TransactionInfo {

  private String empId;
  private long depId;
  private String transDemo;
  private String transReason;
  private double transTime;
  private long statusAccess;
  private long id;
  private java.sql.Timestamp tranTime;
  private String empName;

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }


  public long getDepId() {
    return depId;
  }

  public void setDepId(long depId) {
    this.depId = depId;
  }


  public String getTransDemo() {
    return transDemo;
  }

  public void setTransDemo(String transDemo) {
    this.transDemo = transDemo;
  }


  public String getTransReason() {
    return transReason;
  }

  public void setTransReason(String transReason) {
    this.transReason = transReason;
  }


  public double getTransTime() {
    return transTime;
  }

  public void setTransTime(double transTime) {
    this.transTime = transTime;
  }


  public long getStatusAccess() {
    return statusAccess;
  }

  public void setStatusAccess(long statusAccess) {
    this.statusAccess = statusAccess;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public java.sql.Timestamp getTranTime() {
    return tranTime;
  }

  public void setTranTime(java.sql.Timestamp tranTime) {
    this.tranTime = tranTime;
  }

}
