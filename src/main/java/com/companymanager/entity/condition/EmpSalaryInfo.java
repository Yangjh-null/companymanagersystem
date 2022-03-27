package com.companymanager.entity.condition;

import com.companymanager.entity.Employee;

public class EmpSalaryInfo extends Employee {
    private int sraBasic;
    private int sarMerits;

    public int getSraBasic() {
        return sraBasic;
    }

    public void setSraBasic(int sraBasic) {
        this.sraBasic = sraBasic;
    }

    public int getSarMerits() {
        return sarMerits;
    }

    public void setSarMerits(int sarMerits) {
        this.sarMerits = sarMerits;
    }


    @Override
    public String toString() {
        return "EmpSalaryInfo{" +
                "sraBasic=" + sraBasic +
                ", sarMerits=" + sarMerits +
                '}';
    }
}
