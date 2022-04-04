package com.companymanager.web.dao;

import com.companymanager.entity.Employee;
import com.companymanager.entity.TransactionInfo;
import com.companymanager.entity.TransactionInfoSum;
import com.companymanager.entity.condition.EmployeeCondition;
import com.companymanager.web.dao.sqlcondition.SQLProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;


@Mapper
public interface IEmployeeMapper {

    //员工注册账号
    @Insert("INSERT INTO employee_info " +
            "(emp_id,emp_name,emp_card,emp_sex,emp_educ,emp_deptid,emp_dept,emp_pos_name,emp_status,emp_phone,emp_address,emp_mail,emp_pass,emp_grade) " +
            "VALUES(#{empId},#{empName},#{empCard},#{empSex},#{empEduc},#{empDeptid},#{empDept},#{empPosName},0,#{empPhone},#{empAddress},#{empMail},#{empPass},#{empGrade}); ")
    int insertNewEmployee(EmployeeCondition  emp);

    //员工登录账号
    @Select("select * from employee_info where emp_id = #{empId} AND emp_pass = #{empPass} ")
    Employee queryEmployee(Map<String,String> map);

    @Update("update employee_info set emp_pass = #{empPass} where emp_id = #{empId}")
    int updateEmployeePassword(Map<String,String> map);
    //修改员工基本信息
    @Update("UPDATE  employee_info SET " +
            "emp_phone=#{empPhone},emp_address=#{empAddress},emp_mail=#{empMail}" +
            " where emp_id = #{empId} ")
    int updateEmpInfo(Map<String,String> map);



    //事务申请：
    @Insert("insert into transaction_info (emp_id, dep_id, trans_demo, trans_reason, trans_time, status_access, tran_time)\n" +
            "values(#{empId},#{depId},#{transDemo},#{transReason},#{transTime},0,now());")
    int insertTransaction(TransactionInfo transactionInfo);

    //查看考勤
    @SelectProvider(value = SQLProvider.class,method = "queryTransactionInfoSumByTime")
    List<TransactionInfoSum> queryTransactionInfoSumByDate(Map<String,String> map);

    //打卡  更新记录 之加入迟到早退信息
        // 1 更新迟到记录
    @Update("update transaction_info_sum set exit_sum = exit_sum+1 \n" +
            "where  year(record_time) = year(now()) AND month(record_time) = month(now()) AND emp_id = #{empId}")
    int updateEmpExitSingEveryDay(String empId);
        //更新迟到记录
    @Update("update transaction_info_sum set late_sum = late_sum+1\n" +
            "where  year(record_time) = year(now()) AND month(record_time) = month(now()) AND emp_id = #{empId};")
    int updateEmpSingLateSingEveryday(String empId);

}
