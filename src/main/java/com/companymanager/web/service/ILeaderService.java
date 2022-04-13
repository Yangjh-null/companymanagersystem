package com.companymanager.web.service;

import com.companymanager.entity.SalaryInfo;
import com.companymanager.entity.condition.EmployeeCondition;
import com.companymanager.entity.condition.TransInfoSumCondition;

import java.util.List;
import java.util.Map;

public interface ILeaderService {

    List<TransInfoSumCondition> queryCheckOnTimeByDeptId(Map<String,String> map);

    int updateMeritsByEmpId(SalaryInfo salaryInfo);

    List<EmployeeCondition> querySalMeritsByDeptId(Map<String,Integer> map);

}
