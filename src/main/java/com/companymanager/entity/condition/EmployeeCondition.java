package com.companymanager.entity.condition;

import com.companymanager.entity.Employee;

/**
 * 新增员工的参数实体类
 * */
public class EmployeeCondition extends Employee {

    private int sarBasic;
    private int sarMerits;

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

    @Override
    public String toString() {
        return super.toString()+"EmployeeCondition{" +
                "sarBasic=" + sarBasic +
                ", sarMerits=" + sarMerits +
                '}';
    }
}
