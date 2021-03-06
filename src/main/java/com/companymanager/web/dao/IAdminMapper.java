package com.companymanager.web.dao;

import com.companymanager.entity.*;
import com.companymanager.entity.condition.EmployeeCondition;
import com.companymanager.entity.condition.Position;
import com.companymanager.entity.condition.SalaryOrderTopic;
import com.companymanager.web.dao.sqlcondition.SQLProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.jdbc.core.SqlProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface IAdminMapper {

    @Select("select count(*) from root_hr where admin = #{name} and password = #{pass}")
    int queryAdmin(Map<String,String> map);


    @Update("UPDATE  employee_info SET " +
            "emp_name = #{empName},emp_card=#{empCard},emp_sex=#{empSex},emp_educ=#{empEduc}, " +
            "emp_dept=#{empDept},emp_deptid=#{empDeptid},emp_pos_name=#{empPosName},emp_phone=#{empPhone},emp_address=#{empAddress},emp_mail=#{empMail},emp_grade=#{empGrade}" +
            " where emp_id = #{empId} ")
    int updateEmployeeInfo(Employee emp);

    @Update("update  salary_info set sar_basic = #{sarBasic},sar_merits = #{sarMerits} where emp_id = #{empId}")
    int updateEmpSalary(EmployeeCondition emp);

    //查看所有员工
    @Select("SELECT emp.*,sal.sar_basic ,sal.sar_merits FROM employee_info as emp,salary_info as sal\n" +
            "where emp.emp_id = sal.emp_id;")
    List<EmployeeCondition> queryAllEmployee();

    @SelectProvider(value = SQLProvider.class,method = "highSearch")
    List<EmployeeCondition> highSearch(Map<String,String> map);

//    //审批员工的状态
//        // 1. 查看未审批的员工
//    @Select("select * from employee_info where emp_status = 0")
//    List<Employee> queryNoAccessEmp();
//
//        // 2.修改审批状态 同意审批
//    @Update("update employee_info  set emp_status = 1 where emp_id = #{empId} ")
//    int updateEmployeeStatus(Map<String,String> map);
//        // 3.设置绩效和基本工资
     @Insert("insert into salary_info (emp_id, sar_basic, sar_merits,  sal_merits_precent)\n" +
             "values(#{empId},#{sarBasic},#{sarMerits},100);")
    int insertSalary(EmployeeCondition employeeCondition);

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

    @Update("UPDATE util_info\n" +
            "SET\n" +
            "util_mark = #{utilMark} ,\n" +
            "util_eat_sub = #{utilEatSub},\n" +
            "util_traffic_sub = #{utilTrafficSub},\n" +
            "util_late_money =#{utilLateMoney} ,\n" +
            "util_exit_money = #{utilExitMoney},\n" +
            "util_late_time = #{utilLateTime},\n" +
            "util_exit_time = #{utilExitTime},\n" +
            "util_hoilday_money = #{utilHoildayMoney},\n" +
            "util_overtime_money = #{utilOvertimeMoney}\n" +
            "WHERE id = #{id};")
    int updateUtilInfo(UtilInfo utilInfo);

    //查看各部门的人数
    @Select("select dep_id,dep_name,pos_name  ,count(emp_pos_name) as total from dept_position\n" +
            "left join  dept_info on dept_id = dep_id\n" +
            "left join employee_info on emp_deptid = dep_id AND emp_pos_name = pos_name\n" +
            "group by(emp_pos_name) ")
    List<Map<String,Integer> > queryDeptSumByDeptId();

    //查看所有部门职位信息  包括高级搜索
    @SelectProvider(value = SQLProvider.class,method = "searchPositionInfo")
    List<Position> queryPositionInfo(Map<String,String> map);

    //新增职位 分为 新增部门和职位
    //1. 新增部门
    @Insert("insert into dept_info(dept_name,dept_info)values(#{deptName},#{deptInfo})")
    int insertNewDept(DeptInfo deptInfo);

    //2. 新增职位
    @Insert("insert into dept_position(dept_id,pos_name)values(#{deptId},#{posName}); ")
    int insertNewPosition(DeptInfo deptInfo);


    //3.删除部门  自动删除下边的职位
    @Delete("delete from dept_info where dep_id = #{depId} ; delete from dept_position where dept_id = #{depId} ;")
    int deleteDeptById(int depId);

    //4. 删除职位
    @Delete("delete from dept_position where pos_id = #{posId}")
    int deletePositionById(int posId);




    //结算工资：
    @SelectProvider(value = SQLProvider.class,method = "querySalary")
    List<SalaryOrderTopic> queryEveryEmpSalary();
    //保存工资
    @InsertProvider(value = SQLProvider.class,method = "saveSalaryRecord")
    int saveSalaryRecord(List<SalaryOrderTopic> list);
}
