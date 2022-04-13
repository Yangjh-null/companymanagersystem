package com.companymanager.web.service;

import com.companymanager.entity.*;
import com.companymanager.entity.condition.EmployeeCondition;
import com.companymanager.entity.condition.Position;
import com.companymanager.entity.condition.SalaryOrderTopic;

import java.util.List;
import java.util.Map;

public interface IAdminService {

    int queryAdmin(Map<String,String> map);
    //新增员工
    int insertNewCompanyInfo(EmployeeCondition employeeCondition);

    boolean updateEmpInfo(EmployeeCondition  employeeCondition);

    List<EmployeeCondition> queryAllEmployee();
    List<EmployeeCondition> highSearch(Map<String,String> map);

//    List<Employee> queryNoAccessEmp();
//    boolean updateEmployeeStatus(Map<String,String> map );

    List<TransactionInfo> queryTransactionInfo(Map<String,String> map);

    boolean updateTranStatus(Map<String,String>map);

    UtilInfo queryUtilInfo();

    int updateUtilInfo(UtilInfo utilInfo);

    List<Map<String,Integer> > queryDeptSumByDeptId();

    List<SalaryOrderTopic> queryEveryEmpSalary();

   int  saveSalaryRecord(List<SalaryOrderTopic> list);

    List<Position> queryPositionInfo(Map<String,String> map);

    boolean insertNewDept(DeptInfo deptInfo);

    boolean deleteDeptById(int depId);

    boolean deletePositionById(int posId);


}
