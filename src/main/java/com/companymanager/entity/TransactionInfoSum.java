package com.companymanager.entity;


import java.sql.Date;


//考勤 总计 按月
public class TransactionInfoSum {

  private int id;
  private String empId;  //工号
  private int leaveSum;  //请假
  private int workOvertime;  //加班时间
  private int exitSum;   //早退 次数
  private int lateSum;   //迟到 次数
  private Date recordTime;  //考勤时间

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public int getLeaveSum() {
    return leaveSum;
  }

  public void setLeaveSum(int leaveSum) {
    this.leaveSum = leaveSum;
  }

  public int getWorkOvertime() {
    return workOvertime;
  }

  public void setWorkOvertime(int workOvertime) {
    this.workOvertime = workOvertime;
  }

  public int getExitSum() {
    return exitSum;
  }

  public void setExitSum(int exitSum) {
    this.exitSum = exitSum;
  }

  public int getLateSum() {
    return lateSum;
  }

  public void setLateSum(int lateSum) {
    this.lateSum = lateSum;
  }

  public Date getRecordTime() {
    return recordTime;
  }

  public void setRecordTime(Date recordTime) {
    this.recordTime = recordTime;
  }
}
