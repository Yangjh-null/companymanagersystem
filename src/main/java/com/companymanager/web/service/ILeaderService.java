package com.companymanager.web.service;

import com.companymanager.entity.SalaryInfo;
import com.companymanager.entity.TransactionInfo;
import com.companymanager.entity.TransactionInfoSum;
import com.companymanager.entity.condition.TransInfoSumCondition;

import java.util.List;
import java.util.Map;

public interface ILeaderService {

    List<TransInfoSumCondition> queryCheckOnTimeByDeptId(Map<String,String> map);

    int updateMeritsByEmpId(SalaryInfo salaryInfo);


}
