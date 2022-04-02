package com.companymanager.web.service;

import com.companymanager.entity.Employee;
import com.companymanager.entity.SalaryRealInfo;
import com.companymanager.entity.TransactionInfo;
import com.companymanager.entity.UtilInfo;
import com.companymanager.entity.condition.SalaryOrderTopic;

import java.util.List;
import java.util.Map;

public interface IAdminService {

    int queryAdmin(Map<String,String> map);

    List<Employee> queryNoAccessEmp();
    boolean updateEmployeeStatus(Map<String,String> map );

    List<TransactionInfo> queryTransactionInfo(Map<String,String> map);

    boolean updateTranStatus(Map<String,String>map);

    UtilInfo queryUtilInfo();

    int updateUtilInfo(UtilInfo utilInfo);

    List<Map<String,Integer> > queryDeptSumByDeptId();

    List<SalaryOrderTopic> queryEveryEmpSalary();

   int  saveSalaryRecord(List<SalaryOrderTopic> list);
}
