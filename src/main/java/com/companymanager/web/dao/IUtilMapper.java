package com.companymanager.web.dao;

import com.companymanager.entity.condition.EchartsCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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

    //查看部门下的人数
    @Select("select dep_name as name,count(*) as value from dept_info left join employee_info on emp_deptid = dep_id\n" +
            "group by(dep_name);")
    List<EchartsCondition> queryNumberSumByDeptName();

}
