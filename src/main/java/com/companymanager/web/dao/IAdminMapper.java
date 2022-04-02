package com.companymanager.web.dao;

import com.companymanager.entity.*;
import com.companymanager.entity.condition.SalaryOrderTopic;
import com.companymanager.web.dao.sqlcondition.SQLProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface IAdminMapper {

    @Select("select count(*) from root_hr where admin = #{name} and password = #{pass}")
    int queryAdmin(Map<String,String> map);

    //审批员工的状态
        // 1. 查看未审批的员工
    @Select("select * from employee_info where emp_status = 0")
    List<Employee> queryNoAccessEmp();

        // 2.修改审批状态 同意审批
    @Update("update employee_info  set emp_status = 1 where emp_id = #{empId} ")
    int updateEmployeeStatus(Map<String,String> map);
        // 3.设置绩效和基本工资
     @Insert("insert into salary_info (emp_id, sar_basic, sar_merits,  sal_merits_precent)\n" +
             "values(#{empId},#{sarBasic},#{sarMerits},0);")
    int insertSalary(Map<String,String> map);

    @SelectProvider(value = SQLProvider.class,method = "queryTransactionInfo")
    List<TransactionInfo> queryTransactionInfo(Map<String,String> map);

    //1. 审批事务 请假等
    @Update("update transaction_info set status_access = #{status} where id = #{id}")
    int updateTransactionInfoStatus(Map<String,String> map);
    //2. 同意之后 加入事务总计表，用以计算工资
            // 先查找 当月有无记录
    @Select("select * from transaction_info_sum where emp_id = #{empId}  AND year(#{tranTime}) = year(record_time) AND month(#{tranTime}) = month(record_time);")
    TransactionInfoSum queryTransInfoRecord(Map<String,String> map);
        //2.1 如果没有记录则新增 一条记录
    @Insert("INSERT INTO transaction_info_sum\n" +
            "(emp_id, dep_id, leave_sum, work_overtime, exit_sum, late_sum, record_time) \n" +
            " VALUES (#{empId},#{depId}, #{leaveSum}, #{workOvertime}, #{exitSum}, #{lateSum}, #{recordTime} );")
    int insertTransInfoRecord(TransactionInfoSum transactionInfoSum);

        //2.2 如果当月有记录，则直接在对应的事务次数上加1；
    @Update("update transaction_info_sum set dep_id =#{tran.depId} ,leave_sum = #{tran.leaveSum} , work_overtime =  #{tran.workOvertime}" +
            ",exit_sum = #{tran.exitSum},late_sum = #{tran.lateSum} where emp_id = #{tran.empId} AND year(#{tranTime}) = year(record_time) AND month(#{tranTime}) = month(record_time); ")
    int updateTransInfoRecord(@Param("tran") TransactionInfoSum transactionInfoSum,@Param("tranTime") String tranTime);


    @Select("SELECT * FROM util_info;")
    UtilInfo queryUtilInfo();

    @Update("\n" +
            "UPDATE util_info SET  " +
            "util_mark =#{utilMark} ," +
            "util_eat_sub =#{utilEatSub} ,\n" +
            "util_traffic_sub =#{utilTrafficSub} ,\n" +
            "util_late = #{utilLate},\n" +
            "util_exit = #{utilExit},\n" +
            "util_late_time = #{utilLateTime},\n" +
            "util_exit_time = #{utilExitTime},\n" +
            "util_hoilday_money = #{utilHoildayMoney},\n" +
            "util_overtime_money = #{utilOvertimeMoney} " +
            "WHERE id = #{id} ")
    int updateUtilInfo(UtilInfo utilInfo);

    //查看各部门的人数
    @Select("select dep_id,dep_name,pos_name  ,count(emp_pos_name) as total from dept_position\n" +
            "left join  dept_info on dept_id = dep_id\n" +
            "left join employee_info on emp_deptid = dep_id AND emp_pos_name = pos_name\n" +
            "group by(emp_pos_name) ")
    List<Map<String,Integer> > queryDeptSumByDeptId();

    //结算工资：
    @SelectProvider(value = SQLProvider.class,method = "querySalary")
    List<SalaryOrderTopic> queryEveryEmpSalary();

    @InsertProvider(value = SQLProvider.class,method = "saveSalaryRecord")
    int saveSalaryRecord(List<SalaryOrderTopic> list);
}
