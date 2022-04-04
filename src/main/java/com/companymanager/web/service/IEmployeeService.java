package com.companymanager.web.service;

import com.companymanager.entity.Employee;
import com.companymanager.entity.TransactionInfo;
import com.companymanager.entity.TransactionInfoSum;

import java.util.List;
import java.util.Map;

public interface IEmployeeService  {

    int insertIntoNewEmployee(Employee employee);

    Employee queryEmployeeByUserNameAndPassword(Map<String,String> map);

    int updateEmployeePassword(Map<String,String> map);

    int updateEmployeeInfo(Map<String,String> map);

    int insertTransactionInfo(TransactionInfo transactionInfo);

    List<TransactionInfoSum> queryTransactionInfoSumByDate(Map<String,String> map);

    int updateEmpSingEveryDay(Map<String,String> map);
}
