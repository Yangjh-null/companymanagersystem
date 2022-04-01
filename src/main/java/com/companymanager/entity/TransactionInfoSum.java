package com.companymanager.entity;


public class TransactionInfoSum {

  private long id;
  private String empId;
  private long depId;
  private double leaveSum;
  private double workOvertime;
  private long exitSum;
  private long lateSum;
  private java.sql.Date recordTime;


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


  public long getDepId() {
    return depId;
  }

  public void setDepId(long depId) {
    this.depId = depId;
  }


  public double getLeaveSum() {
    return leaveSum;
  }

  public void setLeaveSum(double leaveSum) {
    this.leaveSum = leaveSum;
  }


  public double getWorkOvertime() {
    return workOvertime;
  }

  public void setWorkOvertime(double workOvertime) {
    this.workOvertime = workOvertime;
  }


  public long getExitSum() {
    return exitSum;
  }

  public void setExitSum(long exitSum) {
    this.exitSum = exitSum;
  }


  public long getLateSum() {
    return lateSum;
  }

  public void setLateSum(long lateSum) {
    this.lateSum = lateSum;
  }


  public java.sql.Date getRecordTime() {
    return recordTime;
  }

  public void setRecordTime(java.sql.Date recordTime) {
    this.recordTime = recordTime;
  }


  @Override
  public String toString() {
    return "TransactionInfoSum{" +
            "id=" + id +
            ", empId='" + empId + '\'' +
            ", depId=" + depId +
            ", leaveSum=" + leaveSum +
            ", workOvertime=" + workOvertime +
            ", exitSum=" + exitSum +
            ", lateSum=" + lateSum +
            ", recordTime=" + recordTime +
            '}';
  }
}
