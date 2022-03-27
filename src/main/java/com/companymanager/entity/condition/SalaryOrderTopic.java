package com.companymanager.entity.condition;


public class SalaryOrderTopic {

    private String empId;
    private String empName;
    private int sarBasic;  //基本工资
    private int sarMerits; //绩效
    private int salMeritsPrecent;//绩效百分比
    private int salRealMerits; //实际绩效工资
    private int lateMon;  //迟到总扣
    private int exitMon;  //早退总扣
    private int leaveMon;  //请假总扣
    private int workOnTime; //加班总费用
    private int eatSub;  //餐补
    private int trffSub;  //交通补贴
    private int markMon;  //全勤
    private int sarAll; //应发工资
    private int sarReal ; //实发工资
    private String recordTime ; //记录时间

    public int getSalRealMerits() {
        return salRealMerits;
    }

    public void setSalRealMerits(int salRealMerits) {
        this.salRealMerits = salRealMerits;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getSarBasic() {
        return sarBasic;
    }

    public void setSarBasic(int sarBasic) {
        this.sarBasic = sarBasic;
    }

    public int getSarMerits() {
        return sarMerits;
    }

    public void setSarMerits(int sarMerits) {
        this.sarMerits = sarMerits;
    }

    public int getLateMon() {
        return lateMon;
    }

    public void setLateMon(int lateMon) {
        this.lateMon = lateMon;
    }

    public int getExitMon() {
        return exitMon;
    }

    public void setExitMon(int exitMon) {
        this.exitMon = exitMon;
    }

    public int getLeaveMon() {
        return leaveMon;
    }

    public void setLeaveMon(int leaveMon) {
        this.leaveMon = leaveMon;
    }

    public int getWorkOnTime() {
        return workOnTime;
    }

    public void setWorkOnTime(int workOnTime) {
        this.workOnTime = workOnTime;
    }

    public int getEatSub() {
        return eatSub;
    }

    public void setEatSub(int eatSub) {
        this.eatSub = eatSub;
    }

    public int getTrffSub() {
        return trffSub;
    }

    public void setTrffSub(int trffSub) {
        this.trffSub = trffSub;
    }

    public int getMarkMon() {
        return markMon;
    }

    public void setMarkMon(int markMon) {
        this.markMon = markMon;
    }

    public int getSarAll() {
        return sarAll;
    }

    public void setSarAll(int sarAll) {
        this.sarAll = sarAll;
    }

    public int getSarReal() {
        return sarReal;
    }

    public void setSarReal(int sarReal) {
        this.sarReal = sarReal;
    }

    public int getSalMeritsPrecent() {
        return salMeritsPrecent;
    }

    public void setSalMeritsPrecent(int salMeritsPrecent) {
        this.salMeritsPrecent = salMeritsPrecent;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }
}
