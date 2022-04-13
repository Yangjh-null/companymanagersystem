package com.companymanager.web.dao;

import com.companymanager.entity.UtilInfo;
import com.companymanager.entity.condition.EchartsCondition;
import com.companymanager.entity.condition.EchartsConditionToOne;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface IUtilMapper {

    @Select("select dep_id as depId, dep_name as depName from dept_info;")
    List<Map<Integer,String>> queryDeptInfo();

    //根据部门查看该部门所有职位名称
    @Select("select pos_name from dept_position where dept_id = #{deptId};")
    List<String> queryPositionByDept(int deptId);

    //根据部门id 查看当前部门的名称
    @Select("select dep_name from dept_info where dep_id = #{depId} ")
    String queryDepNameByDepId(int depId);

    @Select("select dep_id,dep_name,pos_name  ,count(emp_pos_name) as total from dept_position\n" +
            "left join  dept_info on dept_id = dep_id\n" +
            "left join employee_info on emp_deptid = dep_id AND emp_pos_name = pos_name\n" +
            "group by(emp_pos_name) ;")
    List<EchartsConditionToOne> queryPositionNum();

    //查看部门下的人数
    @Select("select dep_name as name,count(*) as value from dept_info left join employee_info on emp_deptid = dep_id\n" +
            "group by(dep_name);")
    List<EchartsCondition> queryNumberSumByDeptName();


}
