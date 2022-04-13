package com.companymanager.web.dao;

import com.companymanager.entity.SalaryInfo;
import com.companymanager.entity.condition.EmployeeCondition;
import com.companymanager.entity.condition.TransInfoSumCondition;
import com.companymanager.web.dao.sqlcondition.SQLProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ILeaderMapper {

    @SelectProvider(value = SQLProvider.class,method = "queryTransactionInfoSumByTime")
    List<TransInfoSumCondition> queryCheckOnTimeByDeptId(Map<String,String>map);

    @Select("SELECT emp.emp_id, emp.emp_name,sal_merits_precent FROM employee_info as emp\n" +
            "left join salary_info as sal on  emp.emp_id = sal.emp_id\n" +
            "where  emp.emp_deptid = #{deptId};")
    List<EmployeeCondition> querySalMeritsByDeptId(Map<String,Integer> map);

    @Update("update salary_info set sal_merits_precent = #{salMeritsPrecent} where emp_id = #{empId}")
    int updateMeritsByEmpId(SalaryInfo salaryInfo);

}
