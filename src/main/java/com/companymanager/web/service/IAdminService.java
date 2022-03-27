package com.companymanager.web.service;

import com.companymanager.entity.Employee;
import com.companymanager.entity.TransactionInfo;
import com.companymanager.entity.UtilInfo;
import com.companymanager.entity.condition.SalaryOrderTopic;
//hot-fix
//冲突

import java.util.List;
import java.util.Map;

public interface IAdminService {

    int queryAdmin(Map<String,String> map);

    List<Employee> queryNoAccessEmp();
    boolean updateEmployeeStatus(Map<String,String> map );

    List<TransactionInfo> queryTransactionInfo(Map<String,String> map);

    int updateTranStatus(Map<String,Integer>map);

    UtilInfo queryUtilInfo();

    int updateUtilInfo(UtilInfo utilInfo);

    List<Map<String,Integer> > queryDeptSumByDeptId();

    List<SalaryOrderTopic> queryEveryEmpSalary();
}
