package com.companymanager.web.dao;

import com.companymanager.entity.Employee;
import com.companymanager.entity.TransactionInfo;
import com.companymanager.entity.UtilInfo;
import com.companymanager.entity.condition.SalaryOrderTopic;
import com.companymanager.entity.condition.TransInfoSumCondition;
import com.companymanager.web.dao.sqlcondition.SQLProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

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
    @Update("update employee_info where set emp_status = 1 ,  where emp_id = #{empId} ")
    int updateEmployeeStatus(Map<String,String> map);
        // 3.设置绩效和基本工资
    @Update("update salary_info set sar_basic = #{sarBasic} , sar_merits = #{sarMerits} where emp_id = #{empId} ")
    int updateSalaryByEmpId(Map<String,String> map);

    @SelectProvider(value = SQLProvider.class,method = "queryTransactionInfo")
    List<TransactionInfo> queryTransactionInfo(Map<String,String> map);

    @Update("update transaction_info set status_access = #{status} where id = #{id}")
    int updateTransactionInfoStatus(Map<String,Integer> map);

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
}
