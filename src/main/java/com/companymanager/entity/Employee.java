package com.companymanager.entity;

public class Employee {

    private String empId;   //工号
    private String empName;
    private String empCard;
    private int empSex;
    private int empEduc;  //学历  1高中 2专科 3本科 4硕士研究生 5博士研究生
    private int empDeptid; //部门id
    private String empDept; //部门名称
    private String empPosName;  //职位名称
    private String empPhone;  //电话
    private String empAddress;  //地址
    private int empStatus; //审批状态
    private String empMail;//邮箱
    private String empPass;//密码  默认身份证后六位
    private int empGrade; //区分普通员工和部门管理员


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

    public String getEmpCard() {
        return empCard;
    }

    public void setEmpCard(String empCard) {
        this.empCard = empCard;
    }

    public int getEmpSex() {
        return empSex;
    }

    public void setEmpSex(int empSex) {
        this.empSex = empSex;
    }

    public int getEmpEduc() {
        return empEduc;
    }

    public void setEmpEduc(int empEduc) {
        this.empEduc = empEduc;
    }

    public String getEmpDept() {
        return empDept;
    }

    public void setEmpDept(String empDept) {
        this.empDept = empDept;
    }

    public String getEmpPosName() {
        return empPosName;
    }

    public void setEmpPosName(String empPosName) {
        this.empPosName = empPosName;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public int getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(int empStatus) {
        this.empStatus = empStatus;
    }

    public String getEmpMail() {
        return empMail;
    }

    public void setEmpMail(String empMail) {
        this.empMail = empMail;
    }

    public String getEmpPass() {
        return empPass;
    }

    public void setEmpPass(String empPass) {
        this.empPass = empPass;
    }

    public int getEmpDeptid() {
        return empDeptid;
    }

    public void setEmpDeptid(int empDeptid) {
        this.empDeptid = empDeptid;
    }

    public int getEmpGrade() {
        return empGrade;
    }

    public void setEmpGrade(int empGrade) {
        this.empGrade = empGrade;
    }
}
